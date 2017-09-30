package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTFileMessageBody;

import java.io.File;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTNormalFileMessageBody extends LYTFileMessageBody implements Parcelable {

    private String fileName;

    private String fileUrl;

    private long size;

    public LYTNormalFileMessageBody(File file, String size) {
        super(file.getAbsolutePath(), 5);
        ((LYTZNormalFileMessageBody) this.lytObject).setSize(size);
    }

    public LYTNormalFileMessageBody(LYTZNormalFileMessageBody lytzMessageBody) {
        super(lytzMessageBody);
    }


    @Override
    public String getFileName() {
        return  ((LYTZNormalFileMessageBody) this.lytObject).getFileName();
    }

    public String getFileUrl() {
        return  ((LYTZNormalFileMessageBody) this.lytObject).getFileUrl();
    }

    public String getSize() {
        return  ((LYTZNormalFileMessageBody) this.lytObject).size();
    }

    protected LYTNormalFileMessageBody(Parcel in) {
        super("",5);
        ((LYTZFileMessageBody) this.lytObject).setDisplayName(in.readString());
        ((LYTZFileMessageBody) this.lytObject).setLocalPath(in.readString());
        ((LYTZFileMessageBody) this.lytObject).setFileLength(in.readLong());
        ((LYTZFileMessageBody) this.lytObject).setSecretKey(in.readString());


    }

    public static final Creator<LYTNormalFileMessageBody> CREATOR = new Creator<LYTNormalFileMessageBody>() {
        @Override
        public LYTNormalFileMessageBody createFromParcel(Parcel in) {
            return new LYTNormalFileMessageBody(in);
        }
        @Override
        public LYTNormalFileMessageBody[] newArray(int size) {
            return new LYTNormalFileMessageBody[size];
        }
    };
    public LYTNormalFileMessageBody(File file) {
        super(file.getAbsolutePath(),5);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(((LYTZFileMessageBody) this.lytObject).getDisplayName());
        dest.writeString(((LYTZFileMessageBody) this.lytObject).getLocalUrl());
//        dest.writeString(((LYTZFileMessageBody) this.lytObject).getRemoteUrl());
        dest.writeLong(((LYTZFileMessageBody) this.lytObject).getFileLength());
        dest.writeString(((LYTZFileMessageBody) this.lytObject).getSecret());
    }
}
