package com.QLDaoTao.dto.request;

import com.QLDaoTao.model.ThongTinChung;

public record KhungChuongTrinhRequest(
        Integer ctdtId,

        String khoiKienThuc,

        String tenNhom,

        int soTinChiBatBuoc,

        int soTinChiTuChon
) {
}
