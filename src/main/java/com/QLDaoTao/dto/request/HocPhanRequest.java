package com.QLDaoTao.dto.request;

public record HocPhanRequest(
        int khungId,

        String tenHocPhan,

        int soTinChi,

        int soTietLyThuyet,

        int soTietThucHanh,

        int soTietThucTap,

        int heSoHocPhan
) {
}
