package com.mhpl.QLDaoTao.dto.request;

public record HocPhanRequest(
        int khungId,

        String tenHocPhan,

        int soTinChi,

        int soTietLyThuyet,

        int soTietThucHanh,

        int soTietThucTap,

        int tongSoTiet,

        int heSoHocPhan
) {
}
