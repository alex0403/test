package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

/**
 * Created by Administrator on 2017/3/1.
 */
public class LYTZCMDMessageBody extends LYTZMessageBody {


    private String content;

    private String action;

    public LYTZCMDMessageBody(String s, String action) {
        this.content = s;
        this.action = action;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    protected LYTZCMDMessageBody(Parcel in) {
        content = in.readString();
        action = in.readString();
    }

    @Override
    public String getMessageType() {

        return MESSAGE_CMD;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
