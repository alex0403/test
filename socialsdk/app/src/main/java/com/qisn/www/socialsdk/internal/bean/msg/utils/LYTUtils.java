package com.qisn.www.socialsdk.internal.bean.msg.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.qisn.www.socialsdk.internal.bean.msg.LYTConversationList;
import com.qisn.www.socialsdk.internal.bean.msg.LYTMessage;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/3.
 */

public class LYTUtils {


    private static Object body;

    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        return map;
    }

    public static Map<String, String> getGetMap(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("authorization", token);
        map.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        return map;
    }


    public static Map<String, String> getPostMap(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("authorization", token);
        map.put("Content-Type", "application/json");
        return map;
    }


    public static String getFileName(String name) {
        int index = name.lastIndexOf("/");
        String fileName = name.substring(index + 1);
        return fileName;
    }


    /**
     * 获取随机数
     *
     * @param n
     * @return
     */
    public static String getRandom(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                int k = ran.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }




    public String game(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }


    public static List<LYTMessage> messageSort(List<LYTMessage> lytMessages) {

        Collections.sort(lytMessages, new Comparator<LYTMessage>() {
            @Override
            public int compare(LYTMessage o1, LYTMessage o2) {
                if (o1.getChatIndex() > o2.getChatIndex())
                    return 1;
                else if (o1.getChatIndex() < o2.getChatIndex())
                    return -1;
                else
                    return 0;
            }
        });


        return lytMessages;

    }

    ;

    //获取app版本号
    public static int getVersionCode(Context mContext) {

        PackageManager pm = mContext.getPackageManager(); // 取得包管理器的对象，这样就可以拿到应用程序的管理对象
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0); // 得到应用程序的包信息对象
            return info.versionCode; // 取得应用程序的版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // 此异常不会发生
            return -1;
        }
    }


    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台"
                            + appProcess.processName);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public static List<LYTConversationList> messageSortList(List<LYTConversationList> lytConversationLists) {

        Collections.sort(lytConversationLists, new Comparator<LYTConversationList>() {
            @Override
            public int compare(LYTConversationList o1, LYTConversationList o2) {

                if (o1.getLytMessage().getMsgTime() > o2.getLytMessage().getMsgTime())
                    return 1;
                else if (o1.getLytMessage().getMsgTime() < o2.getLytMessage().getMsgTime())
                    return -1;
                else
                    return 0;


            }
        });



        return null;
    }
}
