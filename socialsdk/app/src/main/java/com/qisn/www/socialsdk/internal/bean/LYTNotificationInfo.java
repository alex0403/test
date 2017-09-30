package com.qisn.www.socialsdk.internal.bean;

import java.util.List;

/**
 * Created by dell on 2017/5/4.
 */

public class LYTNotificationInfo {


    /**serializationObjects2
     * attach :
     * content : 3213
     * createBy : 9658
     * createTime : 1498096772967
     * notificationId : N149809677296792
     * readState : 1
     * targetId : G149725123724944
     * title : 2161
     * updateBy : 9658
     * updateTime : 1498096772967
     */

    private List<LYTNotificationAttach > notificationAttach;

    public List<LYTNotificationAttach> getNotificationAttach() {
        return notificationAttach;
    }

    public void setNotificationAttach(List<LYTNotificationAttach> notificationAttach) {
        this.notificationAttach = notificationAttach;
    }

    private String attach; //附件信息

    private String content;

    private String createBy;   //创建人----String类型

    private long createTime;  //公告创建时间----String类型

    private String notificationId;  //公告ID----String类型

    private String createId;

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    private String hasAttach;

    public String getHasAttach() {

        return hasAttach;
    }

    public void setHasAttach(String hasAttach) {
        this.hasAttach = hasAttach;
    }

    private int readState;

    private String targetId;

    private String title;  //公告标题----String类型

    private String updateBy;     //修改人----String类型

    private long updateTime; //更新时间

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
