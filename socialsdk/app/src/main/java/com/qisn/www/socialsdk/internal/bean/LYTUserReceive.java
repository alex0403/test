package com.qisn.www.socialsdk.internal.bean;


/**
 * Created by dell on 2017/7/21.
 */

public class LYTUserReceive  {

    private String userId;

    private  int receiveType;//接收消息类型（1.接收并提醒【默认】；2.接收不提醒（免打扰）；3.屏蔽消息）


    private int chatType;
    private String targetId;
    private String state;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(int receiveType) {
        this.receiveType = receiveType;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
