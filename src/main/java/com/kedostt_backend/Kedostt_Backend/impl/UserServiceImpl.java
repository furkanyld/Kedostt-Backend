package com.kedostt_backend.Kedostt_Backend.service.impl;

import com.kedostt_backend.Kedostt_Backend.dto.RegisterRequest;
import com.kedostt_backend.Kedostt_Backend.dto.UserDto;
import com.kedostt_backend.Kedostt_Backend.model.Role;
import com.kedostt_backend.Kedostt_Backend.model.User;
import com.kedostt_backend.Kedostt_Backend.repository.RoleRepository;
import com.kedostt_backend.Kedostt_Backend.repository.UserRepository;
import com.kedostt_backend.Kedostt_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found."));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(userRole));

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDto(user);
    }

    @Override
    public UserDto getCurrentUser(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found."));

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode("123456")); // Default password, ileride değiştirilebilir
        user.setRoles(Set.of(userRole));

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public User createAdmin(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found."));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(adminRole));

        return userRepository.save(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public UserDto updateOwnUser(String username, UserDto userDto){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı!"));

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    @Override
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepository.delete(user);
        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {
        String role = user.getRoles().stream()
                .map(Role::getName)
                .findFirst()
                .orElse("UNKNOWN");

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(role)
                .build();
    }
}
