package com.coditas.democoditas2.controller;

import com.coditas.democoditas2.domain.Address;
import com.coditas.democoditas2.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;


    @PostMapping()
    public String createAddress(@RequestParam String fullAddress, @RequestParam String addressType, @RequestParam String city, @RequestParam String state, @RequestParam String country, @RequestParam Integer pincode) {
        addressRepository.save(new Address(fullAddress, addressType, city, state, country, pincode));
        return addressRepository.findByName(fullAddress, addressType, city, state, country, pincode) + "saved successfully";
    }

    @GetMapping()
    public List<Address> getAllAddress() {
        return (List<Address>) addressRepository.findAll();
    }

}
