package com.example.kakeibo.repository;

import com.example.kakeibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Emailでユーザー検索（ログイン時などに使う）
    Optional<User> findByEmail(String email);
}