package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.exception.UserNotFoundException;
import com.amsidh.mvc.model.Address;
import com.amsidh.mvc.model.User;
import com.amsidh.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    @Value("${address.service.url: http://address-service/address/user/{userId}}")
    private String addressServiceUrl;

    private final static List<User> USERS = Arrays.asList(
            User.builder().userId(1).userName("Amsidh Lokhande").build(),
            User.builder().userId(2).userName("Anjali Lokhande").build(),
            User.builder().userId(3).userName("Adithi Lokhande").build(),
            User.builder().userId(4).userName("Aditya Lokhande").build()
    );

    @Override
    public User getUser(Integer userId) {
        User user = USERS.stream().filter(u -> u.getUserId().equals(userId)).findAny().orElseThrow(() -> new UserNotFoundException(String.format("User not found with userId %d", userId)));
        try {
            ResponseEntity<List<Address>> response = restTemplate.exchange(UriComponentsBuilder.fromHttpUrl(addressServiceUrl).build(userId), HttpMethod.GET, null, new ParameterizedTypeReference<List<Address>>() {
            });

            if (response.getStatusCode().is2xxSuccessful()) {
                user.setAddresses(response.getBody());
            }
        } catch (Exception exception) {
            log.info("No address found for user userId {}", userId);
        }

        return user;
    }
}
