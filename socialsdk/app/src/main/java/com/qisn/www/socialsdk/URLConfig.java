package com.qisn.www.socialsdk;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/12/27.
 */

public class URLConfig {


    private URLConfig () {

    }

    public static abstract class GET implements BaseColumns {


        public static final String GET_TOKEN = "oauth/token";//获取token


        public static final String GET_MQTT = "config/mqtts";//获取MQTT 连接参数

        public static final String GET_USER_CLIENTS = "{companyCode}/{appkey}/user/clients";// 获取当前连接的用户列表

        public static final String GET_FIND_ROOMS = "{companyCode}/{appKey}/room/findRooms";//查询用户所在聊天室，返回聊天室列表

        public static final String GET_ROOM_GET = "{companyCode}/{appKey}/room/get";// 查询单个聊天室详细信息

        public static final String GET_ROOM_FINDMEMBERS = "{companyCode}/{appKey}/room/findMembers";// 查询聊天室所有成员基本信息

        public static final String GET_ROOM_GETMEMBER = "{companyCode}/{appKey}/room/getMember";// 查询聊天室单个成员详细信息


        public static final String GET_FIND_ALLROOMS = "{companyCode}/{appKey}/room/findAllRooms";//  查询app下的所有聊天室

    }


    public static abstract class POST implements BaseColumns {

        public static final String POST_USER_UPDATE = "{companyCode}/{appkey}/user/update";//更新用户信息

        public static final String POST_ROOM_ADD = "{companyCode}/{appKey}/room/add";// 创建聊天室


        public static final String POST_ROOM_DELETE = "{companyCode}/{appKey}/room/delete";// 删除聊天室

        public static final String POST_ROOM_ADDMEMBERS = "{companyCode}/{appKey}/room/addMembers";// 添加聊天室成员

        public static final String POST_ROOM_DELETEMEMBERS = "{companyCode}/{appKey}/room/deleteMembers";//  删除聊天室成员


        public static final String POST_ROOM_UPDATEMEMBER = "{companyCode}/{appKey}/room/updateMember";//  改变成员属性

        public static final String POST_ROOM_UPDTE = "{companyCode}/{appKey}/room/updateMember";//  修改聊天室信息


    }


}
