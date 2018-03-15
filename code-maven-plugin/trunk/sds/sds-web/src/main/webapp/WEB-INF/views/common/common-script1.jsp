<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var base = "${base}";
	var staticbase = "${staticbase}";
	try {
		var userContext = eval('(' + '${userContextJson}' + ')'); 
	} catch (e) {
	}
	var jsessionid="${pageContext.session.id}";
</script>
<script src="${staticbase }/libs/bootstrap-datetimepicker/js/moment-with-locales.js?${version}"></script>
<script src="${base}/wro/base-js.js?${version}"></script>
<script src="${staticbase }/libs/jqueryui/jquery-ui-1.10.4.custom.min.js?${version}"></script>
<script src="${staticbase }/libs/moment/moment.js?${version}"></script>
<script src="${staticbase }/libs/moment/locale/zh-cn.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/tableExport.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/jquery.base64.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/bootstrap-table.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/bootstrap-table-export.js?${version}"></script>
<script src="${staticbase }/libs/zTree_v3/js/jquery.ztree.all-3.5.min.js?${version}"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<%-- <script src="${staticbase }/libs/jquery/jquery.min.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap/js/bootstrap.js?${version}"></script>
<script src="${staticbase }/libs/jqueryui/jquery-ui-1.10.4.custom.min.js?${version}"></script>
<script src="${staticbase }/libs/detectmobile/detect.js?${version}"></script>
<script src="${staticbase }/libs/moment/moment.js?${version}"></script>
<script src="${staticbase }/libs/moment/locale/zh-cn.js?${version}"></script>
<script src="${staticbase }/libs/jquery-animateNumber/jquery.animateNumber.min.js?${version}"></script>
<script src="${staticbase }/libs/ios7-switch/ios7.switch.js?${version}"></script>
<script src="${staticbase }/libs/fastclick/fastclick.js?${version}"></script>
<script src="${staticbase }/libs/jquery-slimscroll/jquery.slimscroll.min.js?${version}"></script>
<script src="${staticbase }/libs/autonumeric/autoNumeric.js?${version}"></script>
<script src="${staticbase }/libs/bootbox/bootbox.js?${version}"></script>
<script src="${staticbase }/libs/nifty-modal/js/classie.js?${version}"></script>
<script src="${staticbase }/libs/nifty-modal/js/modalEffects.js?${version}"></script>
<script src="${staticbase }/libs/jasny-bootstrap/js/jasny-bootstrap.min.js?${version}"></script>
<script src="${staticbase }/libs/magnific-popup/jquery.magnific-popup.min.js?${version}"></script>
<script src="${staticbase }/libs/pace/pace.min.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-datetimepicker/js/moment-with-locales.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?${version}"></script>
<script src="${staticbase }/libs/jquery-icheck/icheck.min.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-treeview/bootstrap-treeview.min.js?${version}"></script>
<script src="${staticbase }/libs/handlerbars/handlebars-v3.0.0.js?${version}"></script>
<script src="${staticbase }/libs/blockui/jquery.blockUI.js?${version}"></script>
<script src="${staticbase }/libs/jquery-cookie/jquery.cookie.js?${version}"></script>
<!-- Common JS Libraries --> 
<script src="${staticbase }/libs/i18next/i18next.min.js?${version}"></script>
<script src="${staticbase }/libs/notifyjs/notify.js?${version}"></script>
<script src="${staticbase }/libs/notifyjs/styles/metro/notify-metro.js?${version}"></script>
<script src="${staticbase }/libs/prettify/prettify.js?${version}"></script>

<script src="${staticbase }/libs/bootstrap-table/js/tableExport.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/jquery.base64.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/bootstrap-table.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-table/js/bootstrap-table-export.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-validator/js/bootstrapValidator.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-validator/js/language/zh_CN.js?${version}"></script>
<script src="${staticbase }/libs/zTree_v3/js/jquery.ztree.all-3.5.min.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-autocomplete/bootstrap-typeahead.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-autocomplete/bootstrap-autocomplete.js?${version}"></script>
<script src="${staticbase }/libs/bootstrap-multiselect/js/multiselect.min.js?${version}"></script>
<script src="${staticbase }/libs/select2/js/select2.full.js?${version}"></script>
<!-- Menu Data -->
<!-- fileUpload -->
<script src="${staticbase }/libs/jquery-Upload/js/fileinput.min.js?${version}"></script>
<script src="${staticbase }/libs/jquery-Upload/js/fileinput_locale_zh.js?${version}"></script>
<!-- uploadify -->
<script src="${staticbase }/libs/uploadify/js/jquery.uploadify.min.js?${version}"></script>

<script src="${staticbase }/libs/ajaxfileupload/js/ajaxfileupload.js?${version}"></script>

<script src="${base}/scripts/home/menus.js?${version}"></script>
<script src="${base}/scripts/home/main.js?${version}"></script>
<script src="${base}/scripts/home/custom.js?${version}"></script> --%>
