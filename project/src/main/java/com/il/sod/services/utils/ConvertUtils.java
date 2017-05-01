package com.il.sod.services.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.util.RestUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.InputStream;
import java.io.StringWriter;

public abstract class ConvertUtils {

	protected static ObjectMapper mapper = new ObjectMapper();
	protected MapperFacade converter = BaseMapper.MAPPER_FACTORY.getMapperFacade();

	public static Response castEntityAsResponse(Object entity) throws SODAPIException {
		return ConvertUtils.castEntityAsResponse(entity, Status.OK);
	}

	public static ResponseEntity castEntityAsResponse(Object entity, HttpStatus status) throws SODAPIException {
		return new ResponseEntity<>(entity, status);
	}

	@Deprecated
	public static Response castEntityAsResponse(Object entity, Status status) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString()).status(status).build();
	}

	public static String castEntityAsString(Object entity) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return writer.toString();
	}

	public static Response genericResponse(boolean flag, String message) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, new GeneralResponseMessage(flag, message));
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString())
				.status((flag) ? Status.OK : Status.INTERNAL_SERVER_ERROR).build();
	}

	public static Response genericResponse(boolean flag) throws SODAPIException {
		return genericResponse(flag, "Response");
	}

	public static Response genericResponse(String message) throws SODAPIException {
		return genericResponse(true, message);
	}

	public static <DTO> DTO getEntityfromJSON(String json, Class<DTO> entityClass) throws SODAPIException {
		try {
			if (json != null && json.trim().length() > 0) {
				return mapper.readValue(json, entityClass);
			} else {
				throw new SODAPIException(Status.NO_CONTENT,
						"No content to map to Object due to end of input. The JSON object provided is empty, it was expected an object.");
			}
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	public static <T> T getJsonISAsObject (InputStream is, Class<T> entityClass) throws SODAPIException {
		try {
			String content = RestUtil.getContentFromInputStream(is);
			T result = mapper.readValue(content, entityClass);

			return result;
		} catch (Exception e) {
			throw new SODAPIException(Status.UNAUTHORIZED,"No valid authentication", e);
		}
	}
	
}
