<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>LarryCMS后台登录</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<!-- load css -->
	<link rel="stylesheet" type="text/css" href="assets/common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="assets/css/login.css" media="all">
</head>
<body>
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">
	<h1>
		 <strong>温控智能生产系统—智能测试子系统</strong>
		 <em>
		 	<#if msg?exists>
		 	${msg}
		 	<#else>
		 	</#if>
		 </em>
	</h1>
	<form action="loginCheck" method="post">
	<div class="layui-user-icon larry-login">
		 <input id="username" name="username" type="text" placeholder="账号" class="login_txtbx"/>
	</div>
	<div class="layui-pwd-icon larry-login">
		 <input id="password" name="password" type="password" placeholder="密码" class="login_txtbx"/>
	</div>
    <div class="layui-val-icon larry-login">
    	<div class="layui-code-box">
    		<input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">
            <img src="http://localhost:8080/one/code" alt="" class="verifyImg" id="imgObj" />
    	</div>
    </div>
    <div class="layui-submit larry-login">
    	<input type="submit" value="立即登陆" class="submit_btn"/>
    </div>
    </fotm>
    <div class="layui-login-text">
    	<p>© 2017-2018 test 版权所有</p>
        <p>test</p>
    </div>
</div>
<script type="text/javascript" src="assets/common/layui/lay/dest/layui.all.js"></script>
<script type="text/javascript" src="assets/js/login.js"></script>
<script type="text/javascript" src="assets/jsplug/jparticle.jquery.js"></script>
<script type="text/javascript">

$(document).ready(function () {
		
	

    function changeImg() {        
        var imgSrc = $("#imgObj");    
        var src = imgSrc.attr("src");        
        imgSrc.attr("src", chgUrl(src));
    }
    
    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
    $("#imgObj").click(changeImg);
});
</script>
</body>
</html>