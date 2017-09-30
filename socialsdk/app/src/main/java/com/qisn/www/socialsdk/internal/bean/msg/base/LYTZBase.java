package com.qisn.www.socialsdk.internal.bean.msg.base;


/**
 * Created by Administrator on 2017/1/6.
 */

public class LYTZBase {

//
//    private int hashCode;
//
//    long handler = 0L;
//
//    public LYTZBase() {
//    }
//
//    public boolean equals(Object var1) {
//        return var1 != null && var1 instanceof LYTZBase && (this.handler == ((LYTZBase) var1).handler || this._equals((LYTZBase) var1));
//    }
//
//    public int hashCode() {
//        int var1 = this.getHashCode();
//        return var1 == 0 ? super.hashCode() : var1;
//    }
//
//    public int getHashCode() {
//        return hashCode;
//    }
//
//    native boolean _equals(EMABase var1);


    public boolean getIsDestroy(int asInt) {
        return asInt == 1 ? true : false;

    }


}
