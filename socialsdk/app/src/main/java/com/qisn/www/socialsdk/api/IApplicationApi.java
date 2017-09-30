package com.qisn.www.socialsdk.api;

import com.qisn.www.socialsdk.callback.DataCallBack;

/*************************************************
 *
 * 应用程序级别接口获取
 *
 * @author  liuc1351
 * @date    2017/9/26.
 *
 ************************************************/

public interface IApplicationApi {

   /**
     * 获取动态Token（登录之后调用）
     *
     * @param sccret
     * @param userId
     */
    public void getToken (String sccret, String userId, DataCallBack callBack);

    public void getPushConfig();


}
