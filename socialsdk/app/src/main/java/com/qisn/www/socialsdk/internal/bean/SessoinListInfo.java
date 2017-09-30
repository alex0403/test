package com.qisn.www.socialsdk.internal.bean;

import java.util.List;

/**
 * Created by dell on 2017/9/25.
 */

public class SessoinListInfo {

    private List<LYTSessoinList> lytSessoinLists;
    private long count;


    public List<LYTSessoinList> getLytSessoinLists() {
        return lytSessoinLists;
    }

    public void setLytSessoinLists(List<LYTSessoinList> lytSessoinLists) {
        this.lytSessoinLists = lytSessoinLists;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
