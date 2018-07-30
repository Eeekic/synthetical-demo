package com.huawei.dao.impl;

import com.huawei.dao.OrdersDao;
import com.huawei.dao.mapper.OrdersMapper;
import com.huawei.projo.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrdersImpl implements OrdersDao {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> queryOrdersList(long userId) {
        return ordersMapper.queryOrdersList(userId);
    }

    @Override
    public int addOrders(Orders orders) {
        return ordersMapper.addOrders(orders);
    }
}
