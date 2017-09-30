package com.qisn.www.socialsdk.internal.bean;


import com.qisn.www.socialsdk.base.BaseBean;

import java.util.List;

/**
 * Created by dell on 2017/5/22.
 */

public class LYTGroupInfo extends BaseBean {



    private String operateId;

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public LYTGroupInfo(String groupId, String groupName, String groupPicture, String groupLocalPicture, int memberCount, String groupOwnerId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupPicture = groupPicture;
        this.groupLocalPicture = groupLocalPicture;
        this.memberCount = memberCount;
        this.groupOwnerId = groupOwnerId;
    }


    public LYTGroupInfo() {
    }

    /**
     * groupId : G654321
     * groupName : 华创众盈
     * groupPicture :
     * memberCount : 50
     * groupOwnerId : P123456
     */
    private int chatType;
    private String createUserId;//创建者ID
    private String createTime;//创建时间
    private String updateTime;//最近更新时间
    private String groupId;//讨论组Id
    private String groupName;//讨论组名字
    private String groupPicture;//远程图片
    private String groupLocalPicture;//本地图片
    private int memberCount;// 人数
    private String groupOwnerId;//管理者ID
    private List<String> managerIds;


    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public List<String> getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(List<String> managerIds) {
        this.managerIds = managerIds;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPicture() {
        return groupPicture;
    }

    public String getGroupLocalPicture() {
        return groupLocalPicture;
    }

    public void setGroupLocalPicture(String groupLocalPicture) {
        this.groupLocalPicture = groupLocalPicture;
    }

    public void setGroupPicture(String groupPicture) {
        this.groupPicture = groupPicture;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(String groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }
}
