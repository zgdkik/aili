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
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}</h4>
				<div class="zhuizong">
					<h4>最多输入10个运单号/货单号，请以逗号、空格或回车键分隔</h4>
					<textarea name="" rows="" cols="" class="form-control" placeholder="最多输入10个运单号/货单号，请以逗号、空格或回车键分隔" id="orderNum">531000020031</textarea>
					<div class="zhuizong-bottom"> 
						<span class="message" style="color:red;font-size: 15px;display: block;float: left;padding-left: 10px;padding-top: 5px;width: 200px;"></span>
						<button class="btn">追踪</button>
					</div>
				</div>
			</div>	
			
			<div class="billresult">
			
			   <!-- <table class="tb_title">
			      
				  <thead>
				  	  <tr>
				  	      <td colspan="6" style="border-bottom:#FFFFFF solid 2px; text-align: left; padding-left:10px;background-color: #fcb814;color: #fff;">签收记录>></td>
				  	  </tr>
					  <tr>
						  <td width="198" style="background-color: #fcb814;color: #fff;">运单编号</td>
						  <td width="198" style="background-color: #fcb814;color: #fff;">托运时间</td>
						  <td width="198" style="background-color: #fcb814;color: #fff;">签收人</td>
						  <td width="198" style="background-color: #fcb814;color: #fff;">签收时间</td>
						  <td width="198" style="background-color: #fcb814;color: #fff;">运单状态</td>
					  <tr>
				  </thead>
				  <tbody id="qsjl">
					  
				  </tbody>
			  </table> -->
			   
			  <br>
			  <div id="gzjl">
				  
			  </div>
	        </div>
		</div>
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript">
        var count = 0;
		$(function(){
			if('${waybillNos}'){
				$('#orderNum').val('${waybillNos}');
				fn_search();
			}
			fn_get_menu("ddwl_khzx"); 
			$('.btn').bind('click',fn_search); 
			
		})
		function fn_search(){
				$('#qsjl').html('');
				$('#gzjl').html('');
				var orderNum = $('#orderNum').val().trim();
				orderNum=orderNum.replace(/，/ig,','); 
				orderNum = getSplitString(orderNum);
				 
				var orderNums = orderNum.split(",");
				if(orderNums == null || orderNums.length<=0){
					$('.message').html('请输入运单号');
				}else if(orderNums.length>10){
					$('.message').html('每次最多可以查询10条运单');
				}else{
					fn_get_waybill(orderNums); 
				}  
		}
		//空白（空格、换行、tab）和逗号分隔的字符串，变成用逗号分隔  
		function getSplitString(str) {  
		    var arr = str.split(",");  
		  
		    var resources = "";  
		    for (var i = 0; i < arr.length; i++) {  
		        var arr1 = arr[i].split(/\s+/);  
		  
		        for (var j = 0; j < arr1.length; j++) {  
		            if (jQuery.trim(arr1[j]) != "") {  
		                resources += jQuery.trim(arr1[j]) + ",";  
		            }  
		        }  
		    }  
		  
		    resources=resources.substring(0,(resources.length-1));
		    return resources;  
		} 
		 
		function fn_get_waybill(orderNum){
			 
			$.ajax({
				  type: "GET",
				  url: "${base}/customer/getwaybill?dt=" + new Date().getTime(),
				  data:{
					  orderNums:orderNum.join("%")
				  },
				  dataType: "json",
				  success: function(data){
					  $('.message').html('');  
					  if(data.success){
						  
					  if(data.data==null|| data.data.length<=0){ 
						  $('.message').html("没有查到运单信息");
						  return;
					  }
					  var gzjl = '';
						$(data.data).each(function(i, cm) { 
							gzjl += '<table class="tb_title"> <thead> <tr> <td colspan="2" style="border-bottom:#666 solid 1px; text-align: left; font-size:15px;padding-left:10px;">'
									+ cm.waybillNo
									+ '&nbsp;&nbsp;&nbsp;>></td> </tr> <tr> <td width="230">操作时间</td> <td width="760">跟踪记录</td><tr> </thead> <tbody>';
									
							$(cm.trackList).each(function(j, cmm) {
								var operTm = "";
								if (cmm['operateTime']) {
									operTm = cmm['operateTime'].replace('T', ' ');
									operTm = operTm.replace('Z',' ')
								}
								var contentValue = "";
								if (cmm['trackInfo']) {
									contentValue = cmm['trackInfo'];
								}
								gzjl += '<tr><td>' + operTm + '</td><td style=" padding: 5px 10px; text-align: left;">' + contentValue + '</td></tr>';
							});
							
							gzjl += '</tbody></table><br>';
						});
						$('#gzjl').append(gzjl);
						$("#gzjl tbody tr:odd").css("background", '#F7F9D7');
						 
						
					  } else if(data.data!=null){
						  $('.message').html(data.data);
					  } else if(data.msg!=null){
						  $('.message').html(data.msg);
					  }else{
						  $('.message').html("未知错误");
					  }
				  }, 
				  error:function(data){ 
					  if(data.data!=null){
						  $('.message').html(data.data);
					  } else if(data.msg!=null){
						  $('.message').html(data.msg);
					  }else{
						  $('.message').html("未知错误");
					  }
				  }
			 });
		}
	</script>
	
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-货物追踪
</script>	
</html>
