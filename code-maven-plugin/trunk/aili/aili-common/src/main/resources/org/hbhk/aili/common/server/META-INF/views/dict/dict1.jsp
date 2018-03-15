<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<c:import url="../../commons/common-meta.jsp" />
<c:import url="../../commons/common-css.jsp" />
<link href="${base}/styles/home/custom.css?${version}" rel="stylesheet">
</head>
<body class="sticky-header">
	<section>
		<!--body wrapper start-->
		<div class="wrapper">
			<div class="row">
				<div class="col-sm-3">
					<div class="panel">
						<div class="panel-heading">
								<h3 class="panel-title">数据字典分类</h3>
						</div>
						<div class="panel-body">
							<div class="col-xs-12">
								<button class="btn btn-primary col-xs-2" type="submit" id="addDict">新增</button>
								<div class="col-xs-1"></div>
								<button class="btn btn-primary col-xs-2" type="submit" id="updateDict">修改</button>
								<div class="col-xs-1"></div>
								<button class="btn btn-primary col-xs-2" type="submit" id="deleteDict">删除</button>
							</div>
						</div>
						<div class="panel-body">
								<div class="col-xs-12" style="height: 653px;">
										<div id="treeDict" class="ztree"></div>
								</div>
						</div>
					</div>
				</div>
				<div class="col-sm-9">
					<!--用户列表  -->
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">数据字典管理</h3>
						</div>
						<div class="panel-body">
								<div class="col-xs-12">
									<table id="dictValueTable" data-toggle="table" data-show-columns="true"
										 data-height="700" data-pagination="true" class="table-striped"
										data-toolbar="#custom-toolbar">
									</table>
									<div id="custom-toolbar">
										<div class="form-inline" role="form">
											<button class="btn btn-primary" type="submit" id="addDictValue">新增</button>
										</div>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--body wrapper end-->
		<div class="modal fade" id="myDictModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span id="title">新增数据字典分类</span>
						</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="dict-form" class="form-horizontal adminex-form">
							<input type="hidden" name="parentDictCode">
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">数据字典分类名称</label>
								<div class="col-xs-8">
									<input type="text" name="dictName" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">数据字典分类编号</label>
								<div class="col-xs-8">
									<input type="text" name="dictCode" class="form-control">
								</div>

							</div>
							
							<div class="form-group ">

								<label class="col-xs-4 control-label">描述</label>
								<div class="col-xs-8">
									<textarea class="col-xs-12" name="remark" rows="5" cols=""></textarea>
								</div>

							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary save" id="saveDict">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<div class="modal fade" id="myUpdateDictModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabelUpdate" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabelUpdate">
							<span id="titleUpdate">修改数据字典分类</span>
						</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="dictUpdate-form" class="form-horizontal adminex-form">
							<input type="hidden" name="id" >
							<input type="hidden" name="parentDictCode">
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">数据字典分类名称</label>
								<div class="col-xs-8">
									<input type="text" name="dictName" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">数据字典分类编号</label>
								<div class="col-xs-8">
									<input type="text" name="dictCode" class="form-control" readonly="readonly">
								</div>

							</div>
							
							<div class="form-group ">

								<label class="col-xs-4 control-label">描述</label>
								<div class="col-xs-8">
									<textarea class="col-xs-12" name="remark" rows="5" cols=""></textarea>
								</div>

							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary save" id="saveUpdateDict">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<div class="modal fade" id="myDictValueModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							<span id="title">新增数据字典</span>
						</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="dictValue-form" class="form-horizontal adminex-form">
							<input type="hidden" name="dictCode">
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">关键字</label>
								<div class="col-xs-8">
									<input type="text" name="key" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">值</label>
								<div class="col-xs-8">
									<input type="text" name="value" class="form-control">
								</div>
							</div>
							<div class="form-group ">
								<label class="col-xs-4 control-label">序号</label>
								<div class="col-xs-8">
									<input type="number" class="form-control" name="orderNo" min="0" max="100" />
								</div>
							</div>
							
							
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">扩展参数1</label>
								<div class="col-xs-8">
									<input type="text" name="paramOne" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">扩展参数2</label>
								<div class="col-xs-8">
									<input type="text" name="paramTwo" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">扩展参数3</label>
								<div class="col-xs-8">
									<input type="text" name="paramThree" class="form-control">
								</div>

							</div>
							
							<div class="form-group ">

								<label class="col-xs-4 control-label">描述</label>
								<div class="col-xs-8">
									<textarea class="col-xs-12" name="remark" rows="5" cols=""></textarea>
								</div>

							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary save" id="saveDictValue">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<div class="modal fade" id="myDictValueUpdateModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 500px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							<span id="title">新增数据字典</span>
						</h4>
					</div>
					<div class="modal-body">
						<form role="form" id="dictValueUpdate-form" class="form-horizontal adminex-form">
							<input type="hidden" name="id">
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">关键字</label>
								<div class="col-xs-8">
									<input type="text" name="key" class="form-control" readonly="readonly">
								</div>

							</div>
							<div class="form-group ">
								<label class="col-xs-4 control-label">值</label>
								<div class="col-xs-8">
									<input type="text" name="value" class="form-control">
								</div>
							</div>
							<div class="form-group ">
								<label class="col-xs-4 control-label">序号</label>
								<div class="col-xs-8">
									<input type="text" class="form-control" name="orderNo" readonly="readonly"/>
								</div>
							</div>
							
							
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">扩展参数1</label>
								<div class="col-xs-8">
									<input type="text" name="paramOne" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">扩展参数2</label>
								<div class="col-xs-8">
									<input type="text" name="paramTwo" class="form-control">
								</div>

							</div>
							<div class="form-group ">
								
								<label class="col-xs-4 control-label">扩展参数3</label>
								<div class="col-xs-8">
									<input type="text" name="paramThree" class="form-control">
								</div>

							</div>
							
							<div class="form-group ">

								<label class="col-xs-4 control-label">描述</label>
								<div class="col-xs-8">
									<textarea class="col-xs-12" name="remark" rows="5" cols=""></textarea>
								</div>

							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary save" id="updateDictValue">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</section>
	<c:import url="../../commons/common-script.jsp" />
	<script type="text/javascript"
		src="${base}/scripts/common/dict/dict.js?${version}"></script>
</body>
</html>
