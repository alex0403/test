package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/8/9.
 */

public class LYTZOrderMessageBody extends LYTZMessageBody implements Serializable {


    /**
     * title : 这是一个转账消息
     * bodyMsg : 支付完成，请查收
     * tailMsg : 立即前往
     * jumpMsg : 跳转消息
     */

    private String title;
    private String bodyMsg;
    private String tailMsg;
    private String jumpMsg;

    public LYTZOrderMessageBody(String s) {

    }

    public LYTZOrderMessageBody() {

    }


    @Override
    public String getMessageType() {

        return LYTMessage.Type.LAWYERORDER.getName();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyMsg() {
        return bodyMsg;
    }

    public void setBodyMsg(String bodyMsg) {
        this.bodyMsg = bodyMsg;
    }

    public String getTailMsg() {
        return tailMsg;
    }

    public void setTailMsg(String tailMsg) {
        this.tailMsg = tailMsg;
    }

    public String getJumpMsg() {
        return jumpMsg;
    }

    public void setJumpMsg(String jumpMsg) {
        this.jumpMsg = jumpMsg;
    }
}