package com.qisn.www.socialsdk.internal.converter;

import android.content.Context;

import com.qisn.www.socialsdk.base.BaseConverter;
import com.qisn.www.socialsdk.config.HttpConfig;

/**
 * Created by hhly-pc on 2017/9/27.
 */

public class TokenGetConverter extends BaseConverter {
    private TokenGetConfig config;
    private Context mContext;

    public TokenGetConverter(Context context){
        mContext = context;
    }

    @Override
    public void enquene(){

    }



    class TokenGetConfig extends HttpConfig{

    }
}
