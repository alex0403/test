package com.qisn.www.socialsdk.internal.bean;

import com.qisn.www.socialsdk.base.BaseBean;

import java.util.List;

/**
 * Created by dell on 2017/9/21.
 */

public class LYTAGroup extends BaseBean {


    /**
     * groupId : G150587512801471
     * members : ["1001100000000005898"]
     */

    private String groupId;
    private List<String> members;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
