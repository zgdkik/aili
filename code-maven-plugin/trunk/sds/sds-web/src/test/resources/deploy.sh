#!/bin/bash
echo "del file start"
rm -rf /usr/tomcat/tomcat2/apache-tomcat-6.0.44/webapps/frontend-web.war
rm -rf /usr/tomcat/apache-tomcat-6.0.44/webapps/frontend-web.war
echo "del file end"
echo "copy file start"
cp /home/hbhkftp1/frontend-web.war  /usr/tomcat/tomcat2/apache-tomcat-6.0.44/webapps/
cp /home/hbhkftp1/frontend-web.war  /usr/tomcat/apache-tomcat-6.0.44/webapps/
echo "copy file end"
echo "close tomcat start"
/usr/tomcat/apache-tomcat-6.0.44/bin/shutdown.sh
/usr/tomcat/tomcat2/apache-tomcat-6.0.44/bin/shutdown.sh
echo "close tomcat end"
echo "start tomcat start"
/usr/tomcat/apache-tomcat-6.0.44/bin/startup.sh
/usr/tomcat/tomcat2/apache-tomcat-6.0.44/bin/startup.sh
echo "start tomca end"