package com.huawei.controller;

import com.alibaba.fastjson.JSONObject;
import com.huawei.service.DataSourcesService;
import com.huawei.tools.PrePareRushToBuyTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

    private static Logger log = Logger.getLogger(ViewController.class);

    @Autowired
    DataSourcesService dataSourcesService;

    @RequestMapping(value="rushToBuyScene", method = RequestMethod.GET)
    public String sign(HttpServletRequest request){
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


}