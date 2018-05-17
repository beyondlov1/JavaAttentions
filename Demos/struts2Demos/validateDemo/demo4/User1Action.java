package cn.itcast.demo4;

import com.opensymphony.xwork2.ActionSupport;

public class User1Action extends ActionSupport{

	private static final long serialVersionUID = 3121323006894260057L;
	
	private String username;
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 该方法还没有执行
	 * @return
	 */
	public String regist(){
		System.out.println("aaaa");
		return NONE;
	}
	
	/**
	 * 查询的方法
	 * @return
	 */
	public String sel(){
		System.out.println("查询执行了...");
		return NONE;
	}
	
	/**
	 * 手动数据的校验，校验的逻辑编写
	 */
	/*public void validate() {
		if(username == null || username.trim().isEmpty()){
			// 在该位置直接向错误的区域中添加错误的信息
			this.addFieldError("username", "亲，用户名不能为空");
		}
		
	}*/
	
	/**
	 * 队Action中的某个方法进行数据的校验
	 */
	public void validateRegist(){
		if(username == null || username.trim().isEmpty()){
			// 在该位置直接向错误的区域中添加错误的信息
			this.addFieldError("username", "亲，用户名不能为空");
		}
	}
	
}
