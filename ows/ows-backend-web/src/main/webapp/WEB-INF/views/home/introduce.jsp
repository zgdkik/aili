<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CKEditor</title>
<script src="${base}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${base}/resources/statics/js/jquery-1.10.2.min.js"></script>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
<c:import url="../commons/common-css-ui.jsp" />
<script type="text/javascript">
	var menuCode='${roleCode}';
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
		config.height=$(window).height()-230;
	};
	$(function(){
		fnLoad();
	});
	CKEDITOR.on('instanceReady',function(e){
        if(htmlContent!=''){
        	CKEDITOR.instances.editor1.setData(htmlContent);
        }
	});
	var htmlContent='';
	function fnLoad(){
		$.ajax({
			//contentType: "text/html; charset=utf-8",
			type: "post",
			url: "${base}/html/getHtmlByMenuId",
			dataType: "json",
			data:{
				menuId:menuCode
			},
			success: function(data) {
				if(data.data.success){
					htmlContent=data.data.htmlContent;
					htmlContent=htmlContent.replace(new RegExp('/common',"gm"),"${base}/common");
					CKEDITOR.instances.editor1.setData(htmlContent);
				}
				
			}
		});
		
	}
	function fn_save(){
		//替换图片上下文
		var content=CKEDITOR.instances.editor1.getData();
		content=content.replace(new RegExp('${base}',"gm"),"");
		$.ajax({
			//contentType: "text/html; charset=utf-8",
			type: "post",
			url: "${base}/html/updateHtml",
			dataType: "json",
			data:{
				menuId:menuCode,
				htmlContent:content
			},
			success: function(data) {
				alert(data.data.msg);
			}
		});
	}
	
</script>
</head>
<body style="margin:0px">
<input type="hidden" id="hd_menuid" value="">
<textarea class="ckeditor" id="editor1" name="editor1"></textarea>
<input type="button" value="保存" onclick="fn_save()"/>
</body>
</html>
