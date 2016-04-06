package com.il.sod.services.test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestCreateSimpleState {
	
	@BeforeClass
	public static void setup() {
	}
	@AfterClass
	public static void teardown() {
	}
	
	@Test
    public void testGet() {
		String path = "http://localhost:8080/api";
		String localPath = path + "/app-orders/orderTypes";
		System.out.println("Testing: " + localPath);
		given()
			.contentType("application/json")
			.when().get(localPath)
			.then().statusCode(200);
		System.out.println("After");
	}
	
	public static void main(String ... args){
		TestCreateSimpleState t = new TestCreateSimpleState();
		t.testGet();
	}
}
