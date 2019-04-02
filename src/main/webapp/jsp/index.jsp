<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>动态广场</title>
</head>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<base href="<%=basePath%>">
<script>
    /**
     * 加载用户头像
     * @param ids
     * @param did
     * @return 用户头像
     */
    function findPictureInIds(ids,did) {
          $.ajax({
            type:"GET",
            url:"/img/findPictureInIds?ids="+ids,
            dataType:"json",
            success:function (data) {
                var names = "";
                $.each(data,function (index,value) {
                    var pictureName = value.name;
                    //upload/
                    names += "<img src='upload/"+pictureName+"' width='200' height='200' alt='图片为："+pictureName+"'/><br>";
                })
                $(".dy_pictures_"+did).html(names);
            },
        });
    }

    /**
     * 加载评论
     * @param did
     */
    function findCommentByDid(did) {
        $.ajax({
            type:"GET",
            url:"/comment/findCommentByDid?did="+did,
            dataType:"json",
            success:function (data) {
                var comments = "";
                $.each(data,function (index,value) {
                    var c_id =  value.id;
                    var uid = value.uid;
                    var uname = value.uName;
                    var context = value.context;
                    comments+= "<div class="+'dycomment_'+did+"><a href='/user/showUserInformation?id="+uid+"'>"+uname+":</a>"+"<span>"+context+"</span><br>";
                    comments+="<input type='button' value='加载讨论内容' onclick='findDiscussByCid("+c_id+','+did+")' />";
                    comments+="<div class="+'dy_comment_discuss_'+did+'_'+c_id+">"+"</div> </div>"
                })
                // 提交评论表单
                    comments+="<div class=\"dynamicForm\">\n" +
                    "    <form id=\"dynamicForm\" method=\"get\">\n" +
                    "        <input id='dynamicFormContext' type=\"text\" name=\"dynamicContext\">" +
                    "<input type=\"button\" value=\"发表评论\" onclick=\"addCommentByDyid("+did+")\">\n" +
                    "    </form>\n" +
                    "</div>"
                $(".dy_comment_"+did).html(comments);
            },
        });
    }
    function findDiscussByCid(cid,did) {
        console.log(did)
        $.ajax({
            type:"GET",
            url:"/discuss/findDiscussByCid?cid="+cid,
            dataType:"json",
            success:function (data) {
                var discuss = "<p>"+'讨论区：<br>'+"</p>";
                $.each(data,function (index,value) {
                    var d_id =  value.id;
                    var uid = value.uid;
                    var uname = value.uName;
                    var context = value.context;
                    discuss+= "<a href='/user/showUserInformation?id="+uid+"'>"+uname+":</a>"+"<span>"+context+"</span><br>";
                })
                //    添加讨论按钮
                //    点击按钮 出现输入框，输入完成提交
                discuss+="<form id=\"discussForm\" method=\"get\">\n" +
                    "        <input id='discussContext' type=\"text\" name=\"discussContext\">" +
                    "<input type=\"button\" value=\"我也说两句\" onclick=\"addDiscussByCid("+cid+")\">\n" +
                    "    </form>"
                $(".dy_comment_discuss_"+did+"_"+cid).html(discuss);
            },
        });
    }
    function addCommentByDyid(did) {
        var context =$("#dynamicFormContext").val();
        console.log(context)
        $.ajax({
            type:"GET",
            url:"/comment/addCommentByDyid?dynamicContext="+context+"&did="+did,
            dataType:"json",
            success:function (data) {
                console.log("返回数据："+data)
            }
        })

    }

    function addDiscussByCid(cid) {
        var context =$("#discussContext").val();
        console.log(context)
        $.ajax({
            type:"GET",
            url:"/discuss/addDiscussByCid?discussContext="+context+"&cid="+cid,
            dataType:"json",
            success:function (data) {
                console.log("返回数据："+data)
            }
        })
    }
    function findNextPage(pageNumber) {
        var page = pageNumber+1;
        window.location.href="/dy/showIndexDynamic?pageNumber="+page;
    }

</script>
<style>
    .dynamic{
        border:3px solid #F00

    }
    .dy_picture{

    }
</style>
<body>
    <h1>动态展示页面</h1>
    当前用户：<a href="/user/showUserInformation?id=${sessionScope.session_user_auth.id}">${sessionScope.session_user_auth.username}</a> | <a href="/user/goOut">退出登录</a>
    <a href="/dy/goUpload">发表动态</a>
    <a href="/user/showMyInfo">修改个人资料</a>
    <c:forEach var="dy" items="${dynamicPoList}">
        <div class="dynamic">
            <%--用户头像--%>
            <div class="dy_picture" >
                <img src="upload/${dy.picture.name}" alt="图片未加载" height="100" width="100">
            </div>
            <%--用户名--%>
            <div class="dy_uname">
                <p>来源于：${dy.uname}</p>
            </div>
            <%--动态内容--%>
            <div class="dy_context">
                <p>${dy.context}</p>
            </div>
            <%--点击查看图片（ids）--%>
            <c:if test="${dy.imageId != null && dy.imageId != ''}">
                <input type="button"
                       onclick="findPictureInIds('${dy.imageId}','${dy.id}')"
                       value="查看图片">
            </c:if>
            <div class="dy_pictures_${dy.id}">

            </div>
            <input id="showComment" type="button" onclick="findCommentByDid(${dy.id})" value="查看评论">
            <%--<input type="button" onclick="openInputComment(${dy.id})" value="添加评论">--%>
            <div class="dy_comment_${dy.id}">
                <%--评论--%>
            </div>
        </div>
    </c:forEach>
    <input type="button" value="加载更多" onclick="findNextPage(${pageNumber})">
    ${requestScope.msg}
    <a href="/dy/showIndexDynamic">返回首页</a>
</body>
</html>
