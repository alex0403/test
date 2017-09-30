package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/7/21.
 */

public class LYTZRevokeMessageBody extends LYTZMessageBody implements Serializable {


    private String messageId;

    @Override
    public String getMessageType() {
        return LYTMessage.Type.REVOKE.getName();
    }

    protected LYTZRevokeMessageBody(Parcel in) {
        messageId = in.readString();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public LYTZRevokeMessageBody(String messageId) {
        this.messageId = messageId;
    }
}

