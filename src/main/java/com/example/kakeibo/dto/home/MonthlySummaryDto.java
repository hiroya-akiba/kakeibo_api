package com.example.kakeibo.dto.home;

import java.math.BigDecimal;

public record MonthlySummaryDto(String yearMonth,
                                BigDecimal balance,
                                BigDecimal income,
                                BigDecimal expense) {}
