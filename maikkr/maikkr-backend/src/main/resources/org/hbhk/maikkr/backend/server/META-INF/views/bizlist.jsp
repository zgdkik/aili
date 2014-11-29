<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp" />
<link href="${styles}/userlist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${scripts}/bizlist.js"></script>
<body>
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">商家信息</h3>
		</div>
		<div  class="panel-body tbl-list">
			<div align="center" style="height: auto;">
				<table id="list"></table>
				<div id="gridPager"></div>
			</div>
		</div>
			<!-- <div class="panel-footer"></div> -->
	</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">图片上传</h4>
         </div>
         <div class="modal-body">
         	<form class="upload-form" action="${base}backend/upload.htm" method="post" enctype="multipart/form-data">
       			<input type="file" name="filedata">
       			<input type="hidden" class="bizid" name="id">
       			<button type="submit" style="float: right;" class="btn btn-default">提交</button>
       		</form>
         </div>
         <div class="modal-footer">
         </div>
      </div>
</div>
</body>
</html>