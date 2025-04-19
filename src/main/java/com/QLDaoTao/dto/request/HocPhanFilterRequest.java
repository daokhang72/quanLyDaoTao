package com.QLDaoTao.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record HocPhanFilterRequest(
        @Min(value = 1, message = "Số tín chỉ phải lớn hơn hoặc bằng 1")
        Integer maHp,

        @Size(max = 100, message = "Tên học phần không được vượt quá 100 ký tự")
        String tenHocPhan,

        @Min(value = 1, message = "Số tín chỉ phải lớn hơn hoặc bằng 1")
        Integer soTinChi,

        @Min(value = 1, message = "Hệ số học phần phải lớn hơn hoặc bằng 1")
        Integer heSoHocPhan
) {
}
