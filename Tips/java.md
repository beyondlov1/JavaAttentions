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