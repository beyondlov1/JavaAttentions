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
