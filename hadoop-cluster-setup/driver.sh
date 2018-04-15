source ./constants.sh

chmod 400 $KEY

for instance in "${aws_instances[@]}"
do
  ssh -o "StrictHostKeyChecking no" $instance "rm -rf $REMOTE_SCRIPT_FOLDER;mkdir -p $REMOTE_SCRIPT_FOLDER"
  scp -o "StrictHostKeyChecking no" ./constants.sh $USER@$instance:$REMOTE_SCRIPT_FOLDER/constants.sh
  scp -o "StrictHostKeyChecking no" $KEY $USER@$instance:$REMOTE_SCRIPT_FOLDER/$KEY
  echo -e "${RED}For: $instance${NC}"
  for point;
  do
    if [ "$point" == "$NAMENODE_SCRIPT" ]; then
      if [ "$instance" == "$NAMENODE" ]; then
        echo -e "1. ${RED}Running $point script${NC}"
        ssh -o "StrictHostKeyChecking no" $USER@$instance "REMOTE_SCRIPT_FOLDER=$REMOTE_SCRIPT_FOLDER bash -s" < $point
      fi
    elif [ "$point" == "$DATANODE_SCRIPT" ]; then
      if [ ! "$instance" == "$NAMENODE" ] && [ ! "$instance" == "$RESOURCE_MANAGER" ]; then
        echo -e "2. ${RED}Running $point script${NC}"
        ssh -o "StrictHostKeyChecking no" $USER@$instance "REMOTE_SCRIPT_FOLDER=$REMOTE_SCRIPT_FOLDER bash -s" < $point
      fi
    elif [ "$point" == "$RESOURCE_MANGER_SCRIPT" ]; then
      if [ "$instance" == "$RESOURCE_MANAGER" ]; then
        echo -e "3. ${RED}Running $point script${NC}"
        ssh -o "StrictHostKeyChecking no" $USER@$instance "REMOTE_SCRIPT_FOLDER=$REMOTE_SCRIPT_FOLDER bash -s" < $point
      fi
    elif [ "$point" == "$NODE_MANAGER_SCRIPT" ]; then
      if [ ! "$instance" == "$NAMENODE" ] && [ ! "$instance" == "$RESOURCE_MANAGER" ]; then
        echo -e "4. ${RED}Running $point script${NC}"
        ssh -o "StrictHostKeyChecking no" $USER@$instance "REMOTE_SCRIPT_FOLDER=$REMOTE_SCRIPT_FOLDER bash -s" < $point
      fi
    else
      echo -e "5. ${RED}Running $point script${NC}"
      ssh -o "StrictHostKeyChecking no" $USER@$instance "REMOTE_SCRIPT_FOLDER=$REMOTE_SCRIPT_FOLDER bash -s" < $point
    fi
  done
done
