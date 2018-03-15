/**
 * EnpService.java
 * Created at 2016年1月6日
 * Created by lixiang
 * Copyright (C) 2016 NO.1 VAN, All rights reserved.
 */
package com.feisuo.sds.common.server.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.feisuo.sds.common.server.dao.IEnpDao;
import com.feisuo.sds.common.server.service.IEnpService;
import com.feisuo.sds.common.share.vo.EnpInfoVo;

/**
 * ClassName: EnpService
 * Description: TODO
 * Author: lixiang
 * Date: 2016年1月6日
 */
@Service
@Transactional
public class EnpService implements IEnpService{
	@Autowired
	IEnpDao enpDao;

	/**
	 * Description: TODO
	 * @param subName
	 * @return
	 * Created by lixiang 2016年1月6日
	 */
	@Override
	public List<EnpInfoVo> findByEnpName(String enpName) {
		if (enpName != null && !"".equals(enpName)) {
			return this.enpDao.findByEnpName(enpName);
		}
		return null;
	}
}
