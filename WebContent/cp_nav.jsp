<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.loginMessage.role == 'admin'}">
    <div class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/user?key=UserMessage">管理用户</a></li>
            <li><a href="${pageContext.request.contextPath}/message?key=messageManager"> 管理留言</a></li>
            <li><a id="Exit" href="${pageContext.request.contextPath}/user?key=Exit">退出系统</a></li>
        </ul>
    </div>
</c:if>
<c:if test="${sessionScope.loginMessage.role == 'user'}">
    <div class="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/msgAdd.jsp">发表留言</a></li>
            <li><a href="${pageContext.request.contextPath}/msgQuery.jsp">查看留言</a></li>
            <li><a href="${pageContext.request.contextPath}/userEditInfo.jsp">修改个人资料</a></li>
            <li><a href="${pageContext.request.contextPath}/userEditPwd.jsp">修改密码</a></li>
            <li><a	id="Exit" href="${pageContext.request.contextPath}/user?key=Exit">退出系统</a></li>
        </ul>
    </div>
</c:if>