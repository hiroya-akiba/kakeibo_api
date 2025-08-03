package com.example.kakeibo.service;

import com.example.kakeibo.dto.user.UserDto;
import com.example.kakeibo.entity.User;
import com.example.kakeibo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserInfo(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
