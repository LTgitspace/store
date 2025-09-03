package com.LT.store.controller.user;

import com.LT.store.dto.user.UserInfoDTO;
import com.LT.store.service.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userinfo")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(userInfoService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserInfoDTO> getByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(userInfoService.getByUserId(userId));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserInfoDTO> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userInfoService.getByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserInfoDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userInfoService.getByEmail(email));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserInfoDTO>> getAllByRoleId(@PathVariable UUID roleId) {
        return ResponseEntity.ok(userInfoService.getAllByRoleId(roleId));
    }

    @PostMapping
    public ResponseEntity<UserInfoDTO> create(@RequestBody UserInfoDTO dto) {
        return ResponseEntity.ok(userInfoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoDTO> update(@PathVariable UUID id, @RequestBody UserInfoDTO dto) {
        return ResponseEntity.ok(userInfoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}