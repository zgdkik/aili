package org.activiti.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.demo.dao.CommonDao;
import org.activiti.demo.domain.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @Title: SysUserServiceImpl.java
 * @Description: org.activiti.demo.service
 * @Package org.activiti.demo.service
 * @author hncdyj123@163.com
 * @date 2013-3-7
 * @version V1.0
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Resource(name = "commonDao")
	private CommonDao commonDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void insertSysUser(SysUser sysUser) throws Exception {
		commonDao.insertObject(sysUser);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void updateSysUser(SysUser sysUser) throws Exception {
		commonDao.updateObject(sysUser);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void deleteSysUser(SysUser sysUser) throws Exception {
		commonDao.deleteObject(sysUser);
	}

	@Override
	public List<SysUser> listSysUser(SysUser sysUser) throws Exception {
		return commonDao.listObject(sysUser);
	}

	@Override
	public Integer listSysUserCount(SysUser sysUser) throws Exception {
		return commonDao.listObjectCount(sysUser);
	}

	@Override
	public SysUser findSysUserByID(SysUser sysUser) throws Exception {
		return (SysUser) commonDao.findObject(sysUser);
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}
