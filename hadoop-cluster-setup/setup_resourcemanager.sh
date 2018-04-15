source $REMOTE_SCRIPT_FOLDER/constants.sh

cp $YARN_SITE $YARN_SITE.bak

sed -i "/<\/configuration>/i \
  <property>\
   <name>yarn.resourcemanager.hostname</name>\
   <value>$YARN_RESOURCE_MANAGER_HOSTNAME</value>\
 </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
    <name>yarn.resourcemanager.scheduler.class</name>\
    <value>$YARN_RESOURCE_MANAGER_SCHEDULER_CLASS</value>\
  </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
    <name>yarn.scheduler.minimum-allocation-mb</name>\
    <value>$YARN_RESOURCE_MANAGER_MIN_ALLOC_MB</value>\
  </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
    <name>yarn.scheduler.maximum-allocation-mb</name>\
    <value>$YARN_RESOURCE_MANAGER_MAX_ALLOC_MB</value>\
  </property>" $YARN_SITE

xmllint --format $YARN_SITE > $YARN_SITE.tmp
mv $YARN_SITE.tmp $YARN_SITE
