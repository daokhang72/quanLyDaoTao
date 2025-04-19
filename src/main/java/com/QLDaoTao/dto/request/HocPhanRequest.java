package com.QLDaoTao.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HocPhanRequest(
        @Min(0)
        int khungId,

        @NotBlank
        String tenHocPhan,

        @Min(1)
        int soTinChi,

        @Min(0)
        int soTietLyThuyet,

        @Min(0)
        int soTietThucHanh,

        @Min(0)
        int soTietThucTap,

        @Min(1)
        int heSoHocPhan
) {
}
