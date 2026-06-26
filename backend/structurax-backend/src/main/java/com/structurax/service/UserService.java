package com.structurax.service;

import com.structurax.dto.LoginRequest;
import com.structurax.dto.LoginResponse;
import com.structurax.dto.UserRequest;
import com.structurax.entity.User;
import com.structurax.exception.UserAlreadyExistsException;
import com.structurax.repository.UserRepository;
import com.structurax.util.JwtUtil;
import com.structurax.dto.UserResponse;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(UserRequest request) {

        if (userRepository.findByEmail(
                request.getEmail()).isPresent()) {

            throw new UserAlreadyExistsException(
                    "Email already registered");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword())
        );

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public LoginResponse loginUser(
            LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(
                        request.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException(
                    "User Not Found");
        }

        User user = optionalUser.get();

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
        jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );      

        return new LoginResponse(token);
    }

public UserResponse getCurrentUser(
        String email) {

    User user =
            userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "User not found"));

    return new UserResponse(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getRole()
    );
}

public List<UserResponse> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(user -> new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getRole()
            ))
            .collect(Collectors.toList());
}

public UserResponse getUserById(Long id) {

    User user = userRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"));

    return new UserResponse(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getRole()
    );
}

public UserResponse updateUserRole(
        Long id,
        String role) {

    User user = userRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"));

    user.setRole(role);

    User updatedUser =
            userRepository.save(user);

    return new UserResponse(
            updatedUser.getId(),
            updatedUser.getFullName(),
            updatedUser.getEmail(),
            updatedUser.getRole()
    );
}


public void deleteUser(Long id) {

    User user = userRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found"));

    userRepository.delete(user);
}



}