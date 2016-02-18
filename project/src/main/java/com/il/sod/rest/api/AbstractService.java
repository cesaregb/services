package com.il.sod.rest.api;

import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.util.RestUtil;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(
    tags = {
        @Tag(name = "clients", description = "Methods related to clients"),
		@Tag(name = "health", description = "Validate API + MODEL Healt")
    }
)
public abstract class AbstractService{
	
	protected ObjectMapper mapper;

	protected AbstractService() {
		mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	protected Response castEntityAsResponse(Object entity) throws SODAPIException {
		return castEntityAsResponse(entity, Response.Status.OK);
	}
	
	protected Response castEntityAsResponse(Object entity, Status status) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString()).status(status).build();
	}
	
	protected String castEntityAsString(Object entity) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return writer.toString();
	}
	
	protected Response genericResponse(boolean flag, String message) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, GeneralResponseMessage.getInstance().setStatus(flag));
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString())
				.status((flag) ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	protected Response genericResponse(boolean flag) throws SODAPIException {
		return genericResponse(flag, "Response");
	}
	protected Response genericResponse(String message) throws SODAPIException {
		return genericResponse(true, message);
	}
	
	protected <DTO> DTO getEntityfromJSON(String json, Class<DTO> entityClass) throws SODAPIException {
		try {
			if (json != null && json.trim().length() > 0) {
				return mapper.readValue(json, entityClass);
			} else {
				throw new SODAPIException(204,
						"No content to map to Object due to end of input. The JSON object provided is empty, it was expected an object.");
			}
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}
	
	protected <T> T getJsonISAsObject (InputStream is, Class<T> entityClass) throws SODAPIException {
		try {
			String content = RestUtil.getContentFromInputStream(is);
			T result = mapper.readValue(content, entityClass);
			
			return result;
		} catch (Exception e) {
			throw new SODAPIException(403,"No valid authentication", e);
		}
	}
}
