package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/1/5.
 */

public class LYTContact implements Parcelable {

    protected String username;
    protected String nick;

    protected LYTContact() {
    }

    public LYTContact(String var1) {
        this.username = var1;
    }

    public String getUsername() {
        return this.username;
    }

    public void setNick(String var1) {
        this.nick = var1;
    }

    public void setNickname(String var1) {
        this.nick = var1;
    }

    public String getNick() {
        return this.getNickname();
    }

    public String getNickname() {
        return this.nick == null?this.getUsername():this.nick;
    }

    public String toString() {
        return "<contact , username:" + this.username + ">";
    }


    private LYTContact(Parcel in) {
        this.username = in.readString();
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTContact> CREATOR = new Creator<LYTContact>() {
        @Override
        public LYTContact createFromParcel(Parcel in) {
            return new LYTContact(in);
        }

        @Override
        public LYTContact[] newArray(int size) {
            return new LYTContact[size];
        }
    };



}
