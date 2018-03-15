package org.hbhk.aili.client.core.widget.print.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hbhk.aili.client.core.widget.print.entity.BillTemplateInfo;
import org.hbhk.aili.client.core.widget.print.entity.BillTemplateSort;
import org.hbhk.aili.client.core.widget.print.exception.PrintException;
import org.hbhk.aili.client.core.widget.print.service.IBillTemplateInfoService;

/**
 * Description: 报表模板或清单模板信息服务实现
 *
 */
public class BillTemplateServiceImpl implements IBillTemplateInfoService {
	
	private Random random = new Random();
	/**
	 * 
	 */
	@Override
	public List<BillTemplateInfo> getBillTemplateInfoList(Long sortId) {
		List<BillTemplateInfo> billTemplateList = new ArrayList<BillTemplateInfo>();
		
		for (long i = 0; i < 10; i++) {
			BillTemplateInfo billSort = new BillTemplateInfo();
			billSort.setId(i);
			billSort.setTitle("模板_" + random.nextInt());
			billTemplateList.add(billSort);
		}

		return billTemplateList;
	}
	
	/**
	 * 获取普通打印模板分类
	 */
	@Override
	public List<BillTemplateSort> getPlainTemplateSortList() {
		List<BillTemplateSort> billTemplateList = new ArrayList<BillTemplateSort>();

		return billTemplateList;
	}

	/**
	 * 获取套打模板分类
	 */
	@Override
	public List<BillTemplateSort> getPreformatBillTemplateSortList() {
		List<BillTemplateSort> billTemplateList = new ArrayList<BillTemplateSort>();
			
	/*	for (long i = 0; i < 10; i++) {	}*/
		
		BillTemplateSort billSort = new BillTemplateSort();
		billSort.setId(1L);
		billSort.setName("运单");
		billTemplateList.add(billSort);

		return billTemplateList;
	}


	/**
	 * 将List数据类型装换为Hashtable数据类型
	 */
	public Map<String, Object> invert(List<BillTemplateSort> billTemplateList) {
		if (billTemplateList == null || billTemplateList.size() == 0) {
			return null;
		}

		Map<String, Object> nodes = new HashMap<String, Object>();
		
		// 普通打印节点
		Map<String, Object> map = new HashMap<String, Object>();
	//	hashtable.put("", "");
		nodes.put("基础报表", map);
		
		// 套打节点
		Map<String, Object> hashtable2 = new HashMap<String, Object>();
		for (BillTemplateSort bill : billTemplateList) {
			hashtable2.put(bill.getName(), bill.getName());
		}
		nodes.put("套打表单", hashtable2);
		
		return nodes;
	}

	@Override
	public BillTemplateInfo getBillTemplate(Long templateId) throws PrintException {
		BillTemplateInfo template = new BillTemplateInfo();
		
		String filePath = "";
		File file = new File(filePath);
		template.setContentFile(file);
		
		return template;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}