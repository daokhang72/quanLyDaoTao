package com.QLDaoTao.dto.request;

public record UserRequest(
        String userName,
        String userEmail,
        String userPassword,
        String userFullName,
        boolean isAdmin
) {}
