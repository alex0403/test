package com.qisn.www.socialsdk.internal.db.dao;


/**
 * Created by dell on 2017/6/22.
 */

public class LYTNoticeDao extends DbConfig {

    public static final String NOTICE_TITLE = "title";
    public static final String NOTICE_READ_STATE = "readState";
    public static final String NOTICE_TARGETID = "targetId";
    public static final String NOTICE_UPDATEBY = "updateBy";
    public static final String NOTICE_UPDATETIME = "updateTime";
    public static final String NOTICE_ATTACH = "attach";
    public static final String NOTICE_CONTENT = "content";
    public static final String NOTICE_CREATEBY = "createBy";
    public static final String NOTICE_CREATETIME = "createTime";
    public static final String NOTICE_NOTIFICATIONID = "notificationId";
    public static final String NOTICE_HASATTACH = "hasAttach";

    public static final String GROUP_NOTICE = "GROUP_NOTICE";


    public static final String NOTICE = "notice";


    /**
     * 公告
     *
     * @param userId
     * @return
     */
    protected static String getGroupNotice(String userId) {

        String GROUP_NOTICE2 = "CREATE TABLE if not exists "
                + GROUP_NOTICE + userId + " (" +

                NOTICE_TITLE + TEXT_TYPE + COMMA_SEP +

                NOTICE_NOTIFICATIONID + " text UNIQUE ON CONFLICT REPLACE, " +

                NOTICE_READ_STATE + TEXT_TYPE + COMMA_SEP +

                NOTICE_TARGETID + TEXT_TYPE + COMMA_SEP +

                NOTICE_UPDATEBY + TEXT_TYPE + COMMA_SEP +

                NOTICE_UPDATETIME + LONG_TYPE + COMMA_SEP +

                NOTICE_ATTACH + TEXT_TYPE + COMMA_SEP +

                NOTICE_CONTENT + TEXT_TYPE + COMMA_SEP +

                NOTICE_CREATEBY + TEXT_TYPE + COMMA_SEP +

                NOTICE_CREATETIME + LONG_TYPE + COMMA_SEP +

                NOTICE_HASATTACH + INTEGER_TYPE + " );";
        return GROUP_NOTICE2;
    }


    /**
     * 公告
     *
     * @param userId
     * @return
     */
    protected static String getNotice(String userId) {

        String GROUP_NOTICE2 = "CREATE TABLE if not exists "
                + NOTICE + userId + " (" +
                CONVERSATION_ID + TEXT_TYPE + COMMA_SEP +
                LYTMessageDao.CHAT_INDEX + LONG_TYPE + COMMA_SEP +
                " primary key  ( session_id, chatIndex ) " + " );";
        return GROUP_NOTICE2;
    }

}
