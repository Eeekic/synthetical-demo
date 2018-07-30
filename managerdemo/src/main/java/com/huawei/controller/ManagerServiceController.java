package com.huawei.controller;

import com.huawei.manager.ConsumerManager;
import com.huawei.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ManagerServiceController {

    @Autowired
    ManagerService managerService;

    @RequestMapping(value="v1/rest/signIn", method = RequestMethod.POST)
    @ResponseBody
    public String signIn(@RequestBody Map<String, Object> param){
        return managerService.signIn(param);
    }
    @RequestMapping(value="v1/rest/signUp", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(@RequestBody Map<String, Object> param){
        return managerService.signUp(param);
    }

    @RequestMapping(value="v1/rest/userDetail", method = RequestMethod.POST)
    @ResponseBody
    public String userDetail(@RequestBody Map<String, Object> param){
        return managerService.userDetail(param);
    }

    @RequestMapping(value="v1/rest/goodsList", method = RequestMethod.GET)
    @ResponseBody
    public String goodsList(HttpServletRequest request){
        String goodsType = request.getParameter("goodsType");
        return managerService.goodsList(goodsType);
    }

    @RequestMapping(value="v1/rest/goodsDetail", method = RequestMethod.GET)
    @ResponseBody
    public String goodsDetail(HttpServletRequest request){
        String goodsId = request.getParameter("goodsId");
        String goodsType = request.getParameter("goodsType");

        return managerService.goodsDetail(goodsId,goodsType);
    }

    @RequestMapping(value="v1/rest/pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay(@RequestBody Map<String, Object> param){
        return managerService.pay(param);
    }
    @RequestMapping(value="v1/rest/orderList", method = RequestMethod.POST)
    @ResponseBody
    public String orderList(@RequestBody Map<String, Object> param){
        return managerService.orderList(param);
    }

    @Autowired
    ConsumerManager consumerManager;

    @RequestMapping(value="v1/rest/kafkaTest", method = RequestMethod.GET)
    @ResponseBody
    public String kafkaTest(){
        return consumerManager.consumeMsg(200)+"";
    }
}
