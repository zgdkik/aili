<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻管理</title>
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

var noticeType;
var noticeId;
$(function(){
 	noticeId='${noticeId}';
 	if(noticeId){
 		fnLoad(noticeId);
 	}
	noticeType='${noticeType}';
	
});
CKEDITOR.on('instanceReady',function(e){
    if(noticeContent!=''){
    	/* CKEDITOR.instances.editor1.setData(noticeContent); */
    }
});
var noticeContent='';
//回显的数据
function fnLoad(noticeId){
	$.ajax({
		//contentType: "text/html; charset=utf-8",
		type: "post",
		url: "${base}/notice/getNoticeById",
		dataType: "json",
		data:{
			noticeId:noticeId
		},
		//返回的数据
		success: function(data) {
			if(data.success){
				data=data.data;
				noticeTitle=data.noticeTitle;
				noticeSummary=data.noticeSummary;
				$("#noticeTitle").textbox('setValue',noticeTitle);
				$("#noticeSummary").textbox('setValue',noticeSummary);
				$("#releaseTime").datebox('setValue',data.releaseTime);
				//替换图片上下文
				var noticeContent="";
				if(data.noticeContent.split(";split;").length>1){
					noticeContent=data.noticeContent.split(";split;")[1].replace(new RegExp('/common',"gm"),"${base}/common");
				}else{
					noticeContent=data.noticeContent.replace(new RegExp('/common',"gm"),"${base}/common");
				}
				CKEDITOR.instances.noticeContent.setData(noticeContent);
				if(data.noticeImg){
					$("#td_noticeImg").html("<img style='width:220px' src='${base}/"+data.noticeImg+"' />");
         	   		$("#noticeImg").val(data.noticeImg); 
				}
				noticeContent=data.noticeContent;
			}
		}
	});
}

//图片上传
function fn_img_upload(){
	  var url = '${base}/upload/fileUpload';
	  var suffixStr = "jpg,JPG,png,PNG,gif,GIF,bmp,BMP";
	  $.ajaxFileUpload({//String fileObjectId,int MaxSize,String suffixStr,String savePath,int width,int height
        url: url+'?fileObjectId=noticeImgFile&MaxSize='+1024+'&suffixStr='+suffixStr+'&width='+xzWid+'&height='+xzHei, //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'noticeImgFile', //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        success: function (data, status)  //服务器成功响应处理函数
        {
            if(data.success=="true"){
          	     $("#td_noticeImg").html("<img style='width:220px' src='"+data.imgurl+"' />");
          	   	  var url=data.imgurl.replace(new RegExp('${base}',"gm"),"");
          	     $("#noticeImg").val(url); 
            }else{
          	    alert(data.msg);
            }
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
      	  $("#noticeimg").val("");
            alert(e);
        }
 })
}

//图片保存
var xzWid = 0;
var xzHei = 0;
var noticeType='${noticeType}';
var noticeId="";
$(function(){
	if(noticeType=='1'){
		//一定要这分辨率才能上传
		$('#td_tips').html('请上传380*245像素的图片');
		xzWid=380;
		xzHei=245;
	}else if(noticeType=='2'){
		$('#td_tips').html('请上传216*183像素的图片');
		xzWid=216;
		xzHei=183;
	}
	else if(noticeType=='4'){
		$('#td_tips').html('请上传300*198像素的图片');
		xzWid=300;
		xzHei=198;
	} 
	else if(noticeType=='5'){
		$('#td_tips').html('请上传300*198像素的图片');
		xzWid=300;
		xzHei=198;
	} 
	var url = '';

	$("#save").click(function(){
		var validate=$("#ff").form('validate');//对表单的校验，如果没填写就不让其请求后台
		if(!validate){
			return;
		}
		 var t = $("#noticeImg");  //标题图片
	     if (t.val() == ""&&noticeType=="1") {      //判断标题图片是否为空   并且是否为新闻咨询类型
	         t.focus();
	         $.messager.alert("提示", "必须上传一张图片供首页显示！", "info");
	         return;
	     }
		var text=CKEDITOR.instances.noticeContent.document.getBody().getText();
		var html=CKEDITOR.instances.noticeContent.getData().replace(new RegExp('${base}',"gm"),"");
		var content=text+";split;"+html;
		$.ajax({
				type:"POST",
				url:"${base}/notice/addOrUpdateNotice",
				data:{ 
					"id":noticeId,   //id是实体类字段
					"noticeTitle":$('#noticeTitle').val(),
					"noticeSummary":$('#noticeSummary').val(),
				    "noticeImg":$('#noticeImg').val(), 
					"noticeContent":content,
					"noticeType":noticeType,
					"releaseTime":$('#releaseTime').datebox('getValue'),  //发布时间
					
				},
				success: function(data) {
					if(data.data==1){
						alert('保存新闻成功!');
						window.opener.location.href=window.opener.location.href;
						window.close(); 
					}else{
						 alert('保存新闻失败!');
					}
				}
			});
	})

})

</script>
</head>
<body style="magin:0">
    <div class="easyui-panel" style="width:1280px">
        <form id="ff" method="post">
            <table>
            <tr>
            <td valign="top">
	            <table cellpadding="5">
	                <tr>
	                    <td>标题:</td>
	                    <td><input class="easyui-textbox" id="noticeTitle" name="noticeTitle" data-options="required:true" style="width:200px"></input></td>
	                </tr>
	                <tr>
	                    <td>发布时间:</td>
	                    <td><input class="easyui-datebox" id="releaseTime"  name="releaseTime" data-options="required:true,editable:false" style="width:200px"></input></td>
	                </tr>
	                <tr>
	                    <td>简介:</td>
	                    <td><input class="easyui-textbox" id="noticeSummary" data-options="multiline:true" style="height:100px;width:200px"></input></td>
	                </tr>
	                 <tr>
	                    <td>标题图片:</td>
	                    <td>
	                    	<input id="noticeImgFile" name="noticeImgFile" type="file" onchange="fn_img_upload()"/>
	                    	<input type="hidden" id="noticeImg" name="noticeImg">
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2" style="color:red" align="right" id="td_tips">
	                      	请上传380*245像素的图片
	                    </td>
	                </tr>
	                <tr>
	                    <td id="td_noticeImg" colspan="2" align="center" valign="middle" style="border:solid #ccc 1px; height:150px">
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
            <td>
	            <table>     
	                <tr>
	                    <td colspan="2">
	              		  <textarea class="ckeditor" id="noticeContent" name="noticeContent"></textarea>
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