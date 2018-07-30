package com.huawei.configbean;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class KafkaConfigBean {

    private static final String SASL_CONFIG = "dms_kafka_client_jaas.conf";

    private static final String TRUSTSTORE_PATH = "client.truststore.jks";

    private static Logger log = Logger.getLogger(KafkaConfigBean.class);

    //Common param
    private String topic;
    private String bootstrapServers;
    private String sslTruststorePassword;
    private String securityProtocol;
    private String saslMechanism;

    //Produce
    private String acks;
    private String retries;
    private String batchSize;
    private String bufferMemory;
    private String keySerializer;
    private String valueSerializer;
    private String msgCount;
    private String produceCount;
    private String msgSize;

    //Consume
    private String groupId;
    private String keyDeserializer;
    private String valueDeserializer;
    private String autoOffsetReset;
    private String enableAutoCommit;
    private String connectionsMaxIdleMs;
    private String maxPollRecords;

    //valid
    private String sslTruststoreLocation = null;

    private Properties consumerConfig = null;


    public KafkaConfigBean(){
        setSslTruststoreLocation();
    }

    static {
        setJavaSecurityAuthLoginConfig();
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public void setSslTruststorePassword(String sslTruststorePassword) {
        this.sslTruststorePassword = sslTruststorePassword;
    }

    public void setSecurityProtocol(String securityProtocol) {
        this.securityProtocol = securityProtocol;
    }

    public void setSaslMechanism(String saslMechanism) {
        this.saslMechanism = saslMechanism;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public void setRetries(String retries) {
        this.retries = retries;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public void setBufferMemory(String bufferMemory) {
        this.bufferMemory = bufferMemory;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public void setProduceCount(String produceCount) {
        this.produceCount = produceCount;
    }

    public void setMsgSize(String msgSize) {
        this.msgSize = msgSize;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }

    public void setEnableAutoCommit(String enableAutoCommit) {
        this.enableAutoCommit = enableAutoCommit;
    }

    public void setConnectionsMaxIdleMs(String connectionsMaxIdleMs) {
        this.connectionsMaxIdleMs = connectionsMaxIdleMs;
    }

    public void setMaxPollRecords(String maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
    }

    private void setSslTruststoreLocation() {
        if(sslTruststoreLocation == null){
            try{
                sslTruststoreLocation = getTrustStorePath();
            }catch (Exception e){
                log.error("SslTruststoreLocation:" + e.getCause() + ":" + e.getMessage());
            }

        }
    }

    private static void  setJavaSecurityAuthLoginConfig() {
        try{
            System.setProperty("java.security.auth.login.config", getSaslConfig());
        }catch (Exception e){
            log.error("JavaSecurityAuthLoginConfig:" + e.getCause() + ":" + e.getMessage());
        }
    }

    public Properties getConsumerConfig() {
        if(consumerConfig == null) {
            consumerConfig = new Properties();
            consumerConfig.put("topic", topic);
            consumerConfig.put("group.id", groupId);
            consumerConfig.setProperty("bootstrap.servers", bootstrapServers);
            consumerConfig.setProperty("ssl.truststore.password", sslTruststorePassword);
            consumerConfig.setProperty("security.protocol", securityProtocol);
            consumerConfig.setProperty("sasl.mechanism", saslMechanism);
            consumerConfig.setProperty("key.deserializer", keyDeserializer);
            consumerConfig.setProperty("value.deserializer", valueDeserializer);
            consumerConfig.setProperty("auto.offset.reset", autoOffsetReset);
            consumerConfig.setProperty("enable.auto.commit", enableAutoCommit);
            consumerConfig.setProperty("connections.max.idle.ms", connectionsMaxIdleMs);
            consumerConfig.put("ssl.truststore.location", sslTruststoreLocation);
            consumerConfig.put("max.poll.records",maxPollRecords);
        }
        return consumerConfig;
    }

    private static String getSaslConfig(){
        return getClassLoader().getResource(SASL_CONFIG).getPath();
//        return "D:\\Program Files\\apache-tomcat-8.5.32\\webapps\\ROOT\\WEB-INF\\classes\\dms_kafka_client_jaas.conf";
    }

    private static String getTrustStorePath(){
        return getClassLoader().getResource(TRUSTSTORE_PATH).getPath();
//        return "D:\\Program Files\\apache-tomcat-8.5.32\\webapps\\ROOT\\WEB-INF\\classes\\client.truststore.jks";
    }

    private static ClassLoader getClassLoader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null){
            classLoader = KafkaConfigBean.class.getClassLoader();
        }
        return classLoader;
    }
}
