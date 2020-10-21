package com.bookstore.entity;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.bookstoredb.entity.Book;
import com.bookstoredb.entity.Review;

import static org.junit.Assert.assertEquals;

public class BookRatingTest {

	@Test
	public void testAverageRating1() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(5.0, averageRating, 0.0);
	}
	
	@Test
	public void testAverageRating2() {
		Book book = new Book();
		
		float averageRating = book.getAverageRating();
		
		assertEquals(0.0, averageRating, 0.0);
	}
	
	@Test
	public void testAverageRating3() {
		Book book = new Book();
		Set<Review> reviews = new HashSet<>();
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		Review review2 = new Review();
		review2.setRating(4);
		reviews.add(review2);
		
		Review review3 = new Review();
		review3.setRating(3);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(4.0, averageRating, 0.0);
	}
	
	@Test
	public void testRatingString1() {
		float averageRating = 0.0f;
		Book book = new Book();		
		String actual = book.getRatingString(averageRating);
		
		String expected = "off,off,off,off,off";
		assertEquals(expected, actual);
	}

	@Test
	public void testRatingString2() {
		float averageRating = 4.9f;
		Book book = new Book();		
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,on,on";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString3() {
		float averageRating = 4.8f;
		Book book = new Book();		
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,on,on";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString4() {
		float averageRating = 4.1f;
		Book book = new Book();		
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,on,off";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString5() {
		float averageRating = 4.3f;
		Book book = new Book();		
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,on,on,half";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString6() {
		float averageRating = 2.3f;
		Book book = new Book();		
		String actual = book.getRatingString(averageRating);
		
		String expected = "on,on,half,off,off";
		assertEquals(expected, actual);
	}
}

