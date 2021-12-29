<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人资料</title>
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
            <form action="${pageContext.request.contextPath}/user" method="post" id="userEditForm" name="userEditForm" onsubmit="return checkUserEditForm();">
                <input type="hidden" name="id" value="${sessionScope.loginMessage.id}">
                <input type="hidden" name="key" value="updateUserMessage">
                <ul>
                    <li>
                        <span class="notice">注意：带<span class="redColor">*</span>号的项目为必选项，请全部填写</span>
                    </li>
                    <li>
                        <span class="tip">用户名:&nbsp;</span>
                        <input type="text" id="username" name="username" value="${sessionScope.loginMessage.username}" readonly>
                        <span class="errMsg" id="usernameMsg"></span>
                        <span class="note">*字母数字下划线6到20位, 不能是数字开头</span>
                    </li>
                    <input type="hidden" name="userpwd" value="${sessionScope.loginMessage.userpwd}">
                    <li>
                        <span class="tip">用户姓名:&nbsp;</span>
                        <input type="text" id="userxm" name="userxm" value="${sessionScope.loginMessage.userxm}">
                        <span class="errMsg" id="userxmMsg"></span>
                        <span class="note">*请输入用户真实姓名</span>
                    </li>
                    <li>
                        <span class="tip">出生日期:&nbsp;</span>
                        <input type="text" id="birthday" name="birthday" value="${sessionScope.loginMessage.birthday}" readonly class="Wdate" onclick="WdatePicker({skin:'whyGreen',minDate:'1900-01-01',maxDate:'2050-12-31'});" />
                        <span class="errMsg" id="birthdayMsg"></span>
                        <span class="note">*格式：2001-01-01</span>
                    </li>
                    <li>
                        <span class="tip">用户邮箱:&nbsp;</span>
                        <input type="text" id="email" name="email" value="${sessionScope.loginMessage.email}">
                        <span class="errMsg" id="emailMsg"></span>
                        <span class="note">*请输入有效的邮箱地址</span>
                    </li>
                    <li>
                        <span class="tip">用户性别:&nbsp;</span>
                        <input type="radio" id="gender" name="gender"
                                <c:if test="${sessionScope.loginMessage.gender == '男'}">
                                checked="checked"
                                </c:if>
                                value="男" />男
                        <input type="radio" id="gender" name="gender"
                                <c:if test="${sessionScope.loginMessage.gender eq '女'}">
                                checked="checked"
                                </c:if>
                               value="女" />女
                    </li>
                    <li>
                        <span class="tip">用户头像:&nbsp;</span>
                        <select id="headpic" name="headpic">
                            <c:forEach var="i" begin="1" end="16">
                                <c:set var="hp" value="head${i}.gif"/>
                                <option value="head${i}.gif"
                                        <c:if test="${sessionScope.loginMessage.headpic eq hp}">
                                            selected="selected"
                                        </c:if>
                                >head${i}</option>
                            </c:forEach>
                        </select>&nbsp;&nbsp;
                        <img id="pic"
                             src="${pageContext.request.contextPath}/res_images/face/${sessionScope.loginMessage.headpic}"
                             alt=头像>
                    </li>
                    <li>
                        <span class="tip"></span>
                        <input type="submit" value="提交" />
                        <input type="reset" value="重置" />
                    </li>
                </ul>
            </form>
        </div>
        <div class="msg">${requestScope.updateUserMessageStatues }</div>
    </div>
    <!-- 网页主体end -->
    <!-- 网页尾部start -->
    <%@include file="cp_fooder.jsp"%>
    <!-- 网页尾部end -->
</div>
</html>
<!--引入javascript-->
<script language="javascript" src="${pageContext.request.contextPath}/res_js/jquery-3.4.1.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/userCommon.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/userEdit.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/switchPic.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/My97DatePicker/WdatePicker.js"></script>