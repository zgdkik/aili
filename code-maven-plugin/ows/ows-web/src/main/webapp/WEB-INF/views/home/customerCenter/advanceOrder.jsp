
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<c:import url="../../commons/common-script.jsp" />
<link rel="stylesheet" href="${base}/resources/Zebra_Datepicker/public/css/default.css" type="text/css">
<script type="text/javascript" src="${base}/resources/js/bootstrap-typeahead.js?${version}"></script>
<style>
  .gdt{
   overflow:auto;height:450px;
   width:700px;margin-left: 25px; margin-top:10px;
  }
  .gdt td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;cursor: pointer;}
  .gdt th{height:35px; background-color:#D24C47;color:#FFFFFF; text-align: center;border-right:#FFFFFF solid 2px;}
  .gdt table{border-collapse:collapse;}
  .edit_btn{border:#fcb814 solid 1px; background:#FFFFFF; color:#999999; cursor:pointer;padding:2px;margin-left:2px}
  .edit_btn:hover{ background: #FFCC00;color:#FFFFFF}
</style>
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
			<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}<a href="${base}/ddwlGw/orderManage?title=预约订单"  style="margin-top:5px; margin-bottom:10px; margin-right:40px;width:80px;width: 100px;height: 30px;font-size: 13px;line-height: 30px;float:right;">
					<strong style="color:#FD2B39">我的订单</strong></a></h4>
				<form id="order">
				<div class="xiadan fuwuxinxi">
					<div class="xiadan-title ub">
						<span>货物信息</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">到达网点：</span>
					  <input id='destZoneView' type="text" class="form-control" aria-describedby="basic-addon1">
					   <input id='destZoneCode' name="destZoneCode" style="display: none">
					  <span class="tips">*</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">货物名称：</span>
					  <input name="goodsName" id="goodsName" type="text" class="form-control" aria-describedby="basic-addon1">
					  <span class="tips">*</span>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">数量：</span>
					  <input name="quantity" id="quantity" type="number" class="form-control" aria-describedby="basic-addon1" onblur="quantityOnblur()">
					  <span class="tips">件*</span>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">声明价值：</span>
					  <input name="valuationFee" id="valuationFee"  onkeypress="return noNumbers(event)"  class="form-control" aria-describedby="basic-addon1">
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">重量：</span>
					  <input name="weightQty" id="weightQty" onkeypress="return noNumbers(event)" class="form-control" aria-describedby="basic-addon1" >
					  <span class="tips">*kg</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">体积：</span>
					  <input name="volume" id="volume" onkeypress="return noNumbers(event)" class="form-control" aria-describedby="basic-addon1">
					  <span class="tips">*m³</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">代收货款：</span>
					  <input name="collectionFee" id="collectionFee" onkeypress="return noNumbers(event)" class="form-control" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">代收运费：</span>
					  <input name="expressFee" id="expressFee" onkeypress="return noNumbers(event)" class="form-control" aria-describedby="basic-addon1">
					</div>
				</div>
				<div class="xiadan packing">
					<div class="xiadan-title ub">
						<span>货物包装以及数量</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1"  >木箱：</span>
					  <input  type="number"  class="form-control packing" aria-describedby="basic-addon1" disabled >
					 	<input  name="compCode"  value="dadaowl" class="form-control" style="display: none"/>
					  <span class="tips" style="color:#000">件</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">纸箱：</span>
					  <input   type="number"  class="form-control packing" aria-describedby="basic-addon1" disabled>
					  <span class="tips" style="color:#000">件</span>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">桶装：</span>
					  <input  type="number"  class="form-control packing" aria-describedby="basic-addon1" disabled>
					  <span class="tips" style="color:#000">件</span>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">纤袋：</span>
					  <input   type="number" class="form-control packing" aria-describedby="basic-addon1" disabled>
					  <span class="tips" style="color:#000">件</span>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">无包：</span>
					  <input type="number"  class="form-control packing" aria-describedby="basic-addon1" disabled>
					  <span class="tips" style="color:#000">件</span>
					</div>
					<!-- <div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">单据：</span>
					  <input   type="number"  class="form-control packing" aria-describedby="basic-addon1">
					  <span class="tips" style="color:#000">件</span>
					</div> -->
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">其他：</span>
					  <input  type="number"  class="form-control packing" id="packingOhter" aria-describedby="basic-addon1" disabled>
					  <span class="tips" style="color:#000">件</span>
					</div>
				</div>
				<div id="edit_div" style="background-color:#FFFFFF; border:#B22923 solid 1px;border-radius: 15px;width:750px;height:520px; position:fixed; z-index:22;display: none">
				    <span style="float:right;color: #FFCC00;padding:10px; cursor: pointer;" onclick="fn_choose_colse()">关闭</span>
					<div class="gdt">	        
						<table class="result_jijian">
						  <thead>
							  <tr>
								  <th width="100">姓名</th>
								  <th width="130">电话</th>
								  <th width="450">地址</th>
							  <tr>
						  </thead>
						  <tbody id="tb_result">
						  </tbody>
						</table>	
				    </div>
				</div>
				<div class="xiadan fuwuxinxi">
					<div class="xiadan-title ub">
						<span>收货人信息</span>
						<button type="button" class="btn-danger" onclick="fn_choose('1')">选择收货人</button>
						<!-- <button type="button" class="btn-warning">设为常用收货人</button> -->
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">收件人姓名：</span>
					  <input name="addresseeName" id="addresseeName" type="text" class="form-control" aria-describedby="basic-addon1">
					  <span class="tips">*</span>
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">收件人电话：</span>
					  <input name="addresseePhone" id="addresseePhone" type="text" class="form-control" aria-describedby="basic-addon1">
					  <span class="tips">*</span>
					</div>
					<div class="input-group" style="width:800px">
					  <span class="input-group-addon" id="basic-addon1" style="width:120px">详细地址：</span>
					  <input name="addresseeAddr" id="addresseeAddr" type="text" class="form-control guding" aria-describedby="basic-addon1" style="width:490px">
						 <span class="tips">*</span>
					</div>
				</div>
				<div class="xiadan fuwuxinxi">
					<div class="xiadan-title ub">
						<span>寄件人信息</span>
						<button type="button" class="btn-danger" onclick="fn_choose('0')">选择寄件人</button>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">会员卡号：</span>
					  <input id='cardNumber' name="cardNumber" type="text" class="form-control" aria-describedby="basic-addon1">
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">持卡人：</span>
					  <input name="cardName" id="cardName" type="text" class="form-control" aria-describedby="basic-addon1">
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">发货人：</span>
					  <input name="consignorName" id="consignorName" type="text" class="form-control" aria-describedby="basic-addon1">
					  <span class="tips">*</span>
					</div>
					
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">电话：</span>
					  <input type="text" id="consignorPhone" name="consignorPhone"  class="form-control" style="height:30px;width:120px">
					  <span class="tips">*</span>
					</div>
					
					<div class="input-group" style="width:800px">
					  <span class="input-group-addon" id="basic-addon1">详细地址：</span>
					  <input name="consignorAddr" id="consignorAddr" type="text" class="form-control guding" aria-describedby="basic-addon1" style="width:490px">
					</div>
				</div>
				<div class="xiadan fuwuxinxi">
					<div class="xiadan-title ub">
						<span>其他信息</span>
					</div>
					<div class="input-group" style="width:800px">
					  <span class="input-group-addon" id="basic-addon1">备注：</span>
					  <input name="remark" id="remark" type="text" class="form-control guding" aria-describedby="basic-addon1" style="width:490px">
					</div>
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">其他选项：</span>
					  <input name="delivery" id="delivery" value="1" type="checkbox" class="form-control" style="height:23px;width: 23px;margin-left: 30px" aria-describedby="basic-addon1">
					   <span class="tips" style="font-size: 18px;color: #555;">送货</span>
					</div>
					<div class="input-group" style="width:500px">
						 <span class="input-group-addon" id="basic-addon1">验证码：</span>
	    				<input type="text" id="phonecode" class="form-control yzm" placeholder="验证码" style="width: 96px" onblur="check_phonecode()"/>
	    				<span class="img"><img id="imgphonecode" src="${base}/register/createCode/phonecertpic" alt="" width="76" height="34" title="看不清？换一张" style="cursor: pointer" onclick="reloadphonecode()"/></span>
	    				<span class="label tips" id="err_phonecode" ></span>
	    				<span class="tips">*</span>
	    			</div>
				</div>
				<div style="width: 100%;text-align: center;">
					<input style="width: 120px;height: 34px;line-height: 34px" type="button" class="btn" value="立即下单" onclick="fn_save()"/>
					<input style="width: 120px;height: 34px;line-height: 34px" type="reset" value="重置" class="btn" />
				</div>
				</form>
			</div>	
		</div>
	</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<script type="text/javascript">
		var orderId = '';
		var url = '';
		$(function(){
			fn_get_menu("ddwl_khzx");
			//$('#sendTm').Zebra_DatePicker();
			/* fn_get_dict('BANK_TYPE','bankType');
			fn_get_dict('SEND_WAY','sendWay');
			fn_get_dict('PAYMENT_TYPE_CODE','paymentTypeCode'); */
			$.ajax({
	 			url : '${base}/customer/getDepartMent/',
	 			async : false,
	 			success : function(data){
	 			 $('#destZoneView').typeahead(
			            {
			                source:data.data,
			                items:50,
			                itemSelected: displayTxt_d
			            });
	 			},
	 			error : function() {
	 				alert('错误');
	 			},
	 			dataType : "json"
	 		});
			orderId = '';
			
			fn_get_person();
		})
		 function displayTxt_d(item, val, text) {
				 $('#destZoneCode').val(val);
       }
		function fn_get_dict(code,id){
			$.ajax({  
				url:"../dictdetail/query",
				async :false,
				data:{
					code:code
				},
				dataType:"json",
				success:function(arr){
					var str = '';
					$(arr).each(function(i,cm){
						str+='<option value="'+cm['type']+'">'+cm['typeName']+'</option>';
					})
					$('#'+id).html(str);
				}            
			});
		}
		function fn_save(){
			
			if(checkCodeFlag==0){
				alert("请填写正确的验证码");
				$("#phonecode").focus();
				return;
			}
			var intFlag=0;//数字框正数标识符
			$("input[type=number]").each(function(){
				if($(this).val()<0){
					$(this).focus();
					intFlag=-1;
					return;
				}
			});
			if(intFlag==-1){
				alert("请填写正数");
				return;
			}
			
			if(!$('#destZoneCode').val()){
				if(!$("#destZoneView").val()){
					alert("到达网点不能为空");
				}else{
					alert("你输入的网点不存在，请重新输入");
				}
				$('#destZoneView').focus();
				return;
			}
			if(!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test($("#consignorName").val())){
				alert("发货人名字为汉字/字母/数字");
				$("#consignorName").focus();
				return;
			}
			if(!/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/i.test($("#consignorPhone").val())&&!/^([0-9]{3,4}-)?[0-9]{7,8}$/.test($("#consignorPhone").val())){
				alert('发货人电话格式不正确,例：18712312312,/021-88888888');
				$("#consignorPhone").focus();
				return;
			}
			if(!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test($("#addresseeName").val())){
				alert("收件人名字为汉字/字母/数字");
				$("#addresseeName").focus();
				return;
			}
			
			if(!/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/i.test($("#addresseePhone").val())&&!/^([0-9]{3,4}-)?[0-9]{7,8}$/.test($("#addresseePhone").val())){
				alert('电话格式不正确,例：18712312312,/021-88888888');
				$("#addresseePhone").focus();
				return;
			}
			
			if(!/^[a-zA-Z0-9-\u4E00-\u9FA5]+$/.test($("#addresseeAddr").val())){
				alert("收件地址为汉字/字母/数字");
				$("#addresseeAddr").focus();
				return;
			}
			
			if(!/^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test($("#goodsName").val())){
				alert("货物名称为汉字/字母/数字");
				$("#goodsName").focus();
				return;
			}
			
			if($("#quantity").val()!=''){
				var packingNumber=0;
				$(".packing").each(function(i,cm){
					var num = $(this).val();
					if(num==null || num==""){
						num = 0;
					}
					packingNumber+=parseInt(num);
				});
				if($("#quantity").val()!=packingNumber){
					alert("数量与货品包装以及数量模块总和不等");
					$("#quantity").focus();
					return;
				}
			}
			
			if(!/^\d+\.{0,1}\d*$/.test($("#weightQty").val())){
				alert("货物重量填写有误");
				$("#weightQty").focus();
				return;
			}
			if(!/^\d+\.{0,1}\d*$/.test($("#volume").val())){
				alert("货物体积填写有误");
				$("#volume").focus();
				return;
			}
			
			if(!/^\d+$/.test($("#quantity").val())){
				alert("货物数量填写有误");
				$("#quantity").focus();
				return;
			}
			if($("#expressFee").val()!=''&&$("#expressFee").val()>50000){
				alert("代收运费不能大于50000");
				$("#expressFee").focus();
				return;
			}
			if($("#collectionFee").val()!=''&&$("#collectionFee").val()>50000){
				alert("代收货款不能大于50000");
				$("#collectionFee").focus();
				return;
			}
			
			var packingNumber="";
			$(".packing").each(function(i,cm){
				if(i==0){
					if($(this).val()){
						packingNumber+="木箱-"+$(this).val();
					}
				}
				else{
					var pickName="";
					if(i==1){
						pickName="纸箱";
					}
					if(i==2){
						pickName="桶装";
					}
					if(i==3){
						pickName="纤袋";
					}
					if(i==4){
						pickName="无包";
					}
					if(i==5){
						pickName="单据";
					}
					if(i==6){
						pickName="其他";
					}
					if($(this).val()){
						if(packingNumber.length>0){
							packingNumber+="-"+pickName+"-"+$(this).val();
						}else{
							packingNumber+=""+pickName+"-"+$(this).val();
						}
					}
				}
			});
			var data=$("#order").getFormObj();
			data.packingNumber=packingNumber;
			
			$.ajax({  
				url:"${base}/order/addOrder",
				type:"post",
				data:data,
				dataType:"json",
				success:function(data){
					if(data.success){
						if(data.data.flag==1){
							alert("下单成功");
							//$("#order")[0].reset();
							window.location.href="${base}/ddwlGw/orderManage?title=预约订单";
						}else{
							alert(data.data.msg);
						}
					}
					else{
						alert(data.msg);
					}
					/* if(data.success=='info'){
						window.location.href='ordermanagement.jsp'
					} */
				}            
			}); 
		}
		var str0='';
		var str1='';
		var type = '';
		function fn_get_person(){
			  $.ajax({
				  type: "POST",
				  url: "${base}/userPeople/getAllUserPeople",
				  dataType: "json",
				  success: function(data){
					  data=data.data;
					  $(data).each(function(i,cm){
						  var _str='<tr onclick="fn_get_choose(\''+cm['userName']+'\',\''+cm['userMobile']+'\',\''+cm['userContent']+cm['userAddress']+'\')">'+
									    '<td>'+cm['userName']+'</td>'+
									    '<td>'+cm['userMobile']+'</td>'+
									    '<td>'+cm['userContent']+cm['userAddress']+'</td>'+
									'</tr>'; 
						  if(cm['userType']=='0'){
						  	 str0+=_str;
					  	  }else{
					  		 str1+=_str;
					  	  }
					  });
				  }
			 });
		}
		function fn_choose(_type){
			type= _type;
			if(_type=='0'){
				if(str0==''){
					alert('您还未设置发货人');
					return;
				}
				$('#tb_result').html(str0);
			}else{
				if(str1==''){
					alert('您还未设置收货人');
					return;
				}
				$('#tb_result').html(str1);
			}
			$(".result_jijian tr:odd").css("background",'#F7F9D7');
			$('#edit_div').show();
			$('#zhe_div').show();	
		}
		function fn_choose_colse(){
			$('#edit_div').hide();
			$('#zhe_div').hide();
		}
		function fn_get_choose(name,phone,address){
			if(type=='0'){
				$('#consignorName').val(name);
				$('#consignorPhone').val(phone);
				$('#consignorAddr').val(address);
			}else{
				$('#addresseeName').val(name);
				$('#addresseePhone').val(phone);
				$('#addresseeAddr').val(address);
			}
			$('#edit_div').hide();
			$('#zhe_div').hide();
		}
		var checkCodeFlag=0;
		function check_phonecode(){
			var code = $("#phonecode").val();
			if(code==''){
				$("#err_phonecode").html("请填写验证码");
				checkCodeFlag=0;
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
							checkCodeFlag=1;
							return true;
						}else if(arr.data==0){
							$("#err_phonecode").html("验证码错误");
							checkCodeFlag=0;
							return false;
						}
					}            
				});
			}
		}
		function reloadphonecode(){
		    var verify=document.getElementById('imgphonecode');
		    verify.setAttribute('src',base+'/register/createCode/phonecertpic?it='+Math.random());
		}
		function quantityOnblur(){
			$(".packing").each(function(i,cm){
				$(this).removeAttr("disabled");
				$(this).val(0);
				$("#packingOhter").val($("#quantity").val());
			});
		}
		$(".packing").on('blur',function(){
			if(Number($(this).val())<1||$(this).val()==''){
				$(this).val(0);
				return;
			}
			var packingOhter=Number($("#packingOhter").val());
			var quantity=Number($("#quantity").val())
			var packNum=Number($(this).val());
			//当前输入框大于总数
			if(packNum>quantity){
				$(this).val(0);
				$("#packingOhter").val(quantity-withoutOhter());
			}else{
					//其他大于0
					if(packingOhter>0){
						if(withoutOhter()>quantity){
							$(this).val(quantity-getPackingNum()+packNum);
						}else{
							$("#packingOhter").val(quantity-withoutOhter());
						}
					}else{
						if(quantity<getPackingNum()){
							$(this).val(quantity-getPackingNum()+packNum);
						}else{
							$("#packingOhter").val(quantity-withoutOhter());
						}	
					}
				}
			
		});
		function getPackingNum(){
			var packTatal=0;
			$(".packing").each(function(i,cm){
				packTatal+=Number($(this).val());
			});
			packTatal=packTatal;
			return Number(packTatal);
		}
		function withoutOhter(){
			var packTatal=0;
			$(".packing").each(function(i,cm){
				packTatal+=Number($(this).val());
			});
			packTatal=packTatal-Number($("#packingOhter").val());
			return Number(packTatal);
		}
	</script>
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-预约订单
</script>	
</html>
