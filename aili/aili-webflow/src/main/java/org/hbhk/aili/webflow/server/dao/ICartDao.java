package org.hbhk.aili.webflow.server.dao;

import org.hbhk.aili.webflow.share.model.CartItem;
import org.springframework.data.repository.CrudRepository;


public interface ICartDao extends CrudRepository<CartItem, Integer>  {

}
