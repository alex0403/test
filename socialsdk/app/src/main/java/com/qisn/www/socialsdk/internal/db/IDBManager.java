package com.qisn.www.socialsdk.internal.db;


import com.qisn.www.socialsdk.internal.bean.LYTGroupInfo;
import com.qisn.www.socialsdk.internal.bean.LYTGroupMember;
import com.qisn.www.socialsdk.internal.bean.LYTMGroup;
import com.qisn.www.socialsdk.internal.bean.LYTSessoinList;
import com.qisn.www.socialsdk.internal.bean.msg.LYTMessage;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZNotificationBody;

import java.util.List;

/**
 * Created by dell on 2017/8/23.
 */

public interface IDBManager {

    /**
     * 创建表
     *
     * @param sql
     * @return
     */
    public boolean createTable(String sql);

    /**
     * 初始化应用的所有数据库
     *
     * @param sql
     */
    public void initTable(String sql);

    /**
     * 保存讨论组列表和详情
     *
     * @param tableName
     * @param info
     * @return
     */
    public boolean saveGroupList(String tableName, LYTMGroup info);


    /**
     * 根据讨论组id删除讨论组
     *
     * @param s
     * @param groupId
     * @return
     */
    public boolean deleteGroupByGroupId(String s, String groupId);

    /**
     * 根据id删除会话列表
     *
     * @param tableName
     * @param id
     * @return
     */
    public boolean deleteSessionById(String tableName, String id);

    /**
     * 保存成员表
     *
     * @param s
     * @param member
     * @return
     */
    public boolean saveMemberToGroup(String s, LYTGroupMember member);

    /**
     * 中间介
     *
     * @param tableName
     * @param userId
     * @param groupId
     */
    public void saveInm(String tableName, String userId, String groupId, String position, int roleLevel);

    /**
     * 更新群主
     *
     * @param s
     * @param groupId
     * @param newOwnerId
     * @return
     */
    public boolean updateGroupOwner(String s, String groupId, String newOwnerId);

    /**
     * 更新群组信息
     *
     * @param s
     * @param LYTMGroup
     * @return
     */
    public boolean updateGroupInfo(String s, LYTMGroup LYTMGroup);

    /**
     * 更改讨论组设置
     *
     * @param s
     * @param LYTMGroup
     * @return
     */
    public boolean modifyGroupSet(String s, LYTMGroup LYTMGroup);


    /**
     * 删除讨论组或者聊天室成员
     *
     * @param s
     * @param groupId
     * @param userId
     */

    public void deleteInm(String s, String groupId, String userId);

    /**
     * 保存讨论组公告
     *
     * @param s
     * @param groupId
     * @param lytzNotificationBody
     * @return
     */

    public boolean saveNotice(String s, String groupId, LYTZNotificationBody lytzNotificationBody);

    /**
     * 更新讨论组公告为已读
     *
     * @param tableName
     * @param groupId
     * @param notificationId
     * @return
     */
    boolean updateNoticeReadState(String tableName, String groupId, String notificationId);

    /**
     * 根据公告ID 删除公告信息
     *
     * @param tableName
     * @param groupId
     * @param notificationId
     * @return
     */

    boolean deleteNoticeByNoticeId(String tableName, String groupId, String notificationId);

    /**
     * 保存消息
     *
     * @param tableName
     * @param lytMessage
     * @return
     */
    boolean saveMessage(String tableName, LYTMessage lytMessage);

    /**
     * @param tableName
     * @param s
     * @param chatType
     * @param toId
     * @return
     */
    @Deprecated
    boolean saveSession(String tableName, String s, int chatType, String toId);


    /**
     * 会话列表保存及保存最后一条消息
     *
     * @param tableName
     * @param lytMessage
     * @param useId
     * @return
     */
    boolean saveSession(String tableName, LYTMessage lytMessage, String useId);

    /**
     * 移除管理员权限
     *
     * @param tableName
     * @param sessionId
     * @param manageId
     * @return
     */
    boolean removeAdminFromGroup(String tableName, String sessionId, String manageId);

    /**
     * 添加讨论组成员
     *
     * @param tableName
     * @param sessionId
     * @param manageId
     * @return
     */

    boolean addAdminFromGroup(String tableName, String sessionId, String manageId);

    /**
     * 如果群主切换到阅后即焚模式之后，没有切换到普通模式，那么用户一直收到2591的消息，需要删除chatIndex小于maxIndex的阅后即焚消息
     *
     * @param tableName
     * @param sessionId
     * @param maxIndex
     * @return
     */
    boolean deleteEphemeralityMessageByChatIndex(String tableName, String sessionId, long maxIndex);

    /**
     * 更新会话顺序
     *
     * @param s
     * @param sessionId
     * @param isTop
     */
    void changeSessionOrder(String s, String sessionId, boolean isTop);


    /**
     * 获取阅后即焚烧模式
     *
     * @param tableName
     * @param sessionId
     * @return
     */
    int getReadModel(String tableName, String sessionId);

    /**
     * 获取最大index
     *
     * @param tableName
     * @return
     */
    long getChatIndex(String tableName);

    /**
     * 更新消息发送成功的状态
     *
     * @param tableName
     * @param messageId
     * @param chatIndex
     * @param msgTime
     * @param sendState @return
     */
    boolean updateMessageTimeAndChatIndex(String tableName, String messageId, long chatIndex, long msgTime, int sendState);


    /**
     * 设置群组开启个关闭阅后即焚烧
     *
     *
     * @param name
     * @param model
     * @return
     */
    long setGroupEphemerality(String name, String groupId, int model);

    /**
     * 保存系统消息
     *
     * @param tableName
     * @param lytMessage
     * @return
     */
    boolean saveSystemMessage(String tableName, LYTMessage lytMessage);


    /**
     * 获取本地会话列表
     *
     * @param tableName
     * @return
     */
    List<LYTSessoinList> getLocalSessionList(String tableName);

    /**
     * 获取本地会话列表
     *
     * @param sessoinName
     * @param infoName
     * @param tableName
     * @return
     */

    List<LYTSessoinList> getLocalSessionList(String sessoinName, String infoName, String tableName);


    /**
     * 查询本地的历史消息
     *
     * @param sessionId
     * @param firstChatIndex
     * @param count
     * @param toBackQuery
     * @return
     */
    List<LYTMessage> localMessage(String sessionId, long firstChatIndex, long count, boolean toBackQuery);

    /**
     * 根据阅读模式查询历史消息
     *
     * @param readModel
     * @param sessionId
     * @param firstChatIndex
     * @param count
     * @param toBackQuery
     * @return
     */
    List<LYTMessage> localMessage(int readModel, String sessionId, long firstChatIndex, long count, boolean toBackQuery);


    /**
     * 查询最后一条视频消息
     * @return
     */
    LYTMessage getLastVideoMessage(String tableName);

    //清除已过时的阅后即焚消息
    int clearDeletableSecret(String tableName);

    /**
     * @param tableName
     * @param state
     * @return
     */
    List<LYTMessage> getATMessageByATState(String tableName, int state);

    /**
     * 获取单个会话内容
     *
     * @param sessoinName
     * @param infoName
     *@param tableName
     * @param sessoinId   @return
     */
    LYTSessoinList getSimpleLocalSession(String sessoinName, String infoName, String tableName, String sessoinId);

    /**
     * 阅后即焚烧清除消息
     * @param s
     * @return
     */
    boolean clearEphemeralityMessage(String s);

    /**
     *
     * @param tableName
     * @param messageId
     * @param atSate
     * @return
     */
    boolean updateAtState(String tableName, String messageId, int atSate);

    /**
     *  更新视频消息状态
     * @param tableName
     * @param messageId
     * @param videoState
     * @return
     */
    boolean updateVideoState(String tableName, String messageId, int videoState);

    boolean updateVoiceState(String tableName, String messageId, int voiceState);

    /**
     * 根据MessageId删除一条消息
     * @param tableName
     * @param messageId
     * @return
     */
    boolean deleteMesaageByMessageId(String tableName, String messageId);

    /**
     * 重置消息未读消息数量
     * @param tableName
     * @return
     */
    boolean resetMessageCountById(String tableName);

    /**
     * 根据文件类型查询文件
     * @param tableName
     * @param fileType
     * @param content
     * @return
     */
    List<LYTMessage> retrievalFile(String tableName, int fileType, String content);



    /**
     * 更新阅后即焚时间
     * @param tableName
     * @param messageId
     * @param startTime
     * @param duration
     * @return
     */
    boolean updateReadMessageSecretTimer(String tableName, String messageId, long startTime, int duration);

    /**
     * 保存本地地址
     * @param tableName
     * @param messageId
     * @param localPath
     * @return
     */
    boolean saveFileLocalPath(String tableName, String messageId, String localPath);

    /**
     * 保存发送的消息
     * @param s
     * @param messageType
     * @param newLYTMessage
     * @return
     */
    boolean saveMessage(String s, String messageType, LYTMessage newLYTMessage);

    /**
     * genjId获取未读消息数量
     * @param sessoinId
     * @return
     */
    long getUnreadMessagesCountBySessoinId(String sessoinId);

    /**
     * 获取所有未读消息数量
     * @return
     * @param tableName
     */
    long getAllUnreadMessagesCount(String tableName);

    /**
     * 根据ID 获取讨论组详情
     * @param tableName
     * @param groupId
     * @return
     */
    LYTGroupInfo getGroupInfoByGroupId(String tableName, String groupId);


    /**
     * 加载本地群组
     * @param tableName
     * @return
     */
    List<LYTGroupInfo> LocalGroupList(String tableName);

    /**
     * 清除一个会话的本地消息
     * @param sessoinId
     * @return
     */
    boolean clearMessage(String sessoinId);

    /**
     * 改变会话在会话列表的排序
     *
     * @param tableName
     * @param sessoinId
     * @param order
     * @return
     */
    boolean changSessoinOrder(String tableName, String sessoinId, int order);

    /**
     * 改变讨论组接收消息的类型
     *
     * @param tableName
     * @param sessoinId
     * @param receiveType
     * @return
     */
    boolean changSessoinReceiveType(String tableName, String sessoinId, int receiveType);


    /**
     * 查询sessoinId 是否存在
     * @param tableName
     * @param sessoinId
     * @return
     */
    boolean querySessoin(String tableName, String sessoinId);

    /**
     * 清除会话消息的时候 需要清除会话列表的消息
     *
     * @param tableName
     * @param sessoinId
     * @return
     */
    boolean clearSessoinMessage(String tableName, String sessoinId);


    /**
     * 检索消息
     * @param tableName
     * @param content
     * @return
     */
    List<LYTMessage> retrievalMessage(String tableName, String content);

    /**
     * 获取本地讨论组成员
     * @param tableName
     * @param groupId
     * @return
     */
    List<LYTGroupMember> localGroupMembers(String tableName, String groupId);

    /**
     * 获取消息的发送状态
     * @param s
     * @param messageId
     * @return
     */
    LYTMessage getMessageState(String s, String messageId);

    /**
     * 获取所有未读消息数量
     * @param tableName
     * @return
     */
    long getNumberofAllUnreadMessages(String tableName);

    /**
     * 查询会话聊天室和讨论组ID
     * @param tableName
     * @return
     */
    List<String> getLocalGroupAndChatRoomId(String tableName);

    /**
     * 根据聊天模式获取最后一条消息
     * @return
     * @param tableName
     * @param model
     */
    LYTMessage getLastMessageByReadModel(String tableName, int model);

    /**
     * 保存系统消息 标记
     * @param s
     * @param sessionId
     * @param chatIndex
     * @return
     */
    boolean saveNoticeFlag(String tableName, String sessionId, long chatIndex);

    /**
     * 查询系统消息标记
     * @param s
     * @param sessionId
     * @param chatIndex
     * @return
     */
    boolean isNotice(String tableName, String sessionId, long chatIndex);

    /**
     * 多终端同步更新消息状态
     * @param s
     * @param sessionId
     * @param chatIndex
     * @return
     */
    boolean updateLYTMessageReadState(String tableName, String sessionId, long chatIndex);



    /**
     * 更新视频消息状态
     * @param sessoinId
     * @param roomId
     * @param roomStatus
     * @return
     */
    boolean updateVideoStateByVideoId(String sessoinId, String roomId, int roomStatus);

    /**
     *
     * @param
     * @param tableName
     * @param messageId
     * @return
     */
    boolean deleteSessionMessageByMessageId(String tableName, String sessoinId, String messageId);



    boolean updateSessoinMessageState(String tableName, String sessoinId, String messageId, long chatIndex, long msgTime, int sendState);

    /**
     * 根据阅读模式来清除未读消息数
     * @param tableName
     * @param readModel
     * @return
     */

    boolean  resetMessageCountById(String tableName, int readModel);
}



