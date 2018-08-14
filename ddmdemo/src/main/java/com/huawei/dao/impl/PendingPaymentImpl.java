package com.huawei.dao.impl;

import com.huawei.dao.PendingPaymentDao;
import com.huawei.dao.mapper.PendingPaymentMapper;
import com.huawei.projo.PendingPayment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PendingPaymentImpl implements PendingPaymentDao {
    @Autowired
    private PendingPaymentMapper pendingPaymentMapper;

    @Override
    public int batchAdd(List<PendingPayment> pendingPaymentList) {
        return pendingPaymentMapper.batchAdd(pendingPaymentList);
    }

    @Override
    public List<PendingPayment> queryPendingPayment(long userId) {
        return pendingPaymentMapper.queryPendingPayment(userId);
    }
}
