package com.qisn.www.socialsdk.internal.bean;


import com.qisn.www.socialsdk.base.BaseBean;

import java.util.List;

/**
 * Created by dell on 2017/5/3.
 */

public class LYTANotifications extends BaseBean {

    private String userId;
    private String title;
    private String content;
    private String attach;
    private String targetId;

    private List<LYTNotificationAttach> lytNotificationAttaches;


    public List<LYTNotificationAttach> getLytNotificationAttaches() {
        return lytNotificationAttaches;
    }

    public void setLytNotificationAttaches(List<LYTNotificationAttach> lytNotificationAttaches) {
        this.lytNotificationAttaches = lytNotificationAttaches;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }


}
