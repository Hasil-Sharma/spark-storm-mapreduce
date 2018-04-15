source $REMOTE_SCRIPT_FOLDER/constants.sh

cp $YARN_SITE $YARN_SITE.bak

mkdir -p $YARN_NODEMANGER_LOG_DIRS
mkdir -p $YARN_NODEMANGER_LOCAL_DIRS

sed -i "/<\/configuration>/i \
  <property>\
   <name>yarn.resourcemanager.hostname</name>\
   <value>$YARN_RESOURCE_MANAGER_HOSTNAME</value>\
 </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
   <name>yarn.nodemanager.resource.memory-mb</name>\
   <value>$YARN_NODEMANGER_RESOURCE_MEMORY_MB</value>\
 </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
    <name>yarn.nodemanager.vmem-pmem-ratio</name>\
    <value>$YARN_NODEMANGER_VMEM_PMEM_RATION</value>\
  </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
    <name>yarn.nodemanager.local-dirs</name>\
    <value>$YARN_NODEMANGER_LOCAL_DIRS</value>\
   </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
  <property>\
    <name>yarn.nodemanager.log-dirs</name>\
    <value>$YARN_NODEMANGER_LOG_DIRS</value>\
  </property>" $YARN_SITE

sed -i "/<\/configuration>/i \
    <property>\
      <name>yarn.nodemanager.aux-services</name>\
      <value>$YARN_NODEMANGER_AUX_SERVICES</value>\
    </property>" $YARN_SITE
xmllint --format $YARN_SITE > $YARN_SITE.tmp
mv $YARN_SITE.tmp $YARN_SITE
