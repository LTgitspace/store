package com.LT.store.service.user;

import com.LT.store.dto.user.UserInfoDTO;
import java.util.List;
import java.util.UUID;

public interface UserInfoService {
    UserInfoDTO getById(UUID id);
    UserInfoDTO getByUserId(UUID userId);
    UserInfoDTO getByUsername(String username);
    UserInfoDTO getByEmail(String email);
    List<UserInfoDTO> getAllByRoleId(UUID roleId);
    UserInfoDTO create(UserInfoDTO dto);
    UserInfoDTO update(UUID id, UserInfoDTO dto);
    void delete(UUID id);
}
