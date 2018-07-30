package com.huawei.manager;

import com.huawei.configbean.KafkaConfigBean;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ConsumerManager {

    private static Logger log = Logger.getLogger(ConsumerManager.class);

    private KafkaConfigBean kafkaConfigBean;

    private KafkaConsumer<String, String> kafkaConsumer = null;

    public ConsumerManager(){

    }

    public void setKafkaConfigBean(KafkaConfigBean kafkaConfigBean) {
        this.kafkaConfigBean = kafkaConfigBean;
    }

    private  void initKafkaConsumerClient(){
        if(kafkaConsumer == null) {
            Properties consumerConfig = kafkaConfigBean.getConsumerConfig();
            kafkaConsumer = new KafkaConsumer<>(consumerConfig);
            try {
                kafkaConsumer.subscribe(Arrays.asList(consumerConfig.getProperty("topic")), new ConsumerListener());
            }catch (Exception e){
                log.error(e);
            }

        }
    }

    private class ConsumerListener implements ConsumerRebalanceListener{

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {

        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        }
    }

    public synchronized int consumeMsg(int timeout){
        ConsumerRecords<String, String> records = kafkaConsumer.poll(timeout);
        int pollCount = records.count();
        kafkaConsumer.commitSync();
        return pollCount;
    }
}
