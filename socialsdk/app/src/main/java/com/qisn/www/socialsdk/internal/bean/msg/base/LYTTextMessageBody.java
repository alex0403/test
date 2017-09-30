package com.qisn.www.socialsdk.internal.bean.msg.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.LYTZTextMessageBody;

/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTTextMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTTextMessageBody(Parcel in) {
        this.lytObject = new LYTZTextMessageBody(in.readString());
    }

    public LYTTextMessageBody(String context) {
        this.lytObject = new LYTZTextMessageBody(context);
    }

    public LYTTextMessageBody(LYTZTextMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getText() {
        return ((LYTZTextMessageBody) lytObject).getText();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZTextMessageBody) this.lytObject).getText());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTTextMessageBody> CREATOR = new Creator<LYTTextMessageBody>() {
        @Override
        public LYTTextMessageBody createFromParcel(Parcel in) {
            return new LYTTextMessageBody(in);
        }

        @Override
        public LYTTextMessageBody[] newArray(int size) {
            return new LYTTextMessageBody[size];
        }
    };
}
