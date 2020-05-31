### 来源: spring的 @ComponentScan 注解是如何工作的
- 如何扫描包: 用的classloader.getResources 获取classpath 下的所有资源, 然后遍历扫描  参考: https://my.oschina.net/huangyong/blog/159155
- 如何获取注解: 用 asm 技术, 字节码读取和写入的技术
参考: https://www.jianshu.com/p/760229bfe18a  
https://github.com/ledboot/ASMTest/blob/master/src/com/ledboot/main/RedefineClass.java
https://blog.csdn.net/conquer0715/article/details/51283610  
https://www.ibm.com/developerworks/cn/java/j-lo-asm30/

demo: helloASM
