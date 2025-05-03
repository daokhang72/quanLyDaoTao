package com.QLDaoTao.service;

import com.QLDaoTao.dto.request.UserRequest;
import com.QLDaoTao.dto.response.UserResponse;
import com.QLDaoTao.model.User;
import com.QLDaoTao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse register(UserRequest request) {
        if (userRepository.existsByUserEmail(request.userEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        User user = new User();
        user.setUserName(request.userName());
        user.setUserEmail(request.userEmail());
        user.setUserPassword(request.userPassword());
        user.setUserFullName(request.userFullName());
        user.setAdmin(request.isAdmin());
        return UserResponse.of(userRepository.save(user));
    }
    public User checkLogin(String email, String password) {
        return userRepository.findByUserEmailAndUserPassword(email, password).orElse(null);
    }
    public UserResponse login(UserRequest request){
        User user = userRepository.findByUserEmailAndUserPassword(request.userEmail(), request.userPassword())
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không đúng"));

        if (!user.isAdmin()) {
            throw new RuntimeException("Bạn không có quyền truy cập trang admin");
        }

        return UserResponse.of(user);
    }
}
