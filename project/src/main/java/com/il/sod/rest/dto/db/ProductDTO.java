package com.il.sod.rest.dto.db;

public class ProductDTO extends DeletableDTO{
	private int idProduct;
	private int maxQty;
	private String name;
	private double price;
	private int idProductType;
	private String typeName;
	private int quantity;

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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

	public int getIdProductType() {
		return idProductType;
	}

	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductDTO)) return false;

		ProductDTO that = (ProductDTO) o;

		if (idProduct != that.idProduct) return false;
		if (maxQty != that.maxQty) return false;
		if (Double.compare(that.price, price) != 0) return false;
		if (idProductType != that.idProductType) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = idProduct;
		result = 31 * result + maxQty;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + idProductType;
		result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
		return result;
	}
}