package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/8/9.
 */

public class LYTGiftMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTGiftMessageBody(Parcel in) {
        this.lytObject = new LYTZGiftMessageBody(in.readString());
    }

    public LYTGiftMessageBody() {
        this.lytObject = new LYTZGiftMessageBody();
    }

    public LYTGiftMessageBody(LYTZGiftMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getBodyMsg() {
        return ((LYTZGiftMessageBody) lytObject).getBodyMsg();
    }

    public String getImageUrl() {
        return ((LYTZGiftMessageBody) lytObject).getImageUrl();
    }

    public String getTailMsg() {
        return ((LYTZGiftMessageBody) lytObject).getTailMsg();
    }

    public void setBodyMsg(String bodyMsg) {
        ((LYTZGiftMessageBody) lytObject).setBodyMsg(bodyMsg);
    }

    public void setImageUrl(String imageUrl) {
        ((LYTZGiftMessageBody) lytObject).setImageUrl(imageUrl);
    }

    public void setTailMsg(String tailMsg) {
        ((LYTZGiftMessageBody) lytObject).setTailMsg(tailMsg);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZGiftMessageBody) this.lytObject).getTailMsg());
        dest.writeString(((LYTZGiftMessageBody) this.lytObject).getBodyMsg());
        dest.writeString(((LYTZGiftMessageBody) this.lytObject).getImageUrl());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTGiftMessageBody> CREATOR = new Creator<LYTGiftMessageBody>() {
        @Override
        public LYTGiftMessageBody createFromParcel(Parcel in) {
            return new LYTGiftMessageBody(in);
        }

        @Override
        public LYTGiftMessageBody[] newArray(int size) {
            return new LYTGiftMessageBody[size];
        }
    };
}