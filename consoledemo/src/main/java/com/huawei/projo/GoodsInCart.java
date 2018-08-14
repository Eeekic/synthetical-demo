package com.huawei.projo;

import com.alibaba.fastjson.JSONObject;

public class GoodsInCart {
    long goodsId;
    long userId;
    String goodsName;
    String goodsPicturePath;
    long pendingPaymentId;
    int goodsPrice;

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPicturePath() {
        return goodsPicturePath;
    }

    public void setGoodsPicturePath(String goodsPicturePath) {
        this.goodsPicturePath = goodsPicturePath;
    }

    public long getPendingPaymentId() {
        return pendingPaymentId;
    }

    public void setPendingPaymentId(long pendingPaymentId) {
        this.pendingPaymentId = pendingPaymentId;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    @Override
    public String toString() {
        return toJson().toJSONString();
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsId",goodsId);
        jsonObject.put("goodsName",goodsName);
        jsonObject.put("goodsPrice",goodsPrice);
        jsonObject.put("goodsPicturePath",goodsPicturePath);
        jsonObject.put("pendingPaymentId",pendingPaymentId);
        jsonObject.put("userId",userId);
        return jsonObject;
    }

    public static GoodsInCart jsonToGoodsInCart(JSONObject jsonObject){
        GoodsInCart goodsInCart=new GoodsInCart();
        goodsInCart.setGoodsId(jsonObject.getLong("goodsId"));
        goodsInCart.setGoodsName(jsonObject.getString("goodsName"));
        goodsInCart.setGoodsPicturePath(jsonObject.getString("goodsPicturePath"));
        goodsInCart.setGoodsPrice(jsonObject.getInteger("goodsPrice"));
        goodsInCart.setPendingPaymentId(jsonObject.getLong("pendingPaymentId"));
        goodsInCart.setUserId(jsonObject.getLong("userId"));
        return goodsInCart;
    }


}
