package com.qisn.www.socialsdk.internal.db.dao;

/**
 * Created by dell on 2017/5/5.
 */

public final class LYTConversationDao extends DbConfig {


    private static final String TEXT_TYPE = " TEXT ";
    private static final String LONG_TYPE = " Long";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String BOOLEAN_TYPE = " Boolean";
    private static final String BLOB_TYPE = " BLOB";


    private static final String COMMA_SEP = ",";

    public static final String CONVERSATION_TABLE_NAME = "conversation_list";

    public static final String CONVERSATION_TABLE_NAME2 = "conversation";

    public static final String CONVERSATION_TABLE_INFO = "conversation_info";


    public static final String SESSOIN_LIST= "session_list";

    public static final String BLACKLIST_TABLE_NAME = "black_list";

    public static final String TARGETID = "targetId";

    public static final String SQL_NAME = "LYT";

    public static final String IS_BALCK = "is_balck";

    public static final String IS_DISTURB = "disturb";// 是否已经开启了免打扰


    public LYTConversationDao() {
    }


    protected static String getInfoSql(String table) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE if not exists " + CONVERSATION_TABLE_INFO + table + " (" +

                        CONVERSATION_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +//id

                        TO + TEXT_TYPE + COMMA_SEP +

                        ISTOP + INTEGER_TYPE + COMMA_SEP + //是否置顶

                        CHAT_TPYPE + INTEGER_TYPE + COMMA_SEP + //聊天方式

                        READ_MODEL + INTEGER_TYPE + COMMA_SEP + //是否开启阅后即焚

                        IS_DISTURB + INTEGER_TYPE + "  default 1 )"; // 是否开启打扰
        return SQL_CREATE_ENTRIES;
    }


    public void createTable(String sql) {
        //会话列表
//        LYTDBManager.getInstance().createTable(LYTConversationDao.getConversationSql(sql));

    }





    protected static String getNewConversationSql(String userId) {

        String CHATROOM_TABLE_CREATE = "CREATE TABLE if not exists "
                + LYTConversationDao.CONVERSATION_TABLE_NAME2 + userId + " (" +
                DbConfig.CONVERSATION_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +
//                LYTBaseDao.TO + TEXT_TYPE + COMMA_SEP +
//                LYTBaseDao.MESSAGE_COUNT + LONG_TYPE + COMMA_SEP +
//                READ_MODEL + INTEGER_TYPE + COMMA_SEP +
//                ISTOP + INTEGER_TYPE + COMMA_SEP +
//                IS_DISTURB + INTEGER_TYPE + COMMA_SEP +
                DbConfig.MESSAGE_COUNT + LONG_TYPE + " );";


        return CHATROOM_TABLE_CREATE;
    }


    //黑名单
    protected static String getBlacklistSql(String userId) {
        String BLACKLIST_TABLE_NAME = "CREATE TABLE if not exists "
                + LYTConversationDao.BLACKLIST_TABLE_NAME + userId + " (" +
                DbConfig.CONVERSATION_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +
                IS_BALCK + INTEGER_TYPE + COMMA_SEP +
                TARGETID + TEXT_TYPE + " );";

        return BLACKLIST_TABLE_NAME;
    }



}
