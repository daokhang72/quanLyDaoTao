package com.QLDaoTao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SAMPLE_ERROR(HttpStatus.BAD_REQUEST, "Sample Error Message"),
    // Chung
    METHOD_ARGUMENT_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "Kiểu dữ liệu của giá trị yêu cầu không đúng, dẫn đến việc binding thất bại."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi máy chủ, vui lòng liên hệ với quản trị viên."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Phương thức HTTP không được hỗ trợ."),

    //Hoc Phan
    HocPhan_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy Học Phần");

    private final HttpStatus status;
    private final String message;
}