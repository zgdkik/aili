package org.hbhk.aili.webflow.server.service;

import java.util.List;

import org.hbhk.aili.webflow.share.model.Product;

public interface IProductService {

	List<Product> getProducts();

	Product getProduct(int productId);

}
