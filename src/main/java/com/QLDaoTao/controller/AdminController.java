package com.QLDaoTao.controller;

import com.QLDaoTao.model.User;
import com.QLDaoTao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        System.out.println(">>> Size = " + users.size()); // debug
        model.addAttribute("users", users);
        return "User";
    }


}
