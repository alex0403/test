package com.qisn.www.socialsdk.internal.bean.msg;


import com.qisn.www.socialsdk.base.BaseBean;

/**
 * Created by dell on 2017/6/26.
 */

public class LYTRetrieva extends BaseBean {
    private String text;//内容


    private String toId;

    private String name;

    private boolean isFile;

    private int fileType;


    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
