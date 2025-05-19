package com.QLDaoTao.dto.response;

import com.QLDaoTao.model.DeCuongChiTiet;

public record DeCuongChiTietResponse(
        Long deCuongId,
        Long maHp,
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
