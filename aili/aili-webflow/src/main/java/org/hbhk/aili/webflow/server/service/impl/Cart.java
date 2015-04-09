package org.hbhk.aili.webflow.server.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.webflow.server.dao.ICartDao;
import org.hbhk.aili.webflow.share.model.CartItem;
import org.hbhk.aili.webflow.share.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class Cart implements Serializable {

	private static final long serialVersionUID = 7901330827203016310L;
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
	@Autowired
	private ICartDao cartDao;

	public List<CartItem> getItems() {
		return new ArrayList<CartItem>(map.values());
	}

	public void addItem(Product product) {
		int id = product.getId();
		CartItem item = map.get(id);
		CartItem cartItem = new CartItem();
		cartItem.setPid(id);
		if (item != null){
			cartItem.setQuantity(item.getQuantity()+1);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", id);
			List<CartItem> cis = cartDao.get(params);
			cartItem.setId(cis.get(0).getId());
			cartDao.update(cartItem);
			item.increaseQuantity();
		}else{
			map.put(id, new CartItem(product, 1));
			cartItem.setQuantity(1);
			cartDao.insert(cartItem);
		}
	
	}

	public int getTotalPrice() {
		int total = 0;
		for (CartItem item : map.values())
			total += item.getProduct().getPrice() * item.getQuantity();
		return total;
	}
}