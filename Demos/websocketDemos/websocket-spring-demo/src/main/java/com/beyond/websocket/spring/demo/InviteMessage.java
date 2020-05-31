package com.beyond.websocket.spring.demo;

import java.util.List;

public class InviteMessage extends BasicMessage{
    private List<InviteStatus> msg;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<InviteStatus> getMsg() {
        return msg;
    }

    public void setMsg(List<InviteStatus> msg) {
        this.msg = msg;
    }
}
