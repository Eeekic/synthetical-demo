package com.huawei.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.Utils.JSONAnalysis;
import com.huawei.configbean.DbServicesConfigBean;
import com.huawei.configbean.KafkaConfigBean;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.dmskfakaapi.ApiUtils;
import com.huawei.dmskfakaapi.ResponseMessage;
import com.huawei.runnable.KafkaProduceRunnable;
import com.huawei.runnable.PrepareTestUserRunnable;
import com.huawei.tools.PrePareRushToBuyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("dataSourcesService")
public class DataSourcesService {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private DbServicesConfigBean dbServicesConfigBean;

    @Autowired
    private KafkaConfigBean kafkaConfigBean;

    @Autowired
    private PrepareTestUserRunnable prepareTestUserRunnable;

    @Autowired
    private KafkaProduceRunnable kafkaProduceRunnable;

    @Autowired
    private ManagerServicesConfigBean managerServicesConfigBean;

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

    private int cleanTestUser(){
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
        return resultJson.toJSONString();
    }

    public int obtainAvailableMessageAmount(){
        ResponseMessage res = ApiUtils.getGroups(
                kafkaConfigBean.getQueueId(),
                kafkaConfigBean.getProjectId(),
                kafkaConfigBean.getEndPointUrl(),
                kafkaConfigBean.getServiceName(),
                kafkaConfigBean.getRegion(),
                kafkaConfigBean.getAk(),
                kafkaConfigBean.getSk());

        return JSONAnalysis.obtainAvailableMsgAmount(res,kafkaConfigBean.getGroupId());
    }

    public void produceMessages(int produceCount){
        kafkaProduceRunnable.setMsgProduceCount(produceCount);
        PrePareRushToBuyTools.execute(kafkaProduceRunnable);
    }

    public ResponseMessage consumeMessage(
            String url,
            String region,
            String ak,
            String sk,
            String pid,
            String qid,
            String gid,
            String sname,
            String msglimit){
        ResponseMessage res;
        res=ApiUtils.consumeMessages(qid,gid,Integer.parseInt(msglimit),pid,url,sname,region,ak,sk);
        ArrayList<String>  handlerIds = ApiUtils.parseHandlerIds(res);
        ResponseMessage resAck;
        if(res.isSuccess()&&handlerIds.size()!=0) {
            resAck = ApiUtils.acknowledgeMessages(handlerIds,gid,qid,pid,url,sname,region,ak,sk);
            return resAck;
        }
        res.setSuccess(false);
        return res;
    }
}
