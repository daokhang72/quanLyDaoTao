package com.mhpl.QLDaoTao.dto.response;

import com.mhpl.QLDaoTao.model.KeHoachDayHoc;

public record KeHoachDayHocResponse(
        int keHoachId,
        int maHp,
        String tenHocPhan,
        int soTinChi,
        String hocKyThucHien,
        int maHocPhanTruoc
) {
    public static KeHoachDayHocResponse of(KeHoachDayHoc kh) {
        return new KeHoachDayHocResponse(
                kh.getKeHoachId(),
                kh.getMaHp(),
                kh.getTenHocPhan(),
                kh.getSoTinChi(),
                kh.getHocKyThucHien(),
                kh.getMaHocPhanTruoc()
        );
    }
}
