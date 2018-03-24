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
<c:import url="../commons/common-css-ui.jsp" />
<c:import url="../commons/common-script-ui.jsp" />
<script type="text/javascript">

var bannerTyp = '${bannerType}';

if(bannerTyp==1){
	columns = [ [{
		title : "<b>主键</b>",
		field : "id",
		hidden:'hidden'
	},{
		title : "<b>banner标题</b>",
		field : "bannerTitle",
		align : 'center',
		width : 100
	},{
		title : "<b>banner类型</b>",
		field : "bannerType",
		align : 'center',
		width : 100,
		hidden:'hidden'
	},{
		title : "<b>banner内容</b>",
		field : "titleDesc",
		width : 100,
		align : 'center',
		hidden:'hidden'
	}, {
		title : "<b>链接</b>",
		field : "bannerUrl",
		align : 'center',
		width : 100,
	}, {
		title : "<b>图片</b>",
		field : "bannerPic",
		align : 'center',
		width : 150   
	},{
		title : "<b>显示顺序</b>",
		field : "bannerSort",
		align : 'center',
		width : 100,
		sortable:true,
		formatter:function(data){
			return data+"号广告位";
		}   
	},  {
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 80,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},  {
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 80,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	}, {
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 80,
	}] ]
}else{
	columns = [ [{
		title : "<b>主键</b>",
		field : "id",
		hidden:'hidden'
	},{
		title : "<b>banner标题</b>",
		field : "bannerTitle",
		align : 'center',
		width : 100
	},{
		title : "<b>banner类型</b>",
		field : "bannerType",
		align : 'center',
		width : 100,
		hidden:'hidden'
	},{
		title : "<b>banner内容</b>",
		field : "titleDesc",
		width : 100,
		align : 'center',
		hidden:'hidden'
	},{
		title : "<b>图片</b>",
		field : "bannerPic",
		align : 'center',
		width : 150   
	},{
		title : "<b>创建时间</b>",
		field : "createDate",
		align : 'center',
		width : 80,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>修改时间</b>",
		field : "changeDate",
		align : 'center',
		width : 80,
		formatter : function(date){
        	return ym.formatTime(date);
		}
	},{
		title : "<b>操作用户</b>",
		field : "changeUser",
		align : 'center',
		width : 80,
	}] ]
}



$(function(){
	init();
	queryData();
})
function queryData(){
   	var opts={};
   	param={"q_str_bannerType":bannerTyp};
	ym.initPage("${base}/homePage/getAllBanner",opts,param,'#list_data')
   };
function init(){
	$('#list_data').datagrid({ 
		pagination : true, //开启分页
		pageSize : 20, //每页显示数目
		pageList : [ 20, 30, 40, 50 ], //选择每页显示数目(与pageSize对应)
		fit : true, //适应大小
		fitColumns : true, //自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
		nowrap:false, //是否关闭自动换行(true关闭，false开启)
		border : false, //边框
		remoteSort:false,
		singleSelect:true,
		rownumbers:true,
	    toolbar: [{ 
	        text: '添加', 
	        iconCls: 'icon-add', 
	        handler: function(){
	        	var iHeight = 600;
	            var iWidth = 1085;
	            var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
	            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	        	if(bannerTyp==1){//当前type为1
		            var win = window.open("${base}/ddwl/homePageCapacity?bannerType="+bannerTyp,'newwindow','height='+iHeight+', width='+iWidth+', top='+iTop+', left='+iLeft+', toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
	        	}
	        	else{//其他banner
	        		var rows=$("#list_data").datagrid('getRows');//当前列表下的banner条数
	        		if(rows.length>0){
	        			  $.messager.alert("提示", "仅需一个banner", "info");
	        			  return ;
	        		}else{
			            var win = window.open("${base}/ddwl/homePageCapacity?bannerType="+bannerTyp,'newwindow',"width=390, height=388,top=" + iTop + ",left=" + iLeft+" , toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
        			}
        		}
        	}
	    }, '-', { 
	        text: '修改', 
	        iconCls: 'icon-edit', 
	        handler: function(){
	        	//请选择一行
				var allCheck=$("#list_data").datagrid('getSelected');
	        	if (allCheck==null) {
		            $.messager.alert("提示", "请选择要修改的行！", "info");
		            return;
		        }
	        	/* var iHeight = 420;
	            var iWidth = 400; */
	        	var iHeight = 600;
	            var iWidth = 1085;
	            var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
	            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	            if(bannerTyp==1){
		            var win = window.open("${base}/ddwl/homePageCapacity?bannerId="+allCheck.id+"&bannerType="+allCheck.bannerType+"",'newwindow','height='+iHeight+', width='+iWidth+', top='+iTop+', left='+iLeft+', toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
	            }else{
	            //toolbar  第一个参数表示去Controller中的ddwl中找noticeCapacity 是否显示工具条,menubar、scrollbars是否显示菜单和滚动栏,resizable是否允许改变窗体大小,localion是否显示地址栏,status是否显示状态栏内的信息,alwaysRaised窗口浮动于浏览器之上,depended是否和父窗口同时关闭
	            var win = window.open("${base}/ddwl/homePageCapacity?bannerId="+allCheck.id+"&bannerType="+allCheck.bannerType+"",' 弹出窗口', "width=390, height=388,top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
	            }
	        }
	    }, '-',{ 
	        text: '删除', 
	        iconCls: 'icon-remove', 
	        handler: function(){
	        	//请选择一行
				var allCheck=$("#list_data").datagrid('getSelected');
	        	var bannerId = allCheck.id;
				if (allCheck==null) {
		            $.messager.alert("提示", "请选择要删除的行！", "info");
		            return;
		        }
				$.messager.confirm("提示","您确定要删除该行数据吗！",function(data){
					if(!data){
						return;
					}
					 $.ajax({
						type:"POST",
						url:"${base}/homePage/deleteBannerById",
						data:{'bannerId':bannerId},
						success:function(data){
							if(data.data.success){
								$("#list_data").datagrid('reload');
							}else{
								$.messager.alert("友情提示","删除失败","info");
							}
						}
					}); 
				});
			}
	     }],
		columns : columns
	});
}
</script>
		<table id="list_data"></table>
</body>
</html>
