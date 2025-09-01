package com.LT.store.dto.auth;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {
    private String username;
    private String passsword;
}
