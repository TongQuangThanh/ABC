@echo off
FOR /F "delims=|" %%I IN ('DIR "*sele*.jar" /B /O:D') DO SET NewestFile=%%I
echo %date% %time% - Starting '%NewestFile%' as hub role
java -jar %NewestFile% -port 4444 -role hub -browserTimeout 36000 -timeout 36000