package com.il.sod.db.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the SpecsValues database table.
 * 
 */
@Entity
@Table(name="SpecsValues")
@NamedQuery(name="SpecsValue.findAll", query="SELECT s FROM SpecsValue s")
public class SpecsValue implements IEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSpecsValues;

	private int idProduct;

	private int type;

	private String value;

	//bi-directional many-to-one association to Spec
	@ManyToOne
	@JoinColumn(name="idSpecs")
	private Spec spec;

	public SpecsValue() {
	}

	public int getIdSpecsValues() {
		return this.idSpecsValues;
	}

	public void setIdSpecsValues(int idSpecsValues) {
		this.idSpecsValues = idSpecsValues;
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Spec getSpec() {
		return this.spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	@Override
	public Integer getId() {
		return this.idSpecsValues;
	}

	@Override
	public SpecsValue setId(Integer id) {
		this.idSpecsValues = id;
		return this;
	}

}