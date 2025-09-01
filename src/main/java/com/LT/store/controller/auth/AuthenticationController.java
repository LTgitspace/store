package com.LT.store.controller.auth;

import com.LT.store.dto.user.UserDTO;
import com.LT.store.dto.auth.AuthenticationDTO;
import com.LT.store.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody AuthenticationDTO authDTO) {
        UserDTO userDTO = authenticationService.SignUp(authDTO.getUsername(), authDTO.getPasssword());
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> signIn(@RequestBody AuthenticationDTO authDTO) {
        UserDTO userDTO = authenticationService.SignIn(authDTO.getUsername(), authDTO.getPasssword());
        return ResponseEntity.ok(userDTO);
    }
}
