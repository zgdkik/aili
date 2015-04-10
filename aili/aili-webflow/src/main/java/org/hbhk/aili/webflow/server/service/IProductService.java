package org.hbhk.aili.webflow.server.service;

import java.util.List;

import org.hbhk.aili.webflow.share.model.CartItem;
import org.hbhk.aili.webflow.share.model.Product;

public interface IProductService {

	List<Product> getProducts();

	Product getProduct(int productId);
	
	void save(List<CartItem> cartItems);
}
