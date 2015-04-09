package org.hbhk.aili.webflow.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_aili_cart_item")
public class CartItem extends BaseModel { 
    private static final long serialVersionUID = 8388627124326126637L; 
    private Product product; 
    @Column("quantity")
    private int quantity; 
    
    @Column("pid")
    private Integer pid; 
    
    public CartItem() {
	}

    public CartItem(Product product, int quantity) { 
        this.product = product; 
        this.quantity = quantity; 
    } 

    public int getTotalPrice() { 
        return this.quantity * this.product.getPrice(); 
    } 

    public void increaseQuantity() { 
        this.quantity++; 
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	} 
    
	
}