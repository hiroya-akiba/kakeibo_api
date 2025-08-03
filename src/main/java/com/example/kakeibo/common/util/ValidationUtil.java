package com.example.kakeibo.common.util;

import com.example.kakeibo.dto.ErrorDetail;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static List<ErrorDetail> fromBindingResult(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(e -> ErrorDetail.builder()
                        .field(e.getField())
                        .message(e.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}