package com.huawei;

import com.huawei.dao.GoodsDao;
import com.huawei.dao.OrdersDao;
import com.huawei.dao.PendingPaymentDao;
import com.huawei.dao.UserDao;
import com.huawei.projo.Goods;
import com.huawei.projo.Orders;
import com.huawei.projo.PendingPayment;
import com.huawei.projo.User;
import com.huawei.service.DbServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})
public class DemoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    PendingPaymentDao pendingPaymentDao;
    @Autowired
    private DbServices dbServices;

    @Test
    public void userTest(){
        System.out.println("Test");
        User user = userDao.querySimpleUserInfoByName("tom");
        if(user != null) {
            System.out.println(user.toString());
        }else {
            System.out.println("user is null");
        }
        User user2 = new User();
        user2.setUserName("Jack2");
        user2.setUserPwd("xxxxx");
        int p = userDao.addUser(user2);
        System.out.println(p);
    }

    @Test
    public void goodsTest(){
        Goods goods = goodsDao.queryGoodsDetail(2);
        if(goods != null) {
            System.out.println(goods.toString());
        }else {
            System.out.println("goods is null");
        }
        List<Goods> goodsList = goodsDao.queryGoodsList("Normal");
        for(Goods goods1:goodsList){
            System.out.println(goods1.toString());
        }
    }

    @Test
    public void ordersTest(){
        List<Orders> ordersList = ordersDao.queryOrdersList(1);
        for(Orders orders:ordersList){
            System.out.println(orders.toString());
        }
    }

    @Test
    public void batchInsertTest(){
        List<PendingPayment> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            PendingPayment pendingPayment = new PendingPayment();
            pendingPayment.setUserId(i);
            pendingPayment.setGoodsId(i);
            pendingPayment.setToken("-");
            list.add(pendingPayment);
        }
        System.out.println(pendingPaymentDao.batchAdd(list));
    }

    @Test
    public void queryTest(){
        System.out.println(pendingPaymentDao.queryPendingPayment(1));
    }

}
