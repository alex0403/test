package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTFileMessageBody;

import java.io.File;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTVoiceMessageBody extends LYTFileMessageBody implements Parcelable {


    private int duration;

    private String audioUrl;




    public static final Creator<LYTVoiceMessageBody> CREATOR = new Creator<LYTVoiceMessageBody>() {
        @Override
        public LYTVoiceMessageBody createFromParcel(Parcel in) {
            return new LYTVoiceMessageBody(in);
        }

        @Override
        public LYTVoiceMessageBody[] newArray(int size) {
            return new LYTVoiceMessageBody[size];
        }
    };

    public LYTVoiceMessageBody(String var1) {
        super(var1);
    }

    public LYTVoiceMessageBody(Parcel var1) {
        super("", 4);
        ((LYTZVoiceMessageBody) this.lytObject).setDisplayName(var1.readString());
        ((LYTZVoiceMessageBody) this.lytObject).setLocalPath(var1.readString());
//        ((LYTZVoiceMessageBody) this.lytObject).setRemotePath(var1.readString());
        ((LYTZVoiceMessageBody) this.lytObject).setDuration(var1.readInt());
    }

    public LYTVoiceMessageBody(File voicePath, int length) {
        super(voicePath.getAbsolutePath(), 4);
        ((LYTZVoiceMessageBody) this.lytObject).setDuration(length);
    }

    public LYTVoiceMessageBody(LYTZVoiceMessageBody lytzMessageBody) {
        super(lytzMessageBody);
    }

    public int getDuration() {
        return ((LYTZVoiceMessageBody) this.lytObject).getDuration();
    }

    public String getAudioUrl() {
        return ((LYTZVoiceMessageBody) this.lytObject).getAudioUrl();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(((LYTZVoiceMessageBody) this.lytObject).getDisplayName());
        dest.writeString(((LYTZVoiceMessageBody) this.lytObject).getLocalUrl());
//        dest.writeString(((LYTZVoiceMessageBody) this.lytObject).getRemoteUrl());
        dest.writeInt(((LYTZVoiceMessageBody) this.lytObject).getDuration());


    }
}
