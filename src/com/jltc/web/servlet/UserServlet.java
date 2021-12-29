package com.jltc.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jltc.constant.Constant;
import com.jltc.domain.PageBean;
import com.jltc.domain.User;
import com.jltc.service.UserService;
import com.jltc.service.impl.UserServiceImpl;
import com.jltc.utils.DateUtils;
import com.jltc.utils.PageUtils;

@SuppressWarnings("all")

public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String path = null;
		if ("login".equals(key)) {
			path = login(req, resp);// 管理员/用户登录功能
		} else if ("Exit".equals(key)) {
			path = Exit(req, resp);// 管理员/用户退出系统
		} else if ("updateUserMessage".equals(key)) {
			path = updateUserMessage(req, resp);// 管理员/用户更新信息 
		} else if ("userReg".equals(key)) {
			path = userReg(req, resp);// 用户注册方法
		} else if ("usernameExist".equals(key)) {
			path = usernameExist(req, resp);// 检测注册界面用户名是否重名
		} else if ("updateUserPassword".equals(key)) {
			path = updateUserPassword(req, resp);// 用户修改密码功能
		} else if ("UserMessage".equals(key)) {
			path = UserMessage(req, resp);// 管理员查询所有用户功能
		}else if("deleteUserMessage".equals(key)){
			path = deleteUserMessage(req,resp);//管理员查询用户信息后 删除用户数据
		}else if("adminSelectUserMessageById".equals(key)){
			path = adminSelectUserMessageById(req,resp);//管理员查询用户信息后 将此id用户的信息显示在修改界面内
		}else if("userPasswordExist".equals(key)){
			path = userPasswordExist(req,resp);//用户修改密码时 对比原密码是否正确
		}
		if (path != null) {
			// 请求重定向
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
	}
	
	private String userPasswordExist(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		User userMessage = (User) session.getAttribute("loginMessage");
		Integer id = userMessage.getId();
		String userpwd = req.getParameter("userpwd");
		UserService UserService = new UserServiceImpl();
		try {
			String oldUserPassword = UserService.userPasswordExist(id,userpwd);
			if (oldUserPassword == null) {
				resp.getWriter().print("noexist");
			} else {
				resp.getWriter().print("exist");
			}
		} catch (Exception e) {
			req.setAttribute("msg", e.getMessage());
		}
		return null;
	}

	private String adminSelectUserMessageById(HttpServletRequest req, HttpServletResponse resp) {
		//获取前面页面的值
				String id = req.getParameter("id");//这个id 是修改按钮传过来的 值在修改用户信息界面的url上
				Integer _id =Integer.valueOf(id);
				//调用业务层方法返回结果
				try {
					UserService userservice = new UserServiceImpl();
					User user=userservice.adminSelectUserMessageById(_id);
					req.setAttribute("UserMessage",user);
					return "/admin/userEdit.jsp";
				} catch (Exception e) {
					e.printStackTrace();
					req.setAttribute("UserMessage",Constant.DATABASE_ACCESS_ERROR);
					return "/admin/userManage.jsp";
				}
	}

	//管理员查询用户信息后 删除用户数据
	private String deleteUserMessage(HttpServletRequest req, HttpServletResponse resp) {
				//获取前面页面的值
				String id = req.getParameter("id");
				String curPage =req.getParameter("curPage");
				String username = req.getParameter("SearchUsername");
				String userxm = req.getParameter("SearchUserxm");
				String gender = req.getParameter("SearchGender");
				String role = req.getParameter("SearchRole");
				Integer _id =Integer.valueOf(id);
				//调用业务层方法返回结果
				try {
					UserService userservice = new UserServiceImpl();
					String result=userservice.deleteUserMessage(_id);
					if(Constant.SUCCESS.equals(result)){
						req.setAttribute("deleteUserMessageStatus", 
								"<script>"
								+ "alert(\"删除成功！\");"
								+ "</script>");
						return "/user?key=UserMessage";
					}else{
						req.setAttribute("deleteUserMessageStatus", 
								"<script>"
								+ "alert(\"删除失败！\");"
								+ "</script>");
						return "/user?key=UserMessage";
					}
				} catch (Exception e) {
					e.printStackTrace();
					req.setAttribute("deleteUserMessageStatus",Constant.DATABASE_ACCESS_ERROR);
					return "/admin/userManage.jsp";
				}
	}
	// 管理员查询所有用户功能
	private String UserMessage(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("SearchUsername");
		String userxm = req.getParameter("SearchUserxm");
		String gender = req.getParameter("SearchGender");
		String role = req.getParameter("SearchRole");
		User user = new User();
		user.setUsername(username);
		user.setUserxm(userxm);
		user.setGender(gender);
		user.setRole(role);
		try {
			UserService userService = new UserServiceImpl();
			int curpage = PageUtils.getCurrentPage(req);
			PageBean<User> pb = userService.UserMessage(curpage, Constant.PAGE_SIZE, user);
			pb.setUrl(PageUtils.getUrl(req));
			if (pb.getList().isEmpty()) {
				req.setAttribute("UserMessageStatus", "无此条用户信息");
			} else {
				req.setAttribute("UserMessage", pb);
			}
			return "/admin/userManage.jsp";
		} catch (Exception e) {
			// e.printStackTrace();
			req.setAttribute("msg", Constant.DATABASE_ACCESS_ERROR);
			return "/admin/userManage.jsp";
		}
	}
	// 用户修改密码功能
	private String updateUserPassword(HttpServletRequest req, HttpServletResponse resp) {
		// 获取id值，原密码和用户输入的原密码进行比对 如果成功执行update方法
		String userpwd = req.getParameter("userpwd");
		String newpwd = req.getParameter("newpwd");
		String confirmpwd = req.getParameter("confirmpwd");
		HttpSession session = req.getSession();
		User userSession = (User) session.getAttribute("loginMessage");
		String password = userSession.getUserpwd();
		Integer id = userSession.getId();
		if (userpwd.equals(password)) {
			User user1 = new User();
			user1.setId(id);
			user1.setUserpwd(newpwd);
			try {
				UserService UserService = new UserServiceImpl();
				String result = UserService.updateUserPassword(user1, confirmpwd, newpwd);
				if (Constant.SUCCESS.equals(result)) {
					req.setAttribute("updateUserPasswordStatus",
									"<script>"
									+ "alert(\"修改密码成功，请重新登录！\");"
									+ "location.href=\"login.jsp\""
									+ "</script>");
					return "/userEditPwd.jsp";
				} else if (Constant.FAIL.equals(result)) {
					req.setAttribute("updateUserPasswordStatus", 
							"<script>"
							+ "alert(\"修改密码失败！\");"
							+ "</script>");
					return "/userEditPwd.jsp";
				} else {
					req.setAttribute("updateUserPasswordStatus", 
							"<script>"
							+ "alert(\""+result+"\");"
							+ "</script>");
					return "/userEditPwd.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("updateUserPasswordStatus", Constant.DATABASE_ACCESS_ERROR);
				return "/userEditPwd.jsp";
			}
		} else {
			req.setAttribute("updateUserPasswordStatus", "原密码输入错误，请重新输入！");
			return "/userEditPwd.jsp";
		}
	}

	// 检测注册界面用户名是否重名
	private String usernameExist(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		UserService UserService = new UserServiceImpl();
		try {
			String oldUsername = UserService.usernameExist(username);
			if (oldUsername == null) {
				resp.getWriter().print("noexist");
			} else {
				resp.getWriter().print("exist");
			}
		} catch (Exception e) {
			req.setAttribute("msg", e.getMessage());
		}
		return null;
	}

	// 用户注册方法
	private String userReg(HttpServletRequest req, HttpServletResponse resp) {
		// 1.获取表单的七个数据
		String username = req.getParameter("username");
		String userpwd = req.getParameter("userpwd");
		String confirmpwd = req.getParameter("confirmpwd");
		String userxm = req.getParameter("userxm");
		String birthday = req.getParameter("birthday");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String headpic = req.getParameter("headpic");

		// 2.封装user对象
		User user = new User();
		user.setUsername(username);
		user.setUserpwd(userpwd);
		user.setUserxm(userxm);
		user.setBirthday(DateUtils.stringToDate(birthday));
		user.setEmail(email);
		user.setGender(gender);
		user.setHeadpic(headpic);
		// 调用service业务层方法实现数据添加
		try {
			UserService userservice = new UserServiceImpl();
			String result = userservice.userReg(user, confirmpwd);

			// 4.根据service业务层方法返回结果，处理页面跳转
			if (result.equals(Constant.SUCCESS)) {
				req.setAttribute("userRegStatus",
						"<script>"
								+ "alert(\"用户注册成功！\");"
								+ "location.href=\"login.jsp\""
								+ "</script>");
				return "/userReg.jsp";
			} else if (result.equals(Constant.FAIL)) {
				req.setAttribute("userRegStatus", 
						"<script>"
								+ "alert(\"用户注册失败！请重试\");"
								+ "</script>");
				return "/userReg.jsp";
			} else {
				req.setAttribute("userRegStatus", result);
				return "/userReg.jsp";
			}
		} catch (Exception e) {
			req.setAttribute("msg", Constant.DATABASE_ACCESS_ERROR);
			return "/userReg.jsp";
		}
	}

	// 管理员/用户更新信息
	private String updateUserMessage(HttpServletRequest req, HttpServletResponse resp) {
		// 获取页面文本框值
		String id = req.getParameter("id");
		String username = req.getParameter("username");
		String userxm = req.getParameter("userxm");
		String birthday = req.getParameter("birthday");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String headpic = req.getParameter("headpic");
		HttpSession session = req.getSession();
		User userMessage = (User) session.getAttribute("loginMessage");
		String role = userMessage.getRole();
		// 封装
		User user = new User();
		if(id!=null){
		user.setId(Integer.parseInt(id));
		}
		user.setUsername(username);
		user.setUserxm(userxm);
		user.setBirthday(DateUtils.stringToDate(birthday));
		user.setEmail(email);
		user.setGender(gender);
		user.setHeadpic(headpic);
		// 调用service 获取数据
		UserService UserService = new UserServiceImpl();
		String result = UserService.updateUserMessage(user);
		if(!"admin".equals(role)){
		if (Constant.SUCCESS.equals(result)) {
			req.setAttribute("updateUserMessageStatues", 
					"<script>"
							+ "alert(\"用户信息修改成功\\n"
							+ "请重新登录！\");"
							+ "location.href=\"login.jsp\""
							+ "</script>");
			return "/userEditInfo.jsp";
		} else if (Constant.FAIL.equals(result)) {
			req.setAttribute("updateUserMessageStatues",
					"<script>"
							+ "alert(\"用户信息修改失败！\");"
							+ "</script>");
			return "/userEditInfo.jsp";
		} else {
			req.setAttribute("updateUserMessageStatues", result);
			return "/userEditInfo.jsp";
		}
		}else{
			if (Constant.SUCCESS.equals(result)) {
				req.setAttribute("updateUserMessageStatues", 
						"<script>"
								+ "alert(\"用户信息修改成功\");"
								+ "window.opener.location.reload();"
								+ "window.close();"
								+ "</script>");
				return "/admin/userEdit.jsp";
			} else if (Constant.FAIL.equals(result)) {
				req.setAttribute("updateUserMessageStatues",
						"<script>"
								+ "alert(\"用户信息修改失败！\");"
								+ "</script>");
				return "/admin/userEdit.jsp";
			} else {
				req.setAttribute("updateUserMessageStatues", result);
				return "/admin/userEdit.jsp";
			}
		}
	}

	// 管理员/用户退出系统
	private static String Exit(HttpServletRequest req, HttpServletResponse resp) {
		String contextPath = req.getContextPath();// 获取当前项目名称
		HttpSession session = req.getSession();
		session.removeAttribute("loginMessage");
		session.invalidate();
		try {
			resp.sendRedirect(contextPath + "/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 管理员/用户登录功能
	private String login(HttpServletRequest req, HttpServletResponse resp) {
		String contextPath = req.getContextPath();// 获取当前项目名称
		// 获取登录界面账号密码
		String username = req.getParameter("username");
		String password = req.getParameter("userpwd");
		// 封装
		User user = new User();
		user.setUsername(username);
		user.setUserpwd(password);
		try {
			UserService UserService = new UserServiceImpl();
			HttpSession session = req.getSession();
			User userMessage = UserService.login(user);
			// 记住用户名
			if ("ok".equals(req.getParameter("saveusername"))) {
				Cookie cookie = new Cookie("saveusername", URLEncoder.encode(user.getUsername(), "utf-8"));
				cookie.setMaxAge(60 * 60 * 24 * 7);// cookie有效期
				resp.addCookie(cookie);
			}
			if (userMessage.getId() == null) {// 判断返回数据是否为空
				req.setAttribute("loginFail", 
						"<script>"
								+ "alert(\"用户名或密码错误\\n"
								+ "请重新输入！\");"
								+ "</script>");
				return "/login.jsp";
			} else {
				session.setAttribute("loginMessage", userMessage);
				resp.sendRedirect(contextPath + "/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "/login.jsp";
		}
		return null;
	}

}
