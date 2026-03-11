package com.example.E_seva_kendra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.E_seva_kendra.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

    @Query(value = "SELECT COALESCE(SUM(amount),0) FROM payments WHERE DATE(created_at) = CURRENT_DATE", nativeQuery = true)
    Double getTodayEarnings();

    
    // Weekly Earnings
    @Query(value =
    "SELECT TO_CHAR(created_at,'Dy') as day, SUM(amount) as total " +
    "FROM payments " +
    "WHERE created_at >= CURRENT_DATE - INTERVAL '7 days' " +
    "GROUP BY day",
    nativeQuery = true)
    List<Object[]> getWeeklyEarnings();


    // Monthly Earnings
    @Query(value =
    "SELECT 'Week ' || CEIL(EXTRACT(DAY FROM created_at)/7) as week, SUM(amount) as total " +
    "FROM payments " +
    "WHERE DATE_TRUNC('month', created_at) = DATE_TRUNC('month', CURRENT_DATE) " +
    "GROUP BY week",
    nativeQuery = true)
    List<Object[]> getMonthlyEarnings();


    // Yearly Earnings
    @Query(value =
    "SELECT TO_CHAR(created_at,'Mon') as month, SUM(amount) as total " +
    "FROM payments " +
    "WHERE DATE_TRUNC('year', created_at) = DATE_TRUNC('year', CURRENT_DATE) " +
    "GROUP BY month",
    nativeQuery = true)
    List<Object[]> getYearlyEarnings();
    
    List<Payment> findByUserId(Long userId);

}