package org.hbhk.zlj.backend.server.service;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.zlj.backend.share.entity.ColumnEntity;
import org.hbhk.zlj.backend.share.entity.Condition;
import org.hbhk.zlj.backend.share.entity.CrudEntity;

public interface ICrudManagerService extends IBaseService<CrudEntity, String> {

	void edit(CrudEntity report, ColumnEntity[] cols, Condition[] cdts);
}
