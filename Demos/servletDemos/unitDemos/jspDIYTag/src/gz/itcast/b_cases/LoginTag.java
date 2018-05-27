package gz.itcast.b_cases;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 自定义登陆页面标签
 * @author APPle
 *
 */
public class LoginTag extends SimpleTagSupport{
	private String username;
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void doTag() throws JspException, IOException {
		 HttpServletResponse response = (HttpServletResponse)((PageContext)this.getJspContext()).getResponse();
		 //设置输出内容类型和编码
		 response.setContentType("text/html;charset=utf-8");
		 String html = "";
		
		 html += "<center><h3>用户登陆页面</h3></center>";
		 html += "<table border='1' align='center' width='400px'>";
		 html += "	<tr>";
		 html += "		<th>用户名：</th>";
		 html += "		<td><input type='text' name='"+username+"'/></td>";
		 html += "	</tr>";
		 html += "	<tr>";
		 html += "		<th>密码：</th>";
		 html += "		<td><input type='password' name='"+password+"'/></td>";
		 html += "	</tr>";
		 html += "	<tr>";
		 html += "		<td colspan='2' align='center'><input type='submit' value='登陆'/>&nbsp;<input type='reset' value='重置'/></td>";
		 html += "	</tr>";
		 html += "</table>";
		
		JspWriter out = this.getJspContext().getOut();
		out.write(html);
	}
}
