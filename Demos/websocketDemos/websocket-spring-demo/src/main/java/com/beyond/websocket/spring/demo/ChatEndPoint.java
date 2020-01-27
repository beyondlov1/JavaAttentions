package com.beyond.websocket.spring.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Component
public class ChatEndPoint {

    public static Map<String, Session> wsSessionMap = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open");
        Properties properties = StringUtil.parseQueryString(session);
        String userId = properties.getProperty("userId");
        if (wsSessionMap.get(userId) != null && wsSessionMap.get(userId).isOpen()){
            return;
        }
        wsSessionMap.put(userId, session);
        System.out.println(wsSessionMap);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);

        ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);
        String toUserId = chatMessage.getToUserId();
        if (StringUtils.isNotBlank(toUserId)) {
            sendTo(toUserId,message);
            if (!StringUtils.equals(toUserId,StringUtil.parseQueryString(session).getProperty("userId"))){
                echo(message,session);
            }
        }else {
            fanOut(message, wsSessionMap.values());
        }
    }

    private void sendTo(String toUserId, String message) throws IOException {
        Session session = wsSessionMap.get(toUserId);
        if (session!=null){
            session.getBasicRemote().sendText(message);
        }
    }

    private void fanOut(String message, Collection<Session> sessions) throws IOException {
        for (Session session : sessions) {
            if (session.isOpen()){
                session.getBasicRemote().sendText(message);
            }
        }
    }

    private void echo(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("error");
        error.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close");
        for (String s : wsSessionMap.keySet()) {
            if (wsSessionMap.get(s) == session){
                wsSessionMap.remove(s);
                return;
            }
        }
    }
}
