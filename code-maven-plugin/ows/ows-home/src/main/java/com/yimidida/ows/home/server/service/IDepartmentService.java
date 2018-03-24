package com.yimidida.ows.home.server.service;


import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.Department;

/**
 * 省 接口
 * @author zhangm
 *
 */
public interface IDepartmentService extends IBaseService<Department, String> {
	String getDepartmentName(String deptCode);

}
