package com.example.asadmin.repository;

import com.example.asadmin.model.Establishment;
import com.example.asadmin.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findByEstablishment(Establishment establishment);

    Menu findAllById(Long id);
}
