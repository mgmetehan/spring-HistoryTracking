package com.mgmetehan.historytracking.core.repository;

import com.mgmetehan.historytracking.core.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<T extends BaseEntity, P extends Serializable> extends JpaRepository<T, P>, JpaSpecificationExecutor<T> {
}
