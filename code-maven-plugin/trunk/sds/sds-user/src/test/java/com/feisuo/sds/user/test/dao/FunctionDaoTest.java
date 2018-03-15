package com.feisuo.sds.user.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feisuo.sds.user.server.dao.IFunctionDao;
import com.feisuo.sds.user.share.entity.PrivilegeEntity;
import com.feisuo.sds.user.share.vo.MenuVo;
import com.feisuo.sds.user.test.SpringTest;

public class FunctionDaoTest extends SpringTest {

	@Autowired
	private IFunctionDao functionDao;

	@Test
	public void getCurrentUserList() {
		String[] uris = new String[] { "url", "url1", "url2" };
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", "0");
		params.put("uris", uris);
		try {
			List<MenuVo> list = functionDao.getMenuTree(params);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	
	}

	@Test
	public void getCurrentUserList1() {
		String[] uris = new String[] { "url1", "url2" };
		List<PrivilegeEntity> list = functionDao.getFunctionListByUris(uris);

		System.out.println(list);
	}
}