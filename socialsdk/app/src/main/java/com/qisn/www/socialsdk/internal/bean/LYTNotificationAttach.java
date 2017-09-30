package com.qisn.www.socialsdk.internal.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 2017/6/22.
 */

public class LYTNotificationAttach implements Parcelable {


    /**
     * fileName : 图片文件396.jpg
     * fileType : jpg
     * fileMD5 : ad636acf77e140e470d4e524370e5de0
     * downloadURL : http://192.168.10.229/UP:01/ad636acf77e140e470d4e524370e5de0.jpg
     * createTime : 1481594208967
     * fileSize : 169674
     */

    private String fileName;
    private String fileType;
    private String fileMD5;
    private String downloadURL;
    private String createTime;
    private int fileSize;
    private String localURL;

    public LYTNotificationAttach() {
    }

    protected LYTNotificationAttach(Parcel in) {
        fileName = in.readString();
        fileType = in.readString();
        fileMD5 = in.readString();
        downloadURL = in.readString();
        createTime = in.readString();
        fileSize = in.readInt();
        localURL = in.readString();
    }

    public static final Creator<LYTNotificationAttach> CREATOR = new Creator<LYTNotificationAttach>() {
        @Override
        public LYTNotificationAttach createFromParcel(Parcel in) {
            return new LYTNotificationAttach(in);
        }

        @Override
        public LYTNotificationAttach[] newArray(int size) {
            return new LYTNotificationAttach[size];
        }
    };

    public String getLocalURL() {
        return localURL;
    }

    public void setLocalURL(String localURL) {
        this.localURL = localURL;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(fileType);
        dest.writeString(fileMD5);
        dest.writeString(downloadURL);
        dest.writeString(createTime);
        dest.writeInt(fileSize);
        dest.writeString(localURL);
    }


}
