package com.huawei.configbean;

public class ManagerServicesConfigBean {
    private String managerServicesUrl;
    private String signInMethod;
    private String signUpMethod;
    private String userDetailMethod;
    private String goodsListMethod;
    private String goodsDetailMethod;
    private String payMethod;
    private String orderListMethod;
    private String endPointurl;
    private String region;
    private String ak;
    private String sk;
    private String queueId;
    private String groupId;
    private String projectId;
    private String msgLimit;
    private String serviceName;

    public String getSignInMethod() {
        return signInMethod;
    }

    public String getEndPointurl() {
        return endPointurl;
    }

    public void setEndPointurl(String endPointurl) {
        if(endPointurl.endsWith("/"))
            this.endPointurl = endPointurl+"v1.0/";
        else
            this.endPointurl+="/v1.0/";
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMsgLimit() {
        return msgLimit;
    }

    public void setMsgLimit(String msgLimit) {
        this.msgLimit = msgLimit;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getManagerServicesUrl() {
        return managerServicesUrl;
    }

    public void setManagerServicesUrl(String managerServicesUrl) {
        this.managerServicesUrl = managerServicesUrl;
    }

    public String getSignInMethodUrl() {
        return managerServicesUrl + "/" + signInMethod;
    }

    public void setSignInMethod(String signInMethod) {
        this.signInMethod = signInMethod;
    }

    public String getSignUpMethodUrl() {
        return managerServicesUrl + "/" + signUpMethod;
    }

    public void setSignUpMethod(String signUpMethod) {
        this.signUpMethod = signUpMethod;
    }

    public String getUserDetailMethodUrl() {
        return managerServicesUrl + "/" + userDetailMethod;
    }

    public void setUserDetailMethod(String userDetailMethod) {
        this.userDetailMethod = userDetailMethod;
    }

    public String getGoodsListMethodUrl(String goodsType) {
        return managerServicesUrl + "/" + goodsListMethod + "?goodsType=" +goodsType;
    }

    public void setGoodsListMethod(String goodsListMethod) {
        this.goodsListMethod = goodsListMethod;
    }

    public String getGoodsDetailMethodUrl(String goodsId,String goodsType) {
        return managerServicesUrl + "/" + goodsDetailMethod + "?goodsId=" +goodsId + "&goodsType=" + goodsType;
    }

    public void setGoodsDetailMethod(String goodsDetailMethod) {
        this.goodsDetailMethod = goodsDetailMethod;
    }

    public String getPayMethodUrl() {
        return managerServicesUrl + "/" + payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderListMethodUrl() {
        return managerServicesUrl + "/" + orderListMethod;
    }

    public void setOrderListMethod(String orderListMethod) {
        this.orderListMethod = orderListMethod;
    }
}
