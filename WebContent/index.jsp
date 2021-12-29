<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网站首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res_css/index.css">
</head>
<body>

    <div class="container">
        <!-- 网页头部start -->
        <%@ include file="cp_header.jsp" %>
        <!-- 网页头部end -->
        <!-- 网页导航栏start -->
        <%@ include file="cp_nav.jsp" %>
        <!-- 网页导航栏end -->
        <div class="main">
        <!-- 网页主体start -->
        <%@ include file="cp_aside.jsp" %>
        <!-- 网页主体end -->
        </div>
        <!-- 网页尾部start -->
        <%@ include file="cp_fooder.jsp" %>
        <!-- 网页尾部end -->
    </div>

</body>
</html>