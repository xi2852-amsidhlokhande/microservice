package com.amsidh.mvc.service;

import com.amsidh.mvc.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddressByUser(Integer userId);

}
