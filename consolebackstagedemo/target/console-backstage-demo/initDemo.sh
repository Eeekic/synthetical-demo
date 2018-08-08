#Manager services config
managerServicesUrl=${managerServicesUrl//\//\\\/}
managerServicesUrl=${managerServicesUrl//&/\\&}
managerServicesConfigPath=/usr/local/tomcat/webapps/console-backstage-demo/WEB-INF/classes/config/managerservices.properties
sed -i 's/\(managerServicesUrl=\).*/\1'$managerServicesUrl'/' $managerServicesConfigPath

#Db services config
dbServicesUrl=${dbServicesUrl//\//\\\/}
dbServicesUrl=${dbServicesUrl//&/\\&}
dbServicesConfigPath=/usr/local/tomcat/webapps/console-backstage-demo/WEB-INF/classes/config/dbservices.properties
sed -i 's/\(hostAndPort=\).*/\1'$dbServicesUrl'/' dbServicesConfigPath

#DMS Kafka config
