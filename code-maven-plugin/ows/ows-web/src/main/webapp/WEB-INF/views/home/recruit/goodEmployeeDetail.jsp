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
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"><%-- <img src="${base}/images/ban16.jpg" alt=""/> --%></div>
			<div class="contenr-right-bottom ub">
				<div class="news-title ub" id="noticeTitle"></div>
				<div class="time1 ub" style="z-index:2"><!--href="javascript:history.back();  -->
					<a id="returnList" style="float: right;cursor: pointer;z-index:100;">返回列表&nbsp;&nbsp;&nbsp;</a>
					<a style="float: right;cursor: pointer;z-index: 100;" id="a_next">&nbsp;&nbsp;下一篇&nbsp;&nbsp;</a>
					<a style="float: right;cursor: pointer;z-index:100;" id="a_prev">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上一篇&nbsp;&nbsp;</a>
					<font id="noticeDate" style="float:right">发布时间：<span></span></font>
				</div>
				<div class="news-content" style="z-index: 1">
				</div>
			</div>
		</div>
		</div>
	
	<!--焦点图滚动-->
	<c:import url="../../commons/common-script.jsp" />
	<c:import url="../../commons/common-footer.jsp" />
	<script type="text/javascript">
		//rowsId总条数
	 	var noticeId = '${noticeId}';
	 	var rowsId = '${rowsId}';
	 	var ids=rowsId.split(",");
	 	var preId;//-上一页
	 	var nextId;//-下一页
	 	for(var i=0;i<ids.length;i++){
	 		if(ids.length>1){
	 			if(noticeId==ids[i]){
		 			if(i==0||i==ids.length-1){//判断是第一条新闻还是最后一条新闻还是其他新闻
		 				if(i==0){
		 					nextId=ids[i+1];
		 				}else{
		 					preId=ids[i-1];
		 				}
		 			}
		 			else{
		 				nextId=ids[i+1];
		 				preId=ids[i-1];
		 			}
		 		}	
	 		}
	 	}
	 	
	 
		$(function(){
			fn_get_menu("ddwl_ddzp");
			//rowsId总条数
		 	var noticeId = '${noticeId}';
		 	var rowsId = '${rowsId}';
		 	var ids=rowsId.split(",");
		 	var preId;//-上一页
		 	var nextId;//-下一页
		 	for(var i=0;i<ids.length;i++){
		 		if(ids.length>1){
		 			if(noticeId==ids[i]){
			 			if(i==0||i==ids.length-1){//判断是第一条新闻还是最后一条新闻还是其他新闻
			 				if(i==0){
			 					nextId=ids[i+1];
			 				}else{
			 					preId=ids[i-1];
			 				}
			 			}
			 			else{
			 				nextId=ids[i+1];
			 				preId=ids[i-1];
			 			}
			 		}	
		 		}
		 	}
		 	
		 	 $.ajax({
			        type: "POST",
			        url: "${base}/notice/queryDetail",
			        data: {
			        	noticeId:noticeId,
			        },
			        dataType: "json",
			        success: function(data){
			        	//总页数
			        	var content=data.data.noticeContent;
			        	if(content.split(";split;").length>1){
			        		content=content.split(";split;")[1];
			        	}
			        	$(".news-content").html(content);
			        	$("#noticeTitle").html(data.data.noticeTitle);
			        	$("#noticeDate span").html(ym.formatTime(data.data.changeDate));
			        	replaceSrc();
			        }
			    });
		})
		$("#a_prev").on('click',function(){
	 		if(preId){
	 			location.href="${base}/ddwlGw/goodEmployeeDetail?title=优秀员工&noticeId="+preId+"&rowsId="+rowsId;
	 		}else{
	 			alert("没有更多数据");
	 		}
	 		
	 	});
	 	$("#a_next").on('click',function(){
	 		if(nextId){
	 			location.href="${base}/ddwlGw/goodEmployeeDetail?title=优秀员工&noticeId="+nextId+"&rowsId="+rowsId;
	 		}else{
	 			alert("没有更多数据");
	 		}
	 	});
	 	$("#returnList").on('click',function(){
	 		location.href="${base}/ddwlGw/goodEmployee?noticeType=5&title=优秀员工";
	 	});
	</script>
</body>
<script type="text/javascript">
	var html5=loadNextBanner(6); //大道招聘 -优秀员工
</script>
</html>
