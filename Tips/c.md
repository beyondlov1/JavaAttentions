### base
#include "file" //引入个人的header
.h 文件声明方法
#include <stdio.sh> //引入系统的header
查找路径 /usr/include

### gtk+3.0
sudo apt-get install libgtk-3-dev
or
sudo apt-get install libgtk3.0* //未测试

https://blog.csdn.net/tennysonsky/article/details/42740865

include:
#include <gtk/gtk.h>
编译时要: gcc `pkg-config --cflags gtk+-3.0` win.c -o main `pkg-config --libs gtk+-3.0`
https://askubuntu.com/questions/892625/ubuntu-16-10-gtk-3-0-include-path-for-headers


https://developer.aliyun.com/article/383378

hello world:
https://riptutorial.com/gtk3

剪切板
https://www.cnblogs.com/silvermagic/p/9087648.html
https://docs.gtk.org/gtk3/class.Clipboard.html

parcellite:
原理: 定时检测剪切板中的数据是否有变化, 如果有变化则更新剪切板历史: main.check_clipboards_tic
自动粘贴: 模拟粘贴按键 main 1647 key ctrl+v


https://gist.github.com/Acry/e0a167f6d323feb0519edb47248ab3bf


vscode编译:
pkg-config --cflags gtk+-3.0
pkg-config --libs gtk+-3.0

```
{
    "configurations": [
        {
            "name": "Linux",
            "includePath": [
                "${workspaceFolder}/**",
                "/usr/include/gtk-3.0",
                "/usr/include/at-spi2-atk/2.0",
                "/usr/include/at-spi-2.0",
                "/usr/include/dbus-1.0",
                "/usr/lib/x86_64-linux-gnu/dbus-1.0/include",
                "/usr/include/gtk-3.0",
                "/usr/include/gio-unix-2.0",
                "/usr/include/cairo",
                "/usr/include/pango-1.0",
                "/usr/include/fribidi",
                "/usr/include/harfbuzz",
                "/usr/include/atk-1.0",
                "/usr/include/cairo",
                "/usr/include/pixman-1",
                "/usr/include/uuid",
                "/usr/include/freetype2",
                "/usr/include/libpng16",
                "/usr/include/gdk-pixbuf-2.0",
                "/usr/include/libmount",
                "/usr/include/blkid",
                "/usr/include/glib-2.0",
                "/usr/lib/x86_64-linux-gnu/glib-2.0/include"
            ],
            "defines": [],
            "compilerPath": "/usr/bin/gcc",
            "cStandard": "gnu17",
            "cppStandard": "gnu++14",
            "intelliSenseMode": "linux-gcc-x64",
            "compilerArgs": [
                "-pthread",
                "-I/usr/include/gtk-3.0",
                "-I/usr/include/at-spi2-atk/2.0",
                "-I/usr/include/at-spi-2.0",
                "-I/usr/include/dbus-1.0",
                "-I/usr/lib/x86_64-linux-gnu/dbus-1.0/include",
                "-I/usr/include/gtk-3.0",
                "-I/usr/include/gio-unix-2.0",
                "-I/usr/include/cairo",
                "-I/usr/include/pango-1.0",
                "-I/usr/include/fribidi",
                "-I/usr/include/harfbuzz",
                "-I/usr/include/atk-1.0",
                "-I/usr/include/cairo",
                "-I/usr/include/pixman-1",
                "-I/usr/include/uuid",
                "-I/usr/include/freetype2",
                "-I/usr/include/libpng16",
                "-I/usr/include/gdk-pixbuf-2.0",
                "-I/usr/include/libmount",
                "-I/usr/include/blkid",
                "-I/usr/include/glib-2.0",
                "-I/usr/lib/x86_64-linux-gnu/glib-2.0/include",
                "-lgtk-3",
                "-lgdk-3",
                "-lpangocairo-1.0",
                "-lpango-1.0",
                "-lharfbuzz",
                "-latk-1.0",
                "-lcairo-gobject",
                "-lcairo",
                "-lgdk_pixbuf-2.0",
                "-lgio-2.0",
                "-lgobject-2.0",
                "-lglib-2.0"
            ]
        }
    ],
    "version": 4
}

```


https://code.visualstudio.com/docs/cpp/config-linux

###编译多个文件
https://zhuanlan.zhihu.com/p/419473501
https://blog.csdn.net/hzf978742221/article/details/116101789



### 教程
http://www.weixueyuan.net/a/81.html


### getchar EOF 
linux: 终端输入 ctrl+d returns EOF
https://stackoverflow.com/questions/10720821/im-trying-to-understand-getchar-eof#:~:text=getchar%20%28%29%20is%20a%20function%20that%20reads%20a,you%20run%20your%20program%20in%20unix%20like%20this%3A


### c github 项目
webbench
tinyhttpd
cjson
memcached
lua
sqlite
unix v6
netBSD



### socket
https://blog.csdn.net/qq_41725312/article/details/90375742

### 关闭stdout缓存
```setbuf(stdout,NULL);```

### socket bind error: Address already in use
SO_REUSEADDR

参考: https://www.cnblogs.com/alantu2018/p/8462500.html#:~:text=%E5%9C%B0%E5%9D%80%E5%B7%B2%E7%BB%8F%E8%A2%AB%E4%BD%BF%E7%94%A8%20-%20Address%20already%20in,use%20%E5%BE%88%E5%A4%9Asocket%E7%BC%96%E7%A8%8B%E7%9A%84%E5%88%9D%E5%AD%A6%E8%80%85%E5%8F%AF%E8%83%BD%E4%BC%9A%E9%81%87%E5%88%B0%E8%BF%99%E6%A0%B7%E7%9A%84%E9%97%AE%E9%A2%98%EF%BC%9A%E5%A6%82%E6%9E%9C%E5%85%88ctrl%2Bc%E7%BB%93%E6%9D%9F%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AB%AF%E7%A8%8B%E5%BA%8F%E7%9A%84%E8%AF%9D%EF%BC%8C%E5%86%8D%E6%AC%A1%E5%90%AF%E5%8A%A8%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%B0%B1%E4%BC%9A%E5%87%BA%E7%8E%B0Address%20already%20in%20use%E8%BF%99%E4%B8%AA%E9%94%99%E8%AF%AF%EF%BC%8C%E6%88%96%E8%80%85%E4%BD%A0%E7%9A%84%E7%A8%8B%E5%BA%8F%E5%9C%A8%E6%AD%A3%E5%B8%B8%E5%85%B3%E9%97%AD%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%AB%AFsocket%E5%90%8E%E8%BF%98%E6%98%AF%E6%9C%89%E8%BF%99%E4%B8%AA%E9%97%AE%E9%A2%98%E3%80%82

tcp TIME_WAIT: https://zhuanlan.zhihu.com/p/99943313
