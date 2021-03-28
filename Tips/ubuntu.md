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
