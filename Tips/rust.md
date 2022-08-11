

### rust tauri build 
Blocking waiting for file lock on package cache #1
https://github.com/ztgx/jlib-rs/issues/1



### rust cargo update crate 卡住
https://blog.csdn.net/rznice/article/details/112424406



### window rust 编译失败
https://blog.csdn.net/jimo_lonely/article/details/122013975



### failed to run custom build command for
rustup default nightly
https://blog.csdn.net/weixin_41760738/article/details/108060293



### windows 安装
https://www.rust-lang.org/tools/install
安装完rustup 还要安装  Visual Studio C++ Build tools 选C++编译相关的
否则会报link.exe找不到之类的错


### rust sqlite
一般的只用npm install, 然后启动会报无法link sqlite.lib
需要自己编译:
重中之重是查看是否编译了 sqlite3.lib 文件。需要注意的是，在 Sqlite3 官方下载的 Windows 二进制文件中，是不含有 sqlite3.lib 文件的，因此我们需要单独编译。

解决
下面说明如何在Windows环境下编译生成sqlite3.lib 文件：

从Sqlite官网(https://www.sqlite.org/download.html)下载源码的 amalgamation 压缩包和二进制文件 Precompiled Binaries for Windows ，其中二进制文件需要下载 dll 和 tools 两个包；
将三个压缩包的全部内容解压到同一目录/文件夹下；
在Windows开始菜单，搜索“Developer Command Prompt”对应版本的 Developer Command Prompt ，如 Developer Command Prompt for VS 2019 或 VS 2017 并打开；
用 cd 命令切换到源代码和二进制文件所在文件夹；
64位操作系统输入： lib /DEF:sqlite3.def /OUT:sqlite3.lib /MACHINE:x64；32位操作系统输入：lib /DEF:sqlite3.def /OUT:sqlite3.lib /MACHINE:x86，完成编译。编译输出sqlite3.lib文件可在目录下找到。
找到输出的sqlite3.lib 文件，将之放在想要运行的Rust项目的目录，即运行cargo run的目录下即可，抑或是将之加入PATH环境变量，重新编译后即可解决问题。

https://blog.itdevwu.com/post/915/




### rust 打包慢, 卡在 https://github.com/wixtoolset/wix3/releases/download/wix3112rtm/wix311-binaries.zip
可以下载下来放到:
用户/xxx/AppData/Local/tauri
https://blog.csdn.net/sinat_36728518/article/details/125663288

