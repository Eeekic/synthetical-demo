package com.huawei.runnable;

import com.huawei.Utils.JSONAnalysis;
import com.huawei.configbean.ManagerServicesConfigBean;
import com.huawei.service.ApiUtils;
import com.huawei.service.DataSourcesService;
import com.huawei.service.ResponseMessage;
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
            int index=0;
            while(true){
                System.out.println(this.MsgExistsCount);
                ResponseMessage rs= dataSourcesService.consumeMessage2(endPointurl, region, ak, sk, projectId, queueId, groupId, serviceName, msgLimit);
                if(rs.getStatusCode()==200)
                    index++;
                if(index>=this.MsgExistsCount)
                    break;
            }
            index=0;
            while(true) {
                ResponseMessage res = ApiUtils.sendMessages(JSONAnalysis.ConstructMsg(index),queueId, projectId, endPointurl, serviceName, region, ak, sk);
                if (res.isSuccess())
                    index++;
                if (index >= this.MsgProduceCount)
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            PrePareRushToBuyTools.resetPrivilegeOfCommitData();
        }
    }
}
