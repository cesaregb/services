package com.il.sod.db.model.entities;


import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SoftDeleteEntity {

    private int deleted;

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
