<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻管理</title>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
<script type="text/javascript" src="${base}/resources/statics/js/ajaxfileupload.js"></script>

<script type="text/javascript">
var xzWid = 0;  //图片设置宽度
var xzHei = 0;  //图片设置高度
$(function(){
 	var imgId='${imgId}';
 	var status = '${status}';
 	if(imgId){
 		fnLoad(imgId,status);
 	}
 	xzWid=52;
	xzHei=50;
 	
});
//回显的数据
function fnLoad(imgId,status){
	$.ajax({
		type: "post",
		url: "${base}/imgManage/getImgById",
		dataType: "json",
		data:{"id":imgId},
		//返回的数据
		success: function(data) {
			if(data.success){
				data=data.data;
				var mainTitle=data.mainTitle;
				var subTitle=data.subTitle;
				var sort = data.sort;
				$("#mainTitle").textbox('setValue',mainTitle);
				$("#subTitle").textbox('setValue',subTitle);
				$("#status").combobox('setValues',status);    //状态
				if(data.defaultImg){
					$("#td_defaultImg").html("<img style='width:50px' src='${base}/"+data.defaultImg+"' />");
         	   		$("#defaultImg").val(data.defaultImg); 
				}
				if(data.clickImg){
					$("#td_clickImg").html("<img style='width:50px' src='${base}/"+data.clickImg+"' />");
         	   		$("#clickImg").val(data.clickImg); 
				}
			}
		}
	});
} 

//图片上传-默认图片
function fn_defaultImg_upload() {
	var url = '${base}/upload/fileUploadImg';
	var suffixStr = "jpg,JPG,png,PNG,gif,GIF,bmp,BMP";
	$.ajaxFileUpload({//String fileObjectId,int MaxSize,String suffixStr,String savePath,int width,int height
				url : url + '?fileObjectId=defaultFile&MaxSize=' + 1024+ '&suffixStr=' + suffixStr + '&width=' + xzWid+ '&height=' + xzHei+'&flag='+1, //用于文件上传的服务器端请求地址
				secureuri : false, //是否需要安全协议，一般设置为false
				fileElementId : 'defaultFile', //文件上传域的ID
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data, status) //服务器成功响应处理函数
				{
					if (data.success == "true") {
						$("#td_defaultImg").html("<img style='width:50px' src='"+data.imgurl+"' />");
						$("#defaultImg").val(data.imgurl);
					} else {
						alert(data.msg);
					}
				},
				error : function(data, status, e)//服务器响应失败处理函数
				{
					$("#defaultImg").val("");
				}
			})
}
	
//上传鼠标悬浮图片	
function fn_clickImg_upload(){
		  //当我上传鼠标悬浮图片的时候我必须确保默认图片已经上传
		  var defaultImg = $("#defaultImg").val();
		  if(defaultImg==""){
			 alert("请先确保有默认图片可供首页展示");
			  return;
		  }
		  var url = '${base}/upload/fileUploadImg';
		  var suffixStr = "jpg,JPG,png,PNG,gif,GIF,bmp,BMP";
		  $.ajaxFileUpload({//String fileObjectId,int MaxSize,String suffixStr,String savePath,int width,int height
	        url: url+'?fileObjectId=clickFile&MaxSize='+1024+'&suffixStr='+suffixStr+'&width='+xzWid+'&height='+xzHei+'&flag='+2, //用于文件上传的服务器端请求地址
	        secureuri: false, //是否需要安全协议，一般设置为false
	        fileElementId: 'clickFile', //文件上传域的ID
	        dataType: 'json', //返回值类型 一般设置为json
	        success: function (data, status)  //服务器成功响应处理函数
	        {
	            if(data.success=="true"){
	          	     $("#td_clickImg").html("<img style='width:50px' src='"+data.imgurl+"' />");
	          	     $("#clickImg").val(data.imgurl); 
	            }else{
	          	    alert(data.msg);
	            }
	        },
	        error: function (data, status, e)//服务器响应失败处理函数
	        {
	      	  $("#clickImg").val("");
	        }
	 })
}
	//保存核心产品图片
	var xzWid = 0;
	var xzHei = 0; 
	var type='${type}';
	$(function() {
				$("#save").click(
							function() {
								var validate = $("#ff").form('validate');//对表单的校验，如果没填写就不让其请求后台
								if (!validate) {
									return;
								}
								var t1 = $("#defaultImg"); //默认图片
								if (t1.val() == "") { //判断标题图片是否为空   
									t1.focus();
									$.messager.alert("提示","必须先上传默认图片！", "info");
									return;
								}
								var status = $('#status').combobox('getValue');
								$.ajax({
										type : "POST",
										url : "${base}/imgManage/saveAndUpdateImgManage",
										data : {
											"id":'${imgId}',					  //id
											"mainTitle" : $('#mainTitle').val(),  //主标题
											"subTitle" : $('#subTitle').val(),    //子标题
											"status" : status,	  //状态
											"defaultImg" : $('#defaultImg').val().replace('/owsadmin',""),//默认图片
											"clickImg" : $('#clickImg').val().replace('/owsadmin',""),	  //鼠标悬浮图片
										},
										success : function(data) {
											if (data.data == 1) {
												alert('保存成功!');
												window.opener.location.href = window.opener.location.href;
												window.close();
											} else {
												alert('保存失败!');
											}
										}
									});
								})

			})
</script>
</head>
<body style="magin:0">
    <div class="easyui-panel" style="width:640px">
        <form id="ff" method="post">
            <table>
            <tr>
            <td valign="top">
	            <table cellpadding="5">
	             	<!-- <tr>
	             		<td>核心产品展示位置:</td>
	                    <td><select name="sort" class="easyui-combobox" data-options="required:true,editable:false" style="width:100px">
	                		<option value="1">--1号展示位--</option>
	                		<option value="2">--2号展示位--</option>
	                		<option value="3">--3号展示位--</option>
	                		<option value="4">--4号展示位--</option>
	                		<option value="5">--5号展示位--</option>
	                		<option value="6">--6号展示位--</option>
	                		<option value="7">--7号展示位--</option>
	                	</select></td>
	                </tr> -->
	                <tr>
	                    <td>主标题:</td>
	                    <td><input class="easyui-textbox" id="mainTitle" name="mainTitle" data-options="required:true" style="width:150px"></input></td>
	                </tr>
	                 <tr>
	                    <td>子标题:</td>
	                    <td><input class="easyui-textbox" id="subTitle" name="subTitle" data-options="required:true" style="width:150px"></input></td>
	                </tr>
	                 <tr>
	                    <td>状态:</td>
	                     <td><select id="status" name="status" class="easyui-combobox" data-options="required:true,editable:false" style="width:100px;">
	                		<option value="1">启用</option>
	                		<option value="0">停用</option>
	                	</select></td>
	                </tr>
	                 <tr>
	                    <td>默认图片:</td>
	                    <td>
	                    	<input id="defaultFile" name="defaultFile" type="file" onchange="fn_defaultImg_upload()"/>
	                    	<input type="hidden" id="defaultImg" name="defaultImg">
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2" style="color:red" align="right" id="td_tips">
	                      	请上传52*50像素的图片
	                    </td>
	                </tr>
	                <tr>
	                    <td id="td_defaultImg" colspan="2" align="center" valign="middle" style="border:solid #ccc 1px; height:150px">
	                       	图片预览
	                    </td>
	                </tr>
	                
	                 <tr>
	                    <td>鼠标放置显示图片:</td>
	                    <td>
	                    	<input id="clickFile" name="clickFile" type="file" onchange="fn_clickImg_upload()"/>
	                    	<input type="hidden" id="clickImg" name="clickImg">
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2" style="color:red" align="right" id="td_tips">
	                      	请上传52*50像素的图片
	                    </td>
	                </tr>
	                <tr>
	                    <td id="td_clickImg" colspan="2" align="center" valign="middle" style="border:solid #ccc 1px; height:150px">
	                       	图片预览
	                    </td>
	                </tr>  
	                <tr>
	                <td colspan="2" align="center">
	                    <a href="javascript:void(0)" class="easyui-linkbutton" id="save" >保存</a>
           			    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window.close()">取消</a>
	                </td>
	                </tr>
	            </table>
            </td>
            </tr>
            </table>
        </form>
   </div>
</body>
</html>