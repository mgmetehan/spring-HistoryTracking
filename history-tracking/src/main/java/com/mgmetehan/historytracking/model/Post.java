package com.mgmetehan.historytracking.model;

import com.mgmetehan.historytracking.core.model.BaseEntity;
import com.mgmetehan.historytracking.shared.annotation.Historical;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Historical(serviceName = "postHistoryServiceImpl", clazz = PostHistory.class)
@Entity
public class Post extends BaseEntity {
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Override
    public <T extends BaseEntity> void update(T entity) {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
