<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css.jsp" />
<title>大道物流</title>
<style type="text/css">
	.home-seach-bottom{ background:url(${base}/images/seach-bg4.png) no-repeat bottom; height:70px; padding:15px 20px;}
	.home-seach-bottom2{ background:url(${base}/images/seach-bg4.png) no-repeat bottom; height:170px; padding:15px 20px;}
</style>

</head>
<body style="width: 100%;" >
		<c:import url="../commons/common-top.jsp" />
		<div class="clear"></div>
    <div class="container-fluid">
    <div class="home-banner">
        	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
              <!-- Wrapper for slides -->
              <div class="carousel-inner bannerReplace" role="listbox" id="homeBanner">
              	 <%-- <div class="item active"><img src="${base}/images/banner/banner1.png" alt="..." style="width: 100%;"></div>  --%>
              	<%-- <div class="item "><img src="${base}/images/banner/banner2.jpg" alt="..." style="width: 100%;"></div>
              	<div class="item "><img src="${base}/images/banner/banner3.jpg" alt="..." style="width: 100%;"></div> --%>
              	 <%-- <div class="item "><img src="${base}/images/banner/banner4.png" alt="..." style="width: 100%;"></div>  --%>
              	<%-- <div class="item "><img src="${base}/images/banner/banner5.jpg" alt="..." style="width: 100%;"></div> --%>
              </div>
              <!-- Controls -->
            </div>
            <div class="container"></div>
           <div class="container">
            	<div class="home-seach1" onmouseleave="fn_search_leave(1)">
                    <div class="home-seach-bottom ub"  >
                    	<input class="form-control" id="orderNum" placeholder="请输入运单号或货单号" style="width: 65%;float: left" maxlength="12"/>
                       	<button class="btn buttom" id="btn_search">查询</button>
                    </div>
            	</div>
            	<div class="home-seach2" onmouseleave="fn_search_leave(2)">
                    <div class="home-seach-bottom ub" >
                		<input class="form-control" id="cityName" placeholder="请输入城市名称" style="width: 65%;float: left" maxlength="12"/>
                		<button class="btn buttom" id="dept_btn_search" >查询</button>
                    </div>
            	</div>
            	<div class="home-seach3" onmouseleave="fn_search_leave(3)">
                    <div class="home-seach-bottom2 ub">
                    	<div class="input-group input-group-lg" style="padding: 10px 10px 5px 10px;">
							  <span class="input-group-addon" id="sizing-addon1">起运站：</span>
							  <input name="sourceDistCode" id="sourceDistCode" style="width: 95%" type="text" class="form-control" aria-describedby="basic-addon1" >
							   <input name="sourceDistValue" id="sourceDistValue"  style="display: none">
							</div>
							<div class="input-group input-group-lg" style="padding: 10px 10px 5px 10px;">
							   <span class="input-group-addon" id="sizing-addon1">到达站：</span>
							  <input name="destDistCode" id="destDistCode" type="text" style="width: 95%" class="form-control" aria-describedby="basic-addon1" >
							   <input name="destDistValue" id="destDistValue"  style="display: none">
							</div>
							<div style="text-align: right;padding: 0 10px 10px 0;">
                				<button class="btn" id="feight_btn_search" style="width:80px; line-height:30px; background-color:#C8161D; color:#fff; padding:0; border:0; font-size:14px;margin: 0 ">查询</button>
                			</div>
                    
                    	<!-- <input class="form-control" id="orderNum" placeholder="请输入运单号或货单号" style="width: 60%;float: left" maxlength="12"/>
                       	<button class="btn" id="btn_search" style="width:80px; line-height:30px; background-color:#C8161D; color:#fff; font-size:14px;margin: 2px 0 0 10px">查询</button> -->
                    </div>
            	</div>
            	<div class="home-seach4" onmouseleave="fn_search_leave(4)">
                    <div class="home-seach-bottom ub">
                		<input class="form-control" id="codWaybillNo" placeholder="请输入运单号或货单号" style="width: 65%;float: left" maxlength="12"/>
                		<button class="btn buttom" id="cod_btn_search" >查询</button>
                    </div>
            	</div>
                <div class="right1">
                	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
            </div> 
      		
       		<div class="banner-seach">
                <div class="container">
                    <ul style="background: #2D2D2D">
                        <li onmouseover="fn_search_over(1)" onclick="location.href='${base}/ddwlGw/waybillTrack?title=货物追踪'">
                        	<a> <img src="${base}/images/homezhuizhong.png" style="white-space:nowrap; "><font style="margin-left: 15px">货物追踪</font></a>
                        </li>
                        <li onmouseover="fn_search_over(2)" onclick="location.href='${base}/ddwlGw/deptSearch?title=网点查询'">
                        	<a> <img src="${base}/images/homewangdian.png" style="white-space:nowrap; "><font style="margin-left: 15px">网点查询</font></a>
                        </li>
                         <li onmouseover="fn_search_over(3)" onclick="location.href='${base}/ddwlGw/queryFreight?title=运价查询'">
                         	<a> <img src="${base}/images/homeyunjia.png" style="white-space:nowrap; "><font style="margin-left: 15px">运价查询</font></a>
                        </li>
                        <li  onmouseover="fn_search_over(4)" onclick="location.href='${base}/ddwlGw/queryCOD?title=代收款查询'">
                        	<a> <img src="${base}/images/homehuokuan.png" style="white-space:nowrap; "><font style="margin-left: 15px">货款查询</font></a>
                        </li>
                    </ul>
                   <%--  <div class="contact-us">
                    	<img src="${base}/images/phone.png"/>
                        <p style="float:right">全国统一服务热线</p>
                        <h3 style="float:right">027-83550339</h3>
                    </div> --%>
                </div>
        	</div>
        </div>
    </div>
    <div class="clear" style="height: 0px"></div>
    <div class="home-content container" onmouseover="fn_search_leave('all')">
    	 <div class="home-content">
    	 	<div class="home-content">
	            <div class="clear" style="height: 27px"></div>
	            <div class="item">
	            	<div class="title ub" id="title_ZYCP">
	            		<div class="left">
	            			<p>PRODUCT</p>
	            			<h3 style="color: #000;">核心产品</h3>
	            		</div>
	            		 <div class="right">
	            		 	<div class="clear" style="height: 10px"></div>
	            		 	<a href="${base}/ddwlGw/productService?roleCode=ddwl_cpfw_gxys&title=干线运输">查看更多
	            		 		<span class="glyphicon glyphicon-menu-right active"></span></a></div>
	                </div>
	                <div class="clear" style="height: 10px"></div>
	                <ul class="ub home-product" id="ul_ZYCP">
	                	<%--  <li onclick="location.href='${base}/ddwlGw/productService?roleCode=ddwl_cpfw_gxys&title=干线运输'">
	                		<div class="thumbnail" style="padding: 50px 0 80px 0 ;">
					            <img src="${base}/images/productganxianyunshu.png" >
					            <div class="caption home-product-font">
					                <h4 class="home-product-title">干线运输</h4>
					                <p  class="home-product-font-5">定时定班，发放全国</p>
					            </div>
					        </div>
	                	</li>	 --%>
	                	<%--<li  onclick="location.href='${base}/ddwlGw/productService?roleCode=ddwl_cpfw_dshk&title=代收货款'">
	                		<div class="thumbnail" style="padding: 50px 0 80px 0">
					            <img src="${base}/images/productdaishouhuokuan.png" >
					            <div class="caption home-product-font">
					                <h4 class="home-product-title">代收货款</h4>
					                 <p class="home-product-font-5">最安全、最快捷的代收业务</p>
					            </div>
					        </div>
	                	</li>	
	                	<li onclick="location.href='${base}/ddwlGw/productService?roleCode=ddwl_cpfw_bjys&title=保价运输'">
	                		<div class="thumbnail" style="padding: 50px 0 80px 0">
					            <img src="${base}/images/productbaojiayunshu.png" >
					            <div class="caption home-product-font">
					                <h4 class="home-product-title">保价运输</h4>
					                <p class="home-product-font-5">定时定班，发放全国</p>
					            </div>
					        </div>
	                	</li>	
	                	<li onclick="location.href='${base}/ddwlGw/productService?roleCode=ddwl_cpfw_psfw&title=配送服务'">
	                		<div class="thumbnail" style="padding: 50px 0 80px 0">
					            <img src="${base}/images/productpeisongfuwu.png" >
					            <div class="caption home-product-font">
					                <h4 class="home-product-title">配送服务</h4>
					                <p class="home-product-font-5">送货上门，服务贴心</p>
					            </div>
					        </div>
	                	</li>	 --%>
	                </ul>
	            </div>
	            
	            
	        </div>
    	 </div>
    </div>
     <div class="container-fluid imgbg" >
     	<div class="home-content container" >
     		<div class="detail">
     			<p>2015年公司进入了新一轮高速发展时期，我们不断推陈出新，积极变革，</p>
     			<p>我们将始终秉承“优化物流服务、提升产品价值、愉悦每位客户”的使</p>
     			<p>命，向着“中部第一、全国十强”的目标全速前进！</p>
     			<button  onclick="location.href='${base}/ddwlGw/advanceOrder?title=预约订单'" class="btn" style="">网上下单</button>
     		</div>
     	</div>
     </div>
     <div class="home-content container" onmouseover="fn_search_leave('all')">
    	 <div class="home-content">
    	 	<div class="home-content">
	            <div class="clear" style="height: 27px"></div>
	            <div class="item" style="margin-bottom: 0">
	            	<div class="title ub" id="title_ZYCP">
	            		<div class="left">
	            			<p>NEWS</p>
	            			<h3 style="color: #000;">最新资讯</h3>
	            		</div>
	            		 <div class="right">
	            		 	<div class="clear" style="height: 10px"></div>
	            		 	<a href="${base}/ddwlGw/notice?noticeType=1&title=公司新闻">查看更多
	            		 		<span class="glyphicon glyphicon-menu-right active"></span></a></div>
	                </div>
	            </div>
	            <ul class="ub home-news" id="ul_XWZX">
                	 <%-- <li style="width:32% ;">
                		<div class="thumbnail" style="overflow: auto;padding: 0 ">
				            <img src="${base}/images/news.jpg"  style="margin:0">
				            <div class="caption home-news-font" >
				                <div class="home-news-title">
				                	<strong>从起点到转折点</strong>
				                </div>
				                <p>
									想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶想干啥你下单噶
								</p>
								<div style="text-align: right;">
									<a style="margin-right:0;color:#959595;margin-top: 5px">2016-11-14</a>
								</div>
				            </div>
				        </div>
                	</li>  --%>	
                	
                </ul>
                 <div class="clear" style="height: 50px"></div>
	            
	            
	        </div>
    	 </div>
    </div>
	<c:import url="../commons/common-footer.jsp" />
	<c:import url="../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/resources/js/bootstrap-typeahead.js?${version}"></script>
	<script type="text/javascript" src="${base}/scripts/home.js"></script>
<%-- 	<script type="text/javascript" src="${base}/scripts/common.js"></script> --%>
	
	<script type="text/javascript">
          var html1=loadHomeBanner(1);
          
          $("img").each(function(){
      		var str=$(this).attr("src");
      		if(!(str.indexOf(base)>-1)){
      			$(this).attr("src",base+str);
      		}
      	});
    </script>
</body>
</html>
