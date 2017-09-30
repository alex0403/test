package com.qisn.www.socialsdk.internal.bean;

import com.qisn.www.socialsdk.base.BaseBean;

import java.util.List;

/**
 * Created by dell on 2017/8/23.
 */

public class LYTMGroup extends BaseBean {

    public int chatType;
    public String createUserId;//创建者ID
    public String createTime;//创建时间
    public String updateTime;//最近更新时间
    public String groupLocalPicture;//本地图片
    public int memberCount;// 人数
    public String groupOwnerId;//管理者ID
    public List<String> managerIds;
    public List<LYTGroupMember> members;


    public  long maxIndex;

    public String userName;
    public String groupOwnerName;
    public String operateId;
    public String roomId;
    public String desc;
    public String remark;
    public String attr1;
    public String attr2;
    public String attr3;
    public List<String> delUserName;
    public String manageId;
    public int roleLevel;
    public String groupName;
    public String localIocn;
    public String groupId;
    public String groupPicture;
    public String oldOwnerId;
    public String newOwnerId;
    public List<String> userIds;
    public String state;
    public List<String> userNames;
    public List<String> deleteUserIds;
    public List<String> deleteUserNames;
    public List<String> delUserNames;
    public String noticeId;
    public List<LYTNotificationAttach> lytNotificationAttaches;
    public String userId;
    public String title;
    public String content;
    public String attach;
    public String targetId;
    public String notificationId;







}
