package com.ayushsingh.user_service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ayushsingh.user_service.Service.UserService;
import com.ayushsingh.user_service.dto.UserDto;
import com.ayushsingh.user_service.entities.Hotel;
import com.ayushsingh.user_service.entities.Rating;
import com.ayushsingh.user_service.entities.User;
import com.ayushsingh.user_service.exceptions.ResourceNotFoundException;
import com.ayushsingh.user_service.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    final private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    final ObjectMapper mapper = new ObjectMapper();

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
        // fetch ratings of the above user from rating service
        // rating service should have an api to send the ratings
        // whenever one service calls another it is done using HTTP client.
        // Therefore, this service should have a HTTP client which can call the HTTP
        // server with
        // with the help of HTTP api
        // we can use RestTemplate

        // fetch the ratings response
        Map<?, ?> ratingsResponse = restTemplate
                .getForEntity("http://localhost:8083/microservices/rating/get-all-ratings-by-user?userId=" + userId,
                        Map.class)
                .getBody();

        if (ratingsResponse != null && ratingsResponse.isEmpty() == false) {
            // get the list of ratings map
            List<Map<?, ?>> ratingsResult = (List<Map<?, ?>>) ratingsResponse.get("data");

            // convert to list of Rating objects
            List<Rating> ratings = new ArrayList<>();
            for (Map<?, ?> rating : ratingsResult) {
                System.out.println("Current rating: " + rating);
                ratings.add(mapper.convertValue(rating, Rating.class));
            }
            // for each rating also fetch the hotel
            ratings.forEach((rating) -> {

                Map<?, ?> hotelResult = (Map<?, ?>) restTemplate.getForEntity(
                        "http://localhost:8086/microservices/hotel/get-hotel-by-id?hotelId=" + rating.getHotelId(),
                        Map.class).getBody();
                if (hotelResult != null) {
                    Hotel hotel = mapper.convertValue(hotelResult.get("data"), Hotel.class);
                    logger.info("fetched hotel: ", hotel);
                    rating.setHotel(hotel);
                }
            });
            user.setRatings(ratings);
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
