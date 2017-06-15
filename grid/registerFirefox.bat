@echo off
FOR /F "delims=|" %%I IN ('DIR "*sele*.jar" /B /O:D') DO SET NewestFile=%%I
echo %date% %time% - Starting '%NewestFile%' as firefox node role
java -jar %NewestFile% -role node -hub http://127.0.0.1:4444/grid/register -port 9020 -browser browserName=firefox,version=ANY,platform=WINDOWS,maxInstances=10