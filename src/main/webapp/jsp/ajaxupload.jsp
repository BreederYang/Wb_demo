<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- js目录需要在springmvc.xml 中做静态文件映射否则无法读取，映射后的路径为restjs -->
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>

</head>
<script>
function upload(obj){
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
			$("#preimage").html("<img src='upload/"+dataArr[0]+"' width='200' height='200'/>");
		//	把data 解析 把ID放入 imageId 中  用于 form 提交
            $("#imageId").val($("#imageId").val()+','+dataArr[1]);
            console.log(dataArr[1])
		},
		error : function(data, status, e) {
			alert("上传失败，请重新选择");
		}
	});
}

function textCount() {
    $('#dy_context').keyup(function() {
        var val=$('#dy_context').val();
        var len=val.length;
        // var len=this.value.length
        $('#text-count').text(len);

    })
}
function commitForm() {
    //动态内容
    var context = $("#dy_context").val()
    //图片ID(去除首位 ，字符)
    var ids = $("#imageId").val().substr(1);
    $.ajax({
        type:"GET",
        url:"/dy/createDynamic?context="+context+"&imageId="+ids,
        dataType:"json",
        success:function (data) {
            if (data>0){
                window.location.href="/dy/showIndexDynamic"
            }
        },
    });
}
</script>
<body>
<form name="theform" action="/dy/ajaxupload" method="post" enctype="multipart/form-data">
    <p>内容：</p>
    <textarea cols="40" rows="5" id="dy_context"
              name="dy_context" maxlength="140" value=""
              onclick="textCount()"
              placeholder="最多可输入140字">
        </textarea>
        <span id="text-count" value="">0</span>/140<br>

    照片:<input type="file" name="theimage" id="theimage" onchange="upload(this)"/><br>
         <input type="hidden" id="imageId" name="imageId">
        <div id="preimage"></div>
        <input type="button" onclick="commitForm()" value="提交 ">
</form>
</body>
</html>