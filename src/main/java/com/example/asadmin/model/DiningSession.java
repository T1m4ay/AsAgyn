package com.example.asadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dining_session")
@DynamicUpdate
@DynamicInsert
public class DiningSession implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date_time")
    private ZonedDateTime startDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Establishment establishment;

    @OneToMany(mappedBy = "diningSession", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties(value = "diningSession")
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        if(this.orders == null){
            this.orders = new HashSet<>();
        }
        this.orders = orders;
    }
}
