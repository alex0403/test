//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qisn.www.socialsdk.internal.db.dao;


public class LYTChatRoomDao extends DbConfig {


    public static final String ROOM_OPERATEID = "operateId";//创建者ID
    public static final String ROOM_NAME = "roomName";//聊天室名字
    public static final String ROOM_DESC = "desc";//聊天室描述
    public static final String ROOM_REMARK = "remark";//聊天室备注
    public static final String ROOM_ATTR1 = "attr1";//聊天室其他信息
    public static final String ROOM_ATTR2 = "attr2";//聊天室其他信息
    public static final String ROOM_ATTR3 = "attr3";//聊天室其他信息
    public static final String ROOM_ROBOTFLAG = "robotFlag";//“0”未开启，”1”开启机器人
    public static final String ROOM_TYPE = "robotType";//机器人类型

    public static final String ROOM_ID = "roomId";//聊天室ID


    public static final String USER_ID = "user_id";

    public static final String USER_NAME = "user_name";


    public static final String CONVERSATION_CHAT_ROOM_NAME = "conversation_chat_room";

    public static final String CHAT_ROOM_MEMBERS = "chat_room_members";

    public static final String CHAT_ROOM_INFO = "chat_room_info";


    /**
     * 聊天室信息表
     *
     * @param userId
     * @return
     */
    protected static String getChatRoomInfo(String userId) {

        String CHATROOM_TABLE_CREATE = "CREATE TABLE if not exists "
                + LYTChatRoomDao.CHAT_ROOM_INFO + userId + " (" +
                LYTChatRoomDao.ROOM_OPERATEID + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_ID + TEXT_TYPE  + ON_CONFLICT_REPLACE+ COMMA_SEP +
                LYTChatRoomDao.ROOM_NAME + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_DESC + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_REMARK + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_ATTR1 + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_ATTR2 + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_ATTR3 + TEXT_TYPE + COMMA_SEP +
                DbConfig.RECEIVE_TYPE + TEXT_TYPE + COMMA_SEP +
                LYTChatRoomDao.ROOM_ROBOTFLAG + TEXT_TYPE + COMMA_SEP +

                LYTChatRoomDao.ROOM_TYPE + TEXT_TYPE + " );";

        return CHATROOM_TABLE_CREATE;
    }


    /**
     * 聊天室成员表
     *
     * @param userId
     * @return
     */
    protected static String getChatRoomMembers(String userId) {

        String GROUP_TABLE_CREATE = "CREATE TABLE if not exists "
                + LYTChatRoomDao.CHAT_ROOM_MEMBERS + userId + " (" +
                LYTChatRoomDao.ROOM_ID + TEXT_TYPE + COMMA_SEP +
                USER_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +
                USER_NAME + TEXT_TYPE + " );";

        return GROUP_TABLE_CREATE;
    }

    /**
     * 聊天室列表
     *
     * @param userId
     * @return
     */

    protected static String getChatRoomList(String userId) {

        String GROUP_TABLE_CREATE = "CREATE TABLE if not exists "
                + CONVERSATION_CHAT_ROOM_NAME + userId + " (" +
                LYTChatRoomDao.ROOM_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +
                CHAT_TPYPE + INTEGER_TYPE + " );";

        return GROUP_TABLE_CREATE;
    }

}
