/** *************1. 基本信息的设定********************** */

/** *************2. 页面加载时设定********************** */

$(document)
		.ready(
				function() {

					// sds.frame.notifyError("notifyError", "notifyError");
					// sds.frame.notifyWarning("notifyWarning",
					// "notifyWarning");
					// sds.frame.notifyInfo("notifyInfo", "notifyInfo");
					// sds.frame.notifySuccess("notifySuccess",
					// "notifySuccess");
					// 返回值为布尔类型，true或false
					bootbox.confirm("确定要删除吗?", "2", function(result) {
						sds.frame.notifySuccess("notifySuccess",
								"notifySuccess");
					});
					// bootbox.alert("确定要删除吗?", function(result) {
					// sds.frame.notifySuccess("notifySuccess",
					// "notifySuccess");
					// });
					// bootbox.prompt("确定要删除吗?", function(result) {
					// sds.frame.notifySuccess("notifySuccess", result);
					// });

					bootbox.dialog({
						title : "修改密码",
						message : "sdfsdf",
						buttons : {
							"success" : {
								"label" : "<i class='icon-ok'></i> 保存",
								"className" : "btn-sm btn-success",
								"callback" : function() {
								}
							},
							"cancel" : {
								"label" : "<i class='icon-info'></i> 取消",
								"className" : "btn-sm btn-danger",
								"callback" : function() {
								}
							
							}
						}
					});
					// url 参数 请求属性
					// sds.asyncPost(base+"/demo/getDemo/1",null,{successHandler:function(data,
					// textStatus, jqXHR){
					// bootbox.alert(data.msg);
					// }});
					var columns = [
							{
								checkbox : true
							},
							{
								field : 'id',
								title : 'id',
								sortable : true
							},
							{
								field : 'name',
								title : 'name',
								sortable : true,
								sorter : "ddd"
							},
							{
								field : 'status',
								title : 'status'
							},
							{
								field : 'name',
								title : 'name'
							},
							{
								field : 'status',
								title : 'status'
							},
							{
								field : 'name',
								title : 'name'
							},
							{
								field : 'status',
								title : 'status'
							},
							{
								field : 'name',
								title : 'name'
							},
							{
								field : 'status',
								title : 'status'
							},
							{
								field : 'name',
								title : 'name'
							},
							{
								field : 'id',
								title : '操作列',
								formatter : function(id) {

									var toolmenu = '<div class="btn-group btn-group-xs"> '
											+ '<a type="button" class="btn btn-default" href="${base}/auth/std/role/update"> <i class="fa fa-edit"></i>修改</a>'
											+ '</div>';
									return toolmenu;
								}
							} ];
					sds.initPage(base + "/demo/getPage", {
						queryParams : function(params) {
							params.q_str_name = $(".query-form").find(
									".q_str_name").val();
							return params;
						},
					}, "#table", columns);

					$('.btn-search').on(
							"click",
							function() {
								var regionCode = $("#autocompleteInput").attr(
										"real-value")
										|| "";
								alert(regionCode);
								sds.initPage(base + "/demo/getPage", {
									queryParams : function(params) {
										// 全查询
										params.q_str_name = $(".query-form")
												.find(".q_str_name").val()
										return params;
									},
								}, "#table", columns);
							});
					//  
					$('.q_str_name').autocomplete({
						source : function(query, process) {
							var matchCount = this.options.items;// 返回结果集最大数量
							$.post(base + "/demo/autocomplete", {
								"matchInfo" : query,
								"matchCount" : matchCount
							}, function(respData) {
								return process(respData);
							});
						},
						formatItem : function(item) {
							return item;// item["regionName"]+"("+item["regionNameEn"]+"，"+item["regionShortnameEn"]+")
										// - "+item["regionCode"];
						},
						setValue : function(item) {
							return {
								'data-value' : item,
								'real-value' : item
							};
						}
					});
					sds.selectAjax(base + "/demo/searchSelect");
					$(".selectpicker").on("change", function() {
						alert();
					});
				});
