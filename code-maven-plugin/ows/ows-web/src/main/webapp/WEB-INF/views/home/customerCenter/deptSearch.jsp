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
			<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img  src="${base}/images/ban16.jpg"alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}</h4>
				<div class="chanpinfuwu">
					<div>
						<span style="font-size: 14px;font-weight: 900;padding-right: 20px">选择您要查询的位置：</span>
						<span>省份：</span><select name="province" id="p" class="form-control" onchange="changeP()" style="width:150px;display: inline-block;">
							<option value="中国" selected="selected">请选择</option>
						</select>&nbsp;&nbsp;&nbsp;
						城市：<select name="city" id="c" class="form-control" onchange="changeC()" style="width:150px;display: inline-block;padding-left: 10px;">
								<option value="">请选择</option>
							</select>&nbsp;&nbsp;&nbsp;
					
					</div>
					<div style="float:left">
						<span style="font-size: 14px;font-weight: 900;padding-right: 20px;float:left;margin-top:15px;">按关键字查询：</span>
						<input id="keyword" type="text"  class="form-control" style="width:120px;float:left;display: inline-block;margin-top:10px;">	
						<button style="width: 130px;line-height: 35px; background-color: #fcb814;color: #fff;padding: 0; border: 0;font-size: 16px;margin-left: 20px;margin-top: 10px;float:left" onclick="fn_search()">查询</button>	
					</div>
					<hr style="float:left;">
					<!-- 地图 -->
					<div id="ditu" style="float:left">
						<div class="siteCount"></div>
						<div style="text-align: right;padding-right: 40px;margin-bottom: 10px;font-size: 14px;"><a href="javascript:void(0)" onclick="liebiao()">切换至列表模式</a></div>
						<div id="map-1" style="width:840px;height:630px;border:  2px solid #fcb814"></div>
					</div>
					<div id="liebiao" style="display: none;float:left">
						<div class="siteCount"></div>
						<div style="text-align: right;padding-right: 40px;margin-bottom: 10px;font-size: 14px;"><a href="javascript:void(0)" onclick="ditu()">切换至地图模式</a></div>
						<div id="liebiaoList" style="width:810px;border:  2px solid #B22923;padding-bottom: 10px">
							
						</div>
					</div>
				</div>
			</div>	
		</div>
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BjaWvU1ZF9VDPM7DnjxZLGzQ"></script>
	<script type="text/javascript">
	var cityCode='${cityCode}';
	var provinceCode='${provinceCode}';
		$(function(){
			map(null,"中国",null,null);
			fn_get_menu("ddwl_khzx");
				$.ajax({  
					url:'${base}/customer/getProvice',
					type:'post',
					dataType:'json',
					error:function(){
						alert("正在维护,请稍后尝试");
					}, 
					success:function(data){ 
						if(data!=null){
							var obj=document.getElementById('p');
							for(var i = 0;i<data.length;i++){
								obj.options.add(new Option(data[i].name,data[i].code));
							}
						}
						
						if(cityCode){
							$("#p").val(provinceCode);
							SelectCity(provinceCode,cityCode);
						}
					}  
				});
				

		})
		//选择省份触发
		function changeP(){
			//调用地图
			var pName='';
			var pCode='';
			if($("#p").children('option:selected').html()!='请选择'){
				pName=$("#p").children('option:selected').html();
				pCode= $("#p").val();
			}
			map(pCode,pName,null,null);
			
			var p = $("#p").val();
			var c = $("#c").val();
			if(p!='0'){
				getCityByP(p);
			}else{
				$("#c").empty();
				$("#d").empty();
				$("#c").append("<option value='0'>请选择</option>");
				$("#d").append("<option value='0'>请选择</option>");
				map(null,"中国",null,null);
			}
		}
		function getCityByP(pCode){
			$.ajax({  
				url:'${base}/customer/getCity',
				data:{'provinceCode':pCode},
				type:'post',
				dataType:'json',
				error:function(){
					alert("正在维护,请稍后尝试");
				}, 
				success:function(data){ 
					if(data!=null){
						$("#c").empty();//清空城市
						//$("#d").empty();//清空区县
						$("#c").append("<option value='0'>请选择</option>");
						$("#d").append("<option value='0'>请选择</option>");
						for(var i = 0;i<data.length;i++){
							$("#c").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
						}
					}
				}  
			});
		}
		function SelectCity(pCode,cityCode){
			$.ajax({  
				url:'${base}/customer/getCity',
				data:{'provinceCode':pCode},
				type:'post',
				dataType:'json',
				error:function(){
					alert("正在维护,请稍后尝试");
				}, 
				success:function(data){ 
					data=data;
					if(data!=null){
						$("#c").empty();//清空城市
						//$("#d").empty();//清空区县
						$("#c").append("<option value='0'>请选择</option>");
						$("#d").append("<option value='0'>请选择</option>");
						for(var i = 0;i<data.length;i++){
							$("#c").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
						}
						$("#c").val(cityCode);
						var pName=$("#p").children('option:selected').html();
						map(pCode,pName,cityCode,null);
					}
				}  
			});
		}
		//选择城市触发
		function changeC(){
			var pName='';
			var pCode='';
			var cName='';
			var cCode='';
			if($("#p").children('option:selected').html()!='请选择'){
				pName=$("#p").children('option:selected').html();
				pCode= $("#p").val();
			}
			if($("#c").children('option:selected').html()!='请选择'){
				cName=$("#c").children('option:selected').html();
				cCode= $("#c").val();
			}
			map(pCode,pName,cCode,cName)
			
		}
		
		
	
		
		function getBoundary(address){       
			var bdary = new BMap.Boundary();
			bdary.get(address, function(rs){       //获取行政区域
				map.clearOverlays();        //清除地图覆盖物       
				var count = rs.boundaries.length; //行政区域的点有多少个
				if (count === 0) {
					alert('未能获取当前输入行政区域');
					return ;
				}
		      	var pointArray = [];
				for (var i = 0; i < count; i++) {
					var ply = new BMap.Polygon(rs.boundaries[i], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.9, fillColor: "blue",fillOpacity: 0.1}); //建立多边形覆盖物
		          
					map.addOverlay(ply);  //添加覆盖物
					pointArray = pointArray.concat(ply.getPath());
		           
				}    
				map.setViewport(pointArray);    //调整视野  
			});   
		}
		function fn_search(){
			
			var pName='';
			var pCode='';
			var cName='';
			var cCode='';
			if($("#p").children('option:selected').html()!='请选择'){
				pName=$("#p").children('option:selected').html();
				pCode= $("#p").val();
			}
			if($("#c").children('option:selected').html()!='请选择'){
				cName=$("#c").children('option:selected').html();
				cCode= $("#c").val();
			}
			var con = $("#keyword").val();//查询条件
			map(pCode,pName,cCode,cName,con);
			
		}
		function checkPIsEmpty(p){
			if(p=="中国"||p==''||p=='null'||p===undefined||p==null|p=='请选择'){
				return true;
			}else{
				return false;
			}
		}
		function checkIsEmpty(str){
			if(str==''||str=='null'||str===undefined||str==null){
				return true;
			}else{
				return false;
			}
		}
		var isSearch = false;//是都查询过过
		//创建地图
		function map(pCode,pName,cCode,cName,deptName){
			isSearch = true;
			var address = pName;
			if(cName!=null){
				address+=cName;
			}
			var map = new BMap.Map("map-1");
			if(checkIsEmpty(address)){
				address="中国";
			}
			//1.在container容器中创建一张地图
			map.centerAndZoom(address, 5);
			//4.激活滚轮调整大小
			map.enableScrollWheelZoom();
			//5.添加控件
			map.addControl(new BMap.NavigationControl());//(左上角缩放功能)
			map.addControl(new BMap.MapTypeControl());//视图类型(地图、卫星、三维)
			map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
			//添加标注
			if(pCode||cCode||deptName){
				$.ajax({
					url:'${base}/customer/getLeague',
					type:'post',
					data:{'province':pCode,'city':cCode,'deptName':deptName},
					dataType:'json',
					error:function(){
						alert("系统异常,请稍后尝试");
					}, 
					success:function(data){
						data=data.data;
						if(checkPIsEmpty(p)&&checkIsEmpty(con)){
							$("#liebiaoList").html("");
							$(".siteCount").html("已查询到<b style='color:blue;font-size:14px'> "+data+" </b>个网点信息");
						}else{
							$(".siteCount").html("已查询到<b style='color:blue;font-size:14px'> "+data.length+" </b>个网点信息");
							if(data.length==0){
								//1.在container容器中创建一张地图
								map.centerAndZoom(address, 5);
								$("#liebiaoList").html("");
							}else{
								var html ="<table style='text-align: center'>";
								html+="<tr style='text-align: center;height:35px;background:#B22923;color:#ffffff;font-weight:900'>";
								html+="<td style='width: 100px'>网点名称</td>";
								html+="<td style='width: 100px'>联系人</td>";
								html+="<td style='width: 150px'>联系电话</td>";
								html+="<td style='width: 150px'>网点地址</td>";
							
								if(data.length!=0){
									map.clearOverlays();
									var bdary = new BMap.Boundary();
									bdary.get(address, function(rs){       //获取行政区域
										//map.clearOverlays();        //清除地图覆盖物       
										var count = rs.boundaries.length; //行政区域的点有多少个
										if (count === 0) {
											alert('未能获取当前行政区域');
											return ;
										}
								      	var pointArray = [];
										for (var i = 0; i < count; i++) {
											var ply = new BMap.Polygon(rs.boundaries[i], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.9, fillColor: "blue",fillOpacity: 0.1}); //建立多边形覆盖物
											map.addOverlay(ply);  //添加覆盖物
											pointArray = pointArray.concat(ply.getPath());
										}
										if(address!='中国'){
											map.setViewport(pointArray);    //调整视野  
										}
									});  
									$(data).each(function(i,cm){
										//author:zb
										  //time:12-23 17:22
										  //如果为空设置为空字符串
										if(cm['othorMessage']==null){
											cm['othorMessage'] = '';
										}
										html+="<tr style='height:50px;'>"
										html+="<td style='border-bottom:1px dashed #fcb814;font-weight:900'>"+cm['name']+"</td>";
										html+="<td style='border-bottom:1px dashed #fcb814'>"+cm['linkMan']+"</td>";
										html+="<td style='border-bottom:1px dashed #fcb814'>"+cm['linkPhone']+"</td>";
										html+="<td style='border-bottom:1px dashed #fcb814;padding-left:10px'>"+cm['address']+"</td>";
										//author:zb
										  //time:12-23 17:22
										
										//2.创建坐标点(经纬度)
										var point = new BMap.Point(cm['longitude'],cm['latitude']);
										var myIcon = new BMap.Icon("${base}/images/marker.png", new BMap.Size(25,40));
										var marker = new BMap.Marker(point,{icon:myIcon});
										map.addOverlay(marker);
										var str = '<table>'+
													'<tr>'+
														'<td>网点名称：</td>'+
														'<td>'+cm['name']+'</td>'+
													'</tr>'+
													'<tr>'+
														'<td>联系人：</td>'+
														'<td>'+cm['linkMan']+'</td>'+
													'</tr>'+
													'<tr>'+
														'<td>联系电话：</td>'+
														'<td>'+cm['linkPhone']+'</td>'+
													'</tr>'+
													'<tr>'+
														'<td>网点地址：</td>'+
														'<td>'+cm['address']+'</td>'+
													'</tr>'+
												'</table>';
										
										//标注所显示的详细信息
								        var infoWindow = new BMap.InfoWindow(str);
										//点击标注（红点）触发事件
										 marker.addEventListener("mouseover",function(e){
											 this.openInfoWindow(infoWindow);}
										 );
										
					                });
								}else{
									html+="<tr style='text-align:center;height:100px;font-size:14px'><td colspan='6'>该区域暂无网点信息</td></tr>";
								}
								html+="</table>";
								$("#liebiaoList").html(html);
							} 
						}
					}
				});
			}
		}
		//列表
		function liebiao(){
			$("#ditu").hide();
			$("#liebiao").show();
		}
		function ditu(){
			$("#liebiao").hide();
			$("#ditu").show();
			if(isSearch){
				fn_search();
			}
			isSearch =false;
		}
				
		</script>
	
</body>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-网点查询
</script>	
</html>
