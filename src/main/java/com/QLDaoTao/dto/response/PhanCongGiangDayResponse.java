package com.QLDaoTao.dto.response;

import com.QLDaoTao.model.PhanCongGiangDay;

public record PhanCongGiangDayResponse(
        int pcgdId,
        int mahp,
        String nhom,
        int giangvienId,
        String hotencbgd,
        int sotietthuchien,
        int sotietthucte
) {
    public static PhanCongGiangDayResponse of(PhanCongGiangDay e) {
        return new PhanCongGiangDayResponse(
                e.getPcgdId(),
                e.getMahp(),
                e.getNhom(),
                e.getGiangvienId(),
                e.getHotencbgd(),
                e.getSotietthuchien(),
                e.getSotietthucte()
        );
    }
}
