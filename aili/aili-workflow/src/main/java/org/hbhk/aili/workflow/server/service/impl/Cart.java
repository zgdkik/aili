package org.hbhk.aili.workflow.server.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.workflow.server.service.ICart;
import org.hbhk.aili.workflow.share.model.CartItem;
import org.hbhk.aili.workflow.share.model.Product;
public class Cart implements ICart,Serializable {

	private static final long serialVersionUID = 9099554025362726284L;
	private Map<Long, CartItem> map = new HashMap<Long, CartItem>();
	

	public List<CartItem> getItems() {
		return new ArrayList<CartItem>(map.values());
	}

	public void addItem(Product product) {
		Long id = product.getId();
		CartItem item = map.get(id);
		if (item != null){
			item.setQuantity(item.getQuantity()+1);
			item.increaseQuantity();
		}else{
			CartItem cartItem = new CartItem(product,1);
			cartItem.setPid(product.getId());
			cartItem.setQuantity(1);
			map.put(id, cartItem);
		}
	
	}

	public int getTotalPrice() {
		int total = 0;
		for (CartItem item : map.values())
			total += item.getProduct().getPrice() * item.getQuantity();
		return total;
	}

	
}