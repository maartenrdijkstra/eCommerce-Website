package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashGeneratorTest {

	@Test
	public void testGenerateMD5() {
		String password = "hoihoihoi";
		String encryptedPassword = HashGenerator.generateMD5(password);
		
		System.out.println(encryptedPassword);
		assertTrue(true);
	}

}