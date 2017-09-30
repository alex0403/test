package com.qisn.www.socialsdk.internal.bean.msg;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2017/6/27.
 */

public class LYTATMessageInfo   implements Serializable {

    private String type; //消息类型，可能是文本、图片等 1001 文本消息  1002 图片消息

    private String content;//内容 文字 或者图片地址


    private String isTipsAll;//是否AT 全部

    private List<String> ids;//@的id

    private List<String> names;//@人的名字


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsTipsAll() {
        return isTipsAll;
    }

    public void setIsTipsAll(String isTipsAll) {
        this.isTipsAll = isTipsAll;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "type :'" + type + '\'' +
//                ", content :'" + content + '\'' +
//                ", isTipsAll :" + isTipsAll +
//                ", ids :" + ids +
//                ", names :" + names +
//                '}';
//    }
//




//    public String toString() {
//
//        StringBuilder stringBuilder=new StringBuilder();
//
//        stringBuilder.append("{");
//        if (type!=null){
//            stringBuilder.append("\""+"type "+"\"").append(":").append("\""+type+"\"").append(",");
//        }
//        if (content!=null){
//            stringBuilder.append("\""+"content "+"\"").append(":").append("\""+content+"\"").append(",");
//        }
//        if (isTipsAll!=null){
//            stringBuilder.append("isTipsAll :").append("\'"+isTipsAll+"\'").append(",");
//        }
//        if (ids!=null){
//            stringBuilder.append("ids :").append(ids).append(",");
//        }
//        if (names!=null){
//            stringBuilder.append("names :").append(names).append(",");
//        }
//
//        stringBuilder.append("}");
//        return stringBuilder.toString();
//    }
}
