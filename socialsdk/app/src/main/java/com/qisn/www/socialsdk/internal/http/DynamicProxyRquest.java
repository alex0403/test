package com.qisn.www.socialsdk.internal.http;

import com.google.gson.Gson;
import com.qisn.www.socialsdk.base.BaseBean;
import com.qisn.www.socialsdk.config.HttpConfig;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 *
 */
public class DynamicProxyRquest implements IDynamicProxyRquest{

    private APIService mAPIService;

    private HttpConfig mHttpConfig;

    public DynamicProxyRquest(APIService _APIService, HttpConfig _HttpConfig){
        this.mAPIService = _APIService;
        this.mHttpConfig = _HttpConfig;
    }

    private String getAppkey() {
        return mHttpConfig.appkey;
    }

    private String getAppSecret() {
        return mHttpConfig.appsecret;
    }

    private String getCompanyCode() {
        return mHttpConfig.companyCode;
    }

    private String getToken() {
        return mHttpConfig.token;
    }

    private String getUserId() {
        return mHttpConfig.userId;
    }

    private Map<String, String> getMap() {

        Map<String, String> map = new HashMap<>();
        map.put("authorization", getToken());
        map.put("Content-Type", "application/json");
        map.put("transId", "1");
        map.put("sourceId", 3 + getAppkey());
        map.put("sourceInsId", "3");
        return map;
    }


    private Map<String, String> getPostMap() {

        Map<String, String> map = new HashMap<>();
        map.put("authorization", getToken());
        map.put("Content-Type", "application/json");
        map.put("transId", "1");
        map.put("sourceId", 3 + getAppkey());
        map.put("sourceInsId", "3");
        return map;
    }


    @Override
    public Call<String> getToken(String id, int sdkType) {
        return mAPIService.getToken(getCompanyCode(), getAppkey(), getAppSecret(), id, sdkType);

//        return mAPIService.getToken("C10086", "1332210086", "191e646e6e6746f0b3b093ed32dc7942", id, sdkType);
    }

    @Override
    public Call<String> mqttConfig(int ways) {
        return mAPIService.mqttConfig(getMap(), getCompanyCode(), getAppkey(), ways);
    }


    @Override
    public <T> Call<String> createRoom(T body) {
        return mAPIService.createRoom(getPostMap(), getCompanyCode(), getAppkey(), getRequestBody(body));
    }


    @Override
    public Call<String> deleteToRoom(String roomId) {
        return mAPIService.deleteToRoom(getMap(), getCompanyCode(), getAppkey(), roomId, getUserId());
    }

    @Override
    public Call<String> addMembersToRoom(BaseBean body) {
        return mAPIService.addMembersToRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> exitChatRoom(BaseBean body) {
        return mAPIService.exitChatRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> updateMemberToRoom(BaseBean body) {
        return mAPIService.updateMemberToRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }


    @Override
    public Call<String> deleteMembersToRoom(BaseBean body) {
        return mAPIService.deleteMembersToRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> getAllMembersInfoToRoom(String roomId) {
        return mAPIService.getAllMembersInfoToRoom(getMap(), getCompanyCode(), getAppkey(), roomId);
    }

    @Override
    public Call<String> getSimpleRoomInfo(String roomId, int open) {
        return mAPIService.getSimpleRoomInfo(getMap(), getCompanyCode(), getAppkey(), roomId, open);
    }

    @Override
    public Call<String> updateRoom(BaseBean body) {
        return mAPIService.updateRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> joinChatRoom(BaseBean body) {
        return mAPIService.joinChatRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> joinHandleChatRoom(BaseBean body) {
        return mAPIService.joinHandleChatRoom(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> queryOfflineMsg(Map<String, String> map, String companyCode, String appkey, RequestBody body) {
        return mAPIService.queryOfflineMsg(map, companyCode, appkey, body);
    }

    @Override
    public Call<String> queryOfflineMsg(String url, Map<String, String> map, RequestBody body) {
        return mAPIService.queryOfflineMsg(url, map, body);
    }

    @Override
    public Call<String> strange(BaseBean body) {

        return mAPIService.strange(getPostMap(), getCompanyCode(), getAppkey(), getRequestBody(body));
    }

    @Override
    public Call<String> getStrange() {
        return mAPIService.getStrange(getMap(), getCompanyCode(), getAppkey(), getUserId());
    }

    @Override
    public Call<String> findIndividRooms() {
        return mAPIService.findIndividRooms(getMap(), getCompanyCode(), getAppkey(), getUserId());
    }

    @Override
    public Call<String> setReceiveType(BaseBean body) {
        return mAPIService.receiveType(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> delBlacklist(BaseBean body) {
        return mAPIService.delBlacklist(getMap(), getCompanyCode(), getAppkey(), getRequestBody(body));
    }

    @Override
    public Call<String> updatePush(Map<String, String> headers, String appcp, String appkey, RequestBody body) {
        return mAPIService.updatePush(headers, appcp, appkey, body);
    }


    @Override
    public Call<String> changeAppRunStatus(BaseBean body) {
        return mAPIService.changeAppRunStatus(getPostMap(), getCompanyCode(), getAppkey(), getRequestBody(body));
    }

    @Override
    public Call<String> setUserReceiveType(BaseBean body) {
        return mAPIService.userReceiveType(getPostMap(), getCompanyCode(), getAppkey(), getRequestBody(body));
    }

    @Override
    public Call<String> groupList() {
        return mAPIService.groupList(getMap(), getCompanyCode(), getAppkey(), getUserId());
    }

    @Override
    public Call<String> groupMembers(String groupId) {
        return mAPIService.groupMembers(getMap(), getCompanyCode(), getAppkey(), getUserId(), groupId);
    }

    @Override
    public Call<String> groupExit(BaseBean body) {
        return mAPIService.groupExit(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> groupOwner(BaseBean body) {
        return mAPIService.groupOwner(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> createGroup(BaseBean body) {
        return mAPIService.createGroup(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> updateGroup(BaseBean body) {
        return mAPIService.updateGroup(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> modifyGroupSet(BaseBean body) {
        return mAPIService.modifyGroupSet(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> dissolveDiscussionGroup(String groupId) {
        return mAPIService.dissolveDiscussionGroup(getMap(), getCompanyCode(), getAppkey(), groupId, getUserId());
    }

    @Override
    public Call<String> getGroupNotifications(String groupId) {
        return mAPIService.getGroupNotifications(getMap(), getCompanyCode(), getAppkey(), getUserId(), groupId);
    }

    @Override
    public Call<String> getNotificationFromNotificationId(String notificationId) {
        return mAPIService.getNotificationFromNotificationId(getMap(), getCompanyCode(), getAppkey(), notificationId);
    }

    @Override
    public Call<String> addnotifications(BaseBean body) {
        return mAPIService.addnotifications(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> modifyNotificationState(BaseBean body) {
        return mAPIService.modifyNotificationState(getPostMap(), getCompanyCode(), getAppkey(), getBody(body));
    }

    @Override
    public Call<String> deleteNotification(String notificationId) {
        return mAPIService.deleteNotification(getMap(), getCompanyCode(), getAppkey(), getUserId(), notificationId);
    }

    @Override
    public Call<String> getRoamMessage(String sessionId, String startChatIndex, String endChatIndex) {
        return mAPIService.getRoamMessage(getMap(), getCompanyCode(), getAppkey(), getUserId(), sessionId, startChatIndex, endChatIndex);
    }

    @Override
    public Call<String> grantGroupManager(BaseBean BaseBean) {
        return mAPIService.groupManager(getPostMap(), getCompanyCode(), getAppkey(), getBody(BaseBean));
    }


    @Override
    public Call<String> findUserToRooms() {
        return mAPIService.findUserToRooms(getMap(), getCompanyCode(), getAppkey(), getUserId());
    }

    @Override
    public Call<String> getRoomInfoToRoom(Map<String, String> headers, int roomId, int ope) {
        return null;
    }


    @Override
    public Call<String> getMemberInfoToRoom(String userId, String roomId) {
        return mAPIService.getMemberInfoToRoom(getMap(), userId, roomId);
    }

    @Override
    public Call<String> getAllRoom(Map<String, String> headers, String companyCode, String appkey) {
        return mAPIService.getAllRoom(headers, companyCode, appkey);
    }


    private <T> RequestBody getRequestBody(T object) {
        return getBody(object);
    }

    private static <T> RequestBody getBody(T object) {
        Gson gson = new Gson();
        String route = gson.toJson(object);//通过Gson将Bean转化为Json字符串形式

//        Log.e("route",">>>"+route.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);

//        Log.e("body",">>>"+body.toString());
        return body;
    }

    public Call<String> uploadFile(String uploadUrl, Map<String, RequestBody> bodyMap) {
        return mAPIService.uploadFile(uploadUrl, bodyMap);
    }

//    @Override
//    public Call<ValidateFileMD5DateBean> validateFileMD5(String key, Long requestTime, String fileMD5, String fileName) {
//        return mAPIService.validateFileMD5(key,requestTime, fileMD5,fileName);
//    }

    @Override
    public Call<String> validateFileMD5(String url, String fileMD5) {
        return mAPIService.validateFileMD5(getMap(), url, fileMD5);
    }

    @Override
    public Call<String> updatePushDeviceToken(BaseBean BaseBean) {
        return mAPIService.updatePushDeviceToken(getPostMap(), getCompanyCode(), getAppkey(), getBody(BaseBean));
    }

    @Override
    public Call<String> getSystemCurrentTime() {
        return mAPIService.getSystemCurrentTime(getMap(), getCompanyCode(), getAppkey());
    }


    @Override
    public Call<ResponseBody> downloadByStreaming(String url) {
        return mAPIService.downloadByStreaming(url);
    }
}
