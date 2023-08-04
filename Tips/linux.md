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
* * * * * echo hello >> /root/hello4.txt

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

### 查看磁盘大小
 df -hl 

### 查看cpu
总核数 = 物理CPU个数 X 每颗物理CPU的核数 
总逻辑CPU数 = 物理CPU个数 X 每颗物理CPU的核数 X 超线程数

查看物理CPU个数
cat /proc/cpuinfo| grep "physical id"| sort| uniq| wc -l

查看每个物理CPU中core的个数(即核数)
cat /proc/cpuinfo| grep "cpu cores"| uniq

查看逻辑CPU的个数
cat /proc/cpuinfo| grep "processor"| wc -l


### tcp 端口（centos）
firewall-cmd --query-port=14343/tcp
firewall-cmd --add-port=14343/tcp --permanent
firewall-cmd --remove-port=14343/tcp --permanent

firewall-cmd --reload

### centos 日志打印乱码问题
```
cd ~
vi .bashrc
export LC_CTYPE='zh_CN.UTF-8'
	如果提示没有zh_CN.UTF-8， 就安装： yum install -y langpacks-zh_CN
source .bashrc
```


### 查看端口被占用
ss -tnlp | grep 8080


### 增加swap空间
查看(如果该命令没有返回出结果，则代表该系统尚未配置过swap。)：
swapon -s

sudo fallocate -l 4G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile

永久生效：
sudo nano /etc/fstab
末尾添加
/swapfile   swap    swap    sw  0   0



https://blog.csdn.net/zstack_org/article/details/53258588


### 免密登录

ssh-keygen
scp id_rsa.pub root@xxxx
id_rsa.pub >> ~/.ssh/authorized_keys


### tree 命令安装
wget ftp://mama.indstate.edu/linux/tree/tree-1.8.0.tgz
tar -zxvf tree-1.8.0.tgz
cd tree-1.8.0
make install

### 解压
1、*.tar 用 tar –xvf 解压
2、*.gz 用 gzip -d或者gunzip 解压
3、*.tar.gz和*.tgz 用 tar –xzf 解压
4、*.bz2 用 bzip2 -d或者用bunzip2 解压
5、*.tar.bz2用tar –xjf 解压
6、*.Z 用 uncompress 解压
7、*.tar.Z 用tar –xZf 解压
8、*.rar 用 unrar e解压
9、*.zip 用 unzip 解压

### 查看网速工具
sudo nethogs -a wlo1
其中wlo1为网卡名称


### 更改环境变量
~/.bashrc
/etc/profile


### sed 正则表达式获取括号中的值
\1  \2
sed 's/dfad/\1/g


### feem 局域网传输
appImage文件， 赋予可执行权限直接执行


### 制作服务
cat >>/usr/lib/systemd/system/node_exporter.service <<EOF
[UNIT]
Description=node_exporter
After=network.target
[Service]
Type=simple
ExecStart=/opt/tools/node_exporter/node_exporter
ExecReload=
ExecStop=
PrivateTmp=True
[Install]
WantedBy=multi-user.target
EOF

systemctl daemon-reload


### tmux
http://c.biancheng.net/linux/tmux.html
分屏: https://blog.csdn.net/xiaochonghao/article/details/69397564



### cron
linux 的cron 不管命令执行多久, 都会到点执行
logstash jdbc 插件中的schedule, 如果有一次卡住了, 不会执行之后的, 后边的会等前一个执行完再立即执行, 并且, 会把之前应该执行的次数短时间内全都补回来(<1s?)



### linux x11 clipboard
https://github.com/exebook/x11clipboard/blob/master/x11copy.c



### top by name
pidof server client |awk '{for(i=1;i<=NF;i++){ printf $i; if(i!=NF) printf ",";}}' |xargs terminator -x top -p




### 正则表达式
https://regex101.com/


### 开启自动执行某个脚本
     

启动脚本举例（文件名为autostart.sh）：

#!/bin/bash
#chkconfig: 2345 80 90
#description:auto_run
/home/myproject/start.sh

    文件内部前三行的意义可参考《服务不支持chkconfig的解决方法》[2]

    将脚本移动到/etc/rc.d/init.d目录下

mv  autostart.sh /etc/rc.d/init.d

    增加脚本的可执行权限

chmod 755 /etc/rc.d/init.d/autostart.sh

    添加脚本到开机自动启动项目中
cd /etc/rc.d/init.d
chkconfig --add autostart.sh
chkconfig autostart.sh on


参考链接
[1] 【centos7】添加开机启动服务/脚本
[2] 服务不支持 chkconfig 的解决方法  https://www.cnblogs.com/areyouready/p/8857807.html

https://blog.csdn.net/qq_29695701/article/details/89254282




### keycode对应
xev
xbindkeys -k



### signal
signal的handler相同, 则等上一个完成在进行下一个
handler不同, 则中断上一个, 开启这个, 等这个执行完, 再回到上一个
对于read被打断, 则之前read的会清空, 然后从信号处理结束之后再输入的才会被读进去



### epoll
https://mp.weixin.qq.com/s/OmRdUgO1guMX76EdZn11UQ?utm_source=pocket_mylist



### 尽量少使用swap
```
1.查看当前swappiness值
　$ cat /proc/sys/vm/swappiness
2.修改swappiness值为10（临时修改，重启后即还原为默认值）
　$ sudo sysctl vm.swappiness=10

3.永久修改swappiness默认值（重启生效）
$ sudo gedit /etc/sysctl.conf
在文档的最后加上:
　　vm.swappiness=10
```
https://blog.csdn.net/qq_37968132/article/details/81584027



### 磁盘修复
https://blog.csdn.net/weixin_30478757/article/details/95736756



### x11 compositors 原理
https://dev.to/l04db4l4nc3r/compositors-in-linux-1hhb

### 查看端口占用(全)
sudo netstat -tunlp | grep 80


### 定时休眠, 定时启动
执行这条命令会立即休眠, 并在20s后启动
sudo rtcwake -v -s 20 -m mem

制定时间启动
rtcwake -v -t `date -d 10:53 +%s` -m mem
