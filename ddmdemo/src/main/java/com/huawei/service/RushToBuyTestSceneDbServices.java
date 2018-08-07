package com.huawei.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.dao.GoodsDao;
import com.huawei.dao.OrdersDao;
import com.huawei.dao.PayDao;
import com.huawei.dao.UserDao;
import com.huawei.projo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RushToBuyTestSceneDbServices {
    private static Logger log = Logger.getLogger(DbServices.class);

    @Resource
    private UserDao userDao;

    @Resource
    private OrdersDao ordersDao;

    public String addTestUser(int count){
        JSONObject jsonObject = new JSONObject();
        try {
            int result = 0;
            for(int i=0;i<count;i++) {
                result += userDao.addTestUser("test" + System.currentTimeMillis() + i,"123",CommonUtils.USER_TEST_TYPE);
            }
            jsonObject.put("errCode",CommonUtils.NORMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            JSONObject resultJson = new JSONObject();
            resultJson.put("testUserCount",result);
            jsonArray.add(resultJson);
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",ExceptionProcess.getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String queryTestUser(){
        JSONObject jsonObject = new JSONObject();
        try {
            List<User> userList = userDao.queryTestUser(CommonUtils.USER_TEST_TYPE);
            jsonObject.put("errCode",CommonUtils.NORMAL_CODE);
            jsonObject.put("resMsg",User.toJsonArray(userList));
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",ExceptionProcess.getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String queryTestUserCount(){
        JSONObject jsonObject = new JSONObject();
        try {
            int result = userDao.queryTestUserCount(CommonUtils.USER_TEST_TYPE);
            jsonObject.put("errCode",CommonUtils.NORMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            JSONObject resultJson = new JSONObject();
            resultJson.put("testUserCount",result);
            jsonArray.add(resultJson);
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",ExceptionProcess.getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String cleanTestUser(){
        JSONObject jsonObject = new JSONObject();
        try {
            int result = userDao.cleanTestUser(CommonUtils.USER_TEST_TYPE);
            jsonObject.put("errCode",CommonUtils.NORMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            JSONObject resultJson = new JSONObject();
            resultJson.put("cleanTestUserCount",result);
            jsonArray.add(resultJson);
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",ExceptionProcess.getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }

    public String rushToBuySuccessCount(){
        JSONObject jsonObject = new JSONObject();
        try {
            int result = ordersDao.rushToBuySuccessCount(CommonUtils.USER_TEST_TYPE,CommonUtils.DEFAULT_VALUE);
            jsonObject.put("errCode",CommonUtils.NORMAL_CODE);
            JSONArray jsonArray = new JSONArray();
            JSONObject resultJson = new JSONObject();
            resultJson.put("rushToBuySuccessCount",result);
            jsonArray.add(resultJson);
            jsonObject.put("resMsg",jsonArray);
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",ExceptionProcess.getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }
    public String rushToBuySuccessUser(){
        JSONObject jsonObject = new JSONObject();
        try {
            List<User> userList = ordersDao.rushToBuySuccessUser(CommonUtils.USER_TEST_TYPE,CommonUtils.DEFAULT_VALUE);
            jsonObject.put("errCode",CommonUtils.NORMAL_CODE);
            jsonObject.put("resMsg",User.toJsonArray(userList));
        }catch (Exception e){
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            jsonObject.put("resMsg",ExceptionProcess.getExceptionInfo(e));
        }
        return jsonObject.toJSONString();
    }
}
