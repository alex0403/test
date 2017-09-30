package com.qisn.www.socialsdk.config;

import android.text.TextUtils;
import android.util.Log;

import com.qisn.www.socialsdk.internal.manager.SharedPrefManager;
import com.qisn.www.socialsdk.utils.GsonUtils;

import java.util.List;

/**
 * Created by hhly-pc on 2017/9/26.
 */

public class HttpConfig {
    public String appkey;
    public String appsecret;
    public String companyCode;
    public String token;
    public String userId;
    public int function; //1 是 使用讨论组, 2是使用聊天室,3 是 使用所有
    public List<String> region;


    public static final String CJ_MQTTINFO = "cj_mqttinfo";

    public MqttConfig lytMqttInfo;

    protected static HttpConfig mInstance;

    /***
     * 靜態單例法
     */
    private static class HttpConfigHolder{
        public static  HttpConfig mInstance = new HttpConfig();
    }

    public HttpConfig() { }

    public  HttpConfig get(){
        return HttpConfigHolder.mInstance;
    }





    public void saveHttpConfig(HttpConfig socialConfig) {

        Log.e("saveSocialConfig", ":::" + GsonUtils.toJsonString(socialConfig));

        Log.e("saveSocialConfig", "::token111111:" + socialConfig.token);
        SharedPrefManager.get().saveHttpConfig(socialConfig.getClass().getName(), GsonUtils.toJsonString(socialConfig));

    }


    public MqttConfig getHttpConfig(HttpConfig httpConfig) {
        String config = SharedPrefManager.get().getHttpConfig(httpConfig.getClass().getName());
         Log.e("saveSocialConfig", "::getSocialConfig:" + GsonUtils.toJsonString(httpConfig));
        if (null != config && TextUtils.isEmpty(config)) {
            return GsonUtils.parseJsonWithGson(config, MqttConfig.class);
        }
        return null;
    }


    public String getMqttConfig() {
        String config = SharedPrefManager.get().getHttpConfig(CJ_MQTTINFO);
        return config;
    }


    public void saveMqttConfig(String info) {
        SharedPrefManager.get().saveHttpConfig(CJ_MQTTINFO, info);
    }
}
