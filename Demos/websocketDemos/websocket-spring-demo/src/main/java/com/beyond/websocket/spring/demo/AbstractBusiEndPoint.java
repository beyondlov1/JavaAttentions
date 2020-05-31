package com.beyond.websocket.spring.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractBusiEndPoint {

    protected ObjectMapper objectMapper = new ObjectMapper();
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open");
        Properties properties = StringUtil.parseQueryString(session);
        String userId = properties.getProperty("userId");
        if (getWsSessionMap().get(userId) != null && getWsSessionMap().get(userId).isOpen()){
            return;
        }
        getWsSessionMap().put(userId, session);
        System.out.println(getWsSessionMap());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        onMessageInternal(message,session);
    }

    protected void onMessageInternal(String message, Session session) throws IOException {
        BasicMessage toSendMessage = getToSendMessage(message);
        String toUserId = toSendMessage.getToUserId();
        message = objectMapper.writeValueAsString(toSendMessage);
        if (StringUtils.isNotBlank(toUserId)) {
            sendTo(toUserId,message);
            if (echo()){
                if (!StringUtils.equals(toUserId,StringUtil.parseQueryString(session).getProperty("userId"))){
                    echo(message,session);
                }
            }
        }else {
            fanOut(message, getWsSessionMap().values());
        }
    }

    protected abstract BasicMessage getToSendMessage(String message) throws IOException;

    void sendTo(String toUserId, String message) throws IOException {
        Session session = getWsSessionMap().get(toUserId);
        if (session!=null){
            session.getBasicRemote().sendText(message);
        }
    }

    void fanOut(String message, Collection<Session> sessions) throws IOException {
        for (Session session : sessions) {
            if (session.isOpen()){
                session.getBasicRemote().sendText(message);
            }
        }
    }

    void echo(String message, Session session) throws IOException {
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
        for (String s : getWsSessionMap().keySet()) {
            if (getWsSessionMap().get(s) == session){
                getWsSessionMap().remove(s);
                return;
            }
        }
    }

    public abstract Map<String, Session> getWsSessionMap();

    protected boolean echo(){
        return true;
    }
}
