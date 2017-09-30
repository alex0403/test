package com.qisn.www.socialsdk.config;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MqttConfig {


    /**
     * userName : value
     * password : value
     * clientId : value
     * host : value
     * port : value
     * fileUploadUrl : value
     * antMessageUrl : value
     * topicIds : []
     */

    private String userName;
    private String password;
    private String clientId;
    private String host;
    private String port;

    private String fileUploadUrl;
    private String antMessageUrl;
    private List<String> topicIds;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getFileUploadUrl() {
        return fileUploadUrl;
    }

    public void setFileUploadUrl(String fileUploadUrl) {
        this.fileUploadUrl = fileUploadUrl;
    }

    public String getAntMessageUrl() {
        return antMessageUrl;
    }

    public void setAntMessageUrl(String antMessageUrl) {
        this.antMessageUrl = antMessageUrl;
    }

    public List<String> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<String> topicIds) {
        this.topicIds = topicIds;
    }


}
