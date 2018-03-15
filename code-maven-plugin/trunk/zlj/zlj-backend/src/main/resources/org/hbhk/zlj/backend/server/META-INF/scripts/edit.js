$(document).ready(function() {
	//保存
	function save(){
		var form = $("<form id='cc'>"+$("#edit-form").html()+"</form>")
//		$.parser.parse(form)
//		var validate=form.form('validate');
//		if(!validate){
//			return;
//		}
		var form=form.getFormObj();
		var sql = $("#sql").val();
		form.sql=sql;
		aili.sendPost(base + "/crud/edit",form,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					$("#dg").datagrid("reload");
					$('#dlg').dialog('close');
					$.messager.alert("提示信息",data.msg);
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	}
	$('.add-btn').on("click",function(){
		save();
		$('#subwrap').layout('resize');
	});
	$('body').on("click",".remove",function(){
		$(this).parents(".del").remove();
	});
	
	//保存
	function validateSql(){
		var sql = $("#sql").val();
		var form={"sql":sql};
		aili.sendPost(base + "/crud/validateSql",form,{successHandler:function(data, textStatus, jqXHR){
			if(data !=null){
				if(data.success){
					var cols = data.data;
					if(cols!=null && cols.length>0){
						$("#col-form").html(null);
						var col = $("#col-temp").clone(true).show().removeAttr("id");
						for ( var i = 0; i < cols.length; i++) {
							col = col.clone(true);
							col.find(".field").val(cols[i]);
							col.find(".title").val(cols[i]);
							
//							col.find(".field").textbox("setValue",cols[i]);
//							col.find(".title").textbox("setValue",cols[i]);
//							col.find(".field").textbox("name","field"+i);
//							col.find(".title").textbox("name","title"+i);
							$("#col-form").append(col)
						}
						 $("#col-form").find(".field").textbox();
						  $("#col-form").find(".title").textbox();
					}
					$(window).resize();
					$.messager.alert("提示信息",data.msg);
				}else{
					$.messager.alert("提示信息",data.msg);
				}
			}
		}});
	}
	$('.validateSql').on("click",function(){
		validateSql();
	});
	
	$('.add-btn-c').on("click",function(){
		var col = $("#condition-temp").clone(true).show().removeAttr("id");
		$("#condition-form").append(col);
		$("#condition-form").find(".conditionType").combobox();
		$("#condition-form").find(".conditionName").textbox();
		$("#condition-form").find(".conditionLabel").textbox();
		$("#condition-form").find(".conditionName").focus();
		$(window).resize();
	});
	
	
});