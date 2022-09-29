package com.mgmetehan.historytracking.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseHistoryEntity extends BaseEntity implements Cloneable {
    private Long mainEntityId;

    @Embedded
    private ChangeEntry changeEntry;

    @Override
    public <T extends BaseEntity> void update(T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BaseHistoryEntity clone() {
        try {
            BaseHistoryEntity clone = (BaseHistoryEntity) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
