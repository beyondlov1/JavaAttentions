



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



#### 长按弹出快速分享

```xml
<activity
    android:name=".view.ShareActivity"
    android:label="note"
    android:theme="@android:style/Theme.NoDisplay">
    <intent-filter>
        <action android:name="android.intent.action.PROCESS_TEXT"/>
        <action android:name="android.intent.action.SEND" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:mimeType="text/plain" />
    </intent-filter>
</activity>
```

PROCESS_TEXT 是快速分享 ,  SEND是一般分享. 获取内容:

```java
if ("text/plain".equals(intent.getType()) && (SEND.equals(intent.getAction()) )){
    Note note = generateNoteFromSend(intent);
    notePresenter.addNote(note);
}
if ("text/plain".equals(intent.getType()) && PROCESS_TEXT.equals(intent.getAction())){
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        Note note = generateNoteFromProcessText(intent);
        notePresenter.addNote(note);
    }
}
```

#### recyclerView插入

要有动画就要这样:  

```
noteRecyclerView.getAdapter().notifyItemInserted(0);
noteRecyclerView.getAdapter().notifyItemRangeChanged(0,data.size());
noteRecyclerView.scrollToPosition(0);
```

notifyItemInserted只会更新0 这一个, 不会管后边的item, 所以后边发生变化的item的position什么的会有问题, 要自己手动notifyItemRangeChanged

ps: 给adapter加data的时候不能 data=newData 要data.addAll(newData)

#### LayoutInflater

用来查找布局, 并且声称实例, 用来动态引用布局

坑: 有的时候会遇到什么id重复之类的, 就是用getActivity.getLayoutInflater..引起的, 用下面的更靠谱些(不知道什么原因)

```
        view = LayoutInflater.from(context).inflate(R.layout.fragment_note_detail_switcher, null);

```

参考 https://blog.csdn.net/biezhihua/article/details/43996289

#### alertDialog 中editText不能输入文字

在显现而editText的时候调用下面的内容(见com.beyond.note5.view.fragment.NoteDetailSwitcherFragment)

```
// 默认情况下，dialog布局中设置EditText，在点击EditText后输入法不能弹出来
// 将此标志位清除，则可以显示输入法
this.getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
```

#### ViewSwitcher

viewSwitcher 主要的方法:

```
viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
    @Override
    public View makeView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_note_detail, null);
        view.setMinimumHeight(1350);

        detailViewHolder = new DetailViewHolder(view);

        initDetailConfig(detailViewHolder);
        initDetailData(detailViewHolder);
        initDetailEvent(detailViewHolder);

        return view;
    }
});
```

makeView中就是返回展示的View

#### 滑动事件

有两种方式: 

1. 重写OnTouchListener, 在onTouch里面直接写逻辑

2. 利用GestureDetector,  GestureDetector.SimpleOnGestureListener这个类里面有双击事件

   参考https://www.jianshu.com/p/e0c863914ae1

```
package com.beyond.note5.view.listener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by beyond on 2019/2/2.
 */

public abstract class OnSlideListener implements View.OnTouchListener {

    private float startX;
    private float startY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                if (endX-startX>200){
                    onSlideRight();
                }else if (endX-startX<-200){
                    onSlideLeft();
                }

                float endY = event.getY();
                if (endY-startY>200){
                    onSlideDown();
                }else if (endY-startY<-200){
                    onSlideUp();
                }

                System.out.println(endX-startX);
                System.out.println(endY-startY);
                break;
        }
        return true;
    }

    protected abstract void onSlideLeft();

    protected abstract void onSlideRight();

    protected abstract void onSlideUp();

    protected abstract void onSlideDown();

    private Context context;

    public OnSlideListener(Context context){
        this.context = context;
    }

    private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

        /**
         * 双击发生时的通知
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {//双击事件
            onDoubleClick(e);
            return super.onDoubleTap(e);
        }

        /**
         * 双击手势过程中发生的事件，包括按下、移动和抬起事件
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }
    });

    protected abstract void onDoubleClick(MotionEvent e);

}
```

#### DialogFragment生命周期

DialogFragment常用的生命周期函数，调用顺序如下： onAttach -->onCreate-->onCreateDialog-->onCreateView-->onViewCreated-->onSaveInstanceState 

#### AlertDialog使按钮点击之后不关弹窗

先定义上, OnClickListner 都设成null

```
builder.setView(view)
        .setPositiveButton("OK",null)
        .setNegativeButton("Cancel",null)
        .setNeutralButton("Modify",null);
```

在之后手动调用, 这样不dismiss就不会关:

```
((AlertDialog)getDialog()).getButton(-1).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DetailViewHolder detailViewHolder = new DetailViewHolder(viewSwitcher.getCurrentView());
        if (isModifyViewShown(detailViewHolder)){
            EventBus.getDefault().post(new UpdateNoteEvent(generateModifiedNote(detailViewHolder)));
            hideModifyView(detailViewHolder);
            dismiss();
        }else{
            dismiss();
        }
    }
});
((AlertDialog)getDialog()).getButton(-2).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DetailViewHolder detailViewHolder = new DetailViewHolder(viewSwitcher.getCurrentView());
        if (isModifyViewShown(detailViewHolder)){
            detailViewHolder.displayWebView.loadData(notes.get(position).getContent(), "text/html", "UTF-8");
            hideModifyView(detailViewHolder);
        }else {
            dismiss();
        }
    }
});
((AlertDialog)getDialog()).getButton(-3).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DetailViewHolder detailViewHolder = new DetailViewHolder(viewSwitcher.getCurrentView());
        if (isModifyViewShown(detailViewHolder)){
            hideModifyView(detailViewHolder);
            ((AlertDialog)getDialog()).getButton(-3).setText("Modify");
        }else{
            showModifyView(detailViewHolder);
            ((AlertDialog)getDialog()).getButton(-3).setText("Hide");
        }
    }
});
```

三个按钮顺序从右到左 -1 -2 -3



#### RecyclerView

https://www.jianshu.com/p/4fc6164e4709

动画: https://www.jianshu.com/p/b375d552db63   https://blog.csdn.net/daisywangyy/article/details/47277749

#### webView中文乱码

```
//直接用webView.loadData(content, MIME_TYPE,"UTF-8");会乱码
webView.loadData(content, "text/html; charset=UTF-8",null);
```

参考 : https://blog.csdn.net/top_code/article/details/9163597    

> loadData(data, "text/html; charset=UTF-8", null);loadData最终的机制是会把传入的三个参数拼接在一起,然		后再进行loadUrl操作,参数就是data, "text/html; charset=UTF-8", null这三个进行拼装,加入text/html; charset=UTF-8就相当于限定了页面的字符

#### webview 加载css

loadDataWithBaseURL()比loadData()多两个参数，可以指定HTML代码片段中相关资源的相对根路径，也可以指定历史Url，其余三个参数相同。

这里主要注意参数baseUrl，baseUrl指定了你的data参数中数据是以什么地址为基准的，因为data中的数据可能会有超链接或者是image元素，而很多网站的地址都是用的相对路径，如果没有baseUrl，webview将访问不到这些资源。

可以在

```
<option name="ASSETS_FOLDER_RELATIVE_PATH" value="/src/main/assets" />
```

的路径下面建普通文件夹, 会自动识别为资源文件夹

路径file:///android_asset/detail.css

#### markdown html 富文本编辑器

https://github.com/zzhoujay/RichText

#### 监控recyclerView的滚动事件

```
noteRecyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
    @Override
    public boolean onFling(int velocityX, int velocityY) {
        //上划
        if (velocityY<0){
            EventBus.getDefault().post(new ShowFABEvent(R.id.note_recycler_view));
        }
        return false;
    }
});
noteRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        //下划到底
        if (!recyclerView.canScrollVertically(1)) {
            EventBus.getDefault().post(new HideFABEvent(R.id.note_recycler_view));
        }
        //上划到顶
        if (!recyclerView.canScrollVertically(-1)) {
            EventBus.getDefault().post(new ShowFABEvent(R.id.note_recycler_view));
        }

    }

});
```
#### 动画

https://blog.csdn.net/yanbober/article/details/46481171



#### 控制DialogFragment 弹出框的大小

DiglogFragment:

```
@Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        //要放到这里才有用, 可能是onCreateView的时候没有加载全
        //初始化默认弹出窗口大小设置
        Window win = getDialog().getWindow();
//        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.TOP;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = dialogHeightWithSoftInputMethod;
        win.setAttributes(params);
        displayWebView.setMinimumHeight(dm.heightPixels);
        contentEditText.setMinimumHeight(dm.heightPixels);
        showKeyboard(contentEditText);
    }
```

```
@Subscribe(threadMode = ThreadMode.MAIN)
public void onKeyBoardShow(ShowKeyBoardEvent event) {
    Integer y = event.get();
    Window win = getDialog().getWindow();
    // 一定要设置Background，如果不设置，window属性设置无效
    win.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    DisplayMetrics dm = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
    WindowManager.LayoutParams params = win.getAttributes();
    params.gravity = Gravity.TOP;
    params.width = dm.widthPixels;
    params.height = dm.heightPixels - y - 50;
    win.setAttributes(params);

    displayWebView.setMinimumHeight(dm.heightPixels);
    contentEditText.setMinimumHeight(dm.heightPixels);

    //设置初始的dialogHeightWithSoftInputMethod, 为了不让开始的时候动画跳一下
    dialogHeightWithSoftInputMethod = dm.heightPixels - y - 50;
}
```

Activity:

```
//监控输入法
this.getWindow().getDecorView().getViewTreeObserver()
        .addOnGlobalLayoutListener(new OnKeyboardChangeListener(this) {
            @Override
            protected void onKeyBoardShow(int x, int y) {
                super.onKeyBoardShow(x, y);
                EventBus.getDefault().post(new ShowKeyBoardEvent(y));
            }

            @Override
            protected void onKeyBoardHide() {
                super.onKeyBoardHide();
            }
        });
```
#### 用浏览器打开网址

```
Uri uri = Uri.parse(url);
Intent intent = new Intent(Intent.ACTION_VIEW, uri);
startActivity(intent);
```
#### 程序打开黑一下屏

```
<style name="StartLoadBackgroundFullScreenTheme" parent="FullscreenTheme">
    <item name="windowNoTitle">true</item>
    <item name="android:windowBackground">@android:color/white</item>
</style>
```

```
AndroidManifest.xml:
<activity
    android:name=".view.MainActivity"
    android:configChanges="orientation|keyboardHidden|screenSize"
    android:label="@string/app_name"
    ***** android:theme="@style/StartLoadBackgroundFullScreenTheme" *****
    >
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```
#### 反编译apk

https://blog.csdn.net/fengyuzhengfan/article/details/80286704