

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

### 打镜像
https://zhuanlan.zhihu.com/p/122380334#:~:text=%E5%88%B6%E4%BD%9CDocker%E9%95%9C%E5%83%8F%E4%B8%80%E8%88%AC%E6%9C%892%E7%A7%8D%E6%96%B9%E6%B3%95%EF%BC%9A,%E4%BD%BF%E7%94%A8hub%E4%BB%93%E5%BA%93%E4%B8%AD%E5%B7%B2%E6%9C%89%E7%9A%84%E7%8E%AF%E5%A2%83%EF%BC%8C%E5%AE%89%E8%A3%85%E8%87%AA%E5%B7%B1%E4%BD%BF%E7%94%A8%E7%9A%84%E8%BD%AF%E4%BB%B6%E7%8E%AF%E5%A2%83%E5%90%8E%E5%AE%8C%E6%88%90image%E5%88%9B%E5%BB%BA%20%E9%80%9A%E8%BF%87Dockerfile%EF%BC%8C%E5%AE%8C%E6%88%90%E9%95%9C%E5%83%8Fimage%E7%9A%84%E5%88%9B%E5%BB%BA




### 保存镜像
```
docker run -it centos /bin/bash
docker ps 查看正在运行的container, 记录container_id
docker cp ./xxxx.tar <container_id>:/xxx/xxx
docker commit -m "upload" -a "beyond" 9d8e0e126419 beyond/xxxx:v1
```

