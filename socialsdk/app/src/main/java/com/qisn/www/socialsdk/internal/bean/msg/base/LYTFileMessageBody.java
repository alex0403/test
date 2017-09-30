package com.qisn.www.socialsdk.internal.bean.msg.base;

import com.qisn.www.socialsdk.internal.bean.msg.LYTZFileMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZImageMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZNormalFileMessageBody;
import com.qisn.www.socialsdk.internal.bean.msg.LYTZVoiceMessageBody;

/**
 * Created by Administrator on 2017/1/7.
 */

public abstract class LYTFileMessageBody extends LYTMessageBody {

    private String localUrl;

    private String remoteUrl;



    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public LYTFileMessageBody(String var1) {
        this.lytObject = new LYTZFileMessageBody(var1);
    }


    public LYTFileMessageBody(LYTZFileMessageBody body) {
        this.lytObject = body;

    }

    public LYTFileMessageBody(String path, int type) {
        switch (type) {
            case 1://图片
                this.lytObject = new LYTZImageMessageBody(path);
                break;
            case 2://视频
//                this.lytObject = new EMAVideoMessageBody(var1, "");
            case 3:
            default:
                break;
            case 4://语音
                this.lytObject = new LYTZVoiceMessageBody(path);
                break;
            case 5://文件
                this.lytObject = new LYTZNormalFileMessageBody(path);
        }

    }

    public String getFileName() {
        return ((LYTZFileMessageBody) this.lytObject).getDisplayName();
    }

    public void setFileName(String var1) {
        ((LYTZFileMessageBody) this.lytObject).setDisplayName(var1);
    }


    public void setName(String var1) {
        ((LYTZNormalFileMessageBody) this.lytObject).setFileName(var1);
    }

    public String getLocalUrl() {
        return ((LYTZFileMessageBody) this.lytObject).getLocalUrl();
    }

    public void setLocalUrl(String localUrl) {
        ((LYTZFileMessageBody) this.lytObject).setLocalPath(localUrl);
    }


}
