package com.qisn.www.socialsdk.internal.bean;


/**
 * Created by dell on 2017/5/22.
 */

public class LYTGroupMember {

    private String userId;//用户ID
    private String userName;//用户名字
    private String userNum;//工号
    private String picture;//用户头像
    private String position;//用户职位
    private int roleLevel;     //0--普通成员 1--管理员  2--群主


    public LYTGroupMember(String userId, String userName, String userNum, String picture, String position, int roleLevel) {
        this.userId = userId;
        this.userName = userName;
        this.userNum = userNum;
        this.picture = picture;
        this.position = position;
        this.roleLevel = roleLevel;
    }

    public LYTGroupMember() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }
}
