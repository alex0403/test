package com.qisn.www.socialsdk.internal.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hhly-pc on 2017/9/27.
 */

public class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();

    public static interface NetworkChangeListener {
        void doConnected();
        void doDisconnected();
    }

    private NetworkChangeListener listener;

    public final void reqister(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(mNetworkReceiver, filter);
    }

    /**
     * 移除网络变化广播接收器
     */
    public final void unreqister(Context context) {
        try {
            context.unregisterReceiver(mNetworkReceiver);
            listener = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNetworkChangeListener(NetworkChangeListener listener) {
        this.listener = listener;
    }

    /**
     * 网络变化监听器
     */
    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) { // WIFI发生变化
                // 获取手机的连接服务管理器，这里是连接管理器类
                NetworkInfo.State wifiState = getNetworkState(context,ConnectivityManager.TYPE_WIFI);
                NetworkInfo.State mobileState = getNetworkState(context,ConnectivityManager.TYPE_MOBILE);


                if (NetworkInfo.State.CONNECTED == wifiState || NetworkInfo.State.CONNECTED == mobileState) {
                    if (null != listener) {
                        listener.doConnected();
                    }

                } else if ((NetworkInfo.State.DISCONNECTED == wifiState || NetworkInfo.State.UNKNOWN == wifiState)
                        || (NetworkInfo.State.DISCONNECTED == mobileState || NetworkInfo.State.UNKNOWN == mobileState)) {

                    if (null != listener) {
                        listener.doDisconnected();
                    }
                }
            }
        }

        private NetworkInfo.State getNetworkState(Context c, int type) {
            NetworkInfo.State state = null;
            try{
                ConnectivityManager manager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(null != manager) {
                    NetworkInfo info = manager.getNetworkInfo(type);
                    state = (null != info ? info.getState() : null);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return state;
        }

    };


    /**
     * 检查当前网络状态是否可用
     *
     * @param context 当前上下文
     * @return true为当前网络可用，false则表示当前网络不可用
     */
    public static boolean isNetworkAvailable(Context context){
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //如果当前的网络状态管理器不为空，则输出true
        if (connectivityManager == null){
            return false;
        }else{
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            //检测当前的网络类型，并输出日志
            if (networkInfo!=null && networkInfo.length>0){
                for (int i = 0; i < networkInfo.length; i++){
//                    Log.e("toast",i + "===状态===" + networkInfo[i].getState());
//                    Log.e("toast",i + "===类型===" + networkInfo[i].getTypeName());
                    if(networkInfo[i].getTypeName().equals("mobile") || networkInfo[i].getTypeName().equals("WIFI")){
                        // 判断当前网络状态是否为连接状态
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
