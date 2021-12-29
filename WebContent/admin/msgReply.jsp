<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复留言</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/page.css">
</head>
<body>
<div class="content">
  <form action="${pageContext.request.contextPath}/message" method="post" name="msgreply">
    <input type="hidden" name="id" value="${requestScope.Message.id}"/>
    <input type="hidden" name="key" value="adminUpdateMessageReply" />
    <ul>
      <li>
        <span class="tip">留言用户:&nbsp;</span>
        <input type="text" id="username" name="username" value="${requestScope.Message.user.username}" readonly>
        <span class="errMsg"></span>
        <span class="note">*</span>
      </li>
      <li>
        <span class="tip">留言类别:&nbsp;</span>

        <input type="radio" id="msgtype" name="msgtype"
                <c:if test="${requestScope.Message.msgtype == '一般'}">
                  checked="checked"
                </c:if>
               value="一般" />一般&nbsp;&nbsp;
        <input type="radio" id="msgtype" name="msgtype"
                <c:if test="${requestScope.Message.msgtype == '重要'}">
                  checked="checked"
                </c:if>
               value="重要" />重要
      </li>
      <li>
        <span class="tip">留言标题:&nbsp;</span>
        <input type="text" id="title" name="title" value="${requestScope.Message.title}">
        <span class="errMsg"></span>
        <span class="note">*</span>
      </li>
      <li>
        <span class="tip">留言内容:&nbsp;</span>
        <textarea id="content"  name="content" >${requestScope.Message.content}</textarea>
        <span class="errMsg"></span>
        <span class="note">*</span>
      </li>
      <li>
        <span class="tip">回复内容:&nbsp;</span>
        <textarea id="reply" name="reply">${requestScope.Message.reply}</textarea>
        <span class="errMsg"></span>
        <span class="note">*请输入回复留言</span>
      </li>
      <li>
        <span class="tip"></span>
        <input type="submit" value="提交" />
        <input type="reset" value="重置" />
      </li>
    </ul>
  </form>
${requestScope.updateMessageStatues }
</div>

</body>
</html>