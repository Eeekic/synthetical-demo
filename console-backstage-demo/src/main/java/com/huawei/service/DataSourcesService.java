package com.huawei.service;

import com.alibaba.fastjson.JSONObject;
import com.huawei.Utils.CommonUtils;
import com.huawei.Utils.JSONAnalysis;
import com.huawei.bean.ManagerServicesConfigBean;
import com.huawei.projo.Goods;
import com.huawei.projo.Orders;
import com.huawei.projo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dataSourcesService")
public class DataSourcesService {

    @Autowired
    private ManagerServicesConfigBean managerServicesConfigBean;

    @Autowired
    private HttpClientService httpClientService;


}
