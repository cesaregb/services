package com.il.sod.services.crud;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import com.il.sod.services.cruds.ProductSv;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by cesaregb on 1/19/17.
 */
public class ProductSvTest extends SpringTestConfiguration {

  private final static Logger LOGGER = getLogger(ProductSvTest.class);

  @Autowired
  ProductSv productSv;


  @Test
  public void testGetProductTypeByServiceId() throws SODAPIException {
    List<ProductTypeDTO> plist = productSv.getProductTypeList(1);
    plist.forEach(it -> LOGGER.info(it.toString()));
  }

}
