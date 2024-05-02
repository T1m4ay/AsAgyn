package com.example.asadmin.repository;

import com.example.asadmin.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByDiningSession_Id(Long sessionId);

    @Query(
            "from OrderEntity o inner join o.diningSession where o.diningSession.establishment.id=:establishmentId"
    )
    List<OrderEntity> findAllByEstablishmentId(Long establishmentId);
}
