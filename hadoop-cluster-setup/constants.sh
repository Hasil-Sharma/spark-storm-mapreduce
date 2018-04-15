declare -a aws_instances=(
  ec2-54-201-77-204.us-west-2.compute.amazonaws.com
  ec2-52-32-206-234.us-west-2.compute.amazonaws.com
  ec2-34-215-135-50.us-west-2.compute.amazonaws.com
  ec2-34-214-211-119.us-west-2.compute.amazonaws.com
)


KEY=thinking-athena.pem
USER=ec2-user
RESOURCE_MANAGER=${aws_instances[0]}
NAMENODE=${aws_instances[0]}

NAMENODE_PORT=hdfs://${NAMENODE}:9000

RED='\033[0;31m'
NC='\033[0m' # No Color

NAMENODE_SCRIPT=setup_namenode.sh
DATANODE_SCRIPT=setup_datanode.sh
RESOURCE_MANGER_SCRIPT=setup_resourcemanager.sh
NODE_MANAGER_SCRIPT=setup_nodemanager.sh
REMOTE_SCRIPT_FOLDER=/home/$USER/TEMP

HADOOP_DOWNLOAD_URL=http://apache.claz.org/hadoop/common/stable/hadoop-2.9.0.tar.gz
HADOOP_ZIP=hadoop-2.9.0.tar.gz
HADOOP_DIR=hadoop-2.9.0

HADOOP_PREFIX=/home/$USER/hadoop-2.9.0
HADOOP_PREFIX_FILE=/etc/profile.d/hadoop.sh
HADOOP_CONF_DIR=${HADOOP_PREFIX}/etc/hadoop

CORE_SITE=${HADOOP_PREFIX}/etc/hadoop/core-site.xml
HDFS_SITE=${HADOOP_PREFIX}/etc/hadoop/hdfs-site.xml
YARN_SITE=${HADOOP_PREFIX}/etc/hadoop/yarn-site.xml
MAPRED_SITE=${HADOOP_PREFIX}/etc/hadoop/mapred-site.xml

IO_FILE_BUFFER_SIZE=131072

DFS_NAMENODE_NAME_DIR=/home/$USER/NAMENODE_DIR
DFS_BLOCK_SIZE=268435456
DFS_NAMENODE_HANDLER_COUNT=100

DFS_DATANODE_DATA_DIR=/home/$USER/DATANODE_DIR

YARN_RESOURCE_MANAGER_HOSTNAME=$RESOURCE_MANAGER
YARN_RESOURCE_MANAGER_SCHEDULER_CLASS=org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.FairScheduler
YARN_RESOURCE_MANAGER_MAX_ALLOC_MB=768
YARN_RESOURCE_MANAGER_MIN_ALLOC_MB=64

YARN_NODEMANGER_RESOURCE_MEMORY_MB=768
YARN_NODEMANGER_VMEM_PMEM_RATION=1.5
YARN_NODEMANGER_LOCAL_DIRS=/home/$USER/NODEMANGER_DIR
YARN_NODEMANGER_LOG_DIRS=/home/$USER/NODEMANGER_LOG_DIR
YARN_NODEMANGER_AUX_SERVICES=mapreduce_shuffle

SLAVES_FILE=${HADOOP_PREFIX}/etc/hadoop/slaves

MAPREDUCE_FRAMEWORK_NAME=yarn
