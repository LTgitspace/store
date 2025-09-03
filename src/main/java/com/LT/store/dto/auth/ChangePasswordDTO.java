package com.LT.store.dto.auth;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}

