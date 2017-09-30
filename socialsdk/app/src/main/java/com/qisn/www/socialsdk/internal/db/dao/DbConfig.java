package com.qisn.www.socialsdk.internal.db.dao;

/**
 * Created by Administrator on 2017/2/24.
 */

public abstract class DbConfig {



    protected static final String GROUP_REMOTE_PIC = "remote_pic";//远程头像地址
    protected static final String GROUP_LOCAL_PIC = "local_pic";//本地头像地址
    protected static final String GROUP_MRMBER_COUNT = "memberCount";//群数量
    protected static final String GROUP_RECEIVE_TYPE = "receive_type";//接收消息状态
    protected static final String GROUP_OWNER_ID = "groupOwnerId";//群主ID

    public static final String ROOM_OPERATEID = "operateId";//创建者ID
    public static final String ROOM_DESC = "desc";//聊天室描述
    public static final String ROOM_REMARK = "remark";//聊天室备注
    public static final String ROOM_ATTR1 = "attr1";//聊天室其他信息
    public static final String ROOM_ATTR2 = "attr2";//聊天室其他信息
    public static final String ROOM_ATTR3 = "attr3";//聊天室其他信息
    public static final String ROOM_ROBOTFLAG = "robotFlag";//“0”未开启，”1”开启机器人
    public static final String ROOM_TYPE = "robotType";//机器人类型



    protected  static final String UNIQUE="unique  ";


    protected static final String ON_CONFLICT_REPLACE=" UNIQUE ON CONFLICT REPLACE ";

    protected static final String TEXT_TYPE = " TEXT ";//

    protected static final String LONG_TYPE = " Long";

    protected static final String INTEGER_TYPE = " INTEGER";

    protected static final String BOOLEAN_TYPE = " Boolean";

    protected static final String BLOB_TYPE = " BLOB";

    protected static final String COMMA_SEP = ",";

    protected static final String CONVERSATION_ID = "session_id";//会话ID(单聊ID,聊天室ID,群组ID)  String



    protected static final String CONVERSATION_NAME = "session_name";//会话名字(单聊名字,聊天室名字,群组名字) String

    protected static final String CONVERSATION_IMAGE = "session_img";//会话图片(或者个人图片) String

    protected static final String REMOTE_PIC = "remote_pic";//远程头像地址
    protected static final String LOCAL_PIC = "local_pic";//本地头像地址

    protected static final String CREATE_USER_ID = "create_user_id";//创建者ID

    protected static final String CREATE_TIME = "create_time";

    protected static final String UPDATE_TIME = "update_time";

    protected static final String MANAGER_IDS = "manager_ids";


//    protected static final String CONVERSATION_IMAGE = "session_img";//会话图片(或者个人图片) String

    protected static final String CONVERSATION_BACKGROUND = "session_bg";//聊天背景 String

    protected static final String ROBOT_FLAG = "robot_flag";//机器人是否开启("0"未开启机器人,"1"开启机器人) int

    protected static final String ROBOT_TYPE = "robot_type";//机器人类型 String

    protected static final String DESC = "desc";//描述  String

    protected static final String REMARK = "remark";//备注  String

    protected static final String ATTR1 = "attr1";//其他信息  String

    protected static final String ATTR2 = "attr2";//其他信息  String

    protected static final String ATTR3 = "attr3";//其他信息  String

    protected static final String RECEIVE_TYPE = "receive_type";//接收消息的几种状态  int

    protected static final String NOTICE_FLAG = "notice_flag";//是否开启强制通知提醒  int

    protected static final String OPERATEID = "operateId";//创建者ID


    protected static final String ISTOP = "istop";//开启置顶 int

    protected static final String READ_MODEL = "read_model";//阅后即焚烧

    public static final String IS_DISTURB ="disturb";// 是否已经开启了免打扰

    protected static final String TO = "to_id";//发送给谁  int

    protected static final String CHAT_TPYPE = "chat_type";//聊天类型  int

    protected static final String MESSAGE_COUNT = "message_count";//数量  int



}
