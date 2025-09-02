package com.LT.store.service.role;

import com.LT.store.dto.role.RoleDTO;
import com.LT.store.dto.role.RoleCreation;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleDTO createRole(RoleCreation roleCreation);
    RoleDTO getRoleById(UUID id);
    List<RoleDTO> getAllRoles();
    RoleDTO updateRole(UUID id, RoleCreation roleCreation);
    void deleteRole(UUID id);
    List<RoleDTO> searchRolesByName(String name);
    RoleDTO getRoleByLevel(int level);
}
