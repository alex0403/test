package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTZTextMessageBody extends LYTZMessageBody implements Serializable {

    private String text;

    protected LYTZTextMessageBody(Parcel in) {
        text = in.readString();
    }



    @Override
    public String getMessageType() {

        return LYTMessage.Type.TXT.getName();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LYTZTextMessageBody(String s) {
        this.text = s;

    }
}
