package com.amsidh.mvc.controller;

import com.amsidh.mvc.model.Address;
import com.amsidh.mvc.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/user/{userId}")
    public List<Address> getAddressForUser(@PathVariable("userId") Integer userId) {
        return addressService.getAddressByUser(userId);
    }
}
