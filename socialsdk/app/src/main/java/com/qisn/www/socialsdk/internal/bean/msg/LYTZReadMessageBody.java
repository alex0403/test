package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/6/28.
 */

public class LYTZReadMessageBody extends LYTZMessageBody implements Serializable {


    private long readIndex;

    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public LYTZReadMessageBody(long readIndex,String messageId) {
        this.readIndex=readIndex;
        this.messageId = messageId;
    }


    @Override
    public String getMessageType() {

        return LYTMessage.Type.READ_RECEIPT.getName();
    }

    public long getReadIndex() {
        return readIndex;
    }

    public void setReadIndex(long readIndex) {
        this.readIndex = readIndex;
    }
}
