package com.il.sod.rest.api;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.config.JacksonObjectMapperProvider;
import com.il.sod.db.dao.IDAO;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.util.RestUtil;

import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import ma.glasnost.orika.MapperFacade;

@SwaggerDefinition(
    tags = {
        @Tag(name = "clients", description = "Methods related to clients"),
		@Tag(name = "health", description = "Validate API + MODEL Healt")
    }
)
@Path("/v1")
public abstract class AbstractService{
	
	protected ObjectMapper mapper;
	
	protected MapperFacade converter = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	
	@Autowired
	protected IDAO genericDaoImpl;
	
	protected AbstractService() {
		mapper = JacksonObjectMapperProvider.MAPPER;
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
	
	@SuppressWarnings("unchecked")
	protected <T> T getEntity(JpaRepository<T, Integer> repository, Integer id){	
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		return gDao.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getEntityList(JpaRepository<T, Integer> repository){	
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		return gDao.findAll();
	}

	public IDAO getGenericDaoImpl() {
		return genericDaoImpl;
	}
}
