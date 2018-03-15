package com.feisuo.sds.user.test.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feisuo.sds.user.server.dao.IUserDao;
import com.feisuo.sds.user.share.vo.CurrentUserVo;
import com.feisuo.sds.user.test.SpringTest;

public class UserDaoTest extends SpringTest {

	@Autowired
	private IUserDao userDao;
	
	@Test
	public void  getCurrentUserList(){
		List<CurrentUserVo> list =  userDao.getCurrentUserList("hbhk");
		
		System.out.println(list);
		if(list!=null){
			for (CurrentUserVo c : list) {
				System.out.println(c.getUserName() +"-"+ c.getFunctionUris());
			}
		}
	}
	
	@Test
	public void  getCurrentUserList1(){
		 userDao.getPage(new HashMap<String, Object>(), 1, 2);
		
	}
}
