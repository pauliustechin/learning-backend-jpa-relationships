package com.psem.relationships.service;

import com.psem.relationships.model.Address;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    Address updateAddress(Address address, Long addressId);
}
