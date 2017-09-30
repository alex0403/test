package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/8/9.
 */

public class LYTZGiftMessageBody  extends LYTZMessageBody implements Serializable {

    /**
     * bodyMsg : 您向对方发了一个红包
     * tailMsg : ￥5.00元
     * imageUrl : www.71ant.com/fdf.png
     */

    private String bodyMsg;
    private String tailMsg;
    private String imageUrl;

    public LYTZGiftMessageBody(String s) {

    }

    public LYTZGiftMessageBody() {

    }

    @Override
    public String getMessageType() {

        return LYTMessage.Type.LAWYERGIFT.getName();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}