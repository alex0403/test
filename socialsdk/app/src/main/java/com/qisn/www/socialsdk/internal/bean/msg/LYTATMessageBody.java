package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

import java.util.List;

/**
 * Created by dell on 2017/6/27.
 */

public class LYTATMessageBody extends LYTMessageBody implements Parcelable {


    public LYTATMessageBody(Parcel in) {
        this.lytObject = new LYTZATMessageBody();
    }

    public LYTATMessageBody(List<LYTATMessageInfo> lytatMessageInfos) {
        this.lytObject = new LYTZATMessageBody(lytatMessageInfos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public LYTATMessageBody() {
        this.lytObject = new LYTZATMessageBody();
    }

    public LYTATMessageBody(LYTZATMessageBody lytzatMessageBody) {
        this.lytObject = lytzatMessageBody;
    }

    public List<LYTATMessageInfo> getLytatMessageInfos() {
        return  ((LYTZATMessageBody) this.lytObject).getLytatMessageInfos();
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(((LYTZATMessageBody) this.lytObject).getLytatMessageInfos());

    }

    public static final Creator<LYTATMessageBody> CREATOR = new Creator<LYTATMessageBody>() {
        @Override
        public LYTATMessageBody createFromParcel(Parcel in) {
            return new LYTATMessageBody(in);
        }

        @Override
        public LYTATMessageBody[] newArray(int size) {
            return new LYTATMessageBody[size];
        }
    };
}
