#### ie10/11 ajax请求缓存机制有问题
解决方法: js中添加
$.ajaxSetup({cache:false});

#### 解决 IE8、IE9 不支持 console 问题
window.console = window.console || (function () {
   var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile
   = c.clear = c.exception = c.trace = c.assert = function () { };
   return c;
})();

#### ie8/9 兼容问题
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE">

#### ie8 css3兼容
用jquery的css方法
