
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

---

**jsp�Զ���ʾ**

MyEclipse��

9.0�Ժ�汾��MyEclipse��html��jsp�Ĵ�����ʾ

1��File-->Export-->General-->Preferences �����һ��������������ļ������������ϣ��ͣ�һ���е�5,6һ��

2����������ʹ���ı��༭���򿪴��ļ����������������һ�����ݺ󱣴�

/instance/org.eclipse.wst.html.ui/autoProposeCode=<= abcdefghijklmnopqrstuvwxyz:

3��Ȼ�󽫴������ļ����룬File-->Import-->General-->Preferences�����ǵ�һ�����������

������https://www.jianshu.com/p/d1a9b472e5e0

---

**XML�Ԅ���ʾ**

�� �� Eclipse ����ѡ�� Window > Preferences > Xml > Xml Files > Editor > Content Assist > Auto activation > Prompt when these characters are inserted �����ÿ���Ĭ���� <=: ��

���ڽ�����Ϊ��

����Ϊ�������ݣ�  <=:. abcdefghijklmnopqrstuvwxyz(,

https://blog.csdn.net/guyuealian/article/details/50767391

---

**MyEclipse����hibernate**

MyEclipse��������POJO������ļ�����һЩ���}����Ҫ�քӸģ�
1. generator �в��ܰ�myeclipse�е�ѡ��uuid.string�������-----�����Ҫ��uuid
2. ���ɵ������ļ�session�����thread��Ҫ�ֶ�����
3. mapping������û�м�����ϵ-----������õ�ʱ��һ���෽��Ҫ����

---

**MyEclipse��html������������**
��meta�е�name��Ϊ http-equiv

```html
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
```

---

#### MyEclipse �޸�web��Ŀ��·��

1.Web��Ŀ�Ҽ�->Properties->Myeclipse->Web->Web Context-root.

2.����Ŀ�д�.project�ļ�,�޸�<projectDescription>�µ�<name>����.



#### Eclispe��ݼ�

- ctrl+shift+h  ������

#### MyEclipse����xmlģ�� (��ʾ���е�)

window - property - XML Source - Templates - New

#### ת����Сд�Ŀ�ݼ�

Eclipse�иı��ַ�����Сд�Ŀ�ݼ���

Сд���д��ctrl��shift��x

��д��Сд��ctrl��shift��y

