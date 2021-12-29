<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/page.css">
</head>
<body>
 <div class="content">
        <form action="${pageContext.request.contextPath}/user" method="post" id="userRegForm" name="userRegForm" onsubmit="return checkUserRegForm();">
        <input type="hidden" name="key" value="userReg"/>
            <ul>
                <li>
                    <span class="notice">注意：带<span class="redColor">*</span>号的项目为必选项，请全部填写</span>
                </li>
                <li>
                    <span class="tip">用户名:&nbsp;</span>
                    <input type="text" id="username" name="username" onblur="usernameObj">
                    <span class="errMsg" id="usernameMsg"></span>
                    <span class="note">*字母数字下划线6到20位, 不能是数字开头</span>
                </li>
                <li>
                    <span class="tip">用户密码:&nbsp;</span>
                    <input type="password" id="userpwd" name="userpwd">
                    <span class="errMsg" id="userpwdMsg"></span>
                    <span class="note">*6-16位字符组成</span>
                </li>
                <li>
                    <span class="tip">确认密码:&nbsp;</span>
                    <input type="password" id="confirmpwd" name="confirmpwd">
                    <span class=" errMsg" id="confirmpwdMsg"></span>
                    <span class="note">*</span>
                </li>
                <li>
                    <span class="tip">用户姓名:&nbsp;</span>
                    <input type="text" id="userxm" name="userxm">
                    <span class="errMsg" id="userxmMsg"></span>
                    <span class="note">*请输入用户真实姓名</span>
                </li>
                <li>
                    <span class="tip">出生日期:&nbsp;</span>
                    <input type="text" id="birthday" name="birthday" readonly class="Wdate" onclick="WdatePicker({skin:'whyGreen',minDate:'2000-01-01',maxDate:'2050-12-31'});" />
                    <span class="errMsg" id="birthdayMsg"></span>
                    <span class="note">*格式：2001-01-01</span>
                </li>
                <li>
                    <span class="tip">用户邮箱:&nbsp;</span>
                    <input type="text" id="email" name="email">
                    <span class="errMsg" id="emailMsg"></span>
                    <span class="note">*请输入有效的邮箱地址</span>
                </li>
                <li>
                    <span class="tip">用户性别:&nbsp;</span>
                    <input type="radio" id="gender" name="gender" checked="checked" value="男" />男&nbsp;&nbsp;
                    <input type="radio" id="gender" name="gender" value="女" />女
                </li>
                <li>
                    <span class="tip">用户头像:&nbsp;</span>
                    <select id="headpic" name="headpic">
                        <option value="head1.gif">head1</option>
                        <option value="head2.gif">head2</option>
                        <option value="head3.gif">head3</option>
                        <option value="head4.gif">head4</option>
                        <option value="head5.gif">head5</option>
                        <option value="head6.gif">head6</option>
                        <option value="head7.gif">head7</option>
                        <option value="head8.gif">head8</option>
                        <option value="head9.gif">head9</option>
                        <option value="head10.gif">head10</option>
                        <option value="head11.gif">head11</option>
                        <option value="head12.gif">head12</option>
                        <option value="head13.gif">head13</option>
                        <option value="head14.gif">head14</option>
                        <option value="head15.gif">head15</option>
                        <option value="head16.gif">head16</option>
                    </select>&nbsp;&nbsp;
                    <img id="pic" src="${pageContext.request.contextPath}/res_images/face/head1.gif" alt=头像>
                </li>
                <li>
                    <span class="tip"></span>
                    <input type="submit" value="提交" />
                    <input type="reset" value="重置" />
                </li>
            </ul>
        </form>
        <div class="msg">${requestScope.userRegStatus }</div>
    </div>
    <!--引入javascript-->
    <script language="JavaScript" src="${pageContext.request.contextPath}/res_js/jquery-3.4.1.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/res_js/userCommon.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/res_js/userReg.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/res_js/switchPic.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/res_js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>