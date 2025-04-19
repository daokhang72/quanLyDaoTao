package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.HocPhanFilterRequest;
import com.QLDaoTao.dto.request.HocPhanRequest;
import com.QLDaoTao.dto.response.HocPhanResponse;
import com.QLDaoTao.exception.CustomException;
import com.QLDaoTao.exception.ErrorCode;
import com.QLDaoTao.model.HocPhan;
import com.QLDaoTao.repository.HocPhanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HocPhanservice {
    @Autowired
    final private HocPhanRepository hocPhanRepository;

    public HocPhanResponse taoHocPhan(HocPhanRequest request){
        HocPhan result =  hocPhanRepository.save(new HocPhan(
                request.soTinChi(),
                request.khungId(),
                request.tenHocPhan(),
                request.soTinChi(),
                request.soTietLyThuyet(),
                request.soTietThucHanh(),
                request.soTietThucTap(),
                request.heSoHocPhan()
        ));
        return HocPhanResponse.of(result);
    }

    public List<HocPhanResponse> layToanBoHocPhan(){
        List<HocPhan> results =  hocPhanRepository.findAll();
        List<HocPhanResponse> dtos = results.stream()
                .map(HocPhanResponse::of)
                .collect(Collectors.toList());
        return dtos;
    }

    public HocPhanResponse suaHocPhan(Integer id, HocPhanRequest request) {
        HocPhan entity = hocPhanRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.HocPhan_NOT_FOUND));

        entity.setKhungId(request.khungId());
        entity.setTenHocPhan(request.tenHocPhan());
        entity.setSoTinChi(request.soTinChi());
        entity.setSoTietLyThuyet(request.soTietLyThuyet());
        entity.setSoTietThucHanh(request.soTietThucHanh());
        entity.setSoTietThucTap(request.soTietThucTap());
        entity.setHeSoHocPhan(request.heSoHocPhan());
        return HocPhanResponse.of(entity);
    }


    public void xoaHocPhan(Integer id) {
        if (!hocPhanRepository.existsById(id)) {
            throw new CustomException(ErrorCode.HocPhan_NOT_FOUND);
        }
        hocPhanRepository.deleteById(id);
    }

    public List<HocPhanResponse> timKiemHocPhan(HocPhanFilterRequest filter) {
        List<HocPhan> results = hocPhanRepository.searchAdvanced(
                Optional.ofNullable(filter.maHp()).orElse(null),
                Optional.ofNullable(filter.tenHocPhan()).orElse(""),
                Optional.ofNullable(filter.soTinChi()).orElse(null),
                Optional.ofNullable(filter.heSoHocPhan()).orElse(null)
        );
        return results.stream()
                .map(HocPhanResponse::of)
                .collect(Collectors.toList());
    }


}
