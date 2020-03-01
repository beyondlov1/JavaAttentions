### install 
http://nginx.org/en/download.html

安装第三方module需要用源码make:
wget http://nginx.org/download/nginx-1.8.1.tar.gz 
tar -zxvf nginx-1.8.1.tar.gz 
cd nginx-1.8.1 
./configure --prefix=/usr/local/nginx  --add-module=../nginx-rtmp-module  --with-http_ssl_module   
make && make install

中间可能报错, 缺依赖
yum -y install gcc gcc-c++ autoconf automake make
yum -y install pcre-devel
yum -y install openssl openssl-devel


参考: https://www.cnblogs.com/suiyuewuxin/p/7256972.html
https://www.cnblogs.com/jpfss/p/9694842.html
https://blog.csdn.net/hfsu0419/article/details/7190152
