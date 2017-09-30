package com.qisn.www.socialsdk.internal.bean;

/**
 * Created by dell on 2017/7/12.
 */

public class ValidateFileMD5 {




    /**
     * fileId : 文件ID
     * fileName : 文件名称
     * fileType : 文件类型
     * dowmnloadUrl : 文件下载地址
     * fileSize : 文件大小
     * fileMD5 : 文件的MD5值
     */

    private String fileId;
    private String fileName;
    private String fileType;
    private String dowmnloadUrl;
    private String fileSize;
    private String fileMD5;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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

    public String getDowmnloadUrl() {
        return dowmnloadUrl;
    }

    public void setDowmnloadUrl(String dowmnloadUrl) {
        this.dowmnloadUrl = dowmnloadUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }
}
