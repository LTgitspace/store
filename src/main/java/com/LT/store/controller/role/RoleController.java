package com.LT.store.controller.role;

import com.LT.store.dto.role.RoleDTO;
import com.LT.store.dto.role.RoleCreation;
import com.LT.store.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleCreation roleCreation) {
        RoleDTO created = roleService.createRole(roleCreation);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable UUID id) {
        RoleDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable UUID id, @RequestBody RoleCreation roleCreation) {
        RoleDTO updated = roleService.updateRole(id, roleCreation);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RoleDTO>> searchRolesByName(@RequestParam String name) {
        List<RoleDTO> roles = roleService.searchRolesByName(name);
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<RoleDTO> getRoleByLevel(@PathVariable int level) {
        RoleDTO role = roleService.getRoleByLevel(level);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<RoleDTO> createAdminRole() {
        RoleCreation adminRole = RoleCreation.builder()
                .name("admin")
                .level(0)
                .build();
        RoleDTO created = roleService.createRole(adminRole);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/create-user")
    public ResponseEntity<RoleDTO> createUserRole() {
        RoleCreation adminRole = RoleCreation.builder()
                .name("user")
                .level(1)
                .build();
        RoleDTO created = roleService.createRole(adminRole);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/create-seller")
    public ResponseEntity<RoleDTO> createSellerRole() {
        RoleCreation adminRole = RoleCreation.builder()
                .name("seller")
                .level(2)
                .build();
        RoleDTO created = roleService.createRole(adminRole);
        return ResponseEntity.ok(created);
    }
}
