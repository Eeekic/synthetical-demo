package com.huawei.controller;

import com.alibaba.fastjson.JSONObject;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.service.DataSourcesService;
import com.huawei.tools.PrePareRushToBuyTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ViewController {

    private static Logger log = Logger.getLogger(ViewController.class);

    @Autowired
    DataSourcesService dataSourcesService;

    @Autowired
    ManagerServicesConfigBean managerServicesConfigBean;

    @RequestMapping(value="rushToBuyScene", method = RequestMethod.GET)
    public String sign(HttpServletRequest request){

        request.setAttribute("rushToBuyUrl",managerServicesConfigBean.getPayMethodUrl());

        return "com/rushToBuyScene";
    }

    @RequestMapping(value="commitPresetDataForRushToBuyGoods", method = RequestMethod.GET)
    @ResponseBody
    public String commitPresetDataForRushToBuyGoods(HttpServletRequest request){
        String result=null;
        try {
            int rushToBuyGoodsCount = Integer.parseInt(request.getParameter("rushToBuyGoodsCount"));
            if (PrePareRushToBuyTools.getPrivilegeOfCommitData(PrePareRushToBuyTools.PREPARE_RUSH_TO_BUY_GOODS)) {
                dataSourcesService.produceMessages(rushToBuyGoodsCount);
                result = "success";
            } else {
                result = "Please do not repeat the submission!";
            }
        }catch (Exception e){
            result = "failed";
            log.error(e);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value="commitPresetDataForTestUser", method = RequestMethod.GET)
    @ResponseBody
    public String commitPresetDataForTestUser(HttpServletRequest request){
        String result=null;
        try {
            int rushToBuyUsersCount = Integer.parseInt(request.getParameter("rushToBuyUsersCount"));
            if (PrePareRushToBuyTools.getPrivilegeOfCommitData(PrePareRushToBuyTools.PREPARE_TEST_USER)) {
                dataSourcesService.commitPrepareTestUser(rushToBuyUsersCount);
                result = "success";
            } else {
                result = "Please do not repeat the submission!";
            }
        }catch (Exception e){
            result = "failed";
            log.error(e);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value="testUserCount", method = RequestMethod.GET)
    @ResponseBody
    public int queryTestUserCount(){
        return dataSourcesService.queryTestUserCount();
    }

    @RequestMapping(value="rushToBuyGoodsDetail", method = RequestMethod.GET)
    @ResponseBody
    public String queryRushToBuyGoodsDetail(){
        return dataSourcesService.queryRushToBuyGoodsDetail();
    }

    @RequestMapping(value="testUserIdRange", method = RequestMethod.GET)
    @ResponseBody
    public String testUserIdRange(){
        return dataSourcesService.testUserIdRange();
    }

    @RequestMapping(value="pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay(HttpServletRequest request){
        String url =null;
        Map<String,Object> urlMap;
        try {
            url = request.getParameter("rushToBuyUrl");
            String requestBody = request.getParameter("requestBody");
            JSONObject responseJson = JSONObject.parseObject(requestBody);
            urlMap = new HashMap<>();
            urlMap.put("userId",responseJson.get("userId"));
            urlMap.put("goodsId",responseJson.get("goodsId"));
            urlMap.put("goodsPrice",responseJson.get("goodsPrice"));
            urlMap.put("goodsType",responseJson.get("goodsType"));
        }catch (Exception e){
            log.error(e);
            JSONObject resultJson = new JSONObject();
            resultJson.put("delay","-");
            resultJson.put("rushToBuyResult",e.toString());
            e.printStackTrace();
            return resultJson.toJSONString() ;
        }
        return dataSourcesService.pay(url,urlMap);
    }

    @RequestMapping(value="queryMsgCount")
    @ResponseBody
    public int queryMsgCount(){
        return dataSourcesService.obtainAvailableMessageAmount();
    }

}
