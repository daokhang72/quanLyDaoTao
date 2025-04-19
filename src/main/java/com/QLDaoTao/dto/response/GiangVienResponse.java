package com.QLDaoTao.dto.response;

import com.QLDaoTao.model.GiangVien;

public record GiangVienResponse(
        int giangVienId,
        int userId,
        String hoTen,
        String loaiGiangVien,
        String donVi,
        String email,
        String soDienThoai
) {
    public static GiangVienResponse of(GiangVien gv) {
        return new GiangVienResponse(
                gv.getGiangVienId(),
                gv.getUserId(),
                gv.getHoTen(),
                gv.getLoaiGiangVien(),
                gv.getDonVi(),
                gv.getEmail(),
                gv.getSoDienThoai()
        );
    }
}
