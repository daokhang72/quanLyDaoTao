package com.mhpl.QLDaoTao.dto.response;

import com.mhpl.QLDaoTao.model.ThongTinChung;

public record ThongTinChungResponse(
        int ctdtId,
        String tenCtdt,
        String bac,
        String loaiBang,
        String loaiHinhDaoTao,
        float thoiGian,
        int soTinChi,
        String khoaQuanLy,
        String ngonNgu,
        String website,
        String banHanh
) {
    public static ThongTinChungResponse of(ThongTinChung ttc) {
        return new ThongTinChungResponse(
                ttc.getCtdtId(),
                ttc.getTenCtdt(),
                ttc.getBac(),
                ttc.getLoaiBang(),
                ttc.getLoaiHinhDaoTao(),
                ttc.getThoiGian(),
                ttc.getSoTinChi(),
                ttc.getKhoaQuanLy(),
                ttc.getNgonNgu(),
                ttc.getWebsite(),
                ttc.getBanHanh()
        );
    }
}
