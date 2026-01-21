package com.psem.relationships.service;

import com.psem.relationships.exceptions.ResourceNotFoundException;
import com.psem.relationships.model.Address;
import com.psem.relationships.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address updateAddress(Address address, Long addressId) {

        // get address from DB by provided ID.
        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address with id: " + addressId + " not found"));

        // update address info with the one provided in request body.
        addressFromDb.setCity(address.getCity());
        addressFromDb.setStreet(address.getStreet());
        addressFromDb.setCountry(address.getCountry());
        addressFromDb.setZipCode(address.getZipCode());

        // save updated address into DB
        Address updatedAddress = addressRepository.save(addressFromDb);

        return updatedAddress;
    }
}
