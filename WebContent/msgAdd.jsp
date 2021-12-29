<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发表留言</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/res_css/index.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/res_css/page.css">
</head>
<body>
	<div class="container">
		<!-- 网页头部start -->
		<%@ include file="cp_header.jsp"%>
		<!-- 网页头部end -->
		<!-- 网页导航栏start -->
		<%@ include file="cp_nav.jsp"%>
		<!-- 网页导航栏end -->
		<div class="main">
			<!-- 网页主体start -->
			<%@ include file="cp_aside.jsp"%>
			<div class="content">
				<form action="${pageContext.request.contextPath}/message" method="post" name="msgadd">
					<input type="hidden" name="key" value="addMessage" />
					<input type="hidden" name="id" value="${sessionScope.loginMessage.id }">
					<ul>
						<li>
						<span class="notice">
							带<span class="redColor">*</span>号的项目为必选项，请全部填写
						</span>
						</li>
						<li>
						<span class="tip">留言类别:&nbsp;</span>
						 	<input type="radio"	id="msgtype" name="msgtype" checked="checked" value="一般" />一般&nbsp;&nbsp;
							<input type="radio"	id="msgtype" name="msgtype" value="重要" />重要
						</li>
						<li>
						<span class="tip">留言标题:&nbsp;</span>
						<input type="text"	id="title" name="title"> 
						<span class="errMsg"></span> 
						<span	class="note">*</span>
						</li>
						<li>
						<span class="tip">留言内容:&nbsp;</span> 
						<textarea	id="content" name="content"></textarea>
						<span class="errMsg"></span>
						<span class="note">*</span>
						</li>
						<li>
						<span class="tip"></span>
						<input type="submit" value="提交" />
						<input type="reset" value="重置" />
						</li>
					</ul>
				</form>
			</div>
			<div class="msg">${requestScope.addMessageStatus }</div>
			<!-- 网页主体end -->
		</div>
		<!-- 网页尾部start -->
		<%@ include file="cp_fooder.jsp"%>
		<!-- 网页尾部end -->
	</div>
</body>
</html>