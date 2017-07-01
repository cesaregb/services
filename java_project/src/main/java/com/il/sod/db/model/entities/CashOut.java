package com.il.sod.db.model.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cesaregb on 6/3/17.
 */
@Entity
public class CashOut extends SoftDeleteEntity implements IEntity<Integer> {
  private int idCashOut;
  private Timestamp created;
  private Integer user;
  private Double subtotal;
  private Double pending;
  private Double discount;
  private Double total;

  @Id
  @Column(name = "idCashOut")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getIdCashOut() {
    return idCashOut;
  }

  public void setIdCashOut(int idCashOut) {
    this.idCashOut = idCashOut;
  }

  @Basic
  @Column(name = "created")
  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  @Basic
  @Column(name = "user")
  public Integer getUser() {
    return user;
  }

  public void setUser(Integer user) {
    this.user = user;
  }

  @Basic
  @Column(name = "subtotal")
  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  @Basic
  @Column(name = "pending")
  public Double getPending() {
    return pending;
  }

  public void setPending(Double pending) {
    this.pending = pending;
  }


  @Basic
  @Column(name = "discount")
  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  @Basic
  @Column(name = "total")
  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CashOut cashOut = (CashOut) o;
    return idCashOut == cashOut.idCashOut &&
            Objects.equal(created, cashOut.created) &&
            Objects.equal(user, cashOut.user) &&
            Objects.equal(subtotal, cashOut.subtotal) &&
            Objects.equal(total, cashOut.total) &&
            Objects.equal(pending, cashOut.pending) &&
            Objects.equal(discount, cashOut.discount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(idCashOut, created, user, subtotal, pending, discount, total);
  }

  @Override
  @Transient
  public Integer getId() {
    return this.idCashOut;
  }

  @Override
  public IEntity setId(Integer id) {
    this.idCashOut = id;
    return this;
  }
}
