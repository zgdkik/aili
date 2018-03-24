
var marker;
var deptBoundary;
//var otherBoundary;
var map;
var deptCode;
var markerInfo;

$(document).ready(function() {
	var setting = {
		async : {
			enable : true,
			url : base + "/dept/getTree",
			autoParam : [ "id", "name" ],
			dataFilter : filter
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : menuClick
		}
	};
	var zTree  = $.fn.zTree.init($("#depttree"), setting);
	setTimeout(function(){
		var treenode = zTree.getNodeByParam("id", "yimidida", null);
		zTree.expandNode(treenode, true, true, null, false);
	},100);
	
	//百度地图API功能
	map = new BMap.Map("mapdiv",{enableMapClick:false});
	map.centerAndZoom(new BMap.Point(116.403765, 39.914850), 5);
	map.enableScrollWheelZoom();
	var scale_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var navigation = new BMap.NavigationControl(); 
	map.addControl(scale_control);        
	map.addControl(navigation);
	var myDis = new BMapLib.DistanceTool(map);
	//setTimeout(function(){
	
	//}, 2000);
	var overlays = [];
	var overlaycomplete = function(e) {
		deptBoundary=e.overlay;
		if($("#polylineDrow").text()=="绘制范围"){
			$("#polylineDrow").text("清除重画");
		}
	};
	var styleOptions = {
		strokeColor : "red", //边线颜色。
		fillColor : "green", //填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight : 3, //边线的宽度，以像素为单位。
		strokeOpacity : 0.8, //边线透明度，取值范围0 - 1。
		fillOpacity : 0.6, //填充的透明度，取值范围0 - 1。
		strokeStyle : 'solid', //边线的样式，solid或dashed。
	}
	//实例化鼠标绘制工具
	var drawingManager = new BMapLib.DrawingManager(map, {
		isOpen : false, //是否开启绘制模式
		enableDrawingTool : false, //是否显示工具栏
		drawingToolOptions : {
			anchor : BMAP_ANCHOR_TOP_RIGHT, //位置
			offset : new BMap.Size(5, 5), //偏离值
		},
		//circleOptions : styleOptions, //圆的样式
		//polylineOptions : styleOptions, //线的样式
		polygonOptions : styleOptions, //多边形的样式
		//rectangleOptions : styleOptions
	//矩形的样式
	});
	//添加鼠标绘制工具监听事件，用于获取绘制结果
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);
	function clearAll() {
		for (var i = 0; i < overlays.length; i++) {
			map.removeOverlay(overlays[i]);
		}
		overlays.length = 0
	}
	// $(".BMapLib_polyline").attr("title","清除重画");	

	// $(".BMapLib_Drawing_panel").css({"display":"none"});

	
	// 创建控件
	//var myZoomConfrim = new myZoomControl("清除重画","#50A0E6",addressTagging,10,10);
	var myZoomCancel = new myZoomControl("#fff",70, 10);
	var myZoomTopL = new myZoomTopLeft("#fff",180, 18);
	// 添加到地图当中
	//map.addControl(myZoomConfrim);
	map.addControl(myZoomCancel);
	map.addControl(myZoomTopL);
	
	//var mapButtonStr="<input id=\"\" class=\"easyui-searchbox\" data-options=\"prompt:'地址定位',searcher:doSearch\" ></input>";
	var mapButtonStr="<input type=\"text\"  id=\"suggestId\" size=\"20\" value=\"百度\" style=\"width:150px;border-radius:5px 5px 5px 5px\" />";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"addressLocator\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">快速定位</button>";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"deptTagging\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">网点标注</button>";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"polylineDrow\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">绘制范围</button>";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"polylineEdit\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">调整范围</button>";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"saveDept\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">保存</button>";
	var DistanceTool="<div class=\"map-measure\" id=\"DistanceTool\" ><span class=\"measure\"> </span> 鼠标测距<ul></div>";
	DistanceTool+="<div class=\"map-measure\" id=\"measureLocator\" ><span class=\"measureLocator\"> </span> 辅助标记<ul></div>";
	$("#myzoom").append(mapButtonStr);
	$("#myzoom2").append(DistanceTool);
	$("#myzoom").css({"border-radius":"5px 5px 5px 5px"});
	$.parser.parse('#myzoom');//渲染
	$("body").on("click","#deptTagging",function(){
		deptTagging();
	});
	$("body").on("click","#polylineDrow",function(){
		polylineDrow();
	});
	$("body").on("click","#polylineEdit",function(){
		polylineEdit();
	});
	$("body").on("click","#saveDept",function(){
		saveDept();
	});
	$("body").on("click","#DistanceTool",function(){
		myDis.open();
		var allOverlay = map.getOverlays();
	});
	var MakerInfoFlag=0;
	$("body").on("click","#measureLocator",function(){
		if(MakerInfoFlag==0){
			map.addEventListener("click", createMakerInfo);
			MakerInfoFlag++;
		}
	});
	
	
	$("body").on("click","#addressLocator",function(){
		addressLocator();
	});
	
	
	 $("#suggestId").keydown(function() {
		 if (event.keyCode == "13") {//keyCode=13是回车键
			 addressLocator();
	     }
	  });
	function deptTagging(){
		if(!deptCode){
			$.messager.alert("提示","请先选择一个网点进行操作","info");
			return;
		}
		drawingManager.close();
		 map.addEventListener("click", mapClick);
	}
	function polylineDrow() {
		if(!deptCode){
			$.messager.alert("提示","请先选择一个网点进行操作","info");
			return;
		}
		if(deptBoundary){
			map.removeOverlay(deptBoundary);
		}
		deptBoundary=null;
		drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
		drawingManager.open();
	}
	function polylineEdit() {
		if(!deptCode){
			$.messager.alert("提示","请先选择一个网点进行操作","info");
			return;
		}
		drawingManager.close();
		if(deptBoundary){
			deptBoundary.enableEditing();
		}
	}
	function addressLocator(){
		if($("#suggestId").val()){
			var myGeo = new BMap.Geocoder();
			myGeo.getPoint($("#suggestId").val(), function(point){
				if (point) {
					removeMarker();
					marker = new BMap.Marker(point);
					map.addOverlay(marker); 
					if(map.getZoom()==12){
						map.panTo(point);
					}else{
						map.centerAndZoom(point, 12);
						}
					marker.enableDragging();
				}else{
					$.messager.alert('提示','无法定位!请确认地址信息','info');
				}
			},"" );
		}else{
			$.messager.alert('提示','请输入地址','info');
		}
	}
	function mapClick(e){
		if(marker instanceof BMap.Marker){
			//$.messager.alert("提示","无需再次标注,可拖动标注进行位置调整","info");
			map.removeEventListener("click", mapClick);
		}else{
			marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
			map.addOverlay(marker); 
			marker.enableDragging();
		}
	}                                            
	var AcIcon = new BMap.Icon(base+"/images/map/markers.png", new BMap.Size(23, 25), {  
        offset: new BMap.Size(10, 25), // 指定定位位置  
        imageOffset: new BMap.Size(0, 0 - 10 * 30) // 设置图片偏移  
    }); 
	function createMakerInfo(e){
		$.messager.prompt('备注', '请输入标记备注(辅助标记,不进行保存)', function(r){
			if (r){
				markerInfo = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat),{icon:AcIcon});
				map.addOverlay(markerInfo); 
				markerInfo.enableDragging();
				map.removeEventListener("click", createMakerInfo);
				var label = new BMap.Label(r,{offset:new BMap.Size(20,-10)});
				markerInfo.setLabel(label);
				MakerInfoFlag=0;
			}else{
				map.removeEventListener("click", createMakerInfo);
				MakerInfoFlag=0;
			}
		});
		
	}
	function saveDept(){
		if(!deptCode){
			$.messager.alert("提示","请先选择一个网点进行操作","info");
			return;
		}
		if(deptBoundary){
			deptBoundary.disableEditing();
		}
		var electroncmapCoor="";
		if(marker){
			electroncmapCoor=marker.point.lng+","+marker.point.lat;
		}
		var electroncmapRange="";
		if(deptBoundary){
			var points=deptBoundary.getPath();
			for(var i=0;i<points.length;i++){
				if(i==0){
					electroncmapRange+=points[i].lng+","+points[i].lat;
				}else{
					electroncmapRange+=";"+points[i].lng+","+points[i].lat;
				}
			}
		}
		var param={"electroncmapCoor":electroncmapCoor,"electroncmapRange":electroncmapRange,"deptCode":deptCode};
		ym.sendPost(base + "/map/save/", param, {});
		
	}

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		//G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		//G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		//map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 12);
			removeMarker();
			marker=new BMap.Marker(pp)
			map.addOverlay(marker);    //添加标注
			//marker.addEventListener("click", showOverlayInfo);  //添加marker单击事件
			marker.enableDragging();
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
	function G(id) {
		return document.getElementById(id);
	}
	function showOverlayInfo(e){  
		 if (e.target.toString() == '[object Polygon]')  
		 {  
		      alert("你单击的是多边形");  
		 }  
		 else if(e.target instanceof BMap.Marker)  
		 {  
		 alert("你单击的是Marker点喔~");  
		 }  
		  
	}
	function menuClick(event, treeId, treeNode, clickFlag) {
		if(!treeNode.isParent){
			deptCode=treeNode.id;
			ym.sendPost(base + "/map/getDept/" + treeNode.id, null, {
				successHandler : function(data, textStatus, jqXHR) {
					drawingManager.close();
					mapDraw(data);
				}
			});
		}
		
	}
	function mapDraw(data){
		if (data != null && data.success) {
			removeAllOverLays();
			if(data.data.deptCoor){
				removeMarkerAndMarkerInfo();
				if(data.data.deptCoor.electroncmapRange){
					$("#polylineDrow").text("清除重画");
					var pointsStr=data.data.deptCoor.electroncmapRange.split(";");
					var pointArray=[];
					var point;
					for(var i=0;i<pointsStr.length;i++){
						point=new BMap.Point(pointsStr[i].split(",")[0],pointsStr[i].split(",")[1]);
						pointArray[i]=point;
					}
					getBoundary(pointArray,"#0099CC",1,deptName);
				}else{
					if($("#polylineDrow").text()=="清除重画"){
						$("#polylineDrow").text("绘制范围");
						$.parser.parse('#myzoom');//渲染
					}
				}
				if(data.data.deptCoor.electroncmapCoor){
					var longitude=data.data.deptCoor.electroncmapCoor.split(",")[0];
					var latitude=data.data.deptCoor.electroncmapCoor.split(",")[1];
					var  point=new BMap.Point(longitude,latitude)
					marker = new BMap.Marker(point);
					map.addOverlay(marker); 	
					//当地图缩放级别固定 则不需要设置缩放级别
					if(map.getZoom()==12){
						map.panTo(point);
					}else{
						map.centerAndZoom(point, 12);
					}
					
					marker.enableDragging();
				}else{
					var cityName="无";
					var address="无";
					//deptBoundary=undefined;
					if(data.data.deptVo.deptAddress){
						address=data.data.deptVo.deptAddress;
					}
					if(data.data.deptVo.cityName){
						cityName=data.data.deptVo.cityName;
					}
					var myGeo = new BMap.Geocoder();
					myGeo.getPoint(address, function(point){
						if (point) {
							marker = new BMap.Marker(point);
							map.addOverlay(marker); 
							if(map.getZoom()==12){
								map.panTo(point);
							}else{
								map.centerAndZoom(point, 12);
							}
							marker.enableDragging();
						}else{
							$.messager.alert('提示','无法获取网点坐标！请在地图上标注','info');
							marker=undefined;
						}
					}, cityName);
					
				
				}
				
			}
			if(data.data.deptCoorList.length>0){
				for(var i=0;i<data.data.deptCoorList.length;i++){
					if(data.data.deptCoorList[i].electroncmapRange){
						var pointArray=new Array();
						var pointsStr=data.data.deptCoorList[i].electroncmapRange.split(";");
						for(var j=0;j<pointsStr.length;j++){
							point=new BMap.Point(pointsStr[j].split(",")[0],pointsStr[j].split(",")[1]);
							pointArray[j]=point;
						}
						var deptName=data.data.deptCoorList[i].deptName;
						var deptPhone=data.data.deptCoorList[i].phoneNo;
						var centerPoint=undefined;
						if(data.data.deptCoorList[i].electroncmapCoor){
							centerPoint=new BMap.Point(data.data.deptCoorList[i].electroncmapCoor.split(",")[0],data.data.deptCoorList[i].electroncmapCoor.split(",")[1]);
						}
						
						getBoundary(pointArray,"#333333",2,deptName,deptPhone,centerPoint);
					}
					
				}
			
			}
			var deptAddress="";
			var deptName="";
			var deptCode="";
			var phoneNo="";
			if(data.data.deptVo.deptAddress){
				deptAddress=data.data.deptVo.deptAddress;
			}
			if(data.data.deptVo.deptName){
				deptName=data.data.deptVo.deptName;
			}
			if(data.data.deptVo.phoneNo){
				phoneNo=data.data.deptVo.phoneNo;
			}
			if(data.data.deptVo.deptCode){
				deptCode=data.data.deptVo.deptCode;
			}
			$("#deptAddress").text(deptAddress);
			$("#compCode").val(data.data.deptVo.compCode);
			$("#deptName").text(deptName);
			$("#deptCode").text(deptCode);
			$("#phoneNo").text(phoneNo);
		}else{
			$.messager.alert('错误',data.msg,'error');
		}
	
	}
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	
	var sContent ;
	var infoMarker;
	var infoWindow;
	
	
	function getBoundary(pointArray,fillColor,boundaryType,deptName,deptPhone,centerPoint){       
	      //获取行政区域
		if(boundaryType==1){
			deptBoundary = new BMap.Polygon(pointArray, {strokeWeight: 2, strokeColor: "#ff0000",fillColor:fillColor}); //建立多边形覆盖物
			map.addOverlay(deptBoundary);  //添加覆盖物
			//map.setViewport(pointArray);    //调整视野 
		}else{
			var otherBoundary=new BMap.Polygon(pointArray, {strokeWeight: 2, strokeColor: "#ff0000",fillColor:fillColor}); //建立多边形覆盖物
			if(!centerPoint){
				centerPoint=pointArray[0];
			}
			otherBoundary.addEventListener("click", function(){
				sContent="";
				if(!deptPhone){
					deptPhone="";
				}
				if(!deptName){
					deptName="";
				}
				sContent +=	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>网点名称:"+deptName+"</h4>";
				sContent +=	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>网点电话:"+deptPhone+"</h4>";
				infoMarker=new BMap.Marker(pointArray[0]);
				infoWindow = new BMap.InfoWindow(sContent); 
				map.openInfoWindow(infoWindow,centerPoint);
			});
//			otherBoundary.addEventListener("mouseout", function(){
//				
//				map.closeInfoWindow();
//			});
			map.addOverlay(otherBoundary);  //添加覆盖物
		}
		          
	}
	//移除地图上的覆盖物
	function removeAllOverLays(){
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++){
			if(allOverlay[i] instanceof BMap.Polygon){
				map.removeOverlay(allOverlay[i]);
			}
		}
	}
	function removeMarker(){
		if(marker instanceof BMap.Marker){
			map.removeOverlay(marker);
		}
	}
	function removeMarkerAndMarkerInfo(){
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++){
			if(allOverlay[i] instanceof BMap.Marker){
				map.removeOverlay(allOverlay[i]);
			}
		}
	}
	$('#autocomplete').combobox({
		url : base+'/map/autocomplete',
		textField : 'deptName',
		valueField : 'deptCode',
		mode : 'remote',
		delay : 500,
		value : '',
		onSelect:searchNetwork
	});
	function searchNetwork(){
		deptCode=$("#autocomplete").combobox("getValue");
		drawingManager.close();
		if(deptCode){
			ym.sendPost(base + "/map/getDept/" + deptCode, null, {
				successHandler : function(data, textStatus, jqXHR) {
					mapDraw(data);
				}
			});
		}else{
			$.messager.alert("提示","请输入网点名称");
		}
		
	}
	$("#searchNetwork").click(function(){
		searchNetwork();
	});
	
});




