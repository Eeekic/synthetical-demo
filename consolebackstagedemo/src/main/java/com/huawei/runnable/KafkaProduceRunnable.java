package com.huawei.runnable;

import com.huawei.Utils.JSONAnalysis;
import com.huawei.configbean.KafkaConfigBean;
import com.huawei.dmskfakaapi.ApiUtils;
import com.huawei.service.DataSourcesService;
import com.huawei.tools.PrePareRushToBuyTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class KafkaProduceRunnable implements Runnable {

    private static Logger log = Logger.getLogger(KafkaProduceRunnable.class);

    @Autowired
    private KafkaConfigBean kafkaConfigBean;

    @Autowired
    private DataSourcesService dataSourcesService;

    private int msgProduceCount;

    private static final int PRODUCE_SPLIT = 10;

    @Override
    public void run() {

        cleanKafkaMsg();

        for(int i = 0;i < this.msgProduceCount/PRODUCE_SPLIT;i++ ){
            produceKafkaMsg(PRODUCE_SPLIT);
        }
        produceKafkaMsg(this.msgProduceCount%PRODUCE_SPLIT);

        PrePareRushToBuyTools.resetPrivilegeOfCommitData(PrePareRushToBuyTools.PREPARE_RUSH_TO_BUY_GOODS);

    }

    private void produceKafkaMsg(int count){
        try {
            ApiUtils.sendMessages(
                    JSONAnalysis.ConstructMsg(count),
                    kafkaConfigBean.getQueueId(),
                    kafkaConfigBean.getProjectId(),
                    kafkaConfigBean.getEndPointUrl(),
                    kafkaConfigBean.getServiceName(),
                    kafkaConfigBean.getRegion(),
                    kafkaConfigBean.getAk(),
                    kafkaConfigBean.getSk());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
        }
    }

    private void cleanKafkaMsg(){
        try {
            int availableMsgAmount = dataSourcesService.obtainAvailableMessageAmount();
            do{
                consumeKafkaMsg(availableMsgAmount);
                availableMsgAmount = dataSourcesService.obtainAvailableMessageAmount();
            }while (availableMsgAmount > 0 || availableMsgAmount == -1);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
        }
    }

//    private void cleanKafkaMsg(){
//        try {
//            int availableMsgAmount = 10;
//            do{
//                consumeKafkaMsg(availableMsgAmount);
//                availableMsgAmount = dataSourcesService.obtainAvailableMessageAmount();
//                if(availableMsgAmount == -1){
//                    availableMsgAmount = 10;
//                }
//            }while (availableMsgAmount==0);
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error(e);
//        }
//    }

    private void consumeKafkaMsg(int count){
        int consumeSplit = Integer.parseInt(kafkaConfigBean.getMsgLimit());
        for(int i = 0; i < count/consumeSplit + 1;i++) {
            dataSourcesService.consumeMessage(
                    kafkaConfigBean.getEndPointUrl(),
                    kafkaConfigBean.getRegion(),
                    kafkaConfigBean.getAk(),
                    kafkaConfigBean.getSk(),
                    kafkaConfigBean.getProjectId(),
                    kafkaConfigBean.getQueueId(),
                    kafkaConfigBean.getGroupId(),
                    kafkaConfigBean.getServiceName(),
                    kafkaConfigBean.getMsgLimit());
        }
    }

    public void setMsgProduceCount(int msgProduceCount) {
        this.msgProduceCount = msgProduceCount;
    }
}
