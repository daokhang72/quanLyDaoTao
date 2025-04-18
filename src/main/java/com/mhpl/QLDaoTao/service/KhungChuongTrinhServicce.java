package com.mhpl.QLDaoTao.service;

import com.mhpl.QLDaoTao.dto.request.KhungChuongTrinhRequest;
import com.mhpl.QLDaoTao.model.KhungChuongTrinh;
import com.mhpl.QLDaoTao.dto.repository.KhungChuongTrinhRepository;
import com.mhpl.QLDaoTao.response.KhungChuongTrinhResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KhungChuongTrinhServicce {
    @Autowired
    final private KhungChuongTrinhRepository khungChuongTrinhRepository;

    public KhungChuongTrinhResponse taoKhungChuongTrinh(KhungChuongTrinhRequest request){
        KhungChuongTrinh result =  khungChuongTrinhRepository.save(new KhungChuongTrinh(
                request.ctdtId(),
                request.soTinChiTuChon(),
                request.khoiKienThuc(),
                request.tenNhom(),
                request.soTinChiBatBuoc(),
                request.soTinChiTuChon()
                ));
        return KhungChuongTrinhResponse.of(result);
    }

    public List<KhungChuongTrinhResponse> layToanBoKhungChuongTrinh(){
        List<KhungChuongTrinh> results =  khungChuongTrinhRepository.findAll();
        List<KhungChuongTrinhResponse> dtos = results.stream()
                .map(KhungChuongTrinhResponse::of)
                .collect(Collectors.toList());
        return dtos;
    }

    public KhungChuongTrinhResponse suaKhungChuongTrinh(Integer id, KhungChuongTrinhRequest request) {
        KhungChuongTrinh entity = khungChuongTrinhRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy KhungChuongTrinh với ID: " + id));

        entity.setKhungId(request.ctdtId());
        entity.setTenNhom(request.tenNhom());
        entity.setCtdtId(request.ctdtId());
        entity.setKhoiKienThuc(request.khoiKienThuc());
        entity.setSoTinChiTuChon(request.soTinChiTuChon());
        entity.setSoTinChiBatBuoc(request.soTinChiBatBuoc());
        return KhungChuongTrinhResponse.of(entity);
    }


    public void xoaKhungChuongTrinh(Integer id) {
        if (!khungChuongTrinhRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy KhungChuongTrinh với ID: " + id);
        }
        khungChuongTrinhRepository.deleteById(id);
    }

}
