package com.qisn.www.socialsdk.internal.db.dao;


/**
 * Created by dell on 2017/5/18.
 */

public class LYTSimpleDao extends DbConfig {


    public static final String USER_NAME = "name";

    public static final String USER_ICON = "icon";


    public static final String USER_ID = "user_id";

    public static final String USER_LOCA_LICON = "local_icon";

    public static final String USER_INFO_NAME = "user_info";


    /**
     * 讨论组会话列表
     *
     * @param userId
     * @return
     */
    protected static String getUserInfo(String userId) {

        String USERTABLE_CREATE = "CREATE TABLE if not exists "
                + USER_INFO_NAME + userId + " (" +
                USER_ICON + TEXT_TYPE + COMMA_SEP +
                USER_NAME + TEXT_TYPE + COMMA_SEP +
                USER_LOCA_LICON + TEXT_TYPE + COMMA_SEP +
                CONVERSATION_ID + TEXT_TYPE + COMMA_SEP +
                USER_ID + TEXT_TYPE + ON_CONFLICT_REPLACE + " );";

        return USERTABLE_CREATE;
    }


}

