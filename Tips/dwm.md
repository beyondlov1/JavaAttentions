

### dwm 打开idea datagrip显示空
echo export _JAVA_AWT_WM_NONREPARENTING=1 >> ~/.profile
or
_JAVA_AWT_WM_NONREPARENTING=1 datagrip



### dwm 打开 firefox addon页面导致桌面崩溃
原因: xft bug, 字体问题
解决: apt remove --purge fonts-noto-color-emoji
参考: https://unix.stackexchange.com/questions/629281/gitk-crashes-when-viewing-commit-containing-emoji-x-error-of-failed-request-ba

