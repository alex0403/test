package com.qisn.www.socialsdk.internal.bean;

import com.qisn.www.socialsdk.base.BaseBean;

/**
 * Created by dell on 2017/5/23.
 */

public class LYTMResult<T> extends BaseBean {

    private String messageType;

    private String sessionId;

    private long chatIndex;


    private T content;

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

    public T getContent() {
        return content;
    }


    public void setContent(T content) {
        this.content = content;
    }
}
