package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Set;

public class ClientDTO extends DeletableDTO{
	private int idClient;
	private String email;
	private String lastName;
	private String name;
	private String password;
	private String twitter;
	private String loginID;
	private String rfc; 
	private String razonSocial;
	private Integer idClientType;
	private String mobilePhone;
	private String homePhone;
	private String otherPhone;

	@ApiModelProperty(hidden=true)
	@JsonProperty(access = Access.READ_ONLY)
	private String defaultAddress;

	@ApiModelProperty(hidden=true)
	@JsonProperty(access = Access.READ_ONLY)
	private String defaultPayment;
	private Set<Integer> orders;
	private Set<AddressDTO> addresses;
	private Date created;
	private Date updated;
	private Set<ClientPaymentInfoDTO> clientPaymentInfos;
	private int deleted;
	private Set<ClientBagDTO> clientBags;
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public Set<Integer> getOrders() {
		return orders;
	}
	public void setOrders(Set<Integer> orders) {
		this.orders = orders;
	}
	public Set<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<AddressDTO> addresses) {
		this.addresses = addresses;
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
	public Set<ClientPaymentInfoDTO> getClientPaymentInfos() {
		return clientPaymentInfos;
	}
	public void setClientPaymentInfos(Set<ClientPaymentInfoDTO> clientPaymentInfos) {
		this.clientPaymentInfos = clientPaymentInfos;
	}
	public String getDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	public String getDefaultPayment() {
		return defaultPayment;
	}
	public void setDefaultPayment(String defaultPayment) {
		this.defaultPayment = defaultPayment;
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
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Set<ClientBagDTO> getClientBags() {
		return clientBags;
	}

	public void setClientBags(Set<ClientBagDTO> clientBags) {
		this.clientBags = clientBags;
	}

	public Integer getIdClientType() {
		return idClientType;
	}

	public void setIdClientType(Integer idClientType) {
		this.idClientType = idClientType;
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
}
