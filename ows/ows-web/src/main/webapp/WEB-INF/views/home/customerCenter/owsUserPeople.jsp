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
		$(function(){
			fn_get_person();
			fn_get_menu("ddwl_khzx");
			$('#edit_div').css('top',($(window).height()-$('#edit_div').height())/2).css('left',($(window).width()-$('#edit_div').width())/2+150);
			$('#zhe_div').css('width',$(window).width()).css('height',$(window).height());
			$('#btn_cancel').click(function(){
				$('#edit_div').hide()
				$('#zhe_div').hide();	
			})
			var type;//定义全局变量
		});
		var url='';
		//打开新增窗口     type为传递参数类型0-寄件人  1-收件人
		function fn_new(userType){//传入了tpye
			/* url='userPeople/addUserPeople'; */
			$('#id').val('');
			$('#userName').val('');
			$('userType').val('');
			$('#userMobile').val('');
			$('#userContent').val('');
			$('#userAddress').val('');
			$('#userPostcode').val('');
			$('#edit_div').show();
			$('#zhe_div').show();
			type = userType; //将局部变量赋值给全局变量
		}
		
		//打开修改的窗口  
		function fn_upd(id,userName,userMobile,userContent,userAddress,userPostcode,userType){
			/* url='userPeople/addOrUpdateUserPeople'; */
			$('#id').val(id);
			$('#userName').val(userName);
			$('#userMobile').val(userMobile);
			$('#userContent').val(userContent);
			$('#userAddress').val(userAddress);
			$('#userPostcode').val(userPostcode);
			$('#edit_div').show();
			$('#zhe_div').show();
			type = userType; //将局部变量赋值给全局变量
		}
		function fn_del(id){
			if (confirm("确定要删除该条信息？")){
				$.ajax({
					  type: "POST",
					  url: "${base}/userPeople/deleteUserPeople",
					  data:{
						  id:id
					  },
					  dataType: "json",
					  success: function(data){
						if(data.data.success){
							alert("删除成功！");
							/* fn_get_person();//刷新-回显页面 */
							 window.location.reload();
						}else{
						   alert("删除失败");
						}
					  }
				});
			}
		}
		//保存新增信息
		function fn_save(){
			var id= $('#id').val();
			var userName = $('#userName').val();
			var userMobile = $('#userMobile').val();
			var userContent = $('#userContent').val();
			var userAddress = $('#userAddress').val();
			var userPostcode = $('#userPostcode').val();
			if(!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(userName) || !/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(userName)){
				alert("名字只可为汉字/字母/数字");
				return;
			}
			if(!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(userMobile)&&!/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(userMobile)){
				alert('电话格式不正确,例：18712312312,/021-88888888');
				return;
			}
			if(userContent==''){
				alert('请选择省市区县');
				return;
			}
			if(userAddress==''){
				alert('请填写详细地址');
				return;
			}
			if(userAddress.length>50){
				alert('请缩减地址长度小于25');
				return;
			}
			if(userPostcode!=''){
				if(!/^[1-9][0-9]{5}$/.test(userPostcode)){
					alert('请填写正确格式的邮编')
					return;
				}
			}
			$.ajax({
				  type: "POST",
				  url: "${base}/userPeople/addOrUpdateUserPeople",
				  data:{
					  id:id,
					  userName:userName,
					  userMobile:userMobile,
					  userContent:userContent,
					  userAddress:userAddress,
					  userPostcode:userPostcode,
					  userType:type, //判断是寄件人还是收件人?去哪里判断,在开始的时候
				  },
				  dataType: "json",
				  success: function(data){
					if(data.data==1||data.data==0){
						alert("保存成功！");
						/* fn_get_person();//刷新-回显页面 */
						 window.location.reload();
					}else{
					   alert("保存失败!");	
					}
				  }
			 });
		}
		//寄件人、收件人   查询显示
		function fn_get_person(){
			  $.ajax({
				  type: "POST",
				  url: "${base}/userPeople/getAllUserPeople",
				  dataType: "json",
				  success: function(data){//返回的参数
					  data=data.data;
					  var str0='';
					  var str1='';
					  //传过来3个对象，挨个判断其中type的状态，然后进行添加
					  $(data).each(function(i,cm){
						  var _str='<tr>'+
									    '<td>'+cm['userName']+'</td>'+
									    '<td>'+cm['userMobile']+'</td>'+
									    '<td>'+cm['userContent']+'</td>'+
										'<td>'+cm['userAddress']+'</td>'+
									    '<td>'+cm['userPostcode']+'</td>'+
									    '<td>'+
											'<input type="button" value="修改" class="edit_btn" onclick="fn_upd(\''+cm['id']+'\',\''+cm['userName']+'\',\''+cm['userMobile']+'\',\''+cm['userContent']+'\',\''+cm['userAddress']+'\',\''+cm['userPostcode']+'\')"/>'+
											'<input type="button" value="删除" class="edit_btn" onclick="fn_del(\''+cm['id']+'\')"/>'+
										'</td>'+
									'</tr>';
						  if(data[i].userType=='0'){
						  	 str0+=_str;
					  	  }else{
					  		 str1+=_str;
					  	  }
					  });
					  $('#tb_result_0').html(str0);
					  $('#tb_result_1').html(str1);
					  $(".result_jijian tr:odd").css("background",'#F7F9D7');
				  }
			 });
		}
	</script>

<body class="home-bg">

<div id="edit_div" style="background-color:#FFFFFF; border:#FFCC00 solid 1px;border-radius: 15px;width:400px; position:fixed; z-index:2;display: none">
	<table class="gerenziliao" style="margin-bottom:25px;margin-left:55px;">
		<tbody>
			<tr>
				<td width="67" align="right">姓名：</td>
				<td><input id="userName" type="text" class="form-control" placeholder="2-8位汉字(必填)" /><input type="hidden" id="id"/></td>
			</tr>
			<tr>
				<td width="67" align="right">电话：</td>
				<td><input id="userMobile" type="text" class="form-control" placeholder="联系电话(必填)" maxlength="11" /></td>
			</tr>
			<!-- 
			<tr>
				<td width="67" align="right">性别：</td>
				<td><div class="sex"><input type="radio" name="sex" />男 </div><div class="sex"><input type="radio" name="sex"/>女</div></td>
			</tr>
		    -->
			<tr>
				<td width="67" align="right">省市区县：</td>
				<td>
					<label class="newprocitySel"> 
						<input class="send_input proCityQueryAll proCitySelAll form-control"
							id="userContent"  placeholder="点击选择省市区县(必填)" name="address" style="font-weight: 100;height:34px;"
							 readonly="readonly"> 
						<a href="javascript:;" class="proCitySelAll_Img png"></a>
					</label>
				</td>
			</tr>
			<tr>
				<td width="67" align="right">具体地址：</td>
				<td><input id="userAddress" type="text" class="form-control" placeholder="具体地址(必填)" /></td>
			</tr>
			<tr>
				<td width="67" align="right">邮编：</td>
				<td><input id="userPostcode" type="text" class="form-control" placeholder="邮编(选填)" /></td>
			</tr>
			<tr>
				<td width="67" align="right"></td>
				<td><button class="btn" style="width:60px" onclick="fn_save()">保存</button><button id="btn_cancel" class="btn" style="width:60px;margin-left:10px">取消</button></td>
			</tr>
		</tbody>
	</table>
</div>
<div id="zhe_div" style="position:fixed;filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5;opacity: 0.5; background-color:#666666; z-index:1; display: none"></div>

	<c:import url="../../commons/common-top.jsp" />
	<div class="clear"></div>
	<div class="container mg">
		<!--sidebar-->
		<c:import url="../../commons/childmenu.jsp" />
		
		<!--具体内容-->
		<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title"></h4>
				<div class="biaoqianye ub">
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" id="sender">寄件人管理</a></li>
				    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" id="receiver">收件人管理</a></li>
				  </ul>
				  <!-- Tab panes -->
				  <div class="tab-content">
				    
				    <div role="tabpanel" class="tab-pane active" id="home">
				        <div style="position: absolute;top:195px;right:0px;"><button class="btn" value="新增" style="margin-top:5px; margin-bottom:10px; margin-right:40px;width:80px;width: 100px;height: 30px;font-size: 13px;line-height: 30px;float:right;" onclick="fn_new(0)">新增</button></div>
					    <div class="gdt">
							<table class="result_jijian">
							  <thead>
								  <tr>
									  <th width="100">姓名</th>
									  <th width="130">电话</th>
									  <th width="260">省市区县</th>
									  <th width="300">具体地址</th>
									  <th width="100">邮编</th>
									  <th width="100">操作</th>
								  <tr>
							  </thead>
							  <tbody id="tb_result_0">
							  </tbody>
							</table>	
					    </div>
				    </div>
				    <div role="tabpanel" class="tab-pane" id="profile">
				    <div style="position: absolute;top:195px;right:0px;"><button class="btn" value="新增" style="margin-top:5px; margin-bottom:10px; margin-right:40px;width:80px;width: 100px;height: 30px;font-size: 13px;line-height: 30px;float:right;" onclick="fn_new(1)">新增</button></div>
					    <div class="gdt">
							<table class="result_jijian">
							  <thead>
								  <tr>
									  <th width="100">姓名</th>
									  <th width="130">电话</th>
									  <th width="260">省市区县</th>
									  <th width="300">具体地址</th>
									  <th width="100">邮编</th>
									  <th width="100">操作</th>
								  <tr>
							  </thead>
							  <tbody id="tb_result_1">
							  </tbody>
							</table>	
					    </div>
				    </div>
				  </div>
				</div>
			</div>	
		</div>
	</div>
	<script type="text/javascript" src="${base}/scripts/city.js"></script>
	<c:import url="../../commons/common-footer.jsp" />
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-收寄人管理
</script>	
</html>
