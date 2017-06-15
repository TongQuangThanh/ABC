export SELENIUM_PACKAGE=`ls -tr *sele*.jar | tail -1`
echo `date` " - Starting '$SELENIUM_PACKAGE' as hub role"
java -jar ${SELENIUM_PACKAGE} -port 4444 -role hub -browserTimeout 36000 -timeout 36000
