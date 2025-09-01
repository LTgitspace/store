package com.LT.store.service.auth;

import com.LT.store.dto.user.UserDTO;

public interface AuthenticationService {
    UserDTO SignUp(String username, String password);
    UserDTO SignIn(String username, String password);
}
