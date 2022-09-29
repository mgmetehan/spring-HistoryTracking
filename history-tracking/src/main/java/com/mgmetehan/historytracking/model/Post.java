package com.mgmetehan.historytracking.model;

import com.mgmetehan.historytracking.core.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Getter
@Setter
//Historical
@Entity
public class Post extends BaseEntity {
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
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
