package com.LT.store.service.role;

import com.LT.store.dto.role.RoleDTO;
import com.LT.store.dto.role.RoleCreation;
import com.LT.store.model.role.Role;
import com.LT.store.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleDTO createRole(RoleCreation roleCreation) {
        if (roleRepository.findByNameContainingIgnoreCase(roleCreation.getName()).isPresent()) {
            throw new RuntimeException("Role name already exists");
        }
        if (roleRepository.findByLevel(roleCreation.getLevel()).isPresent()) {
            throw new RuntimeException("Role level already exists");
        }
        Role role = Role.builder()
                .name(roleCreation.getName())
                .level(roleCreation.getLevel())
                .build();
        Role saved = roleRepository.save(role);
        return mapToDTO(saved);
    }

    @Override
    public RoleDTO getRoleById(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return mapToDTO(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO updateRole(UUID id, RoleCreation roleCreation) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleCreation.getName());
        role.setLevel(roleCreation.getLevel());
        Role updated = roleRepository.save(role);
        return mapToDTO(updated);
    }

    @Override
    public void deleteRole(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDTO> searchRolesByName(String name) {
        Optional<Role> roleOpt = roleRepository.findByNameContainingIgnoreCase(name);
        return roleOpt.map(role -> List.of(mapToDTO(role))).orElse(List.of());
    }

    @Override
    public RoleDTO getRoleByLevel(int level) {
        Role role = roleRepository.findByLevel(level)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return mapToDTO(role);
    }

    private RoleDTO mapToDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .level(role.getLevel())
                .build();
    }
}

