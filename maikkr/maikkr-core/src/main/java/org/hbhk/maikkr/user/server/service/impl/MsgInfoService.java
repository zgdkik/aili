package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IMsgInfoDao;
import org.hbhk.maikkr.user.server.service.IMsgInfoService;
import org.hbhk.maikkr.user.share.pojo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * MsgInfoManager
 * 
 * @author 何波
 * 
 */
@Transactional
@Service("mService")
public class MsgInfoService implements IMsgInfoService {

	@Autowired
	private IMsgInfoDao msgInfoDao;

	public MsgInfo save(MsgInfo model) {
		model.setId(UUIDUitl.getUuid());
		model.setCreateTime(new Date());
		model.setIsRead(0);
		String user = UserContext.getCurrentContext().getCurrentUserName();
		model.setSendUser(user);
		model.setCreatUser(user);
		return msgInfoDao.save(model);
	}

	public MsgInfo update(MsgInfo model) {
		String user = UserContext.getCurrentContext().getCurrentUserName();
		model.setUpdateTime(new Date());
		model.setUpdateUser(user);
		return msgInfoDao.update(model);
	}

	public MsgInfo getOne(MsgInfo model) {
		return msgInfoDao.getOne(model);
	}

	public List<MsgInfo> get(MsgInfo model) {
		return msgInfoDao.get(model);
	}

	public List<MsgInfo> get(MsgInfo model, Page page) {
		return msgInfoDao.get(model, page);
	}

}
