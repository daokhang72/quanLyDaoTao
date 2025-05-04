package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.ThongTinChungRequest;
import com.QLDaoTao.dto.response.ThongTinChungResponse;
import com.QLDaoTao.exception.CustomException;
import com.QLDaoTao.exception.ErrorCode;
import com.QLDaoTao.model.ThongTinChung;
import com.QLDaoTao.repository.ThongTinChungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThongTinChungService {

    private final ThongTinChungRepository repository;

    public ThongTinChungResponse taoThongTinChung(ThongTinChungRequest request) {
        ThongTinChung ttc = new ThongTinChung();
        ttc.setTenCtdt(request.tenCtdt());
        ttc.setBac(request.bac());
        ttc.setLoaiBang(request.loaiBang());
        ttc.setLoaiHinhDaoTao(request.loaiHinhDaoTao());
        ttc.setThoiGian(request.thoiGian());
        ttc.setSoTinChi(request.soTinChi());
        ttc.setKhoaQuanLy(request.khoaQuanLy());
        ttc.setNgonNgu(request.ngonNgu());
        ttc.setWebsite(request.website());
        ttc.setBanHanh(request.banHanh());
        return ThongTinChungResponse.of(repository.save(ttc));
    }

    public List<ThongTinChungResponse> layTatCaThongTinChung() {
        return repository.findAll().stream().map(ThongTinChungResponse::of).collect(Collectors.toList());
    }

    public ThongTinChungResponse capNhatThongTinChung(int id, ThongTinChungRequest request) {
        ThongTinChung ttc = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ThongTinChung_NOT_FOUND));
        ttc.setTenCtdt(request.tenCtdt());
        ttc.setBac(request.bac());
        ttc.setLoaiBang(request.loaiBang());
        ttc.setLoaiHinhDaoTao(request.loaiHinhDaoTao());
        ttc.setThoiGian(request.thoiGian());
        ttc.setSoTinChi(request.soTinChi());
        ttc.setKhoaQuanLy(request.khoaQuanLy());
        ttc.setNgonNgu(request.ngonNgu());
        ttc.setWebsite(request.website());
        ttc.setBanHanh(request.banHanh());
        return ThongTinChungResponse.of(repository.save(ttc));
    }

    public void xoaThongTinChung(int id) {
        repository.deleteById(id);
    }

    public List<ThongTinChungResponse> timKiemThongTinChung(String keyword) {
        return repository.searchByKeyword(keyword).stream().map(ThongTinChungResponse::of).collect(Collectors.toList());
    }
}
