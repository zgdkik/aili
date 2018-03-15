nohup /opt/jboss/sds-web-jboss1/bin/standalone.sh &
tail -100f  /opt/jboss/sds-web-jboss1/standalone/log/server.log
nohup /opt/jboss/sds-web-jboss2/bin/standalone.sh &
tail -100f  /opt/jboss/sds-web-jboss2/standalone/log/server.log
#启动数据同步和job
nohup /opt/jboss/sync-data-jboss/bin/standalone.sh &
tail -100f  /opt/jboss/sync-data-jboss/standalone/log/server.log
http://themeforest.net/item/adminex-bootstrap-3-responsive-admin-template/7399319?ref=er&ref=er&clickthrough_id=664002849&redirect_back=true