package com.qisn.www.socialsdk.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/5/8.
 */

public class GsonUtils {

    //将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }


    public static <T> List<T> jsonToArrayList(String json, Class<T> clazz)
    {
        ArrayList<T> arrayList = null;
        try {
            Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
            List<JsonObject> jsonObjects = new Gson().fromJson(json, type);

            arrayList = new ArrayList<>();
            for (JsonObject jsonObject : jsonObjects)
            {
                arrayList.add(new Gson().fromJson(jsonObject, clazz));
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return arrayList;
    }



    public static String toJsonString(Object clazz) {
        Gson gson = new Gson();
        return gson.toJson(clazz);
    }
    public   static ParameterizedType type(final Class raw, final Type... args) {
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



    public static ArrayList<String> jsonArraytoJson(String asString) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(asString).getAsJsonArray();
        ArrayList<String> lcs = new ArrayList<String>();
        for(JsonElement obj : Jarray ){
            String cse = gson.fromJson( obj , String.class);
            lcs.add(cse);
        }

        return  lcs;
    }
}
