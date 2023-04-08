package com.yoon.boardingExpress.dto;

import com.yoon.boardingExpress.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(
        String id,
        String userPassword,
        String email,
        String name,
        String phoneNumber,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserAccountDto of(String id, String userPassword, String email, String name, String phoneNumber, String memo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new UserAccountDto(id, userPassword, email, name, phoneNumber, memo, createdAt, updatedAt);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
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
                id,
                userPassword,
                email,
                name,
                phoneNumber,
                memo
        );
    }

}