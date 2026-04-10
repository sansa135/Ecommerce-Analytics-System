package com.ecommerce.analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT SUM(e.amount) FROM Event e")
    Double getTotalRevenue();


    @Query("SELECT e.productName, SUM(e.amount) FROM Event e GROUP BY e.productName ORDER BY SUM(e.amount) DESC")
    List<Object[]> getTopProducts();


    @Query("SELECT e.city, SUM(e.amount) FROM Event e GROUP BY e.city")
    List<Object[]> getRevenueByCity();

    @Query("SELECT COUNT(e) FROM Event e WHERE e.status = 'failed'")
    Long getFailedPayments();

    @Query("SELECT e.city, COUNT(e) FROM Event e GROUP BY e.city ORDER BY COUNT(e) DESC")
    List<Object[]> topCities();

    @Query("SELECT COUNT(e) FROM Event e WHERE e.eventType = 'payment_failed'")
    Long failedPayments();

    @Query("SELECT e FROM Event e WHERE e.amount > 50000")
    List<Event> highTransactions();
}