package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.HocPhanFilterRequest;
import com.QLDaoTao.dto.request.HocPhanRequest;
import com.QLDaoTao.dto.response.HocPhanResponse;
import com.QLDaoTao.service.HocPhanservice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/hocphan")
public class HocPhanController {
    @Autowired
    final private HocPhanservice hocPhanservice;
    @PostMapping
    public ResponseEntity<HocPhanResponse> taoHocPhan(@RequestBody @Valid HocPhanRequest request){
        return ResponseEntity.ok(hocPhanservice.taoHocPhan(request));
    }

    @GetMapping()
    public ResponseEntity<List<HocPhanResponse>> layToanBoHocPhan(){
        return ResponseEntity.ok(hocPhanservice.layToanBoHocPhan());
    }

    @PutMapping("/{hocPhanId}")
    public ResponseEntity<HocPhanResponse> suaHocPhan(@PathVariable  Integer id, @RequestBody @Valid HocPhanRequest request){
        return ResponseEntity.ok(hocPhanservice.suaHocPhan(id, request));
    }

    @DeleteMapping("/{hocPhanId}")
    public void xoaHocPhan(@PathVariable Integer id){
        hocPhanservice.xoaHocPhan(id);
    }
    @GetMapping("/searchAdvanced")
    public ResponseEntity<List<HocPhanResponse>> filterHocPhan(@RequestBody @Valid HocPhanFilterRequest filter) {
        return ResponseEntity.ok(hocPhanservice.timKiemHocPhan(filter));
    }

}
