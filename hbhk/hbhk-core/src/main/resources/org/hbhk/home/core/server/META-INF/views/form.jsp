<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<jsp:include page="headercss.jsp" />
<title>hbhk home</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">名字</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="firstname"
								placeholder="请输入名字">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">姓</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="lastname"
								placeholder="请输入姓">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox"> 请记住我
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>

			</div>
			<div class="span10">
				<form class="bs-example bs-example-form"
					data-example-id="simple-input-groups">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">@</span> <input
							type="text" class="form-control" placeholder="Username"
							aria-describedby="basic-addon1">
					</div>
					<br>
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Recipient's username"
							aria-describedby="basic-addon2"> <span
							class="input-group-addon" id="basic-addon2">@example.com</span>
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">$</span> <input type="text"
							class="form-control" aria-label="Amount (to the nearest dollar)">
						<span class="input-group-addon">.00</span>
					</div>
				</form>

			</div>
		</div>
	</div>

	<jsp:include page="footerjs.jsp" />
</body>
</html>