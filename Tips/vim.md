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

gvim --version | grep clipboard
如果clipboard前边是加号则表示支持复制到剪切板, 如果不是则：
sudo apt install vim-gtk

ubuntu 如果设置后没有效果则改成：
```
set clipboard=unnamedplus
```
参考: https://www.zhihu.com/question/19863631
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
cp -r molokai/colors ~/.vim/
echo "colorscheme molokai" >> .vimrc

### fzf 插件安装
1. 下载： https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
2. 放到 ~/.vim/autoload 文件夹下， 没有就创建一个
3. .vimrc 中添加
```
call plug#begin('~/.vim/plugged')
Plug 'junegunn/fzf', { 'do': { -> fzf#install() } }
Plug 'junegunn/fzf.vim'
call plug#end()
```
4. 在vim中执行 :source .vimrc 刷新配置文件(貌似重新打开也可以)
5. vim 中执行 :PlugInstall


### vim快捷键
选中一个word: viw/vaw
删除一个word: diw/daw
