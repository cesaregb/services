package com.il.sod.rest.dto.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Set;

public class ClientDTO extends DeletableDTO{
	public ClientDTO() { }

	public ClientDTO(int idClient, String email, String lastName, String name, String twitter, String rfc, String razonSocial, Integer idClientType, String mobilePhone, String homePhone, String otherPhone, Set<AddressDTO> addresses, Set<ClientPaymentInfoDTO> clientPaymentInfos, Set<ClientBagDTO> clientBags) {
		this.idClient = idClient;
		this.email = email;
		this.lastName = lastName;
		this.name = name;
		this.password = password;
		this.twitter = twitter;
		this.rfc = rfc;
		this.razonSocial = razonSocial;
		this.idClientType = idClientType;
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.otherPhone = otherPhone;
		this.addresses = addresses;
		this.clientPaymentInfos = clientPaymentInfos;
		this.clientBags = clientBags;
	}

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
	private Date created;
	private Date updated;
	private Set<Integer> orders;
	private Set<AddressDTO> addresses;
	private Set<ClientPaymentInfoDTO> clientPaymentInfos;
	private Set<ClientBagDTO> clientBags;

	public String getFullName(){
		StringBuilder sb = new StringBuilder(this.name).append(" ").append(this.lastName);
		return sb.toString();
	}

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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("idClient", idClient)
				.append("email", email)
				.append("lastName", lastName)
				.append("name", name)
				.append("password", password)
				.append("twitter", twitter)
				.append("loginID", loginID)
				.append("rfc", rfc)
				.append("razonSocial", razonSocial)
				.append("idClientType", idClientType)
				.append("mobilePhone", mobilePhone)
				.append("homePhone", homePhone)
				.append("otherPhone", otherPhone)
				.append("defaultAddress", defaultAddress)
				.append("defaultPayment", defaultPayment)
				.append("created", created)
				.append("updated", updated)
				.append("orders", orders)
				.append("addresses", addresses)
				.append("clientPaymentInfos", clientPaymentInfos)
				.append("clientBags", clientBags)
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ClientDTO clientDTO = (ClientDTO) o;
		return idClient == clientDTO.idClient &&
				Objects.equal(email, clientDTO.email) &&
				Objects.equal(lastName, clientDTO.lastName) &&
				Objects.equal(name, clientDTO.name) &&
				Objects.equal(twitter, clientDTO.twitter) &&
				Objects.equal(loginID, clientDTO.loginID) &&
				Objects.equal(rfc, clientDTO.rfc) &&
				Objects.equal(razonSocial, clientDTO.razonSocial) &&
				Objects.equal(idClientType, clientDTO.idClientType) &&
				Objects.equal(mobilePhone, clientDTO.mobilePhone) &&
				Objects.equal(homePhone, clientDTO.homePhone) &&
				Objects.equal(otherPhone, clientDTO.otherPhone);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idClient, email, lastName, name, twitter, loginID, rfc, razonSocial, idClientType, mobilePhone, homePhone, otherPhone);
	}
}
