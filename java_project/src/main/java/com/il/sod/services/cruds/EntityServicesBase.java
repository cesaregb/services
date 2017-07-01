package com.il.sod.services.cruds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.config.jersey.JacksonObjectMapperProvider;
import com.il.sod.db.dao.IDAO;
import com.il.sod.db.model.entities.SoftDeleteEntity;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.services.utils.ConvertUtils;
import com.il.sod.services.utils.ServicesDBHelper;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class EntityServicesBase {
  final static Logger ABS_LOGGER = LoggerFactory.getLogger(EntityServicesBase.class);

  protected ObjectMapper mapper;
  public ServicesDBHelper serviceDbHelper;
  protected MapperFacade converter = BaseMapper.MAPPER_FACTORY.getMapperFacade();

  @Autowired
  private IDAO genericDaoImpl;

  protected EntityServicesBase() {
    mapper = JacksonObjectMapperProvider.MAPPER;
    serviceDbHelper = new ServicesDBHelper(this);
  }

  @SuppressWarnings("unchecked")
  public <T> T getEntity(JpaRepository<T, Integer> repository, Integer id) {
    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    gDao.setRepository(repository);
    return gDao.findById(id);
  }

  @SuppressWarnings("unchecked")
  protected <T> List<T> getEntityList(JpaRepository<T, Integer> repository) {
    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    gDao.setRepository(repository);
    return gDao.findAll();
  }

  @SuppressWarnings("unchecked")
  protected <T> List<T> getEntityListActive(Class<T> clazz, JpaRepository<T, Integer> repository) {
    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    if (SoftDeleteEntity.class.isAssignableFrom(clazz)) {
      gDao.setRepository(repository);
      return gDao.findAllActive();
    } else {
      return null;
    }
  }

  /* ********* Mutations *********** */
  @SuppressWarnings("unchecked")
  protected <T> T saveEntity(JpaRepository<T, Integer> repository, T entity) {
    try {
      ABS_LOGGER.info("Save {}", ConvertUtils.castEntityAsString(entity));
    } catch (SODAPIException e) {
    }
    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    gDao.setRepository(repository);
    entity = gDao.create(entity);
    return entity;
  }

  @SuppressWarnings("unchecked")
  protected <T> boolean deleteEntity(JpaRepository<T, Integer> repository, Integer id) throws SODAPIException {
    ABS_LOGGER.info("Delete " + id);
    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    gDao.setRepository(repository);
    return gDao.delete(id);
  }

  @SuppressWarnings("unchecked")
  protected <T> boolean softDeleteEntity(JpaRepository<T, Integer> repository, Integer id) throws SODAPIException {
    ABS_LOGGER.info("SOFT Delete " + id);
    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    gDao.setRepository(repository);
    gDao.sofDelete(id);
    return true;
  }

  @SuppressWarnings("unchecked")
  protected <T> T updateEntity(JpaRepository<T, Integer> repository, T entity) {
    try {
      ABS_LOGGER.info("Update {}", ConvertUtils.castEntityAsString(entity));
    } catch (SODAPIException e) {
    }

    IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
    gDao.setRepository(repository);
    entity = gDao.update(entity);
    return entity;
  }


  public IDAO getGenericDaoImpl() {
    return genericDaoImpl;
  }
}
