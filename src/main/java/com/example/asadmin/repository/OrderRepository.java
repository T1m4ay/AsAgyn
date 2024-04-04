package com.example.asadmin.repository;

import com.example.asadmin.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
