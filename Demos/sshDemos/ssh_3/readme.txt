加入基本的DAO，Service， 通过spring泛型注入相关对象

userAction 为普通的

userAction2 为泛型

由于userVertify..Interceptor 用到了userService ，所以2没有这个如果数据库有重名不能注册的拦截功能