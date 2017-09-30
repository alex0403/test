package com.qisn.www.socialsdk.callback;

/**
 * Created by hhly-pc on 2017/9/26.
 */

public interface DataCallBack extends BaseCallBack{
    /***
     * 成功返回状态
     * @param object
     * @param state
     */
    public void onSuccess(Object object, int state);

    /***
     * 失败返回状态
     * @param throwable
     * @param errorCode
     */
    public void onFailure(Throwable throwable, int errorCode);
}
