package com.yoon.boardingExpress.dto;

import com.yoon.boardingExpress.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        String userId,
        String userPassword,
        String email,
        String name,
        String phoneNumber,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserAccountDto of(Long id, String userId, String userPassword, String email, String name, String phoneNumber, String memo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new UserAccountDto(id, userId, userPassword, email, name, phoneNumber, memo, createdAt, updatedAt);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                name,
                phoneNumber,
                memo
        );
    }

}