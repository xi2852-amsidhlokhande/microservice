package com.amsidh.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user-service")
    public String callFallbackForUserServiceDown() {
        return "Looks like user-service currently down or under maintenance mode";
    }

    @GetMapping("/address-service")
    public String callFallbackForAddressServiceDown() {
        return "Looks like address-service currently down or under maintenance mode";
    }
}
