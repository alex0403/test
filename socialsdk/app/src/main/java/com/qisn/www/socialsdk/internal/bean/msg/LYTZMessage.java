package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZBase;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTZMessage extends LYTZBase implements Serializable {

    private int chatType;//聊天类型 1：点对点聊天，2：群聊

    private int os;//1：PC，2：WEB，3：ANDROID，4：iOS

    private int isDestroy;//阅后即焚的标识

    private int isRead;//	1：已读，0：未读

    private String msgId;//消息ID

    private String to;//发送给谁

    private boolean videoState;

    private String conversationId;//会话ID
    private String name;
    private String iocn;

    private long msgTime;//消息时间

    private String fromId;//谁发的消息

    private String attr;//扩展字段

    private long chatIndex;

    private boolean isResend;

    private int sendState;

    private int voiceState;//语音是否已读 1是已读 0是未读

    private int atState;//@消息是否已读 1是已读 0是未读

    private long secretTime;//阅后即焚消息时间

    private int duration;//阅后即焚倒计时周期
    private int localIndx;

    private boolean isPlaying;//语音是否播放

    private int isSuccess;

    public boolean isAtRobot;

    public boolean isAtRobot() {
        return isAtRobot;
    }

    public void setAtRobot(boolean atRobot) {
        isAtRobot = atRobot;
    }

    public LYTZMessage(Parcel in) {

    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public long getSecretTime() {
        return secretTime;
    }

    public void setSecretTime(long secretTime) {
        this.secretTime = secretTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAtState() {
        return atState;
    }

    public void setAtState(int atState) {
        this.atState = atState;
    }

    private LYTZMessageBody lytzMessageBody;
    private String appkey;

    public int getVoiceState() {
        return voiceState;
    }

    public void setVoiceState(int voiceState) {
        this.voiceState = voiceState;
    }

    public String getAppkey() {
        return appkey;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int state) {
        this.sendState = state;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isVideoState() {
        return videoState;
    }

    public void setVideoState(boolean videoState) {
        this.videoState = videoState;
    }

    public LYTZMessage() {
    }

    public LYTZMessage(int chatType, int OS) {
        this.chatType = chatType;
        this.os = OS;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public long getChatIndex() {
        return chatIndex;
    }

    public void setChatIndex(long chatIndex) {
        this.chatIndex = chatIndex;
    }


    public void setOs(int os) {
        this.os = os;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public int getOs() {
        return os;
    }

    public boolean isResend() {
        return isResend;
    }

    public void setResend(boolean resend) {
        isResend = resend;
    }


    public int getIsDestroy() {
        return isDestroy;
    }

    public void setIsDestroy(int isDestroy) {
        this.isDestroy = isDestroy;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTo() {
        return to;
    }


    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public String getAttr() {
        return attr;
    }

    public void addAttr(String attr) {
        this.attr = attr;
    }


    public void addBody(LYTZMessageBody lytzMessageBody) {
        this.lytzMessageBody = lytzMessageBody;
    }

    public LYTZMessageBody getLytzMessageBody() {
        return lytzMessageBody;
    }

    public void setLytzMessageBody(LYTZMessageBody lytzMessageBody) {
        this.lytzMessageBody = lytzMessageBody;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static LYTZMessage createSendMessage(int chatType, int OS) {

        return new LYTZMessage(chatType, OS);
    }

    public int chatType() {
        int chatType = this.getChatType();


        return chatType;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIocn() {
        return iocn;
    }

    public void setIocn(String iocn) {
        this.iocn = iocn;
    }

    public void setLocalIndx(int localIndx) {
        this.localIndx = localIndx;
    }

    public int getLocalIndx() {
        return localIndx;
    }




    public static enum LYTZChatType {
        flag,
        Chat,
        ChatRoom,
        COMMET,
        GroupChat,
        flag1,
        flag2,
        flag3,
        flag4,
        flag5,
        flag6,
        flag7,
        MASS;

        private LYTZChatType() {
        }
    }
}
