## 分层

dao service controller

dao：存取数据
sercvice：各种业务逻辑
controller：参数封装，转发/重定向

尽量减小耦合

ssh整合时spring的配置文件按层来分可能会整齐一点，按业务分比较乱，尤其是业务交叉的时候



---

dao可以写一个通用的泛型类，但是感觉还是要每种业务都写一个dao继承这个泛型类

涵盖方法(基础类)： 

add, update, delete

select(id),select(id),select(entity),select(name)

selectAll()

selectAllPage()

count()  //用session

子类方法（如果有互相关联,user,book）：

selectByUser(user)

selectByUserId(id)

查询多条的话hibernateTemplate的QBC可能会好用一点，也可以用executeNativeSession。。。

	// 查询每个用户的书籍 分页
	@SuppressWarnings("unchecked")
	public List<Book> selectAllByUserPage(User user, Page<Book> page) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Book.class);
		criteria.add(Restrictions.eq("owner", user));
		List<Book> findByCriteria = (List<Book>) hibernateTemplate.findByCriteria(criteria, page.getStartRecordIndex(),
				page.getRecordsPerPage());
		return findByCriteria;
	}

---

service感觉没有必要写个基础类，因为不同的业务不一样，用了基础类反而更乱

一个servcie用到另一种业务的时候，最好用它的service（至于好处没感觉到，可能dao和service都写的比较简单吧）

service尽量把问题想全，尽量复用性高

---

Action

可能会和service有业务上的交叉，需要靠创意了

可以弄一个父类，比如上传和下载，直接粘贴，简单粗暴

---

interceptor

一开始就要干的事都可以自定义一个拦截器解决，一个拦截器可以加到几个Action上，机动性比较好，比如登录，加密，转换上传的参数之类的，总之就是能让Action干净点就搞一个， 也可以使用service

---

开发流程

DAO-DTO-ENTITY

实体类（映射文件）->dao->Action(按业务流程走，最好用Action重定向，不然刷新会重新提交表单)->需要什么service就写什么service->struts2映射文件和spring文件注入类

---

ModelDriven

这个东西可以把model放到栈顶，对这个对象操作就直接对值栈中的数据操作，可以改变封装的数据

---

能用常量就用常量，好改

---

要是能用上全局都不变的量就尽量用，比如ThreadLocal可以辅助干这个事

---

jsp能模块化就模块化，省得都写相同的东西




