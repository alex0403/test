package com.qisn.www.socialsdk.internal.bean;

/**
 * Created by Administrator on 2017/2/9.
 */

public class ValidateFileMD5Bean {

    private String fileMD5;
    private String downloadURL;
    private int fileSize;
    private String fileType;
    private long createTime;
    private String fileName;
    private String fileTarget;
    private String cmpcd;
    private String userId;
    private String localFilePath;


    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
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

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTarget() {
        return fileTarget;
    }

    public void setFileTarget(String fileTarget) {
        this.fileTarget = fileTarget;
    }

    public String getCmpcd() {
        return cmpcd;
    }

    public void setCmpcd(String cmpcd) {
        this.cmpcd = cmpcd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
