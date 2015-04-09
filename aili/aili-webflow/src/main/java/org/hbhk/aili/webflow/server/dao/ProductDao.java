package org.hbhk.aili.webflow.server.dao;

import org.hbhk.aili.webflow.share.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao  extends CrudRepository<Product, Integer>  {

}
