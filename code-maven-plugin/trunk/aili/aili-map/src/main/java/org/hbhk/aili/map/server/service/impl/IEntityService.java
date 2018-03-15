package org.hbhk.aili.map.server.service.impl;

import org.hbhk.aili.map.share.entity.Entity;

public interface IEntityService {

	void add(Entity entity);
	
	void delete(Entity entity);
	void update(Entity update);
	
}
