#### 安装
https://android-sdk.en.softonic.com/?ex=BB-682.3  下载SDK
SDK Manager 下载相关包
配置环境变量 ： https://blog.csdn.net/Rflyee/article/details/8973529

#### gradle 配置中国镜像
https://www.cnblogs.com/a8457013/p/8408196.html



#### 弹出输入法

```java
    private void showKeyboard(final View view) {
        //要设定延迟，延迟不可以是0，不然弹不出来
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager)      view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    view.requestFocus();
                    inputMethodManager.showSoftInput(view, 0);
                }
            }
        }, 300);
    } 
```
#### 控制view大小

```
//控制控件 , 控制的是内部的内容把外边撑起来
private void controlViewSize(EditText dialogEditText) {
    DisplayMetrics dm = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
    dialogEditText.setLayoutParams(new FrameLayout.LayoutParams(dm.widthPixels,(int) (dm.heightPixels*0.685)));
}
```

#### 架构

##### MVP

model view presenter

在view中有presenter的引用, model相当于service, presenter中有另外两个的引用, 相当于调度器

##### 事件总线 EventBus

负责控件或者后台线程之间的通信

##### 依赖注入 Dagger2

Component用来注入,  Model+providers用来生产Bean, Inject 用来确定注入位置

