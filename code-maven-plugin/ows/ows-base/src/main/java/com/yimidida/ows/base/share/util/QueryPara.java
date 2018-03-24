/**
 * 
 */
package com.yimidida.ows.base.share.util;


/**
 * 查询参数的封装类
 * 例如：q_int_lifecycle
 * 标记_类型_参数名
 */
public class QueryPara {
	public static final String SEPARATE_CHAR = "_";
	
	/**
	 * 参数标记
	 */
	private String tag;
	/**
	 * 查询类参数标记
	 */
	public static final String TAG_QUERY = "q"; 
	/**
	 * 参数类型
	 */
	private String type;
	/**
	 * 字符串类型，并且需要进行like的转换。此种类型的参数会在参数值的前后加入%%
	 */
	//全模糊匹配
	public static final String TYPE_STRING_LIKE = "sl";
	//左模糊查询
	public static final String TYPE_STRING_LEFT_LIKE = "sll";
	public static final String TYPE_STRING = "str"; 
	public static final String TYPE_INT = "int";
	public static final String TYPE_LONG = "long";
	public static final String TYPE_DATE = "date";
	public static final String TYPE_TIME = "time";
	/**
	 * 参数名称
	 */
	private String name;
	/**
	 * 验证是否是查询类参数
	 * @return
	 */
	public static boolean isQueryPara(String paraKey){
		if(paraKey.indexOf(QueryPara.TAG_QUERY+QueryPara.SEPARATE_CHAR)==0){
			return true;
		}
		return false;
	}
	
	public static QueryPara parse(String paraKey){
		if(!isQueryPara(paraKey)){
			return null;
		}
		String[] qArrary = paraKey.split("_");
		QueryPara queryPara = new QueryPara();
		queryPara.setTag(qArrary[0]);
		queryPara.setType(qArrary[1]);
		queryPara.setName(qArrary[2]);
		return queryPara;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
