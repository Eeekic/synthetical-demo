package com.huawei.test;

import com.alibaba.fastjson.JSONObject;
import com.huawei.manager.ConsumerManager;
import com.huawei.manager.RedisCacheManager;
import com.huawei.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring/*.xml"})
public class DemoTest {

    @Autowired
    RedisCacheManager redisCacheManager;


    @Autowired
    private RestTemplate restTemplate;


    @Test
    public void RedisTest(){
        System.out.println("Test");
        System.out.println("get sun:" + redisCacheManager.hasKey("sun"));
        System.out.println("get peng:" + redisCacheManager.hasKey("peng"));
        redisCacheManager.set("sunpeng","xxxxxx");
        System.out.println("get sunpeng:" + redisCacheManager.get("sunpeng"));
        System.out.println("get peng:" + redisCacheManager.get("peng"));
//        WebClient
    }

    @Test
    public void WebTest(){
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("userName","sunpeng");
        urlVariables.put("password","pengsun");
        String url = "http://localhost:8080/v1/rest/signIn";
        JSONObject result = restTemplate.postForObject(url,urlVariables,JSONObject.class);

//        JSONObject result = restTemplate.getForObject(url,JSONObject.class, urlVariables);
        System.out.println(result.toJSONString());
    }
    @Autowired
    ManagerService managerService;
    @Test
    public void testSignIn(){
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("userName","tom1");
        urlVariables.put("userPwd","tom1");
        System.out.println( managerService.signIn(urlVariables));
    }

    @Test
    public void testSignUp(){
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("userName","tom12");
        urlVariables.put("userPwd","tom");
        System.out.println( managerService.signUp(urlVariables));
    }
    @Test
    public void testGoodsList(){
        System.out.println( managerService.goodsList("Normal"));
    }

    @Test
    public void testGoodsDetailOnRushToBuy(){
        System.out.println( managerService.goodsDetail("2","RushToBuy"));
    }

    @Test
    public void testGoodsDetailOnNormal(){
        System.out.println( managerService.goodsDetail("2","Normal"));
    }

    @Test
    public void testPayOnNormal(){
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("userId","1");
        urlVariables.put("goodsId","1");
        urlVariables.put("goodsPrice","2");
        urlVariables.put("goodsType","Normal");
        System.out.println( managerService.pay(urlVariables));
    }

    @Test
    public void testPayOnRushToBuy(){
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("userId","1");
        urlVariables.put("goodsId","1");
        urlVariables.put("goodsPrice","2");
        urlVariables.put("goodsType","RushToBuy");
        System.out.println( managerService.pay(urlVariables));
    }
    @Test
    public void testOrdersList() {
        Map<String, Object> urlVariables = new HashMap<>();
        urlVariables.put("userId", "1");
        System.out.println(managerService.orderList(urlVariables));
    }

    @Autowired
    ConsumerManager consumerManager;
    @Test
    public void testConsumer() {
        consumerManager.consumeMsg(200);
    }

}
