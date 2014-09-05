$j(document).ready(function() {
	var tblw=$j(".tbl-list").width();
	$j("#list").jqGrid({
        url:base+"backend/userDatas.htm",
        height: 500,
        datatype: "json",
        width:tblw,
        mtype: 'POST',  
        colNames:['用户名', '姓名', '认证邮箱', '创建日期','修改时间','状态'],
        colModel:[
                {name:'mail',index:'mail', width:60, sorttype:"string"},
                {name:'name',index:'name', width:90},
                {name:'remail',index:'remail', width:90},
                {name:'createTime',index:'createTime', width:125, sorttype:"date"},
                {name:'updateTime',index:'updateTime', width:100, sorttype:"date"},                
                {name:'status',index:'status', width:120}       
        ],
        jsonReader : {  
            root:"items",  
            page: "currentPage",  
            total: "totalPages",  
            records: "count"
        },  
        sortname:'createTime',
        sortorder:'desc',
        viewrecords:true,
        rowNum:10,
        rowList:[10,20,30],
        pager:"#gridPager",
        caption: "用户列表"
	}).navGrid('#pager2', { add: true, edit: true, del: true,search:false,refresh:false });
	
//    for(var i=0;i<=mydata.length;i++){
//    	jQuery("#list").jqGrid('addRowData',i+1,mydata[i]);
//    }
});