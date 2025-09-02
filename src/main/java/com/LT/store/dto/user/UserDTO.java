package com.LT.store.dto.user;

import jakarta.persistence.Entity;
import lombok.*;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String passsword;
}
