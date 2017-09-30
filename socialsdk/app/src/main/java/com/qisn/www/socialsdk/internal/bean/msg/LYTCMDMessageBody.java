package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by Administrator on 2017/3/1.
 */

public class LYTCMDMessageBody extends LYTMessageBody implements Parcelable {


    public LYTCMDMessageBody(String action, String content) {
        this.lytObject = new LYTZCMDMessageBody(content, action);
        ((LYTZCMDMessageBody) this.lytObject).setAction(action);
        ((LYTZCMDMessageBody) this.lytObject).setContent(content);
    }

    protected LYTCMDMessageBody(Parcel in) {
        ((LYTZCMDMessageBody) this.lytObject).setAction(in.readString());
        ((LYTZCMDMessageBody) this.lytObject).setContent(in.readString());

        this.lytObject = new LYTZTextMessageBody(in.readString());
    }

    public LYTCMDMessageBody(LYTZCMDMessageBody messageBody) {
        this.lytObject =messageBody;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(((LYTZCMDMessageBody) this.lytObject).getContent());
        dest.writeString(((LYTZCMDMessageBody) this.lytObject).getAction());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTCMDMessageBody> CREATOR = new Creator<LYTCMDMessageBody>() {
        @Override
        public LYTCMDMessageBody createFromParcel(Parcel in) {
            return new LYTCMDMessageBody(in);
        }

        @Override
        public LYTCMDMessageBody[] newArray(int size) {
            return new LYTCMDMessageBody[size];
        }
    };
}
