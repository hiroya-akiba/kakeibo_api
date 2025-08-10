package com.example.kakeibo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_balances")
public class MonthlyBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Userと紐づく
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "year_month", nullable = false, length = 7)
    private String yearMonth; // "YYYY-MM"形式

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    @Column(precision = 12, scale = 2)
    private BigDecimal income;

    @Column(precision = 12, scale = 2)
    private BigDecimal expense;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    // --- Getterのみ（Lombokなし）
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getYearMonth() { return yearMonth; }
    public BigDecimal getBalance() { return balance; }
    public BigDecimal getIncome() { return income; }
    public BigDecimal getExpense() { return expense; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public LocalDateTime getUpdatedDate() { return updatedDate; }
}