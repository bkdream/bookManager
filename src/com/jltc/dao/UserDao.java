package com.jltc.dao;

import java.util.List;

import com.jltc.domain.User;

public interface UserDao {
/**
 * 登录功能
 * @param user
 * @return 用户所有信息
 */
	User login(User user);
/**
 * 更新用户信息
 * @param user
 * @return	是否处理成功
 */
boolean updateUserMessage(User user);
/**
 * 用户注册信息
 * @param user
 * @return 是否处理成功
 */
boolean userReg(User user);
/**
 * 用户名重名检测
 * @param username
 * @return 是否重名 重名就返回这个名字
 */
String usernameExist(String username);
/**
 * 修改用户密码方法
 * @param user1
 * @return 是否成功
 */
boolean updateUserPassword(User user1);
/**
 * 管理员查询有几条用户数据
 * @param user
 * @return 总数据条数
 */
Integer adminSelectUserMessageCount(User user);
/**
 * 管理员查询所有用户
 * @param curpage
 * @param pageSize
 * @param user
 * @return 用户信息
 */
List<User> adminSelectUserMessage(int curpage, int pageSize, User user);
/**
 * 管理员查询所有用户后 删除用户信息
 * @param _id
 * @return
 */
boolean deleteUserMessage(Integer _id);
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
String userPasswordExist(Integer id,String userpwd);
}
