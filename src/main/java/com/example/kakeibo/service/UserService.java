package com.example.kakeibo.service;

import com.example.kakeibo.dto.user.UserDto;

public interface UserService {
    UserDto getUserInfo(Long userId);
}
