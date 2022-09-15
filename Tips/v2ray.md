

### v2ray
```
docker run -d \
  --restart=always \
  --privileged \
  --network=host \
  --name v2raya \
  -e V2RAYA_ADDRESS=0.0.0.0:2017 \
  -v /lib/modules:/lib/modules \
  -v /etc/resolv.conf:/etc/resolv.conf \
  -v /etc/v2raya:/etc/v2raya \
  mzz2017/v2raya
```
```
从浏览器访问 localhost:2017
然后从页面上配置即可
```
https://yaocc.cc/2021/09/27/%E4%B9%9F%E8%AE%B8%E6%98%AF%E6%9C%80%E4%BE%BF%E6%8D%B7%E7%9A%84SS%E5%AE%A2%E6%88%B7%E7%AB%AF/

