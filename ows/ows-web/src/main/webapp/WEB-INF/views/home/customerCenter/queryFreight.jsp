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
					  <span class="input-group-addon" id="sizing-addon1">起运站：</span>
					  <input name="sourceDistCode" id="sourceDistCode" type="text" class="form-control" aria-describedby="basic-addon1">
					   <input name="sourceDistValue" id="sourceDistValue"  style="display: none">
					</div>
					<div class="input-group input-group-lg">
					  <span class="input-group-addon" id="sizing-addon1">到达站：</span>
					  <input name="destDistCode" id="destDistCode" type="text" class="form-control" aria-describedby="basic-addon1">
					   <input name="destDistValue" id="destDistValue"  style="display: none">
					</div>
					<button class="btn" style="width: 90px;" onclick="fn_search()">查询</button>
					<div class="clear"></div>
					<table>
						<thead>
							<tr><th style="background-color: #fcb814;color: #fff;">计费类型</th><th style="background-color: #fcb814;color: #fff;">价格</th>
							<!-- <th>价格</th> -->
							</tr>
						</thead>
						<tbody style="display:none" id="tb_result">
							<tr>
							<!-- author:zb
					  time:12-23 16:17
					 增加'td_name_1,去掉默认值' -->
								<td id='td_name_1'>重量</td><td id="weightPrice"></td>
							</tr>
						
							<tr>
								<td id="td_name_2">体积</td><td id="volumnPrice"></td>
							</tr>
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
							<tr id="beizhu" style="display: none">
								<td colspan="3" style="color:red" id="td_remark"></td>
							</tr>
						</tbody>
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
			fn_get_menu("ddwl_khzx");
			 $.ajax({
	 			url : '${base}/customer/getSearchSourceCity/',
	 			async : false,
	 			success : function(data){
	 				 $('#sourceDistCode').typeahead(
			            {
			                source:data.data.list ,
			                items:50,
			                itemSelected: displayTxt_s
			            });
	 			},
	 			error : function() {
	 				alert('错误11');
	 			},
	 			dataType : "json"
	 		});
			 $.ajax({
	 			url : '${base}/customer/getSearchDestCity/',
	 			async : false,
	 			success : function(data){
	 				$('#destDistCode').typeahead(
			            {
			                source:data.data.list ,
			                items:50,
			                itemSelected: displayTxt_d
			            });
	 			},
	 			error : function() {
	 				alert('错误22');
	 			},
	 			dataType : "json"
	 		}); 
			 function displayTxt_s(item, val, text) {
				 $('#sourceDistValue').val(val);
	        }
			 function displayTxt_d(item, val, text) {
				 $('#destDistValue').val(val);
	        }
			var  sourceDistValue='${sourceDistValue}';
			var  destDistValue='${destDistValue}';
			if(sourceDistValue&&destDistValue){
				$('#sourceDistCode').val('${sourceDistCode}');
				$('#destDistCode').val('${destDistCode}');
				$('#sourceDistValue').val(sourceDistValue);
				$('#destDistValue').val(destDistValue);
				fn_search();
			}
		});
		
		
		function fn_search(){
			 var sourceDistCode= $('#sourceDistValue').val();
			 var destDistCode= $('#destDistValue').val();
			 if(!sourceDistCode){
				 alert("起运站不能为空");
				 return;
			 }
			 if(!destDistCode){
				 alert("到达站不能为空");
				 return;
			 }
			 $.ajax({
	 			url : '${base}/customer/getFreight/',
	 			async : false,
	 			data:{sourceDistCode:sourceDistCode,destDistCode:destDistCode},
	 			success : function(data){
	 			 	if(data.success){
	 			 		var weightPriceStr=data.data.weightPrice*1000+'元每吨';
	 			 		var volumnPriceStr= data.data.volumnPrice+"元每立方";
	 			 		$("#weightPrice").text(weightPriceStr);
	 			 		$("#volumnPrice").text(volumnPriceStr);
	 			 		$('#tb_result').show();
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
</script>	
</html>
