$(document).ready(function() {
//var dgColumns =[];
//for (var i = 0; i < columns.length; i++) {
//	var c = columns[i];
//	var obj =  {
//			field : c.field,
//			title : c.title,
//			formatter : function(val){
//	        	return aili.formatTime(date);
//			}
//		};
//	dgColumns.push(obj);
//}
//var dgColumns = [dgColumns];
    $('#dg').datagrid({columns:columns});
    //查询方法
    function queryData(){
	    var params = $('.query-form').getFormObj();
	    aili.initPage(base + "/crud/dataList/"+code,{},params,'#dg',columns);	
    }
	//查询
	$('.query-btn').on("click",function(){
		queryData();
	});
	
	queryData();
	
});