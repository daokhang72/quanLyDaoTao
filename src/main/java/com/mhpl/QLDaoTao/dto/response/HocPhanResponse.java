package com.mhpl.QLDaoTao.dto.response;

import com.mhpl.QLDaoTao.model.HocPhan;
import com.mhpl.QLDaoTao.model.KhungChuongTrinh;

public record HocPhanResponse(
        int maHp,

        int khungId,

        String tenHocPhan,

        int soTinChi,

        int soTietLyThuyet,

        int soTietThucHanh,

        int soTietThucTap,

        int tongSoTiet,

        int heSoHocPhan
) {
    public static HocPhanResponse of(HocPhan hocPhan) {
        return new HocPhanResponse(
                hocPhan.getMaHp(),
                hocPhan.getKhungId(),
                hocPhan.getTenHocPhan(),
                hocPhan.getSoTinChi(),
                hocPhan.getSoTietLyThuyet(),
                hocPhan.getSoTietThucHanh(),
                hocPhan.getSoTietThucTap(),
                hocPhan.getTongSoTiet(),
                hocPhan.getHeSoHocPhan()
        );

    }
}
