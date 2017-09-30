package com.qisn.www.socialsdk.internal.db.dao;


/**
 * Created by Administrator on 2017/2/7.
 */

public class LYTMessageDao extends DbConfig {

    private static final String TEXT_TYPE = " TEXT";

    public static final String MASS_TARGETID = "900000";

    private static final String LONG_TYPE = " Long";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String BOOLEAN_TYPE = " Boolean";

    private static final String PRIMARY_KEY = " PRIMARY KEY AUTOINCREMENT";

    private static final String PRIMARY_KEY2 = "  PRIMARY KEY  ";

    private static final String BLOB_TYPE = " BLOB";

    private static final String COMMA_SEP = ",";

    public static final String SQL_NAME = "LYT";
    public static final String SQL_NAMER = "LYTR";

    public static final String MESSAGER_TYPE = "messageType";
    public static final String CHAT_TYPE = "chatType";
    public static final String CHAT_RETRIEVAL = "chatRetrieval";
    public static final String OS = "os";
    public static final String ISDESTROY = "isDestroy";
    public static final String ISREAD = "isRead";
    public static final String MSG_ID = "msgId";
    public static final String APPKEY = "appkey";
    public static final String CONVERSATIONID = "conversationId";
    public static final String TARGETID = "targetId";
    public static final String MSGTIME = "msgTime";
    public static final String FROM_ID = "from_id";
    public static final String ATTR = "attr";

    public static final String CHAT_INDEX = "chatIndex";
    public static final String MESSAGE_STATE = "state";
    public static final String MESSAGE_BODY = "messageBody";

    public static final String VIDEO_ID = "video_id";

    public static final String LOCALINDEX = "localIndx";
    public static final String USERNAME = "name";
    public static final String USERICON = "icon";

    public static final String READ_START_TIME = "read_time";

    public static final String READ_DURATION = "read_duration";

    public static final String FILE_TYPE = "file_type";

    public static final String ISERROR = "isError";

    public static final String VOICE_STATE = "voice_state";//语音是否已读

    public static final String AT_SATET = "at_state";//@消息状态

    public static final String VIDEO_STATE = "video_state";//视频消息状态

    public static final String IS_SUCCES = "isSuccess";
//

//    public boolean createTable(String sqlName) {
//        String name = null;
//        if (sqlName.contains("-")) {
//            name = sqlName.replace("-", "");
//        } else {
//            name = sqlName;
//        }
//        return LYTDBManager.getInstance().createTable(getSql(name));
//
//
//    }


    public static String getSql(String table) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE if not exists " + SQL_NAME + table + " (" +
                        LOCALINDEX + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
                        MSG_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +
                        MESSAGER_TYPE + TEXT_TYPE + COMMA_SEP +
                        CHAT_TYPE + INTEGER_TYPE + COMMA_SEP +
                        CHAT_RETRIEVAL + TEXT_TYPE + COMMA_SEP +
                        USERNAME + TEXT_TYPE + COMMA_SEP +
                        USERICON + TEXT_TYPE + COMMA_SEP +
                        IS_SUCCES + INTEGER_TYPE + COMMA_SEP +
                        READ_START_TIME + LONG_TYPE + COMMA_SEP +
                        READ_DURATION + INTEGER_TYPE + COMMA_SEP +
                        VIDEO_ID + TEXT_TYPE + COMMA_SEP +
                        FILE_TYPE + TEXT_TYPE + COMMA_SEP +
                        OS + INTEGER_TYPE + COMMA_SEP +
                        ISDESTROY + BOOLEAN_TYPE + COMMA_SEP +
                        ISREAD + BOOLEAN_TYPE + COMMA_SEP +
                        MESSAGE_STATE + INTEGER_TYPE + COMMA_SEP +
                        VOICE_STATE + INTEGER_TYPE + COMMA_SEP +
                        AT_SATET + INTEGER_TYPE + COMMA_SEP +
                        VIDEO_STATE + INTEGER_TYPE + COMMA_SEP +
//                        APPKEY + TEXT_TYPE + COMMA_SEP +
                        CONVERSATIONID + TEXT_TYPE + COMMA_SEP +
                        TARGETID + TEXT_TYPE + COMMA_SEP +
                        FROM_ID + TEXT_TYPE + COMMA_SEP +
                        ATTR + TEXT_TYPE + COMMA_SEP +
                        CHAT_INDEX + LONG_TYPE + COMMA_SEP +
                        MSGTIME + LONG_TYPE + COMMA_SEP +
                        MESSAGE_BODY + BLOB_TYPE + " )";
        return SQL_CREATE_ENTRIES;
    }


    protected static String getSessionSql(String table) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE if not exists " + LYTConversationDao.SESSOIN_LIST + table + " (" +
                        LOCALINDEX + INTEGER_TYPE  + COMMA_SEP +
                        MSG_ID + TEXT_TYPE  + COMMA_SEP +
                        MESSAGER_TYPE + TEXT_TYPE + COMMA_SEP +
                        CHAT_TYPE + INTEGER_TYPE + COMMA_SEP +
                        CHAT_RETRIEVAL + TEXT_TYPE + COMMA_SEP +
                        USERNAME + TEXT_TYPE + COMMA_SEP +
                        USERICON + TEXT_TYPE + COMMA_SEP +
                        IS_SUCCES + INTEGER_TYPE + COMMA_SEP +
                        READ_START_TIME + LONG_TYPE + COMMA_SEP +
                        READ_DURATION + INTEGER_TYPE + COMMA_SEP +
                        VIDEO_ID + TEXT_TYPE + COMMA_SEP +
                        FILE_TYPE + TEXT_TYPE + COMMA_SEP +
                        OS + INTEGER_TYPE + COMMA_SEP +
                        ISDESTROY + BOOLEAN_TYPE + COMMA_SEP +
                        ISREAD + BOOLEAN_TYPE + COMMA_SEP +
                        MESSAGE_STATE + INTEGER_TYPE + COMMA_SEP +
                        VOICE_STATE + INTEGER_TYPE + COMMA_SEP +
                        AT_SATET + INTEGER_TYPE + COMMA_SEP +
                        VIDEO_STATE + INTEGER_TYPE + COMMA_SEP +
//                        APPKEY + TEXT_TYPE + COMMA_SEP +

                        CONVERSATIONID + TEXT_TYPE+ PRIMARY_KEY2 + COMMA_SEP +


                        TARGETID + TEXT_TYPE + COMMA_SEP +
                        FROM_ID + TEXT_TYPE + COMMA_SEP +
                        ATTR + TEXT_TYPE + COMMA_SEP +
                        CHAT_INDEX + LONG_TYPE + COMMA_SEP +
                        MSGTIME + LONG_TYPE + COMMA_SEP +
                        MESSAGE_BODY + BLOB_TYPE + " )";
        return SQL_CREATE_ENTRIES;
    }


    protected static String getRSql(String table) {

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE if not exists " + SQL_NAMER + table + " (" +
                        LOCALINDEX + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
//                        MSG_ID + TEXT_TYPE + COMMA_SEP +
                        MSG_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + COMMA_SEP +
                        MESSAGER_TYPE + TEXT_TYPE + COMMA_SEP +
                        CHAT_TYPE + INTEGER_TYPE + COMMA_SEP +
                        CHAT_RETRIEVAL + TEXT_TYPE + COMMA_SEP +
                        USERNAME + TEXT_TYPE + COMMA_SEP +
                        USERICON + TEXT_TYPE + COMMA_SEP +
                        IS_SUCCES + INTEGER_TYPE + COMMA_SEP +
                        READ_START_TIME + LONG_TYPE + COMMA_SEP +
                        READ_DURATION + INTEGER_TYPE + COMMA_SEP +
                        VIDEO_ID + TEXT_TYPE + COMMA_SEP +
                        FILE_TYPE + TEXT_TYPE + COMMA_SEP +
                        OS + INTEGER_TYPE + COMMA_SEP +
                        ISDESTROY + BOOLEAN_TYPE + COMMA_SEP +
                        ISREAD + BOOLEAN_TYPE + COMMA_SEP +
                        MESSAGE_STATE + INTEGER_TYPE + COMMA_SEP +
                        VOICE_STATE + INTEGER_TYPE + COMMA_SEP +
                        AT_SATET + INTEGER_TYPE + COMMA_SEP +
                        VIDEO_STATE + INTEGER_TYPE + COMMA_SEP +
//                        APPKEY + TEXT_TYPE + COMMA_SEP +
                        CONVERSATIONID + TEXT_TYPE + COMMA_SEP +
                        TARGETID + TEXT_TYPE + COMMA_SEP +
                        FROM_ID + TEXT_TYPE + COMMA_SEP +
                        ATTR + TEXT_TYPE + COMMA_SEP +
                        CHAT_INDEX + LONG_TYPE + COMMA_SEP +
                        MSGTIME + LONG_TYPE + COMMA_SEP +
                        MESSAGE_BODY + BLOB_TYPE + " )";
        return SQL_CREATE_ENTRIES;
    }


}
