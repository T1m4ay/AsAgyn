package com.example.asadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "establishment")
public class Establishment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "serving_percentage")
    private BigDecimal servingPercentage;

    @Column(name = "background_image")
    private String backgroundImage;

    @OneToMany(mappedBy = "establishment", fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties(value = {"establishment"})
    private Set<DiningSession> diningSessions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @ManyToMany(mappedBy = "establishments")
    @JsonIgnoreProperties(value = "establishment", allowSetters = true)
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

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

    public BigDecimal getServingPercentage() {
        return servingPercentage;
    }

    public void setServingPercentage(BigDecimal servingPercentage) {
        this.servingPercentage = servingPercentage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Set<DiningSession> getDiningSessions() {
        return diningSessions;
    }

    public void setDiningSessions(Set<DiningSession> diningSessions) {
        if(this.diningSessions == null){
            this.diningSessions = new HashSet<>();
        }
        this.diningSessions = diningSessions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
