package com.example.ms_productos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private Integer code;
    private String message;
    private Optional<T> data;
}
