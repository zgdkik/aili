<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>banner</title>
<script src="${base}/resources/ckeditor/ckeditor.js"></script>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
<script type="text/javascript" src="${base}/resources/statics/js/ajaxfileupload.js"></script>

<script type="text/javascript">
CKEDITOR.editorConfig = function( config ) {
	config.toolbar =
		[
		{ name: 'document', items: ['Source', '-', 'Preview'] },
		{ name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo'] },
		{ name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize'] },
		{ name: 'colors', items: ['TextColor', 'BGColor'] },
		{ name: 'tools', items: ['About'] },
		'/',
		{ name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat'] },
		{ name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl'] },
		{ name: 'links', items: ['Link', 'Unlink', 'Anchor'] },
		{ name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak',] }
		
	];
	config.filebrowserImageUploadUrl = '${base}/upload/CKImgUpload';
	config.height=500;
	config.width=800;
};
CKEDITOR.on('instanceReady',function(e){
	
});

var bannerId='${bannerId}';
var bannerType;
//判断是否有id，有就回显
$(function(){
	bannerId='${bannerId}';//banner对应的是ddgw传过来的参数bannerId
	if(bannerId){
		fnload(bannerId);
	}
	bannerType='${bannerType}';
	if(bannerType!=1){
		$("#sortHide").hide();
		$("#bannerTypeHidden").hide();
		document.getElementById('contentTable').style.display='none';
	}
})

function fnload(bannerId){
	$.ajax({
		//contentType: "text/html; charset=utf-8",
		type: "post",
		url: "${base}/homePage/getHomePageById",
		dataType: "json",
		data:{
			'id':bannerId,//id对应实体类的参数
		},
		//返回的数据
		success: function(data) {
			if(data.success){
				data=data.data;
				bannerTitle = data.bannerTitle;
				bannerType = data.bannerType;
				bannerUrl=data.bannerUrl;
				bannerSort=data.bannerSort;
				$("#bannerTitle").textbox('setValue',bannerTitle);
				$("#bannerUrl").textbox('setValue',bannerUrl);
				$("#bannerSort").textbox('setValue',bannerSort);
				$("#bannerPic").val(data.bannerPic);  
				$("#previewBanner").html("<img style='width:300px;' src='${base}/"+data.bannerPic+"' />");
				if(bannerType==1){
				var content=data.content.replace(new RegExp('/common',"gm"),"${base}/common");
				CKEDITOR.instances.content.setData(content);
				content = data.content;
				}
			}
		}
	});
}


//图片上传
function fn_banner_upload(){
	  var url = '${base}/upload/fileUpload';
	  var suffixStr = "jpg,JPG,png,PNG,gif,GIF,bmp,BMP";
	  $.ajaxFileUpload({//String fileObjectId,int MaxSize,String suffixStr,String savePath,int width,int height
        url: url+'?fileObjectId=pageBannerFile&MaxSize='+1024+'&suffixStr='+suffixStr+'&width='+xzWid+'&height='+xzHei, //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'pageBannerFile', //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        success: function (data, status)  //服务器成功响应处理函数
        {
            if(data.success=="true"){
          	     $("#previewBanner").html("<img style='width:300px;height:100;' src='"+data.imgurl+"' />");
          	     $("#bannerPic").val(data.imgurl); 
            }else{
          	    alert(data.msg);
            }
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
      	  $("#bannerPic").val("");
        }
 })
}

//图片保存
var xzWid = 0;
var xzHei = 0;
var bannerId='';
var bannerType="${bannerType}";
$(function(){
	if(bannerType=='1'){
		$("#banner_tips").html('请上传1920*500的像素的图片');
		xzWid=1920;
		xzHei=500;
	}else{
		$("#banner_tips").html('请上传885*173的像素的图片');
		xzWid=885;
		xzHei=173;
	}
	var url = '';
	$("#save").click(function(){
		var bannerType="${bannerType}";
		var validate=$("#bannerTemplate").form('validate');//对表单的校验，如果没填写就不让其请求后台
		if(!validate){
			return;
		}
		var subTitle = $("input[name='subTitle']").val(); 
		var str = '/ddwlGw/pageBanner?&title='+subTitle+'&bannerId='+bannerId;
		//替换图片上下文
		var content=CKEDITOR.instances.content.getData().replace(new RegExp('${base}',"gm"),"");
		$.ajax({
				type:"POST",
				url:"${base}/homePage/addOrUpdateHomePage",
				data:{ 
					"id":bannerId,
					"bannerTitle":$('#bannerTitle').val(),
					"subTitle":subTitle,
					/* "titleDesc":$('#titleDesc').val(), */
					"bannerSort":$('#bannerSort').val(),
					"bannerUrl":str,
				    "bannerPic":$('#bannerPic').val().replace('${base}',""), 
				    "bannerType":bannerType,
				    "content":content,
				},
				success: function(data) {
					if(data.data==1){
						alert("保存banner成功！");
						window.opener.location.href=window.opener.location.href;
						window.close(); 
					}else{
						alert( "保存banner失败！");
					}
				}
			});
	})
})
</script>
</head>
<body style="magin:0">
    <div class="easyui-panel" style="width:1280px">
        <form id="bannerTemplate" method="post">
            <table>
            <tr>
            <td valign="top">
	            <table cellpadding="5">
	                <tr>
	                    <td>banner标题:</td>
	                    <td><input class="easyui-textbox" id="bannerTitle" name="bannerTitle" data-options="required:true" style="width:200px"></input></td>
	                </tr>
	                <tr id="bannerTypeHidden">
	                	<td>banner类型:</td>
	                	<td><select name="subTitle" class="easyui-combobox" data-options="required:true,editable:false" style="width:100px">
	                		<option value="市场活动">--市场活动--</option>
	                		<option value="通知公告">--通知公告--</option>
	                	</select></td>
	                </tr>
	                <tr id="sortHide">
	                	<td><font color="red">*</font>显示顺序:</td>
	                	<td>
	                		<input class="easyui-textbox" id="bannerSort" data-options="multiline:true" required="required" style="width:100px;" value="1" /><a style="color: red">填写数字,不要重复,例:1</a>
	                	</td>
	                </tr>
	         		<tr>
	                    <td>图片上传:</td>
	                    <td>
	                    	<input id="pageBannerFile" name="pageBannerFile" type="file" onchange="fn_banner_upload()"/>
	                    	<input type="hidden" id="bannerPic" name="bannerPic">
	                    </td>
	                </tr>
	                 <tr>
	                    <td colspan="2" style="color:red" align="right" id="banner_tips">
	                      	请上传1920*500的像素的图片
	                    </td>
	                </tr> 
	                <tr>
	                <!-- 预览 -->
	                    <td id="previewBanner" colspan="2" align="center" valign="middle" style="border:solid #ccc 1px; height:150px; border:0;">
	                      
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
	       	<td>
	            <table id="contentTable">     
	                <tr>
	                    <td colspan="2">
	              		  <textarea class="ckeditor" id="content" name="content" style="border:none;width:200px;height:30px;"></textarea>
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