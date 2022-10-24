#!/bin/bash
date_stamp=`date "+%Y-%m-%d-%H-%M-%S"`
echo $date_stamp
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo $DIR
mv $DIR/gzbd-0.0.1.jar $DIR/yypj.jar
# 创建备份位置
if [ ! -d /home/gsidc/bak/gzbd ];then
  mkdir -p /home/gsidc/bak/gzbd
fi
# 拷备历史版本
cp /home/gsidc/app/gzbd-jkzx-8899/gzbd.jar /home/gsidc/bak/gzbd-jkzx/yypj$date_stamp.jar
#关闭服务器
id=$(ps -ef|grep yypj|grep -v grep|awk '{print $2}')
if [ ! $id ]; then echo "process not started"; else kill -9 $id;fi
sleep 1
#删除并添加新包
rm -rf /home/gsidc/app/gzbd-jkzx-8899/yypj*
cp $DIR/yypj.jar /home/gsidc/app/gzbd-jkzx-8899
#启动服务器
/usr/java/jdk1.8.0_202/bin/java -version
nohup /usr/java/jdk1.8.0_202/bin/java -jar /home/gsidc/app/gzbd-jkzx-8899/yypj.jar >> /home/gsidc/app/yypj-web-8899/output.log 2>&1 &
sleep ${logtime:-30}
cat /home/gsidc/app/gzbd-jkzx-8899/output.log