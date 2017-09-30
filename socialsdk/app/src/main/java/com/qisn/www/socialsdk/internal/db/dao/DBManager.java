package com.qisn.www.socialsdk.internal.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.qisn.www.socialsdk.internal.bean.LYTGroupInfo;
import com.qisn.www.socialsdk.internal.bean.LYTGroupMember;
import com.qisn.www.socialsdk.internal.bean.LYTMGroup;
import com.qisn.www.socialsdk.internal.bean.LYTSessoinList;
import com.qisn.www.socialsdk.internal.bean.msg.LYTMessage;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZFileMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZMessage;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZNormalFileMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZNotificationBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZTextMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZVideoMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZVoiceMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;
import com.qisn.www.socialsdk.internal.db.BaseDbOpenHelper;
import com.qisn.www.socialsdk.internal.db.IDBManager;
import com.qisn.www.socialsdk.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.qisn.www.socialsdk.internal.bean.msg.LYTMessage.byteToObject;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.CHAT_TPYPE;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.COMMA_SEP;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.CONVERSATION_ID;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.INTEGER_TYPE;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.ISTOP;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.TEXT_TYPE;
import static com.qisn.www.socialsdk.internal.db.dao.DbConfig.TO;

/**
 * Created by hhly-pc on 2017/9/27.
 */

public class DBManager implements IDBManager {
    private BaseDbOpenHelper dbHelper;


    public DBManager(BaseDbOpenHelper baseDbOpenHelper) {
        this.dbHelper = baseDbOpenHelper;
    }

    public void setDbHelper(BaseDbOpenHelper baseDbOpenHelper) {
        this.dbHelper = baseDbOpenHelper;
    }

    public static final String ETINTERMEDIATEMEDIUM = "ETINTERMEDIATEMEDIUM";


    protected static String getIntermediateMedium(String userId) {

        String GROUP_TABLE_CREATE = "CREATE TABLE if not exists "
                + ETINTERMEDIATEMEDIUM + userId + " (" +
                LYTGroupDao.GROUP_ID + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_ID + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_POSITION + TEXT_TYPE + COMMA_SEP +
                LYTGroupDao.USER_ROLELEVEL + INTEGER_TYPE + COMMA_SEP +
                " primary key  ( groupId, userId ) " + " );";

        return GROUP_TABLE_CREATE;
    }


    public boolean createTable(String sql) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL(sql);
        }

        return true;

    }


    @Override
    public void initTable(String sql) {
        //会话列表
//        LYTDBManager.getInstance().createTable(LYTConversationDao.getConversationSql(sql));

        //阅后即焚发送回执失败的消息
        createTable(LYTMessageDao.getRSql(sql));

        //新的详情表,包括个人,群组 和聊天室
        createTable(LYTConversationDao.getInfoSql(sql));

        //新的会话话列表
        createTable(LYTConversationDao.getNewConversationSql(sql));


        /**
         * 再次改变会话列表
         */
        createTable(LYTMessageDao.getSessionSql(sql));

        //黑名单列表
        createTable(LYTConversationDao.getBlacklistSql(sql));

        //聊天室列表
        createTable(LYTChatRoomDao.getChatRoomList(sql));

        //聊天室联系人
        createTable(LYTChatRoomDao.getChatRoomMembers(sql));

        //聊天室信息表
        createTable(LYTChatRoomDao.getChatRoomInfo(sql));

        //讨论组
        createTable(LYTGroupDao.getGroupList(sql));

        //讨论组信息表
        createTable(LYTGroupDao.getGroupInfo(sql));

        //创建讨论组通知表
        createTable(LYTNoticeDao.getGroupNotice(sql));

        //创建系统基本通知保存
        createTable(LYTNoticeDao.getNotice(sql));

        //单人的信息表
        createTable(LYTSimpleDao.getUserInfo(sql));

        //创建中间表
        createTable(getIntermediateMedium(sql));
    }

    @Override
    public boolean saveGroupList(String tableName, LYTMGroup info) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTGroupDao.GROUP_ID, info.groupId);
            values.put(LYTGroupDao.GROUP_NAME, info.groupName);
            values.put(LYTGroupDao.GROUP_OWNER_ID, info.groupOwnerId);
            values.put(LYTGroupDao.GROUP_REMOTE_PIC, info.groupPicture);
            if (!TextUtils.isEmpty(info.groupLocalPicture)) {
                values.put(LYTGroupDao.GROUP_LOCAL_PIC, info.groupLocalPicture);
            }

            values.put(LYTGroupDao.GROUP_MRMBER_COUNT, info.memberCount);
            values.put(LYTGroupDao.CREATE_TIME, info.createTime);
            values.put(LYTGroupDao.UPDATE_TIME, info.updateTime);
            values.put(LYTGroupDao.CREATE_USER_ID, info.createUserId);
            values.put(LYTGroupDao.MANAGER_IDS, this.ObjectToByte(info.managerIds));
            long size = db.insert(tableName, null, values);
            if (size > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteGroupByGroupId(String tableName, String groupId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTGroupDao.GROUP_ID + " =  ? ";
            int da = db.delete(tableName, whereClause, new String[]{groupId});
        }
        return true;
    }

    @Override
    public boolean deleteSessionById(String tableName, String id) {
        int index = 0;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.CONVERSATIONID + " =  ? ";
            index = db.delete(tableName, whereClause, new String[]{id});
        }

        if (index > 0) {
            return true;
        }


        return false;
    }

    @Override
    public boolean saveMemberToGroup(String tableName, LYTGroupMember member) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            if (!TextUtils.isEmpty(member.getUserName())) {
                values.put(LYTGroupDao.USER_NAME, member.getUserName());
            }

            if (!TextUtils.isEmpty(member.getPicture())) {
                values.put(LYTGroupDao.USER_PIC, member.getPicture());
            }

            if (!TextUtils.isEmpty(member.getPosition())) {
                values.put(LYTGroupDao.USER_POSITION, member.getPosition());
            }


            values.put(LYTGroupDao.USER_ROLELEVEL, member.getRoleLevel());


            if (!TextUtils.isEmpty(member.getUserNum())) {
                values.put(LYTGroupDao.USER_NUM, member.getUserNum());
            }

            if (!TextUtils.isEmpty(member.getUserId())) {
                values.put(LYTGroupDao.USER_ID, member.getUserId());
                db.insert(tableName, null, values);
            }
        }

        return true;
    }


    @Override
    public void saveInm(String tableName, String userId, String groupId, String position, int roleLevel) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTGroupDao.GROUP_ID, groupId);
            values.put(LYTGroupDao.USER_ID, userId);
            values.put(LYTGroupDao.USER_POSITION, position);
            values.put(LYTGroupDao.USER_ROLELEVEL, roleLevel);
            db.replace(tableName, null, values);//TODO
        }
    }

    @Override
    public boolean updateGroupOwner(String tableName, String groupId, String newOwnerId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTGroupDao.GROUP_OWNER_ID, newOwnerId);
            String whereClause = LYTGroupDao.GROUP_ID + "= ?";
            int dd = db.update(tableName, values, whereClause, new String[]{groupId});
            if (dd > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateGroupInfo(String tableName, LYTMGroup LYTMGroup) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            if (!TextUtils.isEmpty(LYTMGroup.groupName)) {
                values.put(LYTGroupDao.GROUP_NAME, LYTMGroup.groupName);
            }
            if (!TextUtils.isEmpty(LYTMGroup.groupPicture)) {
                values.put(LYTGroupDao.GROUP_REMOTE_PIC, LYTMGroup.groupPicture);
            }
            String whereClause = LYTGroupDao.GROUP_ID + "= ?";
            int index = db.update(tableName, values, whereClause, new String[]{LYTMGroup.groupId});
            if (index > 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean modifyGroupSet(String tableName, LYTMGroup LYTMGroup) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            String whereClause = LYTGroupDao.GROUP_ID + "= ?";
            values.put(LYTGroupDao.IS_DISTURB, LYTMGroup.state);
            int index = db.update(tableName, values, whereClause, new String[]{LYTMGroup.groupId});
            if (index > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteInm(String tableName, String groupId, String userId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTGroupDao.GROUP_ID + "= ? and " + LYTGroupDao.USER_ID + " = ?";
            db.delete(tableName, whereClause, new String[]{groupId, userId});
        }
    }

    @Override
    public boolean saveNotice(String tableName, String groupId, LYTZNotificationBody body) {//保存通知栏
        //保存通
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTNoticeDao.NOTICE_TARGETID, groupId);
            values.put(LYTNoticeDao.NOTICE_TITLE, body.getTitle());
            values.put(LYTNoticeDao.NOTICE_NOTIFICATIONID, body.getNotificationId());
            values.put(LYTNoticeDao.NOTICE_READ_STATE, body.getReadState());
            values.put(LYTNoticeDao.NOTICE_UPDATEBY, body.getUpdateBy());
            values.put(LYTNoticeDao.NOTICE_UPDATETIME, body.getUpdateTime());
            values.put(LYTNoticeDao.NOTICE_ATTACH, body.getAttach());
            values.put(LYTNoticeDao.NOTICE_CONTENT, body.getContent());
            values.put(LYTNoticeDao.NOTICE_CREATEBY, body.getCreateBy());
            values.put(LYTNoticeDao.NOTICE_CREATETIME, body.getCreateTime());
            values.put(LYTNoticeDao.NOTICE_HASATTACH, body.getHasAttach());
            index = db.replace(tableName, null, values);
        }


        return index > 0;
    }

    @Override
    public boolean updateNoticeReadState(String tableName, String groupId, String notificationId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(LYTNoticeDao.NOTICE_READ_STATE, 1);
            String whereClause = LYTNoticeDao.NOTICE_TARGETID + "= ? and " + LYTNoticeDao.NOTICE_NOTIFICATIONID + " = ?";
            int index = db.update(tableName, contentValues, whereClause, new String[]{groupId, notificationId});
            if (index > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteNoticeByNoticeId(String tableName, String groupId, String notificationId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTNoticeDao.NOTICE_TARGETID + "= ? and " + LYTNoticeDao.NOTICE_NOTIFICATIONID + " = ?";
            int index = db.delete(tableName, whereClause, new String[]{groupId, notificationId});
            if (index > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public synchronized boolean saveMessage(String tableName, LYTMessage lytMessage) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.MSG_ID + " = ?";
        if (db.isOpen()) {
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{lytMessage.getMsgId()}, null, null, null);
            if (cursor.getCount() == 0) {
                cursor.close();
                ContentValues values = new ContentValues();
                LYTZMessage lytObject = lytMessage.getLytObject();
                getContentValues(values, lytObject);
                long da = db.replace(tableName, null, values);
                if (da > 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    private void getContentValues(ContentValues values, LYTZMessage lytObject) {

        String messageType = lytObject.getLytzMessageBody().getMessageType();
        values.put(LYTMessageDao.MESSAGER_TYPE, messageType);
        values.put(LYTMessageDao.CHAT_TYPE, Integer.valueOf(lytObject.getChatType()));
        values.put(LYTMessageDao.USERNAME, lytObject.getName());
        values.put(LYTMessageDao.USERICON, lytObject.getIocn());
        values.put(LYTMessageDao.OS, Integer.valueOf(lytObject.getOs()));
        values.put(LYTMessageDao.ISDESTROY, lytObject.getIsDestroy());
        values.put(LYTMessageDao.ISREAD, lytObject.getIsRead());
        values.put(LYTMessageDao.MSG_ID, lytObject.getMsgId());
        values.put(LYTMessageDao.TARGETID, lytObject.getTo());
        values.put(LYTMessageDao.CONVERSATIONID, lytObject.getConversationId());
        values.put(LYTMessageDao.MSGTIME, Long.valueOf(lytObject.getMsgTime()));
        values.put(LYTMessageDao.FROM_ID, lytObject.getFromId());
        values.put(LYTMessageDao.ATTR, lytObject.getAttr());
        values.put(LYTMessageDao.CHAT_INDEX, Long.valueOf(lytObject.getChatIndex()));
        values.put(LYTMessageDao.MESSAGE_STATE, lytObject.getSendState());
        values.put(LYTMessageDao.VOICE_STATE, lytObject.getVoiceState());
        values.put(LYTMessageDao.IS_SUCCES, 0);
        LYTZMessageBody body = lytObject.getLytzMessageBody();
        if (messageType.equals(LYTMessage.Type.TXT.getName())) {//文字消息
            values.put(LYTMessageDao.CHAT_RETRIEVAL, ((LYTZTextMessageBody) body).getText());
        } else if (messageType.equals(LYTMessage.Type.IMAGE.getName())) {
            values.put(LYTMessageDao.FILE_TYPE, LYTMessage.FileType.IMGE.ordinal());
        } else if (messageType.equals(LYTMessage.Type.FILE.getName())) {//文件
            String fileName = ((LYTZNormalFileMessageBody) body).getFileName();
            values.put(LYTMessageDao.CHAT_RETRIEVAL, fileName);
            int index = fileName.lastIndexOf(".");
            String filetype = fileName.substring(index + 1, fileName.length()).toLowerCase();
            values.put(LYTMessageDao.FILE_TYPE, FileUtils.getType(filetype));
        } else if (messageType.equals(LYTMessage.Type.MULTI_VIDEO.getName())) {// 视频
            LYTZVideoMessageBody lytzVideoMessageBody = ((LYTZVideoMessageBody) body);
            if (lytzVideoMessageBody.getCallType() == 2) {
                values.put(LYTMessageDao.VIDEO_ID, lytzVideoMessageBody.getRoomId());
                values.put(LYTMessageDao.VIDEO_STATE, lytzVideoMessageBody.getRoomStatus());
            }
        } else if (messageType.equals(LYTMessage.Type.AT.getName())) {
            values.put(LYTMessageDao.AT_SATET, lytObject.getAtState());
        }

        values.put(LYTMessageDao.MESSAGE_BODY, ObjectToByte(body));


    }


    @Override
    @Deprecated
    public boolean saveSession(String tableName, String sessionId, int chatType, String toId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            //保存详情表
            ContentValues infoValues = new ContentValues();
            infoValues.put(CONVERSATION_ID, sessionId);
            infoValues.put(CHAT_TPYPE, chatType);
            infoValues.put(TO, toId);
            index = db.replace(tableName, null, infoValues);

        }
        return index > 0;
    }

    @Override
    public boolean saveSession(String tableName, LYTMessage lytMessage, String useId) {

        if (lytMessage.getLytObject().getChatType() == LYTMessage.ChatType.Chat.ordinal()) {
            if (!lytMessage.getFrom().equals(useId)) {
                lytMessage.getLytObject().setTo(lytMessage.getFrom());
            }
        }


        return saveSessionMessage(tableName, lytMessage);
    }

    private boolean saveSessionMessage(String tableName, LYTMessage lytMessage) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.MSG_ID + " = ?";
        long index = 0;
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            LYTZMessage lytObject = lytMessage.getLytObject();
            getContentValues(values, lytObject);
            index = db.replace(tableName, null, values);
        }

        return index > 0;
    }


    @Override
    public boolean removeAdminFromGroup(String tableName, String sessionId, String manageId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTGroupDao.GROUP_ID + "= ?";
            List<String> strings = new ArrayList<>();
            strings.clear();
            //查询群组管理者
            Cursor cursor = db.query(tableName, new String[]{LYTGroupDao.MANAGER_IDS}, whereClause, new String[]{sessionId}, null, null, null);
            while (cursor.moveToNext()) {
                byte[] ids = cursor.getBlob(cursor.getColumnIndexOrThrow(LYTGroupDao.MANAGER_IDS));
                if (!TextUtils.isEmpty(ids.toString())) {
                    List<String> daa = (List<String>) byteToObject(ids);
                    if (null != daa && daa.size() > 0) {
                        strings.addAll(daa);
                    }


                }
            }

            if (strings.contains(manageId)) {
                strings.remove(manageId);
            }

            cursor.close();
            ContentValues values = new ContentValues();
            values.put(LYTGroupDao.MANAGER_IDS, ObjectToByte(strings));
            int da = db.update(tableName, values, whereClause, new String[]{sessionId});
            if (da > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAdminFromGroup(String tableName, String sessionId, String manageId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTGroupDao.GROUP_ID + "= ?";
            List<String> strings = new ArrayList<>();
            strings.clear();
            //查询群组管理者
            Cursor cursor = db.query(tableName, new String[]{LYTGroupDao.MANAGER_IDS}, whereClause, new String[]{sessionId}, null, null, null);
            while (cursor.moveToNext()) {
                byte[] ids = cursor.getBlob(cursor.getColumnIndexOrThrow(LYTGroupDao.MANAGER_IDS));
                if (!TextUtils.isEmpty(ids.toString())) {
                    List<String> daa = (List<String>) byteToObject(ids);
                    if (null != daa && daa.size() > 0) {
                        strings.addAll(daa);
                    }


                }
            }

            if (!strings.contains(manageId)) {
                strings.add(manageId);
            }

            cursor.close();
            ContentValues values = new ContentValues();
            values.put(LYTGroupDao.MANAGER_IDS, ObjectToByte(strings));
            int da = db.update(tableName, values, whereClause, new String[]{sessionId});
            if (da > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEphemeralityMessageByChatIndex(String tableName, String sessionId, long maxIndex) {
        if (!isTableExist(tableName)) {
            return true;
        }

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.CHAT_INDEX + " < ?" + " and " + LYTMessageDao.ISDESTROY + "  = ? ";
            db.delete(tableName, whereClause, new String[]{String.valueOf(maxIndex), "1"});
        }
        return true;
    }

    @Override
    public void changeSessionOrder(String tableName, String sessionId, boolean isTop) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = CONVERSATION_ID + " =  ? ";
            ContentValues values = new ContentValues();
            values.put(ISTOP, isTop);
            db.update(tableName, values, whereClause, new String[]{sessionId});
        }
    }

    @Override
    public int getReadModel(String tableName, String sessionId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int model = 0;
        if (db.isOpen()) {
            String whereClause = CONVERSATION_ID + "= ?";
            Cursor cursor2 = db.query(tableName, null, whereClause, new String[]{sessionId}, null, null, null, null);
            while (cursor2.moveToNext()) {
                model = cursor2.getInt(cursor2.getColumnIndexOrThrow(LYTConversationDao.READ_MODEL));
            }
        }
        return model;
    }

    @Override
    public long getChatIndex(String tableName) {
        long chatIndex = 0;
        if (!isTableExist(tableName)) {
            return chatIndex;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        if (db.isOpen()) {
            String sql = " SELECT  " + LYTMessageDao.CHAT_INDEX + "  FROM  " + tableName + "  ORDER BY  " + LYTMessageDao.CHAT_INDEX + "  desc  limit 0,1";
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                chatIndex = cursor.getLong(cursor.getColumnIndexOrThrow(LYTMessageDao.CHAT_INDEX));
            }
        }
        return chatIndex;
    }

    @Override
    public boolean updateMessageTimeAndChatIndex(String tableName, String messageId, long chatIndex, long msgTime, int sendState) {
        int index = 0;
        if (!isTableExist(tableName)) {
            return false;
        }

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.MESSAGE_STATE, sendState);
            values.put(LYTMessageDao.MSGTIME, msgTime);
            values.put(LYTMessageDao.CHAT_INDEX, chatIndex);
            values.put(LYTMessageDao.IS_SUCCES, sendState);
            String whereClause = "msgId = ?";
            index = db.update(tableName, values, whereClause, new String[]{messageId});
        }
        if (index > 0) {
            return true;
        }
        return false;
    }

    public boolean isTableExist(String sqlName) {
        String sql2 = " SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + sqlName + "'";
        boolean isTableExist = true;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cus = db.rawQuery(sql2, null);
            //判断表是否存在  false 表示不存在

            while (cus.moveToNext()) {
                if (cus.getInt(0) == 0) {
                    isTableExist = false;
                } else {
                    isTableExist = true;
                }
            }
        }

        return isTableExist;
    }

    //判断会话里表中是否有此会话ID
    public boolean isConversation(String s, String conversationId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.query(s, null, CONVERSATION_ID + "=?", new String[]{conversationId}, null, null, null);
            if (cursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }


    public byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream e = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(e);
            oo.writeObject(obj);
            bytes = e.toByteArray();
            e.close();
            oo.close();
        } catch (Exception var5) {
            System.out.println("translation" + var5.getMessage());
            var5.printStackTrace();
        }
        return bytes;
    }


    public long setGroupEphemerality(String tableName, String grouId, int model) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            String whereClause = CONVERSATION_ID + "= ?";
            ContentValues values = new ContentValues();
            values.put(LYTConversationDao.READ_MODEL, model);
            values.put(CONVERSATION_ID, grouId);
            if (isConversation(tableName, grouId)) {
                index = db.update(tableName, values, whereClause, new String[]{grouId});
            } else {
                index = db.replace(tableName, null, values);
            }
        }
        return index;
    }

    @Override
    public boolean saveSystemMessage(String tableName, LYTMessage lytMessage) {
        //查询最后一条消息的index
        int index = getLastMesaageIndex(tableName);
        lytMessage.getLytObject().setChatIndex(index);
        return saveMessage(tableName, lytMessage);
    }

    @Override
    public List<LYTSessoinList> getLocalSessionList(String tableName) {


        return null;
    }

    @Override
    public synchronized List<LYTSessoinList> getLocalSessionList(String sessoinName, String infoName, String tableName) {
        List<LYTSessoinList> lytSessoinLists = new ArrayList<>();
        String sql = " SELECT * FROM  " + makeSessoinListSortWhereSql(sessoinName, infoName);

        Log.e("getLocalSessionList", "::" + sql);
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (!db.isOpen()) {
            Log.d("LYTDBManager", "数据库未打开");
            return lytSessoinLists;
        }
        if (!isTableExist(sessoinName) || !isTableExist(infoName)) {
            Log.d("LYTDBManager", sessoinName + ":不存在 或者 " + infoName + ": 不存在");
            return lytSessoinLists;
        }
        Cursor sessoinCursor = db.rawQuery(sql, null);
        //查询会话列表
        while (sessoinCursor.moveToNext()) {
            LYTMessage lytMessage = getCursor(sessoinCursor);
            int isTop = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow("bieMing"));//置顶
            int read_model = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(LYTConversationDao.READ_MODEL));
            int disturb = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(LYTConversationDao.IS_DISTURB));
            LYTSessoinList lytSessoinList = new LYTSessoinList();
            lytSessoinList.setLytMessage(lytMessage);
            lytSessoinList.setIsTop(isTop);
            lytSessoinList.setReadModel(read_model);
            lytSessoinList.setDisturb(disturb);
            lytSessoinLists.add(lytSessoinList);
        }

        sessoinCursor.close();

        if (lytSessoinLists.size() == 0) {
            Log.d("LYTDBManager", "未查询到数据");
        }
        for (LYTSessoinList lytSessoinList : lytSessoinLists) {
            LYTMessage lytMessage = lytSessoinList.getLytMessage();
            lytSessoinList.setChatType(lytMessage.getChatType().ordinal());
            String infoSql = "SELECT * FROM " + makeInfoSql(lytMessage.getChatType(), lytMessage.conversationId(), tableName);
            if (!TextUtils.isEmpty(infoSql)) {
                Cursor infoCursor = db.rawQuery(infoSql, null);
                while (infoCursor.moveToNext()) {
                    if (LYTMessage.ChatType.Chat == lytMessage.getChatType()) {//单聊
                        String userName = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTSimpleDao.USER_NAME));
                        String remote_pic = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTSimpleDao.USER_ICON));
                        String local_pic = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTSimpleDao.USER_LOCA_LICON));
                        lytSessoinList.setIcon(remote_pic);
                        lytSessoinList.setLocalIcon(local_pic);
                        lytSessoinList.setName(userName);
                    } else if (LYTMessage.ChatType.ChatRoom == lytMessage.getChatType()) {//聊天室
                        String rooName = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTChatRoomDao.ROOM_NAME));
                        lytSessoinList.setName(rooName);
                    } else if (LYTMessage.ChatType.GroupChat == lytMessage.getChatType()) {//讨论组
                        String groupName = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_NAME));
                        String remote_pic = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_REMOTE_PIC));
                        String local_pic = infoCursor.getString(infoCursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_LOCAL_PIC));
                        lytSessoinList.setIcon(remote_pic);
                        lytSessoinList.setLocalIcon(local_pic);
                        lytSessoinList.setName(groupName);
                    }
                }
                infoCursor.close();
            }
        }

        //查询 未读消息数量
        for (int i = 0; i < lytSessoinLists.size(); i++) {
            //查看是是否消息表
            String id = lytSessoinLists.get(i).getLytMessage().conversationId();
            String joinName = LYTMessageDao.SQL_NAME + tableName + id;
//            if (!isTableExist(joinName)) {
//                if (lytSessoinLists.size() > 0) {
//                    lytSessoinLists.remove(i);
//                    i--;
//                }
//            } else {
            //为讨论组 需要查询通知表
            boolean isNotice = getIsNotice(LYTNoticeDao.GROUP_NOTICE + tableName, id);

            lytSessoinLists.get(i).setNotice(isNotice);
            // 需要查询@消息
            boolean isAt = getIsAt(joinName);
            //获取视频会议消息的记录
            lytSessoinLists.get(i).setAt(isAt);
            boolean isVideo = getVideo(joinName);
            lytSessoinLists.get(i).setVideo(isVideo);
            //清除阅后即焚烧消息
            int chatIndex = clearDeletableSecret(joinName);
            // 查询未读数
            String sqlCount = null;
            if (lytSessoinLists.get(i).getChatType() == LYTMessage.ChatType.GroupChat.ordinal()) {
                //查询所有未读消息数
                sqlCount = "select count(*) as count from  " + joinName + makeMessageCountWhereSql(true, lytSessoinLists.get(i).getReadModel(), 0);
            } else {
                sqlCount = "select count(*) as count from  " + joinName + makeMessageCountWhereSql(false, 0, 0);
            }
            if (!isTableExist(joinName)) {
                lytSessoinLists.get(i).setMsgCount(0);
            } else {
                Cursor messageCount = db.rawQuery(sqlCount, null);
                while (messageCount.moveToNext()) {
                    long count = messageCount.getLong(messageCount.getColumnIndex("count"));
                    lytSessoinLists.get(i).setMsgCount(count);
                }
                messageCount.close();
            }

//            }
        }


        return lytSessoinLists;

    }

    /**
     * 根据聊天类型获取不同的sql语句
     *
     * @param chatType
     * @param sessoinId
     * @param tableName
     * @return
     */
    private String makeInfoSql(LYTMessage.ChatType chatType, String sessoinId, String tableName) {
        String name = null;
        String sessoin = null;
        if (LYTMessage.ChatType.Chat == chatType) {//单聊
            name = LYTSimpleDao.USER_INFO_NAME;
            sessoin = CONVERSATION_ID;
        } else if (LYTMessage.ChatType.ChatRoom == chatType) {//聊天室
            name = LYTChatRoomDao.CHAT_ROOM_INFO;
            sessoin = LYTChatRoomDao.ROOM_ID;
        } else if (LYTMessage.ChatType.GroupChat == chatType) {//讨论组
            name = LYTGroupDao.GROUP_INFO_NAME;
            sessoin = LYTGroupDao.GROUP_ID;
        }
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sessoin)) {
            Log.e("LYTDBManager", "详情数据库拼写错误");
            return null;
        }
        return String.format("  %s  WHERE  %s.%s= '%s' ", name + tableName, name + tableName, sessoin, sessoinId);

    }


    @Override
    public List<LYTMessage> localMessage(String tableName, long firstChatIndex, long count, boolean toBackQuery) {
        List<LYTMessage> lytMessages = new ArrayList<>();

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            //查看消息表是否存在
            if (!isTable(tableName, db)) {
                return lytMessages;
            }
            String sql = "select * from  " + " (  select * from " + tableName + makePageWhereSql2(firstChatIndex, count, toBackQuery);
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                lytMessages.add(getCursor(cursor));
            }
        }
        return lytMessages;
    }


    public static final String makePageWhereSql2(long firstChatIndex, long pageCount, boolean toBackQuery) {
        if (toBackQuery) {
            return String.format("  WHERE  chatIndex >%d   order by chatIndex asc ) order by localIndx asc limit 0,%d", firstChatIndex, pageCount);
        } else {
            return String.format("  WHERE  chatIndex <%d   order by chatIndex desc ) order by localIndx desc limit 0,%d", firstChatIndex, pageCount);
        }
    }


    /**
     * 查询会话列表与属性表 并排序
     *
     * @param sessoinName
     * @param infoName
     * @return
     */
    public static final String makeSessoinListSortWhereSql(String sessoinName, String infoName) {
        return String.format("  (  SELECT  %s.* , %s.%s , %s.%s  ,%s.%s , %s.%s  , case when %s.%s is NULL then 0 else  %s.%s end bieMing  FROM %s left join  %s on  %s.%s = %s.%s order by %s desc ) order by  bieMing  desc",
                sessoinName, infoName, "chat_type", infoName, "disturb", infoName, "read_model", infoName, "to_id", infoName, "istop", infoName, "istop", sessoinName, infoName, sessoinName, "conversationId", infoName, "session_id", "msgTime");
    }


    public static final String makeSessoinListWhereSql(String sessoinName, String infoName) {

        return String.format("  (  SELECT * FROM  %s left join  %s  on  %s.%s=%s.%s  order by  %s desc )  order by  %s desc ",
                sessoinName, infoName, sessoinName, LYTMessageDao.CONVERSATIONID, infoName
                , CONVERSATION_ID, LYTMessageDao.MSGTIME, ISTOP);
    }


    /**
     * 查询会话列表与属性表
     *
     * @param sessoinName
     * @param infoName
     * @param sessoinId
     * @return
     */
    public static final String makeSimpleSessoinWhereSql(String sessoinName, String infoName, String sessoinId) {

        return String.format("  %s left join  %s  on  %s.%s=%s.%s  where  %s.%s='%s' ",
                sessoinName, infoName, sessoinName, LYTMessageDao.CONVERSATIONID, infoName
                , CONVERSATION_ID, sessoinName, LYTMessageDao.CONVERSATIONID, sessoinId);
    }

    /**
     * 查询会话列表与属性表 ,排序
     *
     * @param sessoinName
     * @param infoName
     * @return
     */
    public static final String makeSessoinListWhereSql2(String sessoinName, String infoName) {

        return String.format("  (  SELECT * FROM  %s left join  %s  on  %s.%s=%s.%s  order by  %s desc )  order by  %s desc ",
                sessoinName, infoName, sessoinName, LYTMessageDao.CONVERSATIONID, infoName
                , CONVERSATION_ID, LYTMessageDao.MSGTIME, ISTOP);


    }

    @Override
    public List<LYTMessage> localMessage(int readModel, String tableName, long firstChatIndex, long count, boolean toBackQuery) {
        List<LYTMessage> lytMessages = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            //查看消息表是否存在
            if (!isTable(tableName, db)) {
                return lytMessages;
            }
            String sql = "select * from " + "(  select * from " + tableName + " where " + LYTMessageDao.ISDESTROY + "= " + readModel + makePageWhereSql(firstChatIndex, count, toBackQuery);
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() == 0) {
                return lytMessages;
            }
            while (cursor.moveToNext()) {
                lytMessages.add(getCursor(cursor));
            }
        }
        return lytMessages;

    }

    @Override
    public LYTMessage getLastVideoMessage(String tableName) {
        LYTMessage lytMessage = null;

        if (!isTableExist(tableName)) {
            return lytMessage;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.MESSAGER_TYPE + " = ?";
            String groupBy = LYTMessageDao.CHAT_INDEX + " desc";
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.MULTI_VIDEO.getName()}, null, null, groupBy, "0 , 1");
            while (cursor.moveToNext()) {
                String messageType = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGER_TYPE));
                int chatType = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.CHAT_TYPE));
                int os = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.OS));
                int isDestroy = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.ISDESTROY));
                int isRead = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.ISREAD));
                String msgId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.MSG_ID));
                String conversationId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.CONVERSATIONID));
                long msgTime = cursor.getLong(cursor.getColumnIndexOrThrow(LYTMessageDao.MSGTIME));
                String fromId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.FROM_ID));
                String attr = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.ATTR));
                String to = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.TARGETID));
                byte[] body = cursor.getBlob(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGE_BODY));
                int videoState = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.VIDEO_STATE));
                LYTZMessage lytObject = new LYTZMessage();
                if (videoState == 1) {
                    lytObject.setVideoState(true);
                } else {
                    lytObject.setVideoState(false);
                }
                lytObject.setChatType(chatType);
                lytObject.setOs(os);
                lytObject.setIsRead(isRead);
                lytObject.setIsDestroy(isDestroy);
                lytObject.setMsgId(msgId);
//                    lytObject.setAppkey(appkey);
                lytObject.setConversationId(conversationId);
                lytObject.setMsgTime(msgTime);
                lytObject.setFromId(fromId);
                lytObject.setAttr(attr);
                lytObject.setTo(to);
//                    LYTZMessageBody lytzMessageBody = LYTMessage.getMessage(body, messageType);
//                    lytObject.setLytzMessageBody(lytzMessageBody);
                lytMessage = new LYTMessage(lytObject);
//                    LYTMessage lytMessage = new LYTMessage(lytObject);
                lytMessage.addBody(LYTMessage.getMessage(body, messageType));

            }
            cursor.close();
        }


        return lytMessage;
    }

    private boolean isTable(String tableName, SQLiteDatabase db) {
        String sql2 = " SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + tableName + "'";
        Cursor cus = db.rawQuery(sql2, null);
        boolean isTableExist = true;
        while (cus.moveToNext()) {
            if (cus.getInt(0) == 0) {
                isTableExist = false;
            } else {
                isTableExist = true;
            }
        }

        return isTableExist;
    }


    public static final String makePageWhereSql(long firstChatIndex, long pageCount, boolean toBackQuery) {
        if (toBackQuery) {
            return String.format(" and chatIndex >%d   order by chatIndex asc )  order by localIndx asc limit 0,%d", firstChatIndex, pageCount);
        } else {
            return String.format(" and chatIndex <%d  order by chatIndex desc )  order by localIndx desc limit 0,%d", firstChatIndex, pageCount);
        }
    }

    public static final String makeMessageCountWhereSql(boolean isReadModel, int isDestory, int isRead) {
        if (isReadModel) {
            return String.format(" WHERE " + LYTMessageDao.ISDESTROY + " = %d and  " + LYTMessageDao.ISREAD + "=  %d ", isDestory, isRead);

        } else {
            return String.format(" WHERE " + LYTMessageDao.ISREAD + "=  %d ", isRead);
        }

    }

    //数据库消息操作
    private LYTMessage getCursor(Cursor cursor) {
        String messageType = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGER_TYPE));
        int chatType = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.CHAT_TYPE));
        int os = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.OS));
        int isDestroy = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.ISDESTROY));
        int isRead = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.ISREAD));
        String msgId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.MSG_ID));
//                    String appkey = messageCursor.getString(messageCursor.getColumnIndexOrThrow(LYTMessageDao.APPKEY));
        String conversationId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.CONVERSATIONID));
        long msgTime = cursor.getLong(cursor.getColumnIndexOrThrow(LYTMessageDao.MSGTIME));
        int state = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGE_STATE));
        String fromId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.FROM_ID));
        String attr = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.ATTR));
        String to = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.TARGETID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.USERNAME));
        String icon = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.USERICON));
        byte[] body = cursor.getBlob(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGE_BODY));
        long chatIndex = cursor.getLong(cursor.getColumnIndexOrThrow(LYTMessageDao.CHAT_INDEX));
        int voice_state = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.VOICE_STATE));
        int at_state = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.AT_SATET));
        int video_state = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.VIDEO_STATE));
        int localIndx = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.LOCALINDEX));
        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.READ_DURATION));
        int isSuccess = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.IS_SUCCES));
        long secretTime = cursor.getLong(cursor.getColumnIndexOrThrow(LYTMessageDao.READ_START_TIME));
        LYTZMessage lytObject = new LYTZMessage();
        lytObject.setChatType(chatType);
        lytObject.setOs(os);
        lytObject.setIsRead(isRead);
        lytObject.setIsDestroy(isDestroy);
        lytObject.setIsSuccess(isSuccess);
        lytObject.setMsgId(msgId);
        lytObject.setName(name);
        lytObject.setLocalIndx(localIndx);
        lytObject.setIocn(icon);
        lytObject.setChatIndex(chatIndex);
        lytObject.setVoiceState(voice_state);
        lytObject.setAtState(at_state);
        if (video_state == 1) {
            lytObject.setVideoState(true);
        } else {
            lytObject.setVideoState(false);
        }
//                    lytObject.setAppkey(appkey);
        lytObject.setConversationId(conversationId);
        lytObject.setMsgTime(msgTime);
        lytObject.setFromId(fromId);
        lytObject.setAttr(attr);
        lytObject.setTo(to);
        lytObject.setSendState(state);
        lytObject.setDuration(duration);
        lytObject.setSecretTime(secretTime);
        LYTMessage lytMessage = new LYTMessage(lytObject);

        lytMessage.addBody(LYTMessage.getMessage(body, messageType));

        return lytMessage;
    }


    public static final String makeLocalMessageSql(String sqlName, String messageName) {

        return String.format(" SELECT *  FROM  %s  left JOIN ( select * from ( select * from  %s  order by chatIndex desc )  order by localIndx desc limit 0,1)", sqlName, messageName);


    }


    //清除已过时的阅后即焚消息
    public int clearDeletableSecret(String tableName) {
        if (!isTableExist(tableName)) {
            return 0;
        }
        String sql = "delete from  t_message where sessionId = ? and flag=1 and readSecretTime>0 and ((? - readSecretTime) >= (readSecretDuration*1000)) and creator = ?";
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            String whereClause = LYTMessageDao.ISDESTROY + " = ? " + "and " + LYTMessageDao.READ_START_TIME + " > 0" + " and " + " ( ( ? -" + LYTMessageDao.READ_START_TIME + " ) " + " >= ( " + LYTMessageDao.READ_DURATION + "*1000 ) )";
            int ss = db.delete(tableName, whereClause, new String[]{"1", currentTimeMillis});
            return ss;
        }
        return 0;
    }

    @Override
    public List<LYTMessage> getATMessageByATState(String tableName, int state) {
        List<LYTMessage> lytMessages = new ArrayList<>();
        if (!isTableExist(tableName)) {
            return lytMessages;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.AT_SATET + "=  ?";
        if (db.isOpen()) {
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{String.valueOf(state)}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
            while (cursor.moveToNext()) {
                lytMessages.add(getCursor(cursor));
            }
            cursor.close();
        }

        return lytMessages;
    }

    @Override
    public LYTSessoinList getSimpleLocalSession(String sessoinName, String infoName, String tableName, String sessoinId) {//获取单个的本地
        String sql = " SELECT * FROM  " + makeSimpleSessoinWhereSql(sessoinName, infoName, sessoinId);
        LYTSessoinList lytSessoinList = null;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor sessoinCursor = db.rawQuery(sql, null);
            //查询会话列表
            while (sessoinCursor.moveToNext()) {
                LYTMessage lytMessage = getCursor(sessoinCursor);
                int isTop = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(ISTOP));//置顶
                int read_model = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(LYTConversationDao.READ_MODEL));
                int disturb = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(LYTConversationDao.IS_DISTURB));
                lytSessoinList = new LYTSessoinList();
                lytSessoinList.setLytMessage(lytMessage);
                lytSessoinList.setIsTop(isTop);
                lytSessoinList.setReadModel(read_model);
                lytSessoinList.setDisturb(disturb);
            }

            if (null != lytSessoinList) {

                String joinName = LYTMessageDao.SQL_NAME + tableName + lytSessoinList.getLytMessage().conversationId();
                sessoinCursor.close();
                boolean isNotice = getIsNotice(LYTNoticeDao.GROUP_NOTICE + tableName, lytSessoinList.getLytMessage().conversationId());
                lytSessoinList.setNotice(isNotice);
                // 需要查询@消息
                boolean isAt = getIsAt(joinName);
                //获取视频会议消息的记录
                lytSessoinList.setAt(isAt);
                boolean isVideo = getVideo(joinName);
                lytSessoinList.setVideo(isVideo);
            }

        }
        return lytSessoinList;
    }

    @Override
    public boolean clearEphemeralityMessage(String tableName) {
        if (!isTableExist(tableName)) {
            return true;
        }
        int index = 0;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.ISDESTROY + "= ?";
            index = db.delete(tableName, whereClause, new String[]{"1"});
        }
        return index > 0;

    }

    @Override
    public boolean updateAtState(String tableName, String messageId, int atSate) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long chatIndex = 0;
        if (!isTableExist(tableName)) {
            Log.e("LYTDBManager", "updateAtState:: 删除消息时候找不到表");
            return false;
        }
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.MSG_ID + " =  ? ";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.AT_SATET, atSate);
            chatIndex = db.update(tableName, values, whereClause, new String[]{messageId});
        }

        return chatIndex > 0;
    }

    @Override
    public boolean updateVideoState(String tableName, String messageId, int videoState) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.MSG_ID + " =  ? ";
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.VIDEO_STATE, videoState);
            db.update(tableName, values, whereClause, new String[]{String.valueOf(messageId)});
        }
        return true;
    }


    /**
     * @param tableName
     * @param messageId
     * @param voiceState
     * @return
     */
    @Override
    public boolean updateVoiceState(String tableName, String messageId, int voiceState) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long chatIndex = 0;
        if (!isTableExist(tableName)) {
            Log.e("LYTDBManager", "updateVideoState:: 删除消息时候找不到表");
            return false;
        }
        String whereClause = LYTMessageDao.MSG_ID + " =  ? ";
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.VOICE_STATE, voiceState);
            chatIndex = db.update(tableName, values, whereClause, new String[]{messageId});
        }
        return chatIndex > 0;
    }

    @Override
    public boolean deleteMesaageByMessageId(String tableName, String messageId) {
        long chatIndex = 0;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (!isTableExist(tableName)) {
            Log.e("LYTDBManager", "deleteMesaageByMessageId:: 删除消息时候找不到表");
            return false;
        }

        if (db.isOpen()) {
            String whereClause = LYTMessageDao.MSG_ID + " =  ? ";
            chatIndex = db.delete(tableName, whereClause, new String[]{messageId});

        }

        return true;
    }

    @Override
    public boolean resetMessageCountById(String tableName) {
        if (!isTableExist(tableName)) {
            return true;
        }
        int index = 0;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.ISREAD + "  = ? ";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.ISREAD, "1");
            index = db.update(tableName, values, whereClause, new String[]{"0"});
        }
        return index > 0;
    }

    @Override
    public List<LYTMessage> retrievalFile(String tableName, int fileType, String text) {
        List<LYTMessage> lytMessages = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();


        if (db.isOpen()) {
            //查看消息表是否存在
            if (!isTable(tableName, db)) {
                return lytMessages;
            }
            Cursor cursor = null;
            if (fileType == LYTMessage.FileType.IMGE.ordinal()) {// 图片检索

                String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? ";
                //desc
                cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.IMAGE.getName()}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);

            } else if (fileType == LYTMessage.FileType.FILE.ordinal()) {//检索文件
                if (TextUtils.isEmpty(text)) {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? " + " and " + LYTMessageDao.FILE_TYPE + " =  ? ";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName(), String.valueOf(fileType)}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                } else {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? " + " and " + LYTMessageDao.FILE_TYPE + " =  ? " + " and " + "( " + LYTMessageDao.CHAT_RETRIEVAL + " like ?" + " or " + LYTMessageDao.USERNAME + " like  ? " + " )";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName(), String.valueOf(fileType), "%" + text + "%", "%" + text + "%"}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);

                }


            } else if (fileType == LYTMessage.FileType.OTHER.ordinal()) {
                if (TextUtils.isEmpty(text)) {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? " + " and " + LYTMessageDao.FILE_TYPE + " =  ? ";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName(), String.valueOf(fileType)}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                } else {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? " + " and " + LYTMessageDao.FILE_TYPE + " like ? " + " and " + "( " + LYTMessageDao.CHAT_RETRIEVAL + " like  ?" + " or " + LYTMessageDao.USERNAME + " like  ? " + " )";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName(), String.valueOf(fileType), "%" + text + "%", "%" + text + "%"}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                }
            } else if (fileType == LYTMessage.FileType.ALL.ordinal()) {
                if (TextUtils.isEmpty(text)) {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? ";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName()}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                } else {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? " + " and " + "( " + LYTMessageDao.CHAT_RETRIEVAL + " like ? " + " or " + LYTMessageDao.USERNAME + " like ? " + " )";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName(), "%" + text + "%", "%" + text + "%"}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                }

            } else {
                if (TextUtils.isEmpty(text)) {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? ";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName()}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                } else {
                    String whereClause = LYTMessageDao.MESSAGER_TYPE + " =  ? " + " and " + "( " + LYTMessageDao.CHAT_RETRIEVAL + " like ? " + " or " + LYTMessageDao.USERNAME + " like ? " + " )";
                    cursor = db.query(tableName, null, whereClause, new String[]{LYTMessage.Type.FILE.getName(), "%" + text + "%", "%" + text + "%"}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
                }
            }

            if (cursor == null) {
                return lytMessages;
            }
            while (cursor.moveToNext()) {
                lytMessages.add(getCursor(cursor));
            }
        }
        return lytMessages;
    }


    @Override
    public boolean updateReadMessageSecretTimer(String tableName, String messageId, long startTime, int duration) {
        if (!isTableExist(tableName)) {
            return true;
        }
        int index = 0;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.MSG_ID + "=  ?";
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.READ_START_TIME, startTime);
            values.put(LYTMessageDao.READ_DURATION, duration);
            index = db.update(tableName, values, whereClause, new String[]{messageId});
        }
        return index > 0;
    }

    @Override
    public boolean saveFileLocalPath(String tableName, String messageId, String localPath) {

        int index = 0;
        if (!isTableExist(tableName)) {
            return index == 0;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        LYTMessage lytMessage = null;
        String whereClause = LYTMessageDao.MSG_ID + "=  ?";
        if (db.isOpen()) {
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{messageId}, null, null, null, null);
            while (cursor.moveToNext()) {
                lytMessage = getCursor(cursor);
            }

            if (lytMessage == null) {
                return index == 0;
            }
            LYTZMessageBody lytzMessageBody = lytMessage.getLytObject().getLytzMessageBody();
            if (lytzMessageBody instanceof LYTZFileMessageBody) {
                LYTZFileMessageBody lytzFileMessageBody = (LYTZFileMessageBody) lytzMessageBody;
                lytzFileMessageBody.setLocalUrl(localPath);
            } else if (lytzMessageBody instanceof LYTZVoiceMessageBody) {
                LYTZVoiceMessageBody lytzVoiceMessageBody = (LYTZVoiceMessageBody) lytzMessageBody;
                lytzVoiceMessageBody.setLocalUrl(localPath);
            }
            cursor.close();

            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.MESSAGE_BODY, ObjectToByte(lytzMessageBody));
            index = db.update(tableName, values, whereClause, new String[]{messageId});

        }


        return index > 0;
    }

    @Override
    public boolean saveMessage(String tableName, String messageType, LYTMessage newLYTMessage) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.MSG_ID + " = ?";
        if (db.isOpen()) {
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{newLYTMessage.getMsgId()}, null, null, null);
            if (cursor.getCount() == 0) {
                cursor.close();
                ContentValues values = new ContentValues();
                LYTZMessage lytObject = newLYTMessage.getLytObject();
                getContentValues(values, lytObject, messageType);
                long da = db.replace(tableName, null, values);
                if (da > 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getUnreadMessagesCountBySessoinId(String tableName) {

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long count = 0;
        if (db.isOpen()) {

            if (!isTableExist(tableName)) {
                return count;
            }
            //查询所有未读消息数
            String sqlCount = "select count(*) as count from  " + tableName + "  where isRead='0'";
            Cursor messageCount = db.rawQuery(sqlCount, null);
            while (messageCount.moveToNext()) {
                count = messageCount.getLong(messageCount.getColumnIndex("count"));

            }
            messageCount.close();
        }


        return count;
    }

    @Override
    public long getAllUnreadMessagesCount(String tableName) {

        long count = 0;
        String sql = " SELECT * FROM  " + makeSessoinListWhereSql(LYTConversationDao.SESSOIN_LIST + tableName, LYTConversationDao.CONVERSATION_TABLE_INFO + tableName);
        List<LYTSessoinList> lytSessoinLists = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (!db.isOpen()) {
            Log.d("LYTDBManager", "数据库未打开");
            return count;
        }

        Cursor sessoinCursor = db.rawQuery(sql, null);
        //查询会话列表
        while (sessoinCursor.moveToNext()) {
            LYTMessage lytMessage = getCursor(sessoinCursor);
            int isTop = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(ISTOP));//置顶
            int read_model = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(LYTConversationDao.READ_MODEL));
            int disturb = sessoinCursor.getInt(sessoinCursor.getColumnIndexOrThrow(LYTConversationDao.IS_DISTURB));
            LYTSessoinList lytSessoinList = new LYTSessoinList();
            lytSessoinList.setLytMessage(lytMessage);
            lytSessoinList.setIsTop(isTop);
            lytSessoinList.setReadModel(read_model);
            lytSessoinList.setDisturb(disturb);
            lytSessoinLists.add(lytSessoinList);
        }

        sessoinCursor.close();
        for (LYTSessoinList lytSessoinList : lytSessoinLists) {
            count += getUnreadMessagesCountBySessoinId(lytSessoinList.getLytMessage().conversationId());
        }


        return count;
    }

    @Override
    public LYTGroupInfo getGroupInfoByGroupId(String tableName, String id) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTGroupDao.GROUP_ID + "= ?";
            Cursor cursorInfo = db.query(tableName, null, whereClause, new String[]{id}, null, null, null, null);
            while (cursorInfo.moveToNext()) {
                String groupId = cursorInfo.getString(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_ID));
                String groupName = cursorInfo.getString(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_NAME));
                int memberCount = cursorInfo.getInt(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_MRMBER_COUNT));
                String remote_pic = cursorInfo.getString(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_REMOTE_PIC));
                String local_pic = cursorInfo.getString(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_LOCAL_PIC));
                int receiveType = cursorInfo.getInt(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_RECEIVE_TYPE));
                String groupOwnerId = cursorInfo.getString(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.GROUP_OWNER_ID));
                byte[] ids = cursorInfo.getBlob(cursorInfo.getColumnIndexOrThrow(LYTGroupDao.MANAGER_IDS));
                List<String> managerIds = (List<String>) byteToObject(ids);
                LYTGroupInfo lytGroupInfo = new LYTGroupInfo(groupId, groupName, remote_pic, local_pic, memberCount, groupOwnerId);
                lytGroupInfo.setManagerIds(managerIds);
                lytGroupInfo.setGroupOwnerId(groupOwnerId);
                return lytGroupInfo;
            }
        }

        return null;
    }

    @Override
    public synchronized List<LYTGroupInfo> LocalGroupList(String tableName) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        List<LYTGroupInfo> lytGroupInfos = new ArrayList<>();
        if (db.isOpen()) {
            String sql = "select * from " + tableName;
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                String groupId = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_ID));
                String groupName = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_NAME));
                int memberCount = cursor.getInt(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_MRMBER_COUNT));
                String remote_pic = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_REMOTE_PIC));
                String local_pic = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_LOCAL_PIC));
                int receiveType = cursor.getInt(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_RECEIVE_TYPE));
                String groupOwnerId = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_OWNER_ID));
                byte[] ids = cursor.getBlob(cursor.getColumnIndexOrThrow(LYTGroupDao.MANAGER_IDS));
                List<String> managerIds = (List<String>) byteToObject(ids);
                LYTGroupInfo lytGroupInfo = new LYTGroupInfo(groupId, groupName, remote_pic, local_pic, memberCount, groupOwnerId);
                lytGroupInfo.setManagerIds(managerIds);
                lytGroupInfo.setGroupOwnerId(groupOwnerId);
                lytGroupInfos.add(lytGroupInfo);
            }
            cursor.close();

        }

        return lytGroupInfos;
    }

    @Override
    public boolean clearMessage(String tableName) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (!isTableExist(tableName)) {
            return false;
        }
        if (db.isOpen()) {
            String sql = " DROP TABLE  " + tableName;
            db.execSQL(sql);
            return true;
        }
        return false;
    }

    @Override
    public boolean changSessoinOrder(String tableName, String sessoinId, int order) {

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            String whereClause = CONVERSATION_ID + " =  ? ";
            ContentValues values = new ContentValues();
            values.put(ISTOP, order);
            values.put(CONVERSATION_ID, sessoinId);
            if (querySessoin(tableName, sessoinId)) {
                index = db.update(tableName, values, whereClause, new String[]{sessoinId});
            } else {
                index = db.replace(tableName, null, values);
            }
        }
        return index > 0;
    }

    @Override
    public boolean changSessoinReceiveType(String tableName, String sessoinId, int receiveType) {

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            String whereClause = CONVERSATION_ID + " =  ? ";
            ContentValues values = new ContentValues();
            values.put(LYTConversationDao.IS_DISTURB, receiveType);
            values.put(CONVERSATION_ID, sessoinId);
            if (querySessoin(tableName, sessoinId)) {
                index = db.update(tableName, values, whereClause, new String[]{sessoinId});
            } else {
                index = db.replace(tableName, null, values);
            }

        }
        return index > 0;
    }

    @Override
    public boolean querySessoin(String tableName, String sessoinId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int count;
        if (db.isOpen()) {
            String sql = " SELECT * FROM  " + String.format("  %s  WHERE  %s= '%s' ", tableName, CONVERSATION_ID, sessoinId);
            Cursor cursor = db.rawQuery(sql, null);
            count = cursor.getCount();
            cursor.close();
            return count > 0;
        }
        return false;
    }

    @Override
    public boolean clearSessoinMessage(String tableName, String sessoinId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int index = 0;
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.CONVERSATIONID + " =  ? ";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.MESSAGE_BODY, "");
            values.put(LYTMessageDao.ISDESTROY, 0);
            values.put(LYTMessageDao.FROM_ID, "");
            index = db.update(tableName, values, whereClause, new String[]{sessoinId});
        }

        return index > 0;
    }

    @Override
    public List<LYTMessage> retrievalMessage(String tableName, String content) {
        List<LYTMessage> lytMessages = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            //查看消息表是否存在
            if (!isTable(tableName, db)) {
                return lytMessages;
            }
            String whereClause = LYTMessageDao.CHAT_RETRIEVAL + " like  ? " + " and " + LYTMessageDao.MESSAGER_TYPE + " = ?";
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{"%" + content + "%", LYTMessage.Type.TXT.getName()}, null, null, LYTMessageDao.CHAT_INDEX + " desc", null);
            while (cursor.moveToNext()) {
                lytMessages.add(getCursor(cursor));
            }
        }
        return lytMessages;
    }

    @Override
    public List<LYTGroupMember> localGroupMembers(String tableName, String id) {
        List<LYTGroupMember> lytGroupMembers = new ArrayList<>();
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            //查看消息表是否存在
            if (!isTable(tableName, db)) {
                return lytGroupMembers;
            }

            String sql = " SELECT * FROM  " + makeLocalGrouMember(tableName, id);

//            String whereClause = LYTGroupDao.GROUP_ID + " = ?";
//            Cursor cursor = db.query(tableName, null, whereClause, new String[]{id}, null, null, null, null);
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                String groupId = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_ID));
                String userId = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.USER_ID));
                String position = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.USER_POSITION));
                int roleLevel = cursor.getInt(cursor.getColumnIndexOrThrow(LYTGroupDao.USER_ROLELEVEL));
                String icon = cursor.getString(cursor.getColumnIndexOrThrow(LYTSimpleDao.USER_ICON));
                String userName = cursor.getString(cursor.getColumnIndexOrThrow(LYTSimpleDao.USER_NAME));
                LYTGroupMember lytGroupMember = new LYTGroupMember();
                lytGroupMember.setPicture(icon);
                lytGroupMember.setUserName(userName);
                lytGroupMember.setUserId(userId);
                lytGroupMember.setPosition(position);
                lytGroupMember.setRoleLevel(roleLevel);
            }
        }

        return lytGroupMembers;
    }

    @Override
    public LYTMessage getMessageState(String tableName, String messageId) {
        LYTMessage lytMessage = null;
        if (!isTableExist(tableName)) {
            return lytMessage;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.MSG_ID + " = ?";
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{messageId}, null, null, null, null);
            while (cursor.moveToNext()) {
                lytMessage = getCursor(cursor);
            }


        }

        return lytMessage;
    }

    @Override
    public long getNumberofAllUnreadMessages(String tableName) {
        long count = 0;
        List<LYTSessoinList> lytSessoinLists = new ArrayList<>();
        String sql = " SELECT * FROM  " + makeSessoinListWhereSql(LYTConversationDao.SESSOIN_LIST + tableName, LYTConversationDao.CONVERSATION_TABLE_INFO + tableName);
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (!db.isOpen()) {
            Log.d("LYTDBManager", "数据库未打开");
            return count;
        }
        Cursor sessoinCursor = db.rawQuery(sql, null);

        //查询会话列表
        while (sessoinCursor.moveToNext()) {
            LYTMessage lytMessage = getCursor(sessoinCursor);
            LYTSessoinList lytSessoinList = new LYTSessoinList();
            lytSessoinList.setLytMessage(lytMessage);
            lytSessoinLists.add(lytSessoinList);
        }
        sessoinCursor.close();

        if (lytSessoinLists.size() == 0) {
            return count;
        }


        return 0;
    }

    @Override
    public synchronized List<String> getLocalGroupAndChatRoomId(String tableName) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        List<String> ids = new ArrayList<>();
        String sql = "select * from " + LYTGroupDao.GROUP_INFO_NAME + tableName;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String groupId = cursor.getString(cursor.getColumnIndexOrThrow(LYTGroupDao.GROUP_ID));
            ids.add(groupId);
        }
        cursor.close();
        String sql2 = "select * from " + LYTChatRoomDao.CHAT_ROOM_INFO + tableName;
        Cursor cursor1 = db.rawQuery(sql2, null);
        while (cursor1.moveToNext()) {
            String roomId = cursor1.getString(cursor1.getColumnIndexOrThrow(LYTChatRoomDao.ROOM_ID));
            ids.add(roomId);
        }
        cursor1.close();
        return ids;
    }

    @Override
    public LYTMessage getLastMessageByReadModel(String tableName, int model) {

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        LYTMessage lytMessage = null;
        if (!isTableExist(tableName)) {
            return lytMessage;
        }
        if (db.isOpen()) {
            String sql = "select * from " + makeLocalMessageByReadModel(tableName, model);
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                lytMessage = getCursor(cursor);
            }
            cursor.close();
        }

        return lytMessage;
    }

    @Override
    public boolean saveNoticeFlag(String s, String sessionId, long chatIndex) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CONVERSATION_ID, sessionId);
            contentValues.put(LYTMessageDao.CHAT_INDEX, chatIndex);
            index = db.replace(s, null, contentValues);
        }


        return index > 0;
    }

    @Override
    public boolean isNotice(String tableName, String sessionId, long chatIndex) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long index = 0;
        if (db.isOpen()) {
            String whereClause = CONVERSATION_ID + " = ? and " + LYTMessageDao.CHAT_INDEX + " = ?";
            Cursor cursor = db.query(tableName, null, whereClause, new String[]{sessionId, chatIndex + ""}, null, null, null, null);
            index = cursor.getCount();
            cursor.close();
        }
        return index > 0;
    }

    @Override
    public boolean updateLYTMessageReadState(String tableName, String sessionId, long chatIndex) {
        if (!isTableExist(tableName)) {
            return false;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int index = 0;
        if (db.isOpen()) {

            String whereClause = LYTMessageDao.CHAT_INDEX + "<= ?";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.ISREAD, "1");
            index = db.update(tableName, values, whereClause, new String[]{chatIndex + ""});
        }
        return index > 0;
    }

    @Override
    public boolean updateVideoStateByVideoId(String tableName, String roomId, int roomStatus) {

        if (!isTableExist(tableName)) {
            return true;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String whereClause = LYTMessageDao.VIDEO_ID + " =  ? ";
        int index = 0;
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.VIDEO_STATE, roomStatus);
            index = db.update(tableName, values, whereClause, new String[]{String.valueOf(roomId)});
        }
        return index > 0;
    }

    @Override
    public synchronized boolean deleteSessionMessageByMessageId(String tableName, String sessoinId, String messageId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int index = 0;
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.CONVERSATIONID + " =  ? and " + LYTMessageDao.MSG_ID + " = ?";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.MESSAGE_BODY, "");
            //改变消息
            values.put(LYTMessageDao.ISDESTROY, 0);

            index = db.update(tableName, values, whereClause, new String[]{sessoinId, messageId});
        }

        return index > 0;
    }

    @Override
    public boolean updateSessoinMessageState(String tableName, String sessoinId, String messageId, long chatIndex, long msgTime, int sendState) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int index = 0;

        if (db.isOpen()) {
            String whereClause = LYTMessageDao.CONVERSATIONID + " =  ? and " + LYTMessageDao.MSG_ID + " = ?";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.MESSAGE_STATE, sendState);
            //改变消息
            values.put(LYTMessageDao.CHAT_INDEX, chatIndex);
            values.put(LYTMessageDao.MSGTIME, msgTime);
            index = db.update(tableName, values, whereClause, new String[]{sessoinId, messageId});
        }

        return index > 0;
    }

    @Override
    public boolean resetMessageCountById(String tableName, int readModel) {
        if (!isTableExist(tableName)) {
            return true;
        }
        int index = 0;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {

//            String sql=makeLocalresetMessageCountById(tableName,readModel);
//            db.rawQuery(sql,null);
//            index=2;
            String whereClause = LYTMessageDao.ISREAD + "  = ?  and " + LYTMessageDao.ISDESTROY + " = ?";
            ContentValues values = new ContentValues();
            values.put(LYTMessageDao.ISREAD, "1");
            index = db.update(tableName, values, whereClause, new String[]{"0",String.valueOf(readModel)});
        }
        return index > 0;

    }

    private String makeLocalresetMessageCountById(String tableName, int model) {
        return String.format(" update %s set  isRead='1' where isRead='0' and isDestroy='%s'", tableName, model);


    }

    private String makeLocalMessageByReadModel(String tableName, int model) {
        if (model != 0 && model != 1) {
            return String.format(" %s  ORDER BY   %s  desc   limit 0, 1", tableName, LYTMessageDao.CHAT_INDEX);
        }

        return String.format(" %s where  %s='%s' ORDER BY   %s  desc   limit 0, 1", tableName, LYTMessageDao.ISDESTROY, model + "", LYTMessageDao.CHAT_INDEX);

    }

    private String makeLocalGrouMember(String tableName, String id) {

        String inmName = DBManager.ETINTERMEDIATEMEDIUM + tableName;
        String userName = LYTSimpleDao.USER_INFO_NAME + tableName;

        return String.format(" %s left join  %s  on  %s.%s=%s.%s where  %s.%s='%s'",
                inmName, userName, inmName, LYTGroupDao.USER_ID, userName, LYTGroupDao.USER_ID, inmName, LYTGroupDao.GROUP_ID, id);

    }

    /**
     * 保存发送的消息
     *
     * @param values
     * @param lytObject
     * @param messageType
     */
    private void getContentValues(ContentValues values, LYTZMessage lytObject, String messageType) {
        values.put(LYTMessageDao.MESSAGER_TYPE, messageType);
        values.put(LYTMessageDao.CHAT_TYPE, Integer.valueOf(lytObject.getChatType()));
        values.put(LYTMessageDao.USERNAME, lytObject.getName());
        values.put(LYTMessageDao.USERICON, lytObject.getIocn());
        values.put(LYTMessageDao.OS, Integer.valueOf(lytObject.getOs()));
        values.put(LYTMessageDao.ISDESTROY, lytObject.getIsDestroy());
        values.put(LYTMessageDao.ISREAD, lytObject.getIsRead());
        values.put(LYTMessageDao.MSG_ID, lytObject.getMsgId());
        values.put(LYTMessageDao.TARGETID, lytObject.getTo());
        values.put(LYTMessageDao.CONVERSATIONID, lytObject.getConversationId());
        values.put(LYTMessageDao.MSGTIME, Long.valueOf(lytObject.getMsgTime()));
        values.put(LYTMessageDao.FROM_ID, lytObject.getFromId());
        values.put(LYTMessageDao.ATTR, lytObject.getAttr());
        values.put(LYTMessageDao.CHAT_INDEX, Long.valueOf(lytObject.getChatIndex()));
        values.put(LYTMessageDao.MESSAGE_STATE, lytObject.getSendState());
        values.put(LYTMessageDao.VOICE_STATE, lytObject.getVoiceState());
        values.put(LYTMessageDao.IS_SUCCES, 0);
        LYTZMessageBody body = lytObject.getLytzMessageBody();
        if (messageType.equals(LYTMessage.Type.TXT.getName())) {//文字消息
            values.put(LYTMessageDao.CHAT_RETRIEVAL, ((LYTZTextMessageBody) body).getText());
        } else if (messageType.equals(LYTMessage.Type.IMAGE.getName())) {
            values.put(LYTMessageDao.FILE_TYPE, LYTMessage.FileType.IMGE.ordinal());
        } else if (messageType.equals(LYTMessage.Type.FILE.getName())) {//文件
            String fileName = ((LYTZNormalFileMessageBody) body).getFileName();
            values.put(LYTMessageDao.CHAT_RETRIEVAL, fileName);
            int index = fileName.lastIndexOf(".");
            String filetype = fileName.substring(index + 1, fileName.length()).toLowerCase();
            values.put(LYTMessageDao.FILE_TYPE, FileUtils.getType(filetype));

        } else if (messageType.equals(LYTMessage.Type.MULTI_VIDEO.getName())) {// 视频
            LYTZVideoMessageBody lytzVideoMessageBody = ((LYTZVideoMessageBody) body);
            if (lytzVideoMessageBody.getCallType() == 2) {
                values.put(LYTMessageDao.VIDEO_ID, lytzVideoMessageBody.getRoomId());
                values.put(LYTMessageDao.VIDEO_STATE, lytzVideoMessageBody.getRoomStatus());
            }
        } else if (messageType.equals(LYTMessage.Type.AT.getName())) {
            values.put(LYTMessageDao.AT_SATET, lytObject.getAtState());
        }
        values.put(LYTMessageDao.MESSAGE_BODY, ObjectToByte(body));

    }


    /**
     * 判断会话中是否有视频消息
     *
     * @param joinName
     * @return
     */
    private boolean getVideo(String joinName) {
        boolean isVideo;
        if (!isTableExist(joinName)) {
            isVideo = false;
        } else {
            LYTMessage lytMessage = getVideoMessage(joinName);
            if (null != lytMessage) {
                isVideo = lytMessage.getLytObject().isVideoState();
            } else {
                isVideo = false;
            }
        }
        return isVideo;
    }

    /**
     * 查询最后一条视频消息
     *
     * @param joinName
     */
    public LYTMessage getVideoMessage(String joinName) {
        LYTMessage lytMessage = null;
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.MESSAGER_TYPE + " = ?";
            String groupBy = LYTMessageDao.CHAT_INDEX + " desc";
            Cursor cursor = db.query(joinName, null, whereClause, new String[]{LYTMessage.Type.MULTI_VIDEO.getName()}, null, null, groupBy, "0 , 1");
            while (cursor.moveToNext()) {
                String messageType = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGER_TYPE));
                int chatType = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.CHAT_TYPE));
                int os = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.OS));
                int isDestroy = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.ISDESTROY));
                int isRead = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.ISREAD));
                String msgId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.MSG_ID));
                String conversationId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.CONVERSATIONID));
                long msgTime = cursor.getLong(cursor.getColumnIndexOrThrow(LYTMessageDao.MSGTIME));
                String fromId = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.FROM_ID));
                String attr = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.ATTR));
                String to = cursor.getString(cursor.getColumnIndexOrThrow(LYTMessageDao.TARGETID));
                byte[] body = cursor.getBlob(cursor.getColumnIndexOrThrow(LYTMessageDao.MESSAGE_BODY));
                int videoState = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.VIDEO_STATE));
                LYTZMessage lytObject = new LYTZMessage();
                if (videoState == 1) {
                    lytObject.setVideoState(true);
                } else {
                    lytObject.setVideoState(false);
                }
                lytObject.setChatType(chatType);
                lytObject.setOs(os);
                lytObject.setIsRead(isRead);
                lytObject.setIsDestroy(isDestroy);
                lytObject.setMsgId(msgId);
//                    lytObject.setAppkey(appkey);
                lytObject.setConversationId(conversationId);
                lytObject.setMsgTime(msgTime);
                lytObject.setFromId(fromId);
                lytObject.setAttr(attr);
                lytObject.setTo(to);
//                    LYTZMessageBody lytzMessageBody = LYTMessage.getMessage(body, messageType);
//                    lytObject.setLytzMessageBody(lytzMessageBody);
                lytMessage = new LYTMessage(lytObject);
//                    LYTMessage lytMessage = new LYTMessage(lytObject);
                lytMessage.addBody(LYTMessage.getMessage(body, messageType));

            }
            cursor.close();
        }
        return lytMessage;
    }

    /**
     * 根据ID 查询通知表! 并返回是否有未读的通知
     *
     * @param name
     * @param id
     */
    private boolean getIsNotice(String name, String id) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTNoticeDao.NOTICE_TARGETID + " = ?" + " and " + LYTNoticeDao.NOTICE_READ_STATE + "= ?";
            Cursor cursor = db.query(name, null, whereClause, new String[]{id, "0"}, null, null, null);
            if (cursor.getCount() > 0) {
                return true;
            }
            cursor.close();
        }


        return false;

    }

    /**
     * 判断消息中是否含有@消息
     *
     * @param joinName
     */

    private boolean getIsAt(String joinName) {
        if (!isTableExist(joinName)) {
            return false;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            String whereClause = LYTMessageDao.AT_SATET + "= ?" + " and " + LYTMessageDao.MESSAGER_TYPE + " = ?";
            Cursor cursor = db.query(joinName, null, whereClause, new String[]{"0", LYTMessage.Type.AT.getName()}, null, null, null);
            if (cursor.getCount() > 0) {
                return true;
            }
            cursor.close();
        }

        return false;
    }


    public static final String makeLocalSession(String sqlName, String infoName, String sessionId, String whereSessionId) {
        if (null == whereSessionId) {
            return String.format(" %s left join  %s  WHERE  %s.%s=%s.%s", sqlName, infoName, sqlName, sessionId, infoName, sessionId);
        } else {
            return String.format(" %s  WHERE %s = '%s' ", infoName, sessionId, whereSessionId);
        }


    }


    /**
     * 查询最后一条消息的Index
     *
     * @param sqlName
     * @return
     */
    public int getLastMesaageIndex(String sqlName) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int chatIndex = 0;
        if (db.isOpen()) {
            String sql = " SELECT  " + LYTMessageDao.CHAT_INDEX + "  FROM  " + sqlName + "  ORDER BY  " + LYTMessageDao.CHAT_INDEX + "  desc  limit 0,1";
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                chatIndex = cursor.getInt(cursor.getColumnIndexOrThrow(LYTMessageDao.CHAT_INDEX));
            }
        }
        return chatIndex;
    }
}
