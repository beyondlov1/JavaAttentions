

### yum 安装
问题:
Cannot prepare internal mirrorlist: No URLs in mirrorlist
解决:
```
1、将源文件备份 
cd /etc/yum.repos.d/ && mkdir backup && mv *repo backup/ 
2、下载阿里源文件 
curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-8.repo 

3、更新源里面的地址 
sed -i -e "s|mirrors.cloud.aliyuncs.com|mirrors.aliyun.com|g " /etc/yum.repos.d/CentOS-*
sed -i -e "s|releasever|releasever-stream|g" /etc/yum.repos.d/CentOS-* 

4、生成缓存 
yum clean all && yum makecache
```
参考:https://blog.csdn.net/qq_35002542/article/details/123094232



### yum 安装openjdk
https://blog.csdn.net/piaoranyuji/article/details/114019696

