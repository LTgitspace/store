package com.LT.store.service.auth;

import com.LT.store.dto.user.UserDTO;
import com.LT.store.dto.user.UserInfoDTO;
import com.LT.store.model.user.User;
import com.LT.store.repository.user.UserRepository;
import com.LT.store.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoService userInfoService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDTO SignUp(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        String hashedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .username(username)
                .password(hashedPassword)
                .build();
        User savedUser = userRepository.save(user);
        // Create UserInfo record for the new user
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .userId(savedUser.getId())
                .fullName("")
                .email("")
                .phoneNumber("")
                .avatarUrl("")
                .roleId(null)
                .build();
        userInfoService.create(userInfoDTO);
        return mapToDTO(savedUser);
    }

    @Override
    public UserDTO SignIn(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return mapToDTO(user);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
