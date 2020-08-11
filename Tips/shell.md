### ssh
```
ssh -p 60022 -t root@192.168.0.44 "cd /opt/logstash-package-deploy/logstash_order/test; sh cover-config.sh"
```
-t 可以传递 ctrl-c


### shell 脚本引用其他脚本
. /usr/shell/xxx.sh
xxxx

### shell 脚本中的方法
selectA(){
  arg1=$1
  return i
}
return 返回只能是数字
要获取方法中的值, 直接取就可以, 里面的变量都是全局的



