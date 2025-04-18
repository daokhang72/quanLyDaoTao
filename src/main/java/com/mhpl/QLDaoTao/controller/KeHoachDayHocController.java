package com.mhpl.QLDaoTao.controller;

import com.mhpl.QLDaoTao.dto.request.KeHoachDayHocRequest;
import com.mhpl.QLDaoTao.dto.response.KeHoachDayHocResponse;
import com.mhpl.QLDaoTao.service.KeHoachDayHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kehoach")
@RequiredArgsConstructor
public class KeHoachDayHocController {

    private final KeHoachDayHocService service;

    @PostMapping
    public ResponseEntity<KeHoachDayHocResponse> tao(@RequestBody KeHoachDayHocRequest request) {
        return ResponseEntity.ok(service.tao(request));
    }

    @GetMapping
    public ResponseEntity<List<KeHoachDayHocResponse>> layTatCa() {
        return ResponseEntity.ok(service.layTatCa());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeHoachDayHocResponse> capNhat(@PathVariable int id, @RequestBody KeHoachDayHocRequest request) {
        return ResponseEntity.ok(service.capNhat(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> xoa(@PathVariable int id) {
        service.xoa(id);
        return ResponseEntity.noContent().build();
    }
}
