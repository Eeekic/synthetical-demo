package com.huawei.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.Utils.JSONAnalysis;
import com.huawei.configbean.DbServicesConfigBean;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.controller.ViewController;
import com.huawei.projo.Message_available_status;
import com.huawei.runnable.MyProduce;
import com.huawei.runnable.PrepareTestUserRunnable;
import com.huawei.tools.PrePareRushToBuyTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

import static com.huawei.service.ApiUtils.*;

@Service("dataSourcesService")
public class DataSourcesService {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private DbServicesConfigBean dbServicesConfigBean;

    @Autowired
    private PrepareTestUserRunnable prepareTestUserRunnable;
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
    public com.huawei.projo.Message_available_status CheckMessageAmount(String groupId){
        Message_available_status re=null;
        ResponseMessage res=getGroups(managerServicesConfigBean.getQueueId(),managerServicesConfigBean.getProjectId(),
                managerServicesConfigBean.getEndPointurl(),managerServicesConfigBean.getServiceName(),
                managerServicesConfigBean.getRegion(),
                managerServicesConfigBean.getAk(),
                managerServicesConfigBean.getSk());
        if(!res.isSuccess()){
            return re;
        }
        JSONObject jsonObject=JSON.parseObject(res.getBody());
        re=JSONAnalysis.analysisMessages(jsonObject,groupId);
        return re;
    }
    public String ProduceMessages(Integer num){
        Message_available_status message_available_status=CheckMessageAmount(managerServicesConfigBean.getGroupId());
        MyProduce myProduce=new MyProduce(Integer.parseInt(message_available_status.availableMsg),num,managerServicesConfigBean.getEndPointurl(),
                managerServicesConfigBean.getRegion(),managerServicesConfigBean.getAk(),managerServicesConfigBean.getSk(),
                managerServicesConfigBean.getProjectId(),managerServicesConfigBean.getQueueId(),managerServicesConfigBean.getGroupId(),
                managerServicesConfigBean.getServiceName(),managerServicesConfigBean.getMsgLimit());
        PrePareRushToBuyTools.execute(myProduce);
        return "success";
    }
    public ResponseMessage consumeMessage(){
        ResponseMessage res;
        res=consumeMessages(managerServicesConfigBean.getQueueId(),managerServicesConfigBean.getGroupId()
                ,Integer.parseInt(managerServicesConfigBean.getMsgLimit()),managerServicesConfigBean.getProjectId()
                ,managerServicesConfigBean.getEndPointurl(),managerServicesConfigBean.getServiceName()
                ,managerServicesConfigBean.getRegion(),managerServicesConfigBean.getAk()
                ,managerServicesConfigBean.getSk());
        ArrayList<String> handlerIds=new ArrayList<String>();
        handlerIds = parseHandlerIds(res);
        ResponseMessage resAck;
        if(res.isSuccess()&&handlerIds.size()!=0) {
            resAck = acknowledgeMessages(handlerIds,
                    managerServicesConfigBean.getGroupId(),
                    managerServicesConfigBean.getQueueId(),
                    managerServicesConfigBean.getProjectId(),
                    managerServicesConfigBean.getEndPointurl(),
                    managerServicesConfigBean.getServiceName(),
                    managerServicesConfigBean.getRegion(),
                    managerServicesConfigBean.getAk(),
                    managerServicesConfigBean.getSk());
            return resAck;
        }
        res.setSuccess(false);
        return res;
    }
    public ResponseMessage consumeMessage2(String url,String region,String ak,String sk,String pid,String qid,String gid,String sname,String msglimit){
        ResponseMessage res;
        res=consumeMessages(qid,gid,Integer.parseInt(msglimit),pid,url,sname,region,ak,sk);
        ArrayList<String> handlerIds=new ArrayList<String>();
        handlerIds = parseHandlerIds(res);
        ResponseMessage resAck;
        if(res.isSuccess()&&handlerIds.size()!=0) {
            resAck = acknowledgeMessages(handlerIds,gid,qid,pid,url,sname,region,ak,sk);
            return resAck;
        }
        res.setSuccess(false);
        return res;
    }
}
