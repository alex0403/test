package com.qisn.www.socialsdk.internal.http;

import com.qisn.www.socialsdk.base.BaseBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by hhly-pc on 2017/9/27.
 */

public interface IDynamicProxyRquest {

    Call<String> getToken (String id, int sdkType);

    Call<String> mqttConfig (int ways);


    Call<String> findUserToRooms ();


    Call<String> getRoomInfoToRoom (Map<String, String> headers, int roomId, int ope);


    Call<String> getMemberInfoToRoom (String userId, String roomId);


    Call<String> getAllRoom (Map<String, String> headers, String companyCode, String appkey);


    <T> Call<String> createRoom (T body);


    Call<String> deleteToRoom (String roomId);


    Call<String> addMembersToRoom (BaseBean body);

    Call<String> exitChatRoom (BaseBean body);


    Call<String> updateMemberToRoom (BaseBean body);


    Call<String> deleteMembersToRoom (BaseBean body);


    Call<String> getAllMembersInfoToRoom (String roomId);

    Call<String> getSimpleRoomInfo (String roomId, int open);


    Call<String> updateRoom (BaseBean body);


    Call<String> joinChatRoom (BaseBean body);


    Call<String> joinHandleChatRoom (BaseBean body);


    //查询离线消息

    Call<String> queryOfflineMsg (Map<String, String> map, String companyCode, String appkey, RequestBody body);


    Call<String> queryOfflineMsg (String url, Map<String, String> map, RequestBody body);


    Call<String> strange (BaseBean body);


    Call<String> getStrange ();


    Call<String> findIndividRooms ();

    Call<String> setReceiveType (BaseBean body);


    Call<String> delBlacklist (BaseBean body);

    Call<String> updatePush (Map<String, String> headers, String appcp, String appkey, RequestBody body);

    Call<String> changeAppRunStatus (BaseBean body);


    Call<String> setUserReceiveType (BaseBean body);


    /**************群组**************/


    Call<String> groupList ();


    Call<String> groupMembers (String groupId);


    Call<String> groupExit (BaseBean body);


    Call<String> groupOwner (BaseBean body);


    Call<String> createGroup (BaseBean body);


    Call<String> updateGroup (BaseBean body);


    Call<String> modifyGroupSet (BaseBean body);

    Call<String> dissolveDiscussionGroup (String groupId);

    Call<String> getGroupNotifications (String targetId);

    Call<String> getNotificationFromNotificationId (String groupId);

    Call<String> addnotifications (BaseBean body);

    Call<String> modifyNotificationState (BaseBean body);


    Call<String> deleteNotification (String notificationId);

    Call<String> getRoamMessage (String sessionId, String startChatIndex, String endChatIndex);

    Call<String> grantGroupManager (BaseBean BaseBean);


    Call<String> uploadFile (String uploadUrl, Map<String, RequestBody> bodyMap);

//    Call<ValidateFileMD5DateBean> validateFileMD5(String key, Long requestTime, String fileMD5, String fileName);


    Call<String> validateFileMD5 (String url, String fileMD5);

    Call<String> updatePushDeviceToken (BaseBean BaseBean);

    Call<String> getSystemCurrentTime ();

    Call<ResponseBody> downloadByStreaming (String url);
}
