$j(document).ready(function() {
	var tblw=$j(".tbl-list").width()-15;
	$j("#list").jqGrid({
        url:base+"backend/querySitesByPage.htm",
        editurl:base+"backend/editSite.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','标题','关键字','描述','创建日期','修改时间','状态'],
        colModel:[
                {name:'id', hidden:true ,editable: true },
                {name:'title',index:'q_sl_title', width:60,editable: true,required:true },
                {name:'keywords',index:'q_sl_keywords', width:90,editable: true,required:true },
                {name:'description',index:'description', width:125,editable: true,required:true,search:false},
                {name:'createTime',index:'createTime', width:100,formatter:customDateFmatter,search:false}, 
                {name:'updateUser',index:'updateUser', width:100,formatter:customDateFmatter,search:false},
                {name:'status',index:'status', width:120,search:false,formatter: "select", editoptions:{value:"0:无效;1:有效;2:删除"}}       
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
        caption: "网站信息列表"
	}).navGrid('#gridPager', {
		add: true,
		addtext:'新增',
		edit: true,
		edittext:'修改',
		del: true,
		deltext: '删除',
		search:true,
		searchtext:'查询',
		refresh:true ,
		refresh:'刷新'
		});
});