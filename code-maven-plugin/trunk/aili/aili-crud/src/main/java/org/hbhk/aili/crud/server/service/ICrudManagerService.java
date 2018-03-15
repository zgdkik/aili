package org.hbhk.aili.crud.server.service;

import org.hbhk.aili.base.server.service.IBaseService;
import org.hbhk.aili.crud.share.entity.ColumnEntity;
import org.hbhk.aili.crud.share.entity.Condition;
import org.hbhk.aili.crud.share.entity.CrudEntity;

public interface ICrudManagerService extends IBaseService<CrudEntity, String> {

	void edit(CrudEntity report, ColumnEntity[] cols, Condition[] cdts);
}
