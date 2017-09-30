package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by dell on 2017/6/27.
 */

public class LYTVideoMessageBody extends LYTMessageBody {


    public LYTVideoMessageBody(Parcel in) {
        this.lytObject = new LYTZVideoMessageBody(in);
    }

    public LYTVideoMessageBody(LYTZVideoMessageBody lytzVideoMessageBody) {
        this.lytObject = lytzVideoMessageBody;
    }

    public LYTVideoMessageBody(int roomStatus, String roomId, String roomName, int callType) {
        this.lytObject = new LYTZVideoMessageBody(roomStatus, roomId, roomName, callType);
    }

    public void setCreatorId(String creatorId) {
        ((LYTZVideoMessageBody) lytObject).setCreatorId(creatorId);
    }

    public void setDuration(String duration) {
        ((LYTZVideoMessageBody) lytObject).setDuration(duration);
    }


    public int getRoomStatus() {
        return ((LYTZVideoMessageBody) lytObject).getRoomStatus();
    }


    public String getRoomId() {
        return ((LYTZVideoMessageBody) lytObject).getRoomId();
    }


    public String getRoomName() {
        return ((LYTZVideoMessageBody) lytObject).getRoomName();
    }

    public String getCreatorId() {
        return ((LYTZVideoMessageBody) lytObject).getCreatorId();
    }


    public int getCallType() {
        return ((LYTZVideoMessageBody) lytObject).getCallType();
    }


    public String getDuration() {
        return ((LYTZVideoMessageBody) lytObject).getDuration();
    }


//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(((LYTZVideoMessageBody) this.lytObject).getRoomStatus());
//        dest.writeString(((LYTZVideoMessageBody) this.lytObject).getRoomId());
//        dest.writeString(((LYTZVideoMessageBody) this.lytObject).getRoomName());
//        dest.writeString(((LYTZVideoMessageBody) this.lytObject).getCreatorId());
//        dest.writeInt(((LYTZVideoMessageBody) this.lytObject).getCallType());
//        dest.writeString(((LYTZVideoMessageBody) this.lytObject).getDuration());
//
//
//    }
//
//    public static final Creator<LYTVideoMessageBody> CREATOR = new Creator<LYTVideoMessageBody>() {
//        @Override
//        public LYTVideoMessageBody createFromParcel(Parcel in) {
//            return new LYTVideoMessageBody(in);
//        }
//
//        @Override
//        public LYTVideoMessageBody[] newArray(int size) {
//            return new LYTVideoMessageBody[size];
//        }
//    };
}
