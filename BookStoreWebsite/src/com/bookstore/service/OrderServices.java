package com.bookstore.service;

import static com.bookstore.service.CommonUtility.forwardToPage;
import static com.bookstore.service.CommonUtility.showMessageBackend;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstoredb.entity.Book;
import com.bookstoredb.entity.BookOrder;
import com.bookstoredb.entity.Customer;
import com.bookstoredb.entity.OrderDetail;

public class OrderServices {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDAO orderDAO;
	
	
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}



	public void listAllOrder() throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		
		request.setAttribute("listOrder", listOrder);
		
		forwardToPage("order_list.jsp", request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		
		BookOrder order = orderDAO.get(orderId);
		
		if(order != null) {
			request.setAttribute("order", order);
			forwardToPage("order_detail.jsp", request, response);
			
		} else {
			String message = "Could not find order with ID " + orderId;
			showMessageBackend(message, request, response);
		}
	}

	public void showCheckoutForm() throws ServletException, IOException {
		forwardToPage("frontend/checkout.jsp", request, response);	
	}



	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		String shippingAddress = address + ", " + city + ", " + zipcode + ", " + country;
		
		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getItems();
		
		Iterator<Book> iterator = items.keySet().iterator();
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		
		while(iterator.hasNext()) {
			Book book = iterator.next();
			Integer quantity = items.get(book);
			float subtotal = quantity * book.getPrice();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail);
		}
		
		order.setOrderDetails(orderDetails);
		order.setTotal(shoppingCart.getTotalAmount());
		
		orderDAO.create(order);
		
		shoppingCart.clear();
		
		String message = "Thank you. Your order has been received." +
		" We will deliver your book within a few days.";	
		request.setAttribute("message", message);
	
		forwardToPage("frontend/message.jsp", request, response);
	}

}
