
**��������web.xml�ļ�**

�Ҽ���Ŀ���JavaEE�����¡�generate deployment descriptor stub(���ɲ��������ļ�)��

---

**��Ŀ���ܲ���, һ��tomcat�ͱ���**

����: "Publishing to Tomcat v9.0 Server at localhost...". Could not initialize class com.genuitec.eclipse.webclipse.livepreview.common.LPMappingService.....

ԭ��: MyEclipse��bug

����취: 1) Shut down MyEclipse
2) Run Notepad as Administrator
3) From notepad, open the c:\windows\system32\drivers\etc\hosts file
**4) Add the line**
127.0.0.1 gapdebug.local.genuitec.com
5) Save the file (note, this will only work if Notepad has been run as Administrator)
6) Start MyEclipse.