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


### if 判断
[ -a FILE ]  如果 FILE 存在则为真。 
[ -b FILE ]  如果 FILE 存在且是一个块特殊文件则为真。 
[ -c FILE ]  如果 FILE 存在且是一个字特殊文件则为真。 
[ -d FILE ]  如果 FILE 存在且是一个目录则为真。 
[ -e FILE ]  如果 FILE 存在则为真。 
[ -f FILE ]  如果 FILE 存在且是一个普通文件则为真。 
[ -g FILE ] 如果 FILE 存在且已经设置了SGID则为真。 [ -h FILE ]  如果 FILE 存在且是一个符号连接则为真。 
[ -k FILE ]  如果 FILE 存在且已经设置了粘制位则为真。 
[ -p FILE ]  如果 FILE 存在且是一个名字管道(F如果O)则为真。 
[ -r FILE ]  如果 FILE 存在且是可读的则为真。 
[ -s FILE ]  如果 FILE 存在且大小不为0则为真。 
[ -t FD ]  如果文件描述符 FD 打开且指向一个终端则为真。 
[ -u FILE ]  如果 FILE 存在且设置了SUID (set user ID)则为真。 
[ -w FILE ]  如果 FILE 如果 FILE 存在且是可写的则为真。 
[ -x FILE ]  如果 FILE 存在且是可执行的则为真。 
[ -O FILE ]  如果 FILE 存在且属有效用户ID则为真。 
[ -G FILE ]  如果 FILE 存在且属有效用户组则为真。 
[ -L FILE ]  如果 FILE 存在且是一个符号连接则为真。 
[ -N FILE ]  如果 FILE 存在 and has been mod如果ied since it was last read则为真。 
[ -S FILE ]  如果 FILE 存在且是一个套接字则为真。 
[ FILE1 -nt FILE2 ]  如果 FILE1 has been changed more recently than FILE2, or 如果 FILE1 exists and FILE2 does not则为真。 
[ FILE1 -ot FILE2 ]  如果 FILE1 比 FILE2 要老, 或者 FILE2 存在且 FILE1 不存在则为真。 
[ FILE1 -ef FILE2 ]  如果 FILE1 和 FILE2 指向相同的设备和节点号则为真。 
[ -o OPTIONNAME ]  如果 shell选项 “OPTIONNAME” 开启则为真。 
[ -z STRING ]  “STRING” 的长度为零则为真。 
[ -n STRING ] or [ STRING ]  “STRING” 的长度为非零 non-zero则为真。 
[ STRING1 == STRING2 ]  如果2个字符串相同。 “=” may be used instead of “==” for strict POSIX compliance则为真。 
[ STRING1 != STRING2 ]  如果字符串不相等则为真。
[ STRING1 < STRING2 ]  如果 “STRING1” sorts before “STRING2” lexicographically in the current locale则为真。 
[ STRING1 > STRING2 ]  如果 “STRING1” sorts after “STRING2” lexicographically in the current locale则为真。 
[ ARG1 OP ARG2 ] “OP” is one of -eq, -ne, -lt, -le, -gt or -ge. These arithmetic binary operators return true if “ARG1” is equal to, not equal to, less than, less than or equal to, greater than, or greater than or equal to “ARG2”, respectively. “ARG1” and “ARG2” are integers.

ref: https://blog.csdn.net/sunboy_2050/article/details/5904632


### 数组排序
sort -t : -k 2
sort是对多行字符串排序, -k 2 代表永第二列排序, -t : 代表用冒号分割每一行
-r 是反向排序
参考: https://blog.csdn.net/monkeyduck/article/details/10097829

数组排序: 
```
IFS=$'\n' sorted=($(sort <<<"${array[*]}"))
unset IFS
```
IFS=$'\n'表示array[*]打印时用\n分割
参考: https://qastack.cn/programming/7442417/how-to-sort-an-array-in-bash

```
IFS=$'\n' sorted=($(sort -r -k 1 -t : <<<"${depth_win_arr[*]}"))
unset IFS
```

### 全文搜索
grep -r "xxx" ./*

### auto complete
```
#! /bin/bash

_go()
{
    COMPREPLY=()

    if test -f "/tmp/go.list"
    then
        local -A gomap;
        while read line; do
            kv_arr=($line)
            # echo $line
            # echo ${kv_arr[0]}
            # echo $1
            gomap[${kv_arr[0]}]=${kv_arr[1]}
            if [[ ${kv_arr[0]} == $2* ]];
            then
                # echo ${kv_arr[1]}
                COMPREPLY[${#COMPREPLY[*]}]=${kv_arr[0]}
            fi
        done < /tmp/go.list
    fi

    return 0
}

# _go $1
complete -F _go go.sh
complete -F _go go
complete -F _go codedir

```

```
#!/usr/bin/bash

function _myscript(){
    COMP_FILE=$(find /home/beyond/github/JavaAttentions/Tips/ -maxdepth 1 -type f -print | xargs -n1 basename)
    COMPREPLY=($(compgen -W "${COMP_FILE}" ${COMP_WORDS[${COMP_CWORD}]}))
}

complete -F _myscript tip
```

参考: https://www.somata.net/2020/bash_completion_script.html
