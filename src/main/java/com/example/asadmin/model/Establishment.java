package com.example.asadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "establishment")
@DynamicUpdate
@DynamicInsert
public class Establishment implements Serializable {

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

    @OneToMany(mappedBy = "establishment", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties(value = "establishment")
    private Set<DiningSession> diningSessions;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

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
}
