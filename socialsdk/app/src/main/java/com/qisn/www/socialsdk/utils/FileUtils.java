package com.qisn.www.socialsdk.utils;

import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;

import com.qisn.www.socialsdk.internal.bean.msg.LYTMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell on 2017/6/6.
 */

public class FileUtils {

    private static ConcurrentHashMap<Object, Object> fileMap;

    private static List<String> stringList;


    /**  11  表示图片
     *   22  表示文档
     *   33  表示其他
     */
    static {
        fileMap = new ConcurrentHashMap<>();
        fileMap.put(22, "WORD");
        fileMap.put(22, "EXCEL");
        fileMap.put(22, "PDF");
        fileMap.put(22, "TEX");

    }


    static {
        stringList = Collections.synchronizedList(new ArrayList());//会话模式改变
        stringList.add("word");
        stringList.add("docx");
        stringList.add("doc");
        stringList.add("docm");

        stringList.add("dotx");
        stringList.add("dotm");
        stringList.add("xlsx");
        stringList.add("xlsm");
        stringList.add("xltx");
        stringList.add("xltm");
        stringList.add("xlsb");
        stringList.add("xlam");
        stringList.add("pptx");
        stringList.add("ppsx");
        stringList.add("ppsx");
        stringList.add("potx");
        stringList.add("potm");
        stringList.add("ppam");
        stringList.add("excel");
        stringList.add("pdf");
        stringList.add("txt");
    }

    /**
     * 11 表示图片,
     *
     * @return
     */
    public static ConcurrentHashMap<Object, Object> getFileMap() {


        return fileMap;

    }

    public static int getFileMap(String fileName) {
        int fileType = 55;
        Iterator it = fileMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object obj = entry.getValue();
            if (obj != null && obj.equals(fileName)) {
                fileType = (int) entry.getKey();
            }
        }

        return fileType;

    }


    public static final String getFileName(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        return new File(path).getName();
    }

    public static String getFileType(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String mime = "text/plain";
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath);
                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }


    public static String getFileExtension(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return "";
        }
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public static int getType(String fileName) {
        int fileType = LYTMessage.FileType.OTHER.ordinal();
        if (stringList.contains(fileName)) {
            fileType = LYTMessage.FileType.FILE.ordinal();
        }

        return fileType;
    }

    public static int getFileSize(String filePath) {
        File file = new File(filePath);
        int size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                size = fis.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }
}
