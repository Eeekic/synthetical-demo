package com.huawei.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.dao.GoodsDao;
import com.huawei.dao.OrdersDao;
import com.huawei.dao.PayDao;
import com.huawei.dao.UserDao;
import com.huawei.projo.Goods;
import com.huawei.projo.Orders;
import com.huawei.projo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DbService {

    private static Logger log = Logger.getLogger(DbService.class);

    @Resource
    private UserDao userDao;
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private OrdersDao ordersDao;
    @Resource
    private PayDao payDao;

    public String querySimpleUserInfoByName(String userName){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            User user = userDao.querySimpleUserInfoByName(userName);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(user.toSimpleJson());
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject = processExceptionWithJsonArray(e);
        }
        return jsonObject.toJSONString();
    }

    public String queryUserDetailInfoById(long userId){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            User user = userDao.queryUserDetailInfoById(userId);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(user.toDetailJson());
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject = processExceptionWithJsonArray(e);
        }
        return jsonObject.toJSONString();
    }

    public String addUser(User user){
        JSONObject jsonObject = new JSONObject();
        try {
            userDao.addUser(user);
            long userId=userDao.queryUserId(user.getUserName());
            user.setUserId(userId);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(user.toSimpleJson2());
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String queryGoodsDetail(long goodsId){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            Goods goods = goodsDao.queryGoodsDetail(goodsId);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(goods.toJson());
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject = processExceptionWithJsonArray(e);
        }
        return jsonObject.toJSONString();
    }

    public String queryGoodsList(String goodsType){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            List<Goods> goodsList = goodsDao.queryGoodsList(goodsType);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            for (Goods goods:goodsList) {
                jsonArray.add(goods.toJson());
            }
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject = processExceptionWithJsonArray(e);
        }

        return jsonObject.toJSONString();
    }

    public String queryOrdersList(long userId){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            List<Orders> ordersList = ordersDao.queryOrdersList(userId);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            for (Orders orders:ordersList) {
                jsonArray.add(orders.toJson());
            }
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject = processExceptionWithJsonArray(e);
        }
        return jsonObject.toJSONString();
    }


    public String pay(long userId,long goodsId,int goodsPrice){
        JSONObject jsonObject = new JSONObject();
        try{
            String result = payDao.pay(userId,goodsId,goodsPrice);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("resMsg",result);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String queryGoodsCount(long goodsId){
        JSONObject jsonObject = new JSONObject();
        try{
            int result = goodsDao.queryGoodsCount(goodsId);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            JSONObject countJson = new JSONObject();
            countJson.put("goodsCount",result);
            jsonArray.add(countJson);
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String queryUserBalance(long userId){
        JSONObject jsonObject = new JSONObject();
        try{
            int result = userDao.queryUserBalance(userId);
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            JSONObject balanceJson = new JSONObject();
            balanceJson.put("balance",result);
            jsonArray.add(balanceJson);
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    private static String getExceptionInfo(Exception e){
        log.error(e);
        return e.getCause() + ":" + e.getMessage();
    }

    private JSONObject processExceptionWithJsonArray(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errCode",CommonUtils.ERROR_CODE);
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonTmp = new JSONObject();
        jsonTmp.put("errMsg",getExceptionInfo(e));

        jsonArray.add(jsonTmp);

        jsonObject.put("resMsg",jsonArray);
        return jsonObject;
    }

}
