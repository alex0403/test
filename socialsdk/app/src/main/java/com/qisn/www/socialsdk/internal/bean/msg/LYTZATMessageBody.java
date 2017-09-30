package com.qisn.www.socialsdk.internal.bean.msg;


import com.google.gson.Gson;
import com.qisn.www.socialsdk.internal.bean.msg.base.LYTZMessageBody;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dell on 2017/6/27.
 */

public class LYTZATMessageBody extends LYTZMessageBody implements Serializable {


    private List<LYTATMessageInfo> lytatMessageInfos;



    public LYTZATMessageBody() {
    }



    public <T> String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(LYTZATMessageBody.class, clazz);
        return gson.toJson(this, objectType);
    }

    public  static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public LYTZATMessageBody(List<LYTATMessageInfo> lytatMessageInfos) {
        this.lytatMessageInfos=lytatMessageInfos;

    }

    @Override
    public String getMessageType() {

        return LYTMessage.Type.AT.getName();
    }




    public List<LYTATMessageInfo> getLytatMessageInfos() {
        return lytatMessageInfos;
    }

    public void setLytatMessageInfos(List<LYTATMessageInfo> lytatMessageInfos) {
        this.lytatMessageInfos = lytatMessageInfos;
    }


}
