package com.example.asadmin.repository;

import com.example.asadmin.model.DiningSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningSessionRepository extends JpaRepository<DiningSession, Long> {
}
