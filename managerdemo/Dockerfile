FROM tomcat:9.0
WORKDIR /usr/local/tomcat/webapps/

# DB services
ENV dburl http://49.4.4.124:31021/ddmdemo

# Kafka
ENV bootstrapservers dms-kafka.cn-north-1.myhuaweicloud.com:37000
ENV topic k-0e964f1ae251477ebbfe8cd85e758ee6-0df351cc-5f98-4b78-b71b-3d41c683f55f
ENV groupid g-271545b4-5026-432d-9ecb-5092a9f6a5fe
# Kafka client
ENV accesskey G14IYNXRHFV9ISEWHEMW
ENV secretkey ZGaTtLs1KwcRCOlfmzFMBJ9lqbsZJoyU3Kcgr5gh
ENV projectid 0e964f1ae251477ebbfe8cd85e758ee6

# Redis
ENV redishost redis-a231e00-dcs-rcwm.dcs.huaweicloud.com
ENV redisport 6379
ENV redispasswd Ddm@cloud2017
ENV redismaxactive 250

COPY managerdemo/target/ $WORKDIR
RUN sed -i "2i /usr/local/tomcat/webapps/ddmdemo/initDemo.sh" /usr/local/tomcat/bin/catalina.sh
RUN chmod 777 /usr/local/tomcat/webapps/ddmdemo/initDemo.sh

CMD ["catalina.sh", "run"]