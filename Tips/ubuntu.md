### open terminal
Ctrl+Alt+T

### ubuntu 20.04 中文输入法 （ibus-rime）
sudo apt-get install ibus-rime
ibus restart


设置rime默认简体

这时rime默认的是繁体，很令人头疼。
nano ~/.config/ibus/rime/build/luna_pinyin.schema.yaml
打开后翻到最后面

switches:
  - name: ascii_mode
    reset: 0
    states: ["中文", "西文"]
  - name: full_shape
    states: ["半角", "全角"]
  - name: simplification
    states: ["漢字", "汉字"]
  - name: ascii_punct
    states: ["。，", "．，"]

在- name: simplification下另起一行输入reset: 1保存退出。
下面是完成后的样子

switches:
  - name: ascii_mode
    reset: 0
    states: ["中文", "西文"]
  - name: full_shape
    states: ["半角", "全角"]
  - name: simplification
    reset: 1
    states: ["漢字", "汉字"]
  - name: ascii_punct
    states: ["。，", "．，"]


下面这种设置没有试过可以尝试。。。。
~/.config/ibus/rime/default.yaml

schema_list:   
  - schema: luna_pinyin_simp #simp是简体，第一位是默认输入法 
menu:
  page_size: 9 #每页候选词个数
ascii_composer:
  switch_key:
    Shift_L: commit_code #左shift提交字母


### 修改dock栏操作
方法1  - 使用gsettings命令

gsettings是gsettings的命令行接口，它允许我们获取、设置或监视单击键的更改。

这是启用“点击最小化”功能的最快方法。您只需复制/粘贴以下命令即可立即启用此功能。

linuxidc@linuxidc:~/www.linuxidc.com$ gsettings set org.gnome.shell.extensions.dash-to-dock click-action 'minimize'

如果要预览是否打开了相同应用程序的多个窗口，请改用以下命令：

linuxidc@linuxidc:~/www.linuxidc.com$ gsettings set org.gnome.shell.extensions.dash-to-dock click-action 'minimize-or-overview'

只要点击任何一个窗口，就可以把它打开。这是我喜欢的方式。

要恢复到默认设置，只需：

linuxidc@linuxidc:~/www.linuxidc.com$ $ gsettings reset org.gnome.shell.extensions.dash-to-dock click-action

要查看所有可能的选项，请运行：

linuxidc@linuxidc:~/www.linuxidc.com$ gsettings range org.gnome.shell.extensions.dash-to-dock click-action

输出如下：

enum
'skip'
'minimize'
'launch'
'cycle-windows'
'minimize-or-overview'
'previews'
'quit'

参考： https://www.linuxidc.com/Linux/2019-07/159400.htm

### dpkg 安装到目录
dpkg -x xxx.deb TAEGET_DIR

### 添加环境变量
vim ~/.bashrc
source ~/.bashrc

### xfce panel悬浮
setting editor -> xfce-panel -> disable-structs

### 休眠
Install pm-utils and hibernate:
```
sudo apt install pm-utils hibernate
```
Then:
```
cat /sys/power/state
```
You should see:
```
freeze mem disk
```
Then run:
```
grep swap /etc/fstab
```
Copy the UUID value. You will need it later. Then run:
```
sudo nano /etc/default/grub
```
(or your favourite editor if not nano). Change the line that says
```
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash"
```
so that it instead says:
```
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash resume=UUID=YOUR_VALUE"
```
Then, after saving the file and quitting the text editor, run:
```
sudo update-grub
```
To test it, run:
```
sudo systemctl hibernate
```
参考： https://askubuntu.com/questions/1240123/how-to-enable-hibernate-option-in-ubuntu-20-04


### 关闭tracker-miner-fs
tracker-miner-fs 有的时候会在启动的时候占用大量的io（查看top wa%, 超过10%就算大), 所以要关掉
echo -e "\nHidden=true\n"|sudo tee --append /etc/xdg/autostart/tracker-extract.desktop
echo -e "\nHidden=true\n"|sudo tee --append /etc/xdg/autostart/tracker-miner-apps.desktop
echo -e "\nHidden=true\n"|sudo tee --append /etc/xdg/autostart/tracker-miner-fs.desktop
echo -e "\nHidden=true\n"|sudo tee --append /etc/xdg/autostart/tracker-miner-user-guides.desktop
echo -e "\nHidden=true\n"|sudo tee --append /etc/xdg/autostart/tracker-store.desktop

gsettings set org.freedesktop.Tracker.Miner.Files crawling-interval -2
gsettings set org.freedesktop.Tracker.Miner.Files enable-monitors false

tracker reset --hard

参考: https://ubuntuqa.com/article/488.html


### 蓝牙问题
```
sudo apt-get install pulseaudio-module-bluetooth  
pactl load-module module-bluetooth-discover  
```
第一行执行完会出现:20
参考： https://askubuntu.com/questions/1115671/blueman-protocol-not-available

### chromium 开启chrome sync
参考:https://stackoverflow.com/questions/21276763/google-api-keys-missing-warning-message-when-using-chromium-portable

```
export GOOGLE_API_KEY="AIzaSyCkfPOPZXDKNn8hhgu3JrA62wIgC93d44k"
export GOOGLE_DEFAULT_CLIENT_ID="811574891467.apps.googleusercontent.com"
export GOOGLE_DEFAULT_CLIENT_SECRET="kdloedMFGdGla2P1zacGjAQh"
```

### resolv.conf
sudo apt-get install resolvconf
修改/etc/resolvconf/resolv.conf.d/head或者base

### i3wm 注销
$mod Shift E


### 安装xfce
sudo apt-get install xorg xdm xfce4
选择 gdm3

### xfce 快捷键配置文件
~/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-keyboard-shortcuts.xml

### xfce 去除alt+click 移动窗口
following worked for me
type xfce4-settings-manager in terminal
and then click 'settings editor'
then to the left click xfwm4 channel then in the right pane change the value of easy_click  from <alt> to nothing

Now alt+mouse right click wont move your window........................
its a very crap setting..............

窗口微调

### 控制窗口
wmctrl (https://linux.die.net/man/1/wmctrl)
- 当前窗口置顶: wmctrl -r :ACTIVE: -b toggle,above

jumpapp (依赖wmctrl) 没有则启动,有则跳转
- jumpapp x-terminal-emulator


### docker 占用 172.17 网段解决
https://blog.51cto.com/wujianwei/2656527

### xbindkeys

- xev
功能：通过它可以知道键盘上每一个按键的编码，即keycode， 这个键码与键盘硬件有关系，固定不变的。你想想啊，键盘上这么多按键，怎么让计算机去区分啊？就是通过这个keycode值，每当我们按下一个键时，内核中中断系统就会接收到一个keycode， 从而判断你按下了哪个键。具体操作系统怎么处理这个按键，那就需要keycode值到keysym的映射来决定了。
https://www.cnblogs.com/yinheyi/p/10146900.html

- 如何查看keysym
sudo apt-get install x11proto-core-dev
https://askubuntu.com/questions/93772/where-do-i-find-a-list-of-all-x-keysyms-these-days

- 如何查看keycode
xmodmap -pke
或者 https://www.bejson.com/othertools/keycodes/

- xmodmap
https://wiki.archlinux.org/title/Xmodmap_(%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87)#%E9%94%AE%E6%98%A0%E5%B0%84%E8%A1%A8

- xbindkeys 
m: 代表state, 可以从 xev 输出中获取到, 表示当时的状态，如numlock有没有开, control有没有按下之类. 这应该是个flag, 用二进制 & 做的
b: 鼠标按键, 后边时keycode， 十进制或者十六进制
c: keycode
可以用 xbindkeys -k 之后按键查看到对应的按键


### dns
https://blog.csdn.net/lsc_1893/article/details/118696693
https://bbs.archlinux.org/viewtopic.php?id=268676


### 窗口激活顺序
stack=$(xprop -root | grep '_NET_CLIENT_LIST_STACKING(WINDOW)' | awk -F'# ' '{print $2}');
stack_array_prop=(${stack//,/ })
stack_array_reversed=()
len=${#stack_array_prop[*]}
for((i=$len-1;i>=0;i--));
do
    id='0x0'$(echo ${stack_array_prop[i]} | cut -d 'x' -f 2 )
    stack_array_reversed[${#stack_array_reversed[*]}]=$id
done

echo ${stack_array_reversed[*]}