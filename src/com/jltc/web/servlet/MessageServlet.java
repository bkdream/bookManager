package com.jltc.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jltc.constant.Constant;
import com.jltc.domain.Message;
import com.jltc.domain.PageBean;
import com.jltc.domain.User;
import com.jltc.service.MessageService;
import com.jltc.service.impl.MessageServiceImpl;
import com.jltc.utils.DateUtils;
import com.jltc.utils.PageUtils;

public class MessageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String path = null;
		if ("addMessage".equals(key)) {
			path = addMessage(req, resp);// 用户发表留言功能
		} else if ("userSelectMessage".equals(key)) {
			path = userSelectMessage(req, resp);// 用户查询本人留言和全部留言的功能
		} else if ("messageManager".equals(key)) {
			path = messageManager(req, resp);// 管理员查看所有留言功能
		} else if ("deleteMessage".equals(key)) {
			path = deleteMessage(req, resp);// 管理员查看所有留言后 删除留言功能
		} else if ("adminSelectMessageById".equals(key)) {
			path = adminSelectMessageById(req, resp);
		} else if ("adminUpdateMessageReply".equals(key)) {
			path = adminUpdateMessageReply(req, resp);
		}
		if (path != null) {
			// 请求重定向
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
	}

	private String adminUpdateMessageReply(HttpServletRequest req, HttpServletResponse resp) {
		// 获取页面值
		String id = req.getParameter("id");
		String msgtype = req.getParameter("msgtype");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String reply = req.getParameter("reply");
		// 封装//
		Message message2 = new Message();
		message2.setId(new Integer(id));
		message2.setMsgtype(msgtype);
		message2.setTitle(title);
		message2.setContent(content);
		message2.setReply(reply);
		// 调用服务类传值
		MessageService InsertMessageService = new MessageServiceImpl();
		String result = InsertMessageService.adminUpdateMessageReply(message2);
		if (result == Constant.SUCCESS) {
			req.setAttribute("updateMessageStatues", "<script>" + "alert(\"管理员回复成功！\");"
					+ "window.opener.location.reload();" + "window.close();" + "</script>");
			return "/admin/msgReply.jsp";
		} else {
			req.setAttribute("updateMessageStatues", "<script>" + "alert(\"管理员回复失败！\");"
					+ "</script>");
			return "/admin/msgReply.jsp";
		}
	}

	private String adminSelectMessageById(HttpServletRequest req, HttpServletResponse resp) {
		// 1.获取文本框中的值
		String id = req.getParameter("id");
		Integer _id = Integer.valueOf(id);
		// 调用业务层方法传递数据
		try {
			MessageService messageService = new MessageServiceImpl();
			Message msg = messageService.adminSelectMessageById(_id);
			req.setAttribute("Message", msg);
			return "/admin/msgReply.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("Message", Constant.DATABASE_ACCESS_ERROR);
			return "/admin/msgManage.jsp";
		}
	}

	private String deleteMessage(HttpServletRequest req, HttpServletResponse resp) {
		// 获取前面页面的值
		String id = req.getParameter("id");
		Integer _id = Integer.valueOf(id);
		// 调用业务层方法返回结果
		try {
			MessageService userservice = new MessageServiceImpl();
			String result = userservice.deleteMessage(_id);
			if (Constant.SUCCESS.equals(result)) {
				req.setAttribute("deleteMessageStatus", "<script>" + "alert(\"留言删除成功！\");" + "</script>");
				return "/message?key=messageManager";
			} else {
				req.setAttribute("deleteMessageStatus", "<script>" + "alert(\"留言删除失败！\");" + "</script>");
				return "/admin/userManage.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("UserMessage", Constant.DATABASE_ACCESS_ERROR);
			return "/admin/userManage.jsp";
		}
	}

	private String messageManager(HttpServletRequest req, HttpServletResponse resp) {
		// 1.获取文本框中的值
		String SearchUsername = req.getParameter("username");
		String SearchPubDateStart = req.getParameter("pubdatestart");
		String SearchPubDateEnd = req.getParameter("pubdateend");
		String SearchMessageType = req.getParameter("msgtype");
		String SearchTitle = req.getParameter("title");
		// 2.封装
		Message msg = new Message();
		if (!"".equals(msg.getPubdate())) {
			msg.setPubdate(DateUtils.stringToDate(SearchPubDateStart));
			msg.setPubdateend(DateUtils.stringToDate(SearchPubDateEnd));
		}
		msg.setMsgtype(SearchMessageType);
		msg.setTitle(SearchTitle);
		User user = new User();
		user.setUsername(SearchUsername);
		msg.setUser(user);
		// 3.调用service层 传递数据
		try {
			MessageService InsertMessageService = new MessageServiceImpl();
			int curpage = PageUtils.getCurrentPage(req);
			PageBean<Message> pb = InsertMessageService.messageManager(curpage, Constant.PAGE_SIZE, msg);
			pb.setUrl(PageUtils.getUrl(req));
			req.setAttribute("Message", pb);
			return "/admin/msgManage.jsp";
		} catch (Exception e) {
			req.setAttribute("Message", Constant.DATABASE_ACCESS_ERROR);
			return "/admin/msgManage.jsp";
		}
	}

	private String userSelectMessage(HttpServletRequest req, HttpServletResponse resp) {
		// 获取界面上的值
		String msgtype = req.getParameter("msgtype");
		String pubdate = req.getParameter("pubdate");
		String title = req.getParameter("title");
		String usertype = req.getParameter("usertype");
		String userid = req.getParameter("id");
		String pubdateend = req.getParameter("pubdateend");
		// 封装
		HttpSession session = req.getSession();
		User vakue = (User) session.getAttribute("loginMessage");
		Integer id = vakue.getId();
		Message Message = new Message();
		Message.setMsgtype(msgtype);
		Message.setPubdate(DateUtils.stringToDate(pubdate));
		Message.setTitle(title);
		Message.setUsertype(usertype);
		Message.setPubdateend(DateUtils.stringToDate(pubdateend));
		User user = new User();
		user.setId(id);
		Message.setUser(user);
		// 调用service 接收数据
		try {
			MessageService messageService = new MessageServiceImpl();
			int curpage = PageUtils.getCurrentPage(req);
			PageBean<Message> lists = messageService.userSelectMessage(curpage, Constant.PAGE_SIZE, Message);
			lists.setUrl(PageUtils.getUrl(req));
			req.setAttribute("userSelectMessage", lists);
			return "/msgQuery.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/msgQuery.jsp";
		}
	}

	private String addMessage(HttpServletRequest req, HttpServletResponse resp) {
		// 1.获取表单数据
		String id = req.getParameter("id");
		String msgtype = req.getParameter("msgtype");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Date now = new Date();
		// 2.封装Message对象
		Message message = new Message();
		message.setId(Integer.parseInt(id));
		message.setMsgtype(msgtype);
		message.setTitle(title);
		message.setContent(content);
		message.setPubdate(now);
		// 3.调用service业务层方法实现数据添加
		try {
			MessageService insertMessageService = new MessageServiceImpl();
			String result = insertMessageService.addMessage(message);
			// 4.处理页面跳转
			if (result.equals(Constant.SUCCESS)) {
				req.setAttribute("addMessageStatus", "用户发表成功！");
				return "/msgAdd.jsp";
			} else {
				req.setAttribute("addMessageStatus", "用户发表失败！");
				return "/msgAdd.jsp";
			}
		} catch (Exception e) {
			req.setAttribute("msg", Constant.DATABASE_ACCESS_ERROR);
			return "/msgAdd.jsp";
		}
	}
}
