$j(document).ready(function() {
	var tblw=$j(".tbl-list").width()-15;
	$j("#list").jqGrid({
        url:base+"backend/queryLogsByPage.htm",
        editurl:base+"backend/editLog.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','用户','登陆时间', '登陆ip','状态'],
        colModel:[
                {name:'id', hidden:true,editable: true },
                {name:'user',index:'q_sl_user', width:100},
                {name:'createTime',index:'createTime', width:100,formatter:customDateFmatter,search:false},
                {name:'ip',index:'ip', width:200,search:false},
                {name:'status',index:'status', width:30,search:false,formatter: "select", editoptions:{value:"0:无效;1:有效;2:删除"}}       
        ],
        prmNames : {
 		   page:"page.start", // 表示请求页码的参数名称
 		   rows:"page.size", // 表示请求行数的参数名称
 		 },
        jsonReader : {  
            root:"items",  
            page: "currentPage",  
            total: "totalPages",  
            records: "count",
            repeatitems: false
        },  
        sortname:'createTime',
        sortorder:'desc',
        viewrecords:true,
        rowNum:10,
        rowList:[10,20,30],
        pager:"#gridPager",
        caption: "登陆日志列表"
	}).navGrid('#gridPager', {
		add: false,
		addtext:'新增',
		edit: false,
		edittext:'修改',
		del: true,
		deltext: '删除',
		search:true,
		searchtext:'查询',
		refresh:true ,
		refresh:'刷新'
		});
});