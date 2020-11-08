package com.bookstore.controller.admin.order;

import static com.bookstore.service.CommonUtility.forwardToPage;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstoredb.entity.Book;
import com.bookstoredb.entity.BookOrder;
import com.bookstoredb.entity.OrderDetail;

@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
		
		float subtotal = quantity * book.getPrice();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(book);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subtotal);
		
		float newTotal = order.getTotal() + subtotal;
		order.setTotal(newTotal);
		
		boolean isBookAlreadyInOrder = false;
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		for (OrderDetail od : orderDetails) {
			if (od.getBook().equals(book)) {
				isBookAlreadyInOrder = true;
				od.setQuantity(od.getQuantity() + quantity);
				od.setSubtotal(od.getSubtotal() + subtotal);
				break;
			}
		}
		
		if (!isBookAlreadyInOrder) {
			orderDetails.add(orderDetail);
		}
		
		request.setAttribute("book", book);
		session.setAttribute("NewBookPendingToAddToOrder", true);
		
		forwardToPage("add_book_result.jsp", request, response);
	}

}
