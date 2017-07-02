package com.il.sod.db.model.entities;

import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AssetType database table.
 */
@Entity
@NamedQuery(name = "AssetType.findAll", query = "SELECT a FROM AssetType a")
public class AssetType extends SoftDeleteEntity implements IEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idAssetType;

  private String description;

  private String name;

  //bi-directional many-to-one association to Asset
  @OneToMany(mappedBy = "assetType", fetch = FetchType.EAGER)
  private Set<Asset> assets;

  public AssetType() {
  }

  public int getIdAssetType() {
    return this.idAssetType;
  }

  public void setIdAssetType(int idAssetType) {
    this.idAssetType = idAssetType;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Asset> getAssets() {
    return this.assets;
  }

  public void setAssets(Set<Asset> assets) {
    this.assets = assets;
  }

  public Asset addAsset(Asset asset) {
    getAssets().add(asset);
    asset.setAssetType(this);

    return asset;
  }

  public Asset removeAsset(Asset asset) {
    getAssets().remove(asset);
    asset.setAssetType(null);

    return asset;
  }

  @Override
  public Integer getId() {
    return this.idAssetType;
  }

  @Override
  public AssetType setId(Integer id) {
    this.idAssetType = id;
    return this;
  }
}
