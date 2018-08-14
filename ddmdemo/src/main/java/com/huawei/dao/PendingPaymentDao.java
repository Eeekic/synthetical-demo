package com.huawei.dao;

import com.huawei.projo.PendingPayment;

import java.util.List;

public interface PendingPaymentDao {

    /**
     * create by: sunpeng
     * description:
     * create time: 12:04 2018/8/13
     *
     * @return 
     */
    int batchAdd(List<PendingPayment> pendingPaymentList);

    /**
     * create by: sunpeng
     * description:
   12:04r2018/8/13: 12:04 2018/8/1
     *
     * @return a
     */
    List<PendingPayment> queryPendingPayment(long userId);

}
