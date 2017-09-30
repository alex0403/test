package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/6/8.
 */

public class LYTReadModelMesaageBody extends LYTMessageBody implements Parcelable {
    protected LYTReadModelMesaageBody(Parcel in) {
        this.lytObject = new LYTZReadModelMesaageBody(in.readString());
    }

    public LYTReadModelMesaageBody(String messageType) {
        this.lytObject = new LYTZReadModelMesaageBody(messageType);
    }

    public LYTReadModelMesaageBody(LYTZReadModelMesaageBody messageBody) {
        lytObject = messageBody;
    }


    public String getText() {
        return ((LYTZReadModelMesaageBody) lytObject).getMessageType();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZReadModelMesaageBody) this.lytObject).getMessageType());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTReadModelMesaageBody> CREATOR = new Creator<LYTReadModelMesaageBody>() {
        @Override
        public LYTReadModelMesaageBody createFromParcel(Parcel in) {
            return new LYTReadModelMesaageBody(in);
        }

        @Override
        public LYTReadModelMesaageBody[] newArray(int size) {
            return new LYTReadModelMesaageBody[size];
        }
    };
}
