<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8" isELIgnored="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base target="_self">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title>司机轨迹热力图</title>
<link href="<%=path%>/css/styles.css" rel="Stylesheet" type="text/css">
<link href="<%=path%>/css/reset.css" rel="Stylesheet" type="text/css">
<link rel="stylesheet" href="http://huiyan.baidu.com/mapv/demo/css/style.css" />     
<script src="<%=path%>/js/laydate/laydate.js"></script> 
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=1XjLLEhZhQNUzd93EjU5nOGQ"></script>
<script type="text/javascript" src="http://huiyan.baidu.com/mapv/demo/js/lib/drive.js"></script>


 <style type="text/css">
		
		body{height:100%;min-height:800px; margin:1px;padding:1px;overflow-y:hidden;}
		#queryDiv{  
			  top: 30px;
			  height: 50px;
			  width: 70%;
			  color: 4a4a4a;
			  margin: 0 auto;
			  position: fixed;
			  background: rgba(255, 255, 255, 0.8) none repeat scroll 0 0 !important;
			  filter: Alpha(opacity=80);
			  left: 14%;
			  border: 1px solid #00b90c;
			  line-height: 50px;
		  }
		#mapDiv1{height:100%;width:100%;  min-height: 1000px;}
		#queryForm{width:100%; margin:0 auto; }
		#queryTable{width:100%; overflow:hidden; padding-top:5px;}
		#button{background-color:green;color:#ffffff; border: 1px solid #ffffff; border-radius: 3px; padding:2px 5px; line-height:18px;}
		.movbg {background: #4a4a4a; z-index:11; opacity: 0.6; height: 160px; position: absolute; left:40%;top: 200px; width: 300px;}
		.movtxt{color: #fff; height: 80px;z-index:11; line-height: 160px; position: absolute; left: 40%; text-align: center; top: 200px; width: 300px;}		
		#queryTable span{ float:left; width:50px; line-height:21px;}
		#queryTable .date,#queryTable .city{ width:25%; float:left; }
		#queryTable .search{ text-align:left; width:10%; float:left;}
		#queryTable .fl{ float:left; line-height:18px;}
		#queryTable  .fl#city{ line-height:24px; height:24px; -webkit-appearance: none;}
		#city,#startTime{ -webkit-appearance: none;}
		.search #button{-webkit-appearance: none;}
		@media screen and  (max-width: 768px){
			#queryDiv{ width:98%; margin:0 auto;left:1%; }
			#queryForm{width:98%; margin:0 auto;
			  top: 10px;
			  height: 50px;
			  
			  color: 4a4a4a;
			 
			 }
			  #queryTable .date,#queryTable .city{ width:44%; float:left; }
		      #queryTable .search{ text-align:left; width:10%; float:left;}
		
		  }
		  @media screen and  (max-width: 480px){
			  #queryDiv{ height:100px;}
			  #queryForm{width:98%; margin:0 auto; }
			  #city,#startTime{ width:90%; }
			  #queryTable .date,#queryTable .city{ width:98%; float:left;}
			#queryTable .search{ text-align:right;  width:90%; float:left;}
			  #button{margin-right:18px;}
		  }
		  
  </style>
</head>
<center>
<body style="margin-top: -18px;">
			</br>
			<div id="queryDiv" style="min-width: 885px;" >
				<div style="height:1px;border-bottom:1px solid #ffffff;margin-bottom:5px"></div>
				<form name="queryForm" id="queryForm"  action="/bseservice-gis-web/trackPointAtion.jspa" method="post" style="min-width: 885px;">
				<div id="queryTable" style="line-height:36px;">
					
						<span class="fl" style="width: 100px;">起止日期:</span>
						<div class="date">
							<input class="fl" type="text" id="startTime" name="startTime" value="<c:out value='${trackOrdersHistoricalDTO.startTime}'/>" readonly="readonly" style="width:70%; margin-right:10px;">
						</div>
						<span class="fl" style="width: 30px;">到</span>
						<div class="date">
							<input class="fl" type="text" id="endTime" name="endTime" value="<c:out value='${trackOrdersHistoricalDTO.endTime}'/>" readonly="readonly" style="width:70%; margin-right:10px;">
						</div>
						
						<div class="city">
							<span class="fl">城市:</span><select class="fl" id="city" style="width:70%; margin-right:10px;">
								<c:forEach items="${cityList}" var="city">
									<option value="<c:out value='${city.name}'></c:out>" ><c:out value='${city.name}'></c:out></option>
								</c:forEach>
							</select> 
						</div>
						<div class="search">
							<input type="button" id="button" value="查	询" onclick="queryTrack(123);" >
						</div>
					</tr>
					
				</div>
				</form>
			</div>
			<div id="doing" style="position: relative; display: none;">
				<div class="movbg">
				</div>
				<div class="movtxt">
					<span class="spanText" ><img src="<%=path%>/images/loading.gif" /></span><span style="display:block;">数据计算量较大,狂命加载中...</span>
				</div>
			</div>			
			
			<div id="mapDiv1" ></div>
			
</body>

</center>
<script type="text/javascript" src="http://huiyan.baidu.com/mapv/demo/js/lib/Mapv.js"></script>
<script type="text/javascript" src="http://huiyan.baidu.com/mapv/demo/js/lib/example.js"></script>
<script type="text/javascript">

	var map = null;
	$(document).ready(function() {
		map = new BMap.Map("mapDiv1");
		map.enableScrollWheelZoom();
		var point = new BMap.Point(113.270793,23.135308);
		map.centerAndZoom(point, 9);

		//日期控件初始化
		var start = {
		    elem: '#startTime',
		    format: 'YYYY-MM-DD hh:mm:ss',
		    istime: true,
		    max: '2099-06-16 23:59:59', //最大日期
		    istoday: true
		};
		laydate(start);
		//日期控件初始化
		var end = {
		    elem: '#endTime',
		    format: 'YYYY-MM-DD hh:mm:ss',
		    istime: true,
		    max: '2099-06-16 23:59:59', //最大日期
		    istoday: true
		};
		laydate(end);

		//queryTrack();
		
		//ajax 
		
	});

	
function queryTrack(initParam){
	var startTimestr = document.getElementById('startTime').value;
	if(!startTimestr||startTimestr==""){
		alert("请输入日期！");
		return false;
	}
	document.getElementById('button').disabled=true;
	document.getElementById('doing').style.display = 'block';
	map.clearOverlays();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var city = $("#city").val();

	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	$.ajax({   
	    url:'/bseservice-gis-web/getTrackThermalLineInfoAction.jspa',   
	    type:'post',   
	    data:'startTime='+startTime+"&city="+city+"&endTime="+endTime,   
	    async : true, 
	    error:function(){   
	       alert('数据请求出错！请联系管理员');   
	       document.getElementById('doing').style.display = 'none';
	    },   
	    success:function(data){  
	    	document.getElementById('doing').style.display = 'none';
	   		 document.getElementById('button').disabled=false;
	    	debugger;
   			 clickFlag= false;
   			var trackLines = data.truckThermalInfo;
   			var driveData1 =[]
   			for(var i= 0;i<trackLines.length;i++){
   				var ps = trackLines[i].points;
   				var geo = [];
   				for(var j in ps){
   					var lat = ps[j].latitude;
   					var lng = ps[j].longitude;
   					var projection = new BMap.MercatorProjection();
   					var point = projection.lngLatToPoint(new BMap.Point(lat*1, lng*1));
   					var latlng =[parseInt(point.x),parseInt(point.y)];
   					geo.push(latlng);
   				}
   				var geoObj = {geo:geo};
   				driveData1.push(geoObj);
   				
   			}
   		  /**
   		     * @file 示例代码
   		     */

   		    // 添加时间戳
   		      for (var i = 0; i < driveData1.length; i++) {
   		        driveData1[i].count = Math.random() * 10;
   		    }  

   		    //console.log(driveData);


   		    // 第一步创建mapv示例
   		    var mapv = new Mapv({
   		        drawTypeControl: true,
   		        map: map  // 百度地图的map实例
   		    });

   		   var layer = new Mapv.Layer({
   	        zIndex: 1,
   	        mapv: mapv,
   	        dataType: 'polyline',
   	        coordType: 'bd09mc',
   	        data: driveData1,
   	        drawType: 'heatmap',
   	        drawOptions: {
   	            blur: true, // 是否有模糊效果
   	            shadowBlur: 20,
   	            shadowColor: 'black',
   	            max: 10, // 设置显示的权重最大值
   	            lineWidth: 2, // 半径大小
   	            strokeStyle: 'rgba(0, 0, 0, 0.2)',
   	            maxOpacity: 0.8,
   	            gradient: { // 显示的颜色渐变范围
   	                '0': 'blue',
   	                '0.6': 'cyan',
   	                '0.7': 'lime',
   	                '0.8': 'yellow',
   	                '1.0': 'red'
   	            }
   	        }
   	    });

   			
   			
	   		 document.getElementById('doing').style.display = 'none';
	   		 document.getElementById('button').disabled=false;
	      	
	    }
	}); 

}

</script>

