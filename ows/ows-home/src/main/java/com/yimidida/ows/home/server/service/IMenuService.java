package com.yimidida.ows.home.server.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.Html;
import com.yimidida.ows.home.share.vo.MenuVo;
import com.yimidida.ows.user.share.entity.PrivilegeEntity;

public interface IMenuService {
	MenuVo getMenuVo(List<PrivilegeEntity> list);
}
