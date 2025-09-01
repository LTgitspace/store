package com.LT.store.repository.user;

import com.LT.store.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByUserId(UUID userId);
    Optional<UserInfo> findByUserUsername(String username);

    Optional<UserInfo> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserId(UUID userId);

    List<UserInfo> findAllByRoleId(UUID roleId);
}
