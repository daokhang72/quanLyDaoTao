package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.KhungChuongTrinhRequest;
import com.QLDaoTao.dto.response.KhungChuongTrinhResponse;
import com.QLDaoTao.model.KhungChuongTrinh;
import com.QLDaoTao.repository.KhungChuongTrinhRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KhungChuongTrinhServicce {
    @Autowired
    final private KhungChuongTrinhRepository khungChuongTrinhRepository;

    public KhungChuongTrinhResponse taoKhungChuongTrinh(KhungChuongTrinhRequest request){

        KhungChuongTrinh khung = new KhungChuongTrinh();
        khung.setCtdt(request.ctdtId());
        khung.setKhoiKienThuc(request.khoiKienThuc());
        khung.setTenNhom(request.tenNhom());
        khung.setSoTinChiBatBuoc(request.soTinChiBatBuoc());
        khung.setSoTinChiTuChon(request.soTinChiTuChon());

        KhungChuongTrinh result =  khungChuongTrinhRepository.save(khung);
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

        entity.setTenNhom(request.tenNhom());
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

    public List<KhungChuongTrinhResponse> getKhungByCTDT(Integer ctdtId) {
        List<KhungChuongTrinh> results =  khungChuongTrinhRepository.findByCtdt_CtdtId(ctdtId);
        List<KhungChuongTrinhResponse> dtos = results.stream()
                .map(KhungChuongTrinhResponse::of)
                .collect(Collectors.toList());
        return dtos;
    }
}
