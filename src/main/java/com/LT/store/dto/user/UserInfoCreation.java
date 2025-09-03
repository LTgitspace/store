package com.LT.store.dto.user;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoCreation {
    private UUID userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String avatarUrl;
    private UUID roleId;
}
