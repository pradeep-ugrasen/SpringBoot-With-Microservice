package com.cubisoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<T> {

    private Integer status;
    private String message;
    private T data;
    private List<ErrorServiceResponse> error;

    // Utility factory method for success response
    public static <T> ServiceResponse<T> success(T data, String message) {
        return ServiceResponse.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    // Utility factory method for error response (message + status)
    public static <T> ServiceResponse<T> error(String message, Integer status) {
        return ServiceResponse.<T>builder()
                .status(status)
                .message(message)
                .build();
    }
}
