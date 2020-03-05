<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>登陆页面</title>
    <link type="text/css" rel="stylesheet" href="/css/style.css" />
    <script type="text/javascript">
    </script>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h3>蓝闪练习系统</h3>
        </header>
        <section class="loginCont">
	        <form class="loginForm" action="/user/login"  method="POST" >
				<div class="info">${error }</div>
                <div class="inputbox">
                    <label>用户名：</label>
                    <input type="text" class="input-text" id="usercode" name="usercode" placeholder="请输入用户名" required/>
                </div>
                <div class="inputbox">
                    <label>密码：</label>
                    <input type="password" id="userpassword" name="userpassword" placeholder="请输入密码" required/>
                </div>
                <div class="subBtn">
                    <input type="submit" value="登录"/>
                    <input type="button" value="注册" onclick="location.href='/user/register'"/>
                    <%--<input type="reset" value="重置"/>--%>
                </div>	
			</form>
        </section>
    </section>
    <div class="footer818">
        <h6>版权所有：烟台蓝闪快跑工作室 未经许可不得复制</h6>
    </div>

</body>
<%--<script>--%>
    <%--//对应后台返回的提示--%>
    <%--if ('${status}' != '') {--%>
        <%--if ('${status}' == 0) {--%>
            <%--alert('登录成功,即将跳转至用户详情页！')--%>
            <%--location.href = '/user/userInfo'--%>
        <%--}else if ('${status}' == 1) {--%>
            <%--alert('该账户不存在！');--%>
        <%--}--%>
        <%--else if ('${status}' == 2) {--%>
            <%--alert('密码错误！')--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>

</html>
