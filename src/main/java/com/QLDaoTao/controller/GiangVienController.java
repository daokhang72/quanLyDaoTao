package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.GiangVienRequest;
import com.QLDaoTao.dto.response.GiangVienResponse;
import com.QLDaoTao.model.GiangVien;
import com.QLDaoTao.model.User;
import com.QLDaoTao.repository.GiangVienRepository;
import com.QLDaoTao.service.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giangvien")
@RequiredArgsConstructor
public class GiangVienController {

    private final GiangVienService giangVienService;

    @Autowired
    private final GiangVienRepository giangVienRepository;

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
    @GetMapping("/admin/giangvien")
    public String Giangvien(Model model) {
        List<GiangVien> giangViens = giangVienRepository.findAll();
        System.out.println("Số lượng giảng viên: " + giangViens.size());
        model.addAttribute("giangviens", giangViens);
        return "giangvien";
    }

}
