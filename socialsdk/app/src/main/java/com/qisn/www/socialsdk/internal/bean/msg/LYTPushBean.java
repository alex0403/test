package com.qisn.www.socialsdk.internal.bean.msg;

/**
 * Created by Administrator on 2017/2/28.
 */

public class LYTPushBean {

    private int os;//操作系统 3–android 4–ios

    private String version;//当前app的版本号
    private String userId;//	当前用户ID
    private String deviceToken;//注册各个平台的唯一标识
    private String manufacture;//1–小米 2–华为 3–魅族 0–苹果


    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
}
