package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/8/18.
 */

public class LYTZSimpleVideoMessageBody extends LYTZMessageBody implements Serializable {


    /**
     * eventType : 1
     * callType : 1
     */

    private int eventType;  //拨打电话
    private int callType;  //1--音频通话，2--视频通话

    public LYTZSimpleVideoMessageBody(int eventType, int callType) {
        this.eventType=eventType;
        this.callType=callType;
    }

    public int getEventType() {
        return eventType;
    }

    @Override
    public String getMessageType() {
        return LYTMessage.Type.SIMPLE_VIDEO.getName();
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }
}
