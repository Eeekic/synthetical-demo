package com.huawei;

import com.huawei.Utils.CommonUtils;
import com.huawei.projo.User;
import com.huawei.service.ApplicationContextRegister;
import com.huawei.bean.ManagerServicesConfigBean;
import com.huawei.projo.Goods;
import com.huawei.service.DataSourcesService;
import com.huawei.service.HttpClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})
public class DemoTest {


}
