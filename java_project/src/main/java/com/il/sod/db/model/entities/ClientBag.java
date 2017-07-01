package com.il.sod.db.model.entities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;


/**
 * The persistent class for the ClientBags database table.
 */
@Entity
@Table(name = "ClientBags")
@NamedQuery(name = "ClientBag.findAll", query = "SELECT c FROM ClientBag c")
public class ClientBag implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idClientBags;

  private boolean inOrder;

  private String number;

  //bi-directional many-to-one association to BagType
  @ManyToOne
  @JoinColumn(name = "idBagType")
  private BagType bagType;

  //bi-directional many-to-one association to Client
  @ManyToOne
  @JoinColumn(name = "idClient")
  private Client client;

  public ClientBag() {
  }

  public int getIdClientBags() {
    return this.idClientBags;
  }

  public void setIdClientBags(int idClientBags) {
    this.idClientBags = idClientBags;
  }

  public boolean isInOrder() {
    return inOrder;
  }

  public void setInOrder(boolean inOrder) {
    this.inOrder = inOrder;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public BagType getBagType() {
    return this.bagType;
  }

  public void setBagType(BagType bagType) {
    this.bagType = bagType;
  }

  public Client getClient() {
    return this.client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  @Override
  public Integer getId() {
    return this.getIdClientBags();
  }

  @Override
  public ClientBag setId(Integer id) {
    this.idClientBags = id;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClientBag clientBag = (ClientBag) o;
    return idClientBags == clientBag.idClientBags &&
            inOrder == clientBag.inOrder &&
            Objects.equal(number, clientBag.number) &&
            Objects.equal(bagType, clientBag.bagType);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(idClientBags, inOrder, number);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("idClientBags", idClientBags)
            .add("inOrder", inOrder)
            .add("number", number)
            .add("bagType", bagType)
            .add("client", client)
            .toString();
  }
}