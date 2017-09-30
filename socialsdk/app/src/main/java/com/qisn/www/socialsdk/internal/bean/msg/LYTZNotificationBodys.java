package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2017/6/22.
 */

public class LYTZNotificationBodys extends LYTZMessageBody implements Serializable {

    private List<LYTZNotificationBody> lytzNotificationBodies;


    public List<LYTZNotificationBody> getLytzNotificationBodies() {
        return lytzNotificationBodies;
    }

    public void setLytzNotificationBodies(List<LYTZNotificationBody> lytzNotificationBodies) {
        this.lytzNotificationBodies = lytzNotificationBodies;
    }

    @Override
    public String getMessageType() {

        return LYTMessage.Type.NOTIFICATION.getName();
    }

}
