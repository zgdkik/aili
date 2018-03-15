/**
 * 
 */
package org.hbhk.aili.client.core.widget.print.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.client.core.widget.print.entity.BillTemplateInfo;
import org.hbhk.aili.client.core.widget.print.entity.BillTemplateSort;
import org.hbhk.aili.client.core.widget.print.exception.PrintException;

/**
 * Description: 报表模板或清单模板信息服务接口
 */
public interface IBillTemplateInfoService {
	/**
	 * 
	 * <p>Title: getPlainTemplateSortList</p>
	 * <p>Description: 获取普通打印模板分类</p>
	 * @return
	 * @throws PrintException 打印异常
	 */
	public List<BillTemplateSort> getPlainTemplateSortList() throws PrintException;
	
	/**
	 * 
	 * <p>Title: getPreformatBillTemplateSortList</p>
	 * <p>Description: 获取套打模板分类</p>
	 * @return
	 * @throws PrintException 打印异常
	 */
	public List<BillTemplateSort> getPreformatBillTemplateSortList() throws PrintException;
	
	/**
	 * 
	 * <p>Title: getBillTemplateInfoList</p>
	 * <p>Description: 获取某个类别及其子类别下的所有的报表模板或清单模板名称</p>
	 * @param sortId 排序编号
	 * @return
	 * @throws PrintException 打印异常
	 */
	public List<BillTemplateInfo> getBillTemplateInfoList(Long sortId) throws PrintException;
	
	/**
	 * 
	 * <p>Title: invert</p>
	 * <p>Description: 将List数据类型装换为Hashtable数据类型</p>
	 * @param billTemplateList 运单模板列表
	 * @return
	 * @throws PrintException 打印异常
	 */
	public Map<String, Object> invert(List<BillTemplateSort> billTemplateList) throws PrintException;
	
	/**
	 * 
	 * <p>Title: getBillTemplate</p>
	 * <p>Description: 通过模板ID获取报表模板或清单模板</p>
	 * @param templateId 模板ID
	 * @return
	 * @throws PrintException 打印异常
	 */
	public BillTemplateInfo getBillTemplate(Long templateId) throws PrintException;
}