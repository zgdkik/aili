function $(id){
	return document.getElementById(id);
}
/** *********************�����ע��ģ��******************************* */
function showAttentionInit(){
	var pageIndex = $("pageIndex").value;
	var url="showAttentionAction.do";
	var message = pageIndex+";";
	//Ajax.request(url, "GET", message, "TEXT", attentionCallback);
}
function attentionCallback(text){
	  if(checkLogin(text)){
	       window.location="/blog/jsp/login.jsp"; return;
	  }
	 // ��JSON��ʽ����(�ַ�)ת����JS����
	 var obj=JSON.parse(text);
	 var userName = obj["userName"];
	 $("userName").innerHTML = userName;
	 pageCount = obj["pageCount"];
	 if(pageCount == 0){
		 $("ct_center_main_content").innerHTML = obj["exception"];
		 return;
	 }
	 var pageIndex = $("pageIndex").value;
	 userList = obj["userList"];
	 if(pageCount!=0)
	   showAttention(userList,pageIndex);
}
/**
 * ��ʾ��ע���б?��ҳ����
 * 
 * @param arr
 *            ��ע���б�
 * @param pageIndex
 *            ҳ��
 * @return
 */
function showAttention(arr,pageIndex){// ��ʾ��ע���б�
	 var size = arr.length;
	 $("font1").innerHTML="ȫ����ע("+pageCount+")";
	 var t = 0;
	 var left = 0;
	 var top = 5;
	 var rows = 0;// ��
	 var col = 0;// ��
	 var divStr = "";
	 if(size%3==0){
		 rows = size/3;
	 }else{
		 rows = size/3+1;
	 }
	 for(var i=1;i<=rows;i++){
		 if(size<3){
			 col = size;
		 }else{
			 col=3;
			 size= size-3;
		 }
		 var j = 0;
		 for(j=0;j<col;j++){
			 var user=arr[t];
			 var trStr = new Array();
			 trStr[j]="<div id=\"userData:"+t+"\" style=\"position: absolute;top:"+top+"px;left:"+left+"px;width:250px;height:100px;border: 2px solid #22ddca; \" onmouseover=\"borderChange(this);\" onmouseout=\"borderRecover(this);\">" +
			 	   "<div style=\"position: absolute;top:0;left:220px;height: 30px;width: 30px;border: 0;\" ><img style=\"display: none;\" id=\""+t+"\" src=\"images/image/select.gif\"  /></div>" +
		  		   "<div style=\"position: absolute;top:5px;left:5px;width:55px;height:55px;border: 0;font-size: 12px;\"  onclick=\"forwardPersonHome("+user.userId+")\"><img style=\"width: 55px;heigth: 55px;\"   src="+user.userHead+"></div>" +
		  		   "<div id=\"name"+t+"\" style=\"position: absolute;top:5px;left:70px;width:160px;height:15px;border: 0;font-size: 12px;\">"+user.userName+"</div>" +
		  		   "<div id=\"city"+t+"\" style=\"position: absolute;top:25px;left:70px;width:160px;height:15px;border: 0;font-size: 12px;\">����:"+user.userCity+"</div>" +
		  		   "<div style=\"position: absolute;top:45px;left:70px;width:160px;height:15px;border: 0;font-size: 12px;\"><a href=\"javascript:void(0)\" onclick=\"deleteAttention('"+user.userName+"');\">ȡ���ע</a></div>" +
		  		   "<div style=\"position: absolute;top:70px;left:5px;width:230px;height:25px;border: 0;font-size: 13px;color: #aa7744\">���:"+user.userIntroduce+"</div>" +
	  		       "</div>"; 
			 divStr = divStr+trStr[j];
			 left =left+260;
			 t++;
		 }
		 if(j>=3){
			 top=top+110;
			 left=0;
		 }
		 divStr+="<div ><a id=\"previous\" style=\"position: absolute;top: 440px;left: 500px;display: none;cursor: pointer;\" onclick=\"getPrevious();\">��һҳ</a>" +
			"<a id=\"next\"  style=\"position: absolute;top: 440px;left: 580px;display: none;\" onclick=\"getNext();custor:pointer;\">��һҳ</a></div> ";
	     $("ct_center_main_content").innerHTML=divStr;
	 }
	 $("next").style.display = "none";
	 $("previous").style.display = "none";
	 var count = pageCount%12==0?pageCount/12:parseInt(pageCount/12)+1;// �ܹ��ּ�ҳ
	 if(pageCount>12 &&  pageIndex<count){
		 $("next").style.display = "block";
	 }
	 if(pageIndex!=1 && pageIndex>0){
		 $("previous").style.display = "block";
	 }
		 
}

function  forwardPersonHome(blogUserId){
    var url="/blog/personVisitUserIdAction.do";
 //  Ajax.request(url, "GET", "userId="+blogUserId, "TEXT", updateLocation);
}
function updateLocation(){
window.location="http://localhost:8080/blog/personhomepage.html";
}

/**
 * ��һҳ����
 * 
 * @return
 */
function getNext(){
	var pageIndex = parseInt($("pageIndex").value)+1;
	var userName = $("userName").innerHTML;
	$("pageIndex").value = pageIndex;
	var url="showAttentionAction.do";
	var message = pageIndex+";"+userName;
	Ajax.request(url, "POST", message, "TEXT", attentionCallback);
}
/**
 * ��һҳ����
 * 
 * @return
 */
function getPrevious(){
	var pageIndex = parseInt($("pageIndex").value)-1;
	var userName = $("userName").innerHTML;
	$("pageIndex").value = pageIndex;
	var url="showAttentionAction.do";
	var message = pageIndex+";"+userName;
	Ajax.request(url, "pOST", message, "TEXT", attentionCallback);
}
/**
 * ������룬�ı�߿���ɫ
 * 
 * @param obj ��ǩ
 *           
 */
function borderChange(obj){
	obj.style.borderColor = "orange";
	var objStr = obj.id.split(":");
	var imgid = objStr[1];
	var nameid = "name"+imgid;
	var cityid = "city"+imgid;
	$(imgid).style.display = "block";
	$(cityid).style.color = "red";
	$(nameid).style.color = "#aaa";
}
/**
 * ����Ƴ�ָ��߿�ԭ����ɫ
 * 
 * @param obj
 */
function borderRecover(obj){
	obj.style.borderColor = "#22ddca";
	var objStr = obj.id.split(":");
	var imgid = objStr[1];
	var nameid = "name"+imgid;
	var cityid = "city"+imgid;
	$(imgid).style.display = "none";
	$(cityid).style.color = "black";
	$(nameid).style.color = "black";
}

/**
 * �����ƣ�ȡ��Ըù�ע�˵Ĺ�ע
 * 
 * @param attentionUserName
 *            ��ע�����
 * @return
 */
function deleteAttention(attentionUserName){
	var url = "deleteAttentionAction.do";
	var userName = $("userName").innerHTML; 
	var message = userName+";"+attentionUserName;
	Ajax.request(url, "POST", message, "TEXT", deleteAttentionCallback);
}
function deleteAttentionCallback(text){// ɾ���ע��
	 if(checkLogin(text)){
	      window.location="/blog/jsp/login.jsp"; return;
	 }
	 $("ct_center_main_content").innerHTML=text;
	 alert($("success").innerHTML);
	 setTimeout(showAttentionInit, 100);
	 
}
/** *******************�����˿ģ��************************************* */
function showFansInit(){
	var pageIndex = $("pageIndex").value;
	var userName = $("userName").innerHTML;
	var url="showFansAction.do";
	var message = pageIndex+";"+userName;
	Ajax.request(url, "POST", message, "TEXT", fansCallback);
}
function fansCallback(text){
	 if(checkLogin(text)){
	      window.location="/blog/jsp/login.jsp"; return;
	 }
	 // ��JSON��ʽ����(�ַ�)ת����JS����
	 var obj=JSON.parse(text);
	 pageCount = obj["pageCount"];
	 if(pageCount == 0){
		 $("ct_center_main_content").innerHTML = obj["exception"];
		 return;
	 }
	 var pageIndex = $("pageIndex").value;
	 fansList = obj["fansList"];
	 var attentionCount = obj["attentionCount"];
	 var fansCount = obj["fansCount"];
	 var attFan = obj["attfan"];
	 showFans(fansList,pageIndex,attentionCount,fansCount,attFan);
}
function showFans(arr,pageIndex,attentionArr,fansArr,attFanArr){// ��ʾ��˿�б�
	 var size = arr.length;// ��˿�б�ĳ���
	 $("font1").innerHTML="����"+pageCount+"�˹�ע��";
	 var t = 0;
	 var pageBegin = (pageIndex-1)*4;
	 var top = 5;
	 var col = 0;// ��
	 var divStr = "";
	 var j = 0;
     for(j=0;j<size;j++){
		 var user=arr[t];
		 var attentionCount = attentionArr[pageBegin];
		 var fansCount = fansArr[pageBegin];
		 var attFan = attFanArr[j];
		 var attFanResult = attFan.split(";");
		 var trStr = new Array();
		  trStr[j]="<div id=\"fansData\" style=\"position: absolute;top:"+top+"px;left:0px;width:775px;height:100px;border: 1px solid #eee\">"+
				    "<div style=\"position: absolute;top:5px;left:5px;width:55px;height:55px;font-size: 12px;border: 0;\" onclick=\"forwardPersonHome("+user.userId+")\"><img style=\"width: 55px;height: 55px;\" src="+user.userHead+"></div>"+
				    "<div style=\"position: absolute;top:5px;left:70px;width:455px;height:15px;font-size: 12px;border: 0;\">"+user.userName+"</div>"+
				    "<div style=\"position: absolute;top:25px;left:70px;width:455px;height:15px;font-size: 12px;border: 0;\">ʡ��:"+user.userProvince+"  "+user.userCity+"</div>"+
				    "<div style=\"position: absolute;top:45px;left:70px;width:455px;height:15px;font-size: 12px;border: 0;\"><font color=\"red\">��ע"+attentionCount+"��    ��˿"+fansCount+"��</font></div>"+
				    "<div style=\"position: absolute;top:70px;left:70px;width:455px;height:25px;font-size: 13px;border: 0;\">���:"+user.userIntroduce+"</div>"+
				    "<div style=\"position: absolute;top:40px;left:550px;width:75px;height:20px;font-size: 13px;border: 0;\"><font style=\"pading-top: 10px;margin-left: 13px;color: orange;cursor: pointer;\">"+attFanResult[0]+"</font></div>"; 
		 divStr = divStr+trStr[j];
		 if(attFanResult[0] == "�����ע"){
			 divStr+="<div style=\"position: absolute;top:40px;left:660px;width:75px;height:20px;font-size: 13px;border: 0;\"><a href=\"javaScript:void(0)\" style=\"margin: 10px 10px;\" onclick=\"deleteFans('"+user.userName+"');\">"+attFanResult[1]+"</a></div></div>";
		 }
		 if(attFanResult[0] == "δ��ע"){
			 divStr+="<div style=\"position: absolute;top:40px;left:660px;width:75px;height:20px;font-size: 13px;border: 0;\"><a href=\"javaScript:void(0)\" style=\"margin: 10px 10px;\" onclick=\"addFanToAttention('"+user.userName+"');\">"+attFanResult[1]+"</a></div></div>";
		 }
		 top =top+110;
		 t++;
		 pageBegin++;
     }
     divStr+="<div ><a id=\"fansPrevious\" style=\"position: absolute;top: "+top+"px;left: 500px;display: none;cursor: pointer;\" onclick=\"getFansPrevious();\">��һҳ</a>" +
     		"<a id=\"fansNext\" style=\"position: absolute;top: "+top+"px;left: 580px;display: none;cursor: pointer;\" onclick=\"getFansNext();\">��һҳ</a></div>";
     $("ct_center_main_content").innerHTML=divStr;
     $("fansNext").style.display = "none";
 	 $("fansPrevious").style.display = "none";
 	 var count = pageCount%4==0?pageCount/4:parseInt(pageCount/4)+1;// �ܹ��ּ�ҳ
 	 if(pageCount>4 &&  pageIndex<count){
 		 $("fansNext").style.display = "block";
 	 }
 	 if(pageIndex!=1 && pageIndex>0){
 		 $("fansPrevious").style.display = "block";
 	 }
}
function getFansNext(){
	var pageIndex = parseInt($("pageIndex").value)+1;
	var userName = $("userName").innerHTML;
	$("pageIndex").value = pageIndex;
	var url="showFansAction.do";
	var message = pageIndex+";"+userName;
	Ajax.request(url, "POST", message, "TEXT", fansCallback);
}
function getFansPrevious(){
	var pageIndex = parseInt($("pageIndex").value)-1;
	var userName = $("userName").innerHTML;
	$("pageIndex").value = pageIndex;
	var url="showFansAction.do";
	var message = pageIndex+";"+userName;
	Ajax.request(url, "POST", message, "TEXT", fansCallback);
}
function deleteFans(attentionUserName){
	var url = "deleteFansAction.do";
	var userName = $("userName").innerHTML; 
	var message = userName+";"+attentionUserName;
	Ajax.request(url, "POST", message, "TEXT", deleteFansCallback);
}
function deleteFansCallback(text){// ɾ���˿
	 if(checkLogin(text)){
	    window.location="/blog/jsp/login.jsp"; return;
	 }
	 $("ct_center_main_content").innerHTML=text;
	 alert($("success").innerHTML);
	 setTimeout(showFansInit, 100);
	 
}
function addFanToAttention(fanName){// ���ӷ�˿Ϊ��ע��
	var url = "addAttentionAction.do";
	var userName = $("userName").innerHTML; 
	var message = userName+";"+fanName;
	Ajax.request(url, "POST", message, "TEXT", addFanToAttentionCallback);
}
function addFanToAttentionCallback(text){// ���ӹ�ע��
	 if(checkLogin(text)){
	    window.location="/blog/jsp/login.jsp"; return;
	 }
	 $("ct_center_main_content").innerHTML=text;
	 alert($("success").innerHTML);
	 setTimeout(showFansInit, 100);
}
/** *******************����******************************* */
//�ڹ������м��ص�½�����session�е�user 
function nameLoad(){
	var url = "searchUserAction.do";
	var message = "null;";
	Ajax.request(url, "POST", message, "TEXT", nameCallback);
}
function nameCallback(text){
	if(checkLogin(text)){
	   window.location="/blog/jsp/login.jsp"; return;
	}
	 var obj = JSON.parse(text);
	 var userName = obj["userName"];
	 var school = obj["school"];
	 var company = obj["company"];
	 $("userName").innerHTML = userName;
	 $("cdschool").innerHTML = school;
	 $("cdcompany").innerHTML = company;
}


function findUser(obj){
	var pageIndex = $("Page").value;
    type = obj.id;
    if(type =="inputName"){
    	condition = $("Name").value;
    }else if(type == "inputSchool"){
    	condition = $("schoolName").value;
    }else{
    	condition = $("companyName").value;
    }
	var url = "searchUserAction.do";
	var message = condition+";"+type+";"+pageIndex+";";
	Ajax.request(url, "POST", message, "TEXT", searchUserCallback);
}
function searchUserCallback(text){
	     if(checkLogin(text)){
	        window.location="/blog/jsp/login.jsp"; return;
	     }
		 // ��JSON��ʽ����(�ַ�)ת����JS����
		 var obj=JSON.parse(text);
		 pageCount = obj["pageCount"];
		 if(pageCount == 0){
			 $("result").innerHTML = obj["exception"];
			 return;
		 }
		 var pageIndex = $("Page").value;
		 var userList = obj["list"];
		 var attentionCount = obj["attentionCount"];
		 var fansCount = obj["fansCount"];
		 var attAndFan = obj["attFan"];
		 showUser(userList,pageIndex,attentionCount,fansCount,attAndFan);
	}
	function showUser(arr,pageIndex,attentionCountStr,fansCountStr,attAndFanStr){// ��ʾ�û��б�
		 var size = arr.length;// �û��б�ĳ���
		 $("resultCount").innerHTML="����������˹���"+pageCount+"����";
		 var t = 0;
		 var pageBegin = (pageIndex-1)*4;
		 var top = 0;
		 var col = 0;// ��
		 var divStr = "";
		 var j = 0;
	     for(j=0;j<size;j++){
			 var user=arr[t];
			 var trStr = new Array();
			 var attentionCount = attentionCountStr[pageBegin];
			 var fansCount = fansCountStr[pageBegin];
			 var attAndFan = attAndFanStr[j];
			 var attAndFanResult = attAndFan.split(";");
			  trStr[j]="<div id=\"userData\" style=\"position: absolute;top:"+top+"px;left:0px;width:700px;height:190px;border: 1px solid #9988fe;\">"+
					    "<div style=\"position: absolute;top:15px;left:0px;width:150px;height:150px;font-size: 12px;border: 0;\"><img style=\"width: 150px;height: 150px;\" src="+user.userHead+"></div>"+
					    "<div style=\"position: absolute;top:15px;left:165px;width:390px;height:20px;font-size: 12px;border: 0;color: #447799\">"+user.userName+"</div>"+
					    "<div style=\"position: absolute;top:50px;left:165px;width:390px;height:20px;font-size: 12px;border: 0;\">ʡ��:"+user.userProvince+"  "+user.userCity+"</div>"+
					    "<div style=\"position: absolute;top:85px;left:165px;width:390px;height:20px;font-size: 12px;border: 0;\"><font color=\"red\">��ע"+attentionCount+"��    ��˿"+fansCount+"��</font></div>"+
					    "<div style=\"position: absolute;top:115px;left:165px;width:390px;height:25px;font-size: 13px;border: 0;color: #aaa;\">���:"+user.userIntroduce+"</div>"+
					    "<div style=\"position: absolute;top:40px;left:550px;width:75px;height:20px;font-size: 13px;border: 0;\"><font style=\"pading-top: 10px;margin-left: 13px;color: blue;cursor: pointer;\">"+attAndFanResult[0]+"</font></div>"; 
			 divStr = divStr+trStr[j];
			 if(attAndFanResult[0] == "�ѹ�ע"){
				 divStr+="<div style=\"position: absolute;top:25px;left:470px;width:65px;height:20px;font-size: 13px;border: 0;background-color: orange;color: red;\"><a href=\"javaScript:void(0)\" style=\"margin: 10px 10px;\" onclick=\"deleteUserAttention('"+user.userName+"');\">"+attAndFanResult[1]+"</a></div></div>"; 
			 }
			 if(attAndFanResult[0] == "δ��ע"){
				 divStr+="<div style=\"position: absolute;top:25px;left:470px;width:65px;height:20px;font-size: 13px;border: 0;background-color: orange;color: red;\"><a id=\"add\" href=\"javaScript:void(0)\" style=\"margin: 10px 10px;\" onclick=\"addUserAttention('"+user.userName+"');\">"+attAndFanResult[1]+"</a></div></div>"; 
			 }
			 top =top+200;
			 t++;
			 pageBegin++;
	     }
	     divStr+="<div><a id=\"userPrevious\" style=\"position:absolute;top:"+top+"px;left: 500px;width: 50px;display: none;width: 50px;cursor: pointer;\" onclick=\"getUserPrevious();\">��һҳ</a>" +
	     		"<a id=\"userNext\" style=\"position:absolute;top:"+top+"px;left: 580px;width: 50px;display: none;cursor: pointer;\" onclick=\"getUserNext();\">��һҳ</a></div>";
	     $("result").innerHTML=divStr;
	     $("userNext").style.display = "none";
	 	 $("userPrevious").style.display = "none";
	 	 var count = pageCount%4==0?pageCount/4:parseInt(pageCount/4)+1;// �ܹ��ּ�ҳ
	 	 if(pageCount>4 &&  pageIndex<count){
	 		 $("userNext").style.display = "block";
	 	 }
	 	 if(pageIndex!=1 && pageIndex>0){
	 		 $("userPrevious").style.display = "block";
	 	 }
	}
	function getUserNext(){
		var pageIndex = parseInt($("Page").value)+1;
		$("Page").value = pageIndex;
		var url="searchUserAction.do";
		var userName = $("userName").innerHTML;
		var message = condition+";"+type+";"+pageIndex+";"+userName;
		Ajax.request(url, "POST", message, "TEXT", searchUserCallback);
	}
	function getUserPrevious(){
		var pageIndex = parseInt($("Page").value)-1;
		$("Page").value = pageIndex;
		var url="searchUserAction.do";
		var userName = $("userName").innerHTML;
		var message = condition+";"+type+";"+pageIndex+";"+userName;
		Ajax.request(url, "POST", message, "TEXT", searchUserCallback);
	}
	

	/**
	 * ���ӹ�ע��
	 * 
	 * @param attentionUserName
	 *            ��ע�����
	 * @return
	 */
	function addUserAttention(attentionUserName){
		var url = "addAttentionAction.do";
		var userName = $("userName").innerHTML; 
		var message = userName+";"+attentionUserName;
		Ajax.request(url, "POST", message, "TEXT", addUserAttentionCallback);
	}
	
	function addUserAttentionCallback(text){
		 if(checkLogin(text)){
		    window.location="/blog/jsp/login.jsp"; return;
		 }
		 $("result").innerHTML=text;
		 alert($("success").innerHTML);
		 setTimeout(function a(){
			    var userName = $("userName").innerHTML;
				var url = "searchUserAction.do";
				var pageIndex = $("Page").value;
				var message = condition+";"+type+";"+pageIndex+";"+userName;
				Ajax.request(url, "POST", message, "TEXT", searchUserCallback);
		 }, 100);
	}
	function deleteUserAttention(attentionUserName){
		var url = "deleteAttentionAction.do";
		var userName = $("userName").innerHTML; 
		var message = userName+";"+attentionUserName;
		Ajax.request(url, "POST", message, "TEXT", deleteUserAttentionCallback);
	}
	function deleteUserAttentionCallback(text){// ɾ���ע��
		 if(checkLogin(text)){
		     window.location="/blog/jsp/login.jsp"; return;
		 }
		 $("result").innerHTML=text;
		 alert($("success").innerHTML);
		 setTimeout(function a(){
			    var userName = $("userName").innerHTML;
				var url = "searchUserAction.do";
				var pageIndex = $("Page").value;
				var message = condition+";"+type+";"+pageIndex+";"+userName;
				Ajax.request(url, "POST", message, "TEXT", searchUserCallback);
		 }, 100);
		 
	}
	/**
	 * �˳�
	 * @return
	 */
	function removeUser(){
	    var url = "existAction.do";
	    var message = "";
	    Ajax.request(url,"GET",message,"TEXT",removeCallback);
	}
	function removeCallback(){
		window.location = "jsp/login.jsp";
	}





