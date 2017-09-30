package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/7/21.
 */

public class LYTRevokeMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTRevokeMessageBody(Parcel in) {
        this.lytObject = new LYTZRevokeMessageBody(in.readString());
    }

    public LYTRevokeMessageBody(String messageId) {
        this.lytObject = new LYTZRevokeMessageBody(messageId);
    }

    public LYTRevokeMessageBody(LYTZRevokeMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getMessageId() {
        return ((LYTZRevokeMessageBody) lytObject).getMessageId();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZRevokeMessageBody) this.lytObject).getMessageId());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTRevokeMessageBody> CREATOR = new Creator<LYTRevokeMessageBody>() {
        @Override
        public LYTRevokeMessageBody createFromParcel(Parcel in) {
            return new LYTRevokeMessageBody(in);
        }

        @Override
        public LYTRevokeMessageBody[] newArray(int size) {
            return new LYTRevokeMessageBody[size];
        }
    };
}
