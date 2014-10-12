<%@ page language="java" pageEncoding="UTF-8" info="买客网"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<jsp:include page="common.jsp"/>
	<title>${siteInfo.title}-注册</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link href="${styles}/register.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${scripts}/register.js"></script>
       <script type="text/javascript">
           //alert("验证码刷新！");
        function refreshimg(){
          document.getElementById("virfyCode").src="${base}security/validateCode.htm?code="+Math.random();
          return true;
        }
      </script>
</head>
<body class="B_register" >
		<div class="w_header_line"></div>
		<div class="w_register">
			<div class="w_main">
				<div class="w_m_top">
					<div class="w_m_t_name"></div>
				</div>
				<div class="w_m_main">
					<div class="w_m_m_one">
						<a class="one_register" style="text-decoration: none;" href="javascript:void(0);">个人注册</a>
					</div>
					<div class="w_m_m_two">
						<div class="w_m_m_t_left">
							<div class="left_list">
								<div class="left_tit">
									<span class="left_tit_e"></span><i>*</i>邮箱:
								</div>
								<div class="left_inp">
									<input type="text" autocomplete="off" name="userEmail" class="w_input" placeholder="请输入您的常用邮箱" 
										id="emailId" />
								</div>
								<div class="left_tips">
									<p class="notice"  id="emailNotice" style="display:none">
										<span class="noticeImg" ></span><font color="#808080" size="-1px">您填写的邮箱将作为买客买家网登陆名</font>
										</p>
									<p class="notice" id="emailError" style="display:none">
										<span  class="errorImg" ></span><font color="#E64141" size="-1px">请输入正确的邮箱地址</font>
									</p>
									<p class="notice" id="emailTrue" style="display:none">
										<span class="trueImg" ></span>
									</p>
									<p class="notice" id="emailLogin" style="display:none">
										<span class="noticeImg" ></span><font color="#E64141" size="-1px">该邮箱已被注册，请</font><font color="#0a8cd2" size="-1px"><a href="${base}user/loginpage.htm">直接登录</a></font>
									</p>
								</div>
							</div>
							<div class="left_list">
								<div class="left_tit">
									<span class="left_tit_p"></span><i>*</i>设置密码:
								</div>
								<div class="left_inp">
									<input type="password" autocomplete="off" name="userPwd" class="w_input"
										id="pwdId" />
								</div>
								<div class="left_tips">
									<p class="notice"  id="pwdNotice"  style="display:none">
										<span class="noticeImg"></span><font color="#808080" size="-1px">请输入6-16位数字、字母或常用符号，字母区分大小写</font>
									</p>
									<p class="notice" id="pwdError" style="display:none">
										<span class="errorImg"></span><font color="#E64141" size="-1px">请输入6-16位数字、字母或常用符号，字母区分大小写</font>
									</p>
									<p class="notice" id="pwdMedium" style="display:none">
										<font color="#0A8CD2" size="-1px">中</font>:<font color="#808080" size="-1px">您的密码可以更复杂些</font>
									</p>
									<p class="notice" id="pwdGood"  style="display:none">
										<font color="#4BB900" size="-1px">强</font>:<font color="#808080" size="-1pxs">您的密码很安全</font>
									</p>
									
								</div>
							</div>
							<div class="left_list">
								<div class="left_tit">
									<span class="left_tit_n"></span><i>*</i>昵称:
								</div>
								<div class="left_inp">
									<input type="text" autocomplete="off" name="userName" class="w_input"
										id="nameId" />
								</div>
								<div class="left_tips">
									<p class="notice" id="nameNotice" style="display:none;">
										<span class="noticeImg"></span><font color="#808080" size="-1px">请输入2-24位字符：支持中文、英文、数字、—、_</font>
									</p>
									<p class="notice" id="nameError" style="display:none;">
										<span class="errorImg"></span><font color="#E64141" size="-1px">请输入2-24位字符：支持中文、英文、数字、—、_</font>
									</p>
									<p class="notice" id="nameNull" style="display:none;">
										<span class="errorImg" ></span><font color="#E64141" size="-1px">请输入昵称</font>
									</p>
									<p class="notice" id="nameExist" style="display:none;">
										<span class="noticeImg"></span><font color="#0a8cd2" size="-1px">此昵称已被注册</font>
									</p>
									<p class="notice" id="nameTrue"  style="display:none;">
										<span class="trueImg" ></span>
									</p>
									
								</div>

							</div>
							<div class="left_list virfy_bar">
								<div class="left_tit">
									<span class="left_tit_v"></span><i>*</i>验证码:
								</div>
								<div class="left_inp">
									<input type="text" autocomplete="off" maxlength="4" name="virfy"
										class="w_input virfy_input" id="virfyId" />
									<a class="code" href="javascript:void(0);">
										<img alt="" id="virfyCode" onclick="javascript:refreshimg()" src="${base}security/validateCode.htm"/>
									</a>
									<a class="verify_refresh" title="看不清，换一张" onclick="javascript:refreshimg()" style="cursor: hand"  href="javascript:void(0)"></a>
								</div>
								<div class="left_tips">
									<p class="notice" id="virfyNotice" style="display:none">
										<span class="noticeImg"></span><font color="#808080" size="-1px">请输入验证码</font>
									</p>
									<p class="notice" id="virfyNull" style="display:none">
										<span class="errorImg"></span><font color="#E64141" size="-1px">验证码不能为空</font>
									</p>
									<p class="notice" id="virfyTrue" style="display:none">
									    <span></span>
									</p>
                                    <p class="notice" id="virfyError" style="display:none">
										<span class="errorImg"></span><font color="#E64141" size="-1px">验证码错误</font>
									</p>
								</div>
							</div>
							<div class="left_list">
							    <div class="info-submit">
									<div class="left_inp">
										<a type="submit" class="W_btn_big"
											href="javascript:void(0)" refake-type="submit" onclick="click_register();"
											node-type="btn_submit"><span>立即注册</span>
										</a>
									</div>
							    </div>
							</div>
							<div class="left_list">
								<!-- 
									<div class="inp verify">
										<p class="agreement">
											<a href="/signup/v5/protocol" target="_blank"></a>
										</p>
										<p class="agreement">
											<a href="/signup/v5/privacy" target="_blank"></a>
										</p>
										<p class="agreement">
											<a
												href="http://news.sina.com.cn/c/2012-12-29/051425921660.shtml"
												target="_blank"></a>
										</p>
									</div> -->
							</div>
						</div>
					</div>
				</div>
				<jsp:include page="footer.jsp"/>
			</div>

		</div>
	</body>

</html>
