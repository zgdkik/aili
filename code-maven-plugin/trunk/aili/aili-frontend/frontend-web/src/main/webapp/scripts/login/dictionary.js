/**
 * 数据字典封装组件
 */
Ext.define('Deppon.dict.DictCombo', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.dictcombo',
	queryMode: "local",
	triggerAction: "all",
	displayField: "valueName",
	valueField: "valueCode",
	dictType: "",
	anyRecords: null,
	initComponent: function() {
		var me = this,
			anyRecords = me.anyRecords,
			dictData = login.dictCollection.get(me.dictType);
		if(Ext.isEmpty(dictData)) {
			Deppon.Dictionary.loadDictDataByType(me.dictType);
			dictData = login.dictCollection.get(me.dictType);
		}
		var data = Ext.clone(dictData);
        if (!Ext.isEmpty(anyRecords)) {
            if (Ext.isArray(anyRecords)) {
                for (var i = 0; i < anyRecords.length; i++) {
                	data.unshift(anyRecords[i]);
                }
            } else {
            	data.unshift(anyRecords);
            }
        }
		me.store = Ext.create('Ext.data.JsonStore',{
			fields: [ "valueCode", "valueName" ],
			data: data,
			proxy: {
		        type: 'memory'
		    }
		});
		me.callParent();
	}
});

Ext.define('Deppon.Dictionary', {
	singleton: true,
	dictCollection:  new Ext.util.HashMap(),
	loadDictDataByType: function(dictType) {
		Ext.Ajax.request({
			url: base+'/dict/loadDictionaryByType',
			method: 'POST',
			params: {
				code: dictType
			},
			async: false,
			success: function(response){
				var result = Ext.decode(response.responseText),
					dictionaryList = result.data.dictionaryList;
				if(Ext.isEmpty(dictionaryList)) {
					dictionaryList = [];
				}
				login.dictCollection.add(dictType, dictionaryList);
			}
		});
	},
	getDictDataByType: function(dictType) {
		if(Ext.isEmpty(dictType)) {
			return;
		}
		var dictData = login.dictCollection.get(dictType);
		if(Ext.isEmpty(dictData)) {
			Deppon.Dictionary.loadDictDataByType(dictType);
			dictData = login.dictCollection.get(dictType);
		}
		return Ext.clone(dictData);
	},
	getDictNameByCode: function(valueCode, dictType) {
		var dictData = Deppon.Dictionary.getDictDataByType(dictType);
		if(!Ext.isEmpty(valueCode) && !Ext.isEmpty(dictData)) {
			for ( var i = 0; i < dictData.length; i++) {
				if (valueCode == dictData[i].valueCode) {
				     return dictData[i].valueName;
				}
			}
		}
		return valueCode;
	},
	getDictCodeByName: function(valueName, dictType) {
		var dictData = Deppon.Dictionary.getDictDataByType(dictType);
		if(!Ext.isEmpty(valueName) && !Ext.isEmpty(dictData)) {
			for ( var i = 0; i < dictData.length; i++) {
				if (valueName == dictData[i].valueName) {
				     return dictData[i].valueCode;
				}
			}
		}
		return valueName;
	}
});
