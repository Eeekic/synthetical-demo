FROM tomcat:9.0
WORKDIR /usr/local/tomcat/webapps/
ENV managerServicesUrl http://49.4.4.124:31935/manager-demo
COPY consoledemo/target/ $WORKDIR
RUN sed -i "2i /usr/local/tomcat/webapps/console-demo/initDemo.sh" /usr/local/tomcat/bin/catalina.sh
RUN chmod 777 /usr/local/tomcat/webapps/console-demo/initDemo.sh
CMD ["catalina.sh", "run"]