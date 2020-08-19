### 调整屏幕
xrandr
设置分辨率
xrandr --output default --mode "1920x1080"
双屏
xrandr --output VGA1 --right-of HDMI1 --auto

参考：http://www.mikewootc.com/wiki/linux/usage/set_x_reso.html
### 移动当前窗口到另一个屏幕
mod4+o
参考： https://gist.github.com/fancyoung/7556281

### 查看keycode
xmodmap -pke
(这和普通的keycode不一样， 貌似是专用的， 所以要现查)
