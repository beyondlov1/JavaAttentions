实现二级缓存的步骤：

普通：
1. 导包：注意版本号，很重要；
2. 在hibernate.cfg.xml中开启二级缓存
	<!-- 二级缓存 -->
	<property name="hibernate.cache.use_second_level_cache">true</property>
	<property name="hibernate.cache.region.factory_class" >org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
	<property name="hibernate.generate_statisitcs">true</property><!-- 统计不到，不知道为什么 -->
	<mapping..../>
	<class-cache usage="read-write" class="com.beyond.entity.User" />
3. 测试：用load方法加载；

jpa：
1. maven导包
2. persistance.xml中添加：

	<property name="hibernate.cache.use_second_level_cache" value="true"/>
	<property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>

3. 在实体类添加注解：
	@Entity
	@Cacheable
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)