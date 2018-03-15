<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8" isELIgnored="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base target="_self">
<meta name="viewport"
	content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta HTTP-EQUIV='pragma' CONTENT='no-cache'>
<meta HTTP-EQUIV='Cache-Control' CONTENT='no-cache, must-revalidate'>
<meta HTTP-EQUIV='expires' CONTENT='0'>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>一号货车订单分布聚合图</title>
<link href="http://api.map.baidu.com/res/12/bmap.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/styles.css" rel="Stylesheet" type="text/css">
<link href="<%=path%>/css/reset.css" rel="Stylesheet" type="text/css">
<script src="http://api.map.baidu.com/api?v=2.0&ak=hF4dbGT9jWPE9c4dboQQSM2f"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<script src="<%=path%>/js/laydate/laydate.js"></script>
  <script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>

<style type="text/css">
body {
	height: 100%;
	min-height: 800px;
	margin: 1px;
	padding: 1px;
	overflow-y: hidden;
}

#queryDiv {
  top: 30px;
  height: 65px;
  width: 80%;
  color: 4a4a4a;
  margin: 0 auto;
  position: fixed;
  background: rgba(255, 255, 255, 0.8) none repeat scroll 0 0 !important;
  filter: Alpha(opacity = 80);
  left: 10%;
  border: 1px solid #00b90c;
  line-height: 50px;
  min-width: 700px;
}

#mapDiv1 {
	height: 100%;
	width: 100%;
	min-height: 1000px;
}

#queryForm {
	width: 80%;
	margin: 0 auto;
}

#queryTable {
	width: 100%;
	overflow: hidden;
	padding-top: 5px;
}

#button {
	background-color: green;
	color: #ffffff;
	border: 1px solid #ffffff;
	border-radius: 3px;
	padding: 2px 5px;
	line-height: 18px;
}

.movbg {
	background: #4a4a4a;
	z-index: 11;
	opacity: 0.6;
	height: 160px;
	position: absolute;
	left: 40%;
	top: 200px;
	width: 300px;
}

.movtxt {
	color: #fff;
	height: 80px;
	z-index: 11;
	line-height: 160px;
	position: absolute;
	left: 40%;
	text-align: center;
	top: 200px;
	width: 300px;
}

#queryTable span {
	float: left;
	width: 50px;
	line-height: 21px;
}

#queryTable .date, #queryTable .city {
	width: 20%;
	float: left;
}

#queryTable .search {
	text-align: left;
	width: 14%;
	float: left;
}

#queryTable .fl {
	float: left;
	line-height: 18px;
}

#queryTable  .fl#city {
	line-height: 24px;
	height: 24px;
	-webkit-appearance: none;
}

#city, #startTime {
	-webkit-appearance: none;
}

.search #button {
	-webkit-appearance: none;
}

@media screen and (max-width: 768px) {
	#queryDiv {
		width: 98%;
		margin: 0 auto;
		left: 1%;
		min-width: 500px;
	}
	#queryForm {
		width: 98%;
		margin: 0 auto;
		top: 10px;
		height: 50px;
		color: 4a4a4a;
	}
	#queryTable .date, #queryTable .city {
		width: 44%;
		float: left;
	}
	#queryTable .search {
		text-align: left;
		width: 10%;
		float: left;
	}
}

@media screen and (max-width: 480px) {
	#queryDiv {
		height: 100px;
		min-width: 500px;
	}
	#queryForm {
		width: 98%;
		margin: 0 auto;
	}
	#city, #startTime {
		width: 90%;
	}
	#queryTable .date, #queryTable .city {
		width: 98%;
		float: left;
	}
	#queryTable .search {
		text-align: right;
		width: 90%;
		float: left;
	}
	#button {
		margin-right: 18px;
	}
}
</style>
</head>
<center>
	<body>
		</br>
		<div id="queryDiv">
			<div
				style="height: 1px; border-bottom: 1px solid #ffffff; margin-bottom: 5px"></div>
			<form name="queryForm" id="queryForm"
				action="/bseservice-gis-web/ajaxGetTrackOrderAction.jspa" method="post">
				<div id="queryTable" style="line-height: 36px;">

					<div class="date">
						<span class="fl">起期:</span><input class="fl" type="text" title="请选择一天内的日期期间以免查询速度过慢"
							id="startTime" name="startTime"
							readonly="readonly" style="width: 70%; margin-right: 10px;">
					</div>
					<div class="date">
						<span class="fl">止期:</span><input class="fl" type="text" title="请选择一天内的日期期间以免查询速度过慢"
							id="endTime" name="endTime"
							readonly="readonly" style="width: 70%; margin-right: 10px;">
					</div>

					<div class="city">
						<span class="fl">城市:</span><select id="aid" class="fl" 
							style="width: 70%; margin-right: 10px;height: 23px;">
							<c:forEach items="${cityList}" var="city">
								<option value="<c:out value='${city.name}'></c:out>"><c:out
										value='${city.name}'></c:out></option>
							</c:forEach>
						</select>
						<!-- <input id="condition" style="  margin-top: -23px;  width: 63%;margin-left: 14px;height: 22px;"> -->
					</div>
					<div class="city">
						<span class="fl">类型:</span>
						<select class="fl" id="type" style="width: 70%; margin-right: 10px;">
								<option value="0" >出发</option>
								<option value="">全部</option>
								<option value="1">到达</option>
						</select>
					</div>
					<div class="search">
						<input type="button" id="button" value="查	询"
							onclick="queryTrack(123);">
					</div>
					</tr>

				</div>
			</form>
		</div>
		<div id="doing" style="position: relative; display: none;">
			<div class="movbg"></div>
			<div class="movtxt">
				<span class="spanText"><img
					src="<%=path%>/images/loading.gif" /></span><span style="display: block;">狂命加载中...</span>
			</div>
		</div>

		<div id="mapDiv1"></div>

	</body>

</center>

<script type="text/javascript">

var EXAMPLE_URL = "http://api.map.baidu.com/library/MarkerClusterer/1.2/examples/";
	var map = new BMap.Map("mapDiv1");
	map.enableScrollWheelZoom();
	var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point, 8);
var markers = [];
	$(document).ready(function() {
		//日期控件初始化
		var start = {
		    elem: '#startTime',
		    format: 'YYYY-MM-DD',
		    max: '2099-06-16 23:59:59', //最大日期
		    istoday: true,
		    choose: function(datas){
		    	end.min = datas; //开始日选好后，重置结束日的最小日期
		    	end.start = datas //将结束日的初始值设定为开始日
		    }
		};
		laydate(start);
		//日期控件初始化
		var end = {
		    elem: '#endTime',
		    format: 'YYYY-MM-DD',
		    max: '2099-06-16 23:59:59', //最大日期
		    istoday: true,
		    choose: function(datas){
		        start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		};
		laydate(end);

		
	});
	
	/* $(function(){
		$("#condition").keyup(function(){
			if($("#condition").val() && $("#condition").val()!=''){
				var allOptions = $("#aid").children();
				//alert(allOptions);
				$(allOptions).each(function(i){
					if($(allOptions[i]).html().indexOf($("#condition").val()) <= -1){
						$("#aid").append(allOptions[i]);
						$("#aid option").eq(0).attr('selected', 'true');
						
					}else{
						$("#condition").val(allOptions[i].text);
					}
				});
				
			}
		});
	}); */
</script>

<script> 

var myIcon = new BMap.Icon("<%=path%>/images/track1.png", new BMap.Size(30,38), {
		anchor : new BMap.Size(13, 35)
	});

	function clearAllmarkerPoints() {
		for (var i = 0; i < markers.length; i++) {
			map.removeOverlay(markers[i]);
		}
	}
	var markerClusterer = null;

	function queryTrack(initParam) {
		var startTimestr = document.getElementById('startTime').value;
		if (!startTimestr || startTimestr == "") {
			alert("请输入起期！");
			return false;
		}
		var endTimestr = document.getElementById('endTime').value;
		if (!endTimestr || endTimestr == "") {
			alert("请输入止期！");
			return false;
		}
		//markerClusterer=null;
		if (null != markerClusterer) {
			markerClusterer.clearMarkers();
		}
		document.getElementById('button').disabled = true;
		document.getElementById('doing').style.display = 'block';
		markers = [];
		clearAllmarkerPoints();
		map.clearOverlays();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		//var endTime = $("#endTime").val();
		var city = $("#aid").val();
		var type = $("#type").val();

		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		$
				.ajax({
					url : '/bseservice-gis-web/ajaxGetTrackOrderAction.jspa',
					type : 'post',
					data : '&startTime=' + startTime + '&endTime=' + endTime+ "&city=" + city + "&type=" + type,
					async : true,
					error : function() {
						alert('数据请求出错！请联系管理员');
						document.getElementById('doing').style.display = 'none';
					},
					success : function(data) {
						debugger;
						clickFlag = false;
						var points = eval("("+ eval("(" + data + ")").orderList + ")");
						for (var i = 0; i < points.length; i++) {
							if (i == 0 && city != "全国") {
								map.panTo(new BMap.Point(points[i].longitude,
										points[i].latitude), 14);
							}
							var marker = new BMap.Marker(new BMap.Point(
									points[i].longitude,points[i].latitude ), {
								icon : myIcon
							});
							marker.userID = points[i].userID;
							markers.push(marker);
							/*(function() {
								marker
										.addEventListener(
												'click',
												function(e) {
													clearAllAdress();
													//先去检索后台 调用接口app后台 查询司机信息
													$
															.ajax({
																url : '/bseservice-gis-web/getTrackInfoAction.jspa',
																type : 'post',
																data : "userid="
																		+ e.currentTarget.userID,
																async : false,
																error : function() {
																	alert('数据请求出错！请联系管理员');
																},
																success : function(
																		result) {
																	result = eval("("
																			+ result
																			+ ")");
																	var result_data = eval("("
																			+ result.truckInfo
																			+ ")");
																	if (result_data.d.ErrCode) {
																		alert(result_data.d.ErrMsg);
																	} else {
																		var pt = new BMap.Point(
																				e.point.lng,
																				e.point.lat);
																		myGeo
																				.getLocation(
																						pt,
																						function(
																								rs) {
																							var addComp = rs.addressComponents;
																							var business = "";
																							if (rs.business
																									&& rs.business != null
																									&& rs.business != "") {
																								business = " 附近有:"
																										+ rs.business;
																							} else {
																								business = " 未检索到附近大厦或商圈！";
																							}
																							var address = rs.address;
																							var IsOnline_text = "";
																							if (result_data.d.Data.IsOnline == true) {
																								IsOnline_text = "正在接活";
																							} else {
																								IsOnline_text = "没有接活";
																							}
																							var phoneNumber = result_data.d.Data.PhoneNumber;
																							var phoneNumber_end2 = "";
																							if (phoneNumber
																									&& null != phoneNumber
																									&& phoneNumber != "") {
																								phoneNumber_end2 = phoneNumber
																										.substr(
																												phoneNumber.length - 2,
																												phoneNumber.length);
																							}
																							var imgSrc = "http://photo2.yihaohuoche.com/"
																									+ phoneNumber_end2
																									+ "/phone-"
																									+ phoneNumber
																									+ "-Portrait-small.jpg";
																							var txt = "<p class='turack_driver'><img  src="+imgSrc+" ></img></p><p class='text_css_style'>地址:"
																									+ address
																									+ " </br>司机名称: "
																									+ result_data.d.Data.Name
																									+ " </br>司机手机:<strong> "
																									+ result_data.d.Data.PhoneNumber
																									+ " </strong></br>车型: "
																									+ result_data.d.Data.TruckType
																									+ " </br>是否接活："
																									+ IsOnline_text
																									+ "</p>", mouseoverTxt = txt;
																							var opts = {
																								enableMessage : false
																							//设置允许信息窗发送短息
																							};
																							var infoWindow = new BMap.InfoWindow(
																									txt,
																									opts); // 创建信息窗口对象 
																							map
																									.openInfoWindow(
																											infoWindow,
																											pt); //开启信息窗口

																						});
																	}
																}
															});

												});
							})();*/

						}

						markerClusterer = new BMapLib.MarkerClusterer(map, {
							markers : markers
						});

						document.getElementById('doing').style.display = 'none';
						document.getElementById('button').disabled = false;

					}
				});

	}

	var compOverlays = [];
	function clearAllAdress() {
		for (var i = 0; i < compOverlays.length; i++) {
			map.removeOverlay(compOverlays[i]);
		}
	}
</script>

