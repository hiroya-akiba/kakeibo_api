package com.example.kakeibo.service;

import com.example.kakeibo.dto.user.UserDto;
import com.example.kakeibo.dto.home.MonthlySummaryDto;

import java.util.List;

public interface HomeService {
    List<MonthlySummaryDto> getHomeData(Long userId, String centerMonth, int range);
    UserDto getUserInfo(Long userId);
}