<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<jsp:include page="headercss.jsp" />
<title>编辑客户</title>
<script type="text/javascript">
	$(function() {
		var msg ="${msg}";
		if(msg!=null && msg!=""){
			$("[data-toggle='popover']").popover();
		}
	});
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" style="border: 5px solid rgb(216, 228, 221);">
				<form class="form-horizontal" role="form" method="post"
					action="${base}/cus/edit">
					<legend>客户名义</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label">客户名义:</label>
						<div class="col-sm-10">
							<input type="text" name="name" class="form-control"
								placeholder="请输入" required value="${cus.name}">
							<input type="hidden" name="id" value="${cus.id}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户类型:</label>
						<div class="col-sm-4">
							<input type="text" name="type" class="form-control"
								placeholder="请输入" required value="${cus.type}">
						</div>
						<label class="col-sm-2 control-label">顾问:</label>
						<div class="col-sm-4">
							<input type="text" name="counselor" class="form-control"
								placeholder="请输入" required value="${cus.counselor}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户定义[EN]:</label>
						<div class="col-sm-4">
							<input type="text" name="nameEn" value="${cus.nameEn}" class="form-control"
								placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">客户所在地方:</label>
						<div class="col-sm-4">
							<input type="text" name="location" value="${cus.location}" class="form-control"
								placeholder="请输入" required>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户地址:</label>
						<div class="col-sm-4">
							<input type="text" name="address" value="${cus.address}" class="form-control"
								placeholder="请输入" required>
						</div>
						<label class="col-sm-2 control-label">是否有意向:</label>
						<div class="col-sm-4">
							<select class="form-control" value="${cus.intention}" name="intention" required>
								<c:if test="${cus.intention =='Y' || cus.intention==null}">
									<option value="Y">是</option>
									<option value="N">否</option>
								</c:if>
								<c:if test="${cus.intention =='N'}">
									<option value="N">否</option>
									<option value="Y">是</option>
								</c:if>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户地址[EN]:</label>
						<div class="col-sm-4">
							<input type="text" name="addressEn" value="${cus.addressEn}" class="form-control"
								placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">行业:</label>
						<div class="col-sm-4">
							<input type="text" name="industry" value="${cus.industry}" required class="form-control"
								placeholder="请输入">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-4">
							<input type="text" name="email" value="${cus.email}" class="form-control"
								placeholder="请输入" required type="email">
						</div>
						<label class="col-sm-2 control-label">客户来源:</label>
						<div class="col-sm-4">
							<input type="text" name="resource" value="${cus.resource}" class="form-control"
								placeholder="请输入" required>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">公司网站:</label>
						<div class="col-sm-4">
							<input type="text" name="network" value="${cus.network}" class="form-control"
								placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">客户等级:</label>
						<div class="col-sm-4">
							<input type="text" name="level" value="${cus.level}" class="form-control"
								placeholder="请输入" required>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">注册资本:</label>
						<div class="col-sm-4">
							<input type="text" name="registeredCapital" class="form-control"
								placeholder="请输入" required value="${cus.registeredCapital}">
						</div>
						<label class="col-sm-2 control-label">大客户:</label>
						<div class="col-sm-4">
							<select class="form-control" name="big" required>
								<c:if test="${cus.big =='Y' || cus.big==null}">
									<option value="Y">是</option>
									<option value="N">否</option>
								</c:if>
								<c:if test="${cus.big =='N'}">
									<option value="N">否</option>
									<option value="Y">是</option>
								</c:if>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">大单机会:</label>
						<div class="col-sm-4">
							<select class="form-control" name="bigOrder" required>
								<c:if test="${cus.bigOrder =='Y' || cus.bigOrder==null}">
									<option value="Y">是</option>
									<option value="N">否</option>
								</c:if>
								<c:if test="${cus.bigOrder =='N'}">
									<option value="N">否</option>
									<option value="Y">是</option>
								</c:if>
							</select>
						</div>
						<label class="col-sm-2 control-label">平台开通情况:</label>
						<div class="col-sm-4">
							<select class="form-control" name="open" required>
								<c:if test="${cus.open =='Y' || cus.open==null}">
									<option value="Y">是</option>
									<option value="N">否</option>
								</c:if>
								<c:if test="${cus.open =='N'}">
									<option value="N">否</option>
									<option value="Y">是</option>
								</c:if>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">备注:</label>
						<div class="col-sm-10">
							<textarea rows="3" name="remark" value="${cus.remark}" class="form-control"></textarea>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-5"></div>
						<div class="col-sm-7">
							<button type="submit" class="btn btn-primary"
							 title="提示信息"  
						      data-container="${msg}" data-toggle="popover" data-placement="top" 
						      >提交</button>
							<button type="reset" class="btn btn-warning">重置</button>
						</div>
					</div>

				</form>

			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<jsp:include page="footerjs.jsp" />
</body>
</html>