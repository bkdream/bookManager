package com.jltc.utils;

import javax.servlet.http.HttpServletRequest;

public class PageUtils {

	/**
	 * 处理当前页码
	 * @param req
	 * @return
	 */
	public static int getCurrentPage(HttpServletRequest req) {
		String currentPage = req.getParameter("curPage");
		if (currentPage == null || currentPage.trim().isEmpty() || "null".equals(currentPage)) {
			return 1;
		}
		return Integer.parseInt(currentPage);
	}

	/**
	 * 获取url：项目名/Servlet路径？参数字符串
	 * @param req
	 * @return
	 */
	public static String getUrl(HttpServletRequest req) {

		String contextPath = req.getContextPath();// 获取项目名
		String servletPath = req.getServletPath();// 获到servletPath，即/UserProcess
		String queryString = req.getQueryString();// 获取问号之后的参数

		if (queryString.contains("&curPage=")) {
			int index = queryString.lastIndexOf("&curPage=");
			queryString = queryString.substring(0, index);
		}

		return contextPath + servletPath + "?" + queryString;
	}
}
