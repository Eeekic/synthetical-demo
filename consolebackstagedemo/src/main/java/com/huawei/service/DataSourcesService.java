package com.huawei.service;

import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.Utils.JSONAnalysis;
import com.huawei.configbean.DbServicesConfigBean;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.controller.ViewController;
import com.huawei.runnable.PrepareTestUserRunnable;
import com.huawei.tools.PrePareRushToBuyTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("dataSourcesService")
public class DataSourcesService {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private DbServicesConfigBean dbServicesConfigBean;

    @Autowired
    private PrepareTestUserRunnable prepareTestUserRunnable;

    public void commitPrepareTestUser(int count){
        cleanTestUser();
        prepareTestUserRunnable.setCount(count);
        PrePareRushToBuyTools.execute(prepareTestUserRunnable);
    }

    public int queryTestUserCount(){
        String url = dbServicesConfigBean.getQueryTestUserCountMethodUrl();
        JSONObject resultJson = httpClientService.getDataFromManagerServices(url,HttpClientService.GET_Method_TYPE);
        return JSONAnalysis.analysisForObjectCount(resultJson,"testUserCount");
    }

    public String queryRushToBuyGoodsDetail(){
        String result = "";
        String url = dbServicesConfigBean.getQueryGoodsListMethodUrl(CommonUtils.GOODS_TYPE_RUSH_TO_BUY);
        JSONObject resultJson = httpClientService.getDataFromManagerServices(url,HttpClientService.GET_Method_TYPE);
        JSONObject jsonObject = JSONAnalysis.analysisObjectJson(resultJson);
        if (jsonObject != null){
            jsonObject.put("goodsType",CommonUtils.GOODS_TYPE_RUSH_TO_BUY);
            result = jsonObject.toJSONString();
        }
        return result;
    }

    public int cleanTestUser(){
        String url = dbServicesConfigBean.getCleanTestUserMethodUrl();
        JSONObject resultJson = httpClientService.getDataFromManagerServices(url,HttpClientService.GET_Method_TYPE);
        return JSONAnalysis.analysisForObjectCount(resultJson,"cleanTestUserCount");
    }

    public String testUserIdRange(){
        String url = dbServicesConfigBean.getQueryTestUserIdRangeMethodUrl();
        JSONObject resultJson = httpClientService.getDataFromManagerServices(url,HttpClientService.GET_Method_TYPE);
        return JSONAnalysis.analysisObjectString(resultJson,"testUserIdRange");
    }



    public String pay(String url,Map<String,Object> urlMap){

        long startTime = System.currentTimeMillis();
        JSONObject responseJson = httpClientService.getDataFromManagerServices(url,urlMap,HttpClientService.POST_Method_TYPE);
        long endTime = System.currentTimeMillis();

        JSONObject resultJson = new JSONObject();
        resultJson.put("delay",endTime - startTime);
        resultJson.put("rushToBuyResult",responseJson);
        System.out.println(resultJson.toJSONString());
        return resultJson.toJSONString();
    }
}
