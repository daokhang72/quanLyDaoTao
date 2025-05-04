package com.QLDaoTao.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ThongTinChungRequest(
        @NotNull(message = "Tên chương trình đào tạo không được để trống")
        String tenCtdt,

        @NotNull(message = "Bậc không được để trống")
        String bac,

        @NotNull(message = "Loại bằng không được để trống")
        String loaiBang,

        @NotNull(message = "Loại hình đào tạo không được để trống")
        String loaiHinhDaoTao,

        @NotNull(message = "Thời gian không được để trống")
        float thoiGian,

        @NotNull(message = "Số tín chỉ không được để trống")
        int soTinChi,

        @NotNull(message = "Khoa quản lý không được để trống")
        String khoaQuanLy,

        @NotNull(message = "Ngôn ngữ không được để trống")
        String ngonNgu,

        @NotNull(message = "Website không được để trống")
        @Size(min = 1, message = "Website phải có ít nhất 1 ký tự")
        String website,

        @NotNull(message = "Ban hành không được để trống")
        String banHanh
) {}
