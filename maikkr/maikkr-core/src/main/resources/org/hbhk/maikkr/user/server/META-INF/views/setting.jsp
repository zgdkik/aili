<%@ page language="java" pageEncoding="UTF-8" info="米客网"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="common.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${siteInfo.title}-我的信息</title>
<link href="${base}uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${base}uploadify/jquery.uploadify.js"></script>
<link href="${scripts}/myaccount/styles.css" type="text/css"
	rel="stylesheet" />
<script src="${scripts}/myaccount/data.js"></script>
<script type="text/javascript">
var seesionid = "${pageContext.session.id}";
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
			<img id="u2_img" class="img " src="${images}/mytheme/u4.png" />
			<!-- Unnamed () -->
			<div id="u3" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u4" class="ax_shape">
			<img id="u4_img" class="img " src="${images}/reg/u49.png" />
			<!-- Unnamed () -->
			<div id="u5" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u6" class="ax_shape">
			<img id="u6_img" class="img " src="${images}/myaccount/u6.png" />
			<!-- Unnamed () -->
			<div id="u7" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Menu) -->
		<div id="u8" class="ax_menu">
			<img id="u8_menu" class="img "
				src="${images}/home__nologin_/u22_menu.png" alt="u8_menu" />

			<!-- Unnamed (Table) -->
			<div id="u9" class="ax_table">

				<!-- Unnamed (Menu Item) -->
				<div id="u10" class="ax_table_cell">
					<img id="u10_img" class="img "
						src="${images}/home__nologin_/u24.png" />
					<!-- Unnamed () -->
					<div id="u11" class="text home">
						<p>
							<span>首页</span>
						</p>
					</div>
				</div>

				<!-- Unnamed (Menu Item) -->
				<div id="u12" class="ax_table_cell">
					<img id="u12_img" class="img "
						src="${images}/home__nologin_/u24.png" />
					<!-- Unnamed () -->
					<div id="u13" class="text jhs">
						<p>
							<span>聚优惠</span>
						</p>
					</div>
				</div>

				<!-- Unnamed (Menu Item) -->
				<div id="u14" class="ax_table_cell">
					<img id="u14_img" class="img "
						src="${images}/home__nologin_/u24.png" />
					<!-- Unnamed () -->
					<div id="u15" class="text about">
						<p>
							<span>关于我们</span>
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- Unnamed (Menu) -->
		<div id="u16" class="ax_menu">
			<img id="u16_menu" class="img " src="${images}/mytheme/u320_menu.png"
				alt="u16_menu" />

			<!-- Unnamed (Table) -->
			<div id="u17" class="ax_table">

				<!-- Unnamed (Menu Item) -->
				<div id="u18" class="ax_table_cell">
					<img id="u18_img" class="img "
						src="${images}/home__nologin_/u18.png" />
					<!-- Unnamed () -->
					<div id="u19" class="text">
						<p>
							<span style="text-decoration: underline;">${cuser}</span>
						</p>
					</div>
				</div>

				<!-- Unnamed (Menu Item) -->
				<div id="u20" class="ax_table_cell">
					<img id="u20_img" class="img "
						src="${images}/home__nologin_/u18.png" />
					<!-- Unnamed () -->
					<div id="u21" class="text">
						<p>
							<c:if test="${cuser != null}">
								<a href="${base}user/set.htm" style="text-decoration: none;"><span
									style="text-decoration: underline;">帐号设置</span></a>
							</c:if>
							<c:if test="${cuser == null}">
								<a href="${base}user/register.htm"
									style="text-decoration: none;"><span
									style="text-decoration: underline;">注册</span></a>
							</c:if>
						</p>
					</div>
				</div>

				<!-- Unnamed (Menu Item) -->
				<div id="u22" class="ax_table_cell">
					<img id="u22_img" class="img "
						src="${images}/home__nologin_/u18.png" />
					<!-- Unnamed () -->
					<div id="u23" class="text">
						<p>
							<c:if test="${cuser != null}">
								<a href="${base}security/logout.htm">退出</a>
							</c:if>
							<c:if test="${cuser == null}">
								<a href="${base}user/loginpage.htm">登陆</a>
							</c:if>
						</p>
					</div>
				</div>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u24" class="ax_shape">
			<img id="u24_img" class="img "
				src="${images}/home__nologin_/u314.png" />
			<!-- Unnamed () -->
			<div id="u25" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u26" class="ax_image">
			<img id="u26_img" class="img "
				src="${images}/home__nologin_/u318.jpg" />
			<!-- Unnamed () -->
			<div id="u27" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u28" class="ax_image">
			<img id="u28_img" class="img "
				src="${images}/home__nologin_/u320.jpg" />
			<!-- Unnamed () -->
			<div id="u29" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u30" class="ax_image">
			<img id="u30_img" class="img "
				src="${images}/home__nologin_/u322.jpg" />
			<!-- Unnamed () -->
			<div id="u31" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u32" class="ax_h1">
			<img id="u32_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u33" class="text">
				<p>
					<span>汽车主题分享社区</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u34" class="ax_shape">
			<img id="u34_img" class="img " src="${images}/myaccount/u34.png" />
			<!-- Unnamed () -->
			<div id="u35" class="text">
				<p>
					<span>帐号设置</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Horizontal Line) -->
		<div id="u36" class="ax_horizontal_line">
			<img id="u36_start" class="img " src="${images}/transparent.gif"
				alt="u36_start" /> <img id="u36_end" class="img "
				src="${images}/transparent.gif" alt="u36_end" /> <img id="u36_line"
				class="img " src="${images}/myaccount/u36_line.png" alt="u36_line" />
		</div>

		<!-- paragraph (Shape) -->
		<div id="u37" class="ax_paragraph" data-label="paragraph">
			<img id="u37_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u38" class="text">
				<p>
					<span>我的信息</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Horizontal Line) -->
		<div id="u39" class="ax_horizontal_line">
			<img id="u39_start" class="img " src="${images}/transparent.gif"
				alt="u39_start" /> <img id="u39_end" class="img "
				src="${images}/transparent.gif" alt="u39_end" /> <img id="u39_line"
				class="img " src="${images}/home__nologin_/u66_line.png"
				alt="u39_line" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u40" class="ax_shape">
			<img id="u40_img" class="img " src="${images}/myaccount/u40.png" />
			<!-- Unnamed () -->
			<div id="u41" class="text">
				<p>
					<span>邮箱</span> <input class="email required" type="text"
						style="margin-left: 50px; width: 200px; height: 30px;"
						value="${uc.name}">
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u42" class="ax_shape">
			<img id="u42_img" class="img " src="${images}/myaccount/u40.png" />
			<!-- Unnamed () -->
			<div id="u43" class="text">
				<p>
					<span>昵称</span> <input class="nickname required" type="text" value="${uc.nickName}"
						style="margin-left: 50px; width: 200px; height: 30px;">
				</p>
			</div>
		</div>

		<!-- paragraph (Shape) -->
		<div id="u44" class="ax_paragraph" data-label="paragraph">
			<img id="u44_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u45" class="text">
				<p>
					<span>我的</span><span>头像</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Horizontal Line) -->
		<div id="u46" class="ax_horizontal_line">
			<img id="u46_start" class="img " src="${images}/transparent.gif"
				alt="u46_start" /> <img id="u46_end" class="img "
				src="${images}/transparent.gif" alt="u46_end" /> <img id="u46_line"
				class="img " src="${images}/home__nologin_/u66_line.png"
				alt="u46_line" />
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u47" class="ax_shape">
			<img id="u47_img" class="img " src="${file_server}${userInfo.userHeadImg}" />
			<!-- Unnamed () -->
			<div id="u48" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- paragraph (Shape) -->
		<div id="u49" class="ax_paragraph" data-label="paragraph">
			<img id="u49_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u50" class="text">
				<p>
					<span>我的</span><span>头像</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Image) -->
		<div id="u51" class="ax_image">
			<img id="u51_img" class="img " src="${images}/mytheme/u316.png" />
			<!-- Unnamed () -->
			<div id="u52" class="text">
				<p>
					<span>&nbsp;</span>
				</p>
			</div>
		</div>

		<!-- paragraph (Shape) -->
		<div id="u53" class="ax_paragraph" data-label="paragraph">
			<img id="u53_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u54" class="text">
				<p>
					<span>上传</span><span>头像</span>
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u55" class="ax_shape">
			<img id="u55_img" class="img " src="${images}/myaccount/u40.png" />
			<!-- Unnamed () -->
			<div id="u56" class="text">
				<p>
					<span>所在城市</span> 
					<select 
						style="margin-left: 20px; width: 200px; height: 30px;" name="dept"
						id="u99_input" class="area">
						<c:forEach items="${ps}" var="p">
							<option value="${p.id}">${p.name}</option>
						</c:forEach>
					</select>
				</p>
			</div>
		</div>

		<!-- paragraph (Shape) -->
		<div id="u61" class="ax_paragraph edit" data-label="paragraph">
			<img id="u61_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u62" class="text">
				<p>
					<input type="button" value="修改">
				</p>
			</div>
		</div>

		<!-- Unnamed (Shape) -->
		<div id="u63" class="ax_paragraph">
			<img id="u63_img" class="img " src="${images}/transparent.gif" />
			<!-- Unnamed () -->
			<div id="u64" class="text">
				<p>
					<span>Copyright</span><span> 2014</span><span>&nbsp;&nbsp;
						米客网</span><span>&nbsp;</span><span>&nbsp;</span><span>
						沪ICP备14038776号</span>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
