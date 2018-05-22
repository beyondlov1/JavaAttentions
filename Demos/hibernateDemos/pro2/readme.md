hibernate 5.3.0-Final
Ehcache 3.x

**采用jpa的方式**
- 要在实体类中写注解, 否则不行
- persistence.xml要放在META-INF文件里, 然后放在src中

---

**二级缓存实现方式:** hibernate+Jcache+ehcache
- 由于ehcache 3.x 默认实现了 jcache, 所以配置文件里要用jcache的region_Factory, 如下:

	<property name="hibernate.cache.use_second_level_cache" value="true"></property>
	<property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.jcache.internal.JCacheRegionFactory"></property>
	<property name="hibernate.javax.cache.provider" value="org.ehcache.jsr107.EhcacheCachingProvider"></property>

---

**注意hibernate-entityManager.jar包**

---

**实体类的注解**  
[
https://www.jianshu.com/p/07e8412d577a](https://www.jianshu.com/p/07e8412d577a)

---