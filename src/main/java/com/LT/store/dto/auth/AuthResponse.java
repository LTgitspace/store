package com.LT.store.dto.auth;

import com.LT.store.dto.user.UserDTO;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private UserDTO user;
    private String token;
}

