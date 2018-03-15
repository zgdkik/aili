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
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<form class="form-horizontal" role="form" action="${base}/cus/list"
					method="get" style="border: 5px solid rgb(216, 228, 221);">
					<legend>查询条件</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label">客户名义:</label>
						<div class="col-sm-10">
							<input type="text" name="name"  class="form-control" placeholder="请输入">
							<input type="hidden" name="pageNum"  value="${pageNum}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户类型:</label>
						<div class="col-sm-4">
							<input type="text" name="type"  class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">顾问:</label>
						<div class="col-sm-4">
							<input type="text" name="counselor"  class="form-control" placeholder="请输入">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">是否有意向:</label>
						<div class="col-sm-4">
							<select class="form-control" name="intention" >
							<option value="">全部</option>
								<option value="Y">是</option>
								<option value="N">否</option>
							</select>
						</div>
						<label class="col-sm-2 control-label">行业:</label>
						<div class="col-sm-4">
							<input type="text" name="industry"  class="form-control" placeholder="请输入">
						</div>
					</div>


					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-4">
							<input type="text" name="email" class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">客户来源:</label>
						<div class="col-sm-4">
							<input type="text" name="resource" class="form-control" placeholder="请输入">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">客户等级:</label>
						<div class="col-sm-4">
							<input type="text" name="level"  class="form-control" placeholder="请输入">
						</div>
						<label class="col-sm-2 control-label">大单机会:</label>
						<div class="col-sm-4">
							<select class="form-control" name="bigOrder" >
								<option value="">全部</option>
								<option value="Y">是</option>
								<option value="N">否</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">大客户:</label>
						<div class="col-sm-4">
							<select class="form-control">
								<option value="">全部</option>
								<option value="Y">是</option>
								<option value="N">否</option>
							</select>
						</div>
					</div>


					<div class="form-group">
						<div class="col-sm-9"></div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-primary">查询</button>
							<button type="reset" class="btn btn-warning">重置</button>
						</div>
					</div>

				</form>
				<div class="col-sm-12" style="border: 5px solid rgb(216, 228, 221);">
					<div class="table-responsive">
						<table
							class="table table-striped table-bordered table-hover table-condensed">
							<caption style="font-size: 20px;">客户列表</caption>
							<thead>
								<tr>
									<th>客户名义</th>
									<th>客户类型</th>
									<th>顾问</th>

									<th>意向</th>
									<th>行业</th>
									<th>邮箱</th>

									<th>客户来源</th>
									<th>客户等级</th>
									<th>注册资本</th>

									<th>大客户</th>
									<th>大单机会</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="c">
									<tr class="active">
										<td>${c.name}</td>
										<td>${c.type}</td>
										<td>${c.intention}</td>

										<td>${c.intention}</td>
										<td>${c.industry}</td>
										<td>${c.email}</td>

										<td>${c.source}</td>
										<td>${c.level}</td>
										<td>${c.registeredCapital}</td>

										<td>${c.big}</td>
										<td>${c.bigOrder}</td>
										<td><a href="${base}/cus/edit?id=${c.id}" class="btn btn-primary btn-xs"
											role="button">修改</a> <a href="${base}/cus/del?id=${c.id}"
											class="btn btn-primary btn-xs" role="button">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<ul class="pager" pageNum="${pageNum}">
						<li><a href="#">上一页</a></li>
						<li><a href="#">下一页</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<jsp:include page="footerjs.jsp" />
</body>
</html>