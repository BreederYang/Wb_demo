<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎注册</title>
</head>
<base href="<%=basePath%>">

<body>

       <div id="add">
           <el-form :inline="true" :model="formInline" class="demo-form-inline">
               <el-form-item label="邮箱">
                   <el-input v-model="formInline.email" placeholder="email@**.com"></el-input>
               </el-form-item>
               <el-form-item label="密码">
                   <el-input type="password" v-model="formInline.password" placeholder="18 - 99"></el-input>
               </el-form-item>
               <el-form-item>
                   <el-button type="primary" @click="onSubmit">添加</el-button>
               </el-form-item>
           </el-form>

       </div>

</body>

<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<!-- axios ajax 请求库  -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    new Vue ({
        el:'#add',
        data:function() {
            return {
                formInline: {
                    email: '',
                    password:''
                }
            }
        },
        methods: {
            onSubmit() {
                axios.get("/user/register",
                    {
                        params: {
                            email:this.formInline.email,
                            password:this.formInline.password
                        }
                    })
                    .then(function (response) {
                            window.location.href='/dy/showIndexDynamic'
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
                }
            }
    })
</script>
</html>
