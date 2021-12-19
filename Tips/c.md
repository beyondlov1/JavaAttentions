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