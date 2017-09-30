package com.qisn.www.socialsdk.internal.http;


import com.qisn.www.socialsdk.Const;
import com.qisn.www.socialsdk.URLConfig;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * Created by Administrator on 2016/12/27.
 */

public interface APIService {


    /**
     * @param companyCode 公司代码
     * @param appkey      在创建app时生成的appkey
     * @param appSecret   在创建app时生成的appSecret
     * @param id          用户ID 或者 服务器ID
     * @param sdkType     类型 1:PC，2：WEB，3：ANDROID，4：IOS，5：JAVA SERVER
     * @return LYTTokenInfo
     */
    @Headers ("Content-Type: application/x-www-form-urlencoded;charset=utf-8")
    @GET ("{companyCode}/{appKey}/oauth/token")
    Call<String> getToken(
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.APPSECRET) String appSecret,
            @Query (Const.UserInfo.ID) String id,
            @Query (Const.UserInfo.SDKTYPE) int sdkType);

    /**
     * @param headers token
     * @param ways    0:表示运行多端登录，1：允许一端登录
     * @return LYTMqttInfo
     */
    @GET("core/{companyCode}/{appKey}/config/mqtts")
    Call<String> mqttConfig(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.WAYS) int ways);


    /**
     * 查询用户所在聊天室，返回聊天室列表
     *
     * @param headers
     * @param userId  用户ID
     * @return
     */
    @GET("core/{companyCode}/{appKey}/room/findRooms")
    Call<String> findUserToRooms(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId);


    /**
     * 查询单个聊天室详细信息
     *
     * @param headers
     * @param roomId  聊天室ID
     * @param ope     1表示带上群成员列表，0表示不带群成员列表，只返回群信息(默认不带群成员列表)
     * @return
     */

    @GET("core/{companyCode}/{appKey}/room/get")
    Call<String> getSimpleRoomInfo(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,

            @Query (Const.UserInfo.ROOMID) String roomId,
            @Query (Const.UserInfo.OPE) int ope);


    /**
     * 查询聊天室所有成员基本信息
     *
     * @param headers
     * @param roomId  聊天室ID
     * @return
     */
    @GET("core/{companyCode}/{appKey}/room/findMembers")
    Call<String> getAllMembersInfoToRoom(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.ROOMID) String roomId);

    /**
     * 查询聊天室单个成员详细信息
     *
     * @param headers
     * @param userId  用户ID
     * @param roomId  聊天室ID
     * @return
     */

    @GET(URLConfig.GET.GET_ROOM_GETMEMBER)
    Call<String> getMemberInfoToRoom(
            @HeaderMap Map<String, String> headers,
            @Query (Const.UserInfo.USERID) String userId,
            @Query (Const.UserInfo.ROOMID) String roomId);


    /**
     * 查询app下的所有聊天室
     *
     * @param headers
     * @return
     */
    @GET("core/{companyCode}/{appKey}/room/findAllRooms")
    Call<String> getAllRoom(
            @HeaderMap Map<String, String> headers,

            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey);


    /**
     * 删除聊天室
     *
     * @param headers
     * @param roomId    聊天室ID
     * @param operateId 操作者ID，必须是创建者才可以操作
     * @return
     */

    @GET("core/{companyCode}/{appKey}/room/delete")
    Call<String> deleteToRoom(
            @HeaderMap Map<String, String> headers,

            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.ROOMID) String roomId,
            @Query (Const.UserInfo.OPERATEID) String operateId);


/*************************************************分割线以下为POST 请求*********************************************************************/


    /**
     * 创建聊天室
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @POST ("core/{companyCode}/{appKey}/room/add")
    Call<String> createRoom(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 成员退出聊天室
     */
    @POST("core/{companyCode}/{appKey}/room/exitRoom")
    Call<String> exitChatRoom(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 添加聊天室成员
     */
    @POST("core/{companyCode}/{appKey}/room/addMembers")
    Call<String> addMembersToRoom(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);

    /**
     * 改变成员属性
     *
     * @return
     */
    @POST("core/{companyCode}/{appKey}/room/updateMember")
    Call<String> updateMemberToRoom(
            @HeaderMap Map<String, String> headers,

            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 删除聊天室成员
     */
    @POST("core/{companyCode}/{appKey}/room/deleteMembers")
    Call<String> deleteMembersToRoom(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,

            @Body RequestBody body);


    /**
     * 修改聊天室信息
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @POST("core/{companyCode}/{appKey}/room/update")
    Call<String> updateRoom(@HeaderMap Map<String, String> headers,
                            @Path ("companyCode") String companyCode,
                            @Path ("appKey") String appkey,
                            @Body RequestBody body);


    /**
     * 邀请加入聊天室
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @POST("core/{companyCode}/{appKey}/room/invite")
    Call<String> joinChatRoom(@HeaderMap Map<String, String> headers,
                              @Path ("companyCode") String companyCode,
                              @Path ("appKey") String appkey,
                              @Body RequestBody body);


    /**
     * 处理加入聊天室
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */
    @POST("core/{companyCode}/{appKey}/room/inviteHandle")
    Call<String> joinHandleChatRoom(@HeaderMap Map<String, String> headers,
                                    @Path ("companyCode") String companyCode,
                                    @Path ("appKey") String appkey,
                                    @Body RequestBody body);


    /**
     * 查询离线消息接口 调用此接口 MQTT 会返回离线消息
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */
    @POST("core/{companyCode}/{appKey}/queryOfflineMsg")
    Call<String> queryOfflineMsg(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 查询离线消息
     *
     * @param url
     * @param headers
     * @param body
     * @return
     */
    @POST
    Call<String> queryOfflineMsg(
            @Url String url,
            @HeaderMap Map<String, String> headers,
            @Body RequestBody body);

//
//    /**
//     * 上传图片
//     *
//     * @param url
//     * @param bodyMap
//     * @return
//     */
//
//    @Multipart
//    @POST
//    Call<Upload> uploadFile(@Url String url, @PartMap Map<String, RequestBody> bodyMap);
//

    /**
     * 设置黑名单
     *
     * @param headers
     * @param body
     * @return
     */
//    @POST("core/{companyCode}/{appkey}/user/addBlacklist")
    @POST("core/13322/13322001/user/addBlacklist")
    Call<String> strange(
            @HeaderMap Map<String, String> headers,
//            @Path("companyCode") String companyCode,
//            @Path("appkey") String appkey,
            @Body RequestBody body);


//    @POST("core/{companyCode}/{appkey}/user/addBlacklist")


    @POST("core/{companyCode}/{appKey}/user/addBlacklist")
    Call<String> strange(@HeaderMap Map<String, String> headers,
                         @Path ("companyCode") String companyCode,
                         @Path ("appKey") String appkey,
                         @Body RequestBody body);


    /**
     * 查询用户所在聊天室，返回聊天室列表以及用户个性化设置
     *
     * @param headers
     * @param userId  用户ID
     * @return
     */
    @GET("core/{companyCode}/{appKey}/room/findIndividRooms")
    Call<String> findIndividRooms(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId);


    /*查询黑名单列表
     */
    @GET("core/{companyCode}/{appKey}/user/findBlacklists")
    Call<String> getStrange(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId);


    /**
     * 设置聊天室的接收消息类型
     *
     * @param headers
     * @param appcp
     * @param appkey
     * @param body
     * @return
     */

    @POST("core/{companyCode}/{appKey}/room/setReceiveType")
    Call<String> receiveType(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String appcp,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 移除黑名单
     *
     * @param headers
     * @param appkey
     */

    @POST("core/{companyCode}/{appKey}/user/delBlacklist")
    Call<String> delBlacklist(@HeaderMap Map<String, String> headers,
                              @Path ("companyCode") String companyCode,
                              @Path ("appKey") String appkey,
                              @Body RequestBody body);


    /**
     * 上传推送
     *
     * @param headers
     * @param appcp
     * @param appkey
     * @param body
     * @return
     */
    @POST("core/{companyCode}/{appKey}/updatePushDeviceToken")
    Call<String> updatePush(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String appcp,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    @POST("core/{companyCode}/{appKey}/changeAppRunStatus")
    Call<String> changeAppRunStatus(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    @POST("core/{companyCode}/{appKey}/user/setReceiveType")
    Call<String> userReceiveType(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /*************群组接口*************************************************************/


    /**
     * 获取用户的讨论组列表
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param userId
     * @return
     */
    @GET("core/{companyCode}/{appKey}/groupList")
    Call<String> groupList(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId);


    /**
     * 获取讨论组成员信息
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param userId
     * @return
     */
    @GET("core/{companyCode}/{appKey}/groupMembers")
    Call<String> groupMembers(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId,
            @Query ("groupId") String groupId);


    /**
     * 退出讨论组
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @POST("core/{companyCode}/{appKey}/groupExit")
    Call<String> groupExit(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 群主转让
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */
    @PUT ("core/{companyCode}/{appKey}/groupOwner")
    Call<String> groupOwner(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 创建讨论组
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */
    @POST("core/{companyCode}/{appKey}/group")
    Call<String> createGroup(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);

    /**
     * 更新讨论组
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @PUT("core/{companyCode}/{appKey}/group")
    Call<String> updateGroup(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 修改用户在讨论组的设置
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @PUT("core/{companyCode}/{appKey}/groupConfig")
    Call<String> modifyGroupSet(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 解散讨论组
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param groupId
     * @param userId
     * @return
     */
    @DELETE ("core/{companyCode}/{appKey}/group/{groupId}/user/{userId}")
    Call<String> dissolveDiscussionGroup(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Path ("groupId") String groupId,
            @Path ("userId") String userId);


    /**
     * 获取群的所有公告
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param userId
     * @param targetId
     * @return
     */
    @GET("core/{companyCode}/{appKey}/notifications")
    Call<String> getGroupNotifications(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId,
            @Query ("targetId") String targetId);


    /**
     * 通过ID获取群公告
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @return
     */
    @GET("core/{companyCode}/{appKey}/notifications/{notificationId}")
    Call<String> getNotificationFromNotificationId(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Path ("notificationId") String notificationId);


//    @GET("core/{companyCode}/{appkey}/systemCurrentTime")

    @GET("core/{companyCode}/{appKey}/systemCurrentTime")
    Call<String> getSystemCurrentTime(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey);


//    @GET("core/{companyCode}/{appKey}/systemCurrentTime")
//    Call<String> getSystemCurrentTime(
//            @HeaderMap Map<String, String> headers,
//            @Path("companyCode") String companyCode,
//            @Path("appkey") String appkey);


    /**
     * 添加群公告（群主才有权限）
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */
    @POST("core/{companyCode}/{appKey}/notifications")
    Call<String> addnotifications(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 授权管理员
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */
    @POST("core/{companyCode}/{appKey}/groupManager")
    Call<String> groupManager(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);



    /**
     * 用户修改公告状态为已读
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param body
     * @return
     */

    @PUT("core/{companyCode}/{appKey}/notifications")
    Call<String> modifyNotificationState(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 删除群公告(群主才有权限)
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param notificationId
     * @return
     */
    @DELETE("core/{companyCode}/{appKey}/notifications/{userId}/{notificationId}")
    Call<String> deleteNotification(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Path ("userId") String userId,
            @Path ("notificationId") String notificationId);








    /**
     * 消息漫游
     *
     * @param headers
     * @param companyCode
     * @param appkey
     * @param userId
     * @param sessionId
     * @param startChatIndex
     * @param endChatIndex
     * @return
     */
    @GET("core/{companyCode}/{appKey}/roamMessage")
    Call<String> getRoamMessage(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Query (Const.UserInfo.USERID) String userId,
            @Query ("sessionId") String sessionId,
            @Query ("startChatIndex") String startChatIndex,
            @Query ("endChatIndex") String endChatIndex);


    @POST("core/{companyCode}/{appKey}/updatePushDeviceToken")
    Call<String> updatePushDeviceToken(
            @HeaderMap Map<String, String> headers,
            @Path ("companyCode") String companyCode,
            @Path ("appKey") String appkey,
            @Body RequestBody body);


    /**
     * 上传图片
     *
     * @param url
     * @param bodyMap
     * @return
     */

    @Multipart
    @POST
    Call<String> uploadFile(@Url String url, @PartMap Map<String, RequestBody> bodyMap);

    @GET()
    Call<String> validateFileMD5(
            @HeaderMap Map<String, String> headers, @Url String url, @Query ("fileMD5") String fileMD5);


    /**
     * 验证MD5
     * L
     *
     */
    @FormUrlEncoded
//    @POST("http://192.168.10.227:8088/hhlyupload/get/md5/files.do")

//    @POST("http://ftp.71chat.com/hhlyupload/get/md5/files.do")
//    Call<ValidateFileMD5DateBean> validateFileMD5(@Field("key") String key,
//                                                  @Field("requestTime") Long requestTime,
//                                                  @Field("fileMD5") String fileMD5,
//                                                  @Field("fileName") String fileName);
//

    /**
     * 下载文件
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadByStreaming(@Url String fileUrl);


//    @GET("core/{companyCode}/{appkey}/systemCurrentTime")
//    Call<String> getSystemCurrentTime(
//            @HeaderMap Map<String, String> headers,
//            @Path("companyCode") String companyCode,
//            @Path("appKey") String appkey);



}

