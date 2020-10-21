package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstoredb.entity.Book;
import com.bookstoredb.entity.Customer;
import com.bookstoredb.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}
	

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(4);
		
		Customer customer = new Customer();
		customer.setCustomerId(11);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("Excellent book!");
		review.setRating(3);
		review.setComment("A comprehensive book about a lot of things really valuable about Spring Framework. I jolly like this book! Yippee Ay Ay! Yeeee haaaa! Again, an Excellent book! I absolutely recommend it. To the last word!");
		
		Review savedReview = reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testGet() {
		Integer reviewId = 1;
		Review review = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId = 1;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Excellent book");
		
		Review updatedReview = reviewDao.update(review);
		
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
	}

	@Test
	public void testDeleteReview() {
		int reviewId = 2;
		reviewDao.delete(reviewId);
		Review review = reviewDao.get(reviewId);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
		for (Review review : listReview) {
			System.out.println(review.getReviewId() + " - " + review.getBook().getTitle()
					+ " - " + review.getCustomer().getFullname()
					+ " - " + review. getHeadline()
					+ " - " + review.getRating());
		}
		
		assertTrue(listReview.size() > 0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		System.out.println("Total Reviews: " + totalReviews);
		assertTrue(totalReviews > 0);
	}

}
