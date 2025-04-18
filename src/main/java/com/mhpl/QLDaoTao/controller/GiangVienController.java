package com.mhpl.QLDaoTao.controller;

import com.mhpl.QLDaoTao.dto.request.GiangVienRequest;
import com.mhpl.QLDaoTao.dto.response.GiangVienResponse;
import com.mhpl.QLDaoTao.service.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giangvien")
@RequiredArgsConstructor
public class GiangVienController {

    private final GiangVienService giangVienService;

    @PostMapping
    public ResponseEntity<GiangVienResponse> taoGiangVien(@RequestBody GiangVienRequest request) {
        return ResponseEntity.ok(giangVienService.taoGiangVien(request));
    }

    @GetMapping
    public ResponseEntity<List<GiangVienResponse>> layTatCa() {
        return ResponseEntity.ok(giangVienService.layTatCa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiangVienResponse> capNhat(@PathVariable int id, @RequestBody GiangVienRequest request) {
        return ResponseEntity.ok(giangVienService.capNhat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoa(@PathVariable int id) {
        giangVienService.xoa(id);
        return ResponseEntity.noContent().build();
    }
}
