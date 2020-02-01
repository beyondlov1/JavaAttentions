package com.beyond.websocket.spring.demo;

public class ChatMessage extends BasicMessage{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
