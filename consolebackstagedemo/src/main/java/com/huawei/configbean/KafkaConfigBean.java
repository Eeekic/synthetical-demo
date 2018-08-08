package com.huawei.configbean;

public class KafkaConfigBean {

    private String endPointUrl;
    private String region;
    private String ak;
    private String sk;
    private String projectId;
    private String queueId;
    private String groupId;
    private String serviceName;
    private String msgLimit;

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public void setEndPointUrl(String endPointUrl) {
        if(endPointUrl.endsWith("/"))
            this.endPointUrl = endPointUrl+"v1.0/";
        else
            this.endPointUrl += "/v1.0/";
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMsgLimit() {
        return msgLimit;
    }

    public void setMsgLimit(String msgLimit) {
        this.msgLimit = msgLimit;
    }
}
