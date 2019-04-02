<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息</title>
</head>
<base href="<%=basePath%>">
<body>
    <a href="dy/showIndexDynamic"></a>
    <h1>用户信息</h1>
    <img src="upload/${userPictuer}" height="100" width="100" alt="用户头像">
    <p>${userInfo.username}</p>
    <p>${userInfo.email}</p>
    <p>
        <c:if test="${userInfo.sex == 0}">男</c:if>
        <c:if test="${userInfo.sex == 1}">女</c:if>
    </p>

</body>
</html>
