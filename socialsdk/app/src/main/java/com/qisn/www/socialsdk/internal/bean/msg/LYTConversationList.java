package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LYTConversationList {


    private String cId;//会话ID

    private String cName;//会话名字

    private int cMsgCount;//消息数量

    private String cImage;//会话图片

    private String cBackground;//聊天背景

    private int cRobotFlag;//机器人是否开启("0"未开启机器人,"1"开启机器人)

    private String cRobotType;//机器人类型

    private String cDesc;//描述

    private String cRemark;//备注

    private String cAttr1;

    private long msgTime;

    private String cAttr2;

    private String cAttr3;

    private int cReceiveType;//接收消息的状态

    private int cNoticeFlag;//是否接收强制消息提醒

    private int cReadBurn;//是否开启阅后即焚模式

    private int cStick;//置顶

    private String cExtend1;//扩展字段1

    private String cExtend2;//扩展字段2

    private int cExtend3;//扩展字段3

    private LYTMessage lytMessage;

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public LYTConversationList() {
    }

    public LYTConversationList(String cId, String cName, int cMsgCount, String cImage, String cBackground, int cRobotFlag, String cRobotType, String cDesc, String cRemark, String cAttr1, String cAttr2, String cAttr3, int cReceiveType, int cNoticeFlag, int cReadBurn, int cStick, String cExtend1, String cExtend2, int cExtend3) {
        this.cId = cId;
        this.cName = cName;
        this.cMsgCount = cMsgCount;
        this.cImage = cImage;
        this.cBackground = cBackground;
        this.cRobotFlag = cRobotFlag;
        this.cRobotType = cRobotType;
        this.cDesc = cDesc;
        this.cRemark = cRemark;
        this.cAttr1 = cAttr1;
        this.cAttr2 = cAttr2;
        this.cAttr3 = cAttr3;
        this.cReceiveType = cReceiveType;
        this.cNoticeFlag = cNoticeFlag;
        this.cReadBurn = cReadBurn;
        this.cStick = cStick;
        this.cExtend1 = cExtend1;
        this.cExtend2 = cExtend2;
        this.cExtend3 = cExtend3;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcMsgCount() {
        return cMsgCount;
    }

    public void setcMsgCount(int cMsgCount) {
        this.cMsgCount = cMsgCount;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public String getcBackground() {
        return cBackground;
    }

    public void setcBackground(String cBackground) {
        this.cBackground = cBackground;
    }

    public int getcRobotFlag() {
        return cRobotFlag;
    }

    public void setcRobotFlag(int cRobotFlag) {
        this.cRobotFlag = cRobotFlag;
    }

    public String getcRobotType() {
        return cRobotType;
    }

    public void setcRobotType(String cRobotType) {
        this.cRobotType = cRobotType;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public String getcRemark() {
        return cRemark;
    }

    public void setcRemark(String cRemark) {
        this.cRemark = cRemark;
    }

    public String getcAttr1() {
        return cAttr1;
    }

    public void setcAttr1(String cAttr1) {
        this.cAttr1 = cAttr1;
    }

    public String getcAttr2() {
        return cAttr2;
    }

    public void setcAttr2(String cAttr2) {
        this.cAttr2 = cAttr2;
    }

    public String getcAttr3() {
        return cAttr3;
    }

    public void setcAttr3(String cAttr3) {
        this.cAttr3 = cAttr3;
    }

    public int getcReceiveType() {
        return cReceiveType;
    }

    public void setcReceiveType(int cReceiveType) {
        this.cReceiveType = cReceiveType;
    }

    public int getcNoticeFlag() {
        return cNoticeFlag;
    }

    public void setcNoticeFlag(int cNoticeFlag) {
        this.cNoticeFlag = cNoticeFlag;
    }

    public int getcReadBurn() {
        return cReadBurn;
    }

    public void setcReadBurn(int cReadBurn) {
        this.cReadBurn = cReadBurn;
    }

    public int getcStick() {
        return cStick;
    }

    public void setcStick(int cStick) {
        this.cStick = cStick;
    }

    public String getcExtend1() {
        return cExtend1;
    }

    public void setcExtend1(String cExtend1) {
        this.cExtend1 = cExtend1;
    }

    public String getcExtend2() {
        return cExtend2;
    }

    public void setcExtend2(String cExtend2) {
        this.cExtend2 = cExtend2;
    }

    public int getcExtend3() {
        return cExtend3;
    }

    public void setcExtend3(int cExtend3) {
        this.cExtend3 = cExtend3;
    }

    public LYTMessage getLytMessage() {
        return lytMessage;
    }

    public void setLytMessage(LYTMessage lytMessage) {
        this.lytMessage = lytMessage;
    }

    public String getTo() {
        return lytMessage.getTo();
    }

    public String getFromId() {
        return lytMessage.getLytObject().getFromId();
    }


    public String getMessageType() {
        return lytMessage.getLytObject().getLytzMessageBody().getMessageType();
    }




    public String getLYTMessage() {
        return lytMessage.getLytObject().getLytzMessageBody().getMessageType();
    }

    public LYTZMessageBody getBody() {
        return lytMessage.getLytObject().getLytzMessageBody();
    }

    public long getTime() {
        return lytMessage.getMsgTime();
    }
}