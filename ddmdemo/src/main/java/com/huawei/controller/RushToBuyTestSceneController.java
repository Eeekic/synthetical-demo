package com.huawei.controller;

import com.huawei.service.RushToBuyTestSceneDbServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/rushToBusyTest")
public class RushToBuyTestSceneController {

    @Autowired
    RushToBuyTestSceneDbServices rushToBuyTestSceneDbServices;

    @RequestMapping(value="v1/rest/addTestUser", method = RequestMethod.GET)
    @ResponseBody
    public String addTestUser(HttpServletRequest request){
        int count = Integer.parseInt(request.getParameter("count"));
        return rushToBuyTestSceneDbServices.addTestUser(count);
    }

    @RequestMapping(value="v1/rest/queryTestUser", method = RequestMethod.GET)
    @ResponseBody
    public String queryTestUser(){
        return rushToBuyTestSceneDbServices.queryTestUser();
    }

    @RequestMapping(value="v1/rest/queryTestUserCount", method = RequestMethod.GET)
    @ResponseBody
    public String queryTestUserCount(){
        return rushToBuyTestSceneDbServices.queryTestUserCount();
    }

    @RequestMapping(value="v1/rest/cleanTestUser", method = RequestMethod.GET)
    @ResponseBody
    public String cleanTestUser(){
        return rushToBuyTestSceneDbServices.cleanTestUser();
    }


    @RequestMapping(value="v1/rest/rushToBuySuccessUser", method = RequestMethod.GET)
    @ResponseBody
    public String rushToBuySuccessUser(){
        return rushToBuyTestSceneDbServices.rushToBuySuccessUser();
    }

    @RequestMapping(value="v1/rest/rushToBuySuccessCount", method = RequestMethod.GET)
    @ResponseBody
    public String rushToBuySuccessCount(){
        return rushToBuyTestSceneDbServices.rushToBuySuccessCount();
    }
}