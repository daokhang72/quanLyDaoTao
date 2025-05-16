package com.QLDaoTao.controller;

import com.QLDaoTao.model.GiangVien;
import com.QLDaoTao.model.User;
import com.QLDaoTao.repository.GiangVienRepository;
import com.QLDaoTao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final GiangVienRepository giangVienRepository;

    @GetMapping("/home")
    public String homeAdmin() {return "homeAdmin";}

    @GetMapping("/ctdt")
    public String ctdtAdmin() {return "CTDTAdmin";}

    @GetMapping("/decuong")
    public String DeCuongCTAdmin() {
        return "decuongCT";
    }

    @GetMapping("/khung")
    public String khungCTAdmin() {
        return "KhungCTAdmin";
    }

    @GetMapping("/hocphan")
    public String dsHPAdmin() {
        return "dsHPAdmin";
    }

    @GetMapping("/dayhoc")
    public String KHdayhoc() {
        return "KHdayhoc";
    }

    @GetMapping("/monhom")
    public String KHmonhom() {return "KHmonhom";}

    @GetMapping("/phancong")
    public String phanconggiangday() {return "phanconggiangday";}

//    @GetMapping("/user")
//    public String User() {return "User";}

    @GetMapping("/user")
    public String User(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "User";
    }
    @PostMapping("/user/add")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi thêm người dùng");
        }
    }

    @GetMapping("/user/list")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/user/update")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("Cập nhật thành công");
    }
    @DeleteMapping("/user/delete/{userId}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        try {
            userRepository.deleteById(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra khi xóa người dùng!");
        }
    }

    //Giảng viên
    @GetMapping("/giangvien")
    public String giangVienPage(Model model) {
        List<GiangVien> giangViens = giangVienRepository.findAll();
        List<User> users = userRepository.findAll();

        model.addAttribute("giangviens", giangViens);
        model.addAttribute("users", users);  // Đưa danh sách user vào model để render dropdown
        model.addAttribute("gv", new GiangVien());  // Model mới cho form thêm

        return "giangvien";
    }

    @GetMapping("/giangvien/list")
    @ResponseBody
    public List<GiangVien> getAllGVs() {
        return giangVienRepository.findAll();
    }
    @PostMapping("/giangvien/add")
    @ResponseBody
    public ResponseEntity<?> addGV(@RequestBody GiangVien gv) {
        try {
            // Kiểm tra userId tồn tại
            boolean existsUser = userRepository.existsById(gv.getUserId());
            if (!existsUser) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UserId không tồn tại trong hệ thống");
            }
            giangVienRepository.save(gv);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi thêm giảng viên: " + e.getMessage());
        }
    }
    @PostMapping("/giangvien/update")
    @ResponseBody
    public ResponseEntity<?> updateGV(@RequestBody GiangVien gv) {
        giangVienRepository.save(gv);
        return ResponseEntity.ok("Cập nhật thành công");
    }
    @DeleteMapping("/giangvien/delete/{giangVienId}")
    @ResponseBody
    public ResponseEntity<?> deleteGV(@PathVariable int giangVienId) {
        try {
            giangVienRepository.deleteById(giangVienId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra khi xóa người dùng!");
        }
    }
    @GetMapping("/giangvien/thongke")
    @ResponseBody
    public ResponseEntity<?> thongKeGiangVien() {
        try {
            List<Object[]> rawData = giangVienRepository.thongKeTheoLoai();

            // Tạo dữ liệu JSON trả về
            List<String> labels = new ArrayList<>();
            List<Long> counts = new ArrayList<>();

            for (Object[] row : rawData) {
                labels.add((String) row[0]);          // loaiGiangVien
                counts.add((Long) row[1]);            // số lượng
            }

            Map<String, Object> result = new HashMap<>();
            result.put("labels", labels);
            result.put("counts", counts);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thống kê");
        }
    }

}
