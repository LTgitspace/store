package com.LT.store.service.user;

import com.LT.store.dto.user.UserInfoDTO;
import com.LT.store.model.user.UserInfo;
import com.LT.store.repository.user.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImplementation implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserInfoDTO getById(UUID id) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserInfo not found"));
        return mapToDTO(userInfo);
    }

    @Override
    public UserInfoDTO getByUserId(UUID userId) {
        UserInfo userInfo = userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("UserInfo not found"));
        return mapToDTO(userInfo);
    }

    @Override
    public UserInfoDTO getByUsername(String username) {
        UserInfo userInfo = userInfoRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("UserInfo not found"));
        return mapToDTO(userInfo);
    }

    @Override
    public UserInfoDTO getByEmail(String email) {
        UserInfo userInfo = userInfoRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("UserInfo not found"));
        return mapToDTO(userInfo);
    }

    @Override
    public List<UserInfoDTO> getAllByRoleId(UUID roleId) {
        return userInfoRepository.findAllByRoleId(roleId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoDTO create(UserInfoDTO dto) {
        UserInfo userInfo = mapToEntity(dto);
        UserInfo saved = userInfoRepository.save(userInfo);
        return mapToDTO(saved);
    }

    @Override
    public UserInfoDTO update(UUID id, UserInfoDTO dto) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserInfo not found"));
        userInfo.setFullName(dto.getFullName());
        userInfo.setEmail(dto.getEmail());
        userInfo.setPhoneNumber(dto.getPhoneNumber());
        userInfo.setAvatarUrl(dto.getAvatarUrl());
        // Role and User update logic can be added if needed
        UserInfo updated = userInfoRepository.save(userInfo);
        return mapToDTO(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!userInfoRepository.existsById(id)) {
            throw new RuntimeException("UserInfo not found");
        }
        userInfoRepository.deleteById(id);
    }

    private UserInfoDTO mapToDTO(UserInfo userInfo) {
        return UserInfoDTO.builder()
                .id(userInfo.getId())
                .userId(userInfo.getUser() != null ? userInfo.getUser().getId() : null)
                .fullName(userInfo.getFullName())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .avatarUrl(userInfo.getAvatarUrl())
                .roleId(userInfo.getRole() != null ? userInfo.getRole().getId() : null)
                .build();
    }

    private UserInfo mapToEntity(UserInfoDTO dto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFullName(dto.getFullName());
        userInfo.setEmail(dto.getEmail());
        userInfo.setPhoneNumber(dto.getPhoneNumber());
        userInfo.setAvatarUrl(dto.getAvatarUrl());
        // User and Role should be set by fetching from their repositories if needed
        return userInfo;
    }
}
