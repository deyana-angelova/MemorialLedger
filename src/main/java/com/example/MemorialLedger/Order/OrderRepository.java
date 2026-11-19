package com.example.MemorialLedger.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    long countByStatus(Status status);
    long countByDeadlineBefore(Date date, Status status);
    long countByDeadlineBetween(Date dateFrom, Date dateTo, Status status);


    //query for total revenue for each month
    @Query("""
            select year(o.dateOfOrder), month(o.dateOfOrder), sum(o.totalPrice)
            from Order o
            where o.dateOfOrder >= :start
            group by year(o.dateOfOrder), month(o.dateOfOrder)
            order by year(o.dateOfOrder), month(o.dateOfOrder)
            """)
    List<Object[]> monthlyRevenue(@Param("start") Date start);
}
