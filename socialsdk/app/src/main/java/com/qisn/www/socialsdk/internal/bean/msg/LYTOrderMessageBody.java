package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/8/9.
 */

public class LYTOrderMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTOrderMessageBody(Parcel in) {
        this.lytObject = new LYTZOrderMessageBody(in.readString());
    }

    public LYTOrderMessageBody() {
        this.lytObject = new LYTZOrderMessageBody();
    }

    public LYTOrderMessageBody(LYTZOrderMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getBodyMsg() {
        return ((LYTZOrderMessageBody) lytObject).getBodyMsg();
    }

    public String getJumpMsg() {
        return ((LYTZOrderMessageBody) lytObject).getJumpMsg();
    }

    public String getTailMsg() {
        return ((LYTZOrderMessageBody) lytObject).getTailMsg();
    }

    public String getTitle() {
        return ((LYTZOrderMessageBody) lytObject).getTitle();
    }


    public void setBodyMsg(String bodyMsg) {
        ((LYTZOrderMessageBody) lytObject).setBodyMsg(bodyMsg);
    }

    public void setJumpMsg(String imageUrl) {
        ((LYTZOrderMessageBody) lytObject).setJumpMsg(imageUrl);
    }

    public void setTitle(String tailMsg) {
        ((LYTZOrderMessageBody) lytObject).setTitle(tailMsg);

    }

    public void setTailMsg(String tailMsg) {
        ((LYTZOrderMessageBody) lytObject).setTailMsg(tailMsg);

    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZOrderMessageBody) this.lytObject).getBodyMsg());
        dest.writeString(((LYTZOrderMessageBody) this.lytObject).getJumpMsg());
        dest.writeString(((LYTZOrderMessageBody) this.lytObject).getTailMsg());
        dest.writeString(((LYTZOrderMessageBody) this.lytObject).getTitle());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTOrderMessageBody> CREATOR = new Creator<LYTOrderMessageBody>() {
        @Override
        public LYTOrderMessageBody createFromParcel(Parcel in) {
            return new LYTOrderMessageBody(in);
        }

        @Override
        public LYTOrderMessageBody[] newArray(int size) {
            return new LYTOrderMessageBody[size];
        }
    };
}