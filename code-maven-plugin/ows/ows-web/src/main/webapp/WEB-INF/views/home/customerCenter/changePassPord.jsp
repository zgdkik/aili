<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<link href="${base}/styles/cityLayout.css" type="text/css" rel="stylesheet" />
<c:import url="../../commons/common-script.jsp" />

<style>
  .gdt{
   overflow:auto;
   width:825px;margin-left: 25px; margin-top:10px;
  }
  .gdt td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;}
  .gdt th{height:35px; background-color:#FFCC00;color:#FFFFFF; text-align: center;border-right:#FFFFFF solid 2px;}
  .gdt table{border-collapse:collapse;}
  .edit_btn{border:#FFCC00 solid 1px; background:#FFFFFF; color:#999999; cursor:pointer;padding:2px;margin-left:2px}
  .edit_btn:hover{ background: #FFCC00;color:#FFFFFF}
</style>
</head>
<script type="text/javascript">
		//整體佈局展示
		$(function(){
			fn_get_menu("ddwl_khzx");//加载菜单
			$('#edit_div').css('top',($(window).height()-$('#edit_div').height())/2).css('left',($(window).width()-$('#edit_div').width())/2+150);
			$('#zhe_div').css('width',$(window).width()).css('height',$(window).height());
			$('#btn_cancel').click(function(){
				$('#edit_div').hide()
				$('#zhe_div').hide();	
			})
		});
		//保存修改的新密码
		function save_pwd(){
			var oldPassWord = $('#oldPassWord').val();
			var newPassWord = $('#newPassWord').val();
			var rePassWord = $('#rePassWord').val();
			$.ajax({
				type: "POST",
				  url: "${base}/customer/changePassword",
				  data:{
					    oldPassWord:oldPassWord,
						newPassWord:newPassWord,
						rePassWord:rePassWord,
				  },
				  dataType: "json",
				  success:function (map) {
					  alert(map.msg);
					 //无论填写个人信息是否成功都跳转到首页！

           			/* if(result.data.success){
           				$.messager.alert("友情提示",result.data.msg,"info");
         			}else{
         				$.messager.alert("友情提示",result.data.msg,"info");         				
         			} */
         		}
			});
		}
		//返回首页
		$("#changePassWordBack").on('click',function(){
	 		location.href="//localhost:8081/ddwlGw/personalData?title=%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99";
	 	})
	</script>

<body class="home-bg">

	<c:import url="../../commons/common-top.jsp" />
	<div class="clear"></div>
	<div class="container mg">
		<!--sidebar-->
		<c:import url="../../commons/childmenu.jsp" />
		
		<!--具体内容-->
		<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img  src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}</h4>
				<div class="chanpinfuwu">
					<div class="xiugaimima">
					<table>
						<tbody>
							<tr><td>原始密码：</td><td><input type="password" id="oldPassWord" class="form-control" placeholder="原始旧密码" maxlength="16"/></td></tr>
							<tr><td>重设密码：</td><td><input type="password" id="newPassWord" class="form-control" placeholder="新密码为6-16位字符" maxlength="16"/></td></tr>
							<tr><td>确认密码：</td><td><input type="password" id="rePassWord" class="form-control" placeholder="确认新密码" maxlength="16"/></td></tr>
							<tr><td></td><td><button class="btn" onclick="save_pwd()">保存修改</button> <button id="changePassWordBack" class="btn" onclick="javascript:history.back(-1)">返回</button></td></tr>
						</tbody>
					</table>
				</div>
				</div>
			</div>	
		</div>
		</div>
	
	<%-- <script type="text/javascript" src="${base}/scripts/city.js"></script> --%>
	<c:import url="../../commons/common-footer.jsp" />
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-修改密码
</script>	
</html>
