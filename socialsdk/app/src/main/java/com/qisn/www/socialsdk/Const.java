package com.qisn.www.socialsdk;

/**
 * Created by hhly-pc on 2017/9/27.
 */

public class Const {

    public static class UserInfo {
        public static final String COMPANY = "company";//公司代码
        public static final String APPKEY = "appkey";//当前用户在app下的唯一ID (String)
        public static final String APPSECRET = "appSecret";//当前用户在app下的唯一ID (String)
        public static final String ID = "id";//当前用户在app下的唯一ID (String)
        public static final String SDKTYPE = "sdkType";//当前用户在app下的唯一ID (Integer)
        public static final String COMPANY_CODE = "companyCode";


        public static final String USERID = "userId";//当前用户在app下的唯一ID (String)
        public static final String USERNAME = "userName";//当前用户名称  (String)
        public static final String STATE = "state";//用户备注 (Integer)
        public static final String DESC = "desc";//描述 (String)
        public static final String REMARK = "remark";//备注 (String)
        public static final String ATTR1 = "attr1";//其他信息，最大长度为8，可以修改，可以用作查询 (String)
        public static final String ATTR2 = "attr2";//其他信息，最大长度为512，不可以修改 (String)
        public static final String ATTR3 = "attr3";//	其他信息 (String)

        public static final String OPERATEID = "operateId";//创建者ID (String)


        public static final String ROOMID = "roomId";//聊天室名称 (String)

        public static final String ROOM_NAME = "roomName";//聊天室名称 (String)

        public static final String ROBOT_FLAG = "robotFlag";//“0”未开启，”1”开启机器人 (Integer)

        public static final String ROBOT_TYPE = "robotType";//机器人类型 (String)

        public static final String MEMBERS = "members";//成员 (String)

        public static final String WAYS = "ways";//0:表示运行多端登录，1：允许一端登录(Integer)

        public static final String OPE = "ope";//1表示带上群成员列表，0表示不带群成员列表，只返回群信息(默认不带群成员列表)
    }

    /**
     * SharedPrefManager信息
     */
    public static class SharedPref {
        public static final String PREF_KEY_LOGIN_USER = "lyt.chat.loginuser";             // 登陸信息Key - 用戶名

        public static final String PREF_KEY_LOGIN_PWD = "lyt.chat.loginpwd";               // 登陸密碼Key - password



        public static final String PREF_KEY_FILE_URL = "lyt.chat.fileUploadUrl";           //

        public static final String PREF_KEY_MQTT_HOST = "lyt.chat.host";

        public static final String PREF_KEY_MQTT_PORT = "lyt.chat.port";

        public static final String PREF_KEY_MESSAGE_URL = "lyt.chat.antMessageUrl";

        public static final String PREF_KEY_APPKEY = "lyt.chat.appkey";

        public static final String PREF_KEY_APPCP = "lyt.chat.appCp";

        public static final String PREF_KEY_LOGIN_USER_ID = "lyt.chat.userId";//用户id

        public static final String PREF_KEY_APP_SECRET = "lyt.chat.appSecret";

        public static final String PREF_KEY_APP_TOKEN = "lyt.chat.token";

        public static final String PREF_KEY_APP_XIAOMI_APPID = "lyt.chat.xiaomiAppId";

        public static final String PREF_KEY_APP_XIAOMI_APPKEY = "lyt.chat.xiaomiAppkey";

        public static final String PREF_KEY_APP_MEIZU_APPID = "lyt.chat.meizuAppId";

        public static final String PREF_KEY_APP_MEIZU_APPKEY = "lyt.chat.meizuAppkey";

        public static final String PREF_KEY_APP_PUSH_REGISTER = "lyt.chat.register";

        public static final String Usave_RECEIVETYPE = "lyt.chat.user.receiveType";

        public static final String Usave_FUNCTION = "lyt.chat.user.function";

        public static final String Usave_CLIENTTYPE = "lyt.chat.client.type";

        public static final String LYT_CHAT_MQTTINFO = "lyt.chat.mqttinfo";

        public static final String LYT_CHAT_MESSAGEID = "lyt.chat.list";

        public static final String LYT_CHAT_GROUP = "lyt.chat.group";
    }


}
