package com.example.kakeibo.controller;

import com.example.kakeibo.common.util.ValidationUtil;
import com.example.kakeibo.dto.ApiResponse;
import com.example.kakeibo.dto.ErrorDetail;
import com.example.kakeibo.dto.user.*;
import com.example.kakeibo.dto.user.login.LoginRequest;
import com.example.kakeibo.dto.user.login.LoginResponse;
import com.example.kakeibo.dto.user.me.CustomUserDetailsRequest;
import com.example.kakeibo.dto.user.me.UpdateUserRequest;
import com.example.kakeibo.dto.user.register.RegisterRequest;
import com.example.kakeibo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * ユーザー登録コントローラ
     * @param request
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDetail> errors = ValidationUtil.fromBindingResult(bindingResult);
            return ResponseEntity.badRequest().body(
                    ApiResponse.<Void>builder()
                            .success(false)
                            .message("入力内容に誤りがあります。")
                            .errorCode("VALIDATION_ERROR")
                            .errors(errors)
                            .build()
            );
        }
        // userservice.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("ユーザー登録が完了しました。")
                        .build()
        );
    }

    /**
     * ログイン
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse(); //モック用
        //= userService.login(request);

        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("ログイン成功")
                        .data(response)
                        .build()
        );
    }

    /**
     * 自分の情報取得
     * @param userDetails
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> getMe(@AuthenticationPrincipal CustomUserDetailsRequest userDetails) {
        UserDto dto = new UserDto();
        //userService.getUserInfo(userDetails.getId());

        return ResponseEntity.ok(
                ApiResponse.<UserDto>builder()
                        .success(true)
                        .message("ユーザー情報取得成功")
                        .data(dto)
                        .build()
        );
    }

    /**
     * 自分の情報更新
     * @param userDetails
     * @param request
     * @param bindingResult
     * @return
     */
    @PutMapping("/me")
    public ResponseEntity<ApiResponse<Void>> updateMe(
            @AuthenticationPrincipal CustomUserDetailsRequest userDetails,
            @Valid @RequestBody UpdateUserRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ErrorDetail> errors = ValidationUtil.fromBindingResult(bindingResult);
            return ResponseEntity.badRequest().body(
                    ApiResponse.<Void>builder()
                            .success(false)
                            .message("入力内容に誤りがあります")
                            .errorCode("VALIDATION_ERROR")
                            .errors(errors)
                            .build()
            );
        }

        //userService.updateUser(userDetails.getId(), request);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("ユーザー情報を更新しました")
                        .build()
        );
    }
}
