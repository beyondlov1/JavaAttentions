

### wayfire install
sudo add-apt-repository ppa:soreau/wayfirewm
sudo apt update
sudo apt install wayfire wayfire-plugins-extra wf-shell wcm xwayland



### wayfire 
 WLR_NO_HARDWARE_CURSORS=1 wayfire



### 
/usr/share/wayland-sessions/wayfire.desktop 

[Desktop Entry]
Name=Wayfire
Exec=env WLR_NO_HARDWARE_CURSORS=1 /usr/bin/wayfire
TryExec=wayfire
Icon=
Type=Application
DesktopNames=Wayfire

