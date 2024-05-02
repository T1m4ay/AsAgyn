package com.example.asadmin.repository;

import com.example.asadmin.model.Establishment;
import com.example.asadmin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    Establishment findAllByUser(User user);

    Establishment findAllById(Long id);
}

