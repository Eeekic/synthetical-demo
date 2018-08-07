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

@Service("dataSourcesService")
public class DataSourcesService {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private DbServicesConfigBean dbServicesConfigBean;

    @Autowired
    private PrepareTestUserRunnable prepareTestUserRunnable;

    public void commitPrepareTestUser(int count){
        prepareTestUserRunnable.setCount(count);
        PrePareRushToBuyTools.execute(prepareTestUserRunnable);
    }

    public int queryTestUserCount(){
        String url = dbServicesConfigBean.getQueryTestUserCountMethodUrl();
        JSONObject resultJson = httpClientService.getDataFromManagerServices(url,HttpClientService.GET_Method_TYPE);
        return JSONAnalysis.NormalAnalysisForObjectCount(resultJson);
    }

    public String queryRushToBuyGoodsId(){
        String url = dbServicesConfigBean.getQueryGoodsListMethodUrl(CommonUtils.GOODS_TYPE_RUSH_TO_BUY);
        JSONObject resultJson = httpClientService.getDataFromManagerServices(url,HttpClientService.GET_Method_TYPE);

        return "";
    }
}
