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

	@Column(name = "\"HISTORY.CREATEDBY\"", length = 10)
	private String created_by;

	@Column(name = "\"HISTORY.CREATEDAT\"", length = 256)
	private String last_name;

	@Column(name = "\"address\"", length = 256)
	private String address;

	@Column(name = "\"city\"", length = 256)
	private String city;

	@Column(name = "\"country\"", length = 256)
	private String country;

	@Column(name = "\"zip\"", length = 10)
	private String zip;

	@Column(name = "\"phone\"", length = 25)
	private String phone;

	@Column(name = "\"email\"", length = 50)
	private String email;

	@Column(name = "\"web\"", length = 50)
	private String web;

	public Address() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getWeb() {
		return web;
	}
}