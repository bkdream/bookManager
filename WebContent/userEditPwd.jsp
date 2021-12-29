<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/res_css/index.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/res_css/page.css">
</head>
<body>
<div class="container">
    <!-- 网页头部start -->
    <%@ include file="cp_header.jsp" %>
    <!-- 网页头部end -->
    <!-- 网页导航栏start -->
    <%@ include file="cp_nav.jsp" %>
    <!-- 网页导航栏end -->
    <!-- 网页主体start -->
    <div class="main">
        <%@include file="cp_aside.jsp"%>
        <div class="content">
            <form action="${pageContext.request.contextPath}/user" method="post" id="userEditPwdForm" name="userEditPwdForm" onsubmit="return checkUserEditPwdForm();">
                <input type="hidden" name="key" value="updateUserPassword">
                <input type="hidden" name="id" value="${sessionScope.loginMessage.id }">
                <ul>
                    <li>
                        <span class="notice">注意：用户密码6-16个字符组成</span>
                    </li>
                    <li>
                        <span class="tip">原有密码:&nbsp;</span>
                        <input type="text" id="userpwd" name="userpwd" onblur="userpwdObj">
                        <span class="errMsg" id="userpwdMsg"></span>
                        <span class="note">*6-16位字符组成</span>
                    </li>
                    <li>
                        <span class="tip">新设密码:&nbsp;</span>
                        <input type="password" id="newpwd" name="newpwd">
                        <span class="errMsg" id="newpwdMsg"></span>
                        <span class="note">*6-16位字符组成</span>
                    </li>
                    <li>
                        <span class="tip">确认密码:&nbsp;</span>
                        <input type="password" id="confirmpwd" name="confirmpwd">
                        <span class="errMsg" id="confirmpwdMsg"></span>
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
        <div class="msg">${requestScope.updateUserPasswordStatus }</div>
    </div>
    <!-- 网页主体end -->
    <!-- 网页尾部start -->
    <%@include file="cp_fooder.jsp"%>
    <!-- 网页尾部end -->
</div>
<!--引入javascript-->
<script language="javascript" src="res_js/jquery-3.4.1.js"></script>
<script language="javascript" src="res_js/userCommon.js"></script>
<script language="javascript" src="res_js/userEditPwd.js"></script>
</body>
</html>