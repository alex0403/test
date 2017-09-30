package com.qisn.www.socialsdk.internal.bean;


import com.qisn.www.socialsdk.internal.bean.msg.LYTMessage;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

/**
 * Created by dell on 2017/9/7.
 */

public class LYTSessoinList {




    public LYTSessoinList() {

    }


    private LYTMessage lytMessage;

    private String name;//会话名字

    private long msgCount;//消息数量

    private String icon;//会话图片

    private String localIcon;//本地图片
   private int chatType;//聊天类型

    private int isTop;// 会话是否置顶

    private boolean isNotice;//是否含有通知

    private int readModel;// 阅读模式

    private boolean isAt;//是否含有@消息

    private boolean isVideo;//是否含有视频消息.

    private int disturb;//开启免打扰

    private LYTZMessageBody body;

    public String getLocalIcon() {
        return localIcon;
    }

    public void setLocalIcon(String localIcon) {
        this.localIcon = localIcon;
    }

    public int getDisturb() {
        return disturb;
    }

    public void setDisturb(int disturb) {
        this.disturb = disturb;
    }

    public int getReadModel() {
        return readModel;
    }

    public void setReadModel(int readModel) {
        this.readModel = readModel;
    }



    public LYTMessage getLytMessage() {
        return lytMessage;
    }

    public void setLytMessage(LYTMessage lytMessage) {
        this.lytMessage = lytMessage;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(long msgCount) {
        this.msgCount = msgCount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public boolean isNotice() {
        return isNotice;
    }

    public void setNotice(boolean notice) {
        isNotice = notice;
    }

    public boolean isAt() {
        return isAt;
    }

    public void setAt(boolean at) {
        isAt = at;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }


    public LYTZMessageBody getBody() {
        return lytMessage.getLytObject().getLytzMessageBody();
    }
}
