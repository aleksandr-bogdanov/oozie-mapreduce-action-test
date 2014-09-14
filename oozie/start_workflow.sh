#!/bin/bash

OOZIE_URL=http://192.168.74.129:11000/oozie

HDFS_APPLICATION_PATH="/user/abogdanov/oozie"
FS_APPLICATION_PATH="/home/abogdanov/oozie"

#
# Put all configs and libs to HDFS since Oozie can only use configs and libs located on HDFS.
#

# Delete a directory
hadoop fs -rm -R ${HDFS_APPLICATION_PATH}
# Creating a directory with lib dir inside
hadoop fs -mkdir -p ${HDFS_APPLICATION_PATH}/lib
# Put configs in that dir
hadoop fs -put ${FS_APPLICATION_PATH}/* ${HDFS_APPLICATION_PATH}		
# hadoop fs -put ${FS_APPLICATION_PATH}/lib/*.jar ${HDFS_APPLICATION_PATH}/lib

#
# Run the Oozie job itself.
#

oozie job \
-oozie ${OOZIE_URL} \
-config ${FS_APPLICATION_PATH}/job.properties \
-DHDFS_APPLICATION_PATH=${HDFS_APPLICATION_PATH} \
-run