### install

```
sudo apt-get install vim
sudo apt-get install python3-pip
sudo pip3 install shadowsocks
sudo mkdir /etc/shadowsocks
sudo vim /etc/shadowsocks/shadowsocks.json
shdowsocks.json:
{
  "server": "162.245.239.68",
  "server_port": 34567,
  "local_port": 1080,
  "password": "ntdtv.com",
  "timeout": 600,
  "method": "RC4"
}
https://github.com/Alvin9999/new-pac/wiki/ss%E5%85%8D%E8%B4%B9%E8%B4%A6%E5%8F%B7
sudo vim /usr/local/lib/python3.8/dist-packages/shadowsocks/crypto/openssl.py
replace EVP_CIPHER_CTX_cleanup to EVP_CIPHER_CTX_reset
ref:https://blog.csdn.net/wssxy/article/details/90723409
sudo sslocal -c /etc/shadowsocks/shadowsocks.json -d start

sudo sslocal -c /etc/shadowsocks/shadowsocks.json -d stop
```

### centos 开放ss防火墙端口
iptables -A INPUT -p tcp --dport 14343 -j ACCEPT
以 14343 为例


### 配置http代理
配置全局代理

启动shawdowsocks服务后，发现并不能翻墙上网，这是因为shawdowsocks是socks 5代理，需要客户端配合才能翻墙。

为了让整个系统都走shawdowsocks通道，需要配置全局代理，可以通过polipo实现。

首先是安装polipo：
```
sudo apt-get install polipo
```

接着修改polipo的配置文件/etc/polipo/config：
```
logSyslog = true
logFile = /var/log/polipo/polipo.log

proxyAddress = "0.0.0.0"

socksParentProxy = "127.0.0.1:1080"
socksProxyType = socks5

chunkHighMark = 50331648
objectHighMark = 16384

serverMaxSlots = 64
serverSlots = 16
serverSlots1 = 32
```

重启polipo服务：

```
sudo /etc/init.d/polipo restart
```

为终端配置http代理：

```
export http_proxy="http://127.0.0.1:8123/"
```


接着测试下能否翻墙：

curl www.google.com

如果有响应，则全局代理配置成功。
注意事项

服务器重启后，下面两句需要重新执行：
sudo sslocal -c shawdowsocks.json -d start
export http_proxy="http://127.0.0.1:8123/"

https://jingsam.github.io/2016/05/08/setup-shadowsocks-http-proxy-on-ubuntu-server.html