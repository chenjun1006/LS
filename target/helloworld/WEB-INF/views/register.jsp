<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link type="text/css" rel="stylesheet" href="/css/style.css" />
    <script type="text/javascript">
    </script>
</head>
<body class="login_bg">
<section class="loginBox">
    <header class="loginHeader">
        <h1>注册</h1>
    </header>
    <section class="registerCont">
        <form class="registerForm" action="/user/register"  method="post" >
            <div class="info">${error }</div>
            <div class="inputbox">
                <label>用户名：</label>
                <input type="text" class="input-text" id="username" name="username" placeholder="请输入用户名" required/>
            </div>
            <div class="inputbox">
                <label>密  码：</label>
                <input type="password" id="userpassword" name="userpassword" placeholder="请输入密码" required/>
            </div>
            <div class="inputbox">
                <label>手机号：</label>
                <input type="phone" id="phone" name="phone" required/>
            </div>
            <div class="subBtn1">
                <input type="submit" value="注册"/>
            </div>
        </form>
    </section>
</section>
<div class="footer818">
    版权所有：烟台蓝闪快跑工作室 未经许可不得复制
</div>

</body>
<script>

</script>

</html>
