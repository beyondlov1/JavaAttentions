### 调整屏幕
xrandr
设置分辨率
xrandr --output DP-1 --mode "1920x1080"
xrandr --output HDMI-2 --mode "1920x1080"

双屏
扩展：
xrandr --output DP-1 --right-of HDMI-2 --auto
镜像：
xrandr --auto --output DP-1 --pos 0x0 --mode 1920x1080 --output HDMI-2 --same-as DP-1

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

### 限制鼠标在client的区域

```
Awesome wm signals may be useful. Here is a quick example (more like hint) how it works.
Put this somewhere at the start of rc.lua

local is_mouse_locked = false
This code put inside client.connect_signal("manage", function (c, startup) block

-- in this example
-- signal connected to every window and make action if 'is_mouse_locked' switcher active
-- however much better would be connect and disconnect signal to certain window by hotkey
c:connect_signal("mouse::leave",
    function(c)
        if is_mouse_locked then
            local cg = c:geometry() -- get window size
            local mg = mouse.coords() -- get current mouse position

            -- quick and dirty calculate for mouse position correction
            local newx = mg.x <= cg.x and cg.x + 5 or mg.x >= (cg.x + cg.width) and cg.x + cg.width - 5 or mg.x
            local newy = mg.y <= cg.y and cg.y + 5 or mg.y >= (cg.y + cg.height) and cg.y + cg.height - 5 or mg.y

            -- set mouse to new position
            mouse.coords({ x = newx, y = newy })
        end
    end
)
And add this to hotkeys

awful.key({ modkey,           }, "v", function () is_mouse_locked = not is_mouse_locked end),

```

参考： https://stackoverflow.com/questions/34578015/in-awesome-wm-how-to-prevent-mouse-from-leaving-a-client-e-g-fullscreen-or-w
