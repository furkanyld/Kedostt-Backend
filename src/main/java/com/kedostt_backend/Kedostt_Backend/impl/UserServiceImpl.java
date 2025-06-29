package com.kedostt_backend.Kedostt_Backend.impl;

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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public User createUser(User user) {
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("Username is already taken");
        if(userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email is already in use");

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        user.setRoles(Set.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User createAdmin(User user) {
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("Username is already taken");
        if(userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email is already in use");

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
        user.setRoles(Set.of(adminRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());

        if(userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()){
            user.setPassword(userDetails.getPassword());
        }

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Kullanıcı Bulunamadı: "+id));
        userRepository.deleteById(id);
        return user;
    }
}

