<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.loginMessage}">
	<script>
		alert('当前正在非法登录，请立即停止')
		window.location.href = "${pageContext.request.contextPath}/login.jsp";
	</script>
</c:if>

<div class="header">留言系统</div>