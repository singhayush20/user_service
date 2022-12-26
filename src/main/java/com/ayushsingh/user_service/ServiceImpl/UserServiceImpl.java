package com.ayushsingh.user_service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayushsingh.user_service.Service.UserService;
import com.ayushsingh.user_service.dto.UserDto;
import com.ayushsingh.user_service.entities.User;
import com.ayushsingh.user_service.exceptions.DuplicateResourceException;
import com.ayushsingh.user_service.exceptions.ResourceNotFoundException;
import com.ayushsingh.user_service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
   
    @Autowired
    ModelMapper modelMapper;
    @Override
    public boolean deleteUser(String userId) {

        try {
            this.userRepository.deleteById(userId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(this.userToDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto getUser(String userId) {
        User user = this.userRepository.findById(userId).get();
        if (user == null) {
            throw new ResourceNotFoundException("User", "user id", userId);
        }
        return this.userToDto(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
           User user = this.userRepository.save(this.dtoToUser(userDto));
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = this.userRepository.findById(userDto.getUserId()).get();
        if (user != null) {
            user.setAbout(userDto.getAbout());
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            user.setPhoneNo(userDto.getPhoneNo());
            user = this.userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User", "user id", userDto.getUserId());
        }
        return this.userToDto(user);
    }

    private UserDto userToDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }

    private User dtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto, User.class);
    }
}
