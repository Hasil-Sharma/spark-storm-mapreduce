source $REMOTE_SCRIPT_FOLDER/constants.sh
# issue with sudo and cat

FILE=$HADOOP_PREFIX_FILE
cat <<EOM > /tmp/new.file
HADOOP_PREFIX=${HADOOP_PREFIX}
HADOOP_CONF_DIR=${HADOOP_CONF_DIR}
HADOOP_YARN_HOME=${HADOOP_PREFIX}
export HADOOP_PREFIX
export HADOOP_CONF_DIR
EOM

sudo mv /tmp/new.file $HADOOP_PREFIX_FILE
