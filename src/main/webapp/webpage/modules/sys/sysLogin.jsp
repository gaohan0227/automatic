<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${ctxStatic}/common/css/new-login.css">
		<script src="${ctxStatic}/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
		<!-- SjjtPlus -->
		<link href="${ctxStatic}/common/jeeplus.css" type="text/css" rel="stylesheet" />
		<script src="${ctxStatic}/common/jeeplus.js" type="text/javascript"></script>
		<script type="text/javascript">
		if (window.top !== window.self) {
			window.top.location = window.location;
		}
		</script>
		<style type="text/css">
			.error_msg {
				position:absolute;
				text-align: center;
				color: red;
				top:217px;
				right: 102px;
			}
		</style>
	</head>
	<body>
		 <div class="header-warp">
	        <div class="header">
	            <img class="logo" src="${ctxStatic}/common/img/new-logo.png" alt="">
	            <h1>欢迎登陆</h1>
	    	</div>
	    </div>
		<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		   <div class="conter-warp">
		        <div class="login-carld">
						<%-- <sys:message content="${message}"/> --%>
						<div class="error_msg">${message}</div>
		                <h2>用户登陆</h2>
		                <input class="account" type="text" id="username" name="username" placeholder="登录帐号">
		                <input class="password" type="password" id="password" name="password"   placeholder="登陆密码">
		                <button type="submit" style="cursor: pointer;">登陆</button>
		                <!-- <div class="tles">
		                    <p><span class="xia"><span class="radio"><input type="checkbox" name="checkbox" value="1" style="cursor: pointer;"></span>下次自动登陆</span><span class="wang" style="cursor: pointer;">忘记密码?</span></p>
		                </div> -->
		            </div>
		   </div>
		</form>
	  <div class="footer">
	        <p></p>
	  </div>
	</body>
</html>
