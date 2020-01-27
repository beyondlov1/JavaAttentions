package com.beyond.websocket.spring.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@ServerEndpoint("/users")
@Component
public class UserEndPoint {

    private ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Set<String> onlineUserIds = ChatEndPoint.wsSessionMap.keySet();
        Properties properties = StringUtil.parseQueryString(session);
        String userId = properties.getProperty("userId");
        HashSet<String> onlineUserIdsWithoutMe = new HashSet<>(onlineUserIds);
        onlineUserIdsWithoutMe.remove(userId);
        echo(objectMapper.writeValueAsString(onlineUserIdsWithoutMe),session);
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
    public void onClose() {
        System.out.println("close");
    }
}
