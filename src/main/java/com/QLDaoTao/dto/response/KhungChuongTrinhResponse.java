package com.QLDaoTao.dto.response;

import com.QLDaoTao.model.KhungChuongTrinh;

public record KhungChuongTrinhResponse(
        int khungId,

        int ctdtId,

        String khoiKienThuc,

        String tenNhom,

        int soTinChiBatBuoc,

        int soTinChiTuChon
) {
    public static KhungChuongTrinhResponse of(KhungChuongTrinh khungChuongTrinh) {
        return new KhungChuongTrinhResponse(
                khungChuongTrinh.getKhungId(),
                khungChuongTrinh.getCtdtId(),
                khungChuongTrinh.getKhoiKienThuc(),
                khungChuongTrinh.getTenNhom(),
                khungChuongTrinh.getSoTinChiBatBuoc(),
                khungChuongTrinh.getSoTinChiTuChon()
        );

    }
}
