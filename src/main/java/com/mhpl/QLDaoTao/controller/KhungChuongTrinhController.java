package com.mhpl.QLDaoTao.controller;

import com.mhpl.QLDaoTao.dto.request.KhungChuongTrinhRequest;
import com.mhpl.QLDaoTao.dto.response.KhungChuongTrinhResponse;
import com.mhpl.QLDaoTao.service.KhungChuongTrinhServicce;
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
    public ResponseEntity<KhungChuongTrinhResponse> taoKhungChuongTrinh(KhungChuongTrinhRequest request){
        return ResponseEntity.ok(khungChuongTrinhServicce.taoKhungChuongTrinh(request));
    }

    @GetMapping()
    public ResponseEntity<List<KhungChuongTrinhResponse>> layToanBoKhungChuongTrinh(){
        return ResponseEntity.ok(khungChuongTrinhServicce.layToanBoKhungChuongTrinh());
    }

    @PutMapping("/{khungId")
    public ResponseEntity<KhungChuongTrinhResponse> suaKhungChuongTrinh(Integer id, KhungChuongTrinhRequest request){
        return ResponseEntity.ok(khungChuongTrinhServicce.suaKhungChuongTrinh(id, request));
    }

    @DeleteMapping("/{khungId")
    public void xoaKhungChuongTrinh(Integer id){
        khungChuongTrinhServicce.xoaKhungChuongTrinh(id);
    }
}
