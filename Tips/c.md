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

