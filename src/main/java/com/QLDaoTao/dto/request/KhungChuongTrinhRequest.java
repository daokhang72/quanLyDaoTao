package com.QLDaoTao.dto.request;

public record KhungChuongTrinhRequest(
        int ctdtId,

        String khoiKienThuc,

        String tenNhom,

        int soTinChiBatBuoc,

        int soTinChiTuChon
) {
}
