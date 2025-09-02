package com.LT.store.repository.role;

import com.LT.store.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByNameContainingIgnoreCase(String name);
    Optional<Role> findByLevel(int level);
}
