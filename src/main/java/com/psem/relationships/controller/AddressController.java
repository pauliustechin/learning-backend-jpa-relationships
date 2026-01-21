package com.psem.relationships.controller;

import com.psem.relationships.model.Address;
import com.psem.relationships.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Since target was to learn to handle jpa relationships, added @PutMapping only for addresses.
    @PutMapping("/addresses/{addressId}")
    private ResponseEntity<Address> updateAddress(@RequestBody Address address,
                                                  @PathVariable Long addressId){

        Address updatedAddress = addressService.updateAddress(address, addressId);

        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }


}
