package gz.itcast.b_cases;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachTag extends SimpleTagSupport {
	private Object items;//需要遍历的数据.List和map
	private String var;//每个元素的名称
	
	public void setItems(Object items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		//遍历items数据
		//List
		/*PageContext pageContext = (PageContext)this.getJspContext();
		if(items instanceof List){
			List list = (List)items;
			for (Object object : list) {
				//把每个对象放入域对象中(pageContext)
				pageContext.setAttribute(var, object);
				//显示标签体内容
				this.getJspBody().invoke(null);
			}
		}
		
		//Map
		if(items instanceof Map){
			Map map = (Map)items;
			Set<Entry> entrySet = map.entrySet();
			for(Entry entry :entrySet){
				//把每个对象放入域对象中(pageContext)
				pageContext.setAttribute(var, entry);
				//显示标签体内容
				this.getJspBody().invoke(null);
			}
		}*/
		
		
		//简化代码
		//思路： 
			//1）list         -> Collection
			//2) map.entrySet -> Collection
		PageContext pageContext = (PageContext)this.getJspContext();
		Collection colls = null;
		if(items instanceof List){
			colls = (List)items;
		}
		if(items instanceof Map){
			Map map = (Map)items;
			colls = map.entrySet();
		}
		     
		for(Object object:colls){
			//把每个对象放入域对象中(pageContext)
			pageContext.setAttribute(var, object);
			//显示标签体内容
			this.getJspBody().invoke(null);
		}
	}
}
