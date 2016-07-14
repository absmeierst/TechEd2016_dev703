package com.sap.teched.cds.PurchaseOrder;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "\"PurchaseOrder.Item\"")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "\"PURCHASEORDERID\"", length = 10, nullable = false)
	private String id;

	@Id
	@Column(name = "\"PURCHASEORDERITEM\"", length = 10, nullable = false)
	private String itemId;

	@Column(name = "\"PRODUCT\"", length = 10)
	private String product;

	public Item() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProduct() {
		return product;
	}
}