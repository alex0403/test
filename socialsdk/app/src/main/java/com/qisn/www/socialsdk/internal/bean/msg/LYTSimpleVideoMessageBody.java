package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/8/18.
 */

public class LYTSimpleVideoMessageBody extends LYTMessageBody {


    public LYTSimpleVideoMessageBody(LYTZSimpleVideoMessageBody lytzSimpleVideoMessageBody) {
        this.lytObject = lytzSimpleVideoMessageBody;
    }

    public LYTSimpleVideoMessageBody(int eventType, int callType) {
        this.lytObject = new LYTZSimpleVideoMessageBody(eventType, callType);
    }


    public int getCallType() {
        return ((LYTZSimpleVideoMessageBody) lytObject).getCallType();
    }


    public int getEventType() {
        return ((LYTZSimpleVideoMessageBody) lytObject).getEventType();
    }


}
