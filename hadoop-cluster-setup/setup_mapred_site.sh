source $REMOTE_SCRIPT_FOLDER/constants.sh

cp $MAPRED_SITE.xml $MAPRED_SITE

sed -i "/<\/configuration>/i \
  <property>\
   <name>mapreduce.framework.name/name>\
   <value>$MAPREDUCE_FRAMEWORK_NAME</value>\
 </property>" $MAPRED_SITE

xmllint --format $MAPRED_SITE > $MAPRED_SITE.tmp
mv $MAPRED_SITE.tmp $MAPRED_SITE
