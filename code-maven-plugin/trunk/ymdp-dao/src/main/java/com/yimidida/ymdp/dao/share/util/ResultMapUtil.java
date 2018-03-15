package com.yimidida.ymdp.dao.share.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.yimidida.ymdp.dao.server.annotation.Column;
import com.yimidida.ymdp.dao.server.annotation.Id;

public class ResultMapUtil {
	public static  void  genResultMap(Class<?> cls){
		List<Field>  fields = new ArrayList<Field>();
		ObjectUtil.getFields(cls, fields);
		StringBuilder map = new StringBuilder();
		map.append("<resultMap id=\""+cls.getSimpleName()+"Map\" type=\""+cls.getName()+"\" >");
		map.append("\r");
		for (Field field : fields) {
			Column col = field.getAnnotation(Column.class);
			if(col==null){
				continue;
			}
			Id id = field.getAnnotation(Id.class);
			String name = field.getName();
			String colName = col.value();
			if(id != null){
				map.append("<id property=\""+name+"\" column=\""+colName+"\" />");
			}else{
				map.append("<result property=\""+name+"\" column=\""+colName+"\"/>");
			}
			map.append("\r");
		}
		map.append("</resultMap>");
		map.append("\r");
		System.out.println(map);
	}

}
