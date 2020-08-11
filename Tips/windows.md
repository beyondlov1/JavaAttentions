### 查询端口号占用

```
netstat -aon|findstr "8081"
最后一列为pid
杀死pid:
taskkill /T /F /PID 9088 
```

参考: https://www.runoob.com/w3cnote/windows-finds-port-usage.html
