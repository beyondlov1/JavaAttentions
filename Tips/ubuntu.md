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

### wmware 共享文件

https://www.cnblogs.com/zhouzhishuai/p/8126885.html#:~:text=VMware%E8%99%9A%E6%8B%9F%E6%9C%BA%E5%A6%82%E4%BD%95%E4%B8%8E%E4%B8%BB%E6%9C%BA%E5%85%B1%E4%BA%AB%E6%96%87%E4%BB%B6%E5%A4%B9.%20%E6%89%93%E5%BC%80%E8%99%9A%E6%8B%9F%E6%9C%BA%EF%BC%8C%E9%80%89%E6%8B%A9%E8%A6%81%E6%B7%BB%E5%8A%A0%E5%85%B1%E4%BA%AB%E6%96%87%E4%BB%B6%E7%9A%84%E8%99%9A%E6%8B%9F%E6%9C%BA%EF%BC%8C%E7%82%B9%E5%87%BB%E2%80%9C%E7%BC%96%E8%BE%91%E8%99%9A%E6%8B%9F%E6%9C%BA%E8%AE%BE%E7%BD%AE%E2%80%9D%EF%BC%8C%E5%9C%A8%E5%BC%B9%E5%87%BA%E7%9A%84%E7%BC%96%E8%BE%91%E7%AA%97%E5%8F%A3%E4%B8%AD%EF%BC%8C%E9%80%89%E6%8B%A9%E2%80%9C%E9%80%89%E9%A1%B9%E2%80%9D%E4%B8%AD%E7%9A%84%E2%80%9C%E5%85%B1%E4%BA%AB%E6%96%87%E4%BB%B6%E5%A4%B9%E2%80%9D%E3%80%82.%20%E5%9C%A8%E5%8F%B3%E4%BE%A7%E7%82%B9%E5%87%BB%E2%80%9C%E5%A7%8B%E7%BB%88%E5%90%AF%E7%94%A8%E2%80%9D%EF%BC%8C%E9%80%89%E6%8B%A9%E5%A5%BD%E5%90%8E%E7%82%B9%E5%87%BB%E2%80%9C%E6%B7%BB%E5%8A%A0%E2%80%9D%E6%8C%89%E9%92%AE%E3%80%82.%20%E5%9C%A8%E5%BC%B9%E5%87%BA%E7%9A%84%E7%AA%97%E5%8F%A3%E4%B8%AD%EF%BC%8C%E7%82%B9%E5%87%BB%E2%80%9C%E6%B5%8F%E8%A7%88%E2%80%9D%E3%80%82.,%E8%BF%99%E9%87%8C%E7%9A%84%E6%B5%8F%E8%A7%88%E6%98%AF%E6%B5%8F%E8%A7%88%E7%9A%84%E4%B8%BB%E6%9C%BA%E7%9B%AE%E5%BD%95%EF%BC%8C%E5%9C%A8%E4%B8%BB%E6%9C%BA%E7%A3%81%E7%9B%98%E4%B8%AD%E5%BB%BA%E7%AB%8B%E4%B8%80%E4%B8%AA%E6%96%87%E4%BB%B6%E5%A4%B9%EF%BC%8C%E5%81%9A%E4%B8%BA%E5%85%B1%E4%BA%AB%E6%96%87%E4%BB%B6%E5%A4%B9%EF%BC%9B.%20%E5%9C%A8%E5%BC%B9%E5%87%BA%E7%9A%84%E7%AA%97%E5%8F%A3%E4%B8%AD%EF%BC%8C%E9%80%89%E6%8B%A9%E2%80%9C%E5%90%AF%E7%94%A8%E8%AF%A5%E5%85%B1%E4%BA%AB%E2%80%9D%EF%BC%8C%E7%82%B9%E5%87%BB%E2%80%9C%E5%AE%8C%E6%88%90%E2%80%9D%E3%80%82.%20%E5%AE%8C%E6%88%90%E4%BA%86%E5%85%B1%E4%BA%AB%E6%96%87%E4%BB%B6%E5%A4%B9%E7%9A%84%E6%B7%BB%E5%8A%A0%E8%BF%87%E7%A8%8B%E3%80%82.%205.%E9%80%9A%E8%BF%87%E4%B8%8B%E5%88%97%E5%91%BD%E4%BB%A4%E8%A7%A3%E5%8E%8B%E3%80%81%E6%89%A7%E8%A1%8C%EF%BC%8C%E5%88%86%E5%88%AB%E6%98%AF%E4%B8%8B%E9%9D%A2%E7%9A%84tar%E5%92%8Csudo%E7%9A%84%E4%B8%A4%E8%A1%8C%EF%BC%88%E4%B8%8B%E9%9D%A2%E6%98%AF%E5%B7%B2%E6%9C%89vmware%20tool%E7%9A%84%E6%83%85%E5%86%B5%EF%BC%8C%E6%B3%A8%E6%84%8F%E5%AF%86%E7%A0%81%E6%98%AF%E7%99%BB%E5%BD%95%E5%8F%A3%E4%BB%A4%EF%BC%8C%E4%B8%8D%E4%BC%9A%E6%98%BE%E7%A4%BA%EF%BC%89.


### iptables 开放端口
sudo iptables -I INPUT -p udp --dport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT
sudo iptables -I INPUT -p tcp --dport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT

sudo iptables -A OUTPUT -p udp --sport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT
sudo iptables -A OUTPUT -p tcp --sport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT


### kde connect 同一局域网不能连接
iptables
If your firewall is iptables, you can open the necessary ports with:

sudo iptables -I INPUT -i <yourinterface> -p udp --dport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT
sudo iptables -I INPUT -i <yourinterface> -p tcp --dport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT

sudo iptables -A OUTPUT -o <yourinterface> -p udp --sport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT
sudo iptables -A OUTPUT -o <yourinterface> -p tcp --sport 1714:1764 -m state --state NEW,ESTABLISHED -j ACCEPT

打开端口
windows关闭防火墙

https://userbase.kde.org/KDEConnect#Share_and_Receive


### ubuntu idea 不能输入中文
ibus-daemon -r -d -x
https://www.cnblogs.com/silentdoer/p/8907310.html

meld 文本对比, 目录对比



### vscode 自动切换中文输入法
https://www.zhihu.com/question/303850876
https://github.com/daipeihust/im-select#installation



### rime 关闭F4快捷键
vi /home/beyond/.config/ibus/rime/build/default.yaml
搜索 F4 注释掉

### synergy 一个鼠标控制两个电脑
sudo apt-get install synergy
sudo apt install  libqt4-dbus libqtcore4 libqtgui4  libqt4-network  libqt4-opengl libqtcore4 libqtgui4
https://www.jianshu.com/p/a706d2ece694

windows:
https://sourceforge.net/projects/synergy-stable-builds/files/v1.8.8-stable/

不用注册,弹框拒绝
server 取消ssl


### ubuntu 默认图片地址
/usr/share/backgrounds/brad-huchteman-stone-mountain.jpg



### qq ukylin
https://www.ubuntukylin.com/applications/107-cn.html
command in : /usr/share/applications/QQ.desktop



### 挂载webdav
apt-get install davfs2
mkdir /media/akann
mount -t davfs http://www.server.com/dir /media/akann
参考: https://blog.csdn.net/twlkyao/article/details/10426103


### xcb-imdkit编译
```
sudo apt-get install build-essential
sudo apt-get install extra-cmake-modules
sudo apt-get install libxcb-util-dev
sudo apt-get install libxcb-keysyms1-dev
```
 mkdir build
 cd build
 cmake -DCMAKE_INSTALL_PREFIX=/usr ..
 make
 sudo make install

### tracker cpu 占用过高
禁用: 
```
tracker daemon -t
cd ~/.config/autostart
cp -v /etc/xdg/autostart/tracker-*.desktop ./
for FILE in tracker-*.desktop; do echo Hidden=true >> $FILE; done
rm -rf ~/.cache/tracker ~/.local/share/tracker
```
https://stackoom.com/question/1oBkS
