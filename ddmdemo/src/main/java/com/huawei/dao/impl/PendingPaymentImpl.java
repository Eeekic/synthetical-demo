package com.huawei.dao.impl;

import com.huawei.Utils.CommonUtils;
import com.huawei.dao.PayDao;
import com.huawei.dao.PendingPaymentDao;
import com.huawei.dao.mapper.PendingPaymentMapper;
import com.huawei.projo.PendingPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PendingPaymentImpl implements PendingPaymentDao {

    @Autowired
    private PayDao paydao;

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

    @Transactional
    @Override
    public String payPendingPayment(long pendingPaymentId,long userId,long goodsId,int goodsPrice) {
        String result = paydao.pay(userId,goodsId,goodsPrice);
        if (result == CommonUtils.PAY_SUCCESS ){
            pendingPaymentMapper.setPayedTrue(pendingPaymentId);
        }
        return result;
    }

    @Override
    public PendingPayment queryPendingPaymentById(long pendingPaymentId) {
        return pendingPaymentMapper.queryPendingPaymentById(pendingPaymentId);
    }
}
