source $REMOTE_SCRIPT_FOLDER/constants.sh

cp $HDFS_SITE $HDFS_SITE.bak

mkdir -p $DFS_NAMENODE_NAME_DIR

sed -i "/<\/configuration>/i \
  <property>\
   <name>dfs.namenode.name.dir</name>\
   <value>$DFS_NAMENODE_NAME_DIR</value>\
 </property>" $HDFS_SITE

sed -i "/<\/configuration>/i \
 <property>\
  <name>dfs.blocksize</name>\
  <value>$DFS_BLOCK_SIZE</value>\
</property>" $HDFS_SITE

sed -i "/<\/configuration>/i \
  <property>\
   <name>dfs.namenode.handler.count</name>\
   <value>$DFS_NAMENODE_HANDLER_COUNT</value>\
 </property>" $HDFS_SITE

 xmllint --format $HDFS_SITE > $HDFS_SITE.tmp
 mv $HDFS_SITE.tmp $HDFS_SITE
