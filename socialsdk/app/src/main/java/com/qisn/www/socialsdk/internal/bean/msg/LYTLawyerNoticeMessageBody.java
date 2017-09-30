package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/8/9.
 */

public class LYTLawyerNoticeMessageBody extends LYTMessageBody implements Parcelable {


    protected LYTLawyerNoticeMessageBody(Parcel in) {
        this.lytObject = new LYTZLawyerNoticeMessageBody(in.readString());
    }

    public LYTLawyerNoticeMessageBody(String context) {
        this.lytObject = new LYTZLawyerNoticeMessageBody(context);
    }

    public LYTLawyerNoticeMessageBody(LYTZLawyerNoticeMessageBody messageBody) {
        lytObject = messageBody;
    }


    public String getContent() {
        return ((LYTZLawyerNoticeMessageBody) lytObject).getContent();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZLawyerNoticeMessageBody) this.lytObject).getContent());
    
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTLawyerNoticeMessageBody> CREATOR = new Creator<LYTLawyerNoticeMessageBody>() {
        @Override
        public LYTLawyerNoticeMessageBody createFromParcel(Parcel in) {
            return new LYTLawyerNoticeMessageBody(in);
        }

        @Override
        public LYTLawyerNoticeMessageBody[] newArray(int size) {
            return new LYTLawyerNoticeMessageBody[size];
        }
    };
}