ʵ�ֶ�������Ĳ��裺

��ͨ��
1. ������ע��汾�ţ�����Ҫ��
2. ��hibernate.cfg.xml�п�����������
	<!-- �������� -->
	<property name="hibernate.cache.use_second_level_cache">true</property>
	<property name="hibernate.cache.region.factory_class" >org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
	<property name="hibernate.generate_statisitcs">true</property><!-- ͳ�Ʋ�������֪��Ϊʲô -->
	<mapping..../>
	<class-cache usage="read-write" class="com.beyond.entity.User" />
3. ���ԣ���load�������أ�

jpa��
1. maven����
2. persistance.xml����ӣ�

	<property name="hibernate.cache.use_second_level_cache" value="true"/>
	<property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>

3. ��ʵ�������ע�⣺
	@Entity
	@Cacheable
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)