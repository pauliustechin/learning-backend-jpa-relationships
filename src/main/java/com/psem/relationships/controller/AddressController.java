package com.psem.relationships.controller;

import com.psem.relationships.model.Address;
import com.psem.relationships.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Add some annotations to customize swagger documentation.
    @Tag(name="Address API", description = "API for managing addresses")
    @Operation(summary = "Update address", description = "API to update address")
    // Since API responses pretty informative by itself, just for practising add it on address section.
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated"),
            @ApiResponse(responseCode = "404", description = "Address with provided id is not found")
    })
    // Since target was to learn to handle jpa relationships, added @PutMapping only for addresses.
    @PutMapping("/addresses/{addressId}")
    private ResponseEntity<Address> updateAddress(@Parameter(description = "Address you wish to delete")
                                                  @RequestBody Address address,
                                                  @PathVariable Long addressId){

        Address updatedAddress = addressService.updateAddress(address, addressId);

        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }


}
