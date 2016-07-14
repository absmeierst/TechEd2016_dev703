package com.sap.teched;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sap.teched.cds.PurchaseOrder.Header;
import com.sap.teched.cds.PurchaseOrder.Item;

@Stateless
public class POService {

	@PersistenceContext(name = "dev703-java")
	private EntityManager em;

	public List<Header> findAllHeaders() {
		TypedQuery<Header> query = em.createQuery("SELECT a FROM Header a", Header.class);
		return query.getResultList();
	}
}