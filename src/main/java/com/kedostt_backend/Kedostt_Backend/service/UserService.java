package com.kedostt_backend.Kedostt_Backend.service;

import com.kedostt_backend.Kedostt_Backend.dto.RegisterRequest;
import com.kedostt_backend.Kedostt_Backend.dto.UserDto;
import com.kedostt_backend.Kedostt_Backend.model.User;

import java.util.List;

public interface UserService {
    UserDto registerUser(RegisterRequest registerRequest);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto getCurrentUser(String username);
    UserDto createUser(UserDto userDto);
    User createAdmin(User user);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto updateOwnUser(String username, UserDto userDto);
    UserDto deleteUser(Long id);
}
