package com.bookstore.service;

import static com.bookstore.service.CommonUtility.forwardToPage;
import static com.bookstore.service.CommonUtility.showMessageBackend;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
import com.bookstoredb.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
	}
	
	public void listAllReviews() throws ServletException, IOException {
		listAllReviews(null);
	}
	
	public void listAllReviews(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		
		request.setAttribute("listReviews", listReviews);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		forwardToPage("review_list.jsp", request, response);
	}
	
	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		if(review != null) {
			request.setAttribute("review", review);
			forwardToPage("review_form.jsp", request, response);
		} else {
			String message = "Could not find review with ID " + reviewId;
			showMessageBackend(message, request, response);
		}
		
	
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = reviewDAO.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);
		
		String message = "The review has been updated successfully.";
		listAllReviews(message);
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		if(review != null) {	
			reviewDAO.delete(reviewId);
			String message = "The review has been deleted successfully.";
			listAllReviews(message);
		} else {
			String message = "Could not find review with ID " + reviewId +
					" or it might have been deleted already by another admin";
			showMessageBackend(message, request, response);
		}
	}
}
