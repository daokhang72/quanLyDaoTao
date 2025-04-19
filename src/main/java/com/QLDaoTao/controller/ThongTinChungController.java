package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.ThongTinChungRequest;
import com.QLDaoTao.dto.response.ThongTinChungResponse;
import com.QLDaoTao.service.ThongTinChungService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thongtin")
@RequiredArgsConstructor
public class ThongTinChungController {

    private final ThongTinChungService service;

    @PostMapping
    public ResponseEntity<ThongTinChungResponse> tao(@RequestBody ThongTinChungRequest request) {
        return ResponseEntity.ok(service.tao(request));
    }

    @GetMapping
    public ResponseEntity<List<ThongTinChungResponse>> layTatCa() {
        return ResponseEntity.ok(service.layTatCa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThongTinChungResponse> capNhat(@PathVariable int id, @RequestBody ThongTinChungRequest request) {
        return ResponseEntity.ok(service.capNhat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoa(@PathVariable int id) {
        service.xoa(id);
        return ResponseEntity.noContent().build();
    }
}
