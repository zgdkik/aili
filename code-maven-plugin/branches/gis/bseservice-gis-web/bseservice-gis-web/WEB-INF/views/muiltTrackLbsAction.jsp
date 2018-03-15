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
    <title>查看多个司机位置信息</title>
    <link href="<%=path%>/css/styles.css" rel="Stylesheet" type="text/css">
    <link href="<%=path%>/css/reset.css" rel="Stylesheet" type="text/css">
	<link href="http://api.map.baidu.com/res/12/bmap.css" rel="stylesheet" type="text/css">    
	<script src="http://api.map.baidu.com/api?v=2.0&ak=hF4dbGT9jWPE9c4dboQQSM2f"></script>      
	<script type="text/javascript" src="<%=path%>/js/complexCustomOverlay.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
   
      
</head>

<body > 
	<div id="map_canvas" ></div>
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

	//接收 多个司机位置信息 已经是一个json对象数组 格式[{"userId":xxx,"point":xxx,"time":yyyy-MM-dd HH:mm:ss},{...},{....},{....}]  
	var muiltTrackLbsJSONArray =eval("(<c:out value='${muiltTrackLbsJSONArray}'/>)");
	var myIcon = new BMap.Icon("<%=path%>/images/track1.png",new BMap.Size(30, 38),{anchor: new BMap.Size(13, 35)});
	
      for(var i=0;i<muiltTrackLbsJSONArray.length;i++){
    	  var trackPoint = new BMap.Point( muiltTrackLbsJSONArray[i].point);
    		var marker = new BMap.Marker(trackPoint, {icon: myIcon});
    		 map.addOverlay(marker); 
    		 markerPoints.push(marker);
    		 (function(){
    			 var trackInfo = muiltTrackLbsJSONArray[i];
    			 marker.addEventListener('click', function(e){
    		 			clearAllAdress();
    		 			//先去检索后台 调用接口app后台 查询司机信息
    		 					$.ajax({
										url : '/bseservice-gis-web/getTrackInfoAction.jspa',
										type : 'post',
										data : "userid="+trackInfo.userId,
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

</script>
<script> 


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

