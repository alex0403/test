package com.qisn.www.socialsdk.internal.bean;

import com.qisn.www.socialsdk.base.BaseBean;

import java.util.List;

/**
 * Created by dell on 2017/9/15.
 */

public class LYTDChatRoom extends BaseBean {


    /**
     * attr1 : 0
     * attr2 : 4
     * attr3 : {"clientType":"4","curcs":"0","jsId":"1d59152b3ca7ea21f9d8e695d2ade47b984d5aa8"}
     * desc : 164166
     * isDelete : 1
     * members : ["1d59152b3ca7ea21f9d8e695d2ade47b984d5aa8","149489931642503"]
     * operateId : 3
     * remark : 3
     * robotFlag : 0
     * robotType : 1
     * roomId : 100150545719465624
     * roomName : 聊天
     */

    private String attr1;
    private String attr2;
    private String desc;
    private int isDelete;
    private String operateId;
    private String remark;
    private int robotFlag;
    private String robotType;
    private String roomId;
    private String roomName;
    private List<String> members;

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRobotFlag() {
        return robotFlag;
    }

    public void setRobotFlag(int robotFlag) {
        this.robotFlag = robotFlag;
    }

    public String getRobotType() {
        return robotType;
    }

    public void setRobotType(String robotType) {
        this.robotType = robotType;
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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }


}
