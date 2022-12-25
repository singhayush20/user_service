package com.ayushsingh.user_service.Service;

import java.util.List;

import com.ayushsingh.user_service.dto.UserDto;

public interface UserService {
    // user operations
    UserDto saveUser(UserDto userDto);

    // get all Users
    List<UserDto> getAllUsers();

    // get user by id
    UserDto getUser(String userId);

    // update user
    UserDto updateUser(UserDto userDto);

    // delete user
    boolean deleteUser(String userId);
}
