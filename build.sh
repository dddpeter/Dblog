echo "Start build jar..."
gradle build
echo "Finished!"
echo "Start build docker image..."
docker build -t registry.cn-hangzhou.aliyuncs.com/dddpeter/dddpeter/dblog:1.0 .
echo "Finished!"