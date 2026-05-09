package com.john.inflow.service;

import com.john.inflow.dto.request.LoginRequest;
import com.john.inflow.dto.request.RegisterRequest;
import com.john.inflow.dto.response.AuthResponse;
import com.john.inflow.dto.response.UserResponse;
import com.john.inflow.entity.Role;
import com.john.inflow.entity.User;
import com.john.inflow.repository.RoleRepository;
import com.john.inflow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new RuntimeException("User not found"));
        // Assuming plain text for prototype or just simple check.
        if (!user.getPasswordHash().equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new AuthResponse("dummy-jwt-token-" + user.getId(), toUserResponse(user));
    }

    public AuthResponse register(RegisterRequest request) {
        Role role = roleRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("No roles found"));
        User user = User.builder()
            .username(request.username())
            .firstName(request.firstName())
            .lastName(request.lastName())
            .phoneNumber(request.phoneNumber())
            .email(request.email())
            .passwordHash(request.password()) // In real app, hash it
            .role(role)
            .createdAt(OffsetDateTime.now())
            .build();
        userRepository.save(user);
        return new AuthResponse("dummy-jwt-token-" + user.getId(), toUserResponse(user));
    }

    public UserResponse getMe(String token) {
        if (token != null && token.startsWith("Bearer dummy-jwt-token-")) {
            Integer id = Integer.parseInt(token.substring("Bearer dummy-jwt-token-".length()));
            return userRepository.findById(id).map(this::toUserResponse).orElseThrow();
        }
        return userRepository.findAll().stream().findFirst().map(this::toUserResponse).orElseThrow();
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getRole().getName(),
            user.getCreatedAt(),
            user.getLeftAt()
        );
    }
}
