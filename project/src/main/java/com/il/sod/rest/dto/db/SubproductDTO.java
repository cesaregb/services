package com.il.sod.rest.dto.db;

public class SubproductDTO extends DeletableDTO{
	private int idSubproduct;
	private int maxQty;
	private String name;
	private double price;
	private int idSubproductType;
	private String typeName;

	public int getIdSubproduct() {
		return idSubproduct;
	}

	public void setIdSubproduct(int idSubproduct) {
		this.idSubproduct = idSubproduct;
	}

	public int getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdSubproductType() {
		return idSubproductType;
	}

	public void setIdSubproductType(int idSubproductType) {
		this.idSubproductType = idSubproductType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SubproductDTO)) return false;

		SubproductDTO that = (SubproductDTO) o;

		if (idSubproduct != that.idSubproduct) return false;
		if (maxQty != that.maxQty) return false;
		if (Double.compare(that.price, price) != 0) return false;
		if (idSubproductType != that.idSubproductType) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idSubproduct;
		result = 31 * result + maxQty;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + idSubproductType;
		result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
		return result;
	}
}