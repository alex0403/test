package com.qisn.www.socialsdk.internal.bean;

import com.qisn.www.socialsdk.base.BaseBean;

import java.util.List;

/**
 * Created by dell on 2017/8/23.
 */

public class LYTMChatRoom  extends BaseBean {

    public String attr1;  //聊天室其他信息
    public String attr2;  //聊天室其他信息
    public String attr3;//聊天室其他信息
    public String desc;  //聊天室描述
    public String operateId;  //创建者ID
    public String remark;  //聊天室备注
    public int robotFlag; //“0”未开启，”1”开启机器人
    public String robotType; //机器人类型

    public String userId;

    public String userName;

    public String targetId;

    public String targetName;


    public String handleId;
    public int state;
    public List<LYTChatMember> members;
    public String roomId;
    public String roomName;
    public int receiveType=1;
    public int chatType;





}
