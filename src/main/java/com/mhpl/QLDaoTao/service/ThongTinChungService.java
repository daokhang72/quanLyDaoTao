package com.mhpl.QLDaoTao.service;

import com.mhpl.QLDaoTao.dto.request.ThongTinChungRequest;
import com.mhpl.QLDaoTao.dto.response.ThongTinChungResponse;
import com.mhpl.QLDaoTao.model.ThongTinChung;
import com.mhpl.QLDaoTao.repository.ThongTinChungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThongTinChungService {

    private final ThongTinChungRepository repository;

    public ThongTinChungResponse tao(ThongTinChungRequest request) {
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

    public List<ThongTinChungResponse> layTatCa() {
        return repository.findAll().stream().map(ThongTinChungResponse::of).collect(Collectors.toList());
    }

    public ThongTinChungResponse capNhat(int id, ThongTinChungRequest request) {
        ThongTinChung ttc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Thông tin chung với id = " + id));
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

    public void xoa(int id) {
        repository.deleteById(id);
    }
}
