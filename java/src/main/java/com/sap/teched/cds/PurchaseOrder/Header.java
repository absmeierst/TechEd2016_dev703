package com.sap.teched.cds.PurchaseOrder;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "\"PurchaseOrder.Header\"")
public class Header implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "\"PURCHASEORDERID\"", length = 10, nullable = false)
	private String id;

	@OneToOne
	@JoinColumn(name = "\"ITEMS.PURCHASEORDERID\"", referencedColumnName = "\"PURCHASEORDERID\"")
	private Item item;

	@Column(name = "\"PARTNER\"", length = 10)
	private String partner;

	public Header() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPartner() {
		return partner;
	}
	
}