<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
	
		
</head>
<body style="width: 100%;" >
		<c:import url="../../commons/common-top.jsp" />
		<img alt="top" title="返回顶部" src="${base}/images/top.png"
			style="position: fixed; right: 10px; bottom: 100px; cursor: pointer;"
			onclick="scroll(0,0)" />
		<div class="clear"></div>
		<div class="container mg">
			<!--sidebar-->
			<c:import url="../../commons/childmenu.jsp" />
			<!--具体内容-->
			<!--具体内容-->
		<div class="contenr-right">
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img  src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<h4 id="h_title">${title}</h4>
				<div class="rencaizhaopin">
  					<!-- <div class="ub">
						<div class="div1">
							<strong>请选择岗位：</strong>
							<select name="" id="recruit_category" class="form-control">
							</select>
						</div>
						<div class="div1">
							<strong>请选择工作地点：</strong>
							<select name="" id="recruit_area" class="form-control">
							</select>
						</div>
						<button class="btn" onclick="init()">搜索</button>
					</div> -->
					<div id="tab"></div>
					<div class="fenye">
						<nav>
						  <ul class="pagination"></ul>
						</nav>
					</div>
				</div>
			</div>	
		</div>
		</div>
	
	<!--焦点图滚动-->
    
	<c:import url="../../commons/common-footer.jsp" />
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript">
    var page=1;
    var rows=10;
    
	$(function(){
		fn_get_notice_list();
		fn_get_menu("ddwl_ddzp");
	})
	
	function fn_get_notice_list(){
		 $.ajax({
	        type: "POST",
	        url: "${base}/recruit/queryRecruitList",
	        data: {
	        	compCode:'ddwl',
	        	page:page,
	        	rows:rows
	        },
	        dataType: "json",
	        success: function(data){
	        	var v_consignee=  data.data.rows;
	        	 var html='<table><thead>';
	        	 html+='<tr><th style="background-color: #fcb814;color: #fff;">职位名称</th><th style="background-color: #fcb814;color: #fff;">工作地点</th><th style="background-color: #fcb814;color: #fff;">招聘人数</th><th style="background-color: #fcb814;color: #fff;">发布时间</th><th style="background-color: #fcb814;color: #fff;">招聘结束时间</th></tr>';
	        	 html+='</thead>';
	        	 html+='<tbody>';
	        	 if(v_consignee.length<1){
	        		 $('.pagination').html('');
	        		 html+='<tr><td colspan="4">:( 抱歉，没有查询到相关结果！</td><tr>';
	        	 }else{
			         $.each(v_consignee,function(i, consignee) {
			        	 html+='<tr>'
			        	 		+'<td>'
			        	 		+'<a href="${base}/ddwlGw/queryRecruitDetail?id='+consignee.id+'"'
			        	 		+'>'
			        	 		+consignee.recruitTitle
			        	 		+'</a>'
			        	 		+'</td>'
			        	 		+'<td>'
			        	 		+consignee.recruitArea
			        	 		+'</td>'
			        	 		+'<td>'
			        	 		+consignee.recruitCount
			        	 		+'</td>'
			        	 		+'<td>'
			        	 		+ym.formatDate(consignee.changeDate)
			        	 		+'</td>'
			        	 		+'<td>'
			        	 		+ym.formatDate(consignee.recruitEnddate)
			        	 		+'</td>'
			        	 		+'</tr>';
						})
						var total = data.data.total;
				        var strList = '';
				        if(total>0){
				        	  var totalPage =0;
					          if(total%rows>0){totalPage = parseInt(total/rows)+1;}else{totalPage = parseInt(total/rows);}
					          var pageStr = '<li><a onclick="fn_set_page(1)" aria-label="Previous" style="cursor: pointer"><span aria-hidden="true">&laquo;</span></a></li>';
					          for(i=((page-5)>0?(page-5):1);i<page&&i>0;i++){
					        	  pageStr+='<li><a onclick="fn_set_page('+i+')" style="cursor: pointer">'+i+'</a></li>';
					          }
					          pageStr+='<li><a onclick="fn_set_page('+page+')" style="color:red;cursor: pointer">'+page+'</a></li>';
					          for(j=page+1;j<page+6&&j<=totalPage;j++){
					        	  pageStr+='<li><a onclick="fn_set_page('+j+')" style="cursor: pointer">'+j+'</a></li>';
					          }
					          pageStr+='<li><a onclick="fn_set_page('+totalPage+')" aria-label="Next" style="cursor: pointer"><span aria-hidden="true">&raquo;</span></a></li>';
					          $('.pagination').html(pageStr);
				          }else{
				        	 return; 
				          }
	        	 }
				html+='<tbody></table>';
			    $("#tab").html(html);
		}
		 })     
	}
	function fn_set_page(_page){
		page=_page;
		fn_get_notice_list();
	}
</script>
</body>
<script type="text/javascript">
	var html5=loadNextBanner(6); //大道招聘 -招聘信息
</script>
</html>