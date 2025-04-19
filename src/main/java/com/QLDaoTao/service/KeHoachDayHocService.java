package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.KeHoachDayHocRequest;
import com.QLDaoTao.dto.response.KeHoachDayHocResponse;
import com.QLDaoTao.model.KeHoachDayHoc;
import com.QLDaoTao.repository.KeHoachDayHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeHoachDayHocService {

    private final KeHoachDayHocRepository repository;

    public KeHoachDayHocResponse tao(KeHoachDayHocRequest request) {
        KeHoachDayHoc kh = new KeHoachDayHoc();
        kh.setMaHp(request.maHp());
        kh.setTenHocPhan(request.tenHocPhan());
        kh.setSoTinChi(request.soTinChi());
        kh.setHocKyThucHien(request.hocKyThucHien());
        kh.setMaHocPhanTruoc(request.maHocPhanTruoc());
        return KeHoachDayHocResponse.of(repository.save(kh));
    }

    public List<KeHoachDayHocResponse> layTatCa() {
        return repository.findAll()
                .stream()
                .map(KeHoachDayHocResponse::of)
                .collect(Collectors.toList());
    }

    public KeHoachDayHocResponse capNhat(int id, KeHoachDayHocRequest request) {
        KeHoachDayHoc kh = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kế hoạch với id = " + id));
        kh.setMaHp(request.maHp());
        kh.setTenHocPhan(request.tenHocPhan());
        kh.setSoTinChi(request.soTinChi());
        kh.setHocKyThucHien(request.hocKyThucHien());
        kh.setMaHocPhanTruoc(request.maHocPhanTruoc());
        return KeHoachDayHocResponse.of(repository.save(kh));
    }

    public void xoa(int id) {
        repository.deleteById(id);
    }
}
