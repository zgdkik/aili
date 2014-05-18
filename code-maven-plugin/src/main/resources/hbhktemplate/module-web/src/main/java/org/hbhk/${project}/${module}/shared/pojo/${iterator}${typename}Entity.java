[#ftl]
[#assign properties="id,createDate,createUser,modifyDate,modifyUser"]
package com.deppon.${project}.module.${module}.shared.domain;
import java.sql.Timestamp;
import java.util.Date;
import com.deppon.foss.framework.entity.BaseEntity;
/**
 * ${table.name}
 * 
 * @author DPAP ${.now}
 * 
 */
public class ${table.typeName}Entity extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
[#list table.columnList as column]
	[#if properties?index_of(column.columnName) = -1]
	//${column.comment}	
	private ${column.javaDataType} ${column.columnName}; 
	[/#if]
[/#list]

[#list table.columnList as column]
    [#if properties?index_of(column.columnName) = -1]
    public ${column.javaDataType} get${column.columnName?cap_first}(){
		return ${column.columnName};
	}
	public void set${column.columnName?cap_first}(${column.javaDataType} ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};
	}
	[/#if]
[/#list]

}