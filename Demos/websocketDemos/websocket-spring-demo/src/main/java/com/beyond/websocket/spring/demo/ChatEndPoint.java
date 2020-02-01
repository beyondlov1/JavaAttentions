package com.beyond.websocket.spring.demo;

import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
@Component
public class ChatEndPoint extends AbstractBusiEndPoint{

    private static Map<String, Session> wsSessionMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Session> getWsSessionMap() {
        return wsSessionMap;
    }

    @Override
    protected BasicMessage getToSendMessage(String message) throws IOException {
        return objectMapper.readValue(message, ChatMessage.class);
    }
}
