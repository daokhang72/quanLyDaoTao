package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.ThongTinChungRequest;
import com.QLDaoTao.dto.response.ThongTinChungResponse;
import com.QLDaoTao.service.ThongTinChungService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ThongTinChungResponse> taoThongTinChung(@RequestBody @Valid ThongTinChungRequest request) {
        return ResponseEntity.ok(service.taoThongTinChung(request));
    }

    @GetMapping
    public ResponseEntity<List<ThongTinChungResponse>> layTatCaThongTinChung() {
        return ResponseEntity.ok(service.layTatCaThongTinChung());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThongTinChungResponse> capNhatThongTinChung(@PathVariable int id, @RequestBody @Valid ThongTinChungRequest request) {
        return ResponseEntity.ok(service.capNhatThongTinChung(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoaThongTinChung(@PathVariable int id) {
        service.xoaThongTinChung(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<ThongTinChungResponse>> timKiemThongTinChung(@RequestParam String keyword) {
        return ResponseEntity.ok(service.timKiemThongTinChung(keyword));
    }
}
