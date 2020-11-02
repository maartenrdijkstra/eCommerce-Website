package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstoredb.entity.Book;
import com.bookstoredb.entity.BookOrder;
import com.bookstoredb.entity.Customer;
import com.bookstoredb.entity.OrderDetail;

public class OrderDAOTest {

	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(7);
		
		order.setCustomer(customer);
		order.setRecipientName("David");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("123 South Street, New York, USA");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(2);
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(81.9f);
		orderDetail.setBookOrder(order);

		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		BookOrder savedOrder = orderDAO.create(order);
		
		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);
	}

	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(11);
		
		order.setCustomer(customer);
		order.setRecipientName("Nam Ha Minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("123 South Street, Saigon, Vietnam");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Book book1 = new Book(10);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(57.58f);
		orderDetail1.setBookOrder(order);

		orderDetails.add(orderDetail1);

		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0);
	}
	
	@Test
	public void testUpdateBookOrderShippingAddress() {
		Integer orderId = 8;
		BookOrder order = orderDAO.get(orderId);
		order.setShippingAddress("New Shipping Address");
		
		orderDAO.update(order);
		
		BookOrder updatedOrder = orderDAO.get(orderId);
		assertEquals(order.getShippingAddress(), updatedOrder.getShippingAddress());
	}

	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 9;
		BookOrder order = orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getBook().getBookId() == 3) {
				orderDetail.setQuantity(3);
				orderDetail.setSubtotal(122.85f);
			}
			
		}
				
		orderDAO.update(order);

		int expectedQuantity = 3;
		float expectedSubtotal = 122.85f;
		
		int actualQuantity = 0;
		float actualSubtotal = 0.0f;
		
		iterator = order.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getBook().getBookId() == 3) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
			
		}
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}
	
	@Test
	public void testGet() {
		Integer orderId = 8;
		BookOrder order = orderDAO.get(orderId);
		System.out.println(order.getRecipientName());
		System.out.println(order.getRecipientPhone());
		System.out.println(order.getShippingAddress());
		System.out.println(order.getStatus());
		System.out.println(order.getTotal());
		System.out.println(order.getPaymentMethod());
	
		assertEquals(1, order.getOrderDetails().size());
	}

	@Test
	public void testDeleteOrder() {
		int orderId = 8;
		orderDAO.delete(orderId);
		
		BookOrder order = orderDAO.get(orderId);
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listOrders = orderDAO.listAll();
		
		for(BookOrder order : listOrders) {
			System.out.println(order.getOrderId() + " - " + order.getCustomer().getFullname() + 
					" - " + order.getStatus());
			for(OrderDetail detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubtotal();
				System.out.println("\t" + book.getTitle() + " - " + quantity + " - " + subtotal);
			}
		}
		
		assertTrue(listOrders.size() > 0);
	}

	@Test
	public void testCount() {
		long totalOrders = orderDAO.count();
		assertEquals(2, totalOrders);
	}

}
