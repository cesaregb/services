package oracle.doceng.selfpub.registration.services;

import static com.jayway.restassured.RestAssured.given;

import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;

public class TestPublishService extends TestServicesBase{
	
	@BeforeClass
	public static void setup() {
	}
	
	@AfterClass
	public static void teardown() {
	}
	
	@Test
    public void testPublishSdl(){
		try{
			String json = getJsonFileContent("publish-batch.json");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonParsed = mapper.readTree(json);
			
			String baseUrl = getBaseUrl();
			given().log().all().
			contentType(ContentType.JSON).body(jsonParsed.get("correct").toString()).
	    	when(). 
	        	post(baseUrl + "/v1/publish-batch").
	    	then().log().all().
	    		statusCode(201);
			
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}
	
	@Test
	public void testFailMetadataPublishSdl(){
		try{
			String json = getJsonFileContent("publish-batch.json");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonParsed = mapper.readTree(json);
			String baseUrl = getBaseUrl();
			
			given().log().all().
				contentType(ContentType.JSON).body(jsonParsed.get("incorrect_metadata").toString()).
			when(). 
				post(baseUrl + "/v1/publish-batch").
			then().log().all().
				statusCode(Response.Status.NO_CONTENT.getStatusCode());
			
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}
	
	@Test
	public void testFailPubVersion(){
		try{
			String json = getJsonFileContent("publish-batch.json");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonParsed = mapper.readTree(json);
			String baseUrl = getBaseUrl();
			
			given().log().all().
				contentType(ContentType.JSON).body(jsonParsed.get("incorrect_pubversion").toString()).
			when(). 
				post(baseUrl + "/v1/publish-batch").
			then().log().all().
				statusCode(Response.Status.NO_CONTENT.getStatusCode());
			
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("Error: " + e.getMessage());
		}
	}
}
