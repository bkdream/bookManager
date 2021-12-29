<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理留言</title>
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
        <%@ include file="../cp_aside.jsp" %>
        <div class="content">
            <div class="condition">
                <form action="${pageContext.request.contextPath}/message" method="get" name="msgmanager">
                    <input type="hidden" name="key" value="messageManager">
                    <ul>
                        <li>
                            <span class="tip">留言用户:&nbsp;</span>
                            <input type="text" id="username" name="username">
                            <span></span>
                            <span class="tip">留言日期:&nbsp;</span>
                            <input type="text" id="pubdate" name="pubdatestart" readonly class="Wdate" onclick="WdatePicker({skin:'whyGreen',minDate:'1900-01-01',maxDate:'2050-12-31'});" />
                            至
                            <input type="text" id="pubdate1" name="pubdateend" readonly class="Wdate" onclick="WdatePicker({skin:'whyGreen',minDate:'1900-01-01',maxDate:'2050-12-31'});" />

                        </li>
                        <li><span class="tip">留言类别:&nbsp;</span>
                            <select id="msgtype" name="msgtype">
                            <option value="all" selected="selected">全部留言</option>
                            <option value="一般">一般</option>
                            <option value="重要">重要</option>
                            </select>
                            <span></span>
                            <span class="tip">留言标题:&nbsp;</span>
                            <input type="text" id="title" name="title">
                            <span></span>
                            <input type="submit" value="查询"/></li>
                    </ul>
                </form>
            </div>
            <hr class="data mtop">
            <c:if test="${!empty requestScope.Message.list }">
            <div class="data mtop">
                <table>
                    <tbody>
                    <c:forEach var="message" items="${requestScope.Message.list }">
                        <tr>
                            <td width="20%">留言用户:${message.user.username }&nbsp;<br/>
                                留言时间:${message.pubdate }&nbsp;<br/>
                                留言类别:${message.msgtype }&nbsp;
                            </td>
                            <td width="70%">留言标题:${message.title }&nbsp;<br/>
                                留言内容:${message.content }&nbsp;<br/> <span
                                        class="reply">管理员回复:${message.reply }&nbsp;</span>
                            </td>
                            <td width="10%">
                            <a href="#" onClick="window.open('${pageContext.request.contextPath }/message?key=adminSelectMessageById&id=${message.id }','','width=650px,height=380px,top=100px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')">修改</a>

                                &nbsp;&nbsp;<a href="${pageContext.request.contextPath }/message?key=deleteMessage&id=${message.id }&curPage=<%=request.getParameter("curPage") %>&username=<%=request.getParameter("username") %>&pubdatestart=<%=request.getParameter("pubdatestart") %>&pubdateend=<%=request.getParameter("pubdateend") %>&msgtype=<%=request.getParameter("msgtype") %>&title=<%=request.getParameter("title") %>" 
                                onclick="return confirm('你确定要删除此条留言数据吗？')">删除</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pages mtop">
                第${requestScope.Message.curpage}页&nbsp;/&nbsp;共${requestScope.Message.totalpage}页
                <a href="${requestScope.Message.url }&curPage=1&username=<%=request.getParameter("username") %>&pubdatestart=<%=request.getParameter("pubdatestart") %>&pubdateend=<%=request.getParameter("pubdateend") %>&msgtype=<%=request.getParameter("msgtype") %>&title=<%=request.getParameter("title") %>">首页</a>
                <c:choose>
                    <c:when test="${requestScope.Message.curpage>1 }">
                        <a href="${requestScope.Message.url }&curPage=${requestScope.Message.curpage-1}&username=<%=request.getParameter("username") %>&pubdatestart=<%=request.getParameter("pubdatestart") %>&pubdateend=<%=request.getParameter("pubdateend") %>&msgtype=<%=request.getParameter("msgtype") %>&title=<%=request.getParameter("title") %>">上一页</a>
                    </c:when>
                    <c:otherwise>
                        上一页
                    </c:otherwise>
                </c:choose>
                <!--下一页-->
                <c:choose>
                    <c:when test="${requestScope.Message.curpage<requestScope.Message.totalpage }">
                        <a href="${requestScope.Message.url }&curPage=${requestScope.Message.curpage+1}&username=<%=request.getParameter("username") %>&pubdatestart=<%=request.getParameter("pubdatestart") %>&pubdateend=<%=request.getParameter("pubdateend") %>&msgtype=<%=request.getParameter("msgtype") %>&title=<%=request.getParameter("title") %>">下一页</a>
                    </c:when>
                    <c:otherwise>
                        下一页
                    </c:otherwise>
                </c:choose>
                <a href="${requestScope.Message.url }&curPage=${requestScope.Message.totalpage}&username=<%=request.getParameter("username") %>&pubdatestart=<%=request.getParameter("pubdatestart") %>&pubdateend=<%=request.getParameter("pubdateend") %>&msgtype=<%=request.getParameter("msgtype") %>&title=<%=request.getParameter("title") %>">尾页</a>
            </div>
            </c:if>
        </div>
        <div class="msg">${requestScope.deleteMessageStatus }</div>
    </div>
    <!-- 网页主体end -->
    <!-- 网页尾部start -->
    <%@ include file="../cp_fooder.jsp" %>
    <!-- 网页尾部end -->
</div>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>