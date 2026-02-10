FROM tomcat:8.0.20-jre8
COPY tomcat-users.xml /usr/local/tomcat/conf/
COPY /opt/war/*.war /usr/local/tomcat/webapps/
EXPOSE 8080
