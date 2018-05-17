package cn.itcast.demo4;

import com.opensymphony.xwork2.ActionSupport;

/**
 * XML的方式进行数据的校验
 * @author Administrator
 */
public class User2Action extends ActionSupport{
	
	private static final long serialVersionUID = 2192779314613798290L;
	
	// 做数据的封装
	private String username;
	private String password;
	
	
	/**
	 * 做校验
	 * @return
	 */
	public String regist(){
		
		System.out.println("aaa");
		
		return NONE;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
