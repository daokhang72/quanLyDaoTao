package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.PhanCongGiangDayRequest;
import com.QLDaoTao.dto.response.PhanCongGiangDayResponse;
import com.QLDaoTao.model.PhanCongGiangDay;
import com.QLDaoTao.repository.PhanCongGiangDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhanCongGiangDayService {

    private final PhanCongGiangDayRepository repository;

    public PhanCongGiangDayResponse tao(PhanCongGiangDayRequest r) {
        PhanCongGiangDay e = new PhanCongGiangDay();
        e.setMahp(r.mahp());
        e.setNhom(r.nhom());
        e.setHotencbgd(r.hotencbgd());
        e.setGiangvienId(r.giangvienId());
        e.setSotietthuchien(r.sotietthuchien());
        e.setSotietthucte(r.sotietthucte());
        return PhanCongGiangDayResponse.of(repository.save(e));
    }

    public List<PhanCongGiangDayResponse> layTatCa() {
        return repository.findAll().stream().map(PhanCongGiangDayResponse::of).collect(Collectors.toList());
    }

    public PhanCongGiangDayResponse capNhat(int id, PhanCongGiangDayRequest r) {
        PhanCongGiangDay e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phân công giảng dạy id = " + id));
        e.setMahp(r.mahp());
        e.setNhom(r.nhom());
        e.setHotencbgd(r.hotencbgd());
        e.setSotietthuchien(r.sotietthuchien());
        e.setSotietthucte(r.sotietthucte());
        return PhanCongGiangDayResponse.of(repository.save(e));
    }

    public void xoa(int id) {
        repository.deleteById(id);
    }
}
