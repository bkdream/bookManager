<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理用户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/queryPage.css">
</head>
<body>
    <div class="container">
        <!-- 网页头部start -->
        <%@ include file="../cp_header.jsp" %>
        <!-- 网页头部end -->
        <!-- 网页导航栏start -->
        <%@ include file="../cp_nav.jsp" %>
        <!-- 网页导航栏end -->
        <!-- 网页主体start -->
        <div class="main">
            <%@ include file="../cp_aside.jsp"%>
            <div class="content">
                <div class="condition">
                    <form action="${pageContext.request.contextPath}/user" method="get" name="usermanager">
                    <input type="hidden" name="key" value="UserMessage"/>
                        <ul>
                            <li>
                                <span class="tip">用户名:&nbsp;</span><input type="text" name="SearchUsername">
                                <span></span>
                                <span class="tip">用户姓名:&nbsp;</span><input type="text" name="SearchUserxm">
                            </li>
                            <li>
                                <span class="tip">用户性别:&nbsp;</span><select name="SearchGender">
                                    <option value="all" selected="selected">全部性别</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                                <span></span>
                                <span class="tip">用户角色:&nbsp;</span><input type="text" name="SearchRole">
                                <span></span>
                                <input type="submit" value="查询" />
                            </li>
                        </ul>
                    </form>
                </div>
                <hr class="mtop">
                <c:if test="${!empty requestScope.UserMessage.list}">
                <div class="data mtop">
                    <table>
                        <thead>
                            <tr>
                                <th width=10%>用户名</th>
                                <th width=10%>用户密码</th>
                                <th width=10%>用户姓名</th>
                                <th width=13%>出生日期</th>
                                <th width=12%>用户邮箱</th>
                                <th width=10%>用户性别</th>
                                <th width=10%>用户头像</th>
                                <th width=10%>用户角色</th>
                                <th width=15%>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                       <c:forEach var="message" items="${requestScope.UserMessage.list }">
                            <tr>
                                <td>${message.username }</td>
                                           <td>${message.userpwd }</td>
                                <td>${message.userxm }</td>
                                <td>${message.birthday }</td>
                                <td>${message.email }</td>
                                <td>${message.gender }</td>
                                <td><img src="${pageContext.request.contextPath}/res_images/face/${message.headpic }"></td>
                                <td>${message.role }</td>
                                <td><a href="#" onClick="window.open('${pageContext.request.contextPath }/user?key=adminSelectUserMessageById&id=${message.id }','','width=650px,height=380px,top=100px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')">修改</a>
                                    &nbsp;&nbsp;
                                    <a href="${pageContext.request.contextPath}/user?key=deleteUserMessage&id=${message.id }&curPage=<%=request.getParameter("curPage") %>&SearchUsername=<%=request.getParameter("SearchUsername") %>&SearchUserxm=<%=request.getParameter("SearchUserxm") %>&SearchGender=<%=request.getParameter("SearchGender") %>&SearchRole=<%=request.getParameter("SearchRole") %>" 
                                    onclick="return confirm('你确定要删除此条用户信息吗？')">删除</a></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="pages mtop">
                    第${requestScope.UserMessage.curpage}页&nbsp;/&nbsp;共${requestScope.UserMessage.totalpage}页
                    <a href="${requestScope.UserMessage.url }&curPage=1&SearchUsername=<%=request.getParameter("SearchUsername") %>&SearchUserxm=<%=request.getParameter("SearchUserxm") %>&SearchGender=<%=request.getParameter("SearchGender") %>&SearchRole=<%=request.getParameter("SearchRole") %>">首页</a>
                    <c:choose>
                        <c:when test="${requestScope.UserMessage.curpage>1 }">
                            <a href="${requestScope.UserMessage.url }&curPage=${requestScope.UserMessage.curpage-1}&SearchUsername=<%=request.getParameter("SearchUsername") %>&SearchUserxm=<%=request.getParameter("SearchUserxm") %>&SearchGender=<%=request.getParameter("SearchGender") %>&SearchRole=<%=request.getParameter("SearchRole") %>">上一页</a>
                        </c:when>
                        <c:otherwise>
                            上一页
                        </c:otherwise>
                    </c:choose>
                    <!--下一页-->
                    <c:choose>
                        <c:when test="${requestScope.UserMessage.curpage<requestScope.UserMessage.totalpage }">
                            <a href="${requestScope.UserMessage.url }&curPage=${requestScope.UserMessage.curpage+1}&SearchUsername=<%=request.getParameter("SearchUsername") %>&SearchUserxm=<%=request.getParameter("SearchUserxm") %>&SearchGender=<%=request.getParameter("SearchGender") %>&SearchRole=<%=request.getParameter("SearchRole") %>">下一页</a>
                        </c:when>
                        <c:otherwise>
                            下一页
                        </c:otherwise>
                    </c:choose>
                    <a href="${requestScope.UserMessage.url }&curPage=${requestScope.UserMessage.totalpage}&SearchUsername=<%=request.getParameter("SearchUsername") %>&SearchUserxm=<%=request.getParameter("SearchUserxm") %>&SearchGender=<%=request.getParameter("SearchGender") %>&SearchRole=<%=request.getParameter("SearchRole") %>">尾页</a>
                </div>
                </c:if>
            </div>

            <div class="msg">${requestScope.UserMessageStatus}</div>
			${requestScope.deleteUserMessageStatus }
        </div>
        <!-- 网页主体end -->
        <!-- 网页尾部start -->
        <%@ include file="../cp_fooder.jsp" %>
        <!-- 网页尾部end -->
    </div>
</body>
</html>