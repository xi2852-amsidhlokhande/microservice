package com.amsidh.mvc.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    private Integer addressId;
    private String city;
    private String state;
    private Long pinCode;

    private Integer userId;
}
