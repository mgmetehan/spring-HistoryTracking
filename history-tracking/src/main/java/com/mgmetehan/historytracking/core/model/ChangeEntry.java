package com.mgmetehan.historytracking.core.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@ToString
@Data
@Embeddable
public class ChangeEntry implements Serializable {
    @Column(columnDefinition = "TEXT")
    private String changedCells;

    @Column(columnDefinition = "TEXT")
    private String oldValues;

    @Column(columnDefinition = "TEXT")
    private String newValues;
}