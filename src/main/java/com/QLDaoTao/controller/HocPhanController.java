package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.HocPhanRequest;
import com.QLDaoTao.dto.response.HocPhanResponse;
import com.QLDaoTao.service.HocPhanservice;
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
    public ResponseEntity<HocPhanResponse> taoHocPhan(HocPhanRequest request){
        return ResponseEntity.ok(hocPhanservice.taoHocPhan(request));
    }

    @GetMapping()
    public ResponseEntity<List<HocPhanResponse>> layToanBoHocPhan(){
        return ResponseEntity.ok(hocPhanservice.layToanBoHocPhan());
    }

    @PutMapping("/{khungId")
    public ResponseEntity<HocPhanResponse> suaHocPhan(Integer id, HocPhanRequest request){
        return ResponseEntity.ok(hocPhanservice.suaHocPhan(id, request));
    }

    @DeleteMapping("/{khungId")
    public void xoaHocPhan(Integer id){
        hocPhanservice.xoaHocPhan(id);
    }
}
