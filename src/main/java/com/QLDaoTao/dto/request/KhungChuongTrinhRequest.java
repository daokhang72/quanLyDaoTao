package com.QLDaoTao.dto.request;

import com.QLDaoTao.model.ThongTinChung;

public record KhungChuongTrinhRequest(
        ThongTinChung ctdtId,

        String khoiKienThuc,

        String tenNhom,

        int soTinChiBatBuoc,

        int soTinChiTuChon
) {
}
