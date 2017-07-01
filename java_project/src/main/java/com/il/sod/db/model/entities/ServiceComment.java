package com.il.sod.db.model.entities;

import javax.persistence.*;


/**
 * The persistent class for the ServiceComments database table.
 */
@Entity
@Table(name = "ServiceComments")
@NamedQuery(name = "ServiceComment.findAll", query = "SELECT s FROM ServiceComment s")
public class ServiceComment implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idServiceComments;

  private String comment;

  //bi-directional many-to-one association to Service
  @ManyToOne
  @JoinColumn(name = "idService")
  private Service service;

  public ServiceComment() {
  }

  public int getIdServiceComments() {
    return this.idServiceComments;
  }

  public void setIdServiceComments(int idServiceComments) {
    this.idServiceComments = idServiceComments;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Service getService() {
    return this.service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  @Override
  public Integer getId() {
    return this.idServiceComments;
  }

  @Override
  public ServiceComment setId(Integer id) {
    this.idServiceComments = id;
    return this;
  }

}