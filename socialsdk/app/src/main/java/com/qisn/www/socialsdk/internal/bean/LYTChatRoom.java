package com.qisn.www.socialsdk.internal.bean;

import com.qisn.www.socialsdk.base.BaseBean;

/**
 * Created by dell on 2017/9/19.
 */

public class LYTChatRoom  extends BaseBean {

    private String roomId;
    private String roomName;
    private int receiveType=1;
    private int chatType;

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public int getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(int receiveType) {
        this.receiveType = receiveType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
