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
    <title>查看附近的司机</title>
    <link href="<%=path%>/css/styles.css" rel="Stylesheet" type="text/css">
    <link href="<%=path%>/css/reset.css" rel="Stylesheet" type="text/css">
	<link href="http://api.map.baidu.com/res/12/bmap.css" rel="stylesheet" type="text/css">    
	<script src="http://api.map.baidu.com/api?v=2.0&ak=hF4dbGT9jWPE9c4dboQQSM2f"></script>      
	<script type="text/javascript" src="<%=path%>/js/complexCustomOverlay.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
   
      
</head>

<body > 
	<center>
		
		<div id="track_order_controller" >
			<div id="queryDiv"  >
				<form name="queryForm" id="queryForm" style="text-align:left;  margin-left:25%;" action="/bseservice-gis-web/track_near_order.jspa" method="post">
					地址:<input name="address" id="queryAddress" value="<c:out value='${trackOrdersHistoricalDTO.address}'/>" onkeypress="queryTrack();">
					范围:<select id="kilometers">
						<option value="500">500米</option>
						<option value="1000" >1公里</option>
						<option value="2000">2公里</option>
						<option value="3000">3公里</option>
						<option value="4000">4公里</option>
						<option value="5000" selected="selected">5公里</option>
						<option value="6000">6公里</option>
						<option value="7000">7公里</option>
						<option value="8000">8公里</option>
						<option value="9000">9公里</option>
						<option value="10000">10公里</option>
					</select>
					<span style="display: none">
						查询日期:<input type="text" id="orderTime" name="orderTime" value="<c:out value="${trackOrdersHistoricalDTO.orderTime}"/>" readonly="readonly">
					</span>
					时间:<select id="orderTimeSub">
						<option value="2">2分钟内</option>
						<option value="5">5分钟内</option>
						<option value="10">10分钟内</option>
						<option value="30" selected="selected">30分钟内</option>
						<option value="60">1小时内</option>
						<option value="120">2小时内</option>
						<option value="180">3小时内</option>
						<option value="240">4小时内</option>
						<option value="300">5小时内</option>
						<option value="360">6小时内</option>
						<option value="420">7小时内</option>
						<option value="480">8小时内</option>
						<option value="540">9小时内</option>
						<option value="600">10小时内</option>
						<option value="1440">1天内</option>
						<option value="14400" >10天内</option>
					</select>
					<input type="hidden" id="Province" name="Province" value="<c:out value='${trackOrdersHistoricalDTO.province}'/>" >
					<input type="hidden" id="circlePoint" name="circlePoint" value="<c:out value='${trackOrdersHistoricalDTO.province}'/>" >
					<input type="button" value="查	询" onclick="queryTrack();">
				</form>
				<div id="doing" style="position: relative; display: none;">
					<div class="movbg">
					</div>
					<div class="movtxt">
						<span class="spanText" ><img src="<%=path%>/images/loading.gif" /></span><span style="display:block; float:left">加载中...</span>
					</div>
				</div>
				
			</div>
		<h1 style="margin-top: 5px"></h1>	
		<div id="track_order_bottom_controller">
			<table align="center" height="100%" width="100%">
				<tr nowrap="nowrap">
					<td  style="display: none;vertical-align: top;" id="track_order_left_td" nowrap="nowrap" width="265px">
						<div id="track_order_left_controller"  style="float:left;top: 5px;" ></div>
					</td>
					<td width="100%" id="map_canvas_td" nowrap="nowrap">
						<div id="map_canvas"  style="float:right;"></div>
					</td>
				</tr>
			</table>
		</div>
		</div>

	</center>
</body>
<script> 


//求取地图中心点   
//设置地图初始化参数          
var map = new BMap.Map('map_canvas');
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl());
map.addControl(new BMap.ScaleControl());
map.addControl(new BMap.OverviewMapControl({ isOpen: true }));
map.centerAndZoom(new BMap.Point(121.494015, 31.228717), 10); 

// 创建地址解析器实例
var myGeo = new BMap.Geocoder();

</script>
<script> 

function queryTrack(){
	//先清除所有地图上的标注
	map.clearOverlays();
	document.getElementById("track_order_left_td").style.display='block';
	//先定位
	var queryAddress = $("#queryAddress").attr("value");  
	var orderTime =  $("#orderTime").attr("value"); 
	/* if(!orderTime||orderTime===""||orderTime===null){
		alert("请选择日期！");
		return false;
	} */
	var local = new BMap.LocalSearch(map, { renderOptions: {map: map,panel: "track_order_left_controller",autoViewport:true}});
	
	local.clearResults();
	local.search(queryAddress);
	local.setMarkersSetCallback(localMarkersSetCallback);
	
}

//删除所有圆
var  circles =[];
function clearCircles(){
	for (var i=0;i<circles.length;i++){
		map.removeOverlay(circles[i]);
	}
	circles.length=0;
}
var clickFlag=false;
function localMarkersSetCallback(rs){
	for(var i=0;i<rs.length;i++){
		rs[i].marker.addEventListener('click', function(e){
			runMatch(e);
		});
		
		rs[i].marker.addEventListener('infowindowopen', function(e){
			//图标信息展示操作 要区别是 面板还是地图点击 面板点击 false  地图添加true
			runMatch(e.currentTarget);
			
			
		});
	}
}

function runMatch(e){
	if(!clickFlag){
		//点击图标操作
		clickFlag= true;
		//画一个指定半径的圆 并获取到所有顶点
		clearCircles();
		clearAllmarkerPoints();
		clearAllAdress();
	var circle	= new BMap.Circle(e.point,$("#kilometers").val(),{fillColor:"green", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
		map.addOverlay(circle);
		map.setViewport(circle.W);
		circles.push(circle);
		//逆向地址解析，获取当前点省份
		myGeo.getLocation(e.point, function(rs){
				var addComp = rs.addressComponents;
				var province = addComp.province;
					//查看多久时间
					var orderTimeSub =$("#orderTimeSub").val();
					//选定的时间
					var orderTime =$("#orderTime").attr("value"); 
					//处理参数 把顶点坐标分割成 经度坐标集合 纬度坐标集合
					var circlesArray = null;
					if(circle.ia){
						circlesArray = circle.ia;
					}else if(circle.$){
						circlesArray = circle.$;
					}else{
						alert("发生错误");	
					}
					var circlesArray = circle.ia;
					var circlesLats=[];
					var circlesLngs=[];
					for(var j=0;j<circlesArray.length-1;j++){
						circlesLats.push(circlesArray[j].lat);
						circlesLngs.push(circlesArray[j].lng);
					}
				IsPointInPolygon(province,circlesLats.join(","),circlesLngs.join(","),orderTimeSub,orderTime);
				}); 
	}
}

var myIcon = new BMap.Icon("<%=path%>/images/track1.png",new BMap.Size(30, 38),{anchor: new BMap.Size(13, 35)});
var vTruckIcon = new BMap.Icon("<%=path%>/images/truck02.png",new BMap.Size(30, 38),{anchor: new BMap.Size(13, 35)});

//$.ajax拼接data的异步请求
//参数 省份 圆顶点 时间差 订单时间
var markerPoints=[];
function IsPointInPolygon(province,circlesLats,circlesLngs,orderTimeSub,orderTime){
	$.ajax({   
	    url:'/bseservice-gis-web/IsPointInPolygon.jspa',   
	    type:'post',   
	    data:'circlesLats='+circlesLngs+"&circlesLngs="+circlesLats+"&Province="+province+"&orderTimeSub="+orderTimeSub+"&orderTime="+orderTime,   
	    async : false, 
	    error:function(){   
	       alert('数据请求出错！请联系管理员');   
	    },   
	    success:function(data){   
   		 clickFlag= false;
	      //alert(data);
	      //先展示所有查询出来的司机
	      
		var json =eval("("+data+")");
		var TrackOrdersAllDTOList = eval("("+json.TrackOrdersHistoricalDTOList+")");
	      for(var i=0;i<TrackOrdersAllDTOList.length;i++){
	    	  var trackPoint = new BMap.Point( TrackOrdersAllDTOList[i].latitude,TrackOrdersAllDTOList[i].longitude);
	    		 var icon = myIcon;
	    		if(TrackOrdersAllDTOList[i].isSignTruck=="1"){
	    			icon = vTruckIcon;//V司机
	    		}
	    		var marker = new BMap.Marker(trackPoint, {icon: icon});
	    		 map.addOverlay(marker); 
	    		 markerPoints.push(marker);
	    		 (function(){
	    			 var trackInfo =TrackOrdersAllDTOList[i];
	    			 marker.addEventListener('click', function(e){
	    		 			clearAllAdress();
	    		 			//先去检索后台 调用接口app后台 查询司机信息
	    		 					$.ajax({
											url : '/bseservice-gis-web/getTrackInfoAction.jspa',
											type : 'post',
											data : "userid="+trackInfo.userID,
											async : false,
											error : function() {
												alert('数据请求出错！请联系管理员');
											},
											success : function(result) {
												result = eval("("+result+")");
					    		 				 var result_data = eval("("+result.truckInfo+")");
												if(result_data.d.ErrCode){
													alert(result_data.d.ErrMsg);
												}else{
							    		 			var pt = new BMap.Point(e.point.lng,e.point.lat);
							    		 			myGeo.getLocation(pt, function(rs){
							    		 				var addComp = rs.addressComponents;
							    		 				   var business ="";
							    		 				   if(rs.business&&rs.business!=null&&rs.business!=""){
							    		 					   business=" 附近有:"+rs.business ;
							    		 				   }else{
							    		 					   business=" 未检索到附近大厦或商圈！";
							    		 				   }
							    		 				   var address = rs.address;
							    		 				   trachAddress = trackInfo.address;
							    		 				   /* if(trachAddress&&trachAddress!==null&&trachAddress!==""){
							    		 					  address=trachAddress;
							    		 				   } */
							    		 				  var IsOnline_text = "";
							    		 				   if(result_data.d.Data.IsOnline==true){
							    		 					  IsOnline_text="正在接活";
							    		 				   }else{
							    		 					  IsOnline_text="没有接活";
							    		 				   }
							    		 				   var phoneNumber = result_data.d.Data.PhoneNumber;
							    		 				   var phoneNumber_end2 ="";
							    		 				   if(phoneNumber&&null!=phoneNumber&&phoneNumber!=""){
							    		 					  phoneNumber_end2 = phoneNumber.substr(phoneNumber.length-2,phoneNumber.length);
							    		 				   }
							    		 				   var imgSrc = "http://photo2.yihaohuoche.com/"+phoneNumber_end2+"/phone-"+phoneNumber+"-Portrait-small.jpg";
							    		 				   var txt ="<p class='turack_driver'><img  src="+imgSrc+" ></img></p><p class='text_css_style'>地址:"+address+" </br>时间："+trackInfo.createDate_text+" </br>司机名称: "+result_data.d.Data.Name+" </br>司机手机:<strong> "+result_data.d.Data.PhoneNumber+" </strong></br>车型: "+result_data.d.Data.TruckType+" </br>是否接活："+IsOnline_text+"</p>", mouseoverTxt = txt ;
							    		 					var opts = {
							    		 							enableMessage:false//设置允许信息窗发送短息
							    		 						   };
							    		 						var infoWindow = new BMap.InfoWindow(txt,opts);  // 创建信息窗口对象 
							    		 						map.openInfoWindow(infoWindow,pt); //开启信息窗口
							    		 				    
							    		 				}); 
													}
											}
										});
	    		 			
	    		 			
	    		        });    
	    		    })(); 
	    		 
	      }
	    	//$("#divs").html(data);   
	    }
	});
}

var compOverlays =[];
function clearAllAdress(){
	for (var i=0;i<compOverlays.length;i++){
		map.removeOverlay(compOverlays[i]);
	}
}
function clearAllmarkerPoints(){
	for (var i=0;i<markerPoints.length;i++){
		map.removeOverlay(markerPoints[i]);
	}
}


</script>


<script type="text/javascript">
 	$(document).ready(
		function() {
			//接收后台传入的坐标和时间
			var address = "<c:out value='${trackOrdersHistoricalDTO.address}'/>"; //地址
			var latLng="<c:out value="${trackOrdersHistoricalDTO.latitude}"></c:out>";
			if(latLng&&latLng!=null&&latLng!=""){
				 $("#queryAddress").val(address);
				var orderPoint =new BMap.Point('<c:out value="${trackOrdersHistoricalDTO.latitude}"></c:out>','<c:out value="${trackOrdersHistoricalDTO.longitude}"></c:out>');
				var startIcon = new BMap.Icon("<%=path%>/images/markerLatLng.png",new BMap.Size(33, 45),{anchor: new BMap.Size(20, 42)});
				var marker = new BMap.Marker(orderPoint, {icon: startIcon});
			   		 map.addOverlay(marker); 
			   		 map.panTo(orderPoint);
			   		 map.setViewport([orderPoint]);
			   		var opts = {
			   			  width : 200,     // 信息窗口宽度
			   			  height: 100,     // 信息窗口高度
			   			  title : "订单位置" , // 信息窗口标题
			   			  enableMessage:true//设置允许信息窗发送短息
			   			}
			   		 myGeo.getLocation(orderPoint, function(result){
			   			var infoWindow = new BMap.InfoWindow(result.address, opts); 
			   			marker.openInfoWindow(infoWindow,orderPoint); //开启信息窗口
			   		});
			   		var rs =[];
			   		var m ={marker:marker};
			   		rs.push(m);
			   		localMarkersSetCallback(rs);
				
			}else{
				 //根据IP定位显示当前用户所在城市
				function myFun(result){
					var cityName = result.name;
					map.setCenter(cityName);
				}
				var myCity = new BMap.LocalCity();
				myCity.get(myFun);
				
			}
		}
	); 
</script>