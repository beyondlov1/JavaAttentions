package com.beyond.websocket.spring.demo;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/invite")
public class InviteEndPoint extends AbstractBusiEndPoint{

    public static Map<String, Session> wsSessionMap = new ConcurrentHashMap<>();

    public static Map<String, InviteStatus> userStatusMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Session> getWsSessionMap() {
        return wsSessionMap;
    }

    @Override
    protected BasicMessage getToSendMessage(String message) throws IOException {
        InviteMessage inviteMessage = objectMapper.readValue(message, InviteMessage.class);
        List<InviteStatus>inviteStatuses = inviteMessage.getMsg();
        int size = inviteStatuses.size();
        if (size == 2){
            if(checkUserStatus(inviteStatuses)){
                assignRoles(inviteStatuses);
                inviteMessage.setSuccess(true);
                for (InviteStatus inviteStatus : inviteStatuses) {
                    if (!StringUtils.equals(inviteStatus.getUserId(),inviteMessage.getToUserId())){
                        InviteMessage newInviteMessage = new InviteMessage();
                        BeanUtils.copyProperties(inviteMessage,newInviteMessage);
                        newInviteMessage.setUserId(inviteMessage.getToUserId());
                        newInviteMessage.setToUserId(inviteMessage.getUserId());
                        newInviteMessage.setUserName(UserEndPoint.userMap.get(newInviteMessage.getUserId()).getUserName());
                        sendTo(inviteStatus.getUserId(),objectMapper.writeValueAsString(newInviteMessage));
                    }
                }
                return inviteMessage;
            }else {
                inviteMessage.setSuccess(false);
                return inviteMessage;
            }
        }else if (size == 1){
            return inviteMessage;
        }
        return null;
    }

    private boolean checkUserStatus(List<InviteStatus> inviteStatuses) {
        for (InviteStatus inviteStatus : inviteStatuses) {
            InviteStatus savedInviteStatus = userStatusMap.get(inviteStatus.getUserId());
            if (savedInviteStatus!=null
                    && !Integer.valueOf(0).equals(savedInviteStatus.getStatus())
                    && !Integer.valueOf(1).equals(savedInviteStatus.getStatus()) ){
                return false;
            }
        }
        return true;
    }

    private void assignRoles(List<InviteStatus> inviteStatuses) {
        boolean b = RandomUtils.nextBoolean();
        inviteStatuses.get(0).setType(b?1:4);
        inviteStatuses.get(1).setType(!b?1:4);
        for (InviteStatus inviteStatus : inviteStatuses) {
            inviteStatus.setStatus(2);
            userStatusMap.put(inviteStatus.getUserId(), inviteStatus);
        }
    }

    @Override
    protected boolean echo() {
        return false;
    }
}

