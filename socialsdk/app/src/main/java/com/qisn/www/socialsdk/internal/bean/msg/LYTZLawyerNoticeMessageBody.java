package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/8/9.
 */

public class LYTZLawyerNoticeMessageBody extends LYTZMessageBody implements Serializable {


    private String content;

    public LYTZLawyerNoticeMessageBody(String s) {
        this.content = s;

    }

    @Override
    public String getMessageType() {

        return LYTMessage.Type.LAWYERNOTIC.getName();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
