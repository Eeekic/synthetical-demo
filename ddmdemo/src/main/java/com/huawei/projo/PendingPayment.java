package com.huawei.projo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PendingPayment {

    private static Logger log = Logger.getLogger(PendingPayment.class);

    private long pendingPaymentId;
    private boolean isPayed;
    private String token;
    private long userId;
    private long goodsId;
    private String goodsName;
    private int goodsPrice;
    private String goodsPicturePath;

    public long getPendingPaymentId() {
        return pendingPaymentId;
    }

    public void setPendingPaymentId(long pendingPaymentId) {
        this.pendingPaymentId = pendingPaymentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPicturePath() {
        return goodsPicturePath;
    }

    public void setGoodsPicturePath(String goodsPicturePath) {
        this.goodsPicturePath = goodsPicturePath;
    }

    @Override
    public String toString(){
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("pendingPaymentId",pendingPaymentId);
        jsonObject.put("userId",userId);
        jsonObject.put("goodsId",goodsId);
        jsonObject.put("goodsName",goodsName);
        jsonObject.put("goodsPrice",goodsPrice);
        jsonObject.put("goodsPicturePath",goodsPicturePath);

        return jsonObject.toJSONString();
    }

    public static List<PendingPayment> jsonArrayToList(String pendingPaymentJsonArrayStr){
        List<PendingPayment> pendingPaymentList = new ArrayList<>();
        try {
            JSONArray jsonArray = JSONArray.parseArray(pendingPaymentJsonArrayStr);
            for(int i = 0;i < jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PendingPayment pendingPayment =new PendingPayment();
                pendingPayment.setUserId(jsonObject.getLong("userId"));
                pendingPayment.setGoodsId(jsonObject.getLong("goodsId"));
                pendingPayment.setToken(jsonObject.getString("token"));
                pendingPaymentList.add(pendingPayment);
            }
        }catch (Exception e){
            log.error(e);
            e.printStackTrace();
        }
        return pendingPaymentList;
    }

}
