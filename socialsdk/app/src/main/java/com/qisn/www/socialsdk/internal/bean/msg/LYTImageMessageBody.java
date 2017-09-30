package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTFileMessageBody;

import java.io.File;

/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTImageMessageBody extends LYTFileMessageBody implements Parcelable {


    public boolean isSendOriginalImage() {
        return isSendOriginalImage;
    }

    private boolean isSendOriginalImage;


    public LYTImageMessageBody(File var1, File var2) {
        super(var1.getAbsolutePath(), 1);
        this.isSendOriginalImage = false;
        ((LYTZImageMessageBody) this.lytObject).setThumbnailLocalPath(var2.getAbsolutePath());
    }

    public LYTImageMessageBody(LYTZImageMessageBody var1) {
        super(var1);
        this.isSendOriginalImage = false;
    }


    LYTImageMessageBody(String displayName, String remotePath, String thumbnailRemotePath) {
        super("", 1);
        this.isSendOriginalImage = false;
        this.lytObject = new LYTZImageMessageBody("", "");
        ((LYTZImageMessageBody) this.lytObject).setDisplayName(displayName);
        ((LYTZImageMessageBody) this.lytObject).setPicUrl(remotePath);
        ((LYTZImageMessageBody) this.lytObject).setThumbnailRemotePath(thumbnailRemotePath);
    }

    public LYTImageMessageBody(File var1, String fileName) {
        super(var1.getAbsolutePath(), 1);
        this.isSendOriginalImage = false;
    }


    public String getThumbnailUrl() {
        return ((LYTZImageMessageBody) this.lytObject).getThumbnailRemotePath();
    }

    public void setThumbnailUrl(String var1) {
        ((LYTZImageMessageBody) this.lytObject).setThumbnailRemotePath(var1);
    }


    public String getDisplayName() {
        return   ((LYTZImageMessageBody) this.lytObject).getDisplayName();
    }



    public String getLocalUrl() {
     return   ((LYTZImageMessageBody) this.lytObject).getLocalUrl();
    }

    public String getPicUrl() {
        return   ((LYTZImageMessageBody) this.lytObject).getPicUrl();
    }

    public String toString() {
        return "picUrl:" + ((LYTZImageMessageBody) this.lytObject).getPicUrl();
    }


    protected LYTImageMessageBody(Parcel in) {
        super("", 1);
        this.isSendOriginalImage = false;
        ((LYTZImageMessageBody) this.lytObject).setDisplayName(in.readString());
        ((LYTZImageMessageBody) this.lytObject).setLocalPath(in.readString());
        ((LYTZImageMessageBody) this.lytObject).setPicUrl(in.readString());
        ((LYTZImageMessageBody) this.lytObject).setThumbnailRemotePath(in.readString());
        ((LYTZImageMessageBody) this.lytObject).setSize(in.readInt(), in.readInt());

    }


    public LYTImageMessageBody(File imagePath) {
        super(imagePath.getAbsolutePath(), 1);
        this.isSendOriginalImage = false;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(((LYTZImageMessageBody) this.lytObject).getDisplayName());
        dest.writeString(((LYTZImageMessageBody) this.lytObject).getLocalUrl());
        dest.writeString(((LYTZImageMessageBody) this.lytObject).getPicUrl());
        dest.writeString(((LYTZImageMessageBody) this.lytObject).getThumbnailRemotePath());
        dest.writeInt(((LYTZImageMessageBody) this.lytObject).getWidth());
        dest.writeInt(((LYTZImageMessageBody) this.lytObject).getHeight());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTImageMessageBody> CREATOR = new Creator<LYTImageMessageBody>() {
        @Override
        public LYTImageMessageBody createFromParcel(Parcel in) {
            return new LYTImageMessageBody(in);
        }

        @Override
        public LYTImageMessageBody[] newArray(int size) {
            return new LYTImageMessageBody[size];
        }
    };

    public void setSendOriginalImage(boolean isSendOriginalImage) {
        this.isSendOriginalImage = isSendOriginalImage;
    }

    public void setSize(int width, int height) {
        ((LYTZImageMessageBody) this.lytObject).setSize(width, height);
    }
    public void setRemotePath(String remotePath) {
        ((LYTZImageMessageBody) this.lytObject).setPicUrl(remotePath);
    }

}
