package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/6/22.
 */

public class LYTNotificationBody extends LYTMessageBody implements Parcelable {


    public LYTNotificationBody(Parcel in) {
        this.lytObject = new LYTZTextMessageBody(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(((LYTZNotificationBody) this.lytObject).getAttach());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getContent());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getCreateBy());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getCreateId());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getHasAttach());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getNotificationId());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getTargetId());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getTitle());
        dest.writeString(((LYTZNotificationBody) this.lytObject).getUpdateBy());
        dest.writeLong(((LYTZNotificationBody) this.lytObject).getCreateTime());
        dest.writeLong(((LYTZNotificationBody) this.lytObject).getUpdateTime());
        dest.writeInt(((LYTZNotificationBody) this.lytObject).getReadState());



    }

    public static final Creator<LYTNotificationBody> CREATOR = new Creator<LYTNotificationBody>() {
        @Override
        public LYTNotificationBody createFromParcel(Parcel in) {
            return new LYTNotificationBody(in);
        }

        @Override
        public LYTNotificationBody[] newArray(int size) {
            return new LYTNotificationBody[size];
        }
    };
}
