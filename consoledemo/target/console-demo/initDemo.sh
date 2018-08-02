dburl=${dburl//\//\\\/}
dburl=${dburl//&/\\&}
configPath=/usr/local/tomcat/webapps/console-demo/WEB-INF/classes/config/managerservices.properties
sed -i 's/\(managerServicesUrl=\).*/\1'managerServicesUrl'/' $configPath