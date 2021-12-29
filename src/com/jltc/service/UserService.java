package com.jltc.service;

import com.jltc.domain.PageBean;
import com.jltc.domain.User;

public interface UserService {
	/**
	 * 登录功能
	 */
	User login(User user);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	String updateUserMessage(User user);
	/**
	 * 用户注册
	 * @param user
	 * @param confirmpwd
	 * @return
	 */
	String userReg(User user, String confirmpwd);
 /**
  * 用户名重名检测
  * @param username
  * @return
  */
	String usernameExist(String username);
/**
 * 修改用户密码
 * @param user1
 * @return
 */
String updateUserPassword(User user1,String confirmpwd,String newpwd);
/**
 * 管理员查询用户信息
 * @param curpage
 * @param pageSize
 * @param user
 * @return
 */
PageBean<User> UserMessage(int curpage, int pageSize, User user);
/**
 * 管理员查询用户信息后 删除用户信息
 * @param _id
 * @return 成功/失败的字符串
 */
String deleteUserMessage(Integer _id);
/**
 * 管理员查询用户信息后 将此id用户的信息显示在修改界面内
 * @param _id
 * @return
 */
User adminSelectUserMessageById(Integer _id);
/**
 * 用户修改密码时 局部刷新验证原密码是否正确
 * @param userpwd
 * @return
 */
String userPasswordExist(Integer id, String userpwd);
}
