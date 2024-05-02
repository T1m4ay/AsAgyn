package com.example.asadmin.model;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class Tables{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
