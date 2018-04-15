source $REMOTE_SCRIPT_FOLDER/constants.sh
cp $HDFS_SITE $HDFS_SITE.bak

mkdir -p $DFS_DATANODE_DATA_DIR

sed -i "/<\/configuration>/i \
  <property>\
   <name>dfs.datanode.data.dir</name>\
   <value>$DFS_DATANODE_DATA_DIR</value>\
 </property>" $HDFS_SITE

xmllint --format $HDFS_SITE > $HDFS_SITE.tmp
mv $HDFS_SITE.tmp $HDFS_SITE
