package com.QLDaoTao.dto.response;

import com.QLDaoTao.model.User;

public record UserResponse(
        int userId,
        String userName,
        String userEmail,
        String userFullName,
        boolean isAdmin
) {
    public static UserResponse of(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserFullName(),
                user.isAdmin()
        );
    }
}
