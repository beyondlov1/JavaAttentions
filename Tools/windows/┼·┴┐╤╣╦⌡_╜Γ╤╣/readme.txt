ʹ�÷���: ��bat�ļ��ŵ���Ŀ¼��, ���Ŀ¼�µ��ļ��ж�������һ��rar
ע��:�ļ����ᱻ���




����bat�ļ�:
�������:
����Ҫ����������ļ���Ŀ¼дһ��bat�ļ�(ֻ�����Ŀ¼�µ��ļ���,ÿ���ļ�������һ��rar):
for /d %%i in (*) do "C:\Program Files\WinRAR\rar.exe" a -ep1 -r -ibck -o %%~ni.rar %%i
�������Ҫrar����ĸ�Ŀ¼�ļ���,��ֱ�ӿ�������(���ǼӸ�/*):
for /d %%i in (*) do "C:\Program Files\WinRAR\rar.exe" a -ep1 -r -ibck -o %%~ni.rar %%i/*
������ѹ(����㼶�ṹ):
��һ��bat�ļ���������Ŀ�����ȥ��Ȼ�������Щrar�ŵ�һ��Ŀ¼����
for %%i in (a.rar,b.rar,c.rar) do "C:\Program Files\WinRAR\rar.exe" x %%i .\%%~ni\
https://zhidao.baidu.com/question/518977711764916325.html