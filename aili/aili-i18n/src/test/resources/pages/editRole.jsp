<%@include file="/pages/commons/common.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>首页 | WMS</title>
<%@include file="/pages/commons/common-meta.jsp" %>
<%@include file="/pages/commons/common-css.jsp" %>
<%@include file="/pages/commons/common-compatible.jsp" %>
<%@include file="/pages/commons/common-icon.jsp" %>

</head>
<body class="fixed-left" locale="zh-cn">
<%@include file="/pages/commons/common-layer.jsp" %>
<!-- Begin page -->
<div id="wrapper"> 
  
  <%@include file="/pages/commons/common-top.jsp" %>
  
  
  <jsp:include page="/pages/commons/common-left.jsp">
  	<jsp:param name="pagecode" value="index" />
  </jsp:include>
  
  <!-- Start right content -->
  <div class="content-page">
    <ol class="breadcrumb">
      <li class="active"><a href="#">仪表盘</a></li>
    </ol>
    <!-- ============================================================== --> 
    <!-- Start Content here --> 
    <!-- ============================================================== -->
    <div class="content"> 
      <!--主要内容开始部分 START-->
       <div class="wms-wrpper">
        <div class="row">
          <div class="col-md-12">
            <div class="widget-newall">
              <div class="row">
                <div class="col-md-4">
                	<c:if test="${role.id==null}">
                		<h3 class="marign-none role-title">新建角色</h3>
                	</c:if>
                	<c:if test="${role.id!=null}">
                		<h3 class="marign-none role-title">修改角色</h3>
                	</c:if>
                </div>
                <div class="col-md-8">
                  <div class="toolbar-btn-action">
                    <button class="btn  btn-primary pull-right role-save" type="submit"><i class="fa fa-save"></i> 保存 </button>
                  </div>
                </div>
              </div>
              <div class="widget-content bck-white-all widget-content-mg content-all-padding overflow">
                <form class="form-horizontal role" role="form" data-toggle="form-validator" id="role-form">
                  <input type="hidden" placeholder="高级总监" id="input-text" value="${role.id}" class="form-control id">
                  <div class="form-group">
                    <label class="col-sm-2 control-label" for="input-text">角色名称</label>
                    <div class="col-sm-10">
                      <input type="text" placeholder="高级总监" id="input-text" value="${role.name}" class="form-control name" required/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">基础组织</label>
                   <div class="col-sm-10">
                      <p class="form-control-static">仓库</p>
                    </div>
                  </div>
                </form>
                <!-- 组织对应权限开始 -->
               	<c:set var="dataPris" value="${dataPriMap[orgid]}" ></c:set>
               	<c:set var="funPris" value="${funPriMap[orgid]}" ></c:set>
                <div class="form-group form-group-Privilege" orgid="${orgid}"  style="display:block;">
                  <div class="col-sm-12 form-Privilege-bck">
                    <h4 class="pri-title">勾选权限(代表仓库)</h4>
                  </div>
                  <div class="row">
                    <div class="col-xs-12 col-md-12">
                      <div class="col-xs-8 col-md-8 border-g-lf-rg">
                        <h5><strong>数据权限</strong></h5>
                      </div>
                      <div class="col-xs-4 col-md-4 border-g-right">
                        <h5><strong>功能权限</strong></h5>
                      </div>
                    </div>
                  </div>
                  <div class="border-g overflow">
                    <div class="col-xs-8 col-md-8 border-g-right">
                      <div class="widget margin-bottom-none">
                        <div class="widget-content">
                          <div class="table-responsive">
                            <table data-sortable class="table table-hover th-bold-none">
                              <thead>
                                <tr>
                                  <th>No</th>
                                  <th><input type="checkbox" class="rows-check check-all" data-target="review">
                                 	   查看 </th>
                                  <th><input type="checkbox" class="rows-check check-all" data-target="rebuild">
                                 	   新建 </th>
                                  <th><input type="checkbox" class="rows-check check-all" data-target="edit">
                                    	修改 </th>
                                  <th><input type="checkbox" class="rows-check check-all" data-target="delete">
                                 	   删除 </th>
                                  <th><input type="checkbox" class="rows-check check-all" data-target="all">
                                    	所有 </th>
                                </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${dataPris}" var="fun" varStatus="s">
                                <tr>
                                  <td>${fun.name}</td>
                               		<td><input type="checkbox" funid="${fun.funIds[0]}"  class="rows-check" data-role="review"></td>
                               	 	<td><input type="checkbox" funid="${fun.funIds[1]}"   class="rows-check" data-role="rebuild"></td>
                               		<td><input type="checkbox" funid="${fun.funIds[2]}"  class="rows-check" data-role="edit"></td>
                               	 	<td><input type="checkbox" funid="${fun.funIds[3]}"   class="rows-check" data-role="delete"></td>
                                  <td><input type="checkbox" class="rows-check" data-role="all"></td>
                                </tr>
                               </c:forEach>
                              </tbody>
                            </table>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-xs-4 col-md-4">
                      <div class="widget margin-bottom-none">
                        <div class="widget-content">
                          <div class="table-responsive table-checkbox-style-a">
                            <table class="table table-hover table-bordernone th-bold-none">
                              <thead>
                                <tr>
                                  <th class="col-xs-2 col-md-2"></th>
                                  <th class="col-xs-1 col-md-1"><input type="checkbox" class="rows-check check-all" data-target="execute">
                               		    执行 </th>
                                  <th class="col-xs-1 col-md-1"><input type="checkbox" class="rows-check check-all" data-target="config">
                                  	  配置 </th>
                                </tr>
                              </thead>
                            </table>
	                            <c:forEach items="${funPris}" var="funPri">
	                            	<c:set var="pfs" value="${funPri.value}"></c:set>
		                            <table data-sortable class="table table-hover  table-check-bbottom">
		                              <thead>
		                                <tr class="formth-Privilege-bck-t">
		                                  <th colspan="3">分类${funPri.key}功能</th>
		                                </tr>
		                              </thead>
		                              <tbody>
		                              	 <c:forEach items="${pfs}" var="pri">
			                                <tr>
			                                  <td class="col-xs-2 col-md-2">${pri.name}功能</td>
			                                  <c:set value="${pri.funIds}" var="prifuns"></c:set>
		                                  	 	<td class="col-xs-1 col-md-1"><input type="checkbox" funid="${prifuns[0]}" class="rows-check" data-role="execute"></td>
		                                  		<td class="col-xs-1 col-md-1"><input type="checkbox" funid="${prifuns[1]}" class="rows-check" data-role="config"></td>
			                                </tr>
		                               	</c:forEach>
		                              </tbody>
		                            </table>
	                            </c:forEach>
							 </div>
	                        </div>
	                      </div>
	                    </div>
	                  </div>
	                </div>
                 <!-- 组织对应权限结束 -->
              </div>
              <div class="mg-top10"> <a class="btn btn-default cancel" href="${pagebase}/auth/oper/role/list">
              	<i class="icon-cancel-2"></i> 取消 </a> 
               	<a class="btn btn-primary role-save pull-right" href="javascript:void(0)"><i ></i> 保存 </a>
              	<button type="button" class="btn btn-default pull-right role-save-current"><i></i> 保存在本页</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!--主要内容开始部分 END--> 
     <%@include file="/pages/commons/common-footer.jsp" %>
    </div>
    <!-- ============================================================== --> 
    <!-- End content here --> 
    <!-- ============================================================== --> 
    
  </div>
  <!-- End right content --> 
  
</div>

<!-- End of page --> 
<!-- the overlay modal element -->
<div class="md-overlay"></div>
<%@include file="/pages/commons/common-menu-template.jsp" %>
<!-- End of eoverlay modal --> 
<script>
		var resizefunc = [];
</script> 
 <%@include file="/pages/commons/common-script.jsp" %>

<!-- Page Specific JS Libraries --> 
<script src="${staticbase}/assets/js/wmspages/auth/operate/editRole.js?${version}"></script>

<script type="text/javascript">
	var fids = "${fids}";
</script>
</body>
</html>