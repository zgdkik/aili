package org.hbhk.aili.base.share.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.share.vo.QueryPageVo;
import org.hbhk.aili.mybatis.server.support.QueryBean;
import org.hbhk.aili.mybatis.server.support.Sort;

public class QueryBeanUtil {

	private static Map<String, DateFormat> dateFormatCache = new HashMap<String, DateFormat>();

	/**
	 * 解析request中参数，转换成queryBean
	 * 
	 * @param parameterMap
	 * @return
	 * @throws ParseException
	 */
	public static QueryPageVo parseParameter(HttpServletRequest request)
			throws ParseException {
		QueryPageVo queryBean = new QueryPageVo();
		Object size = request.getParameter("page.pageSize");
		if (size == null) {
			 size = request.getParameter("rows");
		}
		if (size != null) {
			try {
				queryBean.getPage().setPageSize(
						Integer.parseInt(size.toString()));
			} catch (NumberFormatException e) {
				queryBean.getPage().setPageSize(10);
			}
		}
		Object startObject = request.getParameter("page.pageNum");
		if (startObject == null) {
			startObject = request.getParameter("page");
		}
		if (startObject != null) {
			try {
				int pageNum = Integer.parseInt(startObject.toString());
				queryBean.getPage().setPageNum(pageNum);
			} catch (NumberFormatException e) {
				queryBean.getPage().setPageNum(QueryBean.DEFULT_START);
			}
		}
		//sortName:id
		//sortOrder:desc
		String sortName = request.getParameter("sortName");
		String sortOrder = request.getParameter("sortOrder");
		if(StringUtils.isNotEmpty(sortName)&&StringUtils.isNotEmpty(sortOrder)){
			String sortStr =sortName+":"+sortOrder;
			if (sortStr != null) {
				queryBean.setSorts(Sort.parse(sortStr));
			}
		}
	
		Map<String, Object> queryParaMap = new HashMap<String, Object>();
		for (Object keyObject : request.getParameterMap().keySet()) {
			String key = keyObject.toString();
			if (QueryPara.isQueryPara(key)) {
				String value = request.getParameter(key);
				// 若值为null不做处理
				if (StringUtils.isBlank(value)) {
					continue;
				}
				// 去空格处理
				value = value.trim();
				QueryPara queryPara = QueryPara.parse(key);
				queryParaMap.put(queryPara.getName(),
						dealType(queryPara.getType(), value));
			}
		}
		queryBean.setParaMap(queryParaMap);
		return queryBean;
	}

	/**
	 * 根据传入的参数，生成sql进行like查询的表达式 例如： 传入abc 返回%abc%
	 * 
	 * 传入ab_c 返回%ab\_c%
	 * 
	 * @param para
	 * @return
	 */
	public static String genLikeString(String para) {
		String likeString = para.replace("_", "\\_").replace("%", "\\%");
		return "%" + likeString + "%";
	}

	/**
	 * 根据传入的参数，生成sql进行like查询的表达式(左like) 例如： 传入abc 返回%abc%
	 * 
	 * 传入ab_c 返回%ab\_c%
	 * 
	 * @param para
	 * @return
	 */
	public static String genLeftLikeString(String para) {
		String likeString = para.replace("_", "\\_").replace("%", "\\%");
		return likeString + "%";
	}

	/**
	 * 
	 * @param para
	 * @return
	 * @throws ParseException
	 */
	public static Object dealType(String typeString, String value)
			throws ParseException {
		if (QueryPara.TYPE_INT.equals(typeString)) {
			return Integer.parseInt(value);
		} else if (QueryPara.TYPE_LONG.equals(typeString)) {
			return Long.parseLong(value);
		} else if (QueryPara.TYPE_STRING_LIKE.equals(typeString)) {
			return genLikeString(value);
		} else if (QueryPara.TYPE_STRING_LEFT_LIKE.equals(typeString)) {
			return genLeftLikeString(value);
		} else if (QueryPara.TYPE_DATE.equals(typeString) || "datebox".equals(typeString)) {
			try {
				return parse(value, "yyyy-MM-dd");
			} catch (Exception e) {
				return parse(value, "yyyy-MM-dd hh:mm:ss");
			}
			
		} else if (QueryPara.TYPE_TIME.equals(typeString)|| "datetimebox".equals(typeString)) {
			try {
				return parse(value, "yyyy-MM-dd hh:mm:ss");
			} catch (Exception e) {
				return parse(value, "yyyy-MM-dd");
			}
		}
		return value;
	}

	public static Date parse(String str, String pattern) throws ParseException {
		DateFormat df = dateFormatCache.get(pattern);
		if (df == null) {
			df = new SimpleDateFormat(pattern);
			dateFormatCache.put(pattern, df);
		}
		return df.parse(str);
	}

}
