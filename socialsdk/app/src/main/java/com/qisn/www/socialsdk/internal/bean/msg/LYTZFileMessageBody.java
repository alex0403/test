package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTZFileMessageBody extends LYTZMessageBody implements Serializable {

    private String displayName;
    private String localUrl;
    public long fileLength;
    private String secretKey;
    private String secret;



    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public LYTZFileMessageBody(String localPath) {
        this.localUrl = localPath;
    }

    public LYTZFileMessageBody(String localPath, String displayName) {
        this.localUrl = localPath;
        this.displayName = displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLocalPath(String localPath) {
        this.localUrl = localPath;
    }

    public long getFileLength() {
        return fileLength;
    }


    public String getLocalUrl() {
        return localUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecret() {
        return secret;
    }
}
