package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTHybridMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTHybridMessageBody(Parcel in) {
//        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTHybridMessageBody> CREATOR = new Creator<LYTHybridMessageBody>() {
        @Override
        public LYTHybridMessageBody createFromParcel(Parcel in) {
            return new LYTHybridMessageBody(in);
        }

        @Override
        public LYTHybridMessageBody[] newArray(int size) {
            return new LYTHybridMessageBody[size];
        }
    };
}
