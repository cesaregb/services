package com.il.sod.db.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;


/**
 * The persistent class for the ClientPaymentInfo database table.
 */
@Entity
@NamedQuery(name = "ClientPaymentInfo.findAll", query = "SELECT c FROM ClientPaymentInfo c")
public class ClientPaymentInfo implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idClientPaymentInfo;

  private String token;

  private int type;

  private boolean prefered;

  //bi-directional many-to-one association to Client
  @ManyToOne
  @JoinColumn(name = "idClient")
  @JsonBackReference
  private Client client;

  public ClientPaymentInfo() {
  }

  public int getIdClientPaymentInfo() {
    return this.idClientPaymentInfo;
  }

  public void setIdClientPaymentInfo(int idClientPaymentInfo) {
    this.idClientPaymentInfo = idClientPaymentInfo;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Client getClient() {
    return this.client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  @Override
  public Integer getId() {
    return this.idClientPaymentInfo;
  }

  @Override
  public ClientPaymentInfo setId(Integer id) {
    this.idClientPaymentInfo = id;
    return this;
  }

  public boolean isPrefered() {
    return prefered;
  }

  public void setPrefered(boolean prefered) {
    this.prefered = prefered;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClientPaymentInfo that = (ClientPaymentInfo) o;
    return type == that.type &&
            prefered == that.prefered &&
            Objects.equal(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(token, type, prefered);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("idClientPaymentInfo", idClientPaymentInfo)
            .add("token", token)
            .add("type", type)
            .add("prefered", prefered)
            .toString();
  }
}