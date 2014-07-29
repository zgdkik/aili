package org.hbhk.maikkr.backend.server.service.impl;

import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.springframework.stereotype.Service;

/**
 * Service实现类开发规范 1.必须实现自己模块下的Service接口 2.类名必须以Service结尾
 * 3.只允许注入自己模块下的Dao,禁止注入其他模块的Dao,要访问其他模块必须通过Service去访问
 * 4.类或方法上必须标记org.springframework.transaction.annotation.Transactional @Transactional
 * 事务注解
 */
@Service
public class AdminService implements IAdminService {

	public AdminInfo get(AdminInfo admin) {
		return null;
	}

}