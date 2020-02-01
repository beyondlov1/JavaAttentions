package com.beyond.websocket.spring.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/users")
@Component
public class UserEndPoint {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static Map<String,User> userMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Set<String> onlineUserIds = InviteEndPoint.wsSessionMap.keySet();
        Properties properties = StringUtil.parseQueryString(session);
        String userId = properties.getProperty("userId");
        updateUser(userId,message);
        HashSet<String> onlineUserIdsWithoutMe = new HashSet<>(onlineUserIds);
        onlineUserIdsWithoutMe.remove(userId);
        List<User> onlineUsersWithoutMe = new ArrayList<>();
        for (String onlineUserId : onlineUserIdsWithoutMe) {
            onlineUsersWithoutMe.add(userMap.get(onlineUserId));
        }
        echo(objectMapper.writeValueAsString(onlineUsersWithoutMe),session);
    }

    private void updateUser(String userId, String message) throws JsonProcessingException {
        BasicMessage basicMessage = objectMapper.readValue(message, BasicMessage.class);
        String userName = basicMessage.getUserName();
        if (userMap.get(userId) == null){
            User user = new User();
            user.setUserId(userId);
            user.setUserName(userName);
            user.setInviteStatus(InviteEndPoint.userStatusMap.get(userId));
            userMap.put(userId,user);
        }else {
            userMap.get(userId).setUserName(userName);
            userMap.get(userId).setInviteStatus(InviteEndPoint.userStatusMap.get(userId));
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
    public void onClose() {
        System.out.println("close");
    }
}
