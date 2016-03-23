package com.il.sod.db.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the Clients database table.
 *
 */
@Entity
@Table(name="Clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idClient;

	private String email;

	private String lastName;

	private String name;

	private String password;

	private String twitter;

	//bi-directional many-to-one association to AccessKey
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER)
	private List<AccessKey> accessKeys;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Address> addresses;

	//bi-directional many-to-one association to PhoneNumber
//	@OneToMany(mappedBy="client", fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER)
	private List<PhoneNumber> phoneNumbers;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="client", fetch=FetchType.EAGER)
	private List<Order> orders;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	//bi-directional many-to-one association to ClientPaymentInfo
	@OneToMany(mappedBy="client")
	private List<ClientPaymentInfo> clientPaymentInfos;

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

	public List<AccessKey> getAccessKeys() {
		return this.accessKeys;
	}

	public void setAccessKeys(List<AccessKey> accessKeys) {
		this.accessKeys = accessKeys;
	}

	public AccessKey addAccessKey(AccessKey accessKey) {
		getAccessKeys().add(accessKey);
		accessKey.setClient(this);

		return accessKey;
	}

	public AccessKey removeAccessKey(AccessKey accessKey) {
		getAccessKeys().remove(accessKey);
		accessKey.setClient(null);

		return accessKey;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
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

	public List<PhoneNumber> getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
		getPhoneNumbers().add(phoneNumber);
		phoneNumber.setClient(this);

		return phoneNumber;
	}

	public PhoneNumber removePhoneNumber(PhoneNumber phoneNumber) {
		getPhoneNumbers().remove(phoneNumber);
		phoneNumber.setClient(null);

		return phoneNumber;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
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
	
	public List<ClientPaymentInfo> getClientPaymentInfos() {
		return this.clientPaymentInfos;
	}

	public void setClientPaymentInfos(List<ClientPaymentInfo> clientPaymentInfos) {
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
}
