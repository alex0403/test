package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/7/6.
 */

public class LYTFileReceiptMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTFileReceiptMessageBody(Parcel in) {
        this.lytObject = new LYTZFileReceiptMessageBody(in.readString());
    }

    public LYTFileReceiptMessageBody(String context) {
        this.lytObject = new LYTZFileReceiptMessageBody(context);
    }

    public LYTFileReceiptMessageBody(LYTZFileReceiptMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getText() {
        return ((LYTZFileReceiptMessageBody) lytObject).getText();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZTextMessageBody) this.lytObject).getText());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTZFileReceiptMessageBody> CREATOR = new Creator<LYTZFileReceiptMessageBody>() {
        @Override
        public LYTZFileReceiptMessageBody createFromParcel(Parcel in) {
            return new LYTZFileReceiptMessageBody(in);
        }

        @Override
        public LYTZFileReceiptMessageBody[] newArray(int size) {
            return new LYTZFileReceiptMessageBody[size];
        }
    };
}