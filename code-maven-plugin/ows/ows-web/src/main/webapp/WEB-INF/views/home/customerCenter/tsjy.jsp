<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<title>首页</title>

</head>
<body class="home-bg">
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
			<div class="banner ub bannerReplace" id="child_banner" style="width:885px;height:173px;"></div>
			<div class="contenr-right-bottom ub">
			   <a href="#myModal_tsjy" data-toggle="modal" class="btn" style="margin-left: 685px;background: #FFCC00" id="btnAddress" name="btnAddress">咨询/建议/投诉</a>
			<div class="modal fade" id="myModal_tsjy" tabindex="-1" role="dialog" aria-hidden="true">
		        <form id="form_tsjy" role="form" action="" class="form-horizontal">
		        <div class="modal-dialog">
		            <div class="modal-content">
		                <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                    <h4 class="modal-title">投诉建议</h4>
		                </div>
		                <div class="modal-body">
		                    <div class="form-group">
		                        <label for="txt_sunit" class="col-xs-2 control-label col-lable-8">问题类型：</label>
		                        <div class="col-xs-9">
		                                        <input type="radio" name="problemTypes"value="1" checked="checked"/>咨询&nbsp;&nbsp;
									    		<input type="radio" name="problemTypes"value="2" />建议&nbsp;&nbsp;
									    		<input type="radio" name="problemTypes"value="3" />投诉&nbsp;&nbsp;
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label for="txt_stel" class="col-xs-2 control-label col-lable-8">您的姓名：</label>
		                        <div class="col-xs-9">
		                            <input class="form-control validate[required,custom[chinese]]" type="text" id="name" name="name"  placeholder="必填"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label for="txt_saddress" class="col-xs-2 control-label col-lable-8">您的手机：</label>
		                        <div class="col-xs-9">
		                            <input class="form-control validate[required,custom[phone]]" type="text" name="mobilePhone" id="mobilePhone" value="" placeholder="必填" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label for="txt_saddress" class="col-xs-2 control-label col-lable-8">邮箱地址：</label>
		                        <div class="col-xs-9">
		                            <input class="form-control" type="text" name="email" id="email" value="" placeholder="选填" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label for="txt_saddress" class="col-xs-2 control-label col-lable-8">物流单号：</label>
		                        <div class="col-xs-9">
		                            <input class="form-control validate[required,custom[phone]]" type="text" name="singleNumber" id="singleNumber" value=""  placeholder="选填"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label for="txt_saddress" class="col-xs-2 control-label col-lable-8">问题描述：</label>
		                        <div class="col-xs-9">
		                            <textarea class="form-control textarea" name="problemDescription" id="problemDescription"  placeholder="必填"></textarea>
		                        </div>
		                    </div>
		                </div>
		                <label id="errmsg_a" class="text-danger" style="padding:0 15px"></label>
		                <div class="modal-footer">
		                    	<label for="type"></label>
				    			<input id="tijiao" style="width: 60px;height: 34px;line-height: 34px" type="button" value="提交" class="btn" onclick="insert()"/>
				    			<input type="reset" style="width: 60px;height: 34px;line-height: 34px" value="重置" class="btn" />
						</div>
		            </div>
		        </div>
		        </form>
		    </div>
              <hr>
              
              <div class="qiye-news"><!-- 动态添加数据 -->
				<ul class="news1" ><!-- 动态添加在new1中的数据 -->
				
				</ul>
				<div class="fenye">
					<nav>
					  <ul class="pagination" style="float:right;"></ul><!--分页 -->
					</nav>
				</div>
		   </div>
	   </div>	
	</div>
</div>
	
	<c:import url="../../commons/common-script.jsp" />
	<c:import url="../../commons/common-footer.jsp" />
</body>
<script type="text/javascript">
	$(function(){
		fn_get_menu("ddwl_khzx");
		fn_get_notice_list();
	})
	  //投诉建议
	
    var page=1;
    var rows=10;	
   function fn_get_notice_list(){
		 $.ajax({
	        type: "post",
	        url: base+"/complaintAdvice/queryTsjyList",
	        data: {
	        	page:page,
	        	rows:rows,
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
		          $(data.data.rows).each(function(i,cm){
		        	  //拼接字符串 
		       		  strList+='<li>留言内容：'+cm['problemDescription']+'&nbsp;&nbsp';
		       		  if(cm['customerReply']!=null&&cm['customerReply']!=""){
		       		       strList+='<br/><font style="color:red">客服回复：'+cm['customerReply']+'</font>&nbsp;&nbsp';
		       		  }else{
		       			   strList+='<br/><font style="color:red">留言等待回复……</font>&nbsp;&nbsp';
		       		  }
		       		  strList+='<font style="float:right; padding-right: 30px">留言时间：'+ym.formatDate(cm['createTime'])+'</font>&nbsp;&nbsp;<font style="float:right; padding-right: 10px">留言人：'+cm['name']+'</font></li>';
		       	  });
		       	  $('.news1').html(strList);//拼接字符串
		       	var marginTop=$(window).height()-$(".footer").offset().top-$(".footer").height()-50-20;
	        }
	    });
	}
	function fn_set_page(_page){
		page=_page;
		fn_get_notice_list();
	}
	
	function insert(){
		//非空验证
		var csName = $("#name").val();
		var csPhone =$("#mobilePhone").val();
		var csContent = $("#problemDescription").val();
		var csEmail = $("#email").val();
	 	if(csName==''){
			alert("姓名必须填写");
			$("#name").focus();
			return;
		}
		 var checkPhone = /^1[2-9]{1}[0-9]{9}$/;
		 if(csPhone==''||!checkPhone.test(csPhone)){
			 alert("请您填写正确的手机号");
			 $("#mobilePhone").foucs();
			 return;
		 }
		 if(csEmail.length>1){
			 var checkEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			 if(csEmail!=''&&!checkEmail.test(csEmail)){
				 alert("请填写正确的E-Maml邮箱");
				 $("#email").foucs();
				 return;
			 }
		 }
		 var chContent = /\<\w+.*\>/gi;
		 if(chContent.test(csContent) || csContent.length==0){
			 alert("内容描述为空或存在非法字符");
			 $("#problemDescription").focus();
			 return;
		 }
		$("#tijiao").attr("disabled",true);
		$("#tijiao").val("提交中…");
	 	$.ajax({  
	 		url: base+"/complaintAdvice/saveTsjy",
			type:'post',
			data:$("#form_tsjy").serialize(),
			dataType:'json',
			error:function(){
				alert("系统异常,请稍后尝试");
			}, 
			success:function(state){ 
				if(state=='1'){
					fn_get_notice_list(); //刷新列表
					$('#myModal_tsjy').modal('hide')
// 					alert("提交成功,请您耐心等待回复,谢谢！");
// 					location.href=base+'/ddwlGw/register';
				}else if(state=='2'){
					alert("问题留言提交失败,请您重新操作！");
					$("#tijiao").val("提交");
					$("#tijiao").attr("disabled",false);
				}else if(state=='3'){
					alert("非法操作");
					location.href=base+'/ddwlGw/register';
				}
			}  
		});
	}
</script>
<script type="text/javascript">
		var html5=loadNextBanner(5); //客户服务-货物查询
</script>	

</html>