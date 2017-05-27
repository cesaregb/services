package com.il.sod.db.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the Clients database table.
 *
 */
@Entity
@Table(name="Clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client extends SoftDeleteEntity implements IEntity<Integer> {

	private final static Logger LOGGER = LoggerFactory.getLogger(Client.class);

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClient;
	private String email;
	private String lastName;
	private String name;
	private String password;
	private String twitter;
	private String loginID;
	private String rfc; 
	private String razonSocial; 
	private String mobilePhone;
	private String homePhone;
	private String otherPhone;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private Set<Address> addresses;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Set<Order> orders;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	//bi-directional many-to-one association to ClientPaymentInfo
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private Set<ClientPaymentInfo> clientPaymentInfos;
	
	//bi-directional many-to-one association to ClientBag
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ClientBag> clientBags;

	//bi-directional many-to-one association to ClientType
	@ManyToOne()
	@JoinColumn(name="idClientType")
	@JsonBackReference
	private ClientType clientType;
	
	public Client() {
	}

	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lasName) {
		this.lastName = lasName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Set<Address> getAddresses() {
		if (this.addresses == null){
			this.addresses = new HashSet<>();
		}
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setClient(this);
		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setClient(null);
		return address;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setClient(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setClient(null);

		return order;
	}
	@Override
	public Integer getId() {
		return this.idClient;
	}

	@Override
	public Client setId(Integer id) {
		this.idClient = id;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public Set<ClientPaymentInfo> getClientPaymentInfos() {
		if (this.clientPaymentInfos == null){
			this.clientPaymentInfos = new HashSet<>();
		}
		return this.clientPaymentInfos;
	}

	public void setClientPaymentInfos(Set<ClientPaymentInfo> clientPaymentInfos) {
		this.clientPaymentInfos = clientPaymentInfos;
	}

	public ClientPaymentInfo addClientPaymentInfo(ClientPaymentInfo clientPaymentInfo) {
		getClientPaymentInfos().add(clientPaymentInfo);
		clientPaymentInfo.setClient(this);

		return clientPaymentInfo;
	}

	public ClientPaymentInfo removeClientPaymentInfo(ClientPaymentInfo clientPaymentInfo) {
		getClientPaymentInfos().remove(clientPaymentInfo);
		clientPaymentInfo.setClient(null);

		return clientPaymentInfo;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Set<ClientBag> getClientBags() {
		if (this.clientBags == null){
			this.clientBags = new HashSet<>();
		}
		return this.clientBags;
	}

	public void setClientBags(Set<ClientBag> clientBags) {
		this.clientBags = clientBags;
	}

	public ClientBag addClientBag(ClientBag clientBag) {
		getClientBags().add(clientBag);
		clientBag.setClient(this);

		return clientBag;
	}

	public ClientBag removeClientBag(ClientBag clientBag) {
		getClientBags().remove(clientBag);
		clientBag.setClient(null);

		return clientBag;
	}

	public ClientType getClientType() {
		return this.clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	@Transient
	public Address getAddress(Address address){
		for (Address a: getAddresses()){
			if (a.equals(address)){ return a; }
		}
		return null;
	}

	@Transient
	public ClientPaymentInfo getPaymentInfo(ClientPaymentInfo searchFor){
		for (ClientPaymentInfo itm: getClientPaymentInfos()){
			if (itm.equals(searchFor)){ return itm; }
		}
		return null;
	}

	@Transient
	public ClientBag getClientBag(ClientBag searchFor){
		for (ClientBag itm: getClientBags()){
			if (itm.equals(searchFor)){ return itm; }
		}
		return null;
	}

	@Transient
	public String getFullName(){
		StringBuilder sb = new StringBuilder();
		this.lastName = (this.lastName!=null)?this.lastName:"";
		this.name = (this.name!=null)?this.name:"";
		sb.append(this.name).append(" ").append(this.lastName);
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Client)) return false;

		Client client = (Client) o;

		if (idClient != client.idClient) return false;
		if (email != null ? !email.equals(client.email) : client.email != null) return false;
		if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
		if (name != null ? !name.equals(client.name) : client.name != null) return false;
		if (password != null ? !password.equals(client.password) : client.password != null) return false;
		if (twitter != null ? !twitter.equals(client.twitter) : client.twitter != null) return false;
		if (loginID != null ? !loginID.equals(client.loginID) : client.loginID != null) return false;
		if (rfc != null ? !rfc.equals(client.rfc) : client.rfc != null) return false;
		if (razonSocial != null ? !razonSocial.equals(client.razonSocial) : client.razonSocial != null) return false;
		if (created != null ? !created.equals(client.created) : client.created != null) return false;
		return updated != null ? updated.equals(client.updated) : client.updated == null;

	}

	@Override
	public int hashCode() {
		int result = idClient;
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (twitter != null ? twitter.hashCode() : 0);
		result = 31 * result + (loginID != null ? loginID.hashCode() : 0);
		result = 31 * result + (rfc != null ? rfc.hashCode() : 0);
		result = 31 * result + (razonSocial != null ? razonSocial.hashCode() : 0);
		result = 31 * result + (created != null ? created.hashCode() : 0);
		result = 31 * result + (updated != null ? updated.hashCode() : 0);
		return result;
	}
}
