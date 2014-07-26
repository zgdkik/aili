<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<jsp:include page="common.jsp"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>买客网-微主题,微话题-无尽分享-专为买家分享的网站</title>
<link href="${styles}/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/login.js"></script>
</head>
<body class="B_login">
	<div class="w_login">
		<div class="w_l_top">
			<div class="w_top"></div>
		</div>
		<div class="w_login_main">

			<div class="w_l_context">
				<div class="w_l_c_top"></div>
			</div>
			<div class="w_l_info">
				<div class="w_l_i_left">
					<div class="left_top">
						还没有买客网账号？现在加入 <a
							href="${base}user/register.htm"
							class="left_top_btn">立即注册</a>
					</div>
					<div class="left_next">
						<img class="w_img" src="${images}/w_left.png" />
					</div>
				</div>
				<div class="right_top">
					<div class="right_form">

						<div class="form_list">
							<div class="f_email">
								<input type="text" id="emailId" name="userEmail" class="w_input"
									value="1000@qq.com" />
							</div>
						</div>
						<div class="form_list">
							<div class="f_pwd">
								<input type="password" id="pwdId" name="userPwd" class="w_input"
									value="as135246" />
							</div>
						</div>
						<div class="form_list">
							<div class="f_help">
								<div class="f_right">
									<a href="http://localhost:8080/blog/jsp/backpwd.jsp"
										class="f_reght_btn">忘记密码</a>
								</div>
								<input type="checkbox" checked="checked" />下次自动登录 <a
									class="help_img" href="#"></a>
							</div>
						</div>
						<div class="form_list">
							<a href="javascript:void(0)" class="f_btn"><span
								class="f_btn_tip">登录</span></a> <span class="f_no"> 还没有微博？<a
								class="f_no_tip"
								href="${base}user/register.htm">立即注册</a>
							</span>
						</div>

					</div>
					<div class="login_other">
						<div class="other">
							<p class="other_p">使用其他账号登录</p>
							<ul>
								<li><a href="#" class="tiannyi"><span class="t_y"></span>QQ</a></li>
								<li><a href="#" class="liantong"><span class="l_t"></span>新浪微博</a></li>
								<li><a href="#" class="360"><span class="s_l_l"></span>腾讯微博</a></li>
							</ul>
						</div>
					</div>
				</div>

			</div>
			<jsp:include page="footer.jsp"/>
		</div>
		<div class="layer" id="notExistId"
			style="margin-left: -170px; top: 277px; left: 855px; width: 210px; display: none">
			<div class="bg">
				<div class="content">
					<a class="W_ico12 icon_close" href="javascript:void(0)"></a>
					<p class="tips">
						<span class="icon_delS"></span>
						该账号不存在&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <a
							href="http://localhost:8080/blog/jsp/register.jsp">立即注册</a>
					</p>
				</div>
				<div class="arrow arrow_tips"></div>
			</div>
		</div>
		<div class="layer" id="nameOrPwdId"
			style="margin-left: -170px; width: 215px; left: 855px; top: 277px; display: none"
			node-type="outer">
			<div class="bg">
				<div class="content" node-type="inner">
					<a class="W_ico12 icon_close" onclick="return false;"
						node-type="close" action-type="common_layer_errtip_close"
						href="javascript:void(0)"></a>
					<p class="tips" node-type="msg">
						<span class="icon_delS"></span>
						用户名或密码错误&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <a target="_blank"
							href="http://help.weibo.com/faq/q/85/12606#12606">查看帮助</a>
					</p>
				</div>
				<div class="arrow arrow_tips" node-type="arrow" style="left: 86px;"></div>
			</div>
		</div>
	</div>
</body>
</html>
