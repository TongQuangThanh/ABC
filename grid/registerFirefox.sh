export SELENIUM_PACKAGE=`ls -tr *sele*.jar | tail -1`
echo `date` " - Starting '$SELENIUM_PACKAGE' as firefox node role"
java -jar ${SELENIUM_PACKAGE} -role node -hub http://127.0.0.1:4444/grid/register -port 9020 -browser browserName=firefox,version=ANY,platform=LINUX,maxInstances=10