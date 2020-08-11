### vim 访问外部剪切板
vimrc 中添加:
```
set clipboard=unnamed
```
https://stackoverflow.com/questions/3961859/how-to-copy-to-clipboard-in-vim?rq=1

vs code 中改变设置:
```
vim.useSystemClipboard=true
```
https://codeyarns.com/2018/04/26/how-to-use-clipboard-for-vim-in-vscode/

### 设置vim编码

在gvm配置文件中：

（gvim安装目录下的_vimrc文件中）

"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
" vim7.1在windows下的编码设置。By Huadong.Liu
""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
set encoding=utf-8
set fileencodings=utf-8,chinese,latin-1
if has("win32")
 set fileencoding=chinese
else
 set fileencoding=utf-8
endif
"解决菜单乱码
source $VIMRUNTIME/delmenu.vim
source $VIMRUNTIME/menu.vim
"解决consle输出乱码
language messages zh_CN.utf-8

### 设置默认显示行号
_vimrc文件中添加：
set number

### 设置默认显示行号2
~/.vimrc
增加: 
set nu

### 修改配色
git clone https://github.com/tomasr/molokai.git
cp molokai/colors ~/.vim/
