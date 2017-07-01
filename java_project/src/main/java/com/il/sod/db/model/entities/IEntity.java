package com.il.sod.db.model.entities;

import java.io.Serializable;

public interface IEntity<I extends Serializable> extends Serializable {
  /**
   * Property which represents id.
   */
  String P_ID = "id";

  /**
   * Get primary key.
   *
   * @return primary key
   */
  I getId();

  /**
   * Set primary key.
   *
   * @param id primary key
   */
  IEntity setId(I id);
}
