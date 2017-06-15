@echo off
FOR /F "delims=|" %%I IN ('DIR "*sele*.jar" /B /O:D') DO SET NewestFile=%%I
echo %date% %time% - Starting '%NewestFile%' as chrome node role
java  -Dwebdriver.chrome.driver=..\drivers\chromedriver.exe -jar %NewestFile% -role node -hub http://127.0.0.1:4444/grid/register -browser browserName=chrome,version=ANY,platform=WINDOWS,maxInstances=10 -port 9021
