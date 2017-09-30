package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/6/8.
 */

public class LYTZSystemMessageBody extends LYTZMessageBody implements Serializable {


    private int systemType;

    private String text;

    protected LYTZSystemMessageBody(Parcel in) {
        text = in.readString();
    }

    @Override
    public String getMessageType() {

        return LYTMessage.Type.SYSTEMMESSAGE.getName();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getSystemType() {
        return systemType;
    }

    public void setSystemType(int systemType) {
        this.systemType = systemType;
    }

    public LYTZSystemMessageBody(String s, int systemType) {
        this.text = s;
        this.systemType=systemType;

    }
}
