$j(document).ready(function() {
	var tblw=$j(".tbl-list").width();
	$j("#list").jqGrid({
        url:base+"backend/userDatas.htm",
        datatype: "json",
		height: 'auto',
		width:tblw,
        mtype: 'POST',  
        colNames:['id','用户名', '姓名', '认证邮箱', '创建日期','修改时间','状态'],
        colModel:[
                {name:'id', hidden:true},
                {name:'mail',index:'mail', width:60,editable: true },
                {name:'name',index:'name', width:90,editable: true },
                {name:'remail',index:'remail', width:90,editable: true },
                {name:'createTime',index:'createTime', width:125},
                {name:'updateTime',index:'updateTime', width:100},                
                {name:'status',index:'status', width:120}       
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
	}).navGrid('#gridPager', { add: true, edit: true, del: true,search:true,refresh:false });
//	$j("#list").jqGrid({
//		  url: base+"backend/userDatas.htm",
//		  datatype: "json",
//		  mtype: 'POST',
//		  height: 500,
//          width:tblw,
//		  colNames: ['name', 'code', 'receivers', 'level'],
//		  colModel: [
//		   { name: 'name', index: 'name', width: 40, align: "left", editable: true },
//		   { name: 'mail', index: 'mail', width: 100, align: "center" },
//		   { name: 'remail', index: 'remail', width: 100, align: "center" },
//		   { name: 'status', index: 'status', width: 150, align: "center", search: false }
//		  ],
//		  rowList: [10, 20, 30],
//		  sortname: 'mail',
//		  viewrecords: true,
//		  sortorder: "desc",
//		  prmNames : {
//		   page:"page.start", // 表示请求页码的参数名称
//		   rows:"page.size", // 表示请求行数的参数名称
//		  },
//		  jsonReader: {
//			   root: "items",
//			   total: "totalPages",
//			   page: "currentPage",
//			   records: "count",
//			   repeatitems: false
//		  },
//		  pager: jQuery('#gridPager'),
//		  rowNum: 5,
//		  altclass: 'altRowsColour',
//		  //width: 'auto',
//		  width: '500',
//		  height: 'auto',
//		  caption: "用户列表"
//		  }).navGrid('#gridPager', { add: true, edit: true, del: true,search:false,refresh:false });
//    for(var i=0;i<=mydata.length;i++){
//    	jQuery("#list").jqGrid('addRowData',i+1,mydata[i]);
//    }
});