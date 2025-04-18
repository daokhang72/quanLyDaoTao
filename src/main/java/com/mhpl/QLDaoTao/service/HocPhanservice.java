package com.mhpl.QLDaoTao.service;

import com.mhpl.QLDaoTao.dto.request.HocPhanRequest;
import com.mhpl.QLDaoTao.dto.request.KhungChuongTrinhRequest;
import com.mhpl.QLDaoTao.dto.response.HocPhanResponse;
import com.mhpl.QLDaoTao.dto.response.KhungChuongTrinhResponse;
import com.mhpl.QLDaoTao.model.HocPhan;
import com.mhpl.QLDaoTao.model.KhungChuongTrinh;
import com.mhpl.QLDaoTao.repository.HocPhanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
                request.tongSoTiet(),
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
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy KhungChuongTrinh với ID: " + id));

        entity.setKhungId(request.khungId());
        entity.setTenHocPhan(request.tenHocPhan());
        entity.setSoTinChi(request.soTinChi());
        entity.setSoTietLyThuyet(request.soTietLyThuyet());
        entity.setSoTietThucHanh(request.soTietThucHanh());
        entity.setSoTietThucTap(request.soTietThucTap());
        entity.setTongSoTiet(request.tongSoTiet());
        entity.setHeSoHocPhan(request.heSoHocPhan());
        return HocPhanResponse.of(entity);
    }


    public void xoaHocPhan(Integer id) {
        if (!hocPhanRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy KhungChuongTrinh với ID: " + id);
        }
        hocPhanRepository.deleteById(id);
    }
}
