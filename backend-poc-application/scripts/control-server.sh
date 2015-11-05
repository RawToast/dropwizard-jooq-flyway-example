#!/bin/bash
 
ARTIFACT=backend-poc
CONFIG_FILE=$2
 
StartUp()
{
if [ ! -f $CONFIG_FILE ];
then
echo "Configfile $CONFIG_FILE doesn't exist!"
exit
fi

echo "Starting Dropwizard!"
mkdir -p logs
nohup java -jar target/${ARTIFACT}*SNAPSHOT.jar \
server $CONFIG_FILE > logs/stdout.${ARTIFACT}.log 2>&1 </dev/null &
    echo "Service Started"
}
 
ShutDown()
{
  echo "Shutting down"
  pkill -f ${ARTIFACT}*SNAPSHOT.jar
  _retval=$?
  if [ ${_retval} -ne 0 ] ; then
     echo "Failed to send kill signal!"
     exit ${_retval}
  fi
}
 
SCRIPT_ACTION=$1
case ${SCRIPT_ACTION} in
 start)
 StartUp
 _retval=$?
 ;;
 stop)
 ShutDown
 _retval=$?
 ;;
 *)
 echo "Usage:"
 echo "$0 start <configfile>|stop"
 ;;
esac