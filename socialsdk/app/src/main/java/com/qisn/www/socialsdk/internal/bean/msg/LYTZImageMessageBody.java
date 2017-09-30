package com.qisn.www.socialsdk.internal.bean.msg;


import java.io.Serializable;

public class LYTZImageMessageBody extends LYTZFileMessageBody implements Serializable {

    public double width;
    public double height;

    public String picUrl;


    public String thumbnailLocalPath;
    public String thumbnailRemotePath;
    public String thumbnailSecretKey;
    public int thumbnailDownloadStatus;


    public String getThumbnailLocalPath() {
        return thumbnailLocalPath;
    }

    public String getThumbnailSecretKey() {
        return thumbnailSecretKey;
    }

    public void setThumbnailSecretKey(String thumbnailSecretKey) {
        this.thumbnailSecretKey = thumbnailSecretKey;
    }

    public int getThumbnailDownloadStatus() {
        return thumbnailDownloadStatus;
    }

    public void setThumbnailDownloadStatus(int thumbnailDownloadStatus) {
        this.thumbnailDownloadStatus = thumbnailDownloadStatus;
    }

    public LYTZImageMessageBody(String localPath) {
        super(localPath);
    }

    public LYTZImageMessageBody(String localPath, String displayName) {
        super(localPath, displayName);
    }

    @Override
    public String getMessageType() {
        return LYTMessage.Type.IMAGE.getName();
    }

    public void setThumbnailLocalPath(String thumbnailLocalPath) {
        this.thumbnailLocalPath = thumbnailLocalPath;
    }

    public String getThumbnailRemotePath() {
        return thumbnailRemotePath;
    }

    public int getWidth() {
        return (int) width;
    }


    public int getHeight() {
        return (int) height;
    }

    public void setThumbnailRemotePath(String thumbnailRemotePath) {
        this.thumbnailRemotePath = thumbnailRemotePath;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "{" + "\"picUrl\":" + picUrl + '\'' + '}';
    }
}
