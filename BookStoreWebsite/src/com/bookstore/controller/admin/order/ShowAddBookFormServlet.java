package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstoredb.entity.Book;

import static com.bookstore.service.CommonUtility.forwardToPage;

@WebServlet("/admin/add_book_form")
public class ShowAddBookFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO();
		List<Book> listBook = bookDAO.listAll();
		request.setAttribute("listBook", listBook);
		
		forwardToPage("add_book_form.jsp", request, response);
	}
}
