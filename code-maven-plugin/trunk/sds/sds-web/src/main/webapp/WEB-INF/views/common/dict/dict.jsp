<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head >
<title>智送叫车-首页</title>
<%@include file="../../common/common-meta.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-icon.jsp"%>
</head>
<body class="fixed-left" locale="zh-cn">
	<%@include file="../../common/common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">
		<%@include file="../../common/common-top.jsp"%>
		<jsp:include page="../../common/common-left.jsp">
			<jsp:param name="pagecode" value="index" />
		</jsp:include>
		<!-- Start right content -->
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="${base}">系统配置</a></li>
				<li class="active">数据字典管理</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
			<div class="row">
		        <div class="col-md-12">
		            <div class="panel panel-default" style="whdth:200px;height: 740px">
		                <div class="panel-heading">
		                    <h2>数据字典管理</h2>
		                </div>
		                <div class="panel-body">
		                    <div class="row" style="padding-left: 20px;display：inline;">
		                        <div class="col-md-5" style="border:2px solid #ccc;height: 600px;box-shadow: 0px 0px 20px #ccc;">
		                            <div class="row panel-body">
										<div id="query-list" class="panel-collapse collapse in">
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12">
														<table id="KeyTable" data-toggle="table"
															data-show-columns="true" data-show-toggle="true"
															data-pagination="true" data-height="500"
															data-toolbar="#key-custom-toolbar">
														</table>
														<div id="key-custom-toolbar">
															<div class="form-inline" role="form">
																<button type="submit" class="btn btn-default" onclick="showInsert()">新增</button>
															</div>
														</div>
													</div>
												</div>
											</div>
							           </div>
									</div>
		                        </div>
		                        <div class="col-md-6"  style="border:2px solid #ccc;box-shadow: 0px 0px 20px #ccc;height: 600px; margin-left: 20px;">
		                            <div class="row panel-body">
										<div id="query-list" class="panel-collapse collapse in">
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12">
														<table id="valueTable" data-toggle="table"
															data-show-columns="true" data-show-toggle="true"
															data-pagination="true" data-height="500"
															data-toolbar="#value-custom-toolbar">
														</table>
														<div id="value-custom-toolbar">
															<div class="form-inline" role="form">
																<button type="submit" class="btn btn-default" onclick="showDataNew()" id="dataInsert" disabled="disabled">新增</button>
															</div>
														</div>
													</div>
												</div>
											</div>
							           </div>
									</div>
		                       </div>
		                   </div>
		                </div>
		            </div>
		        </div>
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
						           
						            <h4 class="modal-title" id="myModalLabel">
						               <span id="title"></span>
						            </h4>
						         </div>
						         <div class="modal-body">
										<form id="dictKeyForm" method="post" class="form-horizontal" action="">
										    <input type="hidden" id="dictId"/>
										     <input type="hidden" id="operType"/>
				                            <div class="row">
												<div class="form-group">
													<label class="col-md-3 control-label">分类名称:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control" id="dictCode" name="dictCode"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">分类别名:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control" id="dictName" name="dictName"/>
													</div>
												</div>
											</div>
										</form>
						          </div>
						         <div class="modal-footer">
										<button type="submit" class="btn btn-primary" name="save" id="save">保存</button>
										<button type="submit" class="btn btn-primary" name="edit" id="edit">修改</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								 </div>
						      </div><!-- /.modal-content -->
						</div><!-- /.modal -->
			</div>
			
			
			
			 <div class="modal fade" id="myModalValue" tabindex="-1" role="dialog" 
						        aria-labelledby="myModalLabel" aria-hidden="true">
						   <div class="modal-dialog">
						      <div class="modal-content">
						         <div class="modal-header">
						            <button type="button" class="close" 
						               data-dismiss="modal" aria-hidden="true">
						                  &times;
						            </button>
						           
						            <h4 class="modal-title" id="myModalLabel">
						               <span id="titleValue"></span>
						            </h4>
						         </div>
						         <div class="modal-body">
										<form id="dictValueForm" method="post" class="form-horizontal" action="">
										    <input type="hidden" id="dictCodeId"/>
										    <input type="hidden" id="valueId"/>
				                            <div class="row">
												<div class="form-group">
													<label class="col-md-3 control-label">关键字:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control" id="dataKey" name="dataKey"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">值:</label>
													<div class="col-md-5">
														<input type="text" maxlength="30" class="form-control" id="dataValue" name="dataValue"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">序号:</label>
													<div class="col-md-5">
														<input type="number" maxlength="30" class="form-control" id="orderNo" name="orderNo"/>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-md-3 control-label">备注:</label>
													<div class="col-md-5">
														<textarea rows="5" cols="50" name="remark" id="remark" ></textarea>
													</div>
												</div>
											</div>
										</form>
						          </div>
						         <div class="modal-footer">
										<button type="submit" class="btn btn-primary" name="keep" id="keep">保存</button>
										<button type="submit" class="btn btn-primary" name="update" id="update">修改</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								 </div>
						      </div><!-- /.modal-content -->
						</div><!-- /.modal -->
			</div>
				<!--主要内容开始部分 END-->
			</div>
		</div>
	</div>
    <%@include file="../../common/common-footer.jsp"%>

	<%@include file="../../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	
	<script src="${base}/scripts/common/dict.js"></script>

</body>
</html>