package org.hbhk.aili.gen.server.foss.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javassist.Modifier;

import org.hbhk.aili.gen.server.foss.FossGenerateMain;
import org.hbhk.aili.gen.server.foss.ViewDataType;
import org.hbhk.aili.gen.server.model.MakeModel;
import org.hbhk.aili.gen.server.model.PropertyDesc;
import org.hbhk.aili.gen.server.service.MakeModelService;
import org.springframework.util.ReflectionUtils;

public class FossMakeModelServiceImpl implements MakeModelService {

	/**
	 * 通过字段查找对应的annotation数组 如果找不到,则在getxxx方法中查找
	 * 
	 * @param field
	 * @return
	 */
	private Annotation[] findAnnosByFiledName(Class<?> clazz, Field field) {

		Annotation[] annos = field.getAnnotations();

		if (annos.length == 0) {
			String columnName = field.getName();
			String getMethodName = "get"
					+ columnName.substring(0, 1).toUpperCase()
					+ columnName.substring(1);
			Method method = ReflectionUtils.findMethod(clazz, getMethodName);
			if (method != null)
				annos = method.getAnnotations();
		}

		return annos;
	}

	/**
	 * 获取最后一级包名
	 * 
	 * @param name
	 * @return
	 */
	private String queryLastPackageName(String name) {
		int index = name.lastIndexOf(".");
		if (index != -1) {
			name = name.substring(0, index);
		}
		index = name.lastIndexOf(".");
		if (index != -1) {
			name = name.substring(index + 1);
		}

		return name;

	}

	/**
	 * 清除包名
	 * 
	 * @param name
	 * @return
	 */
	private String clearPackage(String name) {
		int index = name.lastIndexOf(".");
		if (index != -1) {
			name = name.substring(index + 1);
		}
		return name;
	}

	/**
	 * 过滤掉一部分属性 如 id ,version
	 * 
	 * @param sourceList
	 * @return
	 */
	private List<PropertyDesc> filterPropertyList(
			List<PropertyDesc> sourceList, MakeModel mm) {

		List<PropertyDesc> result = new ArrayList<PropertyDesc>();

		for (PropertyDesc pd : sourceList) {

			if (!pd.getFieldName().equals(mm.getPkName())
					&& !pd.getFieldName().equals(mm.getVersionField())
					&& !pd.getFieldName().equals(mm.getLifecycle())) {
				result.add(pd);
			}
		}

		return result;
	}

	/**
	 * 通过class反射机制生成makeModel对象
	 * 
	 * @param clazz
	 * @param authName
	 * @param lifecycle
	 * @return
	 */
	public MakeModel queryByClass(Class<?> clazz) {
		MakeModel mm = new MakeModel();
		mm.setEntityPackagName(clazz.getName());
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) { // 不用处理static字段
				continue;
			}
			PropertyDesc pd = new PropertyDesc();
			String fieldName = field.getName();
			String jetName = ":"+field.getName();
			pd.setFieldName(fieldName);
			pd.setJetName(jetName);
			pd.setColumnName(field.getName());
			String veiwType = ViewDataType.forType(field.getType().getName());
			pd.setFieldType(veiwType);
			//pd.setFieldType(clearPackage(field.getType().getName()));
			mm.getPropertyList().add(pd);

		}
		mm.setEntityName(clearPackage(clazz.getName()));
		mm.setPackagName(queryLastPackageName(clazz.getName()));
		//TODO
		//mm.setTableName(table.value());
		// 过滤掉id以及version字段
		mm.setPropertyList(filterPropertyList(mm.getPropertyList(), mm));

		return mm;
	}
}
