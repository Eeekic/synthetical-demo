package com.huawei.runnable;

import com.huawei.configbean.DbServicesConfigBean;
import com.huawei.service.HttpClientService;
import com.huawei.tools.PrePareRushToBuyTools;
import org.springframework.beans.factory.annotation.Autowired;

public class PrepareTestUserRunnable implements Runnable{

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private DbServicesConfigBean dbServicesConfigBean;

    private int count = 100;

    private final static int SPLIT = 5;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        String url = dbServicesConfigBean.getAddTestUserMethodUrl(5);

        for(int i = 0;i < count/5;i++) {
            httpClientService.getDataFromManagerServices(url, HttpClientService.GET_Method_TYPE);
        }

        url = dbServicesConfigBean.getAddTestUserMethodUrl(count%5);
        httpClientService.getDataFromManagerServices(url, HttpClientService.GET_Method_TYPE);
        PrePareRushToBuyTools.resetPrivilegeOfCommitData();
    }
}
