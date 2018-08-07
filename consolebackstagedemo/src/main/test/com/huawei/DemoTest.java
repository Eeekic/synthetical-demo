package com.huawei;

import com.huawei.Utils.CommonUtils;
import com.huawei.projo.User;
import com.huawei.service.ApplicationContextRegister;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.projo.Goods;
import com.huawei.service.DataSourcesService;
import com.huawei.service.HttpClientService;
import com.huawei.tools.PrePareRushToBuyTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})
public class DemoTest {
    @Autowired
    DataSourcesService dataSourcesService;
    @Test
    public void test(){
        String result=null;
        int rushToBuyGoodsCount = 10;
        int rushToBuyUsersCount = 10;
        if(PrePareRushToBuyTools.getPrivilegeOfCommitData()) {
            dataSourcesService.commitPrepareTestUser(rushToBuyUsersCount);
        }else {
            result = "Please do not repeat the submission!";
        }
        System.out.println(result);
    }

    @Test
    public void testFuctureTask(){
        FutureTask<String > task=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
    }
}
