package com.huawei.configbean;

public class DbServiceConfigBean {
    private String hostAndPort;
    private String querySimpleUserInfoMethod;
    private String queryUserDetailInfoByIdMethod;
    private String addUserMethod;
    private String queryGoodsDetailMethod;
    private String queryGoodsListMethod;
    private String queryOrdersListMethod;
    private String payMethod;

    public void setHostAndPort(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public void setQuerySimpleUserInfoMethod(String querySimpleUserInfoMethod) {
        this.querySimpleUserInfoMethod = querySimpleUserInfoMethod;
    }

    public void setQueryUserDetailInfoByIdMethod(String queryUserDetailInfoByIdMethod) {
        this.queryUserDetailInfoByIdMethod = queryUserDetailInfoByIdMethod;
    }

    public void setAddUserMethod(String addUserMethod) {
        this.addUserMethod = addUserMethod;
    }

    public void setQueryGoodsDetailMethod(String queryGoodsDetailMethod) {
        this.queryGoodsDetailMethod = queryGoodsDetailMethod;
    }

    public void setQueryGoodsListMethod(String queryGoodsListMethod) {
        this.queryGoodsListMethod = queryGoodsListMethod;
    }

    public void setQueryOrdersListMethod(String queryOrdersListMethod) {
        this.queryOrdersListMethod = queryOrdersListMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }


    public String querySimpleUserInfoUrl() {
        return hostAndPort + "/" + querySimpleUserInfoMethod;
    }

    public String queryUserDetailInfoByIdUrl() {
        return hostAndPort + "/" + queryUserDetailInfoByIdMethod;
    }

    public String getAddUserUrl() {
        return hostAndPort + "/" + addUserMethod;
    }

    public String getQueryGoodsDetailUrl(String goodsId) {
        return hostAndPort + "/" + queryGoodsDetailMethod + "?goodsId=" + goodsId;
    }

    public String getQueryGoodsListUrl(String goodsType) {
        return hostAndPort + "/" + queryGoodsListMethod + "?goodsType=" + goodsType;
    }

    public String getQueryOrdersListUrl() {
        return hostAndPort + "/" + queryOrdersListMethod;
    }

    public String getPayUrl() {
        return hostAndPort + "/" + payMethod;
    }
}
