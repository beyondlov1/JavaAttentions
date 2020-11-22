### 调整屏幕
xrandr
设置分辨率
xrandr --output DP-1 --mode "1920x1080"
xrandr --output HDMI-2 --mode "1920x1080"

双屏
xrandr --output DP-1 --right-of HDMI-2 --auto

参考：http://www.mikewootc.com/wiki/linux/usage/set_x_reso.html
### 移动当前窗口到另一个屏幕
mod4+o
参考： https://gist.github.com/fancyoung/7556281

### 查看keycode
xmodmap -pke
(这和普通的keycode不一样， 貌似是专用的， 所以要现查)
=======


### keycode 对照表
https://www.cnblogs.com/lxwphp/p/9548823.html

### 移动窗口到另一个tag
shift+mod4+N

### 显示wifi图标
nm-applet --sm-disable 2>&1 &
