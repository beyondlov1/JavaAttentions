package com.beyond.websocket.spring.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/goodBye")
public class GoodByeEndPoint extends AbstractBusiEndPoint {

    private static Map<String, Session> wsSessionMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Session> getWsSessionMap() {
        return wsSessionMap;
    }

    @Override
    public void onMessageInternal(String message, Session session) throws IOException {
        BasicMessage toSendMessage = getToSendMessage(message);
        String toUserId = toSendMessage.getToUserId();
        message = objectMapper.writeValueAsString(toSendMessage);
        if (StringUtils.isNotBlank(toUserId)) {
            sendTo(toUserId, message);
        }
    }

    @Override
    protected BasicMessage getToSendMessage(String message) throws IOException {
        BasicMessage basicMessage = objectMapper.readValue(message, BasicMessage.class);

        if (StringUtils.isNotBlank(basicMessage.getUserId()) ){
            InviteStatus inviteStatus = InviteEndPoint.userStatusMap.get(basicMessage.getUserId());
            if (inviteStatus!=null){
                inviteStatus.setStatus(1);
                inviteStatus.setType(null);
            }
        }
        if (StringUtils.isNotBlank(basicMessage.getToUserId())){
            InviteStatus toUserInviteStatus = InviteEndPoint.userStatusMap.get(basicMessage.getToUserId());
            if (toUserInviteStatus!=null){
                toUserInviteStatus.setStatus(1);
                toUserInviteStatus.setType(null);
            }
        }
        return basicMessage;
    }

    @Override
    public void onClose(Session session) {
        Properties properties = StringUtil.parseQueryString(session);
        String userId = properties.getProperty("userId");
        InviteEndPoint.userStatusMap.get(userId).setStatus(1);
        InviteEndPoint.userStatusMap.get(userId).setType(null);
        super.onClose(session);
    }

    @Override
    protected boolean echo() {
        return false;
    }
}
