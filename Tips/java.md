#### java利用反射修改类的静态变量

```
public static void resetStaticVar()
{

    Field[] fields= Student.class.getDeclaredFields();

    for(Field field:fields)
    {
        if(Modifier.isStatic(field.getModifiers()))
        {
            if(field.getType()==int.class)
            {
                field.set(null,0);
            }
            else if(field.getType()==float.class)
            {
                field.set(null,0.0);
            }
            else if(field.getType()=double.class)
            {
                field.set(null,0.0D);
            }
            else if(field.getType()==boolean.class)
            {
                field.set(null,false);
            }
            else
            {
                Object val= field.getType().newInstance();
                field.set(null,val);
            }
        }
    }

}
```

参考：https://www.cnblogs.com/limingluzhu/p/5975524.html



#### 将jar打包成exe

执行起来基本和jar执行是一样的， 比如读取外部配置文件什么的

方法: https://www.cnblogs.com/duwanjiang/p/6390379.html  (这个里面有添加库的引导)

https://blog.csdn.net/jinny_0019/article/details/80065452 （这个里有选64位jre的引导）


### zero copy
https://developer.ibm.com/articles/j-zerocopy/

### java报错: 找不到或无法加载主类
https://blog.csdn.net/snowlive/article/details/54880537

### 获取源码根目录
(new File("")).getAbsolutePath()  得到的是项目根目录

### 查看对象个数
jconsole或者jvisualVm 堆dump

### java 对象大小
https://blog.csdn.net/jiafu1115/article/details/7017719


### dump 内存
jmap -dump:live,format=b,file=dump.hprof 124474
查看: jprofiler


### 反射
mybatis
SystemMetaObject.forObject(config).getValue("name");

### double 精度丢失
C: DBL_EPSILON
java: java.lang.Math 包和 Math.ulp() 

