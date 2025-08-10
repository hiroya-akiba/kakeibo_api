package com.example.kakeibo.repository;

import com.example.kakeibo.dto.home.MonthlySummaryDto;
import com.example.kakeibo.entity.MonthlyBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.YearMonth;
import java.util.List;

public interface MonthlyBalanceRepository extends JpaRepository<MonthlyBalance, Long> {

    @Query("""
        SELECT new com.example.kakeibo.dto.home.MonthlySummaryDto(
            m.yearMonth, m.balance, m.income, m.expense
        )
        FROM MonthlyBalance m
        WHERE m.user.id = :userId
        AND m.yearMonth BETWEEN :start AND :end
        ORDER BY m.yearMonth
        """)
    List<MonthlySummaryDto> findByUserIdAndRange(
            @Param("userId") Long userId,
            @Param("start") YearMonth start,
            @Param("end") YearMonth end
    );
}