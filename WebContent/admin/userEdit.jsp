<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/page.css">
</head>
<body>
<div class="content">
    <form action="${pageContext.request.contextPath}/user" method="post" id="userEditForm" name="userEditForm" onsubmit="return checkUserEditForm();">
		<input type="hidden" name="id" value="${requestScope.UserMessage.id }">
        <input type="hidden" name="key" value="updateUserMessage"/>
        <ul>
            <li>
                <span class="notice">注意：带<span class="redColor">*</span>号的项目为必选项，请全部填写</span>
            </li>
            <li>
                <span class="tip">用户名:&nbsp;</span>
                <input type="text" id="username" name="username" value="${requestScope.UserMessage.username }" readonly>
                <span class="errMsg" id="usernameMsg"></span>
                <span class="note">*字母数字下划线6到20位, 不能是数字开头</span>
            </li>
            <li>
                <span class="tip">用户姓名:&nbsp;</span>
                <input type="text" id="userxm" name="userxm" value="${requestScope.UserMessage.userxm}">
                <span class="errMsg" id="userxmMsg"></span>
                <span class="note">*请输入用户真实姓名</span>
            </li>
            <li>
                <span class="tip">出生日期:&nbsp;</span>
                <input type="text" id="birthday" name="birthday" value="${requestScope.UserMessage.birthday}" readonly
                       class="Wdate"
                       onclick="WdatePicker({skin:'whyGreen',minDate:'1900-01-01',maxDate:'2050-12-31'});"/>
                <span class="errMsg" id="birthdayMsg"></span>
                <span class="note">*格式：2001-01-01</span>
            </li>
            <li>
                <span class="tip">用户邮箱:&nbsp;</span>
                <input type="text" id="email" name="email" value="${requestScope.UserMessage.email}">
                <span class="errMsg" id="emailMsg"></span>
                <span class="note">*请输入有效的邮箱地址</span>
            </li>
            <li>
                <span class="tip">用户性别:&nbsp;</span>
                <input type="radio" id="gender" name="gender"
                        <c:if test="${requestScope.UserMessage.gender == '男'}">
                            checked="checked"
                        </c:if>
                       value="男"/>男&nbsp;&nbsp;
                <input type="radio" id="gender"
                        <c:if test="${requestScope.UserMessage.gender eq '女'}">
                            checked="checked"
                        </c:if>
                       name="gender" value="女"/>女
            </li>
            <li>
                ${requestScope.i.headpic}
                <span class="tip">用户头像:&nbsp;</span>
                <select id="headpic" name="headpic">
                    <c:forEach var="i" begin="1" end="16">
                        <c:set var="hp" value="head${i}.gif"/>
                        <option value="head${i}.gif"
                                <c:if test="${requestScope.UserMessage.headpic eq hp}">
                                    selected="selected"
                                </c:if>
                        >head${i}</option>
                    </c:forEach>

                </select>&nbsp;&nbsp;

                <img id="pic"
                     src="${pageContext.request.contextPath}/res_images/face/${requestScope.UserMessage.headpic}"
                     alt=头像>
            </li>

            <li>
                <span class="tip"></span>
                <input type="submit" value="提交"/>
                <input type="reset" value="重置"/>
            </li>
        </ul>
    </form>
    ${requestScope.updateUserMessageStatues }
</div>
<!--引入javascript-->
<script language="javascript" src="${pageContext.request.contextPath}/res_js/userCommon.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/userEdit.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/switchPic.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>