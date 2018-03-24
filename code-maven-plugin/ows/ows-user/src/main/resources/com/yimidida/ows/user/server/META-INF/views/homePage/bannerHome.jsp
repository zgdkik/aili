<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="staticbase" scope="request"
	value="${base}/resources/statics"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="../commons/common-meta.jsp" />
<c:import url="../commons/common-css.jsp" />
<title>壹米滴答</title>
</head>
<body style="background: #C7DDEF;width: 100%;" >
	<c:import url="../commons/common-top.jsp" />
	
	<!--焦点图滚动-->
    <div class="clear"></div>
    <div class="container-fluid">
    <div class="home-banner">
        	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
              <!-- Wrapper for slides -->
              <div class="carousel-inner" role="listbox">
              	<div class="item active"><img src="${base}/images/banner/banner1.png" alt="..." style="width: 100%;"></div>
              	<div class="item "><img src="${base}/images/banner/banner2.jpg" alt="..." style="width: 100%;"></div>
              	<div class="item "><img src="${base}/images/banner/banner3.jpg" alt="..." style="width: 100%;"></div>
              	<div class="item "><img src="${base}/images/banner/banner4.jpg" alt="..." style="width: 100%;"></div>
              	<div class="item "><img src="${base}/images/banner/banner5.jpg" alt="..." style="width: 100%;"></div>
              </div>
              <!-- Controls -->
            </div>
            <div class="container">
            	<div class="home-seach" onmouseleave="fn_search_leave()">
            	 	<div class="title ub">货物追踪</div>
                    <div class="home-seach-bottom ub">
                    	<!-- 
                    	<input type="text" placeholder="在此签入快递单进行查询" class="text" />
                    	-->
                    	<textarea placeholder="在此输入运单号进行查询" class="text" id="orderNum"></textarea>
                        <span class="glyphicon glyphicon-search" style="top:70px;cursor: pointer;" id="btn_search"></span>
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
      		<div class="bg"></div>
       		<div class="banner-seach">
                <div class="container">
                    <ul>
                        <li onmouseover="fn_search_over()" onclick="location.href='../online/waybilltrack.jsp'">
                            <span class="glyphicon glyphicon-search"></span>
                            <a>货物追踪</a>
                        </li>
                        <li onclick="location.href='../online/times.jsp'">
                            <span class="glyphicon glyphicon-time"></span>
                            <a>网上下单</a>
                        </li>
                    </ul>
                    <div class="contact-us">
                    	<img src="${base}/images/phone.png"/>
                        <p style="float:right">全国统一服务热线</p>
                        <h3 style="float:right">027-83550339</h3>
                    </div>
                </div>
        	</div>
        </div>
    </div>
    <!--主页内容-->
    <div class="clear"></div>
    <div class="home-content container" onmouseover="fn_search_leave()">
    	 <div class="home-content-left">
            <div class="item">
            	<div class="title ub" id="title_ZYCP">
            		<div class="left"><strong></strong></div>
            		 
                </div>
                <ul class="ub home-product" id="ul_ZYCP">
                	<li style="width:100px ;   margin-right: 120px;" >
                		<a>
                			<img src="img/dshk.png" style="width: 100px;" class="col-md-4"/>
                		</a>
                		<p>运价查询</p>
                	</li>
                	<li style="width:100px ;   margin-right: 120px;">
                		<a>
                			<img src="img/dshk.png" style="width: 100px;" class="col-md-4"/>
                		</a>
                		<p>运价查询</p>
                	</li>
                	<li style="width:100px ;   margin-right: 120px;">
                		<a>
                			<img src="img/dshk.png" style="width: 100px;" class="col-md-4"/>
                		</a>
                		<p>运价查询</p>
                	</li>
                	
                	
                </ul>
            </div>
            <div class="item">
            	<div class="title ub" id="title_ZYCP">
            		<div class="left"><strong>核心产品</strong></div>
            		 <div class="right">
            		 	<a href="../news/news.jsp?type=2&pmenu=cpyfw&menu=schd">查看更多
            		 		<span class="glyphicon glyphicon-menu-right active"></span></a></div>
                </div>
                <ul class="ub home-product" id="ul_ZYCP">
                	<li style="width:100px ;   margin-right: 40px;">
                		<a>
                			<img src="img/zycp.png" />
                		</a>
                		<p>代收货款</p>
                	</li>
                	<li style="width:100px ;   margin-right: 40px;">
                		<a>
                			<img src="img/zycp.png" />
                		</a>
                		<p>干线运输</p>
                	</li>
                	<li style="width:100px ;   margin-right: 40px;">
                		<a>
                			<img src="img/zycp.png" />
                		</a>
                		<p>报价运输</p>
                	</li>
                	<li style="width:100px ;   margin-right: 40px;">
                		<a>
                			<img src="img/zycp.png" />
                		</a>
                		<p>配送服务</p>
                	</li>
                	
                </ul>
            </div>
            
            
        </div>
        <div class="home-content-right">
        	<div class="home-zixun ub" style="margin-top: 0px;">
            	<div class="title">
                	<span class="left">最新资讯</span><span class="right"><a href="../news/news.jsp?type=1&pmenu=gyymdd&menu=qyxw">更多</a></span>
                </div>
<!--
                <img id="firstZx" src="../images/home-zixun.png" alt="" style="cursor: pointer;"/>
-->
                <ul class="ub" id="zxNotice"> 
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                	<li style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
                		<a>【安全生产】壹米滴答·恒邦物流开展安全生产工作会议</a>
                		<br>
                		<a style="float: right;">2016-09-05</a>
                	</li>
                </ul>
            </div>
        </div>
    </div>
	<c:import url="../commons/common-footer.jsp" />
	<c:import url="../commons/common-script.jsp" />
	<script type="text/javascript" src="${base}/scripts/home.js"></script>
	<script type="text/javascript" src="${base}/scripts/menu.js"></script>
</body>
</html>
