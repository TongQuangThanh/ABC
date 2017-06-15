@echo off
set "BASE_DIR=%cd%"

cd %APPIUM_HOME%

%APPIUM_HOME:~0,2%

start node.exe node_modules\appium\bin\appium.js --session-override --nodeconfig %BASE_DIR%\appium_Node1.json -p 4723 --platform-name Android1 --platform-version 23 --automation-name Appium --app %BASE_DIR%\Calculator.apk
