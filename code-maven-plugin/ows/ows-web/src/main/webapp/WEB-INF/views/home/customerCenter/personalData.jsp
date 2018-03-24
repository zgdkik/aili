<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<link href="${base}/styles/cityLayout.css" type="text/css"
	rel="stylesheet" />
<c:import url="../../commons/common-script.jsp" />
<style>
.gdt {
	overflow: auto;
	width: 825px;
	margin-left: 25px;
	margin-top: 10px;
}

.gdt td {
	height: 30px;
	font-size: 12px;
	font-family: "黑体";
	color: #666666;
	text-align: center;
}

.gdt th {
	height: 35px;
	background-color: #FFCC00;
	color: #FFFFFF;
	text-align: center;
	border-right: #FFFFFF solid 2px;
}

.gdt table {
	border-collapse: collapse;
}

.edit_btn {
	border: #FFCC00 solid 1px;
	background: #FFFFFF;
	color: #999999;
	cursor: pointer;
	padding: 2px;
	margin-left: 2px
}

.edit_btn:hover {
	background: #FFCC00;
	color: #FFFFFF
}
</style>
</head>
<body class="home-bg">
	<c:import url="../../commons/common-top.jsp" />
	<div class="clear"></div>
	<div class="container mg">
		<!--sidebar-->
		<c:import url="../../commons/childmenu.jsp" />
		
		<!--具体内容-->
		<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner"
				style="width: 885px; height: 173px;">
				<%-- <img src="${base}/images/ban16.jpg" alt="" /> --%>
			</div>
			<div class="contenr-right-bottom ub">
				<!-- <h4 id="h_title">个人资料</h4> -->
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<!-- 为何能实现切换，是因为href可以进行切换控制 -->
					<li role="presentation" class="active"><a href="#personal"
						aria-controls="profile" role="tab" data-toggle="tab"
						id="personalInformation">个人资料</a></li>
					<!-- <li role="presentation"><a href="#email"
						aria-controls="profile" role="tab" data-toggle="tab"
						id="emailAddress">邮箱绑定</a></li> -->
					<li role="presentation"><a href="#mobile"
						aria-controls="profile" role="tab" data-toggle="tab"
						id=" mobileBinding">手机号绑定</a></li>
					<li role="presentation"><a href="#userMemberCard"
						aria-controls="profile" role="tab" data-toggle="tab"
						id=" memberCardBinding">会员卡绑定</a></li>
				</ul>
				<!-- tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="personal">
						<form method="post" id="self">
							<table class="gerenziliao">
								<tbody>
									<tr>
										<td width="67" align="right">真实姓名：</td>
										<td><input id="realName" name="realName" type="text"
											class="form-control" placeholder="2-8位有效字符(必填)" value="" maxlength="8"/></td>
									</tr>
									<tr>
										<td width="67" align="right">公司名称：</td>
										<td><input type="text" class="form-control"
											placeholder="选填" name="companyName" id="companyName" value="" maxlength="20"></td>
									</tr>
									<tr>
										<td width="67" align="right">性别：</td>
										<td><div class="sex">
												<input type="radio" name="gender" id="man" checked="checked"
													value="1" />男
											</div>
											<div class="sex">
												<input type="radio" name="gender" id="woman" value="0" />女
											</div></td>
									</tr>
									<tr>
										<td width="67" align="right">固定电话：</td>
										<td>
										<input type="text" class="form-control"
											style="width: 70px;" placeholder="区号" name="areaCode"
											id="areaCode" maxlength="4" value="" /> 
										<input type="text"
											class="form-control" placeholder="固话(选填)" name="telephone"
											id="telephone" value=""  maxlength="12"/></td>
									</tr>
									<tr>
										<td width="67" align="right">省市区县：</td>
										<td><label class="newprocitySel"> <input
												class="send_input proCityQueryAll proCitySelAll form-control"
												id="checkAddress" placeholder="点击选择省市区县" name="address"
												style="font-weight: 100; height: 34px;" readonly="readonly"
												value=""> <a href="javascript:;"
												class="proCitySelAll_Img png"></a>
										</label></td>
									</tr>
									<tr>
										<td width="67" align="right">具体地址：</td>
										<td><input type="text" class="form-control"
											placeholder="具体地址(选填)" name="concreteAddress"
											id="concreteAddress" value="" maxlength="50" /></td>
									</tr>
									<tr>
										<td width="67" align="right">银行类型：</td>
										<td>
											<!-- <select class="form-control" name="bankType" id="bankType" /> -->
											<select class="form-control" name="bankType" id="bankType">
												<option>无卡号</option>
												<option>建设银行</option>
												<option>农业银行</option>
												<option>浦发银行</option>
												<option>工商银行</option>
												<!-- <option>无卡号</option> -->
										</select>
										</td>
									</tr>
									<tr>
										<td width="67" align="right">银行帐号：</td>
										<td><input type="text" class="form-control"
											placeholder="银行帐号(选填)" name="bankNumber" id="bankNumber" maxlength="16"
											value="" /></td>
									</tr>
									<tr>
										<td width="67" align="right"></td>
										<td><input type="button" value="保存" class="btn"
											onclick="save_user()"
											style="width: 100px; height: 33px; line-height: 33px; font-size: 13px" />
											<button  class="btn aa" style="width: 100px; height: 33px; line-height: 33px; font-size: 13px" >返回</button>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>

					<!-- 绑定邮箱 -->
					<div role="tabpanel" class="tab-pane" id="email">
						<div class="gdt">

							<table class="gerenziliao">
								<tbody>
									<tr>
										<td width="67" align="right">原邮箱</td>
										<td><input id="oldeMail" name="oldeMail" type="text"
											class="form-control" placeholder="当前未绑定邮箱" value=""
											readonly="readonly" /></td>
									</tr>
									<tr>
										<td width="67" align="right">新邮箱</td>
										<td><input type="text"
											class="form-control validate[required,custom[email],minSize[8],maxSize[30]]"
											placeholder="新的安全邮箱地址" name="newEmail" id="newEmail" value=""></td>
									</tr>
									<tr>
										<td width="67" align="right"></td>
										<td><input type="submit" value="确认修改" class="btn"
											id="btnSubmit_E" name="btnSubmit_E" onclick="save_email()"
											style="width: 100px; height: 33px; line-height: 33px; font-size: 13px" />
											<button class="btn aa"  id="returnEmail" 
											style="width: 100px; height: 33px; line-height: 33px; font-size: 13px" >返回</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<!-- 绑定手机号 -->
					<div role="tabpanel" class="tab-pane" id="mobile">
						<div class="gdt">

							<table class="gerenziliao">
								<tbody>
									<tr>
										<td align="right" ><a style="color:red">*</a>登陆密码</td>
										<td><input type="password" name="password" id="password" class="form-control" 
											placeholder="" />
									</tr>
									<tr>
										<td align="right"><a style="color:red">*</a>手机号码</td>
										<td><input class="form-control validate[required,custom[mobilephone]]" 
											type="text" id="newMobile" maxlength="11" placeholder=""/>
										</td>
										
									</tr>
									<tr>
										<td align="right">验证码</td>
										<td>
											<input type="text" id="phonecode" name="phonecode" class="form-control" placeholder="验证码"  onblur="check_phonecode()"/>
				    					</td>
				    					<td>
				    						<img id="imgphonecode" src="${base}/register/createCode/phonecertpic" alt="" width="76" title="看不清？换一张" style="cursor: pointer" onclick="reloadphonecode()"/>
				    						<span class="label" id="err_phonecode" style="color: red"></span>
				    					</td>
									</tr>
									<tr>
										<td align="right">短信验证码</td>
										<td><input type="text" name="mobileMsgCode" id="mobileMsgCode" class="form-control" placeholder="短信验证码" maxlength="6"/></td>
										<td>
											<input type="button" id="sendMobileMsg" value="发送短信" class="edit_btn" onclick="send_mobileMsg()" />
										</td>
									</tr>
									<tr>
										<td align="right"></td>
										<td>
											<button type="submit" class="btn btn-default"
												data-loading-text="正在绑定..." id="btnSubmit_M" onclick="save_phone()">确认绑定</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<!-- 会员卡绑定 -->
					<div role="tabpanel" class="tab-pane" id="userMemberCard">
						<div class="gdt">
							<table class="gerenziliao">
								<tbody>
									<tr>
										<td width="67" align="right"><a style="color:red">*</a>会员卡卡号</td>
										<td><input type="text"
											class="form-control" placeholder="" name="memberCard" id="memberCard" value=""></td>
									</tr>
									<tr>
										<td width="67" align="right">持卡人</td>
										<td><input type="text"
											class="form-control" placeholder="请填写你的真实姓名" name="userName" id="userName" value="" maxlength="8"></td>
									</tr>
									<tr>
										<td width="67" align="right">身份证</td>
										<td><input type="text"
											class="form-control" placeholder="持卡人身份证" name="IDCard" id="IDCard" value="" maxlength="18"></td>
									</tr>
									<tr>
										<td width="67" align="right">联系电话</td>
										<td><input type="text"
											class="form-control" placeholder="办卡时预留电话" name="initialPhone" id="initialPhone" value="" maxlength="11"></td>
									</tr>
									<tr>
										<td width="67" align="right"></td>
										<td><input type="submit" value="确认绑定" class="btn"
											id="btnSubmit_E" name="btnSubmit_E" onclick="save_memberCard()"
											style="width: 100px; height: 33px; line-height: 33px; font-size: 13px" />
											<button  class="btn aa" id="returnMember" 
											style="width: 100px; height: 33px; line-height: 33px; font-size: 13px" name="aa" >返回</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${base}/scripts/city.js"></script>
	<c:import url="../../commons/common-footer.jsp" />
	<script type="text/javascript">
	//整體佈局展示
	$(function() {
		fnOldEmailLoad(); 
		fnUserLoad();
		fn_get_menu("ddwl_khzx");//加载菜单
		$('#edit_div').css('top',
				($(window).height() - $('#edit_div').height()) / 2).css('left',
				($(window).width() - $('#edit_div').width()) / 2 + 150);
		$('#zhe_div').css('width', $(window).width()).css('height',
				$(window).height());
		$('#btn_cancel').click(function() {
			$('#edit_div').hide()
			$('#zhe_div').hide();
		})
	});
	//检查图形验证码
	function check_phonecode(){
		var code = $("#phonecode").val();
		if(code==''){
			$("#err_phonecode").html("请填写验证码");
			return false;
		}else{
			var params = {'phonecode':code};
			$.ajax({  
				url:base+"/register/checkPhoneCode",
				type:"post",
				data:params,
				dataType:"json",
				success:function(arr){ 
					if(arr.data==1){
						$("#err_phonecode").html("<img src='"+base+"/images/ok.png' />");
						$('#sendMsg').attr('disabled',false);
						$('#sendMsg').attr('style','width:75px;height: 34px;background-color:white;border: 1px solid #ccc;line-height:34px;color: black;');
					}else if(arr.data==0){
						$("#err_phonecode").html("验证码错误");
						return false;
					}
				}            
			});
		}
	}
	//加载默认原始邮箱-回显
	 function fnOldEmailLoad() {
		$.ajax({
			type : "post",
			url : "${base}/customer/accordingOldEmail",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					data = data.data;
					$("#oldeMail").val(data.email);
				}
			}
		});
	} 
	//保存新邮箱-全部后台校验了！    --正则也交给后台进行校验！前台只能接收一个window.onload
	function save_email() {
		var newEmail = $('#newEmail').val();
		$.ajax({
			type : "post",
			url : "${base}/customer/changeEmail",
			dataType : "json",
			data : {
				newEmail : newEmail
			},
			success : function(data) {
				data = data.data;
				alert(data.msg);
				window.location.reload();
			}
		});
	}
	//发送短信验证功能   
	 function send_mobileMsg(){
		document.getElementById("sendMobileMsg").setAttribute("disabled","disabled");//设置禁用标签
		var i = 60;
		setInterval(function() {    
			if(i -- > 0) {  
				$("#sendMobileMsg").val("稍等"+i+"秒"); 
			}else{
				$("#sendMobileMsg").attr("disabled",false);
				$("#sendMobileMsg").val("发送短信");  
			}
		},1000);
		var newMobile = $("#newMobile").val();//新手机号码
		var mobileMsgCode = $("#mobileMsgCode").val();//发送的验证码
		$.ajax({
			url:"${base}/customer/getMobileCode",
			type:"post",
			data:{
				newMobile:$("#newMobile").val(),
				mobileMsgCode:$('#mobileMsgCode').val(),
				phonecode:$("#phonecode").val()
				},
			dataType:"json",
			success:function(data){ 
				if(data=="1"){
					alert("短信已发送");
				}else if(data=='2'){
					alert("短信发送失败");
					clearInterval(dx);
					$("#sendMobileMsg").attr("disabled",false);
					$("#sendMobileMsg").val("发送短信");  
				}else if(data=='3'){
					clearInterval(dx);
					alert("验证码错误");
					$("#sendMobileMsg").attr("disabled",false);
					$("#sendMobileMsg").val("发送短信");  
				}
			}            
		});
	} 
	
	
	//关于手机号码的离交事件-请输入正确的手机号！
	   window.onload = function(){
		var inpEle = document.getElementById('newMobile');
		var myreg = /^1[34578]\d{9}$/;
		inpEle.onblur = function(){
			var inpVal = this.value;
			if (!myreg.exec(inpVal)){
			alert('请输入正确的手机号');
			}
		}
	}   
	
	//关于邮箱的离交事件-请输入正确的邮箱格式~!
	/*  window.onload = function(){
		var verifyMail = document.getElementById('newEmail');
		var mailreg =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		verifyMail.onblur = function(){
			var mailVar = this.value;
			if(!mailreg.exec(mailVar)){
				alert('请输入正确的邮箱格式!');
			}
		}
	}  */
	//保存新手机号码-全部后台校验了！
	function save_phone(){
		$.ajax({
			type:"post",
			url:"${base}/customer/updateNewPhoneByCode",
			dataType:"json",
			data:{
				password:$('#password').val(),
				newMobile:$("#newMobile").val(),
				mobileMsgCode:$("#mobileMsgCode").val(),
			},
			success:function(data){
				data=data.data;
				alert(data.msg);
				window.location.reload();
			}
		});
	}
	
	//会员卡的绑定
	function save_memberCard(){
		if (confirm("确定要保存信息吗？")) {
		$.ajax({
			type:"post",
			url:"${base}/customer/updateMemberCardById",
			dataType:"json",
			data:{
				memberCard:$('#memberCard').val(),//会员卡
				userName:$('#userName').val(),//用户名-持卡人
				IDCard:$("#IDCard").val(),//身份证
				initialPhone:$("#initialPhone").val(),//初始手机号
			},
			success:function(data){
				data=data.data;
				alert(data.msg);
				window.location.reload();
			}
		});
	}
}
	//选择银行
	/* $(function(){
		fn_get_menu('ymzx');
		fn_getleftmenu('ymzx','myself');
		$.ajax({  
			url:"../dictdetail/query",
			type:"post",
			data:{'code':'BANK_TYPE'},
			dataType:"json",
			success:function(data){ 
				$("#bankType").empty();
				var type = '';
	            $(data).each(function(i,cm){
	            	$("#bankType").append('<option value=\''+cm['type']+'\' '+(cm['type']==type?'selected':'')+'>'+cm['typeName']+'</option>');
	            });
			}            
		}); 
	}) */
	//用户个人信息的回显
	function fnUserLoad() {
		$.ajax({
			type : "post",
			url : "${base}/customer/accordingUserDetails",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					data = data.data;
					$("#realName").val(data.realName);
					$("#companyName").val(data.companyName);
					$("#gender").val(data.gender);
					$("#areaCode").val(data.areaCode);
					$("#telephone").val(data.telephone);
					$("#concreteAddress").val(data.concreteAddress);
					$("#bankType").val(data.bankType);
					$("#bankNumber").val(data.bankNumber);
					$("#checkAddress").val(data.checkAddress);
				}
			}
		});
	}
	//用户个人资料的保存
	function save_user() {
		if (!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test($("#realName").val())) {
			alert("姓名只允许输入汉字/字母/数字");
			return;
		}
		if ($("#companyName").val().length > 0
				&& !/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test($("#companyName").val())) {
			alert("公司名称只允许输入汉字/字母/数字");
			return;
		}

		var area = $("#areaCode").val(); //固定电话地区编号
		var tele = $("#telephone").val(); //固定电话地区号码-座机
		if (area != '' || tele != '') {
			if (!/^[0-9]{3,5}$/.test(area)) {
				alert("区号为3-5位数字");
				return;
			}
			if (!/^[0-9]{6,8}$/.test(tele)) {
				alert("电话号码为6-8位数字");
				return;
			}
		}
		if ($("#concreteAddress").val().length > 0) {
			if (!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/
					.test($("#concreteAddress").val())) {
				alert("具体地址只可为汉字/字母/数字");
				return;
			}
		}

		var realName = $('#realName').val(); //真实姓名
		var companyName = $('#companyName').val(); //公司名字
		/* var gender = $('input[name="gender"]:checked').val; *///性别
		var temp = document.getElementsByName("gender");
		for (var i = 0; i < temp.length; i++) {
			if (temp[i].checked)
				var gender = temp[i].value;
		}
		var checkAddress = $('#checkAddress').val(); //省市区选择
		var concreteAddress = $('#concreteAddress').val(); //详情地址
		var bankType = $('#bankType').val(); //银行卡类型
		var bankNumber = $('#bankNumber').val(); //银行卡卡号
		if (confirm("确定要保存个人信息？")) {
			$.ajax({
				url : "${base}/customer/savePersonalDetails",
				type : "post",
				data : {
					realName : realName,
					companyName : companyName,
					gender : gender,
					areaCode : area,
					telephone : tele,
					checkAddress : checkAddress,
					concreteAddress : concreteAddress,
					bankType : bankType,
					bankNumber : bankNumber,
				},
				dataType : "json",
				success : function(data) {
				
					alert(data.data.msg);
					/* if (data.data.flag == "1") {
						alert(data);
					} else {
						alert(data.data.msg);
						/* location.href='../home/home.jsp' 
					} */
				}
			});
		}
	}
	//返回当前页面-等同于刷新
	$(".aa").on('click',function(){
		window.location.href = base+'/ddwlGw/personalData?title=个人资料';
 	})
 	function reloadphonecode(){
	    var verify=document.getElementById('imgphonecode');
	    verify.setAttribute('src',base+'/register/createCode/phonecertpic?it='+Math.random());
	}
 	//
	/* $("#returnEmail").on('click',function(){
		window.location.href = base+'/ddwlGw/personalData?title=个人资料';
 	})
 	//返回首页
	$("#returnMember").on('click',function(){
		window.location.href = base+'/ddwlGw/personalData?title=个人资料';
 	}) */
</script>
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-个人资料
</script>	
</html>
