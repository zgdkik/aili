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
			<div class="contenr-right">
				<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/zjddBanner.png" alt=""/> --%></div>
				<div class="contenr-right-bottom ub">
					<h4 id="h_title">${title}</h4>
					<div class="">
						<div class="contentList">
				
						</div>
						
					</div>
					<div class="rows">
						<div class="rencaizhaopin">
							<div id="tab"></div>
							<div class="fenye">
								<nav>
								  <ul class="pagination">
								  	<li><a onclick="fn_set_page(1)" aria-label="Previous" style="cursor: pointer"><span aria-hidden="true">&laquo;</span></a></li>
								  	<li><a onclick="fn_set_page('+page+')" style="color:red;cursor: pointer">1</a></li>
								  	<li><a onclick="fn_set_page('+totalPage+')" aria-label="Next" style="cursor: pointer"><span aria-hidden="true">&raquo;</span></a></li>
								  </ul>
								</nav>
							</div>
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
    var rows=12;
    var noticeType='${noticeType}';
    if(noticeType==4){
    	title="文化生活";
    }
	$(function(){
		fn_get_notice_list();
		fn_get_menu("ddwl_zjdd");
	})
	
	function fn_get_notice_list(){
		 $.ajax({
	        type: "POST",
	        url: "${base}/notice/queryNoticeList",
	        data: {
	        	noticeType:noticeType,
	        	page:page,
	        	rows:rows,
	        	compCode:'ddwl'
	        },
	        dataType: "json",
	        success: function(data){
	        	//总页数
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
		          var noticeIds="";
		          $(data.data.rows).each(function(i,cm){
		       		 if(i==0){
		       			noticeIds+=cm['id'];
		       		 }else{
		       			noticeIds+=","+cm['id'];
		       		 }
		       	  });
		          $(data.data.rows).each(function(i,cm){
		       		  //strList+='<li><a href="${base}/ddwlGw/queryNoticeDetail?&noticeId='+cm['noticeId']+'&rowsId='+noticeIds+'">>'+cm['noticeTitle']+'</a><a style="float:right; padding-right: 10px">'+ym.formatDate(cm['changeDate'])+'</a></li>';
		        	  strList+='<div class="col-md-3"> '+
		        	  				'<div class="thumbnail">'+
										'<img src="'+cm['noticeImg']+'">'+
									'<div class="caption">'+
										'<h5><a href="${base}/ddwlGw/culturalLifeDetail?title='+title+'&noticeId='+cm['id']+'&rowsId='+noticeIds+'">'+cm['noticeTitle']+'</a></h5>'+
									'</div>'+
									'</div>'+
								'</div>';	
		          });
		       	  
		       	  $('.contentList').html(strList);
		       	replaceSrc();
	        }
	    });
	}
	function fn_set_page(_page){
		page=_page;
		fn_get_notice_list();
	}
	
	</script>
</body>
<script type="text/javascript">
		var html2=loadNextBanner(2); //走进大道-文化生活
</script>	
</html>
