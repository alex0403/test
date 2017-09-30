package com.qisn.www.socialsdk.internal.bean.msg;

/**
 * Created by Administrator on 2017/1/7.
 */
public class LYTZVoiceMessageBody extends LYTZFileMessageBody {

    private int duration;

    private String audioUrl;

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @Override
    public String getMessageType() {
        return LYTMessage.Type.VOICE.getName();
    }

    public LYTZVoiceMessageBody(String localPath) {
        super(localPath);
    }

    public LYTZVoiceMessageBody(String localPath, String displayName) {
        super(localPath, displayName);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "{" + "\"duration :\" " + duration +
                ", \"audioUrl :\" '" + audioUrl + '\'' + '}';
    }
}
