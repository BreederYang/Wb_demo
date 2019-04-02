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
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<script>
    function uploadPicture(obj){
        var date = new Date().getTime();
        alert(date)
        $.ajaxFileUpload({
            url:'dy/ajaxupload?dateTime=' + date,
            secureuri : false,
            fileElementId : obj.id,
            dataType : 'json',
            async : false,
            success : function(data, status) {
                var dataArr= new Array(); //定义一数组
                dataArr=data.split(","); //字符分割
                dataArr[0] = dataArr[0].replace(/<.*?>/ig,"");
                $("#userPicture").html("<img src='upload/"+dataArr[0]+"' width='100' height='100'/>");
                //	把data 解析 把ID放入 imageId 中  用于 form 提交
                $("#portrait").val(dataArr[1]);
                console.log(dataArr[1])
            },
            error : function(data, status, e) {
                alert("上传失败，请重新选择");
            }
        });
    }
</script>
<body>
    <h1>用户信息</h1>
    <div id="userPicture">
        <img src="upload/${sessionUserPicture}" height="100" width="100" alt="用户头像">
    </div>
    <form action="/user/upMyInfo" method="get">
        修改头像:<input type="file" name="theimage" id="theimage" onchange="uploadPicture(this)"/>
        <input type="hidden" name="id" value="${sessionScope.session_user_auth.id}">
        <input type="text" name="email" value="${sessionScope.session_user_auth.email}">
        <input type="text" name="username" value="${sessionScope.session_user_auth.username}">
        <input type="hidden" id="portrait" name="portrait">
        <select name="sex">
            <option value="0">男</option>
            <option value="1">女</option>
        </select>
        <input type="submit" value="修改">
    </form>

</body>
</html>
