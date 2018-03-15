		<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  contentType="text/html;charset=utf-8"  isELIgnored="true" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
		<%
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
		%>
		<script language="javascript">
		//如果焦点不在可输入的input/textarea上，则屏蔽backspace键
		document.onkeydown = function(){
			var oEvent = window.event;
			var ctl = document.activeElement;
			if(oEvent.keyCode != 8) //backspace---8
				return true;
			if( oEvent.keyCode==8 && (ctl.nodeName=='INPUT'||ctl.nodeName=='TEXTAREA')&& ctl.readOnly == false ){
				return true;
			}else {
				return false;
			}	
		  }
		</script>
	
	<head>
	<base target="_self">
	<meta HTTP-EQUIV='pragma' CONTENT='no-cache'>
	<meta HTTP-EQUIV='Cache-Control' CONTENT='no-cache, must-revalidate'>
	<meta HTTP-EQUIV='expires' CONTENT='0'>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<title>司机送达订单有效信息统计</title>
	<link href="<%=path%>/css/styles.css" rel="Stylesheet" type="text/css">
	<link href="<%=path%>/css/text.css" rel="Stylesheet" type="text/css">
	<link href="<%=path%>/css/reset.css" rel="Stylesheet" type="text/css">
	<link href="http://api.map.baidu.com/res/12/bmap.css" rel="stylesheet"
		type="text/css">
	<script
		src="http://api.map.baidu.com/api?v=2.0&ak=hF4dbGT9jWPE9c4dboQQSM2f"></script>
	<script type="text/javascript"
		src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
	<script src="<%=path%>/js/laydate/laydate.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/complexCustomOverlay.js"></script>
	
	</head>
	
	<body>
					<hr>
				<div id="doing" style="position: relative; display: none;">
					<div class="movbg">
					</div>
					<div class="movtxt">
						<span class="spanText" ><img src="<%=path%>/images/loading.gif" /></span><span style="display:block; float:left">计算中...</span>
					</div>
				</div>
			<table  border="1" cellpadding="1" cellspacing="1" id="inputTable" class="table_1" style="width: 900px">
					<tr nowrap="nowrap" >
						<td nowrap="nowrap" class="td_1" colspan="3" width="50%">日期</td>
						<td nowrap="nowrap" class="td_3" colspan="3" width="50%">2015-02-05</td>
					</tr>
					<tr nowrap="nowrap" >
						<td nowrap="nowrap" class="td_1" colspan="3" width="50%">订单总数</td>
						<td nowrap="nowrap" class="td_3" colspan="3" width="50%"><c:out value="${ordersigninfoDTOListLength}"></c:out> </td>
						<input id="ordersigninfoDTOListLength" type="hidden" value="<c:out value='${ordersigninfoDTOListLength}'></c:out>">
					</tr>
					<tr nowrap="nowrap" >
						<td nowrap="nowrap" class="td_1" width="25%">送达地点距司机历史轨迹最近点两公里范围内订单总数</td>
						<td nowrap="nowrap" class="td_3" id="EffectiveOrderLength"  width="15%"></td>
						<input id="EffectiveOrderLengthHidden" type="hidden" >
						<td nowrap="nowrap" class="td_1" width="15%">无司机轨迹订单总数</td>
						<td nowrap="nowrap" class="td_3" id="noOrderLength" width="15%"></td>
						<td nowrap="nowrap" class="td_1" width="15%">有司机轨迹订单总数</td>
						<td nowrap="nowrap" class="td_3" id="OrderLength" width="15%"></td>
						<input id="OrderLengthHidden" type="hidden" >
						<input id="noOrderLengthHidden" type="hidden" >
					</tr>
					<tr nowrap="nowrap" >
						<td nowrap="nowrap" class="td_1" colspan="3" width="50%">百分比(订单无司机轨迹视为无效)</td>
						<td nowrap="nowrap" class="td_3" id="baifenbi" colspan="3" width="50%"></td>
					</tr>
					
			</table>
			<hr>				
			<table  border="1" cellpadding="1" cellspacing="1" id="inputTable" class="table_1">
					<tr nowrap="nowrap">
						<td nowrap="nowrap" class="td_1">距离(米)</td>
						<td nowrap="nowrap" class="td_1">OrderID</td>
						<td nowrap="nowrap" class="td_1">OrderNo</td>
						<td nowrap="nowrap" class="td_1">CargoID</td>
						<td nowrap="nowrap" class="td_1">CargoPhoneNumber</td>
						<td nowrap="nowrap" class="td_1">CargoName</td>
						<td nowrap="nowrap" class="td_1">CargoEnterprise</td>
						<td nowrap="nowrap" class="td_1">CargoStatus</td>
						<td nowrap="nowrap" class="td_1">CargoOrders</td>
						<td nowrap="nowrap" class="td_1">CargoGroup</td>
						<td nowrap="nowrap" class="td_1">CargoCatalog</td>
						<td nowrap="nowrap" class="td_1">CargoAgent</td>
						<td nowrap="nowrap" class="td_1">CargoAgentPhoneNumber</td>
						<td nowrap="nowrap" class="td_1">CargoAgentID</td>
						<td nowrap="nowrap" class="td_1">FromCity</td>
						<td nowrap="nowrap" class="td_1">FromAddr</td>
						<td nowrap="nowrap" class="td_1">FromPoint</td>
						<td nowrap="nowrap" class="td_1">EndCity</td>
						<td nowrap="nowrap" class="td_1">EndAddr</td>
						<td nowrap="nowrap" class="td_1">EndPoint</td>
						<td nowrap="nowrap" class="td_1">TruckType</td>
						<td nowrap="nowrap" class="td_1">Distance</td>
						<td nowrap="nowrap" class="td_1">Cost</td>
						<td nowrap="nowrap" class="td_1">SystemCost</td>
						<td nowrap="nowrap" class="td_1">Immediate</td>
						<td nowrap="nowrap" class="td_1">UseTime</td>
						<td nowrap="nowrap" class="td_1">CreateDate</td>
						<td nowrap="nowrap" class="td_1">PayType</td>
						<td nowrap="nowrap" class="td_1">OrderCost</td>
						<td nowrap="nowrap" class="td_1">AccountCost</td>
						<td nowrap="nowrap" class="td_1">CouponCost</td>
						<td nowrap="nowrap" class="td_1">OnlineCost</td>
						<td nowrap="nowrap" class="td_1">Status</td>
						<td nowrap="nowrap" class="td_1">TruckID</td>
						<td nowrap="nowrap" class="td_1">TruckPhoneNumber</td>
						<td nowrap="nowrap" class="td_1">TruckName</td>
						<td nowrap="nowrap" class="td_1">TruckStatus</td>
						<td nowrap="nowrap" class="td_1">TruckGroup</td>
						<td nowrap="nowrap" class="td_1">TruckCatalog</td>
						<td nowrap="nowrap" class="td_1">TruckAgent</td>
						<td nowrap="nowrap" class="td_1">TruckAgentPhoneNumber</td>
						<td nowrap="nowrap" class="td_1">TruckAgentID</td>
						<td nowrap="nowrap" class="td_1">TruckOrders</td>
						<td nowrap="nowrap" class="td_1">RushSeconds</td>
						<td nowrap="nowrap" class="td_1">SignDate</td>
						<td nowrap="nowrap" class="td_1">SignPoint</td>
						<td nowrap="nowrap" class="td_1">FromBeginDistance</td>
						<td nowrap="nowrap" class="td_1">FromEndDistance</td>
						<td nowrap="nowrap" class="td_1">FromPublishTime</td>
						<td nowrap="nowrap" class="td_1">FromRushTime</td>
						<td nowrap="nowrap" class="td_1">TruckRateType</td>
						<td nowrap="nowrap" class="td_1">CargoRateType</td>
						<td nowrap="nowrap" class="td_1">TruckRateContent</td>
						<td nowrap="nowrap" class="td_1">CargoRateContent</td>
						<td nowrap="nowrap" class="td_1">TruckCall</td>
						<td nowrap="nowrap" class="td_1">CargoCall</td>
					</tr>
				 <c:forEach items='${ordersigninfoDTOList}' var='trackOrders' varStatus='status'> 
					<tr nowrap="nowrap">
						<td nowrap="nowrap" class="td_3" id="<c:out value='${trackOrders.orderID}'></c:out>"></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.orderID}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.orderNo}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoID}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoPhoneNumber}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoName}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoEnterprise}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoStatus}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoOrders}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoGroup}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoCatalog}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoAgent}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoAgentPhoneNumber}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoAgentID}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromCity}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromAddr}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromPoint}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.endCity}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.endAddr}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.endPoint}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckType}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.distance}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cost}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.systemCost}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.immediate}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.useTime}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.createDate}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.payType}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.orderCost}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.accountCost}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.couponCost}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.onlineCost}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.status}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckID}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckPhoneNumber}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckName}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckStatus}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckGroup}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckCatalog}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckAgent}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckAgentPhoneNumber}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckAgentID}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckOrders}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.rushSeconds}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.signDate}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.signPoint}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromBeginDistance}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromEndDistance}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromPublishTime}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.fromRushTime}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckRateType}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoRateType}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckRateContent}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoRateContent}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.truckCall}"></c:out></td>
						<td nowrap="nowrap" class="td_3"><c:out value="${trackOrders.cargoCall}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
	
		<div id="map_canvas" align="center" style="width: 300; height: 200px ;display: none" >
	
		</div>
	
	</body>
	<script> 
	
	//关键点坐标对于的司机用户信息  
	var trackOrdersHistoricalInfo = [
		              <c:forEach items='${ordersigninfoDTOList}' var='trackOrders' varStatus='status'> 
		  	           <c:if test='${!status.first}' >,</c:if >
		  	           {
		  	        	orderID:'<c:out value="${trackOrders.orderID}"></c:out>',
		  	        	truckID:'<c:out value="${trackOrders.truckID}"></c:out>',
		  	        	signDate:'<c:out value="${trackOrders.signDate}"></c:out>',  
		  	        	signPoint:'<c:out value="${trackOrders.signPoint}"></c:out>',   
		  	        	useTime:'<c:out value="${trackOrders.useTime}"></c:out>',
		  	        	endPoint:'<c:out value="${trackOrders.endPoint}"></c:out>',
					  	         points:
					  	        	 [
					  	        	 <c:forEach items='${trackOrders.trackOrdersHistoricalList}' var='trackInfo' varStatus='status1'> 
						  	           <c:if test='${!status1.first}' >,</c:if >
						  	         {point:new BMap.Point(<c:out value="${trackInfo.latitude}"></c:out>,<c:out value="${trackInfo.longitude}"></c:out>)}
				  	        		 </c:forEach>	
					  	       ]
		          		} 
		              </c:forEach>
	         	];
	document.getElementById('doing').style.display = 'block';

	
	//设置地图初始化参数          
	var map = new BMap.Map('map_canvas');
	map.enableScrollWheelZoom();
	map.addControl(new BMap.NavigationControl());
	map.addControl(new BMap.ScaleControl());
	map.addControl(new BMap.OverviewMapControl({ isOpen: true }));
	map.centerAndZoom(new BMap.Point(121.494015, 31.228717), 12);
	
	var EffectiveOrderLength =[];
	var noOrderLength =[];//无轨迹司机订单总数
	
	//1 计算收货点到历史轨迹坐标距离
	//2 
	for(var i=0;i<trackOrdersHistoricalInfo.length;i++){
		var flag =false;//是否进入计算距离
		//单个司机收货点到历史轨迹
		var markerPointsEndDistance=[];//历史轨迹点到收货地址距离对象数组
		var endPoint = trackOrdersHistoricalInfo[i].endPoint.split(",");
		endPoint = new BMap.Point(endPoint[0],endPoint[1]);
		if(trackOrdersHistoricalInfo[i].points.length<=0){
			document.getElementById(trackOrdersHistoricalInfo[i].orderID).innerHTML="订单用车时间、签单时间内无司机轨迹信息";//卡车司机
			noOrderLength.push(trackOrdersHistoricalInfo[i]);
		}
		for(var j=0;j<trackOrdersHistoricalInfo[i].points.length;j++){
			flag = true;
			var pt = trackOrdersHistoricalInfo[i].points[j].point;
			 var distanceEnd = map.getDistance(pt, endPoint);
			 var pointDisEndObj={
					distance:distanceEnd,
					trackInfo:trackOrdersHistoricalInfo[i]
			 	} 
			 	markerPointsEndDistance.push(pointDisEndObj);
		}
		markerPointsEndDistance.sort(compare("distance")); 
		var minDistance ="";
		if(markerPointsEndDistance.length>0){
			markerPointsEndDistance[0];//最小距离 
			//最小距离 是否大于2公里 大于放到统计数组
			debugger;
			if(parseFloat(markerPointsEndDistance[0].distance)<=parseFloat(2000)){
				EffectiveOrderLength.push(markerPointsEndDistance[0]);
			}
			if(parseFloat(markerPointsEndDistance[0].distance)>=0){
				document.getElementById(trackOrdersHistoricalInfo[i].orderID).innerHTML=markerPointsEndDistance[0].distance;//卡车司机
			}
		}
		if(i==trackOrdersHistoricalInfo.length-1){
			document.getElementById('doing').style.display = 'none';
		}
	}
	document.getElementById("EffectiveOrderLength").innerHTML=EffectiveOrderLength.length;//
	document.getElementById("EffectiveOrderLengthHidden").value=EffectiveOrderLength.length;//
	document.getElementById("noOrderLength").innerHTML=noOrderLength.length;//
	document.getElementById("noOrderLengthHidden").value=noOrderLength.length;//
	document.getElementById("baifenbi").innerHTML=parseFloat(EffectiveOrderLength.length)/parseFloat(document.getElementById("ordersigninfoDTOListLength").value)*100+"%";//
	document.getElementById("OrderLength").innerHTML=(parseFloat(EffectiveOrderLength.length)-parseFloat(noOrderLength.length));//
	document.getElementById("OrderLengthHidden").value=(parseFloat(EffectiveOrderLength.length)-parseFloat(noOrderLength.length));//
	

	
	//计算百分比
	
	
	
	//定义一个对象比较器 
	function compare(propertyName) { 
		return function (object1, object2) { 
			var value1 = object1[propertyName]; 
			var value2 = object2[propertyName]; 
			if (value2 < value1) { 
				return 1; 
			} 
			else if (value2 > value1) { 
				return -1; 
			} 
			else { 
				return 0; 
				} 
		} 
	} 
	</script>
	<script type='text/javascript'>
	     <!--
	   function createTable() {
	       var t = document.createElement('table');
	       for (var i = 0; i < 2000; i++) {
	        var r = t.insertRow();
	        for (var j = 0; j < 5; j++) {
	         var c = r.insertCell();
	         c.innerHTML = i + ',' + j;
	        }
	       }
	       document.getElementById('table1').appendChild(t);
	      t.setAttribute('border', '1');
	   }
	   
	

	     //-->
	   </script>
	
	
	  <script>
	//运行加载中
	  function $(element){
	      return document.getElementById(element);
	  }

	  </script>
