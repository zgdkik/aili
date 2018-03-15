<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!-- Top Bar Start -->
<div class="col-md-8 col-md-offset-2 marTop">
	<div class="row nav-top">
		<div class="col-md-12">
			<div class="row pull-left">
				<c:if test="${base==null ||base==''}">
					<a href="/"><span><img src="${base}/images/home/icon/logo.png"></span></a>
				</c:if>
				<c:if test="${base!=null && base !=''}">
					<a href="${base}"><span><img src="${base}/images/home/icon/logo.png"></span></a>
				</c:if>
			</div>
			<div class="row pull-right">
				<div id="menu1">
					<ul id="" class="">
						<li><span>壹米滴答,让货运更智慧</span></li>
						<c:if test="${userContext!=null}">
						<li class="top_icon"><a href='javascript:void(0);'><span>${userContext.name}</span></a>
							<ul>
								<li data-toggle="modal" data-target="#pwd-change"><a><i
										class="icon-edit"></i>修改密码</a></li>
								<li><a class="md-trigger" data-modal="logout-modal"><i
										class="icon-logout-1"></i> 退出</a></li>
							</ul></li>
						</c:if>
					</ul>
				</div>
				<div id="menu">
					<ul id="" class="">
						<c:forEach items="${userMenuList}" var="m">
							<li><a href='javascript:void(0);'><span>${m.name}</span>
							</a> <c:if
									test="${m.childList !=null &&  fn:length(m.childList) != 0}">
									<ul>
										<c:forEach items="${m.childList}" var="sm">
											<li><a href='${base}${sm.url}' mid='${sm.id}'><span>${sm.name}</span></a> <c:if
													test="${sm.childList !=null} &&  fn:length(sm.childList) != 0">
													<ul>
														<c:forEach items="${sm.childList}" var="csm">
															<li><a href='${base}${csm.url}' mid='${csm.id}'><span>${csm.name}</span></a>
															</li>
														</c:forEach>
													</ul>
												</c:if></li>
										</c:forEach>
									</ul>
								</c:if></li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>


	</div>
	<div class="row">