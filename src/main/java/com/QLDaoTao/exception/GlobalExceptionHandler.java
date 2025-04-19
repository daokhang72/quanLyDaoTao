package com.QLDaoTao.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //framework Spring
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.of(ex.getClass().getSimpleName(), ex.getMessage());
        return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
    }

    /**
     * Xảy ra khi có lỗi binding do sử dụng `javax.validation.Valid` hoặc `@Validated`.
     * Xảy ra khi `HttpMessageConverter` không thể binding được dữ liệu đã đăng ký.
     * Chủ yếu xảy ra khi sử dụng các annotation như `@RequestBody`, `@RequestPart`.
     */
    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage(), e);
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorMessage);
        GlobalResponse response = GlobalResponse.fail(status.value(), errorResponse);
        return ResponseEntity.status(status).body(response);
    }

    /** Xử lý ngoại lệ khi xác thực (validation) các tham số Request Param */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalResponse> handleConstraintViolationException(
            ConstraintViolationException e) {
        log.error("ConstraintViolationException : {}", e.getMessage(), e);

        Map<String, Object> bindingErrors = new HashMap<>();
        e.getConstraintViolations()
                .forEach(
                        constraintViolation -> {
                            List<String> propertyPath =
                                    List.of(
                                            constraintViolation
                                                    .getPropertyPath()
                                                    .toString()
                                                    .split("\\."));
                            String path =
                                    propertyPath.stream()
                                            .skip(propertyPath.size() - 1L)
                                            .findFirst()
                                            .orElse(null);
                            bindingErrors.put(path, constraintViolation.getMessage());
                        });

        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), bindingErrors.toString());
        final GlobalResponse response =
                GlobalResponse.fail(HttpStatus.BAD_REQUEST.value(), errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /** Xảy ra khi kiểu dữ liệu không khớp tại các vị trí như PathVariable, RequestParam, RequestHeader hoặc RequestBody */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<GlobalResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException : {}", e.getMessage(), e);
        final ErrorCode errorCode = ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH;
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorCode.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /** Xảy ra khi gọi một HTTP method không được hỗ trợ */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage(), e);
        final ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), errorCode.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /** Xử lý ngoại lệ CustomException */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GlobalResponse> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage(), e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse errorResponse =
                ErrorResponse.of(errorCode.name(), errorCode.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(errorCode.getStatus().value(), errorResponse);
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    /** Xử lý các lỗi thuộc nhóm mã lỗi 500 (Internal Server Error) */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<GlobalResponse> handleException(Exception e) {
        log.error("Internal Server Error : {}", e.getMessage(), e);
        final ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse =
                ErrorResponse.of(e.getClass().getSimpleName(), internalServerError.getMessage());
        final GlobalResponse response =
                GlobalResponse.fail(internalServerError.getStatus().value(), errorResponse);
        return ResponseEntity.status(internalServerError.getStatus()).body(response);
    }
}