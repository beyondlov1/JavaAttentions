UML类图

http://www.uml.org.cn/oobject/201610282.asp

#### 工厂模式 factory

#### 抽象工厂模式 abstractFactory

产品的接口, 产品的实现, 工厂的接口, 工厂的实现

#### 单例模式 singleton

#### 建造者模式 builder

#### 原型模式 protoType

就是利用Object的clone()方法, 在一个原型的基础上复制出一个对象 (要实现cloneable接口 )

分为浅克隆和深克隆, 浅克隆为里面的成员变量是引用的, 而深克隆是将里面的成员变量也克隆了

https://www.cnblogs.com/chenssy/p/3313339.html

#### 适配器模式 Adapter

实现两个不相容的接口(不管用继承还是实现), 然后在内部调用另一个的方法

或者将一个对象放在适配器的内部, 调用内部对象的方法

#### 桥接模式 bridge

将继承关系转成关联关系: Object: Shape Color; Cycle extend Shape, Rect extends Shape ... ; White extends Color ... 

用于由多种维度分类

http://www.cnblogs.com/chenssy/p/3317866.html

#### 过滤器模式

筛选符合条件的对象

https://www.cnblogs.com/simplexx/p/7168755.html

#### 组合模式

组合模式就是将一堆有树形结构的对象抽象成一个统一的抽象类, 通过递归的方式实现不需要区分就完成所有操作;

https://www.cnblogs.com/lfxiao/p/6816026.html

#### 装饰模式

实现相同的接口, 里面存在需装饰的对象的引用

#### facade模式

子系统统一一个接口

#### FlyWeight

如果列表中已经有了相同的对象, 就直接用哪个对象, 如果没有就创建一个(类似数据库连接池的模式, 但又不太一样)

#### 代理模式

#### 访问者模式

#### 模板模式

就是继承, 父类实现通用的方法, 其他方法由子类调用

#### 策略模式

实现一个策略接口, 可以更换策略

#### 状态模式

在状态模式中我们可以减少大块的if…else语句，它是允许态转换逻辑与状态对象合成一体，但是减少if…else语句的代价就是会换来大量的类，所以状态模式势必会增加系统中类或者对象的个数。

State类中封装方法, 在什么state, 调用什么方法



