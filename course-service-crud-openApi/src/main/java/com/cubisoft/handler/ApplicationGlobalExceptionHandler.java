package com.cubisoft.handler;

import com.cubisoft.dto.ErrorServiceResponse;
import com.cubisoft.dto.ServiceResponse;
import exception.CourseServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {

        List<ErrorServiceResponse> errorList = new ArrayList<>();

        // loop through all field errors
        exception.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errorList.add(new ErrorServiceResponse(error.getDefaultMessage()))
                );

        return ServiceResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation Failed")
                .error(errorList)
                .build();
    }

    @ExceptionHandler(CourseServiceBusinessException.class)
    public ServiceResponse<?> handleServiceException(CourseServiceBusinessException exception) {

        List<ErrorServiceResponse> errorList = new ArrayList<>();
        errorList.add(ErrorServiceResponse.builder()
                .errorMessage(exception.getMessage())
                .build()
        );

        return ServiceResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Business Error")
                .error(errorList)
                .build();
    }

}
