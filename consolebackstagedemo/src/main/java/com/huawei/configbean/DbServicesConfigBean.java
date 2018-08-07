package com.huawei.configbean;

public class DbServicesConfigBean {
    private String hostAndPort;
    private String addTestUserMethod;
    private String queryTestUserMethod;
    private String cleanTestUserMethod;
    private String rushToBuySuccessUserMethod;
    private String rushToBuySuccessCountMethod;
    private String queryTestUserCountMethod;
    private String queryGoodsListMethod;

    public void setHostAndPort(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public String getAddTestUserMethodUrl(int count) {
        return hostAndPort + "/" + addTestUserMethod + "?count=" + count;
    }

    public void setAddTestUserMethod(String addTestUserMethod) {
        this.addTestUserMethod = addTestUserMethod;
    }

    public String getQueryTestUserMethodUrl() {
        return hostAndPort + "/" + queryTestUserMethod;
    }

    public void setQueryTestUserMethod(String queryTestUserMethod) {
        this.queryTestUserMethod = queryTestUserMethod;
    }

    public String getCleanTestUserMethodUrl() {
        return hostAndPort + "/" + cleanTestUserMethod;
    }

    public void setCleanTestUserMethod(String cleanTestUserMethod) {
        this.cleanTestUserMethod = cleanTestUserMethod;
    }

    public String getRushToBuySuccessUserMethodUrl() {
        return hostAndPort + "/" + rushToBuySuccessUserMethod;
    }

    public void setRushToBuySuccessUserMethod(String rushToBuySuccessUserMethod) {
        this.rushToBuySuccessUserMethod = rushToBuySuccessUserMethod;
    }

    public String getRushToBuySuccessCountMethodUrl() {
        return hostAndPort + "/" + rushToBuySuccessCountMethod;
    }

    public void setRushToBuySuccessCountMethod(String rushToBuySuccessCountMethod) {
        this.rushToBuySuccessCountMethod = rushToBuySuccessCountMethod;
    }

    public String getQueryTestUserCountMethodUrl() {
        return hostAndPort + "/" + queryTestUserCountMethod;
    }

    public void setQueryTestUserCountMethod(String queryTestUserCountMethod) {
        this.queryTestUserCountMethod = queryTestUserCountMethod;
    }

    public String getQueryGoodsListMethodUrl(String goodsType) {
        return hostAndPort + "/" + queryGoodsListMethod + "?goodsType=" + goodsType;
    }

    public void setQueryGoodsListMethod(String queryGoodsListMethod) {
        this.queryGoodsListMethod = queryGoodsListMethod;
    }
}
