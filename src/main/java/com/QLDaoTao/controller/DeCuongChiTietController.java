package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.DeCuongChiTietRequest;
import com.QLDaoTao.dto.response.DeCuongChiTietResponse;
import com.QLDaoTao.service.DeCuongChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decuong")
public class DeCuongChiTietController {

    private final DeCuongChiTietService deCuongService;

    @PostMapping
    public ResponseEntity<DeCuongChiTietResponse> taoDeCuong(@RequestBody DeCuongChiTietRequest request) {
        return ResponseEntity.ok(deCuongService.taoDeCuong(request));
    }

    @GetMapping
    public ResponseEntity<List<DeCuongChiTietResponse>> layToanBo() {
        return ResponseEntity.ok(deCuongService.layTatCa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeCuongChiTietResponse> suaDeCuong(@PathVariable int id, @RequestBody DeCuongChiTietRequest request) {
        return ResponseEntity.ok(deCuongService.capNhat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaDeCuong(@PathVariable int id) {
        deCuongService.xoa(id);
        return ResponseEntity.noContent().build();
    }
}
