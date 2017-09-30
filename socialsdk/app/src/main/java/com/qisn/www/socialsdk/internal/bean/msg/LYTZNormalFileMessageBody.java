package com.qisn.www.socialsdk.internal.bean.msg;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTZNormalFileMessageBody extends LYTZFileMessageBody {


    private String fileName;

    private String fileUrl;

    private String size;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getMessageType() {
        return  LYTMessage.Type.FILE.getName();
    }

    public LYTZNormalFileMessageBody(String localPath) {
        super(localPath);
    }

    public LYTZNormalFileMessageBody(String localPath, String displayName) {
        super(localPath, displayName);
    }


    public String size() {
        return size;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"fileName:\"'" + fileName + '\'' +
                ", \"fileUrl:\"'" + fileUrl + '\'' +
                ", \"size:\"" + size +
                '}';
    }
}
