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
            </div>
            <div class="container"></div>
           <div class="container">
            	
            </div> 
      		
       		<div class="banner-seach">
                <div class="container">
                </div>
        	</div>
        </div>
    </div>
    <div class="clear" style="height: 0px"></div>
    <div class="home-content container" onmouseover="fn_search_leave('all')">
    	 <div class="home-content">
    	 	<div class="home-content">
	            <div class="clear" style="height: 27px"></div>
	            
	            
	        </div>
    	 </div>
    </div>
     <div class="container-fluid" >
     	<div class="home-content container" >
     	</div>
     </div>
     <div class="home-content container" onmouseover="fn_search_leave('all')">
    	 <div class="home-content">
    	 	<div class="home-content">
	            <div class="clear" style="height: 27px"></div>
	            </div>
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
