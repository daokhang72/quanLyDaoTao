package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.KhungChuongTrinhRequest;
import com.QLDaoTao.dto.response.KhungChuongTrinhResponse;
import com.QLDaoTao.service.KhungChuongTrinhServicce;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/khungchuongtrinh")
public class KhungChuongTrinhController {
    @Autowired
    final private KhungChuongTrinhServicce khungChuongTrinhServicce;
    @PostMapping
    public ResponseEntity<KhungChuongTrinhResponse> taoKhungChuongTrinh(@RequestBody KhungChuongTrinhRequest request){
        return ResponseEntity.ok(khungChuongTrinhServicce.taoKhungChuongTrinh(request));
    }
    @GetMapping("/{ctdtId}")
    public ResponseEntity<List<KhungChuongTrinhResponse>> getByCTDT(@PathVariable Integer ctdtId) {
        return ResponseEntity.ok(khungChuongTrinhServicce.getKhungByCTDT(ctdtId));
    }
    @GetMapping()
    public ResponseEntity<List<KhungChuongTrinhResponse>> layToanBoKhungChuongTrinh(){
        return ResponseEntity.ok(khungChuongTrinhServicce.layToanBoKhungChuongTrinh());
    }

    @PutMapping("/{khungId}")
    public ResponseEntity<KhungChuongTrinhResponse> suaKhungChuongTrinh(@PathVariable Integer khungId,@RequestBody  KhungChuongTrinhRequest request){
        return ResponseEntity.ok(khungChuongTrinhServicce.suaKhungChuongTrinh(khungId, request));
    }

    @DeleteMapping("/{khungId}")
    public void xoaKhungChuongTrinh(@PathVariable  Integer khungId){
        khungChuongTrinhServicce.xoaKhungChuongTrinh(khungId);
    }
}
