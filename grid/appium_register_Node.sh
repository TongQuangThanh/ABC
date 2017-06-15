export HOME=`pwd`
export ENV_FILE=$1
export APP=ContactManager.apk
export DEVICE_NAME=`egrep "^deviceName" ../drivers/$ENV_FILE | awk -F '[= ] *' '{print $NF}' | tr -d '\r'`
export DEVICE_PORT=`egrep "^devicePort" ../drivers/$ENV_FILE | awk -F '[= ] *' '{print $NF}' | tr -d '\r'`
export PLATFORM_VERSION=`egrep "^platformVersion" ../drivers/$ENV_FILE | awk -F '[= ] *' '{print $NF}' | tr -d '\r'`
export PLATFORM_NAME=`egrep "^platformName" ../drivers/$ENV_FILE | awk -F '[= ] *' '{print $NF}' | tr -d '\r'`

appium --session-override --nodeconfig appium_Node1.json --app "${HOME}/${APP}" --platform-name "${PLATFORM_NAME}" --platform-version "${PLATFORM_VERSION}" --automation-name Appium --device-name "${DEVICE_NAME}" -p "${DEVICE_PORT}" --no-reset