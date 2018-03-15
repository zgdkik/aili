<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>

<link href="/jeegis/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="/jeegis/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<link href="/jeegis/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
<!--[if lte IE 7]><link href="/jeegis/static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="/jeegis/static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="/jeegis/static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<link href="/jeegis/static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="/jeegis/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="/jeegis/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="/jeegis/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="/jeegis/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="/jeegis/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="/jeegis/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/jeegis/static/common/mustache.min.js" type="text/javascript"></script>
<link href="/jeegis/static/common/jeesite.css" type="text/css" rel="stylesheet" />
<script src="/jeegis/static/common/jeesite.js" type="text/javascript"></script>

<script type="text/javascript">var ctx = '/jeegis/a', ctxStatic='/jeegis/static';</script>		
	<!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>
	
	
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
<html>
<head>
	<title>运力分布管理图</title>
	<meta name="decorator" content="default"/>

<link href="http://api.map.baidu.com/res/12/bmap.css" rel="stylesheet" type="text/css">
<script src="http://api.map.baidu.com/api?v=2.0&ak=hF4dbGT9jWPE9c4dboQQSM2f"></script>

    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>

   
<script type="text/javascript" src="${ctxStatic}/provincecityarea/area.js"></script>
<script type="text/javascript" src="${ctxStatic}/provincecityarea/location.js"></script>
<link href="${ctxStatic}/provincecityarea/common.css" rel="stylesheet"/>
<link href="${ctxStatic}/provincecityarea/select2.css" rel="stylesheet"/>
<script src="${ctxStatic}/provincecityarea/select2.js"></script>

  <script type="text/javascript">


Date.prototype.Format = function(fmt) 
{ //author: meizz 
 var o = { 
   "M+" : this.getMonth()+1,                 //月份 
   "d+" : this.getDate(),                    //日 
   "H+" : this.getHours(),                   //小时 
   "m+" : this.getMinutes(),                 //分 
   "s+" : this.getSeconds(),                 //秒 
   "q+" : Math.floor((this.getMonth()+3)/3), //季度 
   "S"  : this.getMilliseconds()             //毫秒 
 }; 
 if(/(y+)/.test(fmt)) 
   fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
 for(var k in o) 
   if(new RegExp("("+ k +")").test(fmt)) 
 fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
 return fmt; 
}


  $(document).ready(function(){
	  $("#btnMenu").hide();
	   var now = new Date();
	   
	   var time1 = new Date().Format("yyyy-MM-dd HH:mm:ss"); 
	  $("#endDate").val(time1);
	  var d2=new Date(now); 
	  d2.setFullYear(d2.getFullYear()-1); 
	  d2.setDate(d2.getDate()-1); 
	   var time2 = d2.Format("yyyy-MM-dd HH:mm:ss"); 
	  $("#startDate").val(time2);
  });



	
	var map = null;
	var markers = [];//标注
	var dictionarys=[];//区域数据
	var labels=[];//备注
	var markerClusterer = null;//聚合图
	var pointCollection = null;//麻点图
	var bpoints =[];//点
	var reliPoints=[];//热力点
	var heatmapOverlay =null;
	var startIcon = new BMap.Icon("${ctxStatic}/spellbillmanage/images/juhe1.png", new BMap.Size(35,20));
	var tujingzhaungIcon = new BMap.Icon("${ctxStatic}/spellbillmanage/images/tujingzhaung.png", new BMap.Size(28,32));
		$(document).ready(function() {
			
			map = new BMap.Map("map");
			map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
			map.enableScrollWheelZoom();
			var point = new BMap.Point(121.487899,31.249162);
			map.centerAndZoom(point, 10);
		});


		function clearAllmarkerPoints() {
			for (var i = 0; i < markers.length; i++) {
				map.removeOverlay(markers[i]);
			}
		}


		//隐藏统计
		function hideArea(){
			if (null != markerClusterer) {
				markerClusterer.clearMarkers();
			}
			if (null != pointCollection) {
				pointCollection.clear();
			}
			if (null != heatmapOverlay) {
				heatmapOverlay.hide();
			}			
			clearAllAdress();
			map.clearOverlays();
			
		}
		//显示区域
		function showArea(){
			//clearAllAdress();
			
			var province = $('#loc_province').select2('data').text;
			var city = $('#loc_city').select2('data').text;
			if(!city||city==""||city=="地级市"){
					alertx("请选择城市！");
					document.getElementById('btnSubmit').disabled = false;
					return false;
				}
			var areas =[];
			$("#loc_town option").each(function () {
	             areas.push($(this).text());
	         });
			for(var t =1;t<areas.length;t++){
			(function(){
				
					if(dictionarys&&dictionarys.length>0){
					(function(){
						// 创建地址解析器实例
						var myGeo = new BMap.Geocoder();
						// 将地址解析结果显示在地图上,并调整地图视野
							var tempArea =areas[t];
							myGeo.getPoint(province+city+areas[t], function(point){
							
							if (point) {
								var mk = new BMap.Marker(point,{icon:tujingzhaungIcon});
								mk.setTop(true);
								map.addOverlay(mk);
								
									
									
									var showLabelTxt="";
									var isHave =false;
									for(var j =0;j<dictionarys.length;j++){
										if(trim(tempArea)==trim(dictionarys[j].Key)){
											isHave=true;
											showLabelTxt =dictionarys[j].Key+new Number(dictionarys[j].Value);
										}
									}
									if(!isHave){
										showLabelTxt=tempArea+"*未知错误！*";
									}
									
									var labelway = new BMap.Label("<span style='background:#FF7F00;color: #fff;font-size: 14px; padding:5px;background:#FF7F00;-moz-border-radius: 15px;-webkit-border-radius: 15px;border-radius:15px;' >"+showLabelTxt+"</span>",{offset:new BMap.Size(20,-10)});
									labels.push(labelway);
											labelway.setStyle({
												 fontSize : "14px",
												 height : "20px",
												 lineHeight : "20px",
												 fontFamily:"微软雅黑",
												 backgroundColor:null,
												 border:null
											 });
									mk.setLabel(labelway);
								
								compOverlays.push(mk);
							}else{
								
							}
						}, province+city);
					  })();
					}
					//循环请求 获得城市内区域的边界
					var bdary = new BMap.Boundary();
					bdary.get(province+city+areas[t], function(rs){       //获取行政区域
						var count = rs.boundaries.length; //行政区域的点有多少个
						
			          	var pointArray = [];
						for (var i = 0; i < count; i++) {
							var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#458B00",fillColor:"#fff",fillOpacity:0.1}); //建立多边形覆盖物
							map.addOverlay(ply);  //添加覆盖物
							pointArray = pointArray.concat(ply.getPath());
							compOverlays.push(ply);
						}    
					});   
			 	})(); 
			}
					
		}
		//查寻
		function queryTrack(ctown) {
			var mapType = $('#mapType').val();	
				 markers.length=0;//标注
				 markers =[];
				 dictionarys.length=0;//区域数据
				 dictionarys=[];
				 labels.length=0;//备注
				 labels=[];
				 markerClusterer = null;//聚合图
				 pointCollection = null;//麻点图
				 bpoints.length=0;//点
				 bpoints=[];
				reliPoints.length=0;//点
				reliPoints=[];
				if (null != markerClusterer) {
					markerClusterer.clearMarkers();
				}
				if (null != pointCollection) {
					pointCollection.clear();
				}
				if (null != heatmapOverlay) {
					heatmapOverlay.hide();
				}
				
				
				
				document.getElementById('btnSubmit').disabled = true;
				//document.getElementById('doing').style.display = 'block';
				markers = [];
				clearAllmarkerPoints();
				map.clearOverlays();
				//showArea();
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var province = $('#loc_province').select2('data').text;
				var city = $('#loc_city').select2('data').text;
				if(!city||city==""||city=="地级市"){
					alertx("请选择城市！");
					document.getElementById('btnSubmit').disabled = false;
					return false;
				}
				var area ="";
				if(ctown){
					area = ctown;
				}else{
					area = $('#loc_town').select2('data').text;
				}
				if(!area||area==""||area=="市县区"){
					area="";
				}
			
				var truckType = $("#truckType").val();
				var verified = $("#verified").val();
				var type = $("#mapType").val();
				var addressType = $("#addressType").val();
				
				//根据城市查询当前城市下有多少区域 并描绘区域边界和求取区域中心坐标
				map.centerAndZoom(province+city, 12);
				try{
					resetTip();
					showTip('正在查询，请稍等...', 'info', 5000000, 100);
				}catch (e) { }	
				// 创建地址解析器实例
				var myGeo = new BMap.Geocoder();
				$.ajax({
					url : '${pageContext.request.contextPath}/p/maptools/capacityMap/getTruckList',
					type : 'post',
					data : '&startDate='+startDate+'&endDate='+endDate+"&province="+province+"&city="+city+"&area="+area+"&type="+type+"&truckType="+truckType+"&verified="+verified+"&addressType="+addressType,
					async : true,
					error : function() {
						alert('数据请求出错！请联系管理员');
						document.getElementById('btnSubmit').disabled = false;
						//document.getElementById('doing').style.display = 'none';
							try{
								// 恢复提示框显示
								resetTip();
								// 关闭提示框
								closeTip();
							}catch (e) 
							{ }	
					},
					success : function(data) {
						document.getElementById('btnSubmit').disabled = false;
							try{
								// 恢复提示框显示
								resetTip();
								// 关闭提示框
								closeTip();
							}catch (e) 
							{ }	
						if(data.status==false){
							alert('数据请求出错！请联系管理员');
							return false;
						}
						if(data==null||data==""){
							alert('没有查询到数据');
							return false;
						}
						//解析数据
							
						
						var rs = eval("("+ data[0].rs+ ")");
						var realRs = eval("("+ rs+ ")").d;
						var ErrCode =  realRs.ErrCode;//是否异常
						if(ErrCode&&ErrCode!="0"){
							alertx("数据请求发生错误！详细："+realRs.ErrMsg);
						}
						//总共获得数量
						var CountNum = realRs.Data.CountNum;
						$("#CountNum").html("为您查询到"+CountNum+"条数据");
						//Dictionary区域统计 数组 Key: "徐汇区" Value: 9
						dictionarys = realRs.Data.Dictionary;
						compOverlays.push(dictionarys);
						var htmlDictionarys =""
						for(var dindex in dictionarys){
							htmlDictionarys+="<li class='btns'><a herf='#' >"+dictionarys[dindex].Key+"("+dictionarys[dindex].Value+")</a></br></li>";
						}
						$("#dictionary").html(htmlDictionarys);
						//HeatTrucks司机信息数组 
						//AgentName: ""
						//CreateTime: "2015-10-13 08:56:49"
						//Id: "2d93a4f8-b15b-4af4-b0b2-73a6a6f3f1be"
						//Location: Object
							//Address: "上海市浦东新区X005(陈行公路)"
							//AddressName: ""
							//Altitude: 0
							//Area: "浦东新区"
							//City: "上海市"
						//Country: null
						//Latitude: 31.129987
						//Longitude: 121.533553
						//Province: "上海市"
						//Name: "黄德冬"
						//PhoneNumber: "18817580918"
						//TruckName: "面包车"
						//TruckNo: "晚CHD728"
						var HeatTrucks = realRs.Data.HeatTrucks;
						
						//var points = eval("("+ eval("(" + data + ")").orderList + ")");

						
						for (var i = 0; i < HeatTrucks.length; i++) {
							try 
							{ 
							var p =new BMap.Point(HeatTrucks[i].Location.Longitude,HeatTrucks[i].Location.Latitude);
							bpoints.push(p);
							if(mapType=='2'){
								var relip = {"lng":HeatTrucks[i].Location.Longitude,"lat":HeatTrucks[i].Location.Latitude,"count":50};
								reliPoints.push(relip);
							}
							var marker = new BMap.Marker(p, {
								icon : startIcon
							});
							marker.id = HeatTrucks.Id;
							marker.HeatTruck=HeatTrucks[i];
							
							markers.push(marker);
							 (function(){
									
									 marker.addEventListener('click', function(e){
											
									//先去检索后台 调用接口app后台 查询司机信息				
									
										var pt = new BMap.Point(e.point.lng,e.point.lat);
										var phoneNumber = e.target.HeatTruck.PhoneNumber;
									   var phoneNumber_end2 ="";
										   if(phoneNumber&&null!=phoneNumber&&phoneNumber!=""){
											  phoneNumber_end2 = phoneNumber.substr(phoneNumber.length-2,phoneNumber.length);
										   }
											   var imgSrc = "http://photo2.yihaohuoche.com/"+phoneNumber_end2+"/phone-"+phoneNumber+"-Portrait-small.jpg";
											   var txt ="<p class='turack_driver'><img  src="+imgSrc+" ></img></p><p class='text_css_style'>地址:"+e.target.HeatTruck.Location.Address+" </br>注册时间："+e.target.HeatTruck.CreateTime+" </br>司机名称: "+e.target.HeatTruck.Name+" </br>司机手机:<strong> "+e.target.HeatTruck.PhoneNumber+" </strong></br>车型: "+e.target.HeatTruck.TruckName+" </br>车牌："+e.target.HeatTruck.TruckNo+"</p>", mouseoverTxt = txt ;
											   var opts = {
														enableMessage:false//设置允许信息窗发送短息
													   };
													var infoWindow = new BMap.InfoWindow(txt,opts);  // 创建信息窗口对象 
													map.openInfoWindow(infoWindow,pt); //开启信息窗口
												
											
															
														
											
											
								});    
							})(); 
							
							
							}catch (e) 
							{ 
							
							}	
						}
						
						
				
					if(mapType=='0'){
						//聚合图
						markerClusterer = new BMapLib.MarkerClusterer(map, {
							markers : markers
						});
						compOverlays.push(markerClusterer);
					}else if(mapType=='1'){
						//麻点图
						var cl = 'red';
				        var options = {
				                size: BMAP_POINT_SIZE_SMALL,
				                shape: BMAP_POINT_SHAPE_CIRCLE,
				                color: cl
				            }
				            pointCollection = new BMap.PointCollection(bpoints, options);  // 初始化PointCollection
				            map.addOverlay(pointCollection);  // 添加Overlay
							compOverlays.push(pointCollection);
					}else{
						//热力图
						
						
						   
							if(!isSupportCanvas()){
								alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
							}
							
							heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
							map.addOverlay(heatmapOverlay);
							heatmapOverlay.setDataSet({data:reliPoints,max:100});
						
							compOverlays.push(heatmapOverlay);
							
							
					}	
						//document.getElementById('doing').style.display = 'none';
						
					}
				});				

			}
			
			//热力图			
			function showReli(){
			if (null != markerClusterer) {
				markerClusterer.clearMarkers();
			}
			if (null != pointCollection) {
				pointCollection.clear();
			}
			if (null != heatmapOverlay) {
				heatmapOverlay.hide();
			}					
				if(!isSupportCanvas()){
								alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
							}
							
							heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
							map.addOverlay(heatmapOverlay);
							heatmapOverlay.setDataSet({data:reliPoints,max:100});
						
							compOverlays.push(heatmapOverlay);
			}
			
			//聚合图
			function showAgg(){
			if (null != markerClusterer) {
				markerClusterer.clearMarkers();
			}
			if (null != pointCollection) {
				pointCollection.clear();
			}
			if (null != heatmapOverlay) {
				heatmapOverlay.hide();
			}					
				//聚合图
				clearAllAdress();
				markerClusterer=null;
				markerClusterer = new BMapLib.MarkerClusterer(map, {
					markers : markers
				});
			}
			//麻点图
			function showMD(){
			if (null != markerClusterer) {
				markerClusterer.clearMarkers();
			}
			if (null != pointCollection) {
				pointCollection.clear();
			}
			if (null != heatmapOverlay) {
				heatmapOverlay.hide();
			}					
				clearAllAdress();
				pointCollection = null;
				//麻点图
				var cl = 'red';
				var options = {
						size: BMAP_POINT_SIZE_SMALL,
						shape: BMAP_POINT_SHAPE_CIRCLE,
						color: cl
					}
					pointCollection = new BMap.PointCollection(bpoints, options);  // 初始化PointCollection
					map.addOverlay(pointCollection);  // 添加Overlay
					compOverlays.push(pointCollection);
			}			
			
			var compOverlays = [];
			function clearAllAdress() {
				for (var i = 0; i < compOverlays.length; i++) {
					map.removeOverlay(compOverlays[i]);
				}
				for (var i = 0; i < markers.length; i++) {
					map.removeOverlay(markers[i]);
				}
					
				
				
				
			}
			
			function setGradient(){
								/*格式如下所示:
								{
									0:'rgb(102, 255, 0)',
									.5:'rgb(255, 170, 0)',
									1:'rgb(255, 0, 0)'
								}*/
								var gradient = {};
								var colors = document.querySelectorAll("input[type='color']");
								colors = [].slice.call(colors,0);
								colors.forEach(function(ele){
									gradient[ele.getAttribute("data-key")] = ele.value; 
								});
								heatmapOverlay.setOptions({"gradient":gradient});
							}
							//判断浏览区是否支持canvas
							function isSupportCanvas(){
								var elem = document.createElement('canvas');
								return !!(elem.getContext && elem.getContext('2d'));
							}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="orderLoadesTogetherMap"  class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>地图：</label>
				<form:select path="mapType" class="input-medium"  style="width:100px;">
					<form:option value="2" label="热力图"/>
					<form:option value="1" label="麻点图"/>
				</form:select>			
			</li>
			<li><label>地域：</label>
				<form:select path="addressType" class="input-medium"  style="width:100px;">
					<form:option value="注册地" label="注册地"/>
					<form:option value="当前地" label="当前地"/>
				</form:select>			
			</li>				
			<li ><label>省市区：</label>
				<select id="loc_province" name="province" style="width:100px;"></select>
				<select id="loc_city" name="city" style="width:100px; margin-left: 10px"></select>
				<select id="loc_town" name="area" style="width:100px;margin-left: 10px"></select>
			</li>						
			<li ><label>车型：</label>
				<form:select path="truckType" class="input-medium" style="width:100px;">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('classificationsTruckType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li ><label>审核通过：</label>
				<form:select path="verified" class="input-medium" style="width:60px;">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>			
									
			<li><label>时间：</label>
				<input id="startDate" name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="line-height: 25px;height: 30px;"
					 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="line-height: 25px;height: 30px;"
					
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="queryTrack();"/></li>
			<li class="btns"><input id="reset" class="btn" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td style="width: 15%;height: 800px;    border-width: 0px;min-width: 200px;" >
				<div style="width: 100%;height: 100%;" id="right">
					<div id="" style="    width: 100%;height: 30px; background-color: #B3AFA5; color: #fff; font-size: x-large; line-height: 30px;text-align: center;">结果面板</div>
					<form:form id="searchForm" modelAttribute="orderLoadesTogetherMap"  class="breadcrumb form-search">
						<ul class="ul-form">
							
							<li class="btns"><input id="showArea11" class="btn btn-primary" type="button" onclick="showArea();" value="行政边界"/></li>
							<li class="btns"><input id="hideArea11" class="btn btn-primary" type="button" onclick="hideArea();" value="清除图层"/></li>
							<li class="btns"><input id="showReli1" class="btn btn-primary" type="button" onclick="showReli();" value="显示热力"/></li>
						
							<li class="btns"><input id="showMD1" class="btn btn-primary" type="button" onclick="showMD();" value="显示麻点"/></li>
						
							<br>
							<li class="btns" ><span id="CountNum" style="background-color: red;color: #fff;"></span></br></li>
							
						</ul>
						<ul class="ul-form" id="dictionary">
							
						</ul>
					</form:form>
					
					
				</div>
			</td>
			<td style="width: 85%;height: 800px;    border-width: 0px;" >
				<div style="width: 100%;height: 100%;" id="map">
				</div>
			</td>
			
		</tr>
	</table>
</body>

</html>
