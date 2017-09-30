package com.qisn.www.socialsdk.internal.bean.msg;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class LYTOfflineMsg {


    /**
     * list : [{"chatIndex":101,"sessionId":"G146650210593901"},{"chatIndex":102,"sessionId":"G146650210593902"},{"chatIndex":103,"sessionId":"G146650210593903"}]
     * os : 1
     * userId : P146650175733701
     */

    private int os;
    private String userId;
    /**
     * chatIndex : 101
     * sessionId : G146650210593901
     */

    private List<ListBean> list;

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private long chatIndex;
        private String sessionId;

        public long getChatIndex() {
            return chatIndex;
        }

        public void setChatIndex(long chatIndex) {
            this.chatIndex = chatIndex;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}
