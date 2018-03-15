<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智送叫车-首页</title>
<%@include file="../common/common-meta.jsp"%>
<%@include file="../common/common-css.jsp"%>
<%@include file="../common/common-icon.jsp"%>
</head>
<body class="fixed-left" locale="zh-cn">
	<%@include file="../common/common-layer.jsp"%>
	<!-- Begin page -->
	<div id="wrapper">

		<%@include file="../common/common-top.jsp"%>


		<jsp:include page="../common/common-left.jsp">
			<jsp:param name="pagecode" value="index" />
		</jsp:include>
		<!-- Start right content -->
		<div class="content-page">
			<ol class="breadcrumb">
				<li><a href="/sds-web">智送系统</a></li>
				<li class="active">表单</li>
			</ol>
			<div class="content">
				<!--主要内容开始部分 START-->
				<div class="row">
				    <div class="panel panel-default">
		                <div class="panel-heading">
		                	<h3 class="panel-title">
					        <a data-toggle="collapse" data-parent="#accordion" 
					          href="#form">
					          	表单实例
					        </a>
					      </h3>
		                </div>
		                 <div id="form" class="panel-collapse collapse in">
		                <div class="panel-body">
		                    <div class="row">
		                        <div class="col-md-12">
		                           <form id="defaultForm" method="post" class="form-horizontal"
								action="target.php">
								<div class="form-group">

									<label class="col-md-2 control-label">Full name</label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="firstName"
											placeholder="First name" />
									</div>

									<label class="col-md-2 control-label">simple name</label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="lastName"
											placeholder="Last name" />
									</div>


									<label class="col-md-2 control-label">simple name</label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="lastName"
											placeholder="Last name" />
									</div>

								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Username</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="username" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Email address</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="email" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Password</label>
									<div class="col-md-5">
										<input type="password" class="form-control" name="password" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Retype password</label>
									<div class="col-md-5">
										<input type="password" class="form-control"
											name="confirmPassword" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Gender</label>
									<div class="col-md-5">
										<div class="radio">
											<label> <input type="radio" name="gender"
												value="male" /> Male
											</label>
										</div>
										<div class="radio">
											<label> <input type="radio" name="gender"
												value="female" /> Female
											</label>
										</div>
										<div class="radio">
											<label> <input type="radio" name="gender"
												value="other" /> Other
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Birthday</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="birthday" />
										(YYYY/MM/DD)
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Languages</label>
									<div class="col-md-5">
										<div class="checkbox">
											<label> <input type="checkbox" name="languages[]"
												value="english" /> English
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="languages[]"
												value="french" /> French
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="languages[]"
												value="german" /> German
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="languages[]"
												value="russian" /> Russian
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="languages[]"
												value="other" /> Other
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Programming
										Languages</label>
									<div class="col-md-5">
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="net" /> .Net
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="java" /> Java
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="c" /> C/C++
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="php" /> PHP
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="perl" /> Perl
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="ruby" /> Ruby
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="python" /> Python
											</label>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="programs[]"
												value="javascript" /> Javascript
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label" id="captchaOperation"></label>
									<div class="col-md-2">
										<input type="text" class="form-control" name="captcha" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-9 col-md-offset-3">
										<button type="submit" class="btn btn-primary" name="signup"
											value="Sign up">Sign up</button>
										<button type="submit" class="btn btn-primary" name="signup2"
											value="Sign up 2">Sign up 2</button>
										<button type="button" class="btn btn-info" id="validateBtn">Manual
											validate</button>
										<button type="button" class="btn btn-info" id="resetBtn">Reset
											form</button>
									</div>
								</div>
							</form>
		                        </div>
		                    </div>
		                </div>
		                </div>
		            </div>
				</div>
				<!--主要内容开始部分 END-->
			</div>
			<!-- ============================================================== -->
			<!-- End content here -->
			<!-- ============================================================== -->
			<%@include file="../common/common-footer.jsp"%>
		</div>
		<!-- End right content -->

	</div>

	<!-- End of page -->
	<!-- the overlay modal element -->

	<%@include file="../common/common-modal-footer.jsp"%>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<%@include file="../common/common-script.jsp"%>

	<!-- Page Specific JS Libraries -->
	<script src="${base}/scripts/demo/form-validation.js?${version}"></script>

	<script type="text/javascript">
		var maxPage = "${param.pageCount}";
		var curPage = "${param.currentPage}";
	</script>
</body>
</html>