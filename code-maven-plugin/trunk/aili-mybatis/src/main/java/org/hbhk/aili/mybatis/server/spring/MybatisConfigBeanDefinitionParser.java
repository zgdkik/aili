package org.hbhk.aili.mybatis.server.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.IndexTable;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.server.support.DynamicSqlTemplate;
import org.hbhk.aili.mybatis.server.support.ModelInfo;
import org.hbhk.aili.mybatis.share.util.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.w3c.dom.Element;

public class MybatisConfigBeanDefinitionParser implements BeanDefinitionParser {

	private static final Logger logger = LoggerFactory
			.getLogger(MybatisConfigBeanDefinitionParser.class);

	public static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

	public static final String DIALECT = "dialect";

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		if (element.hasAttribute(BASE_PACKAGE_ATTRIBUTE)) {
			logger.debug("Start auto Dao detection...");
			String dialect = element.getAttribute(DIALECT);
			if(StringUtils.isNotEmpty(dialect)){
				DynamicSqlTemplate.dialect = dialect;
			}
			List<Class<?>> tabClasses = getAnnotatedClasses(Table.class,
					element.getAttribute(BASE_PACKAGE_ATTRIBUTE));
			if (tabClasses != null) {
				for (Class<?> tab : tabClasses) {
					Field[] fields = SqlUtil.getColumnFields(tab);
					ModelInfo tableInfo = new ModelInfo();
					List<String> columnList = new ArrayList<String>();
					List<String> fieldList = new ArrayList<String>();
					StringBuilder columnFields = new StringBuilder();
					String pkName = SqlUtil.getpriKeyName(fields);
					String pk = SqlUtil.getpriKey(fields);
					Field pkField =  SqlUtil.getpriKeyField(fields);
					String tabName = tab.getAnnotation(Table.class).value();
					Map<String, String> fieldColumnMap = new HashMap<String, String>();
					Map<String, Field> indexTable = new HashMap<String, Field>();
					Map<String, Boolean> fieldLikeMap = new HashMap<String, Boolean>();
					for (Field field : fields) {
						Column col = field.getAnnotation(Column.class);
						String colName = col.value();
						String fieldName = field.getName();
						columnList.add(colName);
						fieldList.add(fieldName);
						columnFields.append(colName + ",");
						fieldLikeMap.put(fieldName, col.like());
						fieldColumnMap.put(fieldName, colName);
						IndexTable indexTab = field.getAnnotation(IndexTable.class);
						if(indexTab!=null){
							indexTable.put(fieldName, field);
						}
					}
					String columns = columnFields.substring(0,
							columnFields.length() - 1);
					tableInfo.setColumnFields(fields);
					tableInfo.setCls(tab);
					tableInfo.setColumnList(columnList);
					tableInfo.setFieldList(fieldList);
					tableInfo.setColumns(columns);
					tableInfo.setIndexTable(indexTable);
					tableInfo.setTable(tabName);
					tableInfo.setPk(pk);
					tableInfo.setPkName(pkName);
					Field vfield = SqlUtil.getVersionField(fields);
					if(vfield != null){
						Column vcol = vfield.getAnnotation(Column.class);
						if(vcol!=null){
							tableInfo.setVersion(vcol.value());
							tableInfo.setVersionName(vfield.getName());
						}
					}
					tableInfo.setFieldColumnMap(fieldColumnMap);
					tableInfo.setFieldLikeMap(fieldLikeMap);
					tableInfo.setPkField(pkField);
					DynamicSqlTemplate.tabs.put(tab.getName(), tableInfo);
				}
			}
		}
		return null;
	}

	private List<Class<?>> getAnnotatedClasses(
			Class<? extends Annotation> annotation, String... scannPackage) {
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();

		// 是否使用默认过滤器true使用
		ClassPathScanningCandidateComponentProvider packageScan = new ClassPathScanningCandidateComponentProvider(
				false);
		packageScan.addIncludeFilter(new AnnotationTypeFilter(annotation));
		if (scannPackage == null) {
			return null;
		}
		for (String pack : scannPackage) {
			Set<BeanDefinition> bds = packageScan.findCandidateComponents(pack);
			for (BeanDefinition beanDefinition : bds) {
				try {
					annotatedClasses.add(Class.forName(beanDefinition
							.getBeanClassName()));
				} catch (Exception e) {
				}
			}
		}
		return annotatedClasses;

	}
}
