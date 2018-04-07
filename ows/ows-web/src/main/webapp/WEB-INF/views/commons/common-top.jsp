<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<style>
.nav1 >div >ul > li > a:hover {
    background: #fff;
    color: #000000;
    border-top: 3px #FBD860 solid;
}
</style>
<div class="container-fluid"
	style="bottom: 0; width: 100%; width: 100%; font-size: 15px; color: #919191; line-height: 35px; height: 35px; border-bottom: 1px solid #dddddd;">
	<div class="container">
		<a style="font-weight: bold; color: #000000; margin-left: 30px">您好，欢迎访问</a>
		<div style="float: right; font-weight: bold; color: #FD2B39">
			<a style="">加入收藏 |</a> <a style="">代孕流程 |</a> <a style="">联系我们</a>
		</div>
	</div>
</div>
<div class="container">
	<div class="header-top">
		<a id="logoImg" class="left"><img src="${base}/images/logo.png" /></a>
		<div class="right"></div>
	</div>
</div>
<!--导航条开始-->
<div class="container-fluid nav1"
	style="background: #ceaa30 url(${base}/images/navbg.png) no-repeat center; ">
	<div class="container"
		style="height: 52px; line-height: 52px;">
		<ul id="ul_hmenu">
		</ul>
	</div>
</div>
<script>

function logout(){
	$.ajax({  
		url:base+"/register/logout",
		type:"post",
		dataType:"json",
		success:function(data){ 
			if(data.success){
				location.reload();
			}  
		}	
	});
}
</script>