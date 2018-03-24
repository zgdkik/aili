<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
</head>
<body style="width: 100%;" >
		<c:import url="../../commons/common-top.jsp" />
		<img alt="top" title="返回顶部" src="${base}/images/top.png"
			style="position: fixed; right: 10px; bottom: 100px; cursor: pointer;"
			onclick="scroll(0,0)" />
		<div class="clear"></div>
		<div class="container mg">
			<!--sidebar-->
			<c:import url="../../commons/childmenu.jsp" />
			<!--具体内容-->
			<!--具体内容-->
		<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/ban16.jpg" alt=""/> --%></div>

			<div class="contenr-right-bottom ub">
				<h4 style="width:100%"><font id="h_title"></font><a href="javascript:history.back();" style="float:right">返回列表&nbsp;&nbsp;</a></h4>
				<div class="rencaizhaopin">
					<div class="icon" id="recruitTitle" style=""></div>
					<div class="text">
						<span id="recruitArea">工作地点：</span>
						<span id="recruitCategory">职业类别：</span>
						<span id="recruitCount" >招聘人数：</span>
						<div class="clear"></div>
						<p id="recruitStart">有效期：</p>
					</div>
					<div class="yaoqiu">
						<h4>职位描述：</h4>
						<p id="recruitDesc"></p>
					</div>
					<div class="yaoqiu">
						<h4>任职要求：</h4>
						<p id="recruitRequirement"></p>
					</div>
					<div class="yaoqiu">
						<h4>福利待遇：</h4>
						<p id="recruitWelfare"></p>
					</div>
					<div class="yaoqiu">
						<h4>联系方式：</h4>
						<p id="recruitContact">></p>
					</div>
				</div>
			</div>	
		</div>
	</div>
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript">
    $(function(){
    	fn_get_menu("ddwl_ddzp");
    	var page=1;
        var rows=10;
        var id='${id}';
        $.ajax({  
			url:base+"/recruit/getRecruitById",
			async :false,
			data:{
				recruitId:id
			},
			dataType:"json",
			success:function(arr){
				
				var data=arr.data;
				$("#recruitDesc").html(data.recruitJob);
		    	$("#recruitTitle").html(data.recruitTitle);
		    	$("#recruitArea").append(data.recruitArea);
		    	$("#recruitCategory").append(data.recruitCategory);
		    	$("#recruitCount").append(data.recruitCount);
		    	$("#recruitStart").append("从 "+ym.formatDate(data.recruitBegindate)+" 至  "+ym.formatDate(data.recruitEnddate)+"&nbsp;&nbsp;&nbsp;简历投递邮箱："+data.recruitMail);
		    	$("#recruitRequirement").html(data.recruitRequirement);
		    	$("#recruitWelfare").html(data.recruitWelfare);
		    	$("#recruitContact").html(data.recruitContact);
		    	$("input[name='recruitTitle']").val(data.recruitTitle);
		    	$("input[name='receiveMail']").val(data.recruitMail);
		    	$("#recruitMail").append(data.recruitMail);
			}            
		});
 
    	
    })
</script>
	
</body>
<script type="text/javascript">
	var html5=loadNextBanner(6); //大道招聘 -招聘信息
</script>
</html>
