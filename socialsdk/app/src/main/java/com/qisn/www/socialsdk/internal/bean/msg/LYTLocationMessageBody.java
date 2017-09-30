package com.qisn.www.socialsdk.internal.bean.msg;

import android.os.Parcel;
import android.os.Parcelable;

import com.qisn.www.socialsdk.internal.bean.msg.base.LYTMessageBody;

/**
 * Created by Administrator on 2017/1/7.
 */

public class LYTLocationMessageBody extends LYTMessageBody implements Parcelable {

    protected LYTLocationMessageBody(Parcel in) {
        this.lytObject = new LYTZLocationMessageBody(0.0D, 0.0D, "");
        String address = in.readString();
        double latitude = in.readDouble();
        double longitude = in.readDouble();
        ((LYTZLocationMessageBody) this.lytObject).setAddress(address);
        ((LYTZLocationMessageBody) this.lytObject).setLatitude(latitude);
        ((LYTZLocationMessageBody) this.lytObject).setLongitude(longitude);
    }

    public LYTLocationMessageBody(double latitude, double longitude, String locationAddress) {
        this.lytObject = new LYTZLocationMessageBody(latitude, longitude, locationAddress);


    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(((LYTZLocationMessageBody) this.lytObject).getAddress());
        dest.writeDouble(((LYTZLocationMessageBody) this.lytObject).getLatitude());
        dest.writeDouble(((LYTZLocationMessageBody) this.lytObject).getLongitude());
    }


    public String toString() {
        return "location:" + ((LYTZLocationMessageBody) this.lytObject).getAddress() + ",lat:" + ((LYTZLocationMessageBody) this.lytObject).getLatitude() + "" + ",lng:" + ((LYTZLocationMessageBody) this.lytObject).getLongitude();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LYTLocationMessageBody> CREATOR = new Creator<LYTLocationMessageBody>() {
        @Override
        public LYTLocationMessageBody createFromParcel(Parcel in) {
            return new LYTLocationMessageBody(in);
        }

        @Override
        public LYTLocationMessageBody[] newArray(int size) {
            return new LYTLocationMessageBody[size];
        }
    };
}
