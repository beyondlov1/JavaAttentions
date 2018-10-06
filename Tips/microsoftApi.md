#### 认证

要使用microsoft graph api , 首先要认证 , 首先要到	

https://login.microsoftonline.com/common/oauth2/v2.0/authorize 获取code

再用code 到

https://login.microsoftonline.com/common/oauth2/v2.0/token 获取token_access

之后再将token 加入到 Header 的 Authorize.. 头中

#### java库

scribejava

这个库不支持微软2.0版本, 需要将一版本的改一改, 

而且这个5.6 版本的微软的service也不对, 要将Accept头进行更改, 感觉是写错了

参考;NoteCloud2的test中