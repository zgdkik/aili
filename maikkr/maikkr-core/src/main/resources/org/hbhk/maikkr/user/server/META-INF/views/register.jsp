<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-用户注册</title>
<link href="${scripts}/reg/styles.css" type="text/css" rel="stylesheet" />
<script src="${scripts}/reg/data.js"></script>
<script type="text/javascript">
function refreshimg(){
    document.getElementById("virfyCode").src="${base}core/getRandcode.htm?code="+Math.random();
    return true;
  }
</script>
</head>
<body>
	<div id="base" class="">

		<!-- Unnamed (Shape) -->
		<div id="u0" class="ax_shape">
			<img id="u0_img" class="img " src="${images}/reg/u0.png" />
			<!-- Unnamed () -->
			<div id="u1" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u2" class="ax_shape">
			<img id="u2_img" class="img " src="${images}/reg/u2.png" />
			<!-- Unnamed () -->
			<div id="u3" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u4" class="ax_paragraph">
			<img id="u4_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u5" class="text">
				<p>
					<span>用户名：</span>
				</p>
			</div>
		</div>

		<!-- userName (Text Field) -->
		<div id="u6" class="ax_text_field" data-label="userName">
			<input id="u6_input" class="username required" type="text" value="" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u7" class="ax_paragraph">
			<img id="u7_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u8" class="text">
				<p>
					<span>邮箱</span><span>：</span>
				</p>
			</div>
		</div>

		<!-- userEmail (Text Field) -->
		<div id="u9" class="ax_text_field" data-label="userEmail">
			<input id="u9_input" class="email required" type="text" value="" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u10" class="ax_paragraph">
			<img id="u10_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u11" class="text">
				<p>
					<span>密码：</span>
				</p>
			</div>
		</div>

		<!-- userPwd (Text Field) -->
		<div id="u12" class="ax_text_field" data-label="userPwd">
			<input id="u12_input" class="pwd1 required" type="password" value="" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u13" class="ax_paragraph">
			<img id="u13_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u14" class="text">
				<p>
					<span>确认</span><span>密码：</span>
				</p>
			</div>
		</div>

		<!-- pwdConfirm (Text Field) -->
		<div id="u15" class="ax_text_field" data-label="pwdConfirm">
			<input id="u15_input" class="pwd2 required" type="password" value="" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u16" class="ax_paragraph">
			<img id="u16_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u17" class="text">
				<p>
					<span>验证</span><span>码：</span>
				</p>
			</div>
		</div>

		<!-- checkCode (Text Field) -->
		<div id="u18" class="ax_text_field" data-label="checkCode">
			<input id="u18_input" type="text" class="code required" value="" />
			<a class="code" href="javascript:void(0);" style="margin-left: 190px;">
				<img  style="height: 30px" id="virfyCode" onclick="javascript:refreshimg()" src="${base}core/getRandcode.htm"/>
			</a>
		</div>

		<!-- Unnamed (Checkbox) -->
	   <!-- <div id="u19" class="ax_checkbox">
			<label for="u19_input"> Unnamed ()
				<div id="u20" class="text">
					<p>
						<span>我接受注册协议</span>
					</p>
				</div>
			</label> <input id="u19_input" type="checkbox" value="checkbox" />
		</div> -->

		<!-- Unnamed (Shape) -->
		<div id="u21" class="ax_paragraph">
			<img id="u21_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u22" class="text">
				<p>
					<span style="text-decoration: underline;">
					<a href="${base}user/loginpage.htm" style="text-decoration: none;">登录</a></span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u23" class="ax_paragraph">
			<img id="u23_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u24" class="text">
				<p>
					<span>我已注册</span><span>，</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Dynamic Panel) -->
		<div id="u25" class="ax_dynamic_panel reg">
			<div id="u25_state0" class="panel_state" data-label="State1">
				<div id="u25_state0_content" class="panel_state_content">

					<!-- Unnamed (Shape) -->
					<div id="u26" class="ax_shape">
						<img id="u26_img" class="img " src="${images}/reg/u26.png" />
						<!-- Unnamed () -->
						<div id="u27" class="text">
							<p>
								<span>&nbsp;</span>
							</p>
						</div>
					</div>

					<!-- Unnamed (Shape) -->
					<div id="u28" class="ax_paragraph">
						<img id="u28_img" class="img "
							src="${images}/transparent.gif" />
						<!-- Unnamed () -->
						<div id="u29" class="text">
							<p>
								<span>注册</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u30" class="ax_shape">
			<img id="u30_img" class="img " src="${images}/home__nologin_/u314.png" />
			<!-- Unnamed () -->
			<div id="u31" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u32" class="ax_shape">
			<img id="u32_img" class="img " src="${images}/reg/u32.png" />
			<!-- Unnamed () -->
			<div id="u33" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u34" class="ax_paragraph">
			<img id="u34_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u35" class="text">
				<p>
					<span>Copyright</span><span> 2014</span><span>&nbsp;&nbsp;
						米客网</span><span>&nbsp;</span><span>&nbsp;</span><span>
						沪ICP备14038776号</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u36" class="ax_shape">
			<img id="u36_img" class="img " src="${images}/home__nologin_/u4.png" />
			<!-- Unnamed () -->
			<div id="u37" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u38" class="ax_image">
			<img id="u38_img" class="img " src="${images}/home__nologin_/u318.jpg" />
			<!-- Unnamed () -->
			<div id="u39" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u40" class="ax_image">
			<img id="u40_img" class="img " src="${images}/home__nologin_/u320.jpg" />
			<!-- Unnamed () -->
			<div id="u41" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u42" class="ax_image">
			<img id="u42_img" class="img " src="${images}/home__nologin_/u322.jpg" />
			<!-- Unnamed () -->
			<div id="u43" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u44" class="ax_h1">
			<img id="u44_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u45" class="text">
				<p>
					<span>汽车主题分享社区</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Horizontal Line) -->
		<div id="u46" class="ax_horizontal_line">
			<img id="u46_start" class="img "
				src="${images}/transparent.gif" alt="u46_start" /> <img
				id="u46_end" class="img " src="${images}/transparent.gif"
				alt="u46_end" /> <img id="u46_line" class="img "
				src="${images}/reg/u46_line.png" alt="u46_line" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u47" class="ax_shape">
			<img id="u47_img" class="img " src="${images}/reg/u47.png" />
			<!-- Unnamed () -->
			<div id="u48" class="text">
				<p>
					<span>欢迎</span><span>注册</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u49" class="ax_shape">
			<img id="u49_img" class="img " src="${images}/reg/u49.png" />
			<!-- Unnamed () -->
			<div id="u50" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Menu) -->
		<div id="u51" class="ax_menu">
			<img id="u51_menu" class="img "
				src="${images}/home__nologin_/u22_menu.png" alt="u51_menu" />

			<!-- Unnamed (Table) -->
			<div id="u52" class="ax_table">

				<!-- Unnamed (Menu Item) -->
				<div id="u53" class="ax_table_cell">
					<img id="u53_img" class="img " src="${images}/home__nologin_/u24.png" />
					<!-- Unnamed () -->
					<div id="u54" class="text home">
						<p>
							<a href="${base}" style="text-decoration: none;"><span>首页</span></a>
						</p>
					</div>
				</div>

				<!-- Unnamed (Menu Item) -->
				<div id="u55" class="ax_table_cell">
					<img id="u55_img" class="img " src="${images}/home__nologin_/u24.png" />
					<!-- Unnamed () -->
					<div id="u56" class="text jhs">
						<p>
							<a href="${base}user/jyh.htm" style="text-decoration: none;"><span>聚优惠</span></a>
						</p>
					</div>
				</div>

				<!-- Unnamed (Menu Item) -->
				<div id="u57" class="ax_table_cell">
					<img id="u57_img" class="img " src="${images}/home__nologin_/u24.png" />
					<!-- Unnamed () -->
					<div id="u58" class="text about">
						<p>
							<a href="${base}user/aboutus.htm" style="text-decoration: none;"><span>关于我们</span></a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
