package com.jltc.domain;

public class UserFormCheck {
	private User user;// 用户对象
	private String newpwd;// 用户新密码
	private String confirmpwd;// 用户确认密码

	public UserFormCheck() {
		super();
	}	

	public UserFormCheck(User user) {
		super();
		this.user = user;
	}
	
	public UserFormCheck(User user, String confirmpwd) {
		super();
		this.user = user;
		this.confirmpwd = confirmpwd;
	}

	public UserFormCheck(User user, String newpwd, String confirmpwd) {
		super();
		this.user = user;
		this.newpwd = newpwd;
		this.confirmpwd = confirmpwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getConfirmpwd() {
		return confirmpwd;
	}

	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}

	// 1表单验证
	/**
	 * 1.1用户注册表单验证
	 * @return
	 */
	public String checkUserRegForm() {
		return checkUsername() + checkUserpwd() + checkRegConfirmPwd() + checkUserxm() + checkBirthday() + checkEmail();
	}

	/**
	 * 1.2用户个人信息修改表单验证
	 * @return
	 */
	public String checkUserEditForm() {
		return checkUserxm() + checkBirthday() + checkEmail();
	}

	/**
	 * 1.3用户密码修改表单验证
	 * @return 用户密码错误信息 用户新密码错误信息 确认密码是否正确
	 */
	public String checkUserEditPwdForm() {
		return checkUserpwd() + checkNewpwd() + checkEditPwdConfirmPwd();
	}

	// 2分类验证
	/**
	 * 2.1验证用户名
	 * @return
	 */
	private String checkUsername() {
		StringBuffer strCheck = new StringBuffer();
		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			strCheck.append("请输入用户名!</br>");
		} else if (user.getUsername().length() < 6 || user.getUsername().length() > 20) {
			strCheck.append("用户名的长度必须在6-20之间!</br>");
		}
		return strCheck.toString();
	}

	/**
	 * 2.2.1验证用户密码
	 * @return
	 */
	private String checkUserpwd() {
		StringBuffer strCheck = new StringBuffer();
		if (user.getUserpwd() == null || user.getUserpwd().trim().equals("")) {
			strCheck.append("请输入用户密码!</br>");
		} else if (user.getUserpwd().length() < 6 || user.getUserpwd().length() > 16) {
			strCheck.append("密码的长度必须在6-16之间!</br>");
		}
		return strCheck.toString();
	}

	/**
	 * 2.2.2验证新用户密码
	 * @return
	 */
	private String checkNewpwd() {
		StringBuffer strCheck = new StringBuffer();
		if (newpwd == null || newpwd.trim().equals("")) {
			strCheck.append("请输入用户新密码!</br>");
		} else if (newpwd.length() < 6 || newpwd.length() > 16) {
			strCheck.append("新密码的长度必须在6-16之间!</br>");
		}
		return strCheck.toString();
	}

	/**
	 * 2.3.1验证用户密码与确认密码
	 * @return
	 */
	private String checkRegConfirmPwd() {
		StringBuffer strCheck = new StringBuffer();
		if (confirmpwd == null || confirmpwd.trim().equals("")) {
			strCheck.append("请输入确认密码!</br>");
		} else if (!user.getUserpwd().equals(confirmpwd)) {
			strCheck.append("密码与确认密码不匹配!</br>");
		}
		return strCheck.toString();
	}

	/**
	 * 2.3.2验证用户新密码与确认密码
	 * @return
	 */
	private String checkEditPwdConfirmPwd() {
		StringBuffer strCheck = new StringBuffer();
		if (confirmpwd == null || confirmpwd.trim().equals("")) {
			strCheck.append("请输入确认密码!</br>");
		} else if (!newpwd.equals(confirmpwd)) {
			strCheck.append("新密码与确认密码不匹配!</br>");
		}
		return strCheck.toString();
	}

	/**
	 * 2.4验证用户姓名
	 * @return
	 */
	private String checkUserxm() {
		if (user.getUserxm() == null || user.getUserxm().trim().equals("")) {
			return "请输入用户真实姓名!<br/>";
		}
		return "";
	}

	/**
	 * 2.5验证用户出生日期
	 * @return
	 */
	private String checkBirthday() {
		if (user.getBirthday() == null) {
			return "请输入用户出生日期!<br/>";
		}
		return "";
	}

	/**
	 * 2.6验证用户邮箱，对email格式的校验采用了正则表达式
	 * @return
	 */
	private String checkEmail() {
		StringBuffer strCheck = new StringBuffer();
		if (user.getEmail() == null || user.getEmail().trim().equals("")) {
			strCheck.append("请输入用户邮箱!</br>");
		} else if (!user.getEmail().matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+")) {
			strCheck.append("邮箱格式错误!</br>");
		}
		return strCheck.toString();
	}
}
