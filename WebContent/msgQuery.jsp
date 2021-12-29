<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查看留言</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/queryPage.css">
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
            <div class="condition">
                <form action="${pageContext.request.contextPath }/message" method="get" name="msgquery">
                    <input type="hidden" name="key" value="userSelectMessage">
                    <ul>
                        <li>
                            <span class="tip">留言类别:&nbsp;</span>
                            <select id="msgtype" name="msgtype">
                                <option value="all" selected="selected">全部留言</option>
                                <option value="一般">一般</option>
                                <option value="重要">重要</option>
                            </select>
                            <span></span>
                            <span class="tip">留言日期:&nbsp;</span>
                            <input type="text" id="pubdate" name="pubdate" readonly class="Wdate"
                                   onclick="WdatePicker({skin:'whyGreen',minDate:'1900-01-01',maxDate:'2050-12-31'});" />
                            至
                            <input type="text" id="pubdateend" name="pubdateend" readonly class="Wdate"
                                   onclick="WdatePicker({skin:'whyGreen',minDate:'1900-01-01',maxDate:'2050-12-31'});" />

                        </li>
                        <li>
                            <span class="tip">留言标题:&nbsp;</span>
                            <input type="text" id="title" name="title">
                            <span></span>
                            <span class="tip">留言用户:&nbsp;</span>
                            <input type="radio" id="usertype" name="usertype" checked="checked" value="me" />本人留言&nbsp;&nbsp;
                            <input type="radio" id="usertype" name="usertype" value="all" />全部留言
                            <span></span>
                            <input type="submit" value="查询" />
                        </li>
                    </ul>
                </form>
            </div>
            <hr class="data mtop">
            <c:if test="${!empty requestScope.userSelectMessage}">
            <div class="data mtop">
                <table>
                    <tbody>
                    <c:forEach var="lookMessage" items="${requestScope.userSelectMessage.list}">
                    <tr>

                        <td width="30%">
                            留言用户:&nbsp;${lookMessage.user.username}<br />
                            留言时间:&nbsp;${lookMessage.pubdate}<br />
                            留言类别:&nbsp;${lookMessage.msgtype}
                        </td>
                        <td width="70%">
                            留言标题:&nbsp;${lookMessage.title}<br />
                            留言内容:&nbsp;${lookMessage.content}<br />
                            管理员回复:&nbsp;<span style="color:blue">${lookMessage.reply}</span>
                        </td>

                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            
                <div class="pages mtop">
                    第${requestScope.userSelectMessage.curpage}页&nbsp;/&nbsp;共${requestScope.userSelectMessage.totalpage}页
                    <a href="${requestScope.userSelectMessage.url }">首页</a>
                    <c:choose>
                        <c:when test="${requestScope.userSelectMessage.curpage>1 }">
                            <a href="${requestScope.userSelectMessage.url }&curPage=${requestScope.userSelectMessage.curpage-1}">上一页</a>
                        </c:when>
                        <c:otherwise>
                            上一页
                        </c:otherwise>
                    </c:choose>
                    <!--下一页-->
                    <c:choose>
                        <c:when test="${requestScope.userSelectMessage.curpage<requestScope.userSelectMessage.totalpage }">
                            <a href="${requestScope.userSelectMessage.url }&curPage=${requestScope.userSelectMessage.curpage+1}">下一页</a>
                        </c:when>
                        <c:otherwise>
                            下一页
                        </c:otherwise>
                    </c:choose>
                    <a href="${requestScope.userSelectMessage.url }&curPage=${requestScope.userSelectMessage.totalpage}">尾页</a>
                </div>
                </c:if>
        </div>
    </div>
    <!-- 网页主体end -->
    <!-- 网页尾部start -->
    <%@include file="cp_fooder.jsp"%>
    <!-- 网页尾部end -->
</div>
<script language="javascript" src="${pageContext.request.contextPath}/res_js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>