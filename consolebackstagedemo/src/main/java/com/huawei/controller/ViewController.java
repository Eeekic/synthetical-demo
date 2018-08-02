package com.huawei.controller;

import com.huawei.Utils.CommonUtils;
import com.huawei.projo.Goods;
import com.huawei.projo.Orders;
import com.huawei.projo.User;
import com.huawei.service.DataSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    DataSourcesService dataSourcesService;

    @RequestMapping(value="testGet", method = RequestMethod.GET)
    @ResponseBody
    public String sign(HttpServletRequest request){
        return "Hello world!";
    }

}
