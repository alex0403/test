package com.qisn.www.socialsdk.internal.bean;

import java.util.List;

/**
 * Created by dell on 2017/5/25.
 */

public class LYTMResultList<T>  {

    private String messageType;

    private String sessionId;

    private long chatIndex;


    private List<T> content;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getChatIndex() {
        return chatIndex;
    }

    public void setChatIndex(long chatIndex) {
        this.chatIndex = chatIndex;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }




}

