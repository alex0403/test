package com.qisn.www.socialsdk.internal.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

import static com.qisn.www.socialsdk.Const.SharedPref.LYT_CHAT_GROUP;
import static com.qisn.www.socialsdk.Const.SharedPref.LYT_CHAT_MESSAGEID;
import static com.qisn.www.socialsdk.Const.SharedPref.LYT_CHAT_MQTTINFO;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APPCP;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APPKEY;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_MEIZU_APPID;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_MEIZU_APPKEY;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_PUSH_REGISTER;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_SECRET;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_TOKEN;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_XIAOMI_APPID;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_APP_XIAOMI_APPKEY;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_FILE_URL;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_LOGIN_PWD;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_LOGIN_USER;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_LOGIN_USER_ID;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_MESSAGE_URL;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_MQTT_HOST;
import static com.qisn.www.socialsdk.Const.SharedPref.PREF_KEY_MQTT_PORT;
import static com.qisn.www.socialsdk.Const.SharedPref.Usave_CLIENTTYPE;
import static com.qisn.www.socialsdk.Const.SharedPref.Usave_FUNCTION;
import static com.qisn.www.socialsdk.Const.SharedPref.Usave_RECEIVETYPE;

/**
 * Created by hhly-pc on 2017/9/27.
 */

public class SharedPrefManager {

    private static Context appContext = null;

    public static SharedPrefManager get() {
        return SharedPrefManagerHolder.mInstance;
    }

    /***
     * 靜態單例,線程安全，且運行高效
     */
    private static class SharedPrefManagerHolder{
       public static  SharedPrefManager  mInstance = new SharedPrefManager();
    }

    /***
     * 設置上下文
     * @param context
     */
    public void setAppContext(Context context){
        this.appContext = context;
    }


    /**
     * 最新登陸用戶名
     */
    private String lastLoginUser = null;
    /***
     * 最新登陸密碼
     */
    private String lastLoginPwd = null;
    /***
     * 小米APP_ID
     */
    private String xiaomiAppId;


    boolean saveHost(String host) {
        if (host != null) {
            return saveOrUpdateByAsnc(PREF_KEY_MQTT_HOST, host);
        }
        return false;
    }

    String getHost() {
        return (String) get(PREF_KEY_MQTT_HOST, DataType.STRING);
    }


    boolean savePort(String port) {
        if (port != null) {
            return saveOrUpdateByAsnc(PREF_KEY_MQTT_PORT, port);
        }
        return false;
    }


    String getPort() {
        return (String) get(PREF_KEY_MQTT_PORT, DataType.STRING);
    }

    String getLastLoginUser() {
        return this.lastLoginUser = (String) get(PREF_KEY_LOGIN_USER, DataType.STRING);

    }

    boolean saveLastLoginUser(String var1) {
        if (var1 != null) {
           return saveOrUpdateByAsnc(PREF_KEY_LOGIN_USER, var1);
        }
        return false;
    }


    boolean savefileUploadUrl(String url) {
        if (url != null) {
             return saveOrUpdateByAsnc(PREF_KEY_FILE_URL, url);
        }
        return false;
    }

    String getfileUploadUrl(String url) {
        return (String) get(PREF_KEY_FILE_URL, DataType.STRING);
    }


    public boolean saveAntMessageUrl(String url) {
        if (url != null) {
            return saveOrUpdateByAsnc(PREF_KEY_MESSAGE_URL, url);
        }
        return false;
    }

    public String getAntMessageUrl() {
         return (String) get(PREF_KEY_MESSAGE_URL, DataType.STRING);
    }

    String getLastLoginPwd() {
       return this.lastLoginPwd =  (String) get(PREF_KEY_LOGIN_PWD, DataType.STRING);
    }

    boolean clearLastLoginUser() {
        return saveOrUpdateByAsnc(PREF_KEY_LOGIN_USER, this.lastLoginUser = "");
    }

    boolean clearLastLoginPwd() {
        return saveOrUpdateByAsnc(PREF_KEY_LOGIN_PWD, this.lastLoginPwd = "");
     }

    public boolean saveLastLoginPwd(String var1) {
        if (var1 != null) {
            this.lastLoginPwd = var1;
            return saveOrUpdateByAsnc(PREF_KEY_LOGIN_PWD, this.lastLoginPwd);
        }
        return false;
    }


    public boolean saveUserId(String userId) {
        return saveOrUpdateByAsnc(PREF_KEY_LOGIN_USER_ID, userId);
    }

    public String getUserId() {
        return (String) get(PREF_KEY_LOGIN_USER_ID, DataType.STRING);
    }


    public boolean saveAppcp(String appcp) {
        return saveOrUpdateByAsnc(PREF_KEY_APPCP, appcp);
    }

    public String getAppcp() {
        return (String) get(PREF_KEY_APPCP, DataType.STRING);
    }

    public boolean saveAppKey(String appkey) {
        return saveOrUpdateByAsnc(PREF_KEY_APPKEY, appkey);
    }

    public String getAppkey() {
        return (String) get(PREF_KEY_APPKEY, DataType.STRING);
    }


    public boolean saveAppSecret(String appSecret) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_SECRET, appSecret);
    }

    public String getAppSecret() {
        return (String) get(PREF_KEY_APP_SECRET, DataType.STRING);
    }

    public boolean saveTokey(String tokey) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_TOKEN, tokey);
    }

    public String getTokey() {
        return (String) get(PREF_KEY_APP_TOKEN, DataType.STRING);
    }


    public boolean saveXiaomiAppId(String xiaomiAppId) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_XIAOMI_APPID, xiaomiAppId);
    }

    public String getXiaomiAppId() {
        return (String) get(PREF_KEY_APP_XIAOMI_APPID, DataType.STRING);
    }


    public boolean saveXiaomiAppKey(String xiaomiAppKey) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_XIAOMI_APPKEY, xiaomiAppKey);
    }

    public String getXiaomiAppKey() {
        return (String) get(PREF_KEY_APP_XIAOMI_APPKEY, DataType.STRING);
    }


    public boolean saveMeiZuAppId(String meizuAppId) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_MEIZU_APPID, meizuAppId);
    }

    public String getMeizuAppId() {
        return (String) get(PREF_KEY_APP_MEIZU_APPID, DataType.STRING);
    }


    public boolean saveMeiZuAppKey(String meizuAppKey) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_MEIZU_APPKEY, meizuAppKey);
    }

    public String getMeizuAppKey() {
        return (String) get(PREF_KEY_APP_MEIZU_APPKEY, DataType.STRING);
    }


    public boolean saveRegister(boolean register) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_PUSH_REGISTER, register);
    }

    public boolean getRegister() {
        return (boolean) get(Usave_RECEIVETYPE, DataType.BOOLEAN);
    }


    public int getReceiveType() {
        return (int) get(Usave_RECEIVETYPE, DataType.INTEGER, 0);
    }

    public boolean saveReceiveType(int receiveType) {
        return saveOrUpdateByAsnc(PREF_KEY_APP_PUSH_REGISTER, receiveType);
    }

    public String getUserName() {
        return null;
    }

    public boolean saveFunction(int function) {
        return saveOrUpdateByAsnc(Usave_FUNCTION, function);
    }

    public int getFunction() {
        return (int) get(Usave_FUNCTION, DataType.INTEGER, 0);
    }

    public boolean saveMQTTConfig(String mqttConfig) {
       return saveOrUpdateByAsnc(LYT_CHAT_MQTTINFO, mqttConfig);
    }


    public boolean saveUserGroupFlag(boolean userGroupFlag) {
         return saveOrUpdateByAsnc(LYT_CHAT_GROUP, userGroupFlag);
    }


    public boolean getUserGroupFlag() {
        return (boolean) get(LYT_CHAT_GROUP, DataType.BOOLEAN);
    }

    public boolean saveClientType(int clientType) {
        return saveOrUpdateByAsnc(Usave_CLIENTTYPE, clientType);
    }


    public int getClientType() {
        return (int) get(Usave_CLIENTTYPE, DataType.INTEGER, 0);
    }


    public String getMessageId(){
        return (String) get(LYT_CHAT_MESSAGEID, DataType.STRING);
    }

    public boolean saveCompanyCode(String companyCode) {
        return saveOrUpdateByAsnc(LYT_CHAT_MESSAGEID, companyCode);
    }

    public String getCompanyCode() {
        return (String) get(LYT_CHAT_MESSAGEID, DataType.STRING);
    }


    public String getMQTTConfig() {
        return (String) get(LYT_CHAT_MQTTINFO, DataType.STRING);
    }

    public boolean saveHttpConfig(String name, String _HttpConfig) {
        return saveOrUpdateByAsnc(name, _HttpConfig);

    }

    public String getHttpConfig(String key) {
        return (String) get(key, DataType.STRING);
    }

    /***
     *
     * 異步提交，不返回任何結果，不能確定成功或者失敗
     * @param key
     * @param name
     *
     */
    private void saveOrUpdateBySync(String key, Object name){
        SharedPreferences.Editor editor = saveOrUpdate(key, name);
        if(editor != null)  editor.apply();
    }

    /**
     *
     * 同步提交，返回提交結果{ture : false}
     *
     * @param key
     * @param name
     * @return
     */
    private boolean saveOrUpdateByAsnc(String key, Object name){
        SharedPreferences.Editor editor = saveOrUpdate(key, name);
        if(editor != null) return editor.commit();
        else return false;
    }

    /**
     *
     * 數據組裝核心類
     *
     * @param key
     * @param name
     * @return
     */
    public SharedPreferences.Editor saveOrUpdate(String key, Object name) {
        SharedPreferences sharef = PreferenceManager.getDefaultSharedPreferences(this.appContext);
        SharedPreferences.Editor editor = sharef.edit();
        if(key == null) return null;

        if(name instanceof  String){
            editor.putString(key, String.valueOf(name));
        }else if(name instanceof Integer){
            editor.putInt(key, (Integer)name);
        }else if(name instanceof Boolean){
            editor.putBoolean(key, (Boolean) name);
        }else if(name instanceof Float){
            editor.putFloat(key, (Float) name);
        }else if(name instanceof Set){
            editor.putStringSet(key, (Set<String>) name);
        }else if(name instanceof  Long){
            editor.putLong(key, (Long) name);
        }
        return editor;
    }

    /**
     * 根据key和类型取出数据
     * @param key
     * @return
     */
    private Object get(String key, DataType type){
        SharedPreferences sharef = PreferenceManager.getDefaultSharedPreferences(this.appContext);
        switch (type) {
            case INTEGER:
                return sharef.getInt(key, -1);
            case FLOAT:
                return sharef.getFloat(key, -1f);
            case BOOLEAN:
                return sharef.getBoolean(key, false);
            case LONG:
                return sharef.getLong(key, -1L);
            case STRING:
                return sharef.getString(key, "");
            case STRING_SET:
                return sharef.getStringSet(key, null);
            default: // 默认取出String类型的数据
                return null;
        }
    }

    /**
     * 根据key和类型取出数据
     * @param key
     * @return
     */
    private Object get(String key, DataType type, Object mDefault){
        SharedPreferences sharef = PreferenceManager.getDefaultSharedPreferences(this.appContext);
        switch (type) {
            case INTEGER:
                return sharef.getInt(key, (Integer) mDefault);
            case FLOAT:
                return sharef.getFloat(key, (Float) mDefault);
            case BOOLEAN:
                return sharef.getBoolean(key, (Boolean) mDefault);
            case LONG:
                return sharef.getLong(key, (Long) mDefault);
            case STRING:
                return sharef.getString(key, (String) mDefault);
            case STRING_SET:
                return sharef.getStringSet(key, (Set<String>) mDefault);
            default: // 默认取出String类型的数据
                return null;
        }
    }

    /**
     * 枚举：存储或取出的数据类型
     */
    enum DataType {
        INTEGER, LONG, BOOLEAN, FLOAT, STRING, STRING_SET
    }
}
