package org.hbhk.aili.webflow.server.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.webflow.server.dao.ICartDao;
import org.hbhk.aili.webflow.server.dao.ProductDao;
import org.hbhk.aili.webflow.server.service.IProductService;
import org.hbhk.aili.webflow.share.model.CartItem;
import org.hbhk.aili.webflow.share.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService, Serializable {

	private static final long serialVersionUID = -7979955893857559254L;

	private Map<Integer, Product> products = new HashMap<Integer, Product>();

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ICartDao dao;

	public ProductService() {
		products.put(1, new Product(1, "Bulldog", 1000));
		products.put(2, new Product(2, "Chihuahua", 1500));
		products.put(3, new Product(3, "Labrador", 2000));
	}

	@Override
	public List<Product> getProducts() {
		List<Product> list = productDao.get(null);
		return list;
	}

	@Override
	public Product getProduct(int productId) {
		return productDao.getById(productId);
	}

	@Override
	public void save(List<CartItem> cartItems) {
		for (CartItem cartItem : cartItems) {
			dao.insert(cartItem);
		}
		
	}
}
