### ssh
```
ssh -p 60022 -t root@192.168.0.44 "cd /opt/logstash-package-deploy/logstash_order/test; sh cover-config.sh"
```
-t 可以传递 ctrl-c