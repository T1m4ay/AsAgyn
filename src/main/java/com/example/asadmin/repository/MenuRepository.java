package com.example.asadmin.repository;

import com.example.asadmin.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("from Menu where establishment.id = :establishmentId")
    Menu findAllByEstablishmentId(@Param("establishmentId") Long establishmentId);
}
