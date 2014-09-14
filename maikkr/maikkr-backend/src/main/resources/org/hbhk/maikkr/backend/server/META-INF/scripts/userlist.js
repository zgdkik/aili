$j(document).ready(function() {
	var tblw=$j(".tbl-list").width()-15;
	$j("#list").jqGrid({
        url:base+"backend/userDatas.htm",
        editurl:base+"backend/editUser.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','用户名', '姓名', '认证邮箱', '创建日期','修改时间','状态'],
        colModel:[
                {name:'id', hidden:true},
                {name:'mail',index:'mail', width:60,editable: true,required:true },
                {name:'name',index:'name', width:90,editable: true ,required:true},
                {name:'remail',index:'remail', width:90,editable: true,required:true },
                {name:'createTime',index:'createTime', width:125,formatter:customDateFmatter,search:false},
                {name:'updateTime',index:'updateTime', width:100,formatter:customDateFmatter,search:false},                
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
        caption: "用户列表"
	}).navGrid('#gridPager', { add: false, edit: false, del: true,search:true,refresh:true });

});