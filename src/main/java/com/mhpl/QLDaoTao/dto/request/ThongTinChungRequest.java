package com.mhpl.QLDaoTao.dto.request;

public record ThongTinChungRequest(
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
) {}
