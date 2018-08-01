package com.huawei.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.configbean.DbServiceConfigBean;
import com.huawei.manager.ConsumerManager;
import com.huawei.manager.RedisCacheManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManagerService {

    private static Logger log = Logger.getLogger(ManagerService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DbServiceConfigBean dbServiceConfigBean;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private ConsumerManager consumerManager;

    private final static String POST_Method_TYPE = "Post";
    private final static String GET_Method_TYPE = "Get";

    private final static String GOODS_DETAIL = "GoodsDetail";


    public String signIn(Map<String, Object> urlVariables){
        String url = dbServiceConfigBean.querySimpleUserInfoUrl();
        JSONObject resultJson;
        try {
            Map<String,Object> dbUrlVariables = new HashMap<>();
            dbUrlVariables.put("userName",urlVariables.get("userName"));
            resultJson = getDataFromDbService( url, dbUrlVariables,"SignIn",POST_Method_TYPE);

            String userPwd = urlVariables.get("userPwd").toString();
            String dbUserPwd = resultJson.getJSONArray("resMsg").getJSONObject(0).getString("userPwd");

            if(dbUserPwd.equals(userPwd)){
                resultJson.getJSONArray("resMsg").getJSONObject(0).put("sun","peng");
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = resultJson.getJSONArray("resMsg").getJSONObject(0);
                jsonObject.remove("userPwd");
                jsonArray.add(jsonObject);
                resultJson.put("resMsg",jsonArray);
                resultJson.put("signInResult",CommonUtils.SING_IN_SUCCESS);
            }else{
                resultJson.put("resMsg","[]");
                resultJson.put("signInResult",CommonUtils.SING_IN_FAILED);
            }
        }catch (Exception e){
            resultJson = processException(e);
        }
        return resultJson.toJSONString();
    }

    public String userDetail(Map<String, Object> urlVariables){
        String url = dbServiceConfigBean.queryUserDetailInfoByIdUrl();
        JSONObject jsonObject;
        try {
            jsonObject = getDataFromDbService( url, urlVariables,"UserDetail",POST_Method_TYPE);
        }catch (Exception e){
            jsonObject = processException(e);
        }
        return jsonObject.toJSONString();
    }

    public String signUp(Map<String, Object> urlVariables){
        String url = dbServiceConfigBean.getAddUserUrl();
        JSONObject jsonObject;
        try {
            jsonObject = getDataFromDbService( url, urlVariables,"SignUp",POST_Method_TYPE);
        }catch (Exception e){
            jsonObject = processException(e);
        }
        return jsonObject.toJSONString();
    }


    public String goodsList(String goodsType){
        String url = dbServiceConfigBean.getQueryGoodsListUrl(goodsType);
        JSONObject jsonObject;
        try {
            jsonObject = getDataFromDbService( url, "GoodsList",GET_Method_TYPE);
        }catch (Exception e){
            jsonObject = processException(e);
        }
        return jsonObject.toJSONString();
    }

    public String goodsDetail(String goodsId,String goodsType){
        String url = dbServiceConfigBean.getQueryGoodsDetailUrl(goodsId);
        JSONObject jsonObject;
        try {
            jsonObject = getDataWithRedis( url,"GoodsDetail",GET_Method_TYPE,goodsId,goodsType);
        }catch (Exception e){
            jsonObject = processException(e);
        }
        return jsonObject.toJSONString();
    }

    public String pay( Map<String, Object> urlVariables){
        String goodsType = urlVariables.get("goodsType").toString();
        int rushToBuyToken = -1;
        JSONObject jsonObject;

        if(goodsType.equals(CommonUtils.RUSH_TO_BUY)) {
            rushToBuyToken = consumerManager.consumeMsg(500);
        }
        if(rushToBuyToken == 0){
            jsonObject = new JSONObject();
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("resMsg",CommonUtils.RUSH_TO_BUY_FAILED);
        }else {
            String url = dbServiceConfigBean.getPayUrl();
            try {
                jsonObject = getDataFromDbService(url, urlVariables, "Pay", POST_Method_TYPE);
            } catch (Exception e) {
                jsonObject = processException(e);
            }
        }
        return jsonObject.toJSONString();
    }


    public String orderList( Map<String, Object> urlVariables){
        String url = dbServiceConfigBean.getQueryOrdersListUrl();
        JSONObject jsonObject;
        try {
            jsonObject = getDataFromDbService( url, urlVariables,"OrderList",POST_Method_TYPE);
        }catch (Exception e){
            jsonObject = processException(e);
        }
        return jsonObject.toJSONString();
    }

    private JSONObject getDataFromDbService(String url,String method,String methodType){
        return getDataFromDbService( url, null,method,methodType);
    }

    private JSONObject getDataFromDbService(String url,Map<String, Object> urlVariables,String method,String methodType){
        JSONObject jsonObject =new JSONObject();
        JSONObject result = null;
        if (methodType.equals(POST_Method_TYPE)) {
            result = restTemplate.postForObject(url, urlVariables, JSONObject.class);
        }else {
            result = restTemplate.getForObject(url, JSONObject.class);
        }
        if(result !=null && result.get("errCode") != null && result.get("errCode").equals(CommonUtils.DB_NOMAL_CODE)){
            jsonObject.put("errCode",CommonUtils.NOMAL_CODE);
            jsonObject.put("resMsg",result.get("resMsg"));
        }else {
            jsonObject.put("errCode",CommonUtils.ERROR_CODE);
            if(result != null) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(result);
                jsonObject.put("resMsg", jsonArray);
            }else{
                jsonObject.put("resMsg", method + ":Database service return is null!");
            }
        }
        return jsonObject;
    }

    private JSONObject getDataWithRedis(String url,String method,String methodType,String key,String goodsType){
        JSONObject result = null;
        if(!goodsType.equals(CommonUtils.RUSH_TO_BUY)) {
            switch (method) {
                case GOODS_DETAIL:
                    result = getJSONObjectFromRedis(key);
                    break;
            }
        }
        if(result == null) {
            result = getDataFromDbService(url,method,methodType);
            if(!goodsType.equals(CommonUtils.RUSH_TO_BUY)) {
                if(result.getString("errCode").equals(CommonUtils.NOMAL_CODE)) {
                    try {
                        redisCacheManager.set(key, result);
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }
        return result;
    }

    private JSONObject getJSONObjectFromRedis(String key){
        JSONObject jsonObject = null;
        try {
            Object value = redisCacheManager.get(key);
            if (value != null) {
                jsonObject = JSONObject.parseObject(value.toString());
            }
        }catch (Exception e){
            log.error(e);
        }
        return jsonObject;
    }

    private JSONObject processException(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errCode",CommonUtils.ERROR_CODE);
        jsonObject.put("resMsg",getExceptionInfo(e));
        return jsonObject;
    }

    private static String getExceptionInfo(Exception e){
        log.error(e);
        return e.getCause() + ":" + e.getMessage();
    }

}
