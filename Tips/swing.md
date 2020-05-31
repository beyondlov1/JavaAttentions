#### canvas反转纵坐标

canvas是用graphics来进行画图, 默认的坐标是朝下的, 只有正值

可以通过AfflineTransform 这个类来进行坐标的变换

```java
AffineTransform transform = AffineTransform.getScaleInstance(1,-1);
transform.translate(0,-this.getHeight());
Graphics2D g1 = (Graphics2D) super.getGraphics();
g1.setTransform(transform);
```

或者

```java
AffineTransform transform = new AffineTransform(1.0,0,0,1.0,0,0,0);
transform.translate(0,this.getHeight());
transform.scale(1, -1);
Graphics2D g1 = (Graphics2D) super.getGraphics();
g1.setTransform(transform);
```

注: Graphics中没有setTransform的方法, 要转化成它的子类Graphics2D

参考: https://www.youtube.com/watch?v=mZ0qBfEc0fg

demo: JumpingBoy

#### Canvas中的Graphics为空

Canvas中的Graphics实在添加到JFrame后才会初始化, 否则为null

#### 启动时Component获得焦点

component.setFocusable(true);