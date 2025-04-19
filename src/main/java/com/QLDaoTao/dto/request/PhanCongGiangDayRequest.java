package com.QLDaoTao.dto.request;

public record PhanCongGiangDayRequest(
        int mahp,
        String nhom,
        int giangvienId,
        String hotencbgd,
        int sotietthuchien,
        int sotietthucte
) {}
