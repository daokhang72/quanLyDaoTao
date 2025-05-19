package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.HocPhanRequest;
import com.QLDaoTao.dto.response.HocPhanResponse;
import com.QLDaoTao.exception.CustomException;
import com.QLDaoTao.exception.ErrorCode;
import com.QLDaoTao.model.HocPhan;
import com.QLDaoTao.repository.HocPhanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HocPhanservice {
    final private HocPhanRepository hocPhanRepository;

    public HocPhanResponse taoHocPhan(HocPhanRequest request) {
        HocPhan hocPhan = new HocPhan();

        hocPhan.setKhungId(request.khungId());
        hocPhan.setTenHocPhan(request.tenHocPhan());
        hocPhan.setSoTinChi(request.soTinChi());
        hocPhan.setSoTietLyThuyet(request.soTietLyThuyet());
        hocPhan.setSoTietThucHanh(request.soTietThucHanh());
        hocPhan.setSoTietThucTap(request.soTietThucTap());
        hocPhan.setHeSoHocPhan(request.heSoHocPhan());

        HocPhan result = hocPhanRepository.save(hocPhan);
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

    public List<HocPhanResponse> timKiemHocPhan(String keyword) {
        List<HocPhan> results = hocPhanRepository.searchAdvanced(keyword);
        return results.stream()
                .map(HocPhanResponse::of)
                .collect(Collectors.toList());
    }


    public List<HocPhanResponse> getKhungByCTDT(Integer ctdtId) {
        List<HocPhan> results =  hocPhanRepository.findHocPhansByCtdtId(ctdtId);
        List<HocPhanResponse> dtos = results.stream()
                .map(HocPhanResponse::of)
                .collect(Collectors.toList());
        return dtos;
    }
}
