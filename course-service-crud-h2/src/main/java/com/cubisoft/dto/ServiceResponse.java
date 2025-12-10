package com.cubisoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse <T>{

    private Integer status;
    private String message;
    private T data;

    // Utility factory methods for easy use
    public static <T> ServiceResponse<T> success(T data, String message) {
        return ServiceResponse.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ServiceResponse<T> error(String message, Integer status) {
        return ServiceResponse.<T>builder()
                .status(status)
                .message(message)
                .build();
    }
}
