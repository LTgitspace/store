package com.LT.store.dto.role;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private UUID id;
    private String name;
    private int level;
}
