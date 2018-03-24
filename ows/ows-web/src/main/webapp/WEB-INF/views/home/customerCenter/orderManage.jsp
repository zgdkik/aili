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
   overflow:auto;height:527px;
   width:885px;margin-left: 0px; margin-top:0px;
  }
  .gdt td{height:30px; font-size:12px; font-family:"黑体"; color:#666666; text-align: center;cursor: pointer;}
  .gdt th{height:35px; background-color:#FFCC00;color:#FFFFFF; text-align: center;border-right:#FFFFFF solid 2px;border-top:#FFFFFF solid 2px;}
  .gdt table{border-collapse:collapse;width:2200px}
  body {
	scrollbar-arrow-color: #666; /*图6,三角箭头的颜色*/
	scrollbar-face-color: #FFCC00; /*图5,立体滚动条的颜色*/
	scrollbar-3dlight-color: #666; /*图1,立体滚动条亮边的颜
	
	色*/
	scrollbar-highlight-color: #666; /*图2,滚动条空白部分的
	
	颜色*/
	scrollbar-shadow-color: #999; /*图3,立体滚动条阴影的颜
	
	色*/
	scrollbar-darkshadow-color: #666; /*图4,立体滚动条强阴
	
	影的颜色*/
	scrollbar-track-color: #666; /*图7,立体滚动条背景颜色*/
	
	scrollbar-base-color:#f8f8f8; /*滚动条的基本颜色*/
	Cursor:url(mouse.cur); /*自定义个性鼠标*/
  }
  .edit_btn{border:#FFCC00 solid 1px; background:#FFFFFF; color:#999999; cursor:pointer;padding:2px;margin-left:2px}
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
				<h4 id="h_title">${title}</h4>
				<div class="gdt">
					<table class="result_jijian">
					  <thead>
					      <tr>
					        <th width="30" rowspan="2">操作</th>
					        <th width="40" rowspan="2">状态</th>
					        
					        <th colspan="4">寄件人信息</th>
					        <th colspan="3">收件人信息</th>
					        <th colspan="5">货物信息</th>
					        <th colspan="4">服务信息</th>
					   
					      </tr>
						  <tr>
							  <th width="40">姓名</th>
							  <th width="50">电话</th>
							  <th width="180">地址</th>
							  <th width="50">下单时间</th>
							  
							  <th width="40">姓名</th>
							  <th width="50">电话</th>
							  <th width="180">地址</th>
							  
							  <th width="40">名称</th>
							  <th width="40">重量</th>
							  <th width="40">体积</th>
							  <th width="40">件数</th>
							  <th width="60">目的地</th>
							  
							  <th width="40">代收款</th>
							  <th width="40">保价费</th>
							  <th width="40">代收运费</th>
							  <th width="150">托运备注</th>
							  
							
						  <tr>
					  </thead>
					  <tbody id="tb_result">
					      
					  </tbody>
					  
					</table>
				    <div class="fenye" style="bottom: 0px;position: absolute;right: 0px;">
						<nav>
						  <ul class="pagination">
						    
						  </ul>
						</nav>
				   </div>		
			   </div>
				
		
			</div>	
		</div>
	</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<script type="text/javascript">
		var page=1;
		var rows=10;
		$(function(){
			fn_get_menu("ddwl_khzx");
			 fn_load();
		});
		function fn_load(){
			$.ajax({  
				url:'${base}/order/getOrderByUser',
				type:"post",
				data:{
					page:page,
					rows:rows
				},
				dataType:"json",
				success:function(data){
					var total = data.data.total;
			        if(total>0){
			        	  var totalPage =0;
				          if(total%rows>0){totalPage = parseInt(total/rows)+1;}else{totalPage = parseInt(total/rows);}
				          var pageStr = '<li><a onclick="fn_set_page(1)" aria-label="Previous" style="cursor: pointer"><span aria-hidden="true">&laquo;</span></a></li>';
				          for(i=((page-5)>0?(page-5):1);i<page&&i>0;i++){
				        	  pageStr+='<li><a onclick="fn_set_page('+i+')" style="cursor: pointer">'+i+'</a></li>';
				          }
				          pageStr+='<li><a onclick="fn_set_page('+page+')" style="color:red;cursor: pointer">'+page+'</a></li>';
				          for(j=page+1;j<page+6&&j<=totalPage;j++){
				        	  pageStr+='<li><a onclick="fn_set_page('+j+')" style="cursor: pointer">'+j+'</a></li>';
				          }
				          pageStr+='<li><a onclick="fn_set_page('+totalPage+')" aria-label="Next" style="cursor: pointer"><span aria-hidden="true">&raquo;</span></a></li>';
				          $('.pagination').html(pageStr);
			        }else{ 
			        	$('#tb_result').html('');
			        	 return; 
			        }
			          
					var str = '';
					$(data.data.list).each(function(i,cm){
						var dealFlg="";
						if(cm['dealFlg']==0){
							dealFlg="未处理";
						}else if (cm['dealFlg']==1){
							dealFlg="已处理";
						}
						var sendTm =  new Date(cm['sendTm']).Format("yyyy-MM-dd hh:mm:ss");  
						str+='<tr>'+
								'<td>'+
									/* '<input type="button" value="修改" class="edit_btn" onclick="fn_upd(\''+cm['orderId']+'\')"/>'+ */
									'<input type="button" value="删除" class="edit_btn" onclick="fn_del(\''+cm['orderNo']+'\',\''+cm['dealFlg']+'\')"/>'+
								'</td>'+
								/* '<td>新增</td>'+ */
								'<td>'+dealFlg+'</td>'+
								'<td>'+cm['consignorName']+'</td>'+
								'<td>'+cm['consignorPhone']+'</td>'+
								'<td>'+cm['consignorAddr']+'</td>'+
								'<td>'+sendTm+'</td>'+
								'<td>'+cm['addresseeName']+'</td>'+
								'<td>'+cm['addresseePhone']+'</td>'+
								'<td>'+cm['addresseeAddr']+'</td>'+
								'<td>'+cm['goodsName']+'</td>'+
								'<td>'+cm['weightQty']+'</td>'+
								'<td>'+cm['volume']+'</td>'+
								'<td>'+cm['quantity']+'</td>'+
								'<td>'+cm['destZoneCode']+'</td>'+
								
								'<td>'+(cm['collectionFee']==null?'':cm['collectionFee'])+'</td>'+
								'<td>'+(cm['valuationFee']==null?'':cm['valuationFee'])+'</td>'+
								'<td>'+(cm['expressFee']==null?'':cm['expressFee'])+'</td>'+
								'<td>'+(cm['remark']==null?'':cm['remark'])+'</td>'+
						     '</tr>';
					});
					$('#tb_result').html(str);
					$("#tb_result tr:odd").css("background",'#F7F9D7');
				}            
			}); 
		}
		function fn_set_page(_page){
			page=_page;
			fn_load();
		}
		function fn_del(orderNo,dealFlg){
			if(dealFlg==1){
				alert("订单已受理，无法删除");
				return;
			}
			$.ajax({  
				url:'${base}/order/deleteOrder',
				type:"post",
				data:{
					orderNo:orderNo,
				},
				dataType:"json",
				success:function(data){
					if(data.success){
						alert("删除订单成功");
						fn_load();
					}else{
						alert(data.msg);
					}
				}            
			}); 
		}
	</script>
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-预约订单
</script>	
</html>
