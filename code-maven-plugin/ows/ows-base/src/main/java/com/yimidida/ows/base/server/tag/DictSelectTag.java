package com.yimidida.ows.base.server.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ymdp.cache.server.CacheManager;
import com.yimidida.ymdp.cache.server.ICache;

public class DictSelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9022382600872914840L;

	private Log log = LogFactory.getLog(getClass());

	ICache<String, List<IDictValue>> cache;

	private void init() {
		cache = CacheManager.getInstance().getCache(
				FrontendConstants.DICT_VALUE_CACHE_UUID);

	}

	private String dictType;
	private String defaultSelected;

	@Override
	public int doEndTag() throws JspException {
		JspWriter jspOut = pageContext.getOut();
		try {
			if (StringUtils.isEmpty(dictType)) {
				return SKIP_BODY;
			}
			if (cache == null) {
				init();
			}
			List<IDictValue> dictValues = cache.get(dictType);
			if (dictValues == null || dictValues.size() == 0) {
				return SKIP_BODY;
			}
			for (IDictValue dv : dictValues) {
				if (dv.getKey().equals(defaultSelected)) {
					jspOut.append("<option value=\"" + dv.getKey()+ "\" selected=\"selected\">" + dv.getValue()+ "</option>");
				} else {
					jspOut.append("<option value=\"" + dv.getKey() + "\">"+ dv.getValue() + "</option>");
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
		}
		return EVAL_PAGE;
	}

	public void setDictType(String dictType) {

		this.dictType = dictType;

	}

	public String getDictType() {

		return this.dictType;

	}

	public void setDefaultSelected(String defaultSelected) {

		this.defaultSelected = defaultSelected;

	}

	public String getDefaultSelected() {

		return this.defaultSelected;

	}

}
