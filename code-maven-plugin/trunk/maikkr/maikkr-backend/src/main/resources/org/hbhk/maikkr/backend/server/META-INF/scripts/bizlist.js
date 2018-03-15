$j(document).ready(function() {
	var tblw=$j(".tbl-list").width()-15;
	$j("#list").jqGrid({
        url:base+"backend/queryBizsByPage.htm",
        editurl:base+"backend/editBiz.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','名称','图片','服务', '联系方式','地址','创建日期','修改时间','状态','操作'],
        colModel:[
                {name:'id', hidden:true ,editable: true },
                {name:'name',index:'q_sl_name', width:60,editable: true,required:true },
                {name:'imgUrl', width:100,search:false,formatter: imgFormatter},
                {name:'favorable',index:'q_sl_favorable',edittype:'textarea', width:90,editable: true,required:true },
                {name:'contactWay',index:'q_sl_contactWay',edittype:'textarea', width:200,editable: true,required:true },
                {name:'location',index:'q_sl_location',edittype:'textarea', width:90,editable: true,required:true },
                {name:'createTime',index:'createTime', width:125,formatter:customDateFmatter,search:false},
                {name:'updateTime',index:'updateTime', width:100,formatter:customDateFmatter,search:false},                
                {name:'status',index:'status', width:120,search:false,formatter: "select", editoptions:{value:"0:无效;1:有效;2:删除"}} ,      
                {name:'id',width:80,formatter: optFormatter}
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
        caption: "通用主题列表"
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
		}
//		,{ //edit 编辑时
//		 top : 10,  //位置
//		 left: 200, //位置
//		 height:480, //大小
//		 width:750, //大小
//		},{ //add 添加时
//		 top : 10,
//		 left: 200,
//		 height:480,
//		 width:750,
//		}
		);
	$j("body").on("click",".upload-img",function(){
		var id = $j(this).attr("bizid");
		$j("body").find(".upload-form").find(".bizid").val(id);
	});

	
});

function optFormatter(cellvalue, options, rowdata){
	var opt = '<button style="height: 35px;width: 80px;" bizid="'+cellvalue
	+'" class="btn btn-primary btn-lg upload-img" bizid="'+cellvalue+'" data-toggle="modal" data-target="#myModal">上传图片</button>';
    return opt;
}

function imgFormatter(cellvalue, options, rowdata){
	var opt = '<a href="'+file_server+cellvalue+'" target="view_window"><img style="width:100px;height: 100px;" src="'+file_server+cellvalue+'"></a>';
    return opt;
}


