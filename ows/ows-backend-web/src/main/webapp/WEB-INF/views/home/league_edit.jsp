<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../commons/common-meta.jsp" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
	<script type="text/javascript"
		src="http://api.map.baidu.com/api?v=2.0&ak=IT8OE8Tex2rqr0exakra3DDX3KSwhpKl"></script>
	<script type="text/javascript"
		src="${base}/scripts/map/DrawingManager_min.js"></script>
	<script type="text/javascript" src="${base}/scripts/map/DistanceTool_min.js"></script>
	<link rel="stylesheet"
		href="${base}/styles/map/DrawingManager_min.css" />
	<!--加载检索信息窗口-->
	<script type="text/javascript"
		src="${base}/scripts/map/SearchInfoWindow_min.js"></script>
	<link rel="stylesheet"
		href="${base}/styles/map/SearchInfoWindow_min.css" />
	<link rel="stylesheet"
		href="${base}/resources/statics/easyui/css/formBigInput.css" />	
	<script type="text/javascript"
		src="${base}/scripts/home/league_edit.js?${version}"></script>
	<script type="text/javascript"
		src="${base}/scripts/map/mapUtil.js?${version}"></script>
		<!--  <script type="text/javascript"
		src="${base}/scripts/home/cascade.js?${version}"></script>-->
	<style type="text/css">  
	   .anchorBL{  
	       display:none;  	
	   }  
	   .measure{
	    float: left;
	    height: 15px;
	    width: 29px;
	    background-image: url(http://webmap1.map.bdstatic.com/wolfman/static/common/images/ui3/tools/newtools_9ddbaad.png);
	    background-repeat: no-repeat;
	    background-position: -80px -58px;
	}


.measureLocator {
    float: left;
    height: 40px;
    width: 29px;
    background-image: url(http://webmap1.map.bdstatic.com/wolfman/static/common/images/ui3/tools/newtools_9ddbaad.png);
    background-repeat: no-repeat;
    background-position: -40px -175px;
}
.map-measure{float: left}
</style>  
<title>壹米滴答</title>
</head>
<body class="easyui-layout" style="padding: 5px">
	
	<div class="easyui-layout" style="height: 100%;">
		<div region="west"  title="网点信息" split="true" style="width: 40%;">
				<form id="leagueForm">
	            <table cellpadding="5">
	                <tr>
	                    <td>网点名称:</td>
	                    <td><input class="easyui-textbox" id="name" name="name" data-options="required:true" style="width:250px"/>
	                    <input  id="id" name="id" style="display: none"/>
	                    </td>
	                    
	                </tr>
	                <tr>
	                    <td>联系人:</td>
	                    <td><input class="easyui-textbox" id="linkMan"  name="linkMan" data-options="required:true" style="width:250px"></input></td>
	                </tr>
	                <tr>
	                    <td>联系电话:</td>
	                    <td><input class="easyui-numberbox" id="linkPhone" name="linkPhone" data-options="required:true" style="width:250px"></input></td>
	                </tr>
	                <tr>
	                    <td>网点地址:</td>
	                    <td>省:<input type="text" class="easyui-combobox"  data-options="  required:true,  
							        valueField: 'code',    
							        textField: 'name',    
							        url: '${base}/customer/getProvice',
							        onSelect: function(rec){
							        	 $('#city').combobox('clear');
							        	 $('#district').combobox('clear');    
							            var url = '${base}/customer/getCity?provinceCode='+rec.code ; 
							            $('#city').combobox('reload', url);    
							        }     
							       "id="province" name="province" style="width:90px"/>
	                    	市:<input type="text" class="easyui-combobox" id="city" name="city"  data-options="valueField:'code',required:true,
								  	textField:'name',
								  	url: '${base}/customer/getCity',
								  	onSelect: function(rec){
								  				 $('#district').combobox('clear');
									            var url = '${base}/customer/getArea?cityCode='+rec.code    
									            $('#district').combobox('reload', url);    
									 }  " style="width:90px" />
	                    	区:<input class="easyui-combobox" id="district" name="district" data-options="valueField:'code',required:true,
								  	textField:'name',
								  	url: '${base}/customer/getArea',
								  	" style="width:90px">
	                    </td>
	                </tr>
	                 <tr>
	                    <td>详细地址:</td>
	                    <td>
	                    	<input class="easyui-textbox" id="address" name="address" data-options="multiline:true,required:true" style="height:45px;width:250px"></input></td>
	                    </td>
	                </tr>
	                
	                <tr>
	                    <td>其他信息:</td>
	                    <td>
	                    	<input class="easyui-textbox" id="othorMessage" name="othorMessage" data-options="multiline:true" style="height:100px;width:250px"></input></td>
	                    </td>
	                </tr> 
	                <tr>
	                <td colspan="2" align="center">
	                    <a href="javascript:void(0)" class="easyui-linkbutton" id="save" >保存</a>
           			    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.close()">取消</a>
	                </td>
	                
	            </table>
	            </form>
		</div>
		<div region="center">
			<div class="easyui-layout" style="height: 100%;">
				<div region="center"  id="pMapdiv">
					<div  id="mapdiv" style="width: 100%;height: 100%"></div>
				</div>
				<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var leagueId='${leagueId}';
	</script>
</body>
</html>