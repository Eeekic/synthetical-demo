# DB services config
dburl=${dburl//\//\\\/}
dburl=${dburl//&/\\&}
dbConfigPath=/usr/local/tomcat/webapps/managerdemo/WEB-INF/classes/config/component/dbservice.properties
sed -i 's/\(hostAndPort=\).*/\1'$dburl'/' $dbConfigPath

# Kafka config
kafkaConfigPath=/usr/local/tomcat/webapps/managerdemo/WEB-INF/classes/config/component/kafka.properties
sed -i 's/\(bootstrap.servers=\).*/\1'$bootstrapservers'/' $kafkaConfigPath
sed -i 's/\(topic=\).*/\1'$topic'/' $kafkaConfigPath
sed -i 's/\(group.id=\).*/\1'$groupid'/' $kafkaConfigPath

# Kafka client config
kafkaClientConfigPath=/usr/local/tomcat/webapps/managerdemo/WEB-INF/classes/dms_kafka_client_jaas.conf
sed -i 's/\(access_key=\).*/\1'$accesskey'/' $kafkaClientConfigPath
sed -i 's/\(secret_key=\).*/\1'$secretkey'/' $kafkaClientConfigPath
sed -i 's/\(project_id=\).*/\1'$projectid'/' $kafkaClientConfigPath

# Redis config
redisConfigPath=/usr/local/tomcat/webapps/managerdemo/WEB-INF/classes/config/component/redis.properties
sed -i 's/\(redis.host=\).*/\1'$redishost'/' $redisConfigPath
sed -i 's/\(redis.port=\).*/\1'$redisport'/' $redisConfigPath
sed -i 's/\(redis.passwd=\).*/\1'$redispasswd'/' $redisConfigPath
sed -i 's/\(redis.maxActive=\).*/\1'$redismaxactive'/' $redisConfigPath