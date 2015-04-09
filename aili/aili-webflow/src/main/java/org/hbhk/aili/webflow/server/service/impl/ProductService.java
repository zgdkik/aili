package org.hbhk.aili.webflow.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.webflow.server.dao.ProductDao;
import org.hbhk.aili.webflow.server.service.IProductService;
import org.hbhk.aili.webflow.share.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

	private Map<Integer, Product> products = new HashMap<Integer, Product>();

	@Autowired
	private ProductDao productDao;
	
	public ProductService() {
		products.put(1, new Product(1, "Bulldog", 1000));
		products.put(2, new Product(2, "Chihuahua", 1500));
		products.put(3, new Product(3, "Labrador", 2000));
	}

	@Override
	public List<Product> getProducts() {
		Iterator<Product> list = productDao.findAll().iterator();
		List<Product> products =  new ArrayList<Product>() ;
		while (list.hasNext()) {
			products.add(list.next());
		} 
		return products;
	}

	@Override
	public Product getProduct(int productId) {
		return productDao.findOne(productId);
	}

}
