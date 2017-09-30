package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;

/**
 * Created by dell on 2017/6/27.
 */

public class LYTZVideoMessageBody extends LYTZMessageBody implements Serializable {


    /**
     * roomStatus : 0
     * roomId : 123456
     * roomName : 华创众盈利
     * creatorId : P123456
     * callType : 1
     * duration : 1:24:10
     */

    private int roomStatus; //1--开启多人音视频
    private String roomId; //会议室ID
    private String roomName;  //会议室名字
    private String creatorId;   //会议创建者
    private int callType; //1--音频通话，2--视频通话
    private String duration;  //表示多人音频视频持续的时间为1个小时24分10秒

    protected LYTZVideoMessageBody(Parcel in) {
        roomId = in.readString();
        roomName = in.readString();
        creatorId = in.readString();
        duration = in.readString();
        callType = in.readInt();
        roomStatus = in.readInt();
    }

    public LYTZVideoMessageBody(int roomStatus, String roomId, String roomName, int callType) {
        this.roomStatus = roomStatus;
        this.roomId = roomId;
        this.roomName = roomName;

        this.callType = callType;

    }

    @Override
    public String getMessageType() {
        return LYTMessage.Type.MULTI_VIDEO.getName();
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "{" +
                "roomStatus :" + roomStatus +
                ", roomId :'" + roomId + '\'' +
                ", roomName :'" + roomName + '\'' +
                ", creatorId :'" + creatorId + '\'' +
                ", callType : " + callType +
                ", duration :'" + duration + '\'' +
                '}';
    }

    public String toString2() {
        return "{" +
                "roomStatus :" + roomStatus +
                ", roomId :'" + roomId + '\'' +
                ", roomName :'" + roomName + '\'' +
                ", creatorId :'" + creatorId + '\'' +
                ", callType :'" + callType + '\'' +
                '}';
    }

}
