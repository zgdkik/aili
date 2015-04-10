package org.hbhk.aili.webflow.server.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.webflow.server.service.ICart;
import org.hbhk.aili.webflow.share.model.CartItem;
import org.hbhk.aili.webflow.share.model.Product;
import org.springframework.stereotype.Service;
@Service
public class Cart implements ICart,Serializable {

	private static final long serialVersionUID = 9099554025362726284L;
	private Map<Long, CartItem> map = new HashMap<Long, CartItem>();
	

	public List<CartItem> getItems() {
		return new ArrayList<CartItem>(map.values());
	}

	public void addItem(Product product) {
		Long id = product.getId();
		CartItem item = map.get(id);
		CartItem cartItem = new CartItem();
		if (item != null){
			cartItem.setQuantity(item.getQuantity()+1);
			item.increaseQuantity();
		}else{
			map.put(id, new CartItem(product, 1));
			cartItem.setQuantity(1);
		}
	
	}

	public int getTotalPrice() {
		int total = 0;
		for (CartItem item : map.values())
			total += item.getProduct().getPrice() * item.getQuantity();
		return total;
	}
}