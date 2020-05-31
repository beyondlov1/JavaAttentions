使用方法: 把bat文件放到根目录下, 则根目录下的文件夹都会打包成一个rar
注意:文件不会被打包




运用bat文件:
批量打包:
在想要批量打包的文件根目录写一个bat文件(只打包根目录下的文件夹,每个文件夹生成一个rar):
for /d %%i in (*) do "C:\Program Files\WinRAR\rar.exe" a -ep1 -r -ibck -o %%~ni.rar %%i
如果不想要rar里面的根目录文件夹,想直接看到内容(就是加个/*):
for /d %%i in (*) do "C:\Program Files\WinRAR\rar.exe" a -ep1 -r -ibck -o %%~ni.rar %%i/*
批量解压(保存层级结构):
建一个bat文件，把下面的拷贝进去，然后和你那些rar放到一个目录里面
for %%i in (a.rar,b.rar,c.rar) do "C:\Program Files\WinRAR\rar.exe" x %%i .\%%~ni\
https://zhidao.baidu.com/question/518977711764916325.html