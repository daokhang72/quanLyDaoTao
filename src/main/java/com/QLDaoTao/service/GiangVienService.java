package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.GiangVienRequest;
import com.QLDaoTao.dto.response.GiangVienResponse;
import com.QLDaoTao.model.GiangVien;
import com.QLDaoTao.repository.GiangVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiangVienService{

    private final GiangVienRepository repository;

    public GiangVienResponse taoGiangVien(GiangVienRequest request) {
        GiangVien gv = new GiangVien();
        gv.setUserId(request.userId());
        gv.setHoTen(request.hoTen());
        gv.setLoaiGiangVien(request.loaiGiangVien());
        gv.setDonVi(request.donVi());
        gv.setEmail(request.email());
        gv.setSoDienThoai(request.soDienThoai());
        return GiangVienResponse.of(repository.save(gv));
    }

    public List<GiangVienResponse> layTatCa() {
        return repository.findAll()
                .stream()
                .map(GiangVienResponse::of)
                .collect(Collectors.toList());
    }

    public GiangVienResponse capNhat(int id, GiangVienRequest request) {
        GiangVien gv = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với id = " + id));
        gv.setUserId(request.userId());
        gv.setHoTen(request.hoTen());
        gv.setLoaiGiangVien(request.loaiGiangVien());
        gv.setDonVi(request.donVi());
        gv.setEmail(request.email());
        gv.setSoDienThoai(request.soDienThoai());
        return GiangVienResponse.of(repository.save(gv));
    }

    public void xoa(int id) {
        repository.deleteById(id);
    }
}
