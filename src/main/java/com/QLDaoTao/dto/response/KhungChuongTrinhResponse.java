package com.QLDaoTao.dto.response;

import com.QLDaoTao.model.KhungChuongTrinh;
import com.QLDaoTao.model.ThongTinChung;

public record KhungChuongTrinhResponse(
        int khungId,

        ThongTinChung ctdtId,

        String khoiKienThuc,

        String tenNhom,

        int soTinChiBatBuoc,

        int soTinChiTuChon
) {
    public static KhungChuongTrinhResponse of(KhungChuongTrinh khungChuongTrinh) {
        return new KhungChuongTrinhResponse(
                khungChuongTrinh.getKhungId(),
                khungChuongTrinh.getCtdt(),
                khungChuongTrinh.getKhoiKienThuc(),
                khungChuongTrinh.getTenNhom(),
                khungChuongTrinh.getSoTinChiBatBuoc(),
                khungChuongTrinh.getSoTinChiTuChon()
        );

    }
}
