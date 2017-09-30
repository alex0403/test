package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/7/6.
 */

public class LYTZMassAidesMessageBody extends LYTZMessageBody implements Serializable {


    private String text;

    protected LYTZMassAidesMessageBody(Parcel in) {
        text = in.readString();
    }

    @Override
    public String getMessageType() {
        return MASS_AIDES;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LYTZMassAidesMessageBody(String s) {
        this.text = s;

    }
}
