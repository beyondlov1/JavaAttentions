package gz.itcast.a_tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 标签处理器类
 * @author APPle
 * 1）继承SimpleTagSupport
 *
 */
public class ShowIpTag extends SimpleTagSupport{
	/**
	 * 以下屏蔽的代码在SimpleTagSupport代码中已经做了！这里不需要重复再做！
	 */
	/*private JspContext context;
	
	*//**
	 * 传入pageContext
	 *//*
	@Override
	public void setJspContext(JspContext pc) {
		this.context = pc;
	}*/
	
	

	/**
	 * 2）覆盖doTag方法
	 */
	@Override
	public void doTag() throws JspException, IOException {
		//向浏览器输出客户的ip地址
		PageContext pageContext = (PageContext)this.getJspContext();
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		String ip = request.getRemoteHost();
		
		JspWriter out = pageContext.getOut();
		
		out.write("使用自定义标签输出客户的IP地址："+ip);
		
	}
}
