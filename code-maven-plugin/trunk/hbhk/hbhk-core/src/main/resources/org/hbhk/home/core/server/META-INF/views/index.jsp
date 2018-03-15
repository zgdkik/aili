<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<jsp:include page="headercss.jsp" />
<title>客户管理</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">客户定义:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户类型:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">顾问:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户定义[EN]:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">客户所在地方:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户地址:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">是否有意向:</label>
						<div class="col-sm-4">
							 <select class="form-control">
						         <option value="Y">是</option>
						         <option value="N">否</option>
						      </select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户地址[EN]:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">行业:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
					</div>



					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">客户来源:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">公司网站:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">客户等级:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">注册资本:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">大客户:</label>
						<div class="col-sm-4">
							 <select class="form-control">
						         <option value="Y">是</option>
						         <option value="N">否</option>
						      </select>
						</div>
					</div>
					
					<div class="form-group">
							<label class="col-sm-2 control-label">记录时间:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="请输入">
							</div>
							<label class="col-sm-2 control-label">平台开通情况:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="请输入">
							</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">大单机会:</label>
						<div class="col-sm-4">
							 <select class="form-control">
						         <option value="Y">是</option>
						         <option value="N">否</option>
						      </select>
						</div>  
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">备注:</label>
						<div class="col-sm-10">
							<textarea rows="3"  class="form-control"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-5"></div>
						<div class="col-sm-7">
							<button type="submit" class="btn btn-primary">提交</button>
 							<button type="reset" class="btn btn-warning">重置</button>
						</div>
					</div>
 					
				</form>

			</div>
			<div class="col-sm-4">col-sm-2</div>
		</div>
	</div>
	<jsp:include page="footerjs.jsp" />
</body>
</html>