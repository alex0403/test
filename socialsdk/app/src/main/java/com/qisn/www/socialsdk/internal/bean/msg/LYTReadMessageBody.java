package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/6/28.
 */

public class LYTReadMessageBody  extends LYTMessageBody implements Parcelable {

    public LYTReadMessageBody(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(((LYTZReadMessageBody) this.lytObject).getReadIndex());
    }

    public LYTReadMessageBody(long readIndex,String meaasgeId) {
        this.lytObject = new LYTZReadMessageBody(readIndex,meaasgeId);
    }

    public LYTReadMessageBody(LYTZReadMessageBody messageBody) {
        lytObject = messageBody;
    }

    public String getMessageId() {
        return ((LYTZReadMessageBody)lytObject).getMessageId();
    }
    public long getReadIndex() {
        return ((LYTZReadMessageBody)lytObject).getReadIndex();
    }

    public static final Creator<LYTReadMessageBody> CREATOR = new Creator<LYTReadMessageBody>() {
        @Override
        public LYTReadMessageBody createFromParcel(Parcel in) {
            return new LYTReadMessageBody(in);
        }

        @Override
        public LYTReadMessageBody[] newArray(int size) {
            return new LYTReadMessageBody[size];
        }
    };
}
