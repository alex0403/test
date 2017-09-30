package com.qisn.www.socialsdk.internal.db.dao;


/**
 * Created by dell on 2017/5/9.
 */

public class LYTGroupDao extends DbConfig {


    public static final String GROUP_MEMEBERS_NAME = "memebers";//成员表名

    public static final String CONVERSATION_GROUP_NAME = "conversation_group";//列表表明

    public static final String GROUP_INFO_NAME = "group_info";//讨论组信息
    /**
     * 讨论组详情
     */
    protected static final String GROUP_ID = "groupId";//   群ID
    protected static final String GROUP_NAME = "groupName";//群名字
    protected static final String GROUP_REMOTE_PIC = "remote_pic";//远程头像地址
    protected static final String GROUP_LOCAL_PIC = "local_pic";//本地头像地址
    protected static final String GROUP_MRMBER_COUNT = "memberCount";//群数量

    protected static final String GROUP_RECEIVE_TYPE = "receive_type";//接收消息状态
    protected static final String GROUP_OWNER_ID = "groupOwnerId";//群主ID

//    protected static final String GROUP_READ_MODEL = "read_model";//阅后即焚烧

    protected static final String CREATE_USER_ID = "create_user_id";
    protected static final String CREATE_TIME = "create_time";
    protected static final String UPDATE_TIME = "update_time";
    protected static final String MANAGER_IDS = "manager_ids";


    /**
     * 讨论组成员详情
     */
    protected static final String USER_ID = "userId";
    protected static final String USER_NAME = "userName";
    protected static final String USER_NUM = "userNum";
    protected static final String USER_PIC = "picture";
    protected static final String USER_POSITION = "position";
    protected static final String USER_ROLELEVEL = "roleLevel";


    /**
     * 讨论组信息表
     *
     * @param userId
     * @return
     */
    protected static String getGroupInfo(String userId) {

        String GROUP_TABLE_CREATE = "CREATE TABLE if not exists "
                + GROUP_INFO_NAME + userId + " (" +
                LYTGroupDao.CREATE_USER_ID + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_ID + " text UNIQUE ON CONFLICT REPLACE, " +
                LYTGroupDao.GROUP_NAME + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_REMOTE_PIC + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_LOCAL_PIC + TEXT_TYPE + COMMA_SEP +
//                LYTGroupDao.GROUP_READ_MODEL + INTEGER_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_MRMBER_COUNT + INTEGER_TYPE + COMMA_SEP +
                LYTGroupDao.UPDATE_TIME + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.CREATE_TIME + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.MANAGER_IDS + BLOB_TYPE + COMMA_SEP +

                READ_MODEL + INTEGER_TYPE + COMMA_SEP +
                ISTOP + INTEGER_TYPE + COMMA_SEP +
                IS_DISTURB + INTEGER_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_RECEIVE_TYPE + INTEGER_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_OWNER_ID + TEXT_TYPE + " );";
        return GROUP_TABLE_CREATE;
    }

    /**
     * 讨论组会话列表
     *
     * @param userId
     * @return UNIQUE + " ( " + LYTGroupDao.GROUP_ID + " ) " + ON_CONFLICT_REPLACE +
     */
    protected static String getGroupList(String userId) {

        String GROUP_TABLE_CREATE = "CREATE TABLE if not exists "
                + CONVERSATION_GROUP_NAME + userId + " (" +
                CHAT_TPYPE + INTEGER_TYPE + COMMA_SEP +
                LYTGroupDao.GROUP_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + " );";

        return GROUP_TABLE_CREATE;
    }


    /**
     * 讨论组成员表
     *
     * @param userId
     * @return
     */
    public static String getGroupMember(String userId) {

        String GROUP_TABLE_CREATE = "CREATE TABLE if not exists "
                + GROUP_MEMEBERS_NAME + userId + " (" +
                LYTGroupDao.USER_ID + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_NAME + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_NUM + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_PIC + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_POSITION + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_ROLELEVEL + INTEGER_TYPE + " );";
        return GROUP_TABLE_CREATE;
    }


}
