package com.example.asadmin.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tables")
@DynamicUpdate
@DynamicInsert
public class Tables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_tables")
    @SequenceGenerator(name = "s_tables", allocationSize = 1)
    @Column(name = "id", table = "tables")
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private TableCategory tableCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TableCategory getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(TableCategory tableCategory) {
        this.tableCategory = tableCategory;
    }
}
