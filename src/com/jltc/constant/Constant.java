package com.jltc.constant;

public interface Constant {
	/**
	 * 数据库名
	 */
	String DATABASE_NAME = "msgdb";

	/**
	 * 访问数据库，用户名
	 */
	String USERNAME = "root";

	/**
	 * 访问数据库，密码
	 */
	String PASSWORD = "root";

	/**
	 * 数据库访问错误提示信息
	 */
	String DATABASE_ACCESS_ERROR = "访问数据库错误，请与管理员联系！";

	/**
	 * 操作数据成功
	 */
	String SUCCESS = "success";

	/**
	 * 操作数据失败
	 */
	String FAIL = "fail";

	/**
	 * 原密码正确
	 */
	String OLD_PASSWORD_STATUS = "noCorrect";

	/**
	 * 每页显示记录条件
	 */
	int PAGE_SIZE = 3;

	/**
	 * 记住用户名
	 */
	String SAVE_NAME = "ok";
}
