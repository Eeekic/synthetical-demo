package com.huawei.controller;

import com.alibaba.fastjson.JSONObject;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.projo.Message_available_status;
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

    @RequestMapping(value="commitPresetData", method = RequestMethod.GET)
    @ResponseBody
    public String commitPresetData(HttpServletRequest request){
        String result=null;
        try {
            int rushToBuyGoodsCount = Integer.parseInt(request.getParameter("rushToBuyGoodsCount"));
            int rushToBuyUsersCount = Integer.parseInt(request.getParameter("rushToBuyUsersCount"));
            if (PrePareRushToBuyTools.getPrivilegeOfCommitData()) {
                dataSourcesService.commitPrepareTestUser(rushToBuyUsersCount);
                dataSourcesService.ProduceMessages(rushToBuyGoodsCount);
                result = "success";
            } else {
                result = "Please do not repeat the submission!";
            }
        }catch (Exception e){
            result = "failed";
            log.error(e);
        }
        return result;
    }
    @RequestMapping(value="queryMsgCount")
    @ResponseBody
    public int queryMsgCount(){
        Message_available_status ms=dataSourcesService.CheckMessageAmount(managerServicesConfigBean.getGroupId());
        return Integer.parseInt(ms.availableMsg);
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
            return resultJson.toJSONString() ;
        }
        return dataSourcesService.pay(url,urlMap);
    }

}
