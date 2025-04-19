package com.QLDaoTao.dto.request;

public record GiangVienRequest(
        int userId,
        String hoTen,
        String loaiGiangVien,
        String donVi,
        String email,
        String soDienThoai
) {}
