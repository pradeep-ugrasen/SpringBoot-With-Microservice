package com.cubisoft.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorServiceResponse {

    private String errorMessage;

}
