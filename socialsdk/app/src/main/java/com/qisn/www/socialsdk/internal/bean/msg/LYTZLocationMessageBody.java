package com.qisn.www.socialsdk.internal.bean.msg;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTZLocationMessageBody extends LYTZMessageBody {


    public double latitude;
    public double longitude;
    public String address;

    @Override
    public String getMessageType() {
        return LYTMessage.Type.LOCATION.getName();
    }

    public LYTZLocationMessageBody(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
