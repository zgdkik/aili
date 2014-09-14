$j(document).ready(function() {
	var tblw=$j(".tbl-list").width()-15;
	$j("#list").jqGrid({
        url:base+"backend/queryBlogGroupsByPage.htm",
        editurl:base+"backend/editCom.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','评论用户','评论内容', '评论时间','主题用户','主题标题'],
        colModel:[
                {name:'id', hidden:true,editable: true },
                {name:'commentUser',index:'commentUser', width:100},
                {name:'commentConcent',index:'commentConcent', width:200 },
                {name:'createTime',index:'createTime', width:100,search:false,formatter:customDateFmatter},
                {name:'blogUser',index:'blogUser', width:100},
                {name:'blogTitle',index:'blogTitle', width:100}
        ],
        grouping:true,
        groupingView : {
        groupField : ['blogUser','blogTitle'],//分组属性
        groupColumnShow : [true,true],//是否显示分组列
        groupText : ['<b>{0} - {1} 条记录</b>'],//表头显示数据(每组中包含的数据量)
        groupCollapse :false,//加载数据时是否只显示分组的组信息
        groupSummary : [true,false],//是否显示汇总  如果为true需要在colModel中进行配置summaryType:'max',summaryTpl:'<b>Max: {0}</b>'
        groupDataSorted : true,//分组中的数据是否排序
        groupOrder:['desc','desc'] , //分组后组的排列顺序
       //showSummaryOnHide: true//是否在分组底部显示汇总信息并且当收起表格时是否隐藏下面的分组
        },
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
        caption: "评论列表"
	}).navGrid('#gridPager', { add: false, edit: false, del: true,search:true,refresh:true });

});