package com.mgmetehan.historytracking.model;

import com.mgmetehan.historytracking.core.model.BaseEntity;
import com.mgmetehan.historytracking.core.model.BaseHistoryEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PostHistory extends BaseHistoryEntity {

    @Override
    public <T extends BaseEntity> void update(T entity) {
        throw new UnsupportedOperationException();
    }
}
