package gz.itcast.a_tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 标签处理器类
 * @author APPle
 *
 */
public class DemoTag extends SimpleTagSupport{
	//1.声明属性的成员变量
	private Integer num;
	
	//2.关键点： 必须提供公开的setter方法，用于给属性赋值
	public void setNum(Integer num) {
		this.num = num;
	}


	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("执行了标签");
		
		/**
		 * 1）控制标签内容是否输出
		 *    输出： 调用jspFrament.invoke();
		 *    不输出： 不调用jspFrament.invoke();
		 */
		//1.1 得到标签体内容
		JspFragment jspBody = this.getJspBody();
		
		/**
		 * 执行invoke方法： 把标签体内容输出到指定的Writer对象中
		 */
		//1.2 往浏览器输出内容，writer为null就是默认往浏览器输出
		//JspWriter out = this.getJspContext().getOut();
		//jspBody.invoke(out);
		jspBody.invoke(null);//等价于上面的代码
		
		/**
		 * 3)控制重复输出标签体内容
		 *     方法： 执行多次jspBody.invoke()方法
		 */
		/*for(int i=1;i<=num;i++){
			jspBody.invoke(null);
		}*/
		
		/**
		 * 4)改变标签体内容
		 */
		//4.1 创建StringWriter临时容器
		/*StringWriter sw = new StringWriter();
		//4.2 把标签体拷贝到临时容器
		jspBody.invoke(sw);
		//4.3 从临时容器中得到标签体内容
		String content = sw.toString();
		//4.4 改变内容
		content = content.toLowerCase();
		//System.out.println(content);
		//4.5 把改变的内容输出到浏览器
		//jspBody.invoke(null); 不能使用此方式输出，因为jsbBody没有改变过
		this.getJspContext().getOut().write(content);*/
		
		
		/**
		 * 2)控制标签余下内容是否输出
		 *   输出: 什么都不干！
		 *   不输出： 抛出SkipPageException异常
		 */
		throw new SkipPageException();
		
	}
}
