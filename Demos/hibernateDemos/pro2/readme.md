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

ps:后来发现这种方法也不能实现二级缓存

---

**注意hibernate-entityManager.jar包**

---

**实体类的注解**  
[
https://www.jianshu.com/p/07e8412d577a](https://www.jianshu.com/p/07e8412d577a)

- 注解不能乱加: 
- manyToOne实例:
    @ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

- oneToMany实例:
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

问题: 上面的会生成三张表, 多了一个中间表

原因: 一方和多方都有维护权, 所以只能再生成一张表

解决办法: 添加mappedBy(一旦一方添加了mappedBy, 则表示其放弃了维护权, 由多方来维护, JoinColumn要写在主控方即多方

双向关联示例(参考:[https://blog.csdn.net/qwe6112071/article/details/51093273](https://blog.csdn.net/qwe6112071/article/details/51093273)):

一方:

	@Entity
	@Table(name = "CUSTOMER_9_3")
    public class Customer {
	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "uuid")
	private String id;

	@Column
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Order> orders = new HashSet<>();
	
	...
	}

多方:

    @Entity
	@Table(name = "ORDERS_9_3")
	public class Order {
	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "uuid")
	private String id;

	@Column
	private String price;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	...
	}

注意: 关联的时候要两边都set否则外键会都是null


---

**JPA的线程绑定**

JPA中默认没有类似hibernate的getCurrentSession的方法, 即使在Persistence-Unit中给hibernate配置了绑定session到线程也没用, 所以只能用**ThreadLocal**自己配, 暂时没有找到别的办法

---