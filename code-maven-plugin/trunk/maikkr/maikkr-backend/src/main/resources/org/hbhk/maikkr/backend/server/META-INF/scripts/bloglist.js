$j(document).ready(function() {
	var tblw=$j(".tbl-list").width()-15;
	$j("#list").jqGrid({
        url:base+"backend/queryThemesByPage.htm",
        editurl:base+"backend/editBlog.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','用户','车型', '时间','地区', '主题链接','主题热度','收藏次数','评论次数', '创建日期','状态'],
        colModel:[
                {name:'id', hidden:true,editable: true },
                {name:'blogUser',index:'q_sl_blogUser', width:100},
                {name:'carType',index:'q_sl_blogTitle', width:100 },
                {name:'plannTime',index:'q_sl_blogContent', width:200,formatter:customDateFmatter,search:false},
                {name:'area',index:'q_sl_blogContent', width:200},
                {name:'blogUrl',index:'blogUrl', width:100,search:false},
                {name:'blogHit',index:'blogHit', width:50,search:false},
                {name:'blogCollect',index:'blogCollect', width:50,search:false},
                {name:'blogReview',index:'blogReview', width:50,search:false},
                {name:'createTime',index:'createTime', width:100,formatter:customDateFmatter,search:false},
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
        caption: "主题列表"
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