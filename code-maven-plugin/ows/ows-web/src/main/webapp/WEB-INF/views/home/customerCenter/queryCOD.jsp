<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
	<style type="text/css">
	  .billresult{margin-top:485px;background-color: #FFF}
	  .tb_title{border-collapse:collapse;}
	  /*.tb_title td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;}*/
	  .tb_title tbody td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;}
	 /* .tb_title thead td{height:35px; background-color:#FFCC00;color:#FFFFFF; text-align: center;border-right:#FFFFFF solid 2px;*/
	  .tb_title thead td{height:35px; background-color:#AB5B5A;color:#fff; text-align: center;border-right:#FFFFFF solid 2px;}
	  
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
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img  src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}</h4>
				<div class="jiageshixiao">
					<div class="input-group input-group-lg">
					  <span class="input-group-addon" id="sizing-addon1">运单号/货单号：</span>
					  <input name="waybillNo" id="waybillNo" type="text" placeholder="请输入运单号或货单号"  class="form-control" aria-describedby="basic-addon1" style="width: 200px">
					</div>
					<button class="btn" style="width: 90px;" onclick="fn_search()">查询</button>
					<div class="clear"></div>
					<table>
						<thead>
							<tr><th style="background-color: #fcb814;color: #fff;">运单号</th>
							<!-- <th style="background-color: #fcb814;color: #fff;">货物名称</th> -->
							<th style="background-color: #fcb814;color: #fff;">代收款</th>
							<th style="background-color: #fcb814;color: #fff;">操作状态</th>
							<th style="background-color: #fcb814;color: #fff;">转款时间</th>
							<!-- <th>价格</th> -->
							</tr>
						</thead>
						<tbody style="display:none" id="tb_result">
							
						
							<!--
							<tr>
								<td>产品3</td><td id="td_times_3"></td><td id="td_price_3"></td>
							</tr>
							<tr>
								<td>产品4</td><td id="td_times_4"></td><td id="td_price_4"></td>
							</tr>
							<tr>
								<td>产品5</td><td id="td_times_5"></td><td id="td_price_5"></td>
							</tr>
						-->
							
						</tbody>
						<tr id="beizhu" style="display: none">
							<td colspan="3" style="color:red" id="td_remark">无代收货款信息,请验证运单号或联系客服</td>
						</tr>
					</table>
				</div>
			</div>	
		</div>
			
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/resources/js/bootstrap-typeahead.js?${version}"></script>
</body>
	<script type="text/javascript">
		$(function(){
			var waybillNo='${waybillNo}';
			if(waybillNo){
				$('#waybillNo').val(waybillNo);
				fn_search();
			}
		})
		function fn_search(){
			 var waybillNo= $('#waybillNo').val().trim();
			 if(!waybillNo){
				 alert("运单号/货单号不能为空");
				 return;
			 }
			/*  if(!/(\d){12}/.test(waybillNo)&&!/(\d){8}/.test(waybillNo)){
					alert("请输入12位运单号或8位货单号");
					$('#waybillNo').focus();
					return;
				} */
			 $.ajax({
	 			url : '${base}/customer/getCOD/',
	 			async : false,
	 			data:{waybillNo:waybillNo},
	 			success : function(data){
	 			 	if(data.success){
	 			 		data=data.data;
	 			 		if(data.success){
	 			 			var CODS= data.dataList;
	 			 			if(CODS.length>0){
	 			 				var codStr="";
			 			 		for(var i=0;i<CODS.length;i++){
			 			 			var paystate="";
			 			 			if(CODS[i].paymentState==0){
			 			 				paystate="未转款";
			 			 			}else{
			 			 				paystate="已转款";
			 			 			}
			 			 			codStr+="<tr>"+
									"<td>"+CODS[i].waybillNo+"</td>"+
									/* "<td>"+CODS[i].consName+"</td>"+ */
									"<td>"+CODS[i].outFeeAmt+"元</td>"+
									"<td>"+paystate+"</td>"+
									"<td>"+CODS[i].paymentTm+"</td>"+
								"</tr>";
			 			 		}
			 			 		$("#tb_result").html(codStr);
			 			 		$("#tb_result").show();
			 			 		$("#beizhu").hide();
	 			 			}else{
	 			 				$("#beizhu").show();
	 			 				$("#tb_result").hide();
	 			 			}
		 			 		
	 			 		}else{
	 			 			alert(data.error);
	 			 		}
	 			 	}else{
	 			 		alert(data.msg);
	 			 	}
	 			},
	 			error : function() {
	 				alert('错误');
	 			},
	 			dataType : "json"
	 		}); 
		}
		
	</script>
	<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-货物查询
		fn_get_menu("ddwl_khzx");
</script>	
</html>
