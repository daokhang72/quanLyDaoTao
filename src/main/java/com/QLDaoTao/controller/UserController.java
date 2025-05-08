package com.QLDaoTao.controller;

import com.QLDaoTao.dto.request.UserRequest;
import com.QLDaoTao.dto.response.UserResponse;
import com.QLDaoTao.model.User;
import com.QLDaoTao.repository.UserRepository;
import com.QLDaoTao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @GetMapping("/homeAdmin")
    public String showAdminHome(){
        return "homeAdmin";
    }
    @GetMapping("/CTDTAdmin")
    public String showCTDTAdmin(){
        return "CTDTAdmin";
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String userEmail,
                        @RequestParam("password") String userPassword,
                        Model model) {
        try {
            UserRequest request = new UserRequest(null, userEmail, userPassword, null, false);
            UserResponse response = userService.login(request);

            if (response.isAdmin()) {
                return "homeAdmin";
            } else {
                model.addAttribute("error", "Bạn không có quyền truy cập!");
                return "login";
            }
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "login";
        }
    }

}
