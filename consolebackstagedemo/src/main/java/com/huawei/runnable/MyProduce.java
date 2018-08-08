package com.huawei.runnable;

import com.huawei.Utils.JSONAnalysis;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.service.ApiUtils;
import com.huawei.service.DataSourcesService;
import com.huawei.tools.PrePareRushToBuyTools;

public class MyProduce implements  Runnable{
    private DataSourcesService dataSourcesService=new DataSourcesService();

    private ManagerServicesConfigBean managerServicesConfigBean=new ManagerServicesConfigBean();
    private String endPointurl;
    private String region;
    private String ak;
    private String sk;
    private String projectId;
    private String queueId;
    private String groupId;
    private String serviceName;
    private String msgLimit;
    private int MsgExistsCount;
    private int MsgProduceCount;
    public MyProduce(int MsgExistCount, int MsgProduceCount
            , String url, String region, String ak, String sk, String pid, String qid, String gid, String sname, String msglimit){
        this.MsgExistsCount=MsgExistCount;
        this.MsgProduceCount=MsgProduceCount;
        this.endPointurl = url;
        this.region = region;
        this.ak = ak;
        this.sk = sk;
        this.projectId = pid;
        this.queueId = qid;
        this.groupId = gid;
        this.serviceName = sname;
        this.msgLimit = msglimit;
    }
    @Override
       public void run() {
        try {
            for (int i = 0; i != this.MsgExistsCount; ++i) {
                System.out.println(this.MsgExistsCount);
                dataSourcesService.consumeMessage2(endPointurl, region, ak, sk, projectId, queueId, groupId, serviceName, msgLimit);
            }
            for (int i = 0; i !=MsgProduceCount; ++i) {
                ApiUtils.sendMessages(JSONAnalysis.ConstructMsg(i), queueId, projectId, endPointurl, serviceName, region, ak, sk);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            PrePareRushToBuyTools.resetPrivilegeOfCommitData();
        }
    }
}
