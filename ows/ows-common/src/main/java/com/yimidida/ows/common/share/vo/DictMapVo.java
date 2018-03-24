package com.yimidida.ows.common.share.vo;

import java.io.Serializable;
import java.util.List;

import com.yimidida.ows.common.share.entity.DictValueEntity;

/**
 * 数据字典类型数据列表
 */
public class DictMapVo implements Serializable {

	private static final long serialVersionUID = -1855791586450507520L;

	private String dictCode;

	private List<DictValueEntity> dictDataList;

	public DictMapVo() {
	}

	public DictMapVo(String dictCode, List<DictValueEntity> dictDataList) {
		this.dictCode = dictCode;
		this.dictDataList = dictDataList;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public List<DictValueEntity> getDictDataList() {
		return dictDataList;
	}

	public void setDictDataList(List<DictValueEntity> dictDataList) {
		this.dictDataList = dictDataList;
	}

}
