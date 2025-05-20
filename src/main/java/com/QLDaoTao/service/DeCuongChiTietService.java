package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.DeCuongChiTietRequest;
import com.QLDaoTao.dto.response.DeCuongChiTietResponse;
import com.QLDaoTao.exception.CustomException;
import com.QLDaoTao.exception.ErrorCode;
import com.QLDaoTao.model.DeCuongChiTiet;
import com.QLDaoTao.model.HocPhan;
import com.QLDaoTao.repository.DeCuongChiTietRepository;
import com.QLDaoTao.repository.HocPhanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeCuongChiTietService{

    private final DeCuongChiTietRepository repository;
    private final HocPhanRepository hocPhanRepository;
    public DeCuongChiTietResponse taoDeCuong(DeCuongChiTietRequest request) {
        HocPhan hocPhan = hocPhanRepository.findById(request.maHp())
                .orElseThrow(() -> new CustomException(ErrorCode.HocPhan_NOT_FOUND));

        DeCuongChiTiet entity = new DeCuongChiTiet();
        entity.setMaHp(hocPhan.getMaHp());
        entity.setTenBoPhan(request.tenBoPhan());
        entity.setDiemDanhGia(request.diemDanhGia());
        entity.setTrongSo(request.trongSo());
        entity.setHinhThucDanhGia(request.hinhThucDanhGia());
        return DeCuongChiTietResponse.of(repository.save(entity));
    }

    public List<DeCuongChiTietResponse> layTatCa() {
        return repository.findAll()
                .stream()
                .map(DeCuongChiTietResponse::of)
                .collect(Collectors.toList());
    }

    public DeCuongChiTietResponse capNhat(int id, DeCuongChiTietRequest request) {
        DeCuongChiTiet entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đề cương với id = " + id));
        entity.setTenBoPhan(request.tenBoPhan());
        entity.setDiemDanhGia(request.diemDanhGia());
        entity.setTrongSo(request.trongSo());
        entity.setHinhThucDanhGia(request.hinhThucDanhGia());

        return DeCuongChiTietResponse.of(repository.save(entity));
    }

    public void xoa(int id) {
        repository.deleteById(id);
    }

    public List<DeCuongChiTietResponse> layTheoIdMon(Long idMon) {
        return repository.findByMaHp(idMon)
                .stream()
                .map(DeCuongChiTietResponse::of)
                .collect(Collectors.toList());
    }

}
