package com.qisn.www.socialsdk.internal.bean.msg.base;

/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTZMessageBody extends LYTZBase {

// 2592  清楚普通模式
    //2593 清除消息

    public static final String LYTZMESSAGEBODY_RECEIPE = "1000";//消息回执
    public static final String LYTZMESSAGEBODY_TEXT = "1001";// 文本
    public static final String LYTZMESSAGEBODY_IMAGE = "1002";//图片
    public static final String LYTZMESSAGEBODY_VOICE = "1003";//单人音频
    public static final String LYTZMESSAGEBODY_VIDEO = "1004";//视频
    public static final String LYTZMESSAGEBODY_FILE = "1005";//文件
    public static final String LYTZMESSAGEBODY_LOCATION = "1006";//地理位置
    public static final String LYTZMESSAGEBODY_IMAGE_TEXT = "1007";//图文混合
    public static final String LYTZMESSAGEBODY_ASSIGN = "1008";  //@消息
    public static final String LYTZLYTZMESSAGEBODY_MULTI_VIDEO = "1009";//多人音频视频
    public static final String MESSAGE_CUSTOMIZE = "1999";//自定义消息
    public static final String MESSAGE_CMD = "1998";//透传消息

    public static final String MESSAGE_SYSTEM = "199999";//系统消息

    public static final String MESSAGE_READ_MODEL_NOMAL = "2593";//群主切换到普通消息模式

    public static final String MESSAGE_READ_MODEL = "2591";//群主切换到阅后即焚模式

    public static final String MESSAGE_READ_CLEAR = "2592";//清除消息


    public static final String READ_RECEIPT = "1011";//单人阅后即焚烧已读回执

    public static final String MASS_AIDES = "1012";//群发助手
    public static final String FILE_RECIPT = "1010";//点对点聊天文件消息接受的回执


    private String messageType;


    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
