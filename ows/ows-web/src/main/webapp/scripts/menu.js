//function fn_get_menu(menuId){
//	  $.ajax({
//		  type: "GET",
//		  url: "../menu/queryViewMenuNew",
//		  data: {menuId:'0'},
//		  dataType: "json",
//		  success: function(data){
//		 	  var hstr = '';
//		 	  var fstr = '';
//		 	  $.each(data, function(commentIndex, comment){
//		 		  if(comment['menuName']!='首页'){
//		      	       fstr+='<li><h4>'+comment['menuName']+'</h4>';
//	      	      }
//		 		  hstr+='<li><a menuId="'+comment['menuId']+'" '+((menuId==comment['menuId'])?'class="active"':'')+' href="'+comment['menuViewurl']+'">'+comment['menuName']+'</a>';
//		 		  if(comment['children']){
//		 			  
//			 		  hstr+='<ul class="ul1">';
//			 		  $(comment['children']).each(function(i,cmm){
//			 			 fstr+='<a href="'+cmm['menuViewurl']+'">'+cmm['menuName']+'</a>';
//			 			  hstr+='<li><a href="'+cmm['menuViewurl']+'">'+cmm['menuName']+'</a>';
//			 			  if(cmm['children']){
//			 				    hstr+='<ul>';
//		      	    			$(cmm['children']).each(function(i,cm){
//		      	    				hstr+='<li><a href="'+cm['menuViewurl']+'">>'+cm['menuName']+'</a></li>';
//		      	    			})
//		      	    			hstr+='</ul>';
//			 			  }		
//			 		  })
//			 		  hstr+='</ul>';
//		 		  }
//		 		  fstr+='</li>';
//		 		  hstr+='</li>';
//		 	  })
//		 	  
//		 	  $('#ul_hmenu').html(hstr);
//		 	  $('#ul_fmenu').html(fstr);
//		 	  fn_set_mousestyle(menuId);
//		  }
//	 });
//	 
//}
//function fn_set_mousestyle(menuId){
//	    
//		$('.ul1').mouseover({menuId:menuId},function(event){
//			var pmenuId = event.data.menuId;
//			var menuId = $(this).parent().find('a').attr('menuId');
//			if(pmenuId!=menuId){
//				$(this).prev().addClass('active');
//			}
//		})
//		$('.ul1').mouseout({menuId:menuId},function(event){
//			var pmenuId = event.data.menuId;
//			var menuId = $(this).parent().find('a').attr('menuId');
//			if(pmenuId!=menuId){
//				$(this).prev().removeClass('active');
//			}
//		})
//}

function fn_get_menu(menuId){
	  $.ajax({
		  type: "GET",
		  url: base+"/menu/getMenu",
		  data:{compCode:'ddwl'},
		  dataType: "json",
		  success: function(data){
		 	  var bigMenuList=data.data.list;
		 	  //top菜单
		 	  var menuStr='';
		 	  //左边导航菜单
		 	  var leftMenuStr='';
		 	  var footMenustr='';
		 	  $("#logoImg").attr('href',base+"/");
		 	  for(var i=0;i<bigMenuList.length;i++){
		 		  if(menuId==bigMenuList[i].privilegeCode){
		 			 menuStr+="<li><a href=\""+base+bigMenuList[i].gwUrl+"\" class='active'>"+bigMenuList[i].functionName+"</a>";
		 			 $("#leftMenuParent").text(bigMenuList[i].functionName);
		 			 //leftMenuStr+="<li id=\"li_pmenu\"><a href=\""+base+bigMenuList[i].gwUrl+"\"><strong style=\"color:#B22923\">"+bigMenuList[i].functionName+"</strong></li>";
		 		  }else{
		 			 menuStr+="<li><a href=\""+base+bigMenuList[i].gwUrl+"\">"+bigMenuList[i].functionName+"</a>";
		 		  }
		 		 if(bigMenuList[i].functionName!='首页'){
		 			 footMenustr+="<li><h4>"+bigMenuList[i].functionName+"</h4>";
		 		 }
		 		  var menuList=bigMenuList[i].menuList;
		 		  if(menuList.length>0){
		 			 menuStr+="<ul class=\"ul1\">";
		 			 for(var j=0;j<menuList.length;j++){
			 			menuStr+="<li><a href=\""+base+menuList[j].gwUrl+"\">"+menuList[j].functionName+"</a></li>";
			 			footMenustr+="<a href=\""+base+menuList[j].gwUrl+"\">"+menuList[j].functionName+"</a>"
			 			if(menuId==bigMenuList[i].privilegeCode){
			 				if(title==menuList[j].functionName){
			 					leftMenuStr+="<li><a  href=\""+base+menuList[j].gwUrl+"\" style=\"color: #323232;\"><strong >"+menuList[j].functionName+"</strong></a></li>";
			 				}else{
			 					leftMenuStr+="<li><a  href=\""+base+menuList[j].gwUrl+"\"><strong >"+menuList[j].functionName+"</strong></a></li>";
			 				}
			 			 }
		 			 } 
		 			menuStr+="</ul>";
		 		  }
		 		menuStr+= "</li>";
		 		footMenustr+= "</li>";
		 	  }
		 	  $("#ul_hmenu").html(menuStr);
		 	  $("#ul_lfmenu").html(leftMenuStr);
		 	  $("#ul_fmenu").html(footMenustr);

		 	var iconNavStr= "<li>"+
		 	"<div class=\"img\"><img src=\""+base+"/images/ejymicon-02.png\" alt=\"\" style=\"cursor: pointer;\" onclick=\"location.href='"+base+"/ddwlGw/waybillTrack?title=货物追踪\'\"/></div>"+
				"<a href=\""+base+"/ddwlGw/waybillTrack?title=货物追踪\">货物追踪</a>"+
			"</li>"+
			"<li>"+
				"<div class=\"img\"><img src=\""+base+"/images/ejymicon-03.png\" alt=\"\" style=\"cursor: pointer;\" onclick=\"location.href='"+base+"/ddwlGw/queryFreight?title=运价查询'\"/></div>"+
				"<a href=\"\">运价查询</a>"+
			"</li>"+
			"<li>"+
				"<div class=\"img\"><img src=\""+base+"/images/ejymicon-04.png\" alt=\"\" style=\"cursor: pointer;\" onclick=\"location.href='"+base+"/ddwlGw/deptSearch?title=网点查询'\"/></div>"+
				"<a href=\""+base+"/ddwlGw/deptSearch?title=网点查询\">网点查询</a>"+
			"</li>"+
			"<li>"+
				"<div class=\"img\"><img src=\""+base+"/images/ejymicon-05.png\" alt=\"\" style=\"cursor: pointer;\" onclick=\"location.href=''\"/></div>"+
				"<a href=\"\">代款查询</a>"+
			"</li>";
			$("#iconNav").html(iconNavStr);
		  }
	 });
}

