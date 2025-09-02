package com.LT.store.dto.role;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreation {
    private String name;
    private int level;
}
