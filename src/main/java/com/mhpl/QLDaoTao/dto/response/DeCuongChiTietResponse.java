package com.mhpl.QLDaoTao.dto.response;

import com.mhpl.QLDaoTao.model.DeCuongChiTiet;

public record DeCuongChiTietResponse(
        int deCuongId,
        int maHp,
        String tenBoPhan,
        String diemDanhGia,
        Float trongSo,
        String hinhThucDanhGia
) {
    public static DeCuongChiTietResponse of(DeCuongChiTiet entity) {
        return new DeCuongChiTietResponse(
                entity.getDeCuongId(),
                entity.getMaHp(),
                entity.getTenBoPhan(),
                entity.getDiemDanhGia(),
                entity.getTrongSo(),
                entity.getHinhThucDanhGia()
        );
    }
}
