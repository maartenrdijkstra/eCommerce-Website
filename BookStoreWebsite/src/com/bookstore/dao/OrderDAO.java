package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstoredb.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("Processing");
		return super.create(order);
	}

	@Override
	public BookOrder update(BookOrder order) {
		return super.update(order);
	}

	@Override
	public BookOrder get(Object orderId) {
		return super.find(BookOrder.class, orderId);
	}

	@Override
	public void delete(Object orderId) {
		super.delete(BookOrder.class, orderId);
		
	}

	@Override
	public List<BookOrder> listAll() {
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("BookOrder.countAll");
	}

}
