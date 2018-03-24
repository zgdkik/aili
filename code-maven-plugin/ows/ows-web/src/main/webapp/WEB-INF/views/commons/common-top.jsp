<%@page import="com.yimidida.ows.home.share.entity.OwsUser"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script>
</script>
 <div  class="container-fluid"  style="bottom: 0;width: 100%;width: 100%;font-size: 15px;background: #C0C0C0;color:#fff;line-height: 30px;">
        <div class="container">
        	 <a style="font-weight:bold;color:#000000 ;cursor: pointer" onclick="location.href='http://www.ddwl.net.cn'">大道集团 </a>
        	 <a  style="font-weight:bold;color:#000000;margin-left: 30px" >大道物流</a>
        	  <a  style="font-weight:bold;color:#000000;cursor: pointer;margin-left: 30px" onclick="location.href='http://bbs.ddwl.com.cn/login.aspx'">大道论坛</a>
       		 <a style="float: right;font-weight:bold;color:#FD2B39">客服专线:027-83550339</a>	
        </div>
  </div>
	<div class="container">
        <div class="header-top">
            <a id="logoImg" class="left"><img src="${base}/images/ddlogo.png"/></a>
            <!-- <a href="../home/home.jsp" class="left"><img src="../images/anneng.png" alt=""/></a>-->
            <div class="right">
                <ul>
                    <li style="width:108px"><a href="${base}/ddwlGw/introduce?roleCode=zjdd_gsjj&title=公司简介" ><font style="font-size: 14px; font-weight: 500;">公&nbsp;司&nbsp;简&nbsp;介</font></a></li>
                    <li style="width:108px"><a href="${base}/ddwlGw/notice?noticeType=1&title=公司新闻"><font style="font-size: 14px; font-weight: 500;">新&nbsp;闻&nbsp;中&nbsp;心</font></a></li>
                    <li style="width:108px"><a href="${base}/ddwlGw/advanceOrder?title=预约订单"><font style="font-size: 14px; font-weight: 500;">营&nbsp;业&nbsp;厅</font></a></li>
                   <c:if test="${sessionScope.user!=null}">
                    	<li class="land">
	                        <a href="${base}/ddwlGw/personalData?title=个人资料">${sessionScope.user.userName}</a>
	                        <i>|</i>
	                        <a href="#" onclick="logout()">注销</a>
                    	</li>
                    </c:if>
                    <c:if test="${sessionScope.user==null}">
                    	<li class="land">
	                        <a href="${base}/ddwlGw/register?title=登录注册">登录</a>
	                        <i>|</i>
	                        <a href="${base}/ddwlGw/register?title=登录注册">注册</a>
                    	</li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
<!--导航条开始-->
<div class="container-fluid nav1" style="background: #fff;border-top: 3px #ECECEC solid;">
        <div class="container">
        <ul id="ul_hmenu">
        </ul>
    </div>
</div>
<script>

function logout(){
	$.ajax({  
		url:base+"/register/logout",
		type:"post",
		dataType:"json",
		success:function(data){ 
			if(data.success){
				location.reload();
			}  
		}	
	});
}
</script>