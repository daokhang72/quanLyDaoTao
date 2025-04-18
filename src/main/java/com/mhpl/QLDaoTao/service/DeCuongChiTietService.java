package com.mhpl.QLDaoTao.service;

import com.mhpl.QLDaoTao.dto.request.DeCuongChiTietRequest;
import com.mhpl.QLDaoTao.dto.response.DeCuongChiTietResponse;
import com.mhpl.QLDaoTao.model.DeCuongChiTiet;
import com.mhpl.QLDaoTao.repository.DeCuongChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeCuongChiTietService{

    private final DeCuongChiTietRepository repository;

    public DeCuongChiTietResponse taoDeCuong(DeCuongChiTietRequest request) {
        DeCuongChiTiet entity = new DeCuongChiTiet();
        entity.setMaHp(request.maHp());
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

        entity.setMaHp(request.maHp());
        entity.setTenBoPhan(request.tenBoPhan());
        entity.setDiemDanhGia(request.diemDanhGia());
        entity.setTrongSo(request.trongSo());
        entity.setHinhThucDanhGia(request.hinhThucDanhGia());

        return DeCuongChiTietResponse.of(repository.save(entity));
    }

    public void xoa(int id) {
        repository.deleteById(id);
    }
}
