export SELENIUM_PACKAGE=`ls -tr *sele*.jar | tail -1`
echo `date` " - Starting '$SELENIUM_PACKAGE' as chrome node role"
java -jar ${SELENIUM_PACKAGE} -role node -hub http://127.0.0.1:4444/grid/register -browser browserName=chrome,version=ANY,platform=LINUX,maxInstances=10 -port 9021 -Dwebdriver.chrome.driver=..\drivers\chromedriver_linux64