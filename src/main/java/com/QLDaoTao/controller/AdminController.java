package com.QLDaoTao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/home")
    public String homeAdmin() {
        return "homeAdmin";
    }

    @GetMapping("/ctdt")
    public String ctdtAdmin() {
        return "CTDTAdmin";
    }
    @GetMapping("/khung")
    public String khungCTAdmin() {
        return "KhungCTAdmin";
    }
}
