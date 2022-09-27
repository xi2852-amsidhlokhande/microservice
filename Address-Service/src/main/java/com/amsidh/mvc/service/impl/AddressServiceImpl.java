package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.exception.AddressNotFoundException;
import com.amsidh.mvc.model.Address;
import com.amsidh.mvc.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final static List<Address> ADDRESSES = Arrays.asList(
            Address.builder().addressId(1).city("Pune").state("Maharashtra").pinCode(412105L).userId(1).build(),
            Address.builder().addressId(2).city("Mumbai").state("Maharashtra").pinCode(400011L).userId(1).build(),
            Address.builder().addressId(3).city("Lohegaon").state("Maharashtra").pinCode(411047L).userId(1).build(),

            Address.builder().addressId(4).city("Pune").state("Maharashtra").pinCode(412105L).userId(2).build(),
            Address.builder().addressId(5).city("Lohegaon").state("Maharashtra").pinCode(411047L).userId(2).build(),


            Address.builder().addressId(6).city("Pune").state("Maharashtra").pinCode(412105L).userId(3).build(),
            Address.builder().addressId(7).city("Lohegaon").state("Maharashtra").pinCode(411047L).userId(3).build(),
            Address.builder().addressId(8).city("Bijapur").state("Maharashtra").pinCode(586119L).userId(3).build(),
            Address.builder().addressId(9).city("Akurdi").state("Maharashtra").pinCode(412116L).userId(3).build()

    );

    @Override
    public List<Address> getAddressByUser(Integer userId) {
        List<Address> addresses = ADDRESSES.stream().filter(address -> address.getUserId().equals(userId)).collect(Collectors.toList());
        if (addresses.isEmpty()) {
            throw new AddressNotFoundException(String.format("No address found for userId %d", userId));
        }
        return addresses;
    }
}
