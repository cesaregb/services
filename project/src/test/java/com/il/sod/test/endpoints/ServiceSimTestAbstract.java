package com.il.sod.test.endpoints;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.config.jersey.JacksonObjectMapperProvider;
import com.il.sod.db.dao.IDAO;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.util.RestUtil;
import com.il.sod.test.config.SpringTestConfiguration;

import ma.glasnost.orika.MapperFacade;

public class ServiceSimTestAbstract extends SpringTestConfiguration{
	
	protected ObjectMapper mapper;
	protected MapperFacade converter = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	
	@Autowired
	protected IDAO genericDaoImpl;
	
	protected ServiceSimTestAbstract() {
		mapper = JacksonObjectMapperProvider.MAPPER;
	}
	
	public Response castEntityAsResponse(Object entity) throws SODAPIException {
		return castEntityAsResponse(entity, Response.Status.OK);
	}
	
	public Response castEntityAsResponse(Object entity, Status status) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString()).status(status).build();
	}
	
	public String castEntityAsString(Object entity) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return writer.toString();
	}
	
	public Response genericResponse(boolean flag, String message) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, GeneralResponseMessage.getInstance().setStatus(flag));
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString())
				.status((flag) ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	public Response genericResponse(boolean flag) throws SODAPIException {
		return genericResponse(flag, "Response");
	}
	
	public Response genericResponse(String message) throws SODAPIException {
		return genericResponse(true, message);
	}
	
	public <DTO> DTO getEntityfromJSON(String json, Class<DTO> entityClass) throws SODAPIException {
		try {
			if (json != null && json.trim().length() > 0) {
				return mapper.readValue(json, entityClass);
			} else {
				throw new SODAPIException(Response.Status.NO_CONTENT,
						"No content to map to Object due to end of input. The JSON object provided is empty, it was expected an object.");
			}
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}
	
	public <T> T getJsonISAsObject (InputStream is, Class<T> entityClass) throws SODAPIException {
		try {
			String content = RestUtil.getContentFromInputStream(is);
			T result = mapper.readValue(content, entityClass);
			
			return result;
		} catch (Exception e) {
			throw new SODAPIException(Response.Status.UNAUTHORIZED,"No valid authentication", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEntity(JpaRepository<T, Integer> repository, Integer id){	
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
	
	@SuppressWarnings("unchecked")
	protected <T> T saveEntity(JpaRepository<T, Integer> repository, T entity){
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.create(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> boolean deleteEntity(JpaRepository<T, Integer> repository, Integer id) throws SODAPIException {
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.delete(id);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T updateEntity(JpaRepository<T, Integer> repository, T entity) throws SODAPIException{
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		gDao.update(entity);
		return entity;
	}   
}