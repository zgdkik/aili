	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  contentType="text/html;charset=utf-8"  isELIgnored="true" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title>订单历史轨迹</title>
    <link href="<%=path%>/css/styles.css" rel="Stylesheet" type="text/css">
    <link href="<%=path%>/css/reset.css" rel="Stylesheet" type="text/css">
	<link href="http://api.map.baidu.com/res/12/bmap.css" rel="stylesheet" type="text/css">  
	<script src="http://api.map.baidu.com/api?v=2.0&ak=hF4dbGT9jWPE9c4dboQQSM2f"></script>      
	<script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
	<script src="<%=path%>/js/laydate/laydate.js"></script>      
	<script type="text/javascript" src="<%=path%>/js/complexCustomOverlay.js"></script>
    <style type="text/css">
        #tackDiv {
            width: 100%;
            
        }
        #fset {
            width: 100%;
            
        }
        #track_order_controller{
            width: 100%;
        }
        #map_canvas {
            width: 100%;
            filter: alpha(Opacity=100);
            -moz-opacity: 1;
            opacity: 1;
            z-index: 10000;
            background-color: #9BCD9B;
        }
        #result {
            width: 100%;
            height: 30px;
            filter: alpha(Opacity=100);
            -moz-opacity: 1;
            opacity: 1;
            z-index: 10000;
            background-color: #9BCD9B;
            
        }        
        #queryDiv {
            width: 100%;
            height: 40px;
            filter: alpha(Opacity=100);
            -moz-opacity: 1;
            opacity: 1;
            z-index: 10000;
            background-color: #9BCD9B;
        }        
    </style>  
</head>

<body >
	<center>
		<div id="track_order_controller" align="center" >
			<div id="queryDiv" align="center" >
				<form name="queryForm" id="queryForm"  style="text-align:center;" action="/bseservice-gis-web/trackOrdersHistorical.jspa" method="post">
					<table align="center" width="600Px" cellpadding="5px" cellspacing="5px" style=" line-height: 28px;margin: 0px auto;">
						<input type="hidden" name="UserID" id="UserID" value="<c:out value="${trackOrdersHistoricalDTO.userID}"/>">
						<input type="hidden" name="orderStart" id="orderStart" value="<c:out value="${orderStartPoint}"/>">
						<input type="hidden" name="orderEnd" id="orderEnd" value="<c:out value="${orderEndPoint}"/>">
						<input type="hidden" name="signpoint" id="signpoint" value="<c:out value="${signpoint}"/>">
						<input type="hidden" name="signdate" id="signdate" value="<c:out value="${signdate}"/>">
						<tr>
							<td>
								开始时间:<input type="text" id="drivingStart" name="drivingStart" value="<c:out value="${trackOrdersHistoricalDTO.drivingStart}"/>" readonly="readonly" class="laydate-icon" id="start" style="width:150px; margin-right:10px;"/>
							<td>
								结束时间:<input type="text" id="drivingEnd" name="drivingEnd" value="<c:out value="${trackOrdersHistoricalDTO.drivingEnd}"/>" readonly="readonly" class="laydate-icon" id="start" style="width:150px; margin-right:10px;"/>
							</td>
							<td>
								<input type="button" value="查     询" onclick="queryTrack();">
							</td>
						</tr>
					</table>
				</form>
				<hr>
				<div id="doing" style="position: relative; display: none;">
					<div class="movbg">
					</div>
					<div class="movtxt">
						<span class="spanText" ><img src="<%=path%>/images/loading.gif" /></span><span style="display:block; float:left">加载中...</span>
					</div>
				</div>
				
			</div>
			<div id="result">
				<button id="run">播放行驶路径</button>
				<button id="stop">停止路径播放</button>
				<button id="pause">暂停路径播放</button>
				<button id="showAll" onclick="bdGEO(0)">查看所有途径地址</button>
				<button id="clearAll" onclick="clearAllAdress();">清除所有途径地址</button>
				<div id="doing" style="position: relative; display: none;">
					<div class="movbg">
					</div>
					<div class="movtxt">
						<span class="spanText" ><img src="<%=path%>/images/loading.gif" /></span><span style="display:block;">加载中...</span>
					</div>
				</div>
			</div>
			
			<div id="track_order_bottom_controller">
				<table align="center" height="100%" width="100%" style="border-color: green;border: 2px;border-width: 1px;">
					<tr nowrap="nowrap">
						<td  style=" vertical-align:top;border-color: green;border: 2px" id="track_order_left_td" nowrap="nowrap" width="265px">
								<table style="overflow-y:scroll;border:1px solid #9BCD9B;margin-top: 5px"  width="265px" height:="250px">
				                    <tr nowrap="nowrap" style="background-color:#9BCD9B;border:1px solid #9BCD9B;" height="30px"> 
				                        <td  colspan="2" align="center" height="30px">查询结果</td>
									</tr> 								
									<tr nowrap="nowrap" style="border:1px solid #9BCD9B;" >
				                        <td style="border:1px solid #9BCD9B;" height="30px" >订单开始时间:</td>
				                        <td style="border:1px solid #9BCD9B;" height="30px"><font color="green"><c:out value="${trackOrdersHistoricalDTO.drivingStart}" /></font></td>
									</tr> 
									<tr nowrap="nowrap" style="border:1px solid #9BCD9B;" >
				                        <td style="border:1px solid #9BCD9B;" height="30px" >送达时间:</td>
				                        <td style="border:1px solid #9BCD9B;" height="30px"><c:if test="${!empty trackOrdersHistoricalDTO.signdate}"> <font color="green"><c:out value="${trackOrdersHistoricalDTO.signdate}"/></font><br></c:if></td>
									</tr> 
									<tr nowrap="nowrap" style="border:1px solid #9BCD9B;" >
				                        <td style="border:1px solid #9BCD9B;" height="30px" >送达地址:</td>
				                        <td style="border:1px solid #9BCD9B;" height="30px" id="signAdress"></td>
									</tr> 
									<tr nowrap="nowrap" style="border:1px solid #9BCD9B;" >
				                        <td style="border:1px solid #9BCD9B;" height="30px" id="startPlaceDistance" colspan="2"></td>
									</tr> 
									<tr nowrap="nowrap" style="border:1px solid #9BCD9B;" >
				                        <td style="border:1px solid #9BCD9B;" height="30px" id="endPlaceDistance" colspan="2"></td>
									</tr> 
								</table>
								
							<div id="track_order_left_controller1"  style=" font-size:13px; line-height:21px; padding:10px; height: 500px;overflow-y:scroll; " >
								<table style="height: 500px;overflow-y:scroll;border:1px solid #9BCD9B;width:100%;" >
								    <thead>
				                    <tr nowrap="nowrap" style="background-color:#9BCD9B;border:1px solid #9BCD9B;">
				                        <td  colspan="4" align="center">该司机轨迹时间分布</td>
									</tr> 
				                    <tr nowrap="nowrap" style="border:1px solid #9BCD9B;">
				                        <td style="border:1px solid #9BCD9B;">序号</td>
				                        <td style="border:1px solid #9BCD9B;">时间</td>
				                        <td style="border:1px solid #9BCD9B;">到发货<br>公里</td>
				                        <td style="border:1px solid #9BCD9B;">到收货<br>公里</td>
									</tr> 
									</thead>                       
	              					<c:forEach items='${trackOrdersHistoricalList}' var='trackOrders' varStatus='status'> 
										<tr nowrap="nowrap">
											<td width="25%" nowrap="nowrap" align="right" style="border:1px solid #9BCD9B;">
												<c:out value='${status.index+1}'></c:out>
											</td>
											<td width="25%" nowrap="nowrap" style="border:1px solid #9BCD9B;" >
												<a  title="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${trackOrders.createDate}' />" href="javascript:void(0);" onclick="truckHistoricalShow(<c:out value='${trackOrders.latitude}'></c:out>,<c:out value='${trackOrders.longitude}'></c:out>,'<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${trackOrders.createDate}' />');"><font color=green><fmt:formatDate pattern='HH:mm:ss' value='${trackOrders.createDate}' /></font></a>
											</td>
											<td width="25%" nowrap="nowrap" style="border:1px solid #9BCD9B;" id="startDis<c:out value='${status.index}'></c:out>">
											</td>
											<td width="25%" nowrap="nowrap" style="border:1px solid #9BCD9B;" id="endDis<c:out value='${status.index}'></c:out>">
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</td>
						<td width="100%" id="map_canvas_td" nowrap="nowrap" >
							<div id="map_canvas"  style="float:right;"></div>
						</td>
					</tr>
				</table>
			</div>
			
		</div>

	</center>
</body>
<script> 
//导航结果面板鼠标操作
function truckHistoricalShow(latitude,longitude,createDate){
	pt =  new BMap.Point(latitude,longitude);
		clearAllAdress();
		myGeo.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			   var business ="";
			   if(rs.business&&rs.business!=null&&rs.business!=""){
				   business=" 附近有:"+rs.business ;
			   }else{
				   business=" 未检索到附近大厦或商圈！";
			   }
			   var txt ="地址:"+rs.address+business+" 时间："+createDate, mouseoverTxt = txt ;
			   <c:if test="${!empty orderEnd.latitude}">//订单结束坐标不为空
				var orderEndPoint = new BMap.Point(<c:out value="${orderEnd.latitude}"></c:out>,<c:out value="${orderEnd.longitude}"></c:out>);
				var orderStartPoint = new BMap.Point(<c:out value="${orderStart.latitude}"></c:out>,<c:out value="${orderStart.longitude}"></c:out>);
			   	txt =txt+" 到收货地址直线距离："+((map.getDistance(pt, orderEndPoint)).toFixed(2))/1000+' 公里。';//获取两点距离,保留小数点后两位
			    txt =txt+" 到发货地址直线距离："+((map.getDistance(pt, orderStartPoint)).toFixed(2))/1000+' 公里';//获取两点距离,保留小数点后两位
			    var mouseoverTxt = txt ;
			    var myCompOverlay = new ComplexCustomOverlay(pt, txt,mouseoverTxt);
			    map.addOverlay(myCompOverlay);
			    markers.push(myCompOverlay);
			    map.panTo(pt);
			    map.setViewport([pt]);
			   </c:if>
				
			   <c:if test="${empty orderEnd.latitude}">//订单结束坐标为空
				   var mouseoverTxt = txt ;
				    var myCompOverlay = new ComplexCustomOverlay(pt, txt,mouseoverTxt);
				    map.addOverlay(myCompOverlay);
				    markers.push(myCompOverlay);
			   </c:if>		 			        
			   
			}); 
}
</script>
<script> 
//
var lushu; 
var allArrPois=[];
//需要显示的关键点
var arrPois = [
              <c:forEach items='${trackOrdersHistoricalList}' var='trackOrders' varStatus='status'> 
  	           	 <c:if test='${!status.first}'>,</c:if>
  	           	 new BMap.Point(<c:out value="${trackOrders.latitude}"></c:out>,<c:out value="${trackOrders.longitude}"></c:out>)
              </c:forEach>
          ];
//关键点坐标对于的司机用户信息  
var trackOrdersHistoricalInfo = [
	              <c:forEach items='${trackOrdersHistoricalList}' var='trackOrders' varStatus='status'> 
	  	           <c:if test='${!status.first}' >,</c:if >
	  	           {
	  	        	 	sequenceID :"<c:out value='${trackOrders.sequenceID}'/>",
	  	        	 	userID  :'<c:out value="${trackOrders.userID}"></c:out>',
	  	        	 	latitude:'<c:out value="${trackOrders.latitude}"></c:out>',
	  	        	 	longitude:'<c:out value="${trackOrders.longitude}"></c:out>',
	  	        	 	addressName:'<c:out value="${trackOrders.addressName}"></c:out>',
	  	        	 	address:'<c:out value="${trackOrders.address}"></c:out>',
	  	        	 	country:'<c:out value="${trackOrders.country}"></c:out>',
	  	        	 	province:'<c:out value="${trackOrders.province}"></c:out>',
	  	        	 	city:'<c:out value="${trackOrders.city}"></c:out>',
	  	        	 	area:'<c:out value="${trackOrders.area}"></c:out>',
	  	        	 	createDate:'<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${trackOrders.createDate}" />',
	          			Point    :new BMap.Point(<c:out value="${trackOrders.latitude}"></c:out>,<c:out value="${trackOrders.longitude}"></c:out>)
	          		} 
	              </c:forEach>
         	];

	
	
//设置地图初始化参数          
var map = new BMap.Map('map_canvas');
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl());
map.addControl(new BMap.ScaleControl());
map.addControl(new BMap.OverviewMapControl({ isOpen: true }));
map.centerAndZoom(new BMap.Point(121.494015, 31.228717), 12);
var markers =[];//存放所有逆地址解析覆盖物
//地址和逆地址解析器
var myGeo = new BMap.Geocoder();
//地图显示宽度
var viewport=[];

<c:if test="${empty message}">
	//求取地图中心点   
	var centerPoint = "";
	if(arrPois.length>0){
		centerPoint = new BMap.Point((arrPois[0].lng + arrPois[arrPois.length - 1].lng) / 2, (arrPois[0].lat + arrPois[arrPois.length - 1].lat) / 2);
		map.panTo(centerPoint);
		map.addOverlay(new BMap.Polyline(arrPois,  {strokeColor:"green", strokeWeight:3, strokeOpacity:0.7} ));
		
	}
	var markerPoints =[];//存放所有逆地址解析覆盖物
	var orderMarkers =[];//存放所有订单覆盖物
	
	//设置订单起点终点信息
	var orderStartIcon = new BMap.Icon("<%=path%>/images/start.png",new BMap.Size(33, 45),{anchor: new BMap.Size(15,45)});	//设置订单起点图片
	var orderEndIcon = new BMap.Icon("<%=path%>/images/end.png",new BMap.Size(33, 45),{anchor: new BMap.Size(15,45)});	//设置订单终点图片
	var signpointIcon  = new BMap.Icon("<%=path%>/images/track1.png",new BMap.Size(30, 38),{anchor: new BMap.Size(13, 35)});//签单图片
	var signdate = "<c:out value="${trackOrdersHistoricalDTO.signdate}"></c:out>";//签单时间

	<c:if test="${!empty trackOrdersHistoricalDTO.signpoint and trackOrdersHistoricalDTO.signpoint ne '0,0'}">//签单坐标为空加载
		var signpoint = new BMap.Point(<c:out value="${trackOrdersHistoricalDTO.signpoint}"></c:out>);
		viewport.push(signpoint);
		if(signpoint&&null!=signpoint&&''!=signpoint){
			var signpointMarker = new BMap.Marker(signpoint, {icon: signpointIcon});
			map.addOverlay(signpointMarker); 
			myGeo.getLocation(signpoint, function(rs){
				var addComp = rs.addressComponents;
				    document.getElementById("signAdress").innerHTML="<font color='green'>"+rs.address+"</font>";
				}); 
			
			//orderMarkers.push(signpointMarker);
			signpointMarker.addEventListener('click', function(e){
				myGeo.getLocation(signpoint, function(rs){
						var addComp = rs.addressComponents;
						   var txt ="送达地址:"+rs.address+" 送达时间:"+signdate, mouseoverTxt = txt ;
						    document.getElementById("signAdress").innerHTML="<font color='green'>送达地址:"+rs.address+"</font>";
						    var myCompOverlay = new ComplexCustomOverlay(signpoint, txt,mouseoverTxt);
						    map.addOverlay(myCompOverlay);
						    markers.push(myCompOverlay);
						}); 
			});
		} 
	</c:if>

	<c:if test="${!empty orderStart.latitude}">//订单开始坐标
		var orderStartPoint = new BMap.Point(<c:out value="${orderStart.latitude}"></c:out>,<c:out value="${orderStart.longitude}"></c:out>);
		viewport.push(orderStartPoint);
		if(orderStartPoint&&null!=orderStartPoint&&''!=orderStartPoint){
			var orderStartMarker = new BMap.Marker(orderStartPoint, {icon: orderStartIcon});
			map.addOverlay(orderStartMarker); 
					orderStartMarker.addEventListener('click', function(e){
					myGeo.getLocation(orderStartPoint, function(rs){
							var addComp = rs.addressComponents;
							var txt ="发货地址:"+rs.address, mouseoverTxt = txt ;
							    var myCompOverlay = new ComplexCustomOverlay(orderStartPoint, txt,mouseoverTxt);
							    map.addOverlay(myCompOverlay);
							    markers.push(myCompOverlay);
							}); 
					});					
		} 
	</c:if>		
	
	<c:if test="${!empty orderEnd.latitude}">//订单结束坐标
	var orderEndPoint = new BMap.Point(<c:out value="${orderEnd.latitude}"></c:out>,<c:out value="${orderEnd.longitude}"></c:out>);
	viewport.push(orderEndPoint);
	if(orderEndPoint&&null!=orderEndPoint&&''!=orderEndPoint){
		var orderEndMarker = new BMap.Marker(orderEndPoint, {icon: orderEndIcon});
		map.addOverlay(orderEndMarker); 
		//orderMarkers.push(orderEndMarker);
		orderEndMarker.addEventListener('click', function(e){
			myGeo.getLocation(orderEndPoint, function(rs){
					var addComp = rs.addressComponents;
					var txt ="收货地址:"+rs.address, mouseoverTxt = txt ;
					    var myCompOverlay = new ComplexCustomOverlay(orderEndPoint, txt,mouseoverTxt);
					    map.addOverlay(myCompOverlay);
					    markers.push(myCompOverlay);
					}); 
			});
		 
	 	for (i =0; i<orderMarkers.length; i ++) {
		    (function(){
		    	orderMarkers[i].addEventListener('click', function(e){
		 			clearAllAdress();
		 			var pt = new BMap.Point(e.point.lng,e.point.lat);
		 			myGeo.getLocation(pt, function(rs){
		 				var addComp = rs.addressComponents;
		 				   var txt ="地址"+rs.address, mouseoverTxt = txt ;
		 				    var myCompOverlay = new ComplexCustomOverlay(pt, txt,mouseoverTxt);
		 				    map.addOverlay(myCompOverlay);
		 					markers.push(myCompOverlay);
		 				}); 
		        });    
		    })();
		} 
		
		//查找历史记录中距离收货地最近的一个点
		//document.getElementById("track_order_left_td").style.display='block';
		//显示当前用户信息
		//计算收货地点到到所有轨迹中最近的一个点 直线距离和导航距离
		
		
	}
	</c:if>
	
	
	// 批量地址解析
	var index = 0;

	var myIcon = new BMap.Icon("<%=path%>/images/point.png",new BMap.Size(20, 25),{anchor: new BMap.Size(5,6.25)});
	//把关键点图标地图上展示   
	 for(var i = 0; i<arrPois.length; i++){
		 viewport.push(arrPois[i]);
		var marker = new BMap.Marker(arrPois[i], {icon: myIcon});
		 map.addOverlay(marker); 
		 markerPoints.push(marker);
		 if(i==arrPois.length-1){
			     lushu = new BMapLib.LuShu(map,arrPois,{
				 defaultContent:"一号货车",
				 landmarkPois:[],
				 speed:map.getZoom()*150,
				 autoView:true,
				 icon  : new BMap.Icon('<%=path%>/images/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
				 enableRotation:true
			 	});
			 setTimeout(lushu.start(),5000);
		 }
	}
	
		map.setViewport(viewport);
	
	var markerPointsEndDistance=[];//历史轨迹点到收货地址距离对象数组
	var markerPointsStartDistance=[];//历史轨迹点到发货地址距离对象数组
	 for (i =0; i<markerPoints.length; i ++) {
		 //获取所有点到收货点的直线距离 排序 并得到最进一条距离的导航距离
		 //构造一个点坐标和收货点距离放置一起的对象
		 <c:if test="${!empty orderEnd.latitude}">//订单结束坐标
		 var distanceEnd = map.getDistance(markerPoints[i].point, orderEndPoint);

		 var pointDisEndObj={
				marker: markerPoints[i],
				distance:distanceEnd,
				trackInfo:trackOrdersHistoricalInfo[i]
		 	} 
		 	markerPointsEndDistance.push(pointDisEndObj);
		 var endDis = "endDis"+i;
		 document.getElementById(endDis).innerHTML=(distanceEnd/1000).toFixed(2);
		 </c:if>
		 //构造一个点坐标和发货点距离放置一起的对象
		 <c:if test="${!empty orderStart.latitude}">//订单开始坐标
		 var distanceStart = map.getDistance(markerPoints[i].point, orderStartPoint);
		 var pointStartEndObj={
				marker: markerPoints[i],
				distance:distanceStart,
				trackInfo:trackOrdersHistoricalInfo[i]
		 	} 
		 markerPointsStartDistance.push(pointStartEndObj);
		 var startDis = "startDis"+i;
		 document.getElementById(startDis).innerHTML=(distanceStart/1000).toFixed(2);
		 </c:if>
		   //设置关键点的鼠标点击监听
		    (function(){
		        var  markerPoint = trackOrdersHistoricalInfo[i];
		        markerPoints[i].addEventListener('click', function(e){
		 			clearAllAdress();
		 			var pt = new BMap.Point(e.point.lng,e.point.lat);
		 			myGeo.getLocation(pt, function(rs){
		 				var addComp = rs.addressComponents;
		 				   var business ="";
		 				   if(rs.business&&rs.business!=null&&rs.business!=""){
		 					   business=" 附近有:"+rs.business ;
		 				   }else{
		 					   business=" 未检索到附近大厦或商圈！";
		 				   }
		 				   var txt ="地址:"+rs.address+business+" 时间："+markerPoint.createDate, mouseoverTxt = txt ;
		 				   <c:if test="${!empty orderEnd.latitude}">//订单结束坐标不为空
		 				   	txt =txt+" 到收货地址直线距离："+((map.getDistance(pt, orderEndPoint)).toFixed(2))/1000+' 公里。';//获取两点距离,保留小数点后两位
		 				    txt =txt+" 到发货地址直线距离："+((map.getDistance(pt, orderStartPoint)).toFixed(2))/1000+' 公里';//获取两点距离,保留小数点后两位
		 				   var mouseoverTxt = txt ;
						    var myCompOverlay = new ComplexCustomOverlay(pt, txt,mouseoverTxt);
						    map.addOverlay(myCompOverlay);
						    markers.push(myCompOverlay);
		 				   </c:if>
		 					
		 				   <c:if test="${empty orderEnd.latitude}">//订单结束坐标不为空
		 					   var mouseoverTxt = txt ;
		 					    var myCompOverlay = new ComplexCustomOverlay(pt, txt,mouseoverTxt);
		 					    map.addOverlay(myCompOverlay);
		 					    markers.push(myCompOverlay);
		 				   </c:if>		 			        
		 				   
		 				}); 
		        });    
		    })();
		}
	 
	 <c:if test="${!empty orderStart.latitude}">//订单开始坐标
		//排序获取最小距离到收货点
		markerPointsStartDistance.sort(compare("distance")); 
		var minStartDistance ="";
		if(markerPointsStartDistance.length>0){
			myGeo.getLocation(markerPointsStartDistance[0].marker.point, function(rs){
				var addComp = rs.addressComponents;
					minStartDistance =rs.address;
					//把计算结果最小直线距离和导航距离放置到结果面板中
					document.getElementById("startPlaceDistance").innerHTML+='<span width:100%>司机到发货地址最近的直线距离为:<br><font color=green>【'+markerPointsStartDistance[0].distance/1000+"】公里<br>到达该地时间是:<br>"+markerPointsStartDistance[0].trackInfo.createDate+"<br> 地址："+minStartDistance+"</font></span></br>";
				}); 
		}
		
	</c:if>
	 <c:if test="${!empty orderEnd.latitude}">//订单结束坐标 
		//排序获取最小距离到收货点
		markerPointsEndDistance.sort(compare("distance")); 
		var minDistance ="";
		if(markerPointsStartDistance.length>0){
			myGeo.getLocation(markerPointsEndDistance[0].marker.point, function(rs){
				var addComp = rs.addressComponents;
					minDistance =rs.address;
					//把计算结果最小直线距离和导航距离放置到结果面板中
					document.getElementById("endPlaceDistance").innerHTML+='<span width:100%>司机到收货地址最近的直线距离为:<br><font color=green>【'+markerPointsEndDistance[0].distance/1000+"】公里<br>到达该地时间是:<br>"+markerPointsEndDistance[0].trackInfo.createDate+"<br> 地址："+minDistance+"</font></span>";
				}); 
		}
		</c:if>
	
	 
	 //批量逆地址解析器   
	function bdGEO(){
		if(index===0){
			clearAllAdress();
			document.getElementById('doing').style.display = 'block';
			
		}
		var pt = arrPois[index];
		geocodeSearch(pt);
		index++;
	}

	//var markersP//存放所有逆地址解析和坐标到结果面板中
	function geocodeSearch(pt){
		if(index < arrPois.length-1){
			setTimeout(window.bdGEO,50);
		} 
		myGeo.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			var text ="地址(" + addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber + ")";
			var label = new BMap.Label(text,{offset:new BMap.Size(20,-10)});
			   var business ="";
			   if(rs.business&&rs.business!=null&&rs.business!=""){
				   business=" 附近有:"+rs.business ;
			   }else{
				   business=" 未检索到附近大厦或商圈！";
			   }
			   var txt ="记录序号:"+(index-1)+" 地址:"+rs.address+business+" 记录时间："+trackOrdersHistoricalInfo[index-1].createDate;
			   //如果收货点不为空 计算当前点到收货点的距离 包含导航距离和直线距离 地图上画出点
			   <c:if test="${!empty orderEnd.latitude}">//订单结束坐标不为空
			   	txt =txt+" 到收货地址直线距离："+((map.getDistance(pt, orderEndPoint)).toFixed(2))/1000+' 公里。';//获取两点距离,保留小数点后两位
				txt =txt+" 到收货地址直线距离："+((map.getDistance(pt, orderStartPoint)).toFixed(2))/1000+' 公里';//获取两点距离,保留小数点后两位

				var searchComplete = function (results){
					if (transit.getStatus() != BMAP_STATUS_SUCCESS){
						return ;
					}
					var plan = results.getPlan(0);
					 txt =txt+" 到收货地址导航距离："+plan.getDistance(true)+' 公里';//获取两点距离,保留小数点后两位
					 var mouseoverTxt = txt ;
					    var myCompOverlay = new ComplexCustomOverlay(arrPois[index-1], txt,mouseoverTxt);
					    map.addOverlay(myCompOverlay);
					    markers.push(myCompOverlay);
				}
				/* var transit = new BMap.DrivingRoute(map, {
					onSearchComplete: searchComplete,
					onPolylinesSet: function(){        
				}});
				transit.search(pt, orderEndPoint); */
			   </c:if>
				   var mouseoverTxt = txt ;
				    var myCompOverlay = new ComplexCustomOverlay(arrPois[index-1], txt,mouseoverTxt);
				    map.addOverlay(myCompOverlay);
				    markers.push(myCompOverlay);
			 if(index===arrPois.length){
				 document.getElementById('doing').style.display = 'none';
				 index=0;
			 }
		});
	}
	
	//清除地图上的坐标
	function clearAllAdress(){
		for (var i=0;i<markers.length;i++){
			map.removeOverlay(markers[i]);
		}
	}
	
	
</c:if>
//绑定事件 暂停 停止 开始
$("run").onclick = function(){
    lushu.start();
}
$("stop").onclick = function(){
    lushu.stop();
}
$("pause").onclick = function(){
    lushu.pause();
}

//显示页面加载后台的错误信息
function displayErrorMessage(){
		var msg="<c:out value="${message}"></c:out>";
		var errorMessage = "<c:out value="${errorMessage}"></c:out>";
		if(msg&&null!=msg&&""!=msg){
			alert(msg);
			return false;
		}
}
displayErrorMessage();


//运行加载中
function $(element){
    return document.getElementById(element);
}


//查询历史轨迹
function queryTrack(){
	var  drivingStart =document.getElementById("drivingStart").value;// $("#drivingStart").attr("value");
	var  drivingEnd =document.getElementById("drivingEnd").value; //$("#drivingEnd").attr("value"); 
	var  UserID =document.getElementById("UserID").value; //$("#UserID").attr("value"); 
	if(null==UserID||""==UserID){
		alert("用户编号为空！");
		return false;
	}
	if(null==drivingStart||""==drivingStart){
		alert("请输入开始时间");
		return false;
	}
	if(null==drivingEnd||""==drivingEnd){
		alert("请输入结束时间");
		return false;
	}
	queryForm.submit();
}

//日期控件初始化
var start = {
    elem: '#drivingStart',
    format: 'YYYY-MM-DD hh:mm:ss',
    max: '2099-06-16 23:59:59', //最大日期
    istime: true,
    istoday: true,
    choose: function(datas){
         end.min = datas; //开始日选好后，重置结束日的最小日期
         end.start = datas //将结束日的初始值设定为开始日
    }
};
var end = {
    elem: '#drivingEnd',
    format: 'YYYY-MM-DD hh:mm:ss',
    max: '2099-06-16 23:59:59',
    istime: true,
    istoday: true,
    choose: function(datas){
        start.max = datas; //结束日选好后，重置开始日的最大日期
    }
};
laydate(start);
laydate(end);




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

