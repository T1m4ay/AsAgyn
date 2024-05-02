package com.example.asadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "payment_method")
public class PaymentMethod{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_payment_method__establishment",
            joinColumns = @JoinColumn(name = "payment_method_id"),
            inverseJoinColumns = @JoinColumn(name = "establishment_id")
    )
    @JsonIgnoreProperties(value = { "payment_method" }, allowSetters = true)
    private Set<Establishment> establishments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Establishment> getEstablishment() {
        return establishments;
    }

    public void setEstablishment(Set<Establishment> establishments) {
        this.establishments = establishments;
    }
}