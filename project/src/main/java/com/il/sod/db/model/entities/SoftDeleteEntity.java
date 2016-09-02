package com.il.sod.db.model.entities;


import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SoftDeleteEntity {

    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
