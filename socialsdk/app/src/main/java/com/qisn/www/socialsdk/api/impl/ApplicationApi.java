package com.qisn.www.socialsdk.api.impl;

import android.content.Context;

import com.qisn.www.socialsdk.api.IApplicationApi;
import com.qisn.www.socialsdk.base.BaseModuleApi;
import com.qisn.www.socialsdk.callback.DataCallBack;

/**
 * Created by hhly-pc on 2017/9/26.
 */

public class ApplicationApi extends BaseModuleApi implements IApplicationApi {

    private static ApplicationApi mInstance = new ApplicationApi();

    public ApplicationApi(){

    }

    /***
     * 实例化类对象
     * @return
     */

    public synchronized  static ApplicationApi getInstance (Context context) {
        if(mInstance == null){
            mInstance = new ApplicationApi();
        }
        return mInstance;
    }

    /**
     * 获取动态Token（登录之后调用）
     * 需要静态获取用户接入平台类型{@link APP_SDK_TYPE}
     * @param sccret
     * @param userId
     */
    public  void getToken (String sccret, String userId, DataCallBack callBack){

    }

    @Override
    public void getPushConfig () {

    }
}
