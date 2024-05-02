package com.example.asadmin.repository;

import com.example.asadmin.model.DiningSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiningSessionRepository extends JpaRepository<DiningSession, Long> {
    List<DiningSession> findAllByUserId(Long id);

    @Query("SELECT ds FROM DiningSession ds WHERE ds.establishment.id = :id AND ds.isClose = false")
    List<DiningSession> findAllByEstablishment_Id(Long id);
}
