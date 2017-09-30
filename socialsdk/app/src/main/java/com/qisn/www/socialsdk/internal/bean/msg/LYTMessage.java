package com.qisn.www.socialsdk.internal.bean.msg;

import android.text.TextUtils;
import android.util.Log;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTBase;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTTextMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.utils.LYTTimeUtils;
import com.qisn.www.socialsdk.internal.bean.msg.utils.LYTUtils;
import com.qisn.www.socialsdk.utils.GsonUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTMessage extends LYTBase<LYTZMessage> implements Serializable {


    LYTMessageBody body;

    LYTZMessage lytObject;

    private static int OS = 3;
    private static String M = "M";

    public static String getMessageType(LYTMessage lytMessage) {
        return lytMessage.getLytObject().getLytzMessageBody().getMessageType();
    }


    public String getMessageType() {
        return lytObject.getLytzMessageBody().getMessageType();
    }

    public LYTZMessage getLytObject() {
        return lytObject;
    }


    private String receipt;

    public void setAtRobot(boolean atRobot) {
        lytObject.setAtRobot(atRobot);
    }


    public LYTMessage(LYTZMessage var1) {
        this.lytObject = var1;
    }


//    public boolean isFromCustomer() {
//        return getFrom().equals(LYTPlugins.getChatConfigPrivate().getUserId());
//    }


    /**
     * 创建系统消息
     *
     * @param content
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createSystrmMessage(String content, int type, String toChatUserId) {//文字消息
        if (TextUtils.isEmpty(content)) {
            new Exception("发送消息不能为空");
        }
        if (TextUtils.isEmpty(toChatUserId) || toChatUserId == null) {

        }
        LYTMessage lytMessage = createSendMessage();
        LYTSystemMessageBody lytTextMessageBody = new LYTSystemMessageBody(content, type);
        lytMessage.addBody(lytTextMessageBody);

        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.getLytObject().setIsRead(1);//设置为已读
        //设置会话ID
        lytMessage.setConversationId(toChatUserId);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);


        return lytMessage;

    }


    /**
     * 创建系统消息
     */
    public static LYTMessage createGiftMessage(String bodyMsg, String tailMsg, String imageUrl, int type, String toChatUserId) {//红包消息
        LYTMessage lytMessage = createSendMessage();
        LYTGiftMessageBody lytGiftMessageBody = new LYTGiftMessageBody();
        lytGiftMessageBody.setBodyMsg(bodyMsg);
        lytGiftMessageBody.setTailMsg(tailMsg);
        lytGiftMessageBody.setImageUrl(imageUrl);
        lytMessage.addBody(lytGiftMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.getLytObject().setIsRead(1);//设置为已读
        //设置会话ID
        lytMessage.setConversationId(toChatUserId);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }


    /**
     * 创建订单消息
     */
    public static LYTMessage createOrderMessage(String bodyMsg, String title, String jumpMsg, String tailMsg, int type, String toChatUserId) {
        LYTMessage lytMessage = createSendMessage();
        LYTOrderMessageBody lytGiftMessageBody = new LYTOrderMessageBody();
        lytGiftMessageBody.setBodyMsg(bodyMsg);
        lytGiftMessageBody.setTitle(title);
        lytGiftMessageBody.setJumpMsg(jumpMsg);
        lytGiftMessageBody.setTailMsg(tailMsg);
        lytMessage.addBody(lytGiftMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.getLytObject().setIsRead(1);//设置为已读
        //设置会话ID
        lytMessage.setConversationId(toChatUserId);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }


    private static void setUserInfo(LYTMessage lytMessage) {
//        LYTUserInfoListener lytUserInfoListener = LYTPlugins.getClient().getChatManager().getLYTUserInfoListener();
//        if (lytUserInfoListener != null) {
//            LYTUserInfo userInfo = lytUserInfoListener.onUserName(LYTPlugins.getChatConfigPrivate().getUserId());
//            if (userInfo != null) {
//                lytMessage.getLytObject().setIocn(userInfo.iocn);
//                lytMessage.getLytObject().setName(userInfo.name);
//            }
//        }
    }

    /**
     * 创建文字消息
     *
     * @param content
     * @param toChatUserId
     * @return
     */

    public static LYTMessage createTxtSendMessage(String content, String toChatUserId) {//文字消息
        if (TextUtils.isEmpty(content)) {
            new Exception("发送消息不能为空");
        }
        if (TextUtils.isEmpty(toChatUserId) || toChatUserId == null) {


        }
        LYTMessage lytMessage = createSendMessage();
        LYTTextMessageBody lytTextMessageBody = new LYTTextMessageBody(content);
        lytMessage.addBody(lytTextMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }

    /**
     * 单人文件回执
     *
     * @param content
     * @param toChatUserId
     * @return
     */

    public static LYTMessage createFileReceiptSendMessage(String content, String toChatUserId) {
        if (TextUtils.isEmpty(content)) {
            new Exception("发送消息不能为空");
        }
        if (TextUtils.isEmpty(toChatUserId) || toChatUserId == null) {


        }
        LYTMessage lytMessage = createSendMessage();
        LYTFileReceiptMessageBody lytFileReceiptMessageBody = new LYTFileReceiptMessageBody(content);
        lytMessage.addBody(lytFileReceiptMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }


    /**
     * 发送撤销消息
     *
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createRevokeSendMessage(String messageId, String toChatUserId) {

        LYTMessage lytMessage = createSendMessage();
        LYTRevokeMessageBody lytRevokeMessageBody = new LYTRevokeMessageBody(messageId);
        lytMessage.addBody(lytRevokeMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }


    /**
     * 创建阅后即焚烧已读消息
     *
     * @param messageId
     * @param readIndex
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createReadReceiptMessage(String messageId, long readIndex, String toChatUserId) {

        LYTMessage lytMessage = createSendMessage();
        LYTReadMessageBody lytReadMessageBody = new LYTReadMessageBody(readIndex, messageId);
        lytMessage.addBody(lytReadMessageBody);
        lytMessage.getLytObject().setIsDestroy(1);//设置为阅后即焚烧消息
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.getLytObject().setIsRead(1);//设置为已读
        //设置会话ID
        lytMessage.setConversationId(toChatUserId);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }


    /**
     * 创建@消息
     *
     * @param lytatMessageInfos
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createATSendMessage(List<LYTATMessageInfo> lytatMessageInfos, String toChatUserId) {//文字消息

        LYTATMessageBody lytatMessageBody = new LYTATMessageBody(lytatMessageInfos);
        LYTMessage lytMessage = createSendMessage();
        lytMessage.addBody(lytatMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setAtState(1);
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;

    }


    /**
     * 创建图片消息
     *
     * @param imagePath
     * @param isOriginal
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createImageSendMessage(String imagePath, boolean isOriginal, String toChatUserId) {
        if (!(new File(imagePath)).exists()) {
            Log.e("msg", "image file does not exsit");
            return null;
        } else {
            LYTMessage lytMessage = createSendMessage();//TODO
            lytMessage.setReceipt(toChatUserId);
            String fileName = LYTUtils.getFileName(imagePath);
            LYTImageMessageBody lytImageMessageBody = new LYTImageMessageBody(new File(imagePath));
            lytImageMessageBody.setSendOriginalImage(isOriginal);
            lytImageMessageBody.setFileName(fileName);
            lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
            lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
            lytMessage.addBody(lytImageMessageBody);
            setUserInfo(lytMessage);
            return lytMessage;
        }

    }

    /**
     * 创建语音消息
     *
     * @param filePath
     * @param length
     * @param toChatUserId
     * @return
     */

    public static LYTMessage createVoiceSendMessage(String filePath, int length, String toChatUserId) {

        if (!(new File(filePath)).exists()) {
            Log.e("msg", "voice file does not exsit");
            return null;
        } else {
            LYTMessage lytMessage = createSendMessage();//TODO
            lytMessage.setReceipt(toChatUserId);
            lytMessage.getLytObject().setVideoState(true);
            String fileName = LYTUtils.getFileName(filePath);
            LYTVoiceMessageBody lytVoiceMessageBody = new LYTVoiceMessageBody(new File(filePath), length);
            lytVoiceMessageBody.setFileName(fileName);
            lytMessage.addBody(lytVoiceMessageBody);
            lytMessage.setVoiceState(1);// 自己发送的消息为已读
            lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
            lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
            setUserInfo(lytMessage);
            return lytMessage;
        }


    }


    /**
     * 创建视频会议消息
     *
     * @param eventType
     * @param callType
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createSimpleVideoSendMessage(int eventType, int callType, String toChatUserId) {
        LYTMessage lytMessage = createSendMessage();//TODO
        lytMessage.setReceipt(toChatUserId);
        LYTSimpleVideoMessageBody lytSimpleVideoMessageBody = new LYTSimpleVideoMessageBody(eventType, callType);
        lytMessage.setReceipt(toChatUserId);
//        lytVideoMessageBody.setCreatorId(LYTPlugins.getChatConfigPrivate().getUserId());
//        if (duration != null && !duration.equals("")) {
//
//            lytVideoMessageBody.setDuration(duration);
//        }
//        if (status == 1) {
//            lytMessage.getLytObject().setVideoState(true);
//        } else {
//            lytMessage.getLytObject().setVideoState(false);
//        }
        lytMessage.addBody(lytSimpleVideoMessageBody);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;
    }


    /**
     * 创建视频会议消息
     *
     * @param status
     * @param name
     * @param callType
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createVideoSendMessage(int status, String roomId, String name, int callType, String duration, String toChatUserId) {
        LYTMessage lytMessage = createSendMessage();//TODO
        lytMessage.setReceipt(toChatUserId);
        LYTVideoMessageBody lytVideoMessageBody = new LYTVideoMessageBody(status, roomId, name, callType);
//        lytVideoMessageBody.setCreatorId(LYTPlugins.getChatConfigPrivate().getUserId());
        if (duration != null && !duration.equals("")) {

            lytVideoMessageBody.setDuration(duration);
        }
        if (status == 1) {
            lytMessage.getLytObject().setVideoState(true);
        } else {
            lytMessage.getLytObject().setVideoState(false);
        }
        lytMessage.addBody(lytVideoMessageBody);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;
    }


    /**
     * 创建阅后即焚消息
     *
     * @param messageType
     * @param chatType
     * @param toChatUserId @return
     */
    public static LYTMessage createReadModelMessage(String messageType, int chatType, String toChatUserId) {
        LYTMessage lytMessage = createSendMessage();//TODO
        lytMessage.setReceipt(toChatUserId);
        lytMessage.setChatType(chatType);
        LYTReadModelMesaageBody lytReadModelMesaageBody = new LYTReadModelMesaageBody(messageType);
        lytMessage.addBody(lytReadModelMesaageBody);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;
    }

    /**
     * 创建文件消息
     *
     * @param filePath
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createFileSendMessage(String filePath, String size, String toChatUserId) {
        File var2 = new File(filePath);
        if (!var2.exists()) {
            Log.e("msg", "file does not exist");
            return null;
        } else {
            LYTMessage lytMessage = createSendMessage();//TODO
            lytMessage.setReceipt(toChatUserId);
            String fileName = LYTUtils.getFileName(filePath);
            LYTNormalFileMessageBody lytNormalFileMessageBody = new LYTNormalFileMessageBody(new File(filePath), size);
            lytNormalFileMessageBody.setName(fileName);
            lytMessage.addBody(lytNormalFileMessageBody);
            lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
            lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
            setUserInfo(lytMessage);
            return lytMessage;
        }

    }


    /**
     * 创建地理位置消息
     *
     * @param latitude
     * @param longitude
     * @param locationAddress
     * @param toChatUserId
     * @return
     */
    public static LYTMessage createLocaltionSendMessage(double latitude, double longitude, String locationAddress, String toChatUserId) {
        LYTMessage lytMessage = createSendMessage();
        LYTLocationMessageBody lytLocationMessageBody = new LYTLocationMessageBody(latitude, longitude, locationAddress);
        lytMessage.setReceipt(toChatUserId);

        lytMessage.addBody(lytLocationMessageBody);
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);

        return lytMessage;
    }

    /**
     * 创建评论消息
     *
     * @param content
     * @param toChatUserId
     * @return
     */

    public static LYTMessage createCommentMessage(String content, String toChatUserId) {//评论消息
        if (TextUtils.isEmpty(content)) {
            new Exception("发送消息不能为空");
        }
        LYTMessage lytMessage = createSendMessage();
        LYTCommentMessageBody lytCommentMessageBody = new LYTCommentMessageBody(content);
        lytMessage.addBody(lytCommentMessageBody);
        lytMessage.setReceipt(toChatUserId);//发送给谁
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        lytMessage.setChatType(ChatType.COMMENT.ordinal());
        return lytMessage;
    }

    public void addBody(LYTMessageBody lytMessageBody) {
        this.body = lytMessageBody;
        this.lytObject.addBody(lytMessageBody.lytObject);//LYTZTextMessageBody

    }

    private static LYTMessage createSendMessage() {
        LYTZMessage lytzMessage = LYTZMessage.createSendMessage(ChatType.ChatRoom.ordinal(), 3);
        LYTMessage lytMessage = new LYTMessage(lytzMessage);
        return lytMessage;
    }


    public void setReceipt(String receipt) {
        this.lytObject.setTo(receipt);
    }

    public void setChatType(int chatType) {
        this.lytObject.setChatType(chatType);
    }

    public void setIndex(long index) {
        this.lytObject.setChatIndex(index);
    }

    public String conversationId() {
        return lytObject.getConversationId();
    }

    public String getTo() {
        return lytObject.getTo();
    }

    public String getFrom() {
        return lytObject.getFromId();
    }


    public LYTMessage.ChatType getChatType() {
        int chatTpye = this.lytObject.chatType();
        LYTMessage.ChatType var2 = LYTMessage.ChatType.Chat;
        if (chatTpye == LYTZMessage.LYTZChatType.Chat.ordinal()) {
            var2 = LYTMessage.ChatType.Chat;
        } else if (chatTpye == LYTZMessage.LYTZChatType.ChatRoom.ordinal()) {
            var2 = LYTMessage.ChatType.ChatRoom;
        } else if (chatTpye == LYTZMessage.LYTZChatType.GroupChat.ordinal()) {
            var2 = LYTMessage.ChatType.GroupChat;
        } else if (chatTpye == LYTZMessage.LYTZChatType.COMMET.ordinal()) {
            var2 = ChatType.COMMENT;
        } else if (chatTpye == LYTZMessage.LYTZChatType.MASS.ordinal()) {
            var2 = ChatType.MASS;
        }
        return var2;
    }


    public LYTMessageBody getBody() {//TODO
        LYTZMessageBody messageBody = lytObject.getLytzMessageBody();
        if (messageBody instanceof LYTZTextMessageBody) {
            body = new LYTTextMessageBody((LYTZTextMessageBody) messageBody);
        } else if (messageBody instanceof LYTZImageMessageBody) {
            body = new LYTImageMessageBody((LYTZImageMessageBody) messageBody);
        } else if (messageBody instanceof LYTZCMDMessageBody) {
            body = new LYTCMDMessageBody((LYTZCMDMessageBody) messageBody);
        } else if (messageBody instanceof LYTZImageMessageBody) {
            body = new LYTImageMessageBody((LYTZImageMessageBody) messageBody);
        } else if (messageBody instanceof LYTZNormalFileMessageBody) {
            body = new LYTNormalFileMessageBody((LYTZNormalFileMessageBody) messageBody);
        } else if (messageBody instanceof LYTZVoiceMessageBody) {
            body = new LYTVoiceMessageBody((LYTZVoiceMessageBody) messageBody);
        } else if (messageBody instanceof LYTZSystemMessageBody) {
            body = new LYTSystemMessageBody((LYTZSystemMessageBody) messageBody);
        } else if (messageBody instanceof LYTZVideoMessageBody) {
            body = new LYTVideoMessageBody((LYTZVideoMessageBody) messageBody);
        } else if (messageBody instanceof LYTZATMessageBody) {
            body = new LYTATMessageBody((LYTZATMessageBody) messageBody);
        } else if (messageBody instanceof LYTZReadMessageBody) {
            body = new LYTReadMessageBody((LYTZReadMessageBody) messageBody);
        } else if (messageBody instanceof LYTZFileReceiptMessageBody) {
            body = new LYTFileReceiptMessageBody((LYTZFileReceiptMessageBody) messageBody);
        } else if (messageBody instanceof LYTZMassAidesMessageBody) {
            body = new LYTMassAidesMessageBody((LYTZMassAidesMessageBody) messageBody);
        } else if (messageBody instanceof LYTZRevokeMessageBody) {
            body = new LYTRevokeMessageBody((LYTZRevokeMessageBody) messageBody);
        } else if (messageBody instanceof LYTZLawyerNoticeMessageBody) {
            body = new LYTLawyerNoticeMessageBody((LYTZLawyerNoticeMessageBody) messageBody);
        } else if (messageBody instanceof LYTZOrderMessageBody) {
            body = new LYTOrderMessageBody((LYTZOrderMessageBody) messageBody);
        } else if (messageBody instanceof LYTZGiftMessageBody) {
            body = new LYTGiftMessageBody((LYTZGiftMessageBody) messageBody);
        } else if (messageBody instanceof LYTZSimpleVideoMessageBody) {
            body = new LYTSimpleVideoMessageBody((LYTZSimpleVideoMessageBody) messageBody);
        }
        if (body != null) {
            return body;
        } else {
            return null;
        }

    }

    public String getMsgId() {
        return lytObject.getMsgId();
    }


    public void setResend(boolean isServe) {
        lytObject.setResend(isServe);
    }

    public boolean isResend() {
        return lytObject.isResend();
    }

    public long getMsgTime() {
        return lytObject.getMsgTime();
    }

    public void setMsgTime(long msgTime) {
        this.lytObject.setMsgTime(msgTime);
    }

    public void setSendSate(int sate) {
        this.lytObject.setSendState(sate);
    }

    public int getSendState() {
        return lytObject.getSendState();
    }

    public long getChatIndex() {
        return lytObject.getChatIndex();
    }


    public static LYTMessage createCMDSendMessage(String toChatUserId, LYTCMDMessageBody cmdBody) {
        if (cmdBody != null && !TextUtils.isEmpty(toChatUserId)) {
            LYTMessage lytMessage = createSendMessage();
            lytMessage.addBody(cmdBody);
            lytMessage.setReceipt(toChatUserId);
            lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
            lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
            return lytMessage;
        } else {
            return null;
        }

    }


    /**
     * 创建群发助手消息
     *
     * @param content
     * @param toChatUserIds
     * @return
     */
    public static LYTMessage createMassAidesSendMessage(String content, List<String> toChatUserIds) {
        if (TextUtils.isEmpty(content)) {
            new Exception("发送消息不能为空");
        }
        LYTMessage lytMessage = createSendMessage();
        LYTMassAidesMessageBody lytMassAidesMessageBody = new LYTMassAidesMessageBody(content);
        lytMessage.addBody(lytMassAidesMessageBody);
        lytMessage.setReceipt(GsonUtils.toJsonString(toChatUserIds));//发送给谁
        lytMessage.setMsgTime(LYTTimeUtils.getLocalTime());
        lytMessage.setMsgId(M + LYTTimeUtils.getLocalTime() + OS + LYTUtils.getRandom(8));
        setUserInfo(lytMessage);
        return lytMessage;
    }


    public void getLYTMessage(LYTMessage lytMessage) {
        String type = lytMessage.getLytObject().getLytzMessageBody().getMessageType();


    }


    public void setMsgId(String msgId) {
        this.lytObject.setMsgId(msgId);
    }

    public void setAtState(int atState) {
        this.lytObject.setAtState(atState);
    }

    public static LYTMessageBody getMessage(byte[] body, String messageType) {//TODO 处理消息

//        if (0==body.length){
//            Log.e("LYTMessage",":111111111:");
//        }
//        if (null==body){
//            Log.e("LYTMessage",":2222222222:");
//        }
//
//        if (TextUtils.isEmpty(body.toString())){
//            Log.e("LYTMessage",":3333333333:");
//        }
//
        if (TextUtils.isEmpty(messageType)||body.length<=1) {//类型
            return new LYTTextMessageBody("");
        }

        if (messageType.equals(Type.TXT.getName())) {
            LYTTextMessageBody lytzTextMessageBody = new LYTTextMessageBody((LYTZTextMessageBody) byteToObject(body));
            return lytzTextMessageBody;
        } else if (messageType.equals(Type.IMAGE.getName())) {
            LYTZImageMessageBody lytzImageMessageBody1 = (LYTZImageMessageBody) byteToObject(body);
            return new LYTImageMessageBody(lytzImageMessageBody1);
        } else if (messageType.equals(Type.VOICE.getName())) {
            LYTZVoiceMessageBody lytzVoiceMessageBody = (LYTZVoiceMessageBody) byteToObject(body);
            return new LYTVoiceMessageBody(lytzVoiceMessageBody);
        } else if (messageType.equals(Type.VIDEO.getName())) {

        } else if (messageType.equals(Type.FILE.getName())) {
            LYTZNormalFileMessageBody lytzNormalFileMessageBody = (LYTZNormalFileMessageBody) byteToObject(body);
            return new LYTNormalFileMessageBody(lytzNormalFileMessageBody);

        } else if (messageType.equals(Type.SYSTEMMESSAGE.getName())) {
            LYTZSystemMessageBody lytzSystemMessageBody = (LYTZSystemMessageBody) byteToObject(body);
            return new LYTSystemMessageBody(lytzSystemMessageBody);
        } else if (messageType.equals(Type.FILE_RECIPT.getName())) {
            LYTZFileReceiptMessageBody lytzFileReceiptMessageBody = (LYTZFileReceiptMessageBody) byteToObject(body);
            return new LYTFileReceiptMessageBody(lytzFileReceiptMessageBody);
        } else if (messageType.equals(Type.MULTI_VIDEO.getName())) {

            LYTZVideoMessageBody lytzVideoMessageBody = (LYTZVideoMessageBody) byteToObject(body);
            return new LYTVideoMessageBody(lytzVideoMessageBody);

        } else if (messageType.equals(Type.SIMPLE_VIDEO.getName())) {
            LYTZSimpleVideoMessageBody lytzSimpleVideoMessageBody = (LYTZSimpleVideoMessageBody) byteToObject(body);
            return new LYTSimpleVideoMessageBody(lytzSimpleVideoMessageBody);

        } else if (messageType.equals(Type.IMAGE_TEXT.getName())) {


        } else if (messageType.equals(Type.MASSAIDES.getName())) {
            LYTZMassAidesMessageBody lytzMassAidesMessageBody = (LYTZMassAidesMessageBody) byteToObject(body);
            return new LYTMassAidesMessageBody(lytzMassAidesMessageBody);
        } else if (messageType.equals(Type.AT.getName())) {
            LYTZATMessageBody lytzatMessageBody = (LYTZATMessageBody) byteToObject(body);
            return new LYTATMessageBody(lytzatMessageBody);

        } else if (messageType.equals(Type.REVOKE.getName())) {
            LYTZRevokeMessageBody lytzRevokeMessageBody = (LYTZRevokeMessageBody) byteToObject(body);
            return new LYTRevokeMessageBody(lytzRevokeMessageBody);

        } else if (messageType.equals(Type.LAWYERNOTIC.getName())) {
            LYTZLawyerNoticeMessageBody lytzLawyerNoticeMessageBody = (LYTZLawyerNoticeMessageBody) byteToObject(body);
            return new LYTLawyerNoticeMessageBody(lytzLawyerNoticeMessageBody);

        } else if (messageType.equals(Type.LAWYERORDER.getName())) {
            LYTZOrderMessageBody lytzOrderMessageBody = (LYTZOrderMessageBody) byteToObject(body);
            return new LYTOrderMessageBody(lytzOrderMessageBody);

        } else if (messageType.equals(Type.LAWYERGIFT.getName())) {
            LYTZGiftMessageBody lytzGiftMessageBody = (LYTZGiftMessageBody) byteToObject(body);
            return new LYTGiftMessageBody(lytzGiftMessageBody);

        } else if (messageType.equals(Type.MULTI_VIDEO.getName())) {


        } else if (messageType.equals(Type.CUSTOMIZE.getName())) {


        } else {

        }


        return new LYTTextMessageBody("");
    }

    public static LYTZMessageBody getMessage(String messageType, String content) {//TODO 处理消息
        if (TextUtils.isEmpty(messageType)) {//类型
            return new LYTZTextMessageBody("");
        }
        if (messageType.equals(Type.TXT.getName())) {
            LYTZTextMessageBody lytzTextMessageBody = new LYTZTextMessageBody(content);
            return lytzTextMessageBody;
        } else if (messageType.equals(Type.IMAGE.getName())) {
            LYTZImageMessageBody lytzImageMessageBody = GsonUtils.parseJsonWithGson(content, LYTZImageMessageBody.class);
            return lytzImageMessageBody;
        } else if (messageType.equals(Type.VOICE.getName())) {
            LYTZVoiceMessageBody lytzVoiceMessageBody = GsonUtils.parseJsonWithGson(content, LYTZVoiceMessageBody.class);
            return lytzVoiceMessageBody;
        } else if (messageType.equals(Type.VIDEO.getName())) {

        } else if (messageType.equals(Type.FILE.getName())) {
            LYTZNormalFileMessageBody lytzNormalFileMessageBody = GsonUtils.parseJsonWithGson(content, LYTZNormalFileMessageBody.class);
            return lytzNormalFileMessageBody;
        } else if (messageType.equals(Type.MULTI_VIDEO.getName())) {
            LYTZVideoMessageBody lytzVideoMessageBody = GsonUtils.parseJsonWithGson(content, LYTZVideoMessageBody.class);

            return lytzVideoMessageBody;

        } else if (messageType.equals(Type.READ_RECEIPT.getName())) {

            LYTZReadMessageBody lytzReadMessageBody = GsonUtils.parseJsonWithGson(content, LYTZReadMessageBody.class);
            return lytzReadMessageBody;

        } else if (messageType.equals(Type.MASSAIDES.getName())) {
            LYTZMassAidesMessageBody lytzMassAidesMessageBody = new LYTZMassAidesMessageBody(content);
            return lytzMassAidesMessageBody;
        } else if (messageType.equals(Type.IMAGE_TEXT.getName())) {


        } else if (messageType.equals(Type.AT.getName())) {
            List<LYTATMessageInfo> lytatMessageInfos = GsonUtils.jsonToArrayList(content, LYTATMessageInfo.class);
            LYTZATMessageBody lytzVideoMessageBody = new LYTZATMessageBody(lytatMessageInfos);
//            LYTZVideoMessageBody lytzVideoMessageBody = LYTGsonUtil.parseJsonWithGson(content, LYTZVideoMessageBody.class);
            return lytzVideoMessageBody;

        } else if (messageType.equals(Type.REVOKE.getName())) {
            LYTZRevokeMessageBody lytzRevokeMessageBody = GsonUtils.parseJsonWithGson(content, LYTZRevokeMessageBody.class);
            return lytzRevokeMessageBody;
        } else if (messageType.equals(Type.FILE_RECIPT.getName())) {
            LYTZFileReceiptMessageBody lytzFileReceiptMessageBody = new LYTZFileReceiptMessageBody(content);
            return lytzFileReceiptMessageBody;

        } else if (messageType.equals(Type.MULTI_VIDEO.getName())) {

        } else if (messageType.equals(Type.CUSTOMIZE.getName())) {

        } else if (messageType.equals(Type.NOTIFICATION.getName()) || messageType.equals(Type.ONDELETENOTIFICATION.getName()) || messageType.equals(Type.SYNOTIFICATION.getName())) {//通知
            LYTZNotificationBody lytzNotificationBody = GsonUtils.parseJsonWithGson(content, LYTZNotificationBody.class);
//            lytzNotificationBody.setMessageType(messageType);
            return lytzNotificationBody;
        } else if (messageType.equals(Type.NOTIFICATIONS.getName())) {//2580
            List<LYTZNotificationBody> lytzNotificationBodyList = GsonUtils.jsonToArrayList(content, LYTZNotificationBody.class);
            LYTZNotificationBodys lytzNotificationBodys = new LYTZNotificationBodys();
            lytzNotificationBodys.setLytzNotificationBodies(lytzNotificationBodyList);
            return lytzNotificationBodys;
        }else if (messageType.equals(Type.SIMPLE_VIDEO.getName())){
            LYTZSimpleVideoMessageBody lytzSimpleVideoMessageBody = GsonUtils.parseJsonWithGson(content, LYTZSimpleVideoMessageBody.class);
            return lytzSimpleVideoMessageBody;
        }

        else if (messageType.equals(Type.LAWYERGIFT.getName())) {//红包消息
            LYTZGiftMessageBody lytzGiftMessageBody = GsonUtils.parseJsonWithGson(content, LYTZGiftMessageBody.class);
            return lytzGiftMessageBody;
        } else if (messageType.equals(Type.LAWYERNOTIC.getName())) {//系统消息
            LYTZLawyerNoticeMessageBody lytzGiftMessageBody = GsonUtils.parseJsonWithGson(content, LYTZLawyerNoticeMessageBody.class);
            return lytzGiftMessageBody;
        } else if (messageType.equals(Type.LAWYERORDER.getName())) {//订单消息
            LYTZOrderMessageBody lytzOrderMessageBody = GsonUtils.parseJsonWithGson(content, LYTZOrderMessageBody.class);
            return lytzOrderMessageBody;
        } else {

        }


        return null;
    }

    public static LYTZMessageBody getCommentMessage(String messageType, String content) {//TODO 处理消息
        if (TextUtils.isEmpty(messageType)) {//类型
            return new LYTZTextMessageBody("");
        }

        if (messageType.equals(Type.TXT.getName())) {
            LYTZCommentMessageBody lytzTextMessageBody = new LYTZCommentMessageBody(content);
            return lytzTextMessageBody;
        }


        return new LYTZTextMessageBody("");
    }


    public static Object byteToObject(byte[] bytes) {
        Object obj = null;

        try {
            ByteArrayInputStream e = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(e);
            obj = oi.readObject();
            e.close();
            oi.close();
        } catch (Exception var5) {
            Log.e("byteToObject", "translation" + var5.getMessage());
            var5.printStackTrace();
        }

        return obj;
    }

    public void setConversationId(String conversationId) {
        this.lytObject.setConversationId(conversationId);

    }

    public void setFromId(String userId) {
        this.lytObject.setFromId(userId);
    }

    public String getMessageId() {
        return lytObject.getMsgId();
    }

    public int getOS() {
        return lytObject.getOs();
    }

    public void setReadModel(int model) {
        lytObject.setIsDestroy(model);
    }

    public void setVoiceState(int unread) {
        lytObject.setVoiceState(unread);
    }

    public void setMessageType(String messageType) {
        lytObject.getLytzMessageBody().setMessageType(messageType);
    }


    public static enum ChatType {
        flag,
        Chat,
        ChatRoom,
        COMMENT,
        GroupChat,
        flag1,
        flag2,
        flag3,
        flag4,
        flag5,
        flag6,
        flag7,
        MASS;

        private ChatType() {
        }
    }

    public static enum Type {

        TXT("1001"),//文本
        IMAGE("1002"),//图片
        VOICE("1003"),//语音
        VIDEO("1004"),//视频
        FILE("1005"),//文件
        LOCATION("1006"),//地理位置
        IMAGE_TEXT("1007"),//图文混合
        AT("1008"),//@消息
        MASSAIDES("1012"),//群发助手
        MULTI_VIDEO("1009"),//多人音视频

        SIMPLE_VIDEO("1013"),//单人音视频
        READ_RECEIPT("1011"),//单人阅后即焚烧

        FILE_RECIPT("1010"),//单人文件 笑死

        REVOKE("1014"),//撤销消息

        SYSTEMMESSAGE("199999"),//系统消息
        CUSTOMIZE("1999"),//自定义
        NOTIFICATIONS("2580"),//通知
        NOTIFICATION("2581"),//通知
        ONDELETENOTIFICATION("2582"),//删除通知
        SYNOTIFICATION("2583"),//异步消息
        READMODELABNOMAL("2591"),//阅后即焚
        READMODELNOMAL("2593"),//正常模式
        READMODELCLEAR("2592"),//阅后即焚下清除聊天消息
        LAWYERORDER("3997"),//律师订单
        LAWYERNOTIC("3999"),//律师通知
        LAWYERGIFT("3998");//律师红包
        private String name;

        Type(String s) {
            name = s;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static enum State {
        flag,
        SENDING,
        SENDSUCCESS,
        SENDERROR;

        private State() {
        }
    }


    public static enum FileType {
        flag1,
        flag2,
        flag3,
        IMGE, //3
        FILE, //4
        OTHER,//5
        ALL;//6

        private FileType() {
        }

    }


}
