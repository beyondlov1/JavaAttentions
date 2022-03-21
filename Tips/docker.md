

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

