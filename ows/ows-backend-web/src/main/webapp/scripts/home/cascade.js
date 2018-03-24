var cascadeCache = new HashMap();
function cascadeCompany(companyId,provinceId){
	setTimeout(function(){
		var company = $(companyId);
		company.combobox({
			url : base+'/common/getCompany',
			textField : 'name',
			valueField : 'id',
			mode : 'remote',
			panelHeight : '150',
			value:"",
			onHidePanel: function(){
				var provinceNetwork =$(provinceId);
				if(provinceNetwork.length>0){
				   provinceNetwork.combobox("loadData",[{"id":"","name":"全部"}]);
			       var code = $(companyId).combobox('getValue');
			       var dataSelect = cascadeCache.get(companyId+code);
			       if(dataSelect!=null){
			    	   provinceNetwork.combobox("loadData",dataSelect);
						if(dataSelect.length>0){
							provinceNetwork.combobox("setValue","");
						}
			       }else{
			    	   if(code!=null && code!=""){
			    		   ym.sendPost(base+'/district/getCascadeComp/' + code, null, {
								successHandler : function(data, textStatus, jqXHR) {
									console.log(data);
									
									if(data!=null && data.length>0){
										 data.splice(0, 0, {"id":"","name":"全部"});
										 provinceNetwork.combobox("loadData",data);
										 cascadeCache.put(companyId+code,data);
										 provinceNetwork.combobox("setValue","");
									}
								}
							});
			    	   }
			    	   
			    	 
			       }
			    	
				}
		   }
		});
		setTimeout(function(){
			var medata = company.combobox("getData");
			medata.splice(0, 0, {"id":"","name":"全部"});
			company.combobox("loadData",medata);
		},200);
		
		$(provinceId).combobox({
			valueField : 'id', 
			textField : 'name',
			value:"",
			data:[{"id":"","name":"全部"}],
			panelHeight : '150'
		});	
		
	},0);
};

function cascadeCompProvDept(companyId,provinceId,deptId){
	setTimeout(function(){
		var company = $(companyId);
		company.combobox({
			url : base+'/common/getCompany',
			textField : 'name',
			valueField : 'id',
			mode : 'remote',
			panelHeight : '150',
			value:"",
			onHidePanel: function(){
				var provinceNetwork =$(provinceId);
				if(provinceNetwork.length>0){
				   provinceNetwork.combobox("loadData",[{"id":"","name":"全部"}]);
			       var code = $(companyId).combobox('getValue');
			       var cacheid= "cascadeCompProvDept"+companyId+code;
			       var dataSelect = cascadeCache.get(cacheid);
			       if(dataSelect!=null){
			    	   provinceNetwork.combobox("loadData",dataSelect);
						if(dataSelect.length>0){
							provinceNetwork.combobox("setValue","");
						}
			       }else{
			    	   if(code!=null && code!=""){
			    		   ym.sendPost(base+'/district/getCascadeComp/' + code, null, {
								successHandler : function(data, textStatus, jqXHR) {
									console.log(data);
									
									if(data!=null && data.length>0){
										 data.splice(0, 0, {"id":"","name":"全部"});
										 provinceNetwork.combobox("loadData",data);
										 cascadeCache.put(cacheid,data);
										 provinceNetwork.combobox("setValue","");
									}
								}
							});
			    	   }
			    	   
			    	 
			       }
			    	
				}
		   }
		});
		setTimeout(function(){
			var medata = company.combobox("getData");
			medata.splice(0, 0, {"id":"","name":"全部"});
			company.combobox("loadData",medata);
		},200);
		
		$(provinceId).combobox({
			valueField : 'id', 
			textField : 'name',
			value:"",
			data:[{"id":"","name":"全部"}],
			panelHeight : '150',
			onHidePanel: function(){
				var provinceNetwork =$(deptId);
				if(provinceNetwork.length>0){
				   provinceNetwork.combobox("loadData",[ {"deptCode":"","deptName":"全部"}]);
			       var code = $(provinceId).combobox('getValue');
			       var cacheid= "cascadeCompProvDept"+companyId+provinceId+code;
			       var dataSelect = cascadeCache.get(cacheid);
			       if(dataSelect!=null){
			    	   provinceNetwork.combobox("loadData",dataSelect);
						if(dataSelect.length>0){
							provinceNetwork.combobox("setValue","");
						}
			       }else{
			    	   if(code!=null && code!=""){
			    		   ym.sendPost(base+'/dept/getDepartmentByProvinceCode/'+code,null, {
								successHandler : function(data, textStatus, jqXHR) {
									console.log(data);
									if(data!=null && data.length>0){
										 data.splice(0, 0, {"deptCode":"","deptName":"全部"});
										 cascadeCache.put(cacheid,data);
										 provinceNetwork.combobox("loadData",data);
										 provinceNetwork.combobox("setValue","");
									}
								}
							});
			    	   }
			       }
			    	
				}
		   }
		});	
		
		
		$(deptId).combobox({
			valueField : 'deptCode', 
			textField : 'deptName',
			value:"",
			data:[{"deptCode":"","deptName":"全部"}],
			panelHeight : '150'
		});	
		
	},0);

};

function cascadeAbnormalType(abnormalTypeId, abnormalItemId){
	setTimeout(function(){
		var abnormalType = $(abnormalTypeId);
		abnormalType.combobox({
			url : base+'/abnormalType/getAbnormalType',
			textField : 'abnormalType',
			valueField : 'abnormalTypeId',
			mode : 'remote',
			panelHeight : '150',
			value:"",
			onHidePanel: function(){
				var abnormalType =$(abnormalItemId);
				if(abnormalType.length > 0){
					abnormalType.combobox("loadData",[{"abnormalTypeId":"","abnormalType":"全部"}]);
			       var code = $(abnormalTypeId).combobox('getValue');
			       var dataSelect = cascadeCache.get(abnormalTypeId+code);
			       if(dataSelect != null){
			    	   abnormalType.combobox("loadData", dataSelect);
						if(dataSelect.length > 0){
							abnormalType.combobox("setValue","");
						}
			       }else{
			    	   if(code!=null && code!=""){
			    		   ym.sendPost(base+'/abnormalType/getCascadeAbnormalType/' + code, null, {
								successHandler : function(data, textStatus, jqXHR) {
									console.log(data);
									if(data!=null && data.length>0){
										 data.splice(0, 0, {"abnormalTypeId":"","abnormalType":"全部"});
										 abnormalType.combobox("loadData",data);
										 cascadeCache.put(abnormalTypeId+code,data);
										 abnormalType.combobox("setValue","");
									}
								}
							});
			    	   }
			    	   
			    	 
			       }
			    	
				}
		   }
		});
		setTimeout(function(){
			var medata = abnormalType.combobox("getData");
			medata.splice(0, 0, {"abnormalTypeId":"","abnormalType":"全部"});
			abnormalType.combobox("loadData",medata);
		},200);
		
		$(abnormalItemId).combobox({
			textField : 'abnormalType',
			valueField : 'abnormalTypeId',
			value:"",
			data:[{"abnormalTypeId":"","abnormalType":"全部"}],
			panelHeight : '150'
		});	
		
	},0);
};

function cascadeBigDept(companyId,provinceId,deptId){
	setTimeout(function(){
		var company = $(companyId);
		company.combobox({
			url : base+'/dept/searchBigDept',
			textField : 'deptName',
			valueField : 'deptCode',
			mode : 'remote',
			panelHeight : '150',
			value:"",
			onHidePanel: function(){
				var provinceNetwork =$(provinceId);
				if(provinceNetwork.length>0){
				   provinceNetwork.combobox("loadData",[{"deptCode":"","deptName":"全部"}]);
				   $(deptId).combobox("loadData",[]);
			       var code = $(companyId).combobox('getValue');
			       var dataSelect = cascadeCache.get(companyId+code);
			       if(dataSelect!=null){
			    	   provinceNetwork.combobox("loadData",dataSelect);
						if(dataSelect.length>0){
							provinceNetwork.combobox("setValue","");
						}
			       }else{
			    	   if(code!=null && code!=""){
			    		   ym.sendPost(base+'/dept/searchSmallDept/' + code, null, {
								successHandler : function(data, textStatus, jqXHR) {
									console.log(data);
									if(data!=null && data.length>0){
										 data.splice(0, 0, {"deptCode":"","deptName":"全部"});
										 cascadeCache.put(companyId+code,data);
										 provinceNetwork.combobox("loadData",data);
										 provinceNetwork.combobox("setValue","");
									}
								}
							});
			    	   }
			       }
			    	
				}
		   }
		});
		setTimeout(function(){
			var medata = company.combobox("getData");
			medata.splice(0, 0, {"deptCode":"","deptName":"全部"});
			company.combobox("loadData",medata);
		},200);
		$(provinceId).combobox({
			textField : 'deptName',
			valueField : 'deptCode',
			panelHeight : '150'	,
			data:[{"deptCode":"","deptName":"全部"}],
			value:"",
			onHidePanel: function(){
				var provinceNetwork =$(deptId);
				if(provinceNetwork.length>0){
				   provinceNetwork.combobox("loadData",[{"deptCode":"","deptName":"全部"}]);
			       var code = $(provinceId).combobox('getValue');
			       var dataSelect = cascadeCache.get(provinceId+code);
			       if(dataSelect!=null){
			    	   provinceNetwork.combobox("loadData",dataSelect);
						if(dataSelect.length>0){
							provinceNetwork.combobox("setValue","");
						}
			       }else{
			    	   if(code!=null && code!=""){
			    		   ym.sendPost(base+'/soelector/getDeptList', {"parentCode":code}, {
								successHandler : function(data, textStatus, jqXHR) {
									console.log(data);
									if(data!=null && data.length>0){
										 cascadeCache.put(provinceId+code,data);
										 provinceNetwork.combobox("loadData",data);
										 provinceNetwork.combobox("setValue","");
									}
								}
							});
			    	   }
			       }
			    	
				}
		   }
		});	
		if($(deptId).length>0){
			$(deptId).combobox({
				textField : 'deptName',
				valueField : 'deptCode',
				value:"",
				data:[{"deptCode":"","deptName":"全部"}],
				panelHeight : '150'
			});
		}
	},0);
};
function provinceSelector(proId){
	$(proId).combobox({
		valueField : 'id', // 值字段
		textField : 'name', // 显示的字段
		url : base+'/district/getCascadeProvince/all',
		panelHeight : '150',
		value:"",
		editable : true,
	});
};

function provinceDeptSelector(proId,deptId){
	$(proId).combobox({
		valueField : 'id', // 值字段
		textField : 'name', // 显示的字段
		url : base+'/district/getCascadeProvince/all',
		panelHeight : '150',
		value:"",
		data:[{"deptCode":"","deptName":"全部"}],
		value:"",
		onHidePanel: function(){
			var provinceNetwork =$(deptId);
			if(provinceNetwork.length>0){
			   provinceNetwork.combobox("loadData",[ {"deptCode":"","deptName":"全部"}]);
		       var code = $(proId).combobox('getValue');
		       var cacheid= "provinceDeptSelector"+proId+code;
		       var dataSelect = cascadeCache.get(cacheid);
		       if(dataSelect!=null){
		    	   provinceNetwork.combobox("loadData",dataSelect);
					if(dataSelect.length>0){
						provinceNetwork.combobox("setValue","");
					}
		       }else{
		    	   if(code!=null && code!=""){
		    		   ym.sendPost(base+'/dept/getDepartmentByProvinceCode/'+code,null, {
							successHandler : function(data, textStatus, jqXHR) {
								console.log(data);
								if(data!=null && data.length>0){
									 data.splice(0, 0, {"deptCode":"","deptName":"全部"});
									 cascadeCache.put(cacheid,data);
									 provinceNetwork.combobox("loadData",data);
									 provinceNetwork.combobox("setValue","");
								}
							}
						});
		    	   }
		       }
			}
		}
	});
	
	if($(deptId).length>0){
		$(deptId).combobox({
			textField : 'deptName',
			valueField : 'deptCode',
			value:"",
			data:[{"deptCode":"","deptName":"全部"}],
			panelHeight : '150'
		});
	}
};

function deptSelector(deptId,prompt){
	setTimeout(function(){
		$(deptId).combobox({
			url : base+'/soelector/getDeptList',
			textField : 'deptName',
			valueField : 'deptCode',
			mode : 'remote',
			data:[{"deptCode":"","deptName":"全部"}],
			value:"",
			delay:500,
			panelHeight : '150'
		});
	},0);
	setTimeout(function(){
		var medata = $(deptId).combobox("getData");
		if(prompt){
			medata.splice(0, 0, {"deptCode":"","deptName":prompt});
		}else{
			medata.splice(0, 0, {"deptCode":"","deptName":"请选择"});
		}
		$(deptId).combobox("loadData",medata);
	},200);
};

function companySelector(companyId){
	setTimeout(function(){
		var company = $(companyId);
		company.combobox({
			url : base+'/common/getCompany',
			textField : 'name',
			valueField : 'id',
			mode : 'remote',
			panelHeight : '150',
			value:"",
		});
	});
};
$(document).ready(function() {
	setTimeout(function(){
		if($("#company-province").length>0 && $("#company-province").length>0){
			cascadeCompany("#company-province","#province-network");
		}
		if($("#dept-selector").length>0){
			deptSelector("#dept-selector");
		}
	},0);
	
});

function driverSelector(driId){
	$(driId).combobox({
		valueField : 'driverCode', // 值字段
		textField : 'driverName', // 显示的字段
		url : base+'/driver/selector',
		panelHeight : '150',
		value:"",
		editable : true,
	});
};

function vehicleSelector(driId){
	$(driId).combobox({
		valueField : 'vehiclesNo', // 值字段
		textField : 'vehiclesNo', // 显示的字段
		url : base+'/vehicle/selector',
		panelHeight : '150',
		value:"",
		editable : true,
	});
};

function areaSelector(driId,prompt){
	setTimeout(function(){
		$(driId).combobox({
			valueField : 'areaCode', // 值字段
			textField : 'areaName', // 显示的字段
			url : base+'/areaMap/selector',
			panelHeight : '150',
			value:"",
			//iconCls:'icon-search',
			editable : true,
		});
	},0);
	setTimeout(function(){
		var medata = $(driId).combobox("getData");
		if(prompt){
			medata.splice(0, 0, {"areaCode":"","areaName":prompt});
		}else{
			medata.splice(0, 0, {"areaCode":"","areaName":"全部"});
		}
		 $(driId).combobox("loadData",medata);
	},200);
};