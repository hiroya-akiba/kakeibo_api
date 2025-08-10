package com.example.kakeibo.service;

import com.example.kakeibo.dto.user.UserDto;
import com.example.kakeibo.dto.home.MonthlySummaryDto;
import com.example.kakeibo.repository.MonthlyBalanceRepository;
import com.example.kakeibo.repository.UserRepository;
import com.example.kakeibo.service.HomeService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    private final MonthlyBalanceRepository monthlyBalanceRepository;
    private final UserRepository userRepository;

    public HomeServiceImpl(MonthlyBalanceRepository monthlyBalanceRepository,
                           UserRepository userRepository) {
        this.monthlyBalanceRepository = monthlyBalanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<MonthlySummaryDto> getHomeData(Long userId, String centerMonth, int range) {
        YearMonth center = (centerMonth != null) ? YearMonth.parse(centerMonth) : YearMonth.now();
        YearMonth start = center.minusMonths(range);
        YearMonth end = center.plusMonths(range);
        return monthlyBalanceRepository.findByUserIdAndRange(userId, start, end);
    }

    @Override
    public UserDto getUserInfo(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserDto(
                        user.getId(),
                        userId,
                        user.getEmail(),
                        user.getName(),
                        user.getRole()
                ))
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
    }
}