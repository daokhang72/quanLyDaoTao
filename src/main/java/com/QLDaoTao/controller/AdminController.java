package com.QLDaoTao.controller;

import com.QLDaoTao.model.User;
import com.QLDaoTao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/giangvien")
    public String giangvien() {
        return "giangvien";
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


}
