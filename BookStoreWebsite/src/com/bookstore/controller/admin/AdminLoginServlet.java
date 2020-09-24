package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.UserServices;


@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices userServices = new UserServices(entityManager, request, response);
		
		System.out.println("--- doPost() method of AdminLoginServlet invoked ---");
		
		userServices.login();
		
	}

}
