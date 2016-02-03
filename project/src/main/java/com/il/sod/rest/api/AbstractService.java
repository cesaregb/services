package com.il.sod.rest.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.soap.AddressingFeature.Responses;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.util.RestUtil;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(
    tags = {
        @Tag(name = "publications", description = "Metadata about a currently managed Publications in our pipelines"),
        @Tag(name = "publish-environments", description = "all Self-Publish target environments must be registered here"),
        @Tag(name = "promotion", description = "manage publications for a particular environment"),
        @Tag(name = "pub-registry", description = " Used by Publication systems to register new publications. "
        		+ "All publications visible to the GO Portal must have been registered with an SSOT-valid Product release"),
		@Tag(name = "health", description = "Validate API + MODEL Healt")
    }
)
public abstract class AbstractService{
	
	protected ObjectMapper mapper;

	protected AbstractService() {
		mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	protected Response getEntityAsJSON(Object entity) throws SODAPIException {
		return getEntityAsJSON(entity, Response.Status.ACCEPTED);
	}
	
	protected Response getEntityAsJSON(Object entity, Status status) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (JsonGenerationException e) {
			throw new SODAPIException(e);
		} catch (JsonMappingException e) {
			throw new SODAPIException(e);
		} catch (IOException e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString()).status(status).build();
	}
	
	protected <DTO> DTO getEntityfromJSON(String json, Class<DTO> entityClass) throws SODAPIException {
		try {
			if (json != null && json.trim().length() > 0) {
				return mapper.readValue(json, entityClass);
			} else {
				throw new SODAPIException(204,
						"No content to map to Object due to end of input. The JSON object provided is empty, it was expected an object.");
			}
		} catch (JsonParseException e) {
			throw new SODAPIException(e);
		} catch (JsonMappingException e) {
			throw new SODAPIException(e);
		} catch (IOException e) {
			throw new SODAPIException(e);
		}
	}
	
	protected <T> T getAuthObject (InputStream is, Class<T> entityClass) throws SODAPIException {
		T entity = null;
		try {
			String content = RestUtil.getContentFromInputStream(is);
			// we need to create JSON, get the auth token and create the object. 
			JsonReader jsonReader = Json.createReader(new StringReader(content));
		    JsonObject object = jsonReader.readObject();
		    jsonReader.close();
		    String token = null;
		    if (object != null && object.containsKey("token") &&  object.getString("token") != null){
		    	token = object.getString("token");
		    }
			// validate token  
		    if (token == null || token != "1"){
		    	throw new Exception("Token not valid");
		    }else{
		    	String dto = token = object.getString("object");
		    	entity = getEntityfromJSON(dto, entityClass);
		    }
			return entity;
		} catch (Exception e) {
			throw new SODAPIException(
					403,"No valid authentication", e);
		}
	}
	
}
