<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="aside">
	<ul>
		<li>欢迎:&nbsp;${sessionScope.loginMessage.userxm}</li>
		<li>类别:&nbsp;${sessionScope.loginMessage.role}</li>
		<li><img
			src="${pageContext.request.contextPath}/res_images/face/${sessionScope.loginMessage.headpic}"
			alt=""></li>
	</ul>
</div>