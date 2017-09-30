package com.qisn.www.socialsdk.internal.bean.msg;

/**
 * Created by Administrator on 2017/2/8.
 */

public class LYTReceiptMessage {

    private String messageId;

    private String sessionId;

    private long chatIndex;

    private long sendTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
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

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

}
