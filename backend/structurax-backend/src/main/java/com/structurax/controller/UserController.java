package com.structurax.controller;

import com.structurax.dto.LoginRequest;
import com.structurax.dto.LoginResponse;
import com.structurax.dto.UserRequest;
import com.structurax.entity.User;
import com.structurax.service.UserService;
import com.structurax.util.ApiResponse;
import com.structurax.dto.UserResponse;
import com.structurax.dto.RoleUpdateRequest;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<User> registerUser(
            @Valid @RequestBody UserRequest request) {

        User user = userService.registerUser(request);

        return new ApiResponse<>(
                true,
                "User Registered Successfully",
                user);
    }

    @PostMapping("/login")
public LoginResponse loginUser(
        @Valid @RequestBody LoginRequest request) {

    return userService.loginUser(request);
}

@GetMapping("/me")
public UserResponse getCurrentUser(
        Authentication authentication) {

    String email =
            authentication.getName();

    return userService.getCurrentUser(email);
}

@GetMapping
public List<UserResponse> getAllUsers() {

    return userService.getAllUsers();
}

@GetMapping("/{id}")
public UserResponse getUserById(
        @PathVariable Long id) {

    return userService.getUserById(id);
}

@PutMapping("/{id}/role")
public UserResponse updateUserRole(
        @PathVariable Long id,
        @RequestBody RoleUpdateRequest request) {

    return userService.updateUserRole(
            id,
            request.getRole());
}

@DeleteMapping("/{id}")
public String deleteUser(
        @PathVariable Long id) {

    userService.deleteUser(id);

    return "User Deleted Successfully";
}
}