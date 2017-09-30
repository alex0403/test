package com.qisn.www.socialsdk.internal.bean.msg;


import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

public class LYTSystemMessageBody extends LYTMessageBody {

    protected LYTSystemMessageBody(Parcel in) {
        this.lytObject = new LYTZSystemMessageBody(in.readString(),in.readInt());
    }

    public LYTSystemMessageBody(String context, int systemType) {
        this.lytObject = new LYTZSystemMessageBody(context,systemType);
    }

    public LYTSystemMessageBody(LYTZSystemMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getText() {
        return ((LYTZSystemMessageBody) lytObject).getText();
    }

    public int getSystemType() {
        return ((LYTZSystemMessageBody) lytObject).getSystemType();
    }

//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//        dest.writeString(((LYTZSystemMessageBody) this.lytObject).getText());
//    }
//
//
//    public static final Creator<LYTSystemMessageBody> CREATOR = new Creator<LYTSystemMessageBody>() {
//        @Override
//        public LYTSystemMessageBody createFromParcel(Parcel in) {
//            return new LYTSystemMessageBody(in);
//        }
//
//        @Override
//        public LYTSystemMessageBody[] newArray(int size) {
//            return new LYTSystemMessageBody[size];
//        }
//    };

}
