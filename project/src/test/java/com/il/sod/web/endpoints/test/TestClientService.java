package com.il.sod.web.endpoints.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.web.endpoints.BaseTestServices;
import com.jayway.restassured.http.ContentType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class TestClientService extends BaseTestServices{
	
	@BeforeClass
	public static void setup() {
	}
	
	@AfterClass
	public static void teardown() {
	}
	
	@Test
    public void testInsert(){
		try{
			String json = getJsonFileContent("client.json");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonParsed = mapper.readTree(json);
			
			String baseUrl = getBasepath();
			given().log().all().
			contentType(ContentType.JSON).body(jsonParsed.get("insert").toString()).
	    	when(). 
	        	post(baseUrl + "/v1/publish-batch").
	    	then().log().all().
	    		statusCode(201);
			
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}
}
