package org.hbhk.aili.workflow.server.service;

import java.util.List;

import org.hbhk.aili.workflow.share.model.CartItem;
import org.hbhk.aili.workflow.share.model.Product;

public interface IProductService {

	List<Product> getProducts();

	Product getProduct(int productId);
	
	void save(List<CartItem> cartItems);
}
