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