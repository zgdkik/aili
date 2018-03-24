
var marker;
var deptBoundary;
//var otherBoundary;
var map;
var deptCode;

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
	// 创建控件
	var myZoomCancel = new myZoomControl("#fff",70, 10);
	var myZoomTopL = new myZoomTopLeft("#fff",160, 18);
	// 添加到地图当中
	map.addControl(myZoomCancel);
	map.addControl(myZoomTopL);
	//var mapButtonStr="<input id=\"\" class=\"easyui-searchbox\" data-options=\"prompt:'地址定位',searcher:doSearch\" ></input>";
	var mapButtonStr="<input type=\"text\"  id=\"suggestId\" size=\"20\" value=\"百度\" style=\"width:150px;border-radius:5px 5px 5px 5px\" />";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"addressLocator\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">快速定位</button>";
	mapButtonStr += "<button class=\"easyui-linkbutton\" data-options=\"plain:true\" id=\"mapini\" style=\"margin-left:10px;color: #fff; background-color: #5bc0de; border-color: #46b8da;\">返回</button>";
	var DistanceTool="<ul id=\"DistanceTool\"><li class=\"nonoe\"><a class=\"map-measure\" ><span class=\"last measure\"> </span></a></li><i>鼠标测距</></li><ul>";
	$("#myzoom").append(mapButtonStr);
	$("#myzoom2").append(DistanceTool);
	$("#myzoom").css({"border-radius":"5px 5px 5px 5px"});
	$.parser.parse('#myzoom');//渲染
	//获取省网点总数信息
	getPronviceNetworkCount();
	$("body").on("click","#mapini",function(){
		removeOverLays("Complex");
		getPronviceNetworkCount();
	});
	$("body").on("click","#addressLocator",function(){
		addressLocator();
	});
	$("body").on("click","#DistanceTool",function(){
		myDis.open();
	});
	$("#suggestId").keydown(function() {
		 if (event.keyCode == "13") {//keyCode=13是回车键
			 addressLocator();
	     }
	  });
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
			map.centerAndZoom(pp, 10);
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
	function mapDraw(data){
		if (data != null && data.success) {
			removeOverLays("Polygon");
			if(data.data.deptCoor){
				removeMarker();
				if(data.data.deptCoor.electroncmapCoor){
					var longitude=data.data.deptCoor.electroncmapCoor.split(",")[0];
					var latitude=data.data.deptCoor.electroncmapCoor.split(",")[1];
					var  point=new BMap.Point(longitude,latitude)
					marker = new BMap.Marker(point);
					map.addOverlay(marker); 
					if(map.getZoom()==12){
						map.panTo(point);
					}else{
						map.centerAndZoom(point, 12);
					}
					marker.enableDragging();
				}
				if(data.data.deptCoor.electroncmapRange){
					var pointsStr=data.data.deptCoor.electroncmapRange.split(";");
					var pointArray=[];
					var point;
					for(var i=0;i<pointsStr.length;i++){
						point=new BMap.Point(pointsStr[i].split(",")[0],pointsStr[i].split(",")[1]);
						pointArray[i]=point;
					}
					getBoundary(pointArray,"#0099CC",1,deptName,null,null);
				}else{
					var cityName="无";
					var address="无";
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
							$.messager.alert('提示','无法获取网点坐标！请维护网点信息','info');
						}
					}, cityName);
					
					
				}
				
			}
			
			
			if(data.data.deptCoorList.length>0){
				for(var i=0;i<data.data.deptCoorList.length;i++){
					if(data.data.deptCoorList[i].electroncmapRange){
						var pointsStr=data.data.deptCoorList[i].electroncmapRange.split(";");
						var pointArray=new Array();
						for(var j=0;j<pointsStr.length;j++){
							point=new BMap.Point(pointsStr[j].split(",")[0],pointsStr[j].split(",")[1]);
							pointArray[j]=point;
						}
						var centerPoint=undefined;
						if(data.data.deptCoorList[i].electroncmapCoor){
							centerPoint=new BMap.Point(data.data.deptCoorList[i].electroncmapCoor.split(",")[0],data.data.deptCoorList[i].electroncmapCoor.split(",")[1]);
						}
						var deptName=data.data.deptCoorList[i].deptName;
						var deptPhone=data.data.deptCoorList[i].phoneNo;
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
	function menuClick(event, treeId, treeNode, clickFlag) {
		if(!treeNode.isParent){
			deptCode=treeNode.id;
			ym.sendPost(base + "/map/getDept/" + treeNode.id, null, {
				successHandler : function(data, textStatus, jqXHR) {
					mapDraw(data);
				}
			});
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
			if(!centerPoint){
				centerPoint=pointArray[0];
			}
			var otherBoundary=new BMap.Polygon(pointArray, {strokeWeight: 2, strokeColor: "#ff0000",fillColor:fillColor}); //建立多边形覆盖物
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
	function removeOverLays(OverLaysType){
		if(OverLaysType=="Polygon"){
			OverLaysType=BMap.Polygon;
		}
		if(OverLaysType=="Complex"){
			OverLaysType=ComplexNetworkOverlay;
		}
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++){
			if(allOverlay[i] instanceof OverLaysType){
				map.removeOverlay(allOverlay[i]);
			}
		}
	}
	function removeMarker(){
		if(marker instanceof BMap.Marker){
			map.removeOverlay(marker);
		}
	}
	$("#searchNetwork").click(function(){
		deptCode=$("#network").combobox("getValue");
		if(deptCode){
			ym.sendPost(base + "/map/getDept/" + deptCode, null, {
				successHandler : function(data, textStatus, jqXHR) {
					removeOverLays("Complex");
					mapDraw(data);
				}
			});
		}else{
			var provinceCode=$("#province-city").combobox("getValue");
			var provinceName=$("#province-city").combobox("getText");
			if(provinceCode){
				removeOverLays("Complex");
				getProvinceNetwork(provinceCode,provinceName);
			}else{
				getPronviceNetworkCount();
			}
			
		}
		
	});
	
	function getPronviceNetworkCount(){
		map.centerAndZoom(new BMap.Point(116.403765, 39.914850), 5);
		removeOverLays("Polygon");
		ym.sendPost(base + "/map/getProvinceNeworkList", null, {
			successHandler : function(data, textStatus, jqXHR) {
				for(var i=0;i<data.data.length;i++){
					getProvinceCompOverlay(data.data[i]);
				}
			}
		});
	}
	function getProvinceCompOverlay(data){
		var myCompOverlay;
		var provinceCompOverlay;
		var myGeo = new BMap.Geocoder();
		myGeo.getPoint(data.provinceName, function(point){
			if (point) {
				provinceCompOverlay= new ComplexNetworkOverlay(point, data.provinceName+"（"+data.networkCount+"）");
				map.addOverlay(provinceCompOverlay);
				provinceCompOverlay.addEventListener('click',function(e){
					//此对象是个覆盖物Div
					removeOverLays("Complex");
					getProvinceNetwork(data.provinceCode,data.provinceName);
				});
			}
		},data.provinceName);
	}
	//根据省编码查询省所有网点
	function getProvinceNetwork(provinceCode,provinceName){
		ym.sendPost(base + "/map/getNeworkByProvince/" + provinceCode, null, {
			successHandler : function(data, textStatus, jqXHR) {
				removeOverLays("Polygon");
				var myGeo = new BMap.Geocoder();
				myGeo.getPoint(provinceName, function(point){
					if (point) {
						map.centerAndZoom(point, 9);
					}
				}, provinceName);
				for(var i=0;i<data.data.length;i++){
					var centerPoint=undefined;
					if(data.data[i].electroncmapCoor){
						centerPoint=new BMap.Point(data.data[i].electroncmapCoor.split(",")[0],data.data[i].electroncmapCoor.split(",")[1]);
					}
					if(data.data[i].electroncmapRange){
						var pointsStr=data.data[i].electroncmapRange.split(";");
						var pointArray=new Array();
						for(var j=0;j<pointsStr.length;j++){
							point=new BMap.Point(pointsStr[j].split(",")[0],pointsStr[j].split(",")[1]);
							pointArray[j]=point;
						}
						var deptName=data.data[i].deptName;
						var deptPhone=data.data[i].phoneNo;
						getBoundary(pointArray,"#333333",3,deptName,deptPhone,centerPoint);
					}
					
				}
			}
		});
	}

});


