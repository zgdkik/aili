/**
 * IEnpService.java
 * Created at 2016年1月6日
 * Created by lixiang
 * Copyright (C) 2016 NO.1 VAN, All rights reserved.
 */
package com.feisuo.sds.common.server.service;

import java.util.List;
import com.feisuo.sds.common.share.vo.EnpInfoVo;

/**
 * ClassName: IEnpService
 * Description: TODO
 * Author: lixiang
 * Date: 2016年1月6日
 */
public interface IEnpService {
	List<EnpInfoVo> findByEnpName(String enpName);
}
