package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/7/6.
 */

public class LYTMassAidesMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTMassAidesMessageBody(Parcel in) {
        this.lytObject = new LYTZMassAidesMessageBody(in);
    }

    public LYTMassAidesMessageBody(String context) {
        this.lytObject = new LYTZMassAidesMessageBody(context);
    }

    public LYTMassAidesMessageBody(LYTZMassAidesMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getText() {
        return ((LYTZMassAidesMessageBody) lytObject).getText();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZMassAidesMessageBody) this.lytObject).getText());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTMassAidesMessageBody> CREATOR = new Creator<LYTMassAidesMessageBody>() {
        @Override
        public LYTMassAidesMessageBody createFromParcel(Parcel in) {
            return new LYTMassAidesMessageBody(in);
        }

        @Override
        public LYTMassAidesMessageBody[] newArray(int size) {
            return new LYTMassAidesMessageBody[size];
        }
    };
}
