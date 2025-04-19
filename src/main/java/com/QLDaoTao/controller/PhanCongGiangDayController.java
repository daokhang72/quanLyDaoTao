package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.PhanCongGiangDayRequest;
import com.QLDaoTao.dto.response.PhanCongGiangDayResponse;
import com.QLDaoTao.service.PhanCongGiangDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phanconggiangday")
@RequiredArgsConstructor
public class PhanCongGiangDayController {

    private final PhanCongGiangDayService service;

    @PostMapping
    public ResponseEntity<PhanCongGiangDayResponse> tao(@RequestBody PhanCongGiangDayRequest request) {
        return ResponseEntity.ok(service.tao(request));
    }

    @GetMapping
    public ResponseEntity<List<PhanCongGiangDayResponse>> layTatCa() {
        return ResponseEntity.ok(service.layTatCa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhanCongGiangDayResponse> capNhat(@PathVariable int id, @RequestBody PhanCongGiangDayRequest request) {
        return ResponseEntity.ok(service.capNhat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoa(@PathVariable int id) {
        service.xoa(id);
        return ResponseEntity.noContent().build();
    }
}
