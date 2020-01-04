### 虚拟机安装 centos

iso: http://isoredirect.centos.org/centos/7/isos/x86_64/CentOS-7-x86_64-DVD-1810.iso

联网教程: https://www.jianshu.com/p/0d4a365ef350

开启网卡 & 查看IP: https://blog.csdn.net/dancheren/article/details/73611878

联网:https://blog.csdn.net/dancheren/article/details/73611878, 不要关闭(https://www.jianshu.com/p/0d4a365ef350)所说的DNS
只用https://blog.csdn.net/dancheren/article/details/73611878  就行了

### centos vm 固定ip
https://blog.csdn.net/zsg88/article/details/75095229


### 定时任务
crontab -l 查看任务
crontab -e 编辑/添加任务

以上命令修改的其实是  /var/spool/cron 下的用户名文件

systemctl status crond.service

参考:https://blog.csdn.net/xiyuan1999/article/details/8160998


### shell 脚本
files=($(ls /root))
for file in ${files[@]);do
	echo ${file}
done

files=($(ls /root))
for i in ${!files[@]);do
	echo ${files[i]}
done

sed -i '1,$s/old/new/g' test.txt # 修改原始文件

${str//old/new} 替换

默认分隔符: 空格 制表符 换行