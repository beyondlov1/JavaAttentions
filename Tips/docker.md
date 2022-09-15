

杀死docker有关的容器：
docker kill $(docker ps -a -q)

删除所有docker容器：
docker rm $(docker ps -a -q)

删除所有docker镜像：
docker rmi $(docker images -q)

停止 docker 服务：
systemctl stop docker

sudo rm -rf /etc/docker
sudo rm -rf /run/docker
sudo rm -rf /var/lib/dockershim
sudo rm -rf /var/lib/docker

sudo apt-get remove docker  
sudo apt-get remove --auto-remove docker
sudo apt remove docker-ce

sudo apt-get remove docker docker-engine docker.io containerd runc
sudo apt purge docker-ce

https://blog.csdn.net/ispeasant/article/details/108405488
https://cloud.tencent.com/developer/article/1541011
https://www.runoob.com/docker/ubuntu-docker-install.html



### docker ubuntu 安装
https://docs.docker.com/engine/install/ubuntu/

### docker ubuntu 二进制安装
```
FROM ubuntu
COPY docker-20.10.9.tgz /opt
WORKDIR /opt
RUN tar xzvf docker-20.10.9.tgz && cp docker/* /usr/bin/
RUN apt update
RUN apt install -y iptables
CMD ["dockerd &"]
```

### 打镜像
https://zhuanlan.zhihu.com/p/122380334#:~:text=%E5%88%B6%E4%BD%9CDocker%E9%95%9C%E5%83%8F%E4%B8%80%E8%88%AC%E6%9C%892%E7%A7%8D%E6%96%B9%E6%B3%95%EF%BC%9A,%E4%BD%BF%E7%94%A8hub%E4%BB%93%E5%BA%93%E4%B8%AD%E5%B7%B2%E6%9C%89%E7%9A%84%E7%8E%AF%E5%A2%83%EF%BC%8C%E5%AE%89%E8%A3%85%E8%87%AA%E5%B7%B1%E4%BD%BF%E7%94%A8%E7%9A%84%E8%BD%AF%E4%BB%B6%E7%8E%AF%E5%A2%83%E5%90%8E%E5%AE%8C%E6%88%90image%E5%88%9B%E5%BB%BA%20%E9%80%9A%E8%BF%87Dockerfile%EF%BC%8C%E5%AE%8C%E6%88%90%E9%95%9C%E5%83%8Fimage%E7%9A%84%E5%88%9B%E5%BB%BA




### 保存镜像
```
docker run -it centos /bin/bash
docker ps 查看正在运行的container, 记录container_id
docker cp ./xxxx.tar <container_id>:/xxx/xxx
docker commit -m "upload" -a "beyond" 9d8e0e126419 beyond/xxxx:v1
```



### 删除所有已停止的container
docker container ls -a --filter status=exited --filter status=created
docker system prune



<<<<<<< HEAD
### docker systemctl
docker run -it centos /bin/bash 这种方式不会启动systemctl, 需要使用下面的方式启动
docker run --privileged=true -d beyond/canal-adapter-rabbitmq:v2 /usr/sbin/init



### centos mariadb
https://blog.csdn.net/TengYu456/article/details/115599479
=======
1、搜索mysql5.7镜像
docker search mysql5.7.18

2、拉取镜像
docker pull docker.io/sandou/mysql5.7.18

3、查看镜像
docker images

4、新建映射目录
mkdir /opt/mysql63307
mkdir /etc/mysql/   #创建my.cnf映射目录
vim /etc/mysql/my.cnf

5、运行镜像
docker run -d -p 63307:63307 -v /etc/mysql/:/etc/mysql/conf.d/ -v /opt/mysql63307:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql_test docker.io/sandou/mysql5.7.18

6、进入docker
docker exec -it mysql bash

>>>>>>> 97def5a394b3d0d90debb0b7faac7c7d68e43e37


### 查看docker容器ip
```
docker inspect NAMES 
# 查看容器所有状态信息；

docker inspect --format='{{.NetworkSettings.IPAddress}}'
# 查看 容器ip 地址

docker inspect --format '{{.Name}} {{.State.Running}}'
# 容器运行状态
```
https://www.php.cn/docker/458430.html#:~:text=Docker%E6%80%8E%E4%B9%88%E6%9F%A5%E7%9C%8B%E5%AE%B9%E5%99%A8IP%E5%9C%B0%E5%9D%80%EF%BC%9F%20%E6%9F%A5%E7%9C%8B%E6%96%B9%E6%B3%95%EF%BC%9A1%E3%80%81%E4%BD%BF%E7%94%A8%E2%80%9Cdocker%20inspect%20--format%3D%27%20%7B%20%7B.NetworkSettings.IPAddress%7D%7D%27%E2%80%9D%E5%91%BD%E4%BB%A4%EF%BC%9B2%E3%80%81%E4%BD%BF%E7%94%A8%E2%80%9Cdocker,exec%20-it%20ID%2FNAMES%20ip%20addr%E2%80%9D%E5%91%BD%E4%BB%A4%E3%80%82%20%E6%9F%A5%E7%9C%8BDocker%E7%9A%84%E5%BA%95%E5%B1%82%E4%BF%A1%E6%81%AF%E3%80%82


### docker compose 启动前再build一次
docker compose up --build --no-deps



### docker0 网段问题
在装上docker后, 会有一个docker0的虚拟网卡, 如果container的network设置的bridge会用这个转发, host貌似也会
但是有个问题, 就是ping或者数据库连接链不了,
但是换个网段就可以了: 192.186.0.1
```
sudo vim /etc/docker/daemon.json (没有就创建)
{
    "bip":"192.168.0.1/24"
}
重启docker
```
暂未明白为什么

